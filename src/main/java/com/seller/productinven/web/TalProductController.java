/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.seller.productinven.web;

import com.seller.material.entity.TalMaterial;
import com.seller.material.service.TalMaterialService;
import com.seller.modelnum.entity.TalModelnum;
import com.seller.modelnum.service.TalModelnumService;
import com.seller.productinven.entity.TalProduct;
import com.seller.productinven.service.TalProductService;
import com.seller.spec.entity.TalSpec;
import com.seller.spec.service.TalSpecService;
import com.seller.talcolor.entity.TalColor;
import com.seller.talcolor.service.TalColorService;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 库存Controller
 * @author wn
 * @version 2017-12-28
 */
@Controller
@RequestMapping(value = "${adminPath}/productinven/talProduct")
public class TalProductController extends BaseController {

	@Autowired
	private TalProductService talProductService;

	//款号
	@Autowired
	private TalModelnumService talModelnumService;
	//颜色
	@Autowired
	private TalColorService talColorService;
	//规格
	@Autowired
	private TalSpecService talSpecService;
	//材质
	@Autowired
	private TalMaterialService talMaterialService;

	
	@ModelAttribute
	public TalProduct get(@RequestParam(required=false) String id) {
		TalProduct entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = talProductService.get(id);
		}
		if (entity == null){
			entity = new TalProduct();
		}
		return entity;
	}
	
	@RequiresPermissions("productinven:talProduct:view")
	@RequestMapping(value = {"list", ""})
	public String list(TalProduct talProduct, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		Double allNum =0.0;
		//判断是不是管理员，管理员可以看全部
		if(user.getId().equals("1")){
			talProduct.setUser(null);
		}else{
			talProduct.setUser(user);
		}
		Page<TalProduct> page = talProductService.findPage(new Page<TalProduct>(request, response), talProduct);
		//加入用户信息
		for(int i=0;i<page.getList().size();i++){
			page.getList().get(i).setUser(UserUtils.get(page.getList().get(i).getCreateBy().getId()));
			allNum = allNum+ Double.valueOf(page.getList().get(i).getNowinventory());
		}

        //获取颜色列表
        TalColor talColor = new TalColor();
        talColor.setUser(user);
        List<TalColor> colorList= talColorService.findList(talColor);
        //获取颜色名字列表
        List<String> colorNameList = new ArrayList();
        for(int i=0;i<colorList.size();i++){
            colorNameList.add(colorList.get(i).getColorname());
        }

        //获取款号列表
        TalModelnum talModelnum = new TalModelnum();
        talModelnum.setUser(user);
        List<TalModelnum> talModelnumList= talModelnumService.findList(talModelnum);
        //获取款号名字列表
        List<String> modelnumNameList = new ArrayList();
        for(int i=0;i<talModelnumList.size();i++){
            modelnumNameList.add(talModelnumList.get(i).getModelnum());
        }

        //获取材质列表
        TalMaterial talMaterial = new TalMaterial();
        talMaterial.setUser(user);
        List<TalMaterial> talMaterialList= talMaterialService.findList(talMaterial);
        //获取材质名字列表
        List<String> materialNameList = new ArrayList();
        for(int i=0;i<talMaterialList.size();i++){
            materialNameList.add(talMaterialList.get(i).getMaterial());
        }

        //获规格列表
        TalSpec talSpec = new TalSpec();
        talSpec.setUser(user);
        List<TalSpec> talSpecList= talSpecService.findList(talSpec);
        //获取规格名字列表
        List<String> specNameList = new ArrayList();
        for(int i=0;i<talSpecList.size();i++){
            specNameList.add(talSpecList.get(i).getSpec());
        }


        model.addAttribute("colorNameList",colorNameList);
        model.addAttribute("modelnumNameList",modelnumNameList);
        model.addAttribute("materialNameList",materialNameList);
        model.addAttribute("specNameList",specNameList);
		model.addAttribute("allNum",allNum);
		model.addAttribute("page", page);
		return "seller/productinven/talProductList";
	}

	@RequiresPermissions("productinven:talProduct:view")
	@RequestMapping(value = "form")
	public String form(TalProduct talProduct, Model model) {
		//定义用户信息
		User user = UserUtils.getUser();
        talProduct.setUser(user);
		//获取颜色列表
		TalColor talColor = new TalColor();
		talColor.setUser(user);
		List<TalColor> colorList= talColorService.findList(talColor);
        //获取颜色名字列表
        List<String> colorNameList = new ArrayList();
        for(int i=0;i<colorList.size();i++){
            colorNameList.add(colorList.get(i).getColorname());
        }

		//获取款号列表
		TalModelnum talModelnum = new TalModelnum();
		talModelnum.setUser(user);
		List<TalModelnum> talModelnumList= talModelnumService.findList(talModelnum);
        //获取款号名字列表
        List<String> modelnumNameList = new ArrayList();
        for(int i=0;i<talModelnumList.size();i++){
            modelnumNameList.add(talModelnumList.get(i).getModelnum());
        }

        //获取材质列表
        TalMaterial talMaterial = new TalMaterial();
        talMaterial.setUser(user);
        List<TalMaterial> talMaterialList= talMaterialService.findList(talMaterial);
        //获取材质名字列表
        List<String> materialNameList = new ArrayList();
        for(int i=0;i<talMaterialList.size();i++){
            materialNameList.add(talMaterialList.get(i).getMaterial());
        }

        //获规格列表
        TalSpec talSpec = new TalSpec();
        talSpec.setUser(user);
        List<TalSpec> talSpecList= talSpecService.findList(talSpec);
        //获取规格名字列表
        List<String> specNameList = new ArrayList();
        for(int i=0;i<talSpecList.size();i++){
            specNameList.add(talSpecList.get(i).getSpec());
        }


        model.addAttribute("colorNameList",colorNameList);
        model.addAttribute("modelnumNameList",modelnumNameList);
        model.addAttribute("materialNameList",materialNameList);
        model.addAttribute("specNameList",specNameList);
		model.addAttribute("talProduct", talProduct);
		return "seller/productinven/talProductForm";
	}

    @RequiresPermissions("productinven:talProduct:view")
    @RequestMapping(value = "formamend")
    public String formamend(TalProduct talProduct, Model model) {
        //定义用户信息
        User user = UserUtils.getUser();
        //更新用户
        talProduct.setUpdateBy(user);
        model.addAttribute("talProduct", talProduct);
        return "seller/productinven/talProductFormAmend";
    }

	@RequiresPermissions("productinven:talProduct:view")
	@RequestMapping(value = "formpicture")
	public String formpicture(TalProduct talProduct, Model model) {
		//定义用户信息
		User user = UserUtils.getUser();
		//更新用户
		talProduct.setUpdateBy(user);
		model.addAttribute("talProduct", talProduct);
		return "seller/productinven/talProductFormPicture";
	}

	@RequiresPermissions("productinven:talProduct:view")
	@RequestMapping(value = "formindex")
	public String formindex(Model model) {
		return "seller/productinven/talProductFormIndex";
	}

	@RequiresPermissions("productinven:talProduct:edit")
	@RequestMapping(value = "save")
	public String save(TalProduct talProduct, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, talProduct)){
			return form(talProduct, model);
		}
		User user = UserUtils.getUser();
		talProduct.setUser(user);

		talProductService.save(talProduct);

		addMessage(redirectAttributes, "保存库存成功");
		return "redirect:"+Global.getAdminPath()+"/productinven/talProduct/?repage";
	}
	
	@RequiresPermissions("productinven:talProduct:edit")
	@RequestMapping(value = "delete")
	public String delete(TalProduct talProduct, RedirectAttributes redirectAttributes) {
		talProductService.delete(talProduct);
		addMessage(redirectAttributes, "删除库存成功");
		return "redirect:"+Global.getAdminPath()+"/productinven/talProduct/?repage";
	}

	/**
	 * 导出用户数据
	 * @param talProduct
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("productinven:talProduct:view")
	@RequestMapping(value = "export", method= RequestMethod.POST)
	public String exportFile(TalProduct talProduct, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			User user = UserUtils.getUser();
			//判断是不是管理员，管理员可以看全部
			if(user.getId().equals("1")){
				talProduct.setUser(null);
			}else{
				talProduct.setUser(user);
			}
			String fileName = "产品数据"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			Page<TalProduct> page = talProductService.findPage(new Page<TalProduct>(request, response, -1), talProduct);
			new ExportExcel("产品数据", TalProduct.class).setDataList(page.getList()).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出产品失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/productinven/talProduct/?repage";
	}
}