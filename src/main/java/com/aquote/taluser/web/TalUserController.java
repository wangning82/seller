/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.aquote.taluser.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aquote.taluser.entity.TalUser;
import com.aquote.taluser.service.TalUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;

/**
 * 终端用户注册后的管理功能Controller
 * @author wn
 * @version 2016-11-24
 */
@Controller
@RequestMapping(value = "${adminPath}/taluser/talUser")
public class TalUserController extends BaseController {

	@Autowired
	private TalUserService talUserService;
	
	@ModelAttribute
	public TalUser get(@RequestParam(required=false) String id) {
		TalUser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = talUserService.get(id);
		}
		if (entity == null){
			entity = new TalUser();
		}
		return entity;
	}
	
	@RequiresPermissions("taluser:talUser:view")
	@RequestMapping(value = {"list", ""})
	public String list(TalUser talUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TalUser> page = talUserService.findPage(new Page<TalUser>(request, response), talUser); 
		model.addAttribute("page", page);
		return "aquote/taluser/talUserList";
	}

	@RequiresPermissions("taluser:talUser:view")
	@RequestMapping(value = "form")
	public String form(TalUser talUser, Model model) {
		model.addAttribute("talUser", talUser);
		return "aquote/taluser/talUserForm";
	}

	@RequiresPermissions("taluser:talUser:edit")
	@RequestMapping(value = "save")
	public String save(TalUser talUser, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, talUser)){
			return form(talUser, model);
		}
		talUserService.save(talUser);
		addMessage(redirectAttributes, "保存注册用户成功");
		return "redirect:"+Global.getAdminPath()+"/taluser/talUser/?repage";
	}
	
	@RequiresPermissions("taluser:talUser:edit")
	@RequestMapping(value = "delete")
	public String delete(TalUser talUser, RedirectAttributes redirectAttributes) {
		talUserService.delete(talUser);
		addMessage(redirectAttributes, "删除注册用户成功");
		return "redirect:"+Global.getAdminPath()+"/taluser/talUser/?repage";
	}

}