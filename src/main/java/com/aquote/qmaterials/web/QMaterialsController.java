/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.aquote.qmaterials.web;

import com.aquote.model.entity.QModel;
import com.aquote.qburden.entity.QBurden;
import com.aquote.qburden.service.QBurdenService;
import com.aquote.qmaterials.entity.QMaterials;
import com.aquote.qmaterials.service.QMaterialsService;
import com.aquote.qspecification.entity.QSpecification;
import com.aquote.qspecification.service.QSpecificationService;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
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
import java.util.ArrayList;
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
	@Autowired
	private QBurdenService qBurdenService;
	@Autowired
	private QSpecificationService qSpecificationService;


	
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
//		qMaterials.setModelId(qMaterials.getModelId());

		this.setQSpecification(qMaterials.getId());


		qMaterialsService.save(qMaterials);
		addMessage(redirectAttributes, "保存原材料成功");
		//更新规格的成本相关内容
		try {
			//获取规格相关列表
			List<QSpecification> qsList =this.setQSpecification(qMaterials.getId());
			for (int i=0;i<qsList.size();i++){
				//更新每个规格的数据
				qSpecificationService.save(this.setQSpecification(qMaterials.getId()).get(i));

			}
		}catch (Exception e){
			System.out.printf(e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/qmaterials/qMaterials/?repage";
	}
	
	@RequiresPermissions("qmaterials:qMaterials:edit")
	@RequestMapping(value = "delete")
	public String delete(QMaterials qMaterials, RedirectAttributes redirectAttributes) {
		if(this.qBurdenService.findSpecList(qMaterials.getId()).size()>0){
			addMessage(redirectAttributes, "原材料被使用中，不能删除！");
		}else{
			qMaterialsService.delete(qMaterials);
			addMessage(redirectAttributes, "删除原材料成功");
		}
		return "redirect:"+Global.getAdminPath()+"/qmaterials/qMaterials/?repage";
	}

	public List<QSpecification> setQSpecification(String materialsId){
		//初始化返回对象列表
		List<QSpecification> qSpecificationList = new ArrayList();
		//获得每个材料对应的记录
		List<QBurden> qBurdenList = qBurdenService.findSpecList(materialsId);
		for(int i=0;i<qBurdenList.size();i++){

			//获取材料所属的规格的id
			String specificationId =qBurdenList.get(i).getSpecificationId();
			//获取对应的规格对象
			QSpecification qSpecification =qSpecificationService.get(specificationId);
			//获取规格对应的所有材料的列表
			List<QBurden> qMaterialsList = qBurdenService.findList(qBurdenList.get(i));
			//计算和更新规格的成本、利润、加工费、价格

			double burdenCost =0;

			for(int j=0;j<qMaterialsList.size();j++){
				//获取材料的id
				String maid = qMaterialsList.get(j).getMaterialsId();
				//获取材料配件的价格
				double maprice =Double.parseDouble(qMaterialsService.get(maid).getPrice());
				//计算规格的成本
				burdenCost= burdenCost+Double.parseDouble(qMaterialsList.get(j).getMaterialsUsenum())*maprice;

			}
			//设定成本
			qSpecification.setCost(Double.toString(burdenCost));

			//设定利润
			double burdenProfit =burdenCost*Double.parseDouble(qSpecification.getProfitratio())/100;
			qSpecification.setProfit(Double.toString(burdenProfit));
			//设定加工费
			double burdenCharge =burdenCost*Double.parseDouble(qSpecification.getChargeratio())/100;
			qSpecification.setCharge(Double.toString(burdenCharge));
			//设定产品价格
			double burdenPrice =burdenCost+burdenProfit+burdenCharge;
			qSpecification.setPrice(Double.toString(burdenPrice));
			//将修改完成的规格添加到列表中
			qSpecificationList.add(qSpecification);

		}

		return qSpecificationList;
	}

}