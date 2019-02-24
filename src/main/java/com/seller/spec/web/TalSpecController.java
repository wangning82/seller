/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.seller.spec.web;

import com.seller.spec.entity.TalSpec;
import com.seller.spec.service.TalSpecService;
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
 * 规格Controller
 * @author wn
 * @version 2017-12-28
 */
@Controller
@RequestMapping(value = "${adminPath}/spec/talSpec")
public class TalSpecController extends BaseController {

	@Autowired
	private TalSpecService talSpecService;
	
	@ModelAttribute
	public TalSpec get(@RequestParam(required=false) String id) {
		TalSpec entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = talSpecService.get(id);
		}
		if (entity == null){
			entity = new TalSpec();
		}
		return entity;
	}
	
	@RequiresPermissions("spec:talSpec:view")
	@RequestMapping(value = {"list", ""})
	public String list(TalSpec talSpec, HttpServletRequest request, HttpServletResponse response, Model model) {
		//判断是不是管理员，管理员可以看全部
		User user = UserUtils.getUser();
		if(user.getId().equals("1")){
			talSpec.setUser(null);
		}else{
			talSpec.setUser(user);
		}
		Page<TalSpec> page = talSpecService.findPage(new Page<TalSpec>(request, response), talSpec);
		//加入用户信息
		for(int i=0;i<page.getList().size();i++){
			page.getList().get(i).setUser(UserUtils.get(page.getList().get(i).getCreateBy().getId()));
		}
		model.addAttribute("page", page);
		return "seller/spec/talSpecList";
	}

	@RequiresPermissions("spec:talSpec:view")
	@RequestMapping(value = "form")
	public String form(TalSpec talSpec, Model model) {
		model.addAttribute("talSpec", talSpec);
		return "seller/spec/talSpecForm";
	}

	@RequiresPermissions("spec:talSpec:edit")
	@RequestMapping(value = "save")
	public String save(TalSpec talSpec, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, talSpec)){
			return form(talSpec, model);
		}
		User user = UserUtils.getUser();
		talSpec.setUser(user);
		talSpecService.save(talSpec);
		addMessage(redirectAttributes, "保存规格成功");
		return "redirect:"+Global.getAdminPath()+"/spec/talSpec/?repage";
	}
	
	@RequiresPermissions("spec:talSpec:edit")
	@RequestMapping(value = "delete")
	public String delete(TalSpec talSpec, RedirectAttributes redirectAttributes) {
		talSpecService.delete(talSpec);
		addMessage(redirectAttributes, "删除规格成功");
		return "redirect:"+Global.getAdminPath()+"/spec/talSpec/?repage";
	}

}