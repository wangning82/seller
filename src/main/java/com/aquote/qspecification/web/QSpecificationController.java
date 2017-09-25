/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.aquote.qspecification.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aquote.model.entity.QModel;
import com.aquote.qburden.entity.QBurden;
import com.aquote.qburden.service.QBurdenService;
import com.aquote.qmaterials.entity.QMaterials;
import com.aquote.qmaterials.service.QMaterialsService;
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
import com.aquote.qspecification.entity.QSpecification;
import com.aquote.qspecification.service.QSpecificationService;

import java.util.List;

/**
 * 产品规格管理Controller
 * @author wn
 * @version 2017-09-15
 */
@Controller
@RequestMapping(value = "${adminPath}/qspecification/qSpecification")
public class QSpecificationController extends BaseController {

	@Autowired
	private QSpecificationService qSpecificationService;
	@Autowired
	private QBurdenService qBurdenService;
	@Autowired
	private QMaterialsService qMaterialsService;

	@ModelAttribute
	public QSpecification get(@RequestParam(required=false) String id) {
		QSpecification entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = qSpecificationService.get(id);
		}
		if (entity == null){
			entity = new QSpecification();
		}
		return entity;
	}
	@RequiresPermissions("qspecification:qSpecification:view")
	@RequestMapping(value = {"index"})
	public String index(QModel qModel, Model model) {
		return "aquote/qspecification/qSpecificationIndex";
	}
	//获取规格的配料列表
	@RequiresPermissions("qspecification:qSpecification:view")
	@RequestMapping(value = "burdenlist")
	public String burdenlist(QSpecification qSpecification,HttpServletRequest request, HttpServletResponse response, Model model)
	{
		QBurden qBurden = new QBurden();
		qBurden.setSpecificationId(qSpecification.getId());
		Page<QBurden> page = qBurdenService.findPage(new Page<QBurden>(request, response), qBurden);
		model.addAttribute("page", page);
		return "aquote/qspecification/qSpecificationBurden";
	}

	//打开原材料的列表
	@RequestMapping(value = "burdenmaterialslist")
	public String burdenmaterialslist(QMaterials qMaterials, HttpServletRequest request, HttpServletResponse response, Model model)
	{
		Page<QMaterials> page = qMaterialsService.findPage(new Page<QMaterials>(request, response), qMaterials);
		model.addAttribute("qMaterials",qMaterials);
		model.addAttribute("page", page);
		return "aquote/qspecification/qBurdenMaterials";
	}

	@RequiresPermissions("qspecification:qSpecification:view")
	@RequestMapping(value = "burdensave")
	public String burdensave(QBurden qBurden, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes)
	{
//		if (!beanValidator(model, qBurden)){
//			return burdenlist(qBurden, model);
//		}
		qBurden.setSpecificationId(request.getParameter("qSpecificationId"));
		qBurdenService.save(qBurden);
		addMessage(redirectAttributes, "保存产品规格成功");
		return "redirect:"+Global.getAdminPath()+"/qspecification/qSpecification/?repage";
	}

	@RequiresPermissions("qspecification:qSpecification:view")
	@RequestMapping(value = {"list", ""})
	public String list(QSpecification qSpecification, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<QSpecification> page = qSpecificationService.findPage(new Page<QSpecification>(request, response), qSpecification);
		model.addAttribute("qSpecification", qSpecification);
		model.addAttribute("page", page);
		return "aquote/qspecification/qSpecificationList";
	}

	@RequiresPermissions("qspecification:qSpecification:view")
	@RequestMapping(value = "form")
	public String form(QSpecification qSpecification, Model model) {
		model.addAttribute("qSpecification", qSpecification);
		return "aquote/qspecification/qSpecificationForm";
	}

	@RequiresPermissions("qspecification:qSpecification:edit")
	@RequestMapping(value = "save")
	public String save(QSpecification qSpecification, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, qSpecification)){
			return form(qSpecification, model);
		}
		// 修正引用赋值问题，不知道为何，Company和Office引用的一个实例地址，修改了一个，另外一个跟着修改。
		qSpecification.setqModel(new QModel(request.getParameter("qModel.id")));
		qSpecification.setModelId(request.getParameter("qModel.id"));
		qSpecificationService.save(qSpecification);
		addMessage(redirectAttributes, "保存产品规格成功");
		return "redirect:"+Global.getAdminPath()+"/qspecification/qSpecification/?repage";
	}
	
	@RequiresPermissions("qspecification:qSpecification:edit")
	@RequestMapping(value = "delete")
	public String delete(QSpecification qSpecification, RedirectAttributes redirectAttributes) {
		qSpecificationService.delete(qSpecification);
		addMessage(redirectAttributes, "删除产品规格成功");
		return "redirect:"+Global.getAdminPath()+"/qspecification/qSpecification/?repage";
	}



}