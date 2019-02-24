/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.seller.material.web;

import com.seller.material.entity.TalMaterial;
import com.seller.material.service.TalMaterialService;
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

/**
 * 材料管理Controller
 * @author wn
 * @version 2017-12-28
 */
@Controller
@RequestMapping(value = "${adminPath}/material/talMaterial")
public class TalMaterialController extends BaseController {

	@Autowired
	private TalMaterialService talMaterialService;
	
	@ModelAttribute
	public TalMaterial get(@RequestParam(required=false) String id) {
		TalMaterial entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = talMaterialService.get(id);
		}
		if (entity == null){
			entity = new TalMaterial();
		}
		return entity;
	}
	
	@RequiresPermissions("material:talMaterial:view")
	@RequestMapping(value = {"list", ""})
	public String list(TalMaterial talMaterial, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		//判断是不是管理员，管理员可以看全部
		if(user.getId().equals("1")){
			talMaterial.setUser(null);
		}else{
			talMaterial.setUser(user);
		}
		Page<TalMaterial> page = talMaterialService.findPage(new Page<TalMaterial>(request, response), talMaterial);
		//加入用户信息
		for(int i=0;i<page.getList().size();i++){
			page.getList().get(i).setUser(UserUtils.get(page.getList().get(i).getCreateBy().getId()));
		}
		model.addAttribute("page", page);

		return "seller/material/talMaterialList";
	}

	@RequiresPermissions("material:talMaterial:view")
	@RequestMapping(value = "form")
	public String form(TalMaterial talMaterial, Model model) {
		model.addAttribute("talMaterial", talMaterial);
		return "seller/material/talMaterialForm";
	}

	@RequiresPermissions("material:talMaterial:edit")
	@RequestMapping(value = "save")
	public String save(TalMaterial talMaterial, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, talMaterial)){
			return form(talMaterial, model);
		}
		User user = UserUtils.getUser();
		talMaterial.setUser(user);
		talMaterialService.save(talMaterial);
		addMessage(redirectAttributes, "保存材料管理成功");
		return "redirect:"+Global.getAdminPath()+"/material/talMaterial/?repage";
	}
	
	@RequiresPermissions("material:talMaterial:edit")
	@RequestMapping(value = "delete")
	public String delete(TalMaterial talMaterial, RedirectAttributes redirectAttributes) {
		talMaterialService.delete(talMaterial);
		addMessage(redirectAttributes, "删除材料管理成功");
		return "redirect:"+Global.getAdminPath()+"/material/talMaterial/?repage";
	}

}