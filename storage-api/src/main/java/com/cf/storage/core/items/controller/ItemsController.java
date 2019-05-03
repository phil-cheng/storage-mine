package com.cf.storage.core.items.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cf.storage.common.AjaxResponse;
import com.cf.storage.common.BaseController;
import com.cf.storage.common.Constant;
import com.cf.storage.common.ErrorCode;
import com.cf.storage.common.FstException;
import com.cf.storage.common.PageInfo;
import com.cf.storage.common.annotation.LogAnnotation;
import com.cf.storage.common.initvars.AppVars;
import com.cf.storage.common.jqgrid.JqGridResponse;
import com.cf.storage.core.items.po.Items;
import com.cf.storage.core.items.service.ItemsService;
import com.cf.storage.util.Base64FileUtil;
import com.cf.storage.util.ImageHash;
import com.cf.storage.util.RandomUtil;
import com.github.pagehelper.Page;


@Controller
@RequestMapping("/items")
public class ItemsController extends BaseController {

	  @Resource
	  private ItemsService itemsService;
	  
	  @Resource
	  private AppVars appVars;
	  
	  
	  @RequestMapping("addItem")
	  @ResponseBody
	  @LogAnnotation(description = "增加物品")
	  public AjaxResponse addItem(@RequestBody Items items) throws Exception {
		 // 非空验证
		 if(items == null){
			 throw new FstException(ErrorCode.PARAMS_ERROE_200000);
		 }
		 if(items.getName() == null || "".equals(items.getName())){
			 throw new FstException(ErrorCode.PARAMS_ERROE_200000);
		 }
		 if(items.getTitle() == null || "".equals(items.getTitle())){
			 throw new FstException(ErrorCode.PARAMS_ERROE_200000);
		 }
		 if(items.getNum() == null){
			 throw new FstException(ErrorCode.PARAMS_ERROE_200000);
		 }
		 if(items.getPrice() == null){
			 throw new FstException(ErrorCode.PARAMS_ERROE_200000);
		 }
		 
		 String id = RandomUtil.get32UUID();
		 String imageFileName = id + ".jpg";
		
		 // 写入文件
		 String baseStr = items.getProfilePic();
		 baseStr = baseStr.replace("data:image/jpeg;base64,", "");
		 Base64FileUtil.base64ToFile(appVars.imageFilePath, baseStr, imageFileName);
		 String imagePath = appVars.imageFilePath + "/" + imageFileName;
		 items.setProfilePic(imagePath);
		 
		 // 计算图片hash
		 ImageHash p = new ImageHash();
		 String imagHash = p.getHash(new FileInputStream(new File(imagePath)));
		 items.setImgFinger(imagHash);
		 
		 // 接口如果没有传pId，则认为增加的是根节点。
		 if(items.getPid() == null || "".equals(items.getPid())){
			 items.setPid(Constant.MENU_PARENTID);
			 items.setPath(Constant.MENU_PARENTID + "-" + id);
		 }else{
			 // 根据父级id计算path路径，中间用-拼接
			 Items oneItem = itemsService.selectOneByPrimayKey(items.getPid());
			 if(oneItem != null){
				 items.setPath(oneItem.getPath() + "-" +id);
			 }
		 }
		 items.setId(id);
		 items.setCreateTime(new Date());
		 items.setType("1"); // 0:收藏夹；1：物品
		 Integer a =  itemsService.addItem(items);
		 
		 // 递归更新父级总金额
		 if(a > 0 && !(Constant.MENU_PARENTID).equals(items.getPid()) && items.getPrice() != null){
			 itemsService.updateParentPrice(items,items.getPrice());
		 }
		 
		 AjaxResponse ret = new AjaxResponse();
	     Map<String,Object> retMap = new HashMap<String,Object>();
	     retMap.put("result", a);
	     ret.setData(retMap);
	     return ret;
	  }
	  
	  
	  @RequestMapping("addDir")
	  @ResponseBody
	  @LogAnnotation(description = "增加收藏夹")
	  public AjaxResponse addDir(@RequestBody Items items) throws Exception {
		  // 非空验证
		 if(items == null){
			 throw new FstException(ErrorCode.PARAMS_ERROE_200000);
		 }
		 if(items.getName() == null || "".equals(items.getName())){
			 throw new FstException(ErrorCode.PARAMS_ERROE_200000);
		 }
		 if(items.getTitle() == null || "".equals(items.getTitle())){
			 throw new FstException(ErrorCode.PARAMS_ERROE_200000);
		 }
		 if(items.getProfilePic() == null || "".equals(items.getProfilePic())){
			 throw new FstException(ErrorCode.PARAMS_ERROE_200000);
		 }
		 
		 String id = RandomUtil.get32UUID();
		 String imageFileName = id + ".jpg";
		
		 // 写入文件
		 String baseStr = items.getProfilePic();
		 baseStr = baseStr.replace("data:image/jpeg;base64,", "");
		 Base64FileUtil.base64ToFile(appVars.imageFilePath, baseStr, imageFileName);
		 String imagePath = appVars.imageFilePath + "/" + imageFileName;
		 items.setProfilePic(imagePath);
		 
		 // 计算图片hash
		 ImageHash p = new ImageHash();
		 String imagHash = p.getHash(new FileInputStream(new File(imagePath)));
		 items.setImgFinger(imagHash);
		 
		 // 接口如果没有传pId，则认为增加的是根节点。
		 if(items.getPid() == null || "".equals(items.getPid())){
			 items.setPid(Constant.MENU_PARENTID);
			 items.setPath(Constant.MENU_PARENTID + "-" + id);
		 }else{
			 // 根据父级id计算path路径，中间用-拼接
			 Items oneItem = itemsService.selectOneByPrimayKey(items.getPid());
			 if(oneItem != null){
				 items.setPath(oneItem.getPath() + "-" +id);
			 }
		 }
		 
		 items.setId(id);
		 items.setCreateTime(new Date());
		 items.setType("0"); // 0:收藏夹；1：物品
		 Integer a =  itemsService.addItem(items);
		 
		 AjaxResponse ret = new AjaxResponse();
	     Map<String,Object> retMap = new HashMap<String,Object>();
	     retMap.put("result", a);
	     ret.setData(retMap);
	     return ret;
	  }
	  
	  
	  
