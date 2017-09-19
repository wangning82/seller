/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.aquote.qmaterials.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aquote.model.entity.QModel;
import com.thinkgem.jeesite.modules.sys.service.DictService;
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
import com.aquote.qmaterials.entity.QMaterials;
import com.aquote.qmaterials.service.QMaterialsService;

import java.util.List;

/**
 * 原材料Controller
 * @author wn
 * @version 2017-09-18
 */
@Controller
@RequestMapping(value = "${adminPath}/qmaterials/qMaterials")
public class QMaterialsController extends BaseController {

	@Autowired
	private QMaterialsService qMaterialsService;

	
	@ModelAttribute
	public QMaterials get(@RequestParam(required=false) String id) {
		QMaterials entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = qMaterialsService.get(id);
		}
		if (entity == null){
			entity = new QMaterials();
		}
		return entity;
	}

	@RequiresPermissions("qspecification:qSpecification:view")
	@RequestMapping(value = {"index"})
	public String index(QModel qModel, Model model) {
		return "aquote/qmaterials/qMaterialsIndex";
	}

	@RequiresPermissions("qmaterials:qMaterials:view")
	@RequestMapping(value = {"list", ""})
	public String list(QMaterials qMaterials, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<QMaterials> page = qMaterialsService.findPage(new Page<QMaterials>(request, response), qMaterials);
		model.addAttribute("qMaterials",qMaterials);
		model.addAttribute("page", page);
		return "aquote/qmaterials/qMaterialsList";
	}

	@RequiresPermissions("qmaterials:qMaterials:view")
	@RequestMapping(value = "form")
	public String form(QMaterials qMaterials, Model model) {
		model.addAttribute("qMaterials", qMaterials);
		return "aquote/qmaterials/qMaterialsForm";
	}

	@RequiresPermissions("qmaterials:qMaterials:edit")
	@RequestMapping(value = "save")
	public String save(QMaterials qMaterials, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, qMaterials)){
			return form(qMaterials, model);
		}
		qMaterialsService.save(qMaterials);
		addMessage(redirectAttributes, "保存原材料成功");
		return "redirect:"+Global.getAdminPath()+"/qmaterials/qMaterials/?repage";
	}
	
	@RequiresPermissions("qmaterials:qMaterials:edit")
	@RequestMapping(value = "delete")
	public String delete(QMaterials qMaterials, RedirectAttributes redirectAttributes) {
		qMaterialsService.delete(qMaterials);
		addMessage(redirectAttributes, "删除原材料成功");
		return "redirect:"+Global.getAdminPath()+"/qmaterials/qMaterials/?repage";
	}

}