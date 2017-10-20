/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.aquote.model.web;

import com.aquote.model.entity.QModel;
import com.aquote.model.service.QModelService;
import com.aquote.qspecification.entity.QSpecification;
import com.aquote.qspecification.service.QSpecificationService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
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
import java.util.List;
import java.util.Map;

/**
 * 产品型号管理Controller
 * @author wn
 * @version 2017-09-14
 */
@Controller
@RequestMapping(value = "${adminPath}/model/qModel")
public class QModelController extends BaseController {

	@Autowired
	private QModelService qModelService;

	@Autowired
	private QSpecificationService qSpecificationService;
	
	@ModelAttribute
	public QModel get(@RequestParam(required=false) String id) {
		QModel entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = qModelService.get(id);
		}
		if (entity == null){
			entity = new QModel();
		}
		return entity;
	}
	@RequiresPermissions("model:qModel:view")
	@RequestMapping(value = {"list", ""})
	public String list(QModel qModel, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<QModel> list = qModelService.findList(qModel);
		model.addAttribute("qModel",qModel);
		model.addAttribute("list", list);
		return "aquote/model/qModelList";
	}

	@RequiresPermissions("model:qModel:view")
	@RequestMapping(value = "form")
	public String form(QModel qModel, Model model) {
		if (qModel.getParent()!=null && StringUtils.isNotBlank(qModel.getParent().getId())){
			qModel.setParent(qModelService.get(qModel.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(qModel.getId())){
				QModel qModelChild = new QModel();
				qModelChild.setParent(new QModel(qModel.getParent().getId()));
				List<QModel> list = qModelService.findList(qModel); 
				if (list.size() > 0){
					qModel.setSort(list.get(list.size()-1).getSort());
					if (qModel.getSort() != null){
						qModel.setSort(qModel.getSort() + 30);
					}
				}
			}
		}
		if (qModel.getSort() == null){
			qModel.setSort(30);
		}
		model.addAttribute("qModel", qModel);
		return "aquote/model/qModelForm";
	}

	@RequiresPermissions("model:qModel:edit")
	@RequestMapping(value = "save")
	public String save(QModel qModel, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, qModel)){
			return form(qModel, model);
		}
		//型号价格的调整
		qModelService.save(qModel);
		addMessage(redirectAttributes, "保存产品型号管理成功");
		return "redirect:"+Global.getAdminPath()+"/model/qModel/?repage";
	}
	
	@RequiresPermissions("model:qModel:edit")
	@RequestMapping(value = "delete")
	public String delete(QModel qModel, RedirectAttributes redirectAttributes) {
		//判断是否具有下级规格，如果具有规格不能删除此型号
		//规格数量
		QSpecification qSpecification = new QSpecification();
		String strmodel =qModel.getId();
		qSpecification.setModelId(strmodel);
		//节点本身是否关联规格
		int specCount = 0;
		specCount = qSpecificationService.findList(qSpecification).size();

		//获得所有子节点的列表
		QModel qm = new QModel();
		qm.setId(strmodel);
		List<QModel> childList = qModelService.findList(qm);
		int parentCount =0;
		for(int i=0;i<childList.size();i++){
			//重新modelid赋值
			qSpecification.setModelId(childList.get(i).getId());
			//判断子节点中是不是包含规格产品
			if(qSpecificationService.findList(qSpecification).size()>0){
				parentCount++;
			}
		}
		//自己是否关联规格，子节点是否关联规格
		if (specCount+parentCount>0){
			addMessage(redirectAttributes, "产品型号下包含了规格，请删除规格后在进行型号删除操作！");
		}else {
			qModelService.delete(qModel);
			addMessage(redirectAttributes, "删除产品型号成功");
		}
		return "redirect:"+Global.getAdminPath()+"/model/qModel/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<QModel> list = qModelService.findList(new QModel());
		for (int i=0; i<list.size(); i++){
			QModel e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}

}