/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.seller.minusinventory.web;

import com.seller.minusinventory.entity.TalProductOut;
import com.seller.minusinventory.service.TalProductOutService;
import com.seller.productinven.entity.TalProduct;
import com.seller.productinven.service.TalProductService;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 销售Controller
 * @author wn
 * @version 2017-12-30
 */
@Controller
@RequestMapping(value = "${adminPath}/minusinventory/talProductOut")
public class TalProductOutController extends BaseController {

	@Autowired
	private TalProductOutService talProductOutService;
	@Autowired
	private TalProductService talProductService;
	
	@ModelAttribute
	public TalProductOut get(@RequestParam(required=false) String id) {
		TalProductOut entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = talProductOutService.get(id);
		}
		if (entity == null){
			entity = new TalProductOut();
		}
		return entity;
	}
	
	@RequiresPermissions("minusinventory:talProductOut:view")
	@RequestMapping(value = {"list", ""})
	public String list(TalProductOut talProductOut, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		//获产品列表
		TalProduct talProduct = new TalProduct();
		//判断是不是管理员，管理员可以看全部
		if(user.getId().equals("1")){
			talProductOut.setUser(null);
			talProduct.setUser(null);
		}else{
			talProductOut.setUser(user);
			talProduct.setUser(user);
		}

		Page<TalProductOut> page = talProductOutService.findPage(new Page<TalProductOut>(request, response), talProductOut);

		//设定权限，管理员看全部，自己只能查看自己

		List<TalProduct> talProductList= talProductService.findList(talProduct);

		//获取产品列表
		Map<String,String> talProductMap = new HashMap<String,String>();
		for(int i=0;i<talProductList.size();i++){
			talProductMap.put(talProductList.get(i).getId(),talProductList.get(i).getProductname());
		}
		//加入用户信息
		for(int i=0;i<page.getList().size();i++){
			//人员信息
			page.getList().get(i).setUser(UserUtils.get(page.getList().get(i).getCreateBy().getId()));
			//添加产品信息
			page.getList().get(i).setProduct(talProductService.get(page.getList().get(i).getProduct()));
		}

		model.addAttribute("talProductMap", talProductMap);
		model.addAttribute("page", page);
		return "seller/minusinventory/talProductOutList";
	}

	@RequiresPermissions("minusinventory:talProductOut:view")
	@RequestMapping(value = "form")
	public String form(TalProductOut talProductOut, Model model) {

		User user = UserUtils.getUser();
		//获产品列表
		TalProduct talProduct = new TalProduct();
		talProduct.setUser(user);
		List<TalProduct> talProductList= talProductService.findList(talProduct);
		//获取产品列表
		Map<String,String> talProductMap = new HashMap<String,String>();

		for(int i=0;i<talProductList.size();i++){
			talProductMap.put(talProductList.get(i).getId(),talProductList.get(i).getProductname());
		}
		model.addAttribute("talProductMap",talProductMap);
		model.addAttribute("talProductOut", talProductOut);
		return "seller/minusinventory/talProductOutForm";
	}

	@RequiresPermissions("minusinventory:talProductOut:edit")
	@RequestMapping(value = "save")
	public String save(TalProductOut talProductOut, Model model, RedirectAttributes redirectAttributes) {
		TalProduct talProduct =new TalProduct();
		if (!beanValidator(model, talProductOut)){
			return form(talProductOut, model);
		}
		try{
			//写入用户信息
			User user = UserUtils.getUser();
			talProductOut.setUser(user);
			talProduct = talProductService.get(talProductOut.getProduct().getId());
			int minusNum =Integer.valueOf(talProduct.getNowinventory())-Integer.valueOf(talProductOut.getMinusinventory());
			if(minusNum>=0){

				if(talProductOut.getId().equals("")){
					//没有记录，则是新增，可以直接更新库存
					talProductOutService.save(talProductOut);
					talProduct.setNowinventory(String.valueOf(minusNum));
					talProductService.save(talProduct);
				}else{
					//如果有记录，则需要先讲库存增加，在减少
					int minusNum2 =Integer.valueOf(talProduct.getNowinventory())+Integer.valueOf(talProductOutService.get(talProductOut.getId()).getMinusinventory())-Integer.valueOf(talProductOut.getMinusinventory());
					talProduct.setNowinventory(String.valueOf(minusNum2));
					talProductService.save(talProduct);
					talProductOutService.save(talProductOut);
				}
				addMessage(redirectAttributes, "保存销售成功");
			}else{
				addMessage(redirectAttributes, "库存不足");
			}

		}catch (Exception e){
			addMessage(redirectAttributes, "保存销售失败");
		}




		return "redirect:"+Global.getAdminPath()+"/minusinventory/talProductOut/?repage";
	}
	
	@RequiresPermissions("minusinventory:talProductOut:edit")
	@RequestMapping(value = "delete")
	public String delete(TalProductOut talProductOut, RedirectAttributes redirectAttributes) {
		//删除记录 先补回库存
		try{
			TalProduct talProduct =new TalProduct();
			talProduct =  talProductService.get(talProductOut.getProduct().getId());
			int minusNum2 =Integer.valueOf(talProduct.getNowinventory())+Integer.valueOf(talProductOutService.get(talProductOut.getId()).getMinusinventory());
			talProduct.setNowinventory(String.valueOf(minusNum2));
			talProductService.save(talProduct);
			talProductOutService.delete(talProductOut);
			addMessage(redirectAttributes, "删除销售成功");
		}catch (Exception e){
			addMessage(redirectAttributes, "删除销售失败");
		}

		return "redirect:"+Global.getAdminPath()+"/minusinventory/talProductOut/?repage";
	}

}