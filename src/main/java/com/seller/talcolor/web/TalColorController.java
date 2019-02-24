/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.seller.talcolor.web;

import com.seller.talcolor.entity.TalColor;
import com.seller.talcolor.service.TalColorService;
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
 * 颜色管理Controller
 * @author wn
 * @version 2017-12-28
 */
@Controller
@RequestMapping(value = "${adminPath}/talcolor/talColor")
public class TalColorController extends BaseController {

	@Autowired
	private TalColorService talColorService;
	
	@ModelAttribute
	public TalColor get(@RequestParam(required=false) String id) {
		TalColor entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = talColorService.get(id);
		}
		if (entity == null){
			entity = new TalColor();
		}
		return entity;
	}
	
	@RequiresPermissions("talcolor:talColor:view")
	@RequestMapping(value = {"list", ""})
	public String list(TalColor talColor, HttpServletRequest request, HttpServletResponse response, Model model) {
		//判断是不是管理员
		User user = UserUtils.getUser();
		if(user.getId().equals("1")){
			talColor.setUser(null);
		}else{
			talColor.setUser(user);
		}

		Page<TalColor> page = talColorService.findPage(new Page<TalColor>(request, response), talColor);
		//添加用户信息
		for(int i=0;i<page.getList().size();i++){
			page.getList().get(i).setUser(UserUtils.get(page.getList().get(i).getCreateBy().getId()));
		}
		model.addAttribute("page", page);
		return "seller/talcolor/talColorList";
	}

	@RequiresPermissions("talcolor:talColor:view")
	@RequestMapping(value = "form")
	public String form(TalColor talColor, Model model) {
		model.addAttribute("talColor", talColor);
		return "seller/talcolor/talColorForm";
	}

	@RequiresPermissions("talcolor:talColor:edit")
	@RequestMapping(value = "save")
	public String save(TalColor talColor, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, talColor)){
			return form(talColor, model);
		}
		User user = UserUtils.getUser();
		talColor.setUser(user);
		talColorService.save(talColor);
		addMessage(redirectAttributes, "保存颜色管理成功");
		return "redirect:"+Global.getAdminPath()+"/talcolor/talColor/?repage";
	}
	
	@RequiresPermissions("talcolor:talColor:edit")
	@RequestMapping(value = "delete")
	public String delete(TalColor talColor, RedirectAttributes redirectAttributes) {
		talColorService.delete(talColor);
		addMessage(redirectAttributes, "删除颜色管理成功");
		return "redirect:"+Global.getAdminPath()+"/talcolor/talColor/?repage";
	}

}