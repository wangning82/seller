/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.seller.modelnum.web;

import com.seller.modelnum.entity.TalModelnum;
import com.seller.modelnum.service.TalModelnumService;
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
 * 款号Controller
 * @author wn
 * @version 2017-12-28
 */
@Controller
@RequestMapping(value = "${adminPath}/modelnum/talModelnum")
public class TalModelnumController extends BaseController {

	@Autowired
	private TalModelnumService talModelnumService;
	
	@ModelAttribute
	public TalModelnum get(@RequestParam(required=false) String id) {
		TalModelnum entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = talModelnumService.get(id);
		}
		if (entity == null){
			entity = new TalModelnum();
		}
		return entity;
	}
	
	@RequiresPermissions("modelnum:talModelnum:view")
	@RequestMapping(value = {"list", ""})
	public String list(TalModelnum talModelnum, HttpServletRequest request, HttpServletResponse response, Model model) {
		//判断是不是管理员
		User user = UserUtils.getUser();
		if(user.getId().equals("1")){
			talModelnum.setUser(null);
		}else{
			talModelnum.setUser(user);
		}
		Page<TalModelnum> page = talModelnumService.findPage(new Page<TalModelnum>(request, response), talModelnum);
		//添加用户信息
		for(int i=0;i<page.getList().size();i++){
			page.getList().get(i).setUser(UserUtils.get(page.getList().get(i).getCreateBy().getId()));
		}
		model.addAttribute("page", page);
		return "seller/modelnum/talModelnumList";
	}

	@RequiresPermissions("modelnum:talModelnum:view")
	@RequestMapping(value = "form")
	public String form(TalModelnum talModelnum, Model model) {
		model.addAttribute("talModelnum", talModelnum);
		return "seller/modelnum/talModelnumForm";
	}

	@RequiresPermissions("modelnum:talModelnum:edit")
	@RequestMapping(value = "save")
	public String save(TalModelnum talModelnum, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, talModelnum)){
			return form(talModelnum, model);
		}
		User user = UserUtils.getUser();
		talModelnum.setUser(user);
		talModelnumService.save(talModelnum);
		addMessage(redirectAttributes, "保存款号成功");
		return "redirect:"+Global.getAdminPath()+"/modelnum/talModelnum/?repage";
	}
	
	@RequiresPermissions("modelnum:talModelnum:edit")
	@RequestMapping(value = "delete")
	public String delete(TalModelnum talModelnum, RedirectAttributes redirectAttributes) {
		talModelnumService.delete(talModelnum);
		addMessage(redirectAttributes, "删除款号成功");
		return "redirect:"+Global.getAdminPath()+"/modelnum/talModelnum/?repage";
	}

}