/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.seller.addinventory.web;

import com.seller.addinventory.entity.TalProductIn;
import com.seller.addinventory.service.TalProductInService;
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
 * 入库Controller
 * @author wn
 * @version 2017-12-29
 */
@Controller
@RequestMapping(value = "${adminPath}/addinventory/talProductIn")
public class TalProductInController extends BaseController {

	@Autowired
	private TalProductInService talProductInService;

	@Autowired
	private TalProductService talProductService;
	
	@ModelAttribute
	public TalProductIn get(@RequestParam(required=false) String id) {
		TalProductIn entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = talProductInService.get(id);
		}
		if (entity == null){
			entity = new TalProductIn();
		}
		return entity;
	}
	
	@RequiresPermissions("addinventory:talProductIn:view")
	@RequestMapping(value = {"list", ""})
	public String list(TalProductIn talProductIn, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		//获产品列表
		TalProduct talProduct = new TalProduct();
		//判断是不是管理员，管理员可以看全部
		if(user.getId().equals("1")){
			talProductIn.setUser(null);
			talProduct.setUser(null);
		}else{
			talProductIn.setUser(user);
			talProduct.setUser(user);
		}
		Page<TalProductIn> page = talProductInService.findPage(new Page<TalProductIn>(request, response), talProductIn);


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

		model.addAttribute("talProductMap",talProductMap);
		model.addAttribute("page", page);
		return "seller/addinventory/talProductInList";
	}

	@RequiresPermissions("addinventory:talProductIn:view")
	@RequestMapping(value = "form")
	public String form(TalProductIn talProductIn, Model model) {
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
		model.addAttribute("talProductIn", talProductIn);
		return "seller/addinventory/talProductInForm";
	}

	@RequiresPermissions("addinventory:talProductIn:edit")
	@RequestMapping(value = "save")
	public String save(TalProductIn talProductIn, Model model, RedirectAttributes redirectAttributes) {
		TalProduct talProduct =new TalProduct();

		if (!beanValidator(model, talProductIn)){
			return form(talProductIn, model);
		}
		try{
			//写入用户信息
			User user = UserUtils.getUser();
			talProductIn.setUser(user);
			talProduct = talProductService.get(talProductIn.getProduct().getId());


			//增加总库存
			double toalnum =0;
			if(Double.valueOf(talProductIn.getAddinventory())>0){
				 toalnum = Double.valueOf(talProduct.getTallinventory())+Double.valueOf(talProductIn.getAddinventory());
			}else{
				 toalnum =Double.valueOf(talProduct.getTallinventory());
			}
			talProduct.setTallinventory(String.valueOf(toalnum));
			//增加剩余库存
			double lastnum = Double.valueOf(talProduct.getNowinventory())+Double.valueOf(talProductIn.getAddinventory());
			talProduct.setNowinventory(String.valueOf(lastnum));

			talProductInService.save(talProductIn);
			talProductService.save(talProduct);
			addMessage(redirectAttributes, "保存入库成功");
		}catch (Exception e){
			addMessage(redirectAttributes, "保存入库失败");
		}

		return "redirect:"+Global.getAdminPath()+"/addinventory/talProductIn/?repage";
	}
	
	@RequiresPermissions("addinventory:talProductIn:edit")
	@RequestMapping(value = "delete")
	public String delete(TalProductIn talProductIn, RedirectAttributes redirectAttributes) {
		talProductInService.delete(talProductIn);
		addMessage(redirectAttributes, "删除入库成功");
		return "redirect:"+Global.getAdminPath()+"/addinventory/talProductIn/?repage";
	}

}