/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.aquote.qspecification.web;

import com.alibaba.fastjson.JSONObject;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		//存储实体类
		QBurden qBurden = new QBurden();
		//查询所属的规格的ID
		qBurden.setSpecificationId(qSpecification.getId());
		//获取查询数据
		Page<QBurden> page = qBurdenService.findPage(new Page<QBurden>(request, response), qBurden);

		//查询原材料的数据
		for (int i =0;i<page.getList().size();i++){
			QMaterials qMaterials = new QMaterials();
			qMaterials.setId(page.getList().get(i).getMaterialsId());
			List<QMaterials> qMaterialsList = qMaterialsService.findList(qMaterials);
			//设置相关的参数
			String materialsName = qMaterialsList.get(0).getName();
			String materialsQuality = qMaterialsList.get(0).getQuality();
			String materialsPrice = qMaterialsList.get(0).getPrice();
			//设置对象的相关参数
			page.getList().get(i).setMaterialsName(materialsName);
			page.getList().get(i).setMaterialsQuality(materialsQuality);
			page.getList().get(i).setMaterialsPrice(materialsPrice);
		}


		model.addAttribute("page", page);
		return "aquote/qspecification/qSpecificationBurden";
	}

	//打开原材料的列表
	@RequestMapping(value = "burdenmaterialslist")
	public String burdenmaterialslist(QMaterials qMaterials, HttpServletRequest request, HttpServletResponse response, Model model)
	{
		Page<QMaterials> page = qMaterialsService.findPage(new Page<QMaterials>(request, response), qMaterials);
		model.addAttribute("qMaterials",qMaterials);
		model.addAttribute("specificationId",request.getParameter("specificationId"));
		model.addAttribute("page", page);
		return "aquote/qspecification/qBurdenMaterials";
	}

	//规格选中的材料的数据写入
	@RequestMapping(value = "materialsadd")
	@ResponseBody
	public String burdenadd(QBurden qBurden, String qspecificationId, String qMaterialsId)
	{
		Map<String,Object> hashMap = new HashMap<>();
		qBurden.setSpecificationId(qspecificationId);
		qBurden.setMaterialsId(qMaterialsId);
		try{
			//判断是否包含了此参数
			int isexist = qBurdenService.findList(qBurden).size();
			if(isexist>0){
				hashMap.put("msg", "已经存在");
			}
			else {
				qBurdenService.save(qBurden);
				hashMap.put("msg", "保存成功");
			}

		}catch(Exception e){
			hashMap.put("msg", "保存失败");
		}


		return JSONObject.toJSONString(hashMap);
	}


	@RequiresPermissions("qspecification:qSpecification:view")
	@RequestMapping(value = "burdensave")
	public String burdensave(QBurden qBurden, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes)
	{
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
		//
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

	@ResponseBody
	@RequestMapping(value = "burdendelete")
	public String burdendelete(QBurden qBurden,String qspecificationId, String qMaterialsId, RedirectAttributes redirectAttributes) {

		qBurden.setSpecificationId(qspecificationId);
		qBurden.setMaterialsId(qMaterialsId);
		Map<String,Object> hashMap = new HashMap<>();
		try{
			qBurdenService.delete(qBurden);
			qSpecificationService.save(setQSpecification(qspecificationId));
			hashMap.put("msg", "删除成功");
		}catch(Exception e){
			System.out.print(e.getMessage());
			hashMap.put("msg", "删除失败");
		}
		return JSONObject.toJSONString(hashMap);
	}
	@ResponseBody
	@RequestMapping(value = "burdenupdate")
	public String burdenupdate(QBurden qBurden,String qBurdenId,String specificationId,String materialsId, String materialsUsenum, RedirectAttributes redirectAttributes) {
		//添加更新参数
		qBurden.setId(qBurdenId);
		qBurden.setSpecificationId(specificationId);
		//材料id
		qBurden.setMaterialsId(materialsId);
		qBurden.setMaterialsUsenum(materialsUsenum);
		Map<String,Object> hashMap = new HashMap<>();
		try{
			qBurdenService.update(qBurden);
			//改变用量更新规格的成本
			try {
				qSpecificationService.save(setQSpecification(specificationId));
				hashMap.put("msg", "成本计算失败");
			}catch (Exception e){

			}
			hashMap.put("msg", "设置成功");
		}catch(Exception e){
			System.out.print(e.getMessage());
			hashMap.put("msg", "设置失败");
		}
		return JSONObject.toJSONString(hashMap);
	}
	//用量数量的变化带来的成本的变化
	public QSpecification setQSpecification(String specificationId){

		QSpecification qSpecification = new QSpecification();
		//设定更新规格的id
		qSpecification =qSpecificationService.get(specificationId);
		//设定规格的cost
		try {
			//获取规格的所有参数值
			QBurden qb = new QBurden();
			qb.setSpecificationId(specificationId);
			List<QBurden> qBurdenList = qBurdenService.findList(qb);
			//当一个材料发生变化的时候，计算规格成本，对规格所述的所有材料进行计算
			double burdenCost =0;

			for(int i=0;i<qBurdenList.size();i++){
				//获取材料的id
				String maid = qBurdenList.get(i).getMaterialsId();
				//获取材料配件的价格
				double maprice =Double.parseDouble(qMaterialsService.get(maid).getPrice());
				//计算规格的成本
				burdenCost= burdenCost+Double.parseDouble(qBurdenList.get(i).getMaterialsUsenum())*maprice;

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
		}catch (Exception e){
			System.out.print(e.getMessage());
		}
		return qSpecification;
	}


}