	  @RequestMapping("queryItems")
	  @ResponseBody
	  public AjaxResponse queryItems(PageInfo pageInfo,String searchName,String pId,String userId) throws Exception {
		  AjaxResponse ret = new AjaxResponse();
		  if(pId == null || "".equals(pId)){
			  pId = Constant.MENU_PARENTID;
		  }
		  Map<String,Object> params = pageInfo.getSortParamMap();
		  params.put("searchName",searchName);
		  params.put("pId",pId);
		  params.put("userId",userId);
		  Page page = itemsService.queryItems(params,pageInfo.getRowBounds());
		  
		  
		  List<Items> list = page.getResult();
		  if(list !=null && list.size() > 0){
			  for(int i = 0; i < list.size(); i++){
				  // 图片处理
				  String filePath =  list.get(i).getProfilePic();
				  if(filePath != null && !"".equals(filePath)){
					  list.get(i).setProfilePic("data:image/jpeg;base64," +Base64FileUtil.fileToBase64(filePath));
				  }
			  }
		  }
		  ret.setData(new JqGridResponse(page,pageInfo));
		  return ret;
	  }
	  
	  
	  @RequestMapping("queryItemsByImage")
	  @ResponseBody
	  public AjaxResponse queryItemsByImage(PageInfo pageInfo, @RequestBody Items items) throws Exception {
		 // 参数判断
		 if(items.getProfilePic() == null || "".equals(items.getProfilePic())){
				 throw new FstException(ErrorCode.PARAMS_ERROE_200000);
		 }
		  
		 // 写入文件
		 String id = RandomUtil.get32UUID();
		 String imageFileName = id + ".jpg";
		 String baseStr = items.getProfilePic();
		 baseStr = baseStr.replace("data:image/jpeg;base64,", "");
		 Base64FileUtil.base64ToFile(appVars.imageFilePath, baseStr, imageFileName);
		 String imagePath = appVars.imageFilePath + "/" + imageFileName;
		 
		 // 计算图片hash
		 ImageHash p = new ImageHash();
		 String imagHash = p.getHash(new FileInputStream(new File(imagePath)));
		
		 if(imagHash != null && !"".equals(imagHash)){
			new File(imagePath).delete();
		 }
		  
		  Map<String,Object> params = pageInfo.getSortParamMap();
		  params.put("imagHash",imagHash);
		  
		  Page page = itemsService.queryItemsByImage(params,pageInfo.getRowBounds());
		  
		  List<Items> list = page.getResult();
		  if(list !=null && list.size() > 0){
			  for(int i = 0; i < list.size(); i++){
				  // 图片处理
				  String filePath =  list.get(i).getProfilePic();
				  if(filePath != null && !"".equals(filePath)){
					  list.get(i).setProfilePic("data:image/jpeg;base64," +Base64FileUtil.fileToBase64(filePath));
				  }
			  }
		  }
		  
		  AjaxResponse ret = new AjaxResponse();
		  ret.setData(new JqGridResponse(page,pageInfo));
		  return ret;
	  }
	  
	  
	  
	  @RequestMapping("delItems")
	  @ResponseBody
	  @LogAnnotation(description = "删除")
	  public AjaxResponse delItems(@RequestBody Items items) throws Exception {
		 // 非空验证
		 if(items == null){
			 throw new FstException(ErrorCode.PARAMS_ERROE_200000);
		 }
		 if(items.getId() == null || "".equals(items.getId())){
			 throw new FstException(ErrorCode.PARAMS_ERROE_200000);
		 }
		 Integer a =  itemsService.delItems(items);
		 AjaxResponse ret = new AjaxResponse();
	     Map<String,Object> retMap = new HashMap<String,Object>();
	     retMap.put("result", a);
	     ret.setData(retMap);
	     return ret;
	  }
	  
}
