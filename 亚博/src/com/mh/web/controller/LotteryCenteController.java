package com.mh.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mh.commons.conf.CommonConstant;
import com.mh.commons.orm.Page;
import com.mh.commons.utils.HttpClientUtil;
import com.mh.commons.utils.ServletUtils;
import com.mh.service.LotteryCenterService;
/**
 * 
 * 开奖中心主页  不需要传参数
 * 开奖中心详情 参数：code ：游戏类型编码          date: 日期
 *
 */
@Controller
@RequestMapping("/lotteryCenter")
public class LotteryCenteController extends BaseController{
	
	
	@Autowired
	private LotteryCenterService LotteryCenterService;
	@RequestMapping("/getAllGameTypeNewResultList")
	public void getAllGameTypeNewResultList(HttpServletRequest request,HttpServletResponse response,String code ,String date){
		
		try {
			Page page=new Page();
			Map<String, String> params = new HashMap<String, String>();
			params.put("date",date);
			params.put("code", code);
			params.put("pageNo", null == request.getParameter("currentPage") ? "1" : request.getParameter("currentPage"));// 第几页
			params.put("pageSize", null==request.getParameter("pageLimit")?CommonConstant.DEFAULT_PAGE_SIZE+"":request.getParameter("pageLimit"));// 每页显示多少条
			page  = LotteryCenterService.getAllGameTypeNewResultList(page,params);
			ServletUtils.outPrintSuccess(request, response, page);
		} catch (Exception e) {
			e.printStackTrace();
			ServletUtils.outPrintFail(request, response, "查询开奖结果失败！");
		}
		
	}
	
	
	@RequestMapping("/getKjResults")
	public void getKjResults(HttpServletRequest request,HttpServletResponse response,String code ,String searchBeginTime,String searchEndTime){
		
		try {
			Page page=new Page();
			Map<String, String> params = new HashMap<String, String>();
			params.put("code", code);
			params.put("bTime", searchBeginTime);
			params.put("eTime", searchEndTime);
			params.put("pageNo", null == request.getParameter("currentPage") ? "1" : request.getParameter("currentPage"));// 第几页
			params.put("pageSize", null==request.getParameter("pageLimit")?CommonConstant.DEFAULT_PAGE_SIZE+"":request.getParameter("pageLimit"));// 每页显示多少条
			
			page  = LotteryCenterService.getAllGameTypeNewResultList(page,params);
			ServletUtils.outPrintSuccess(request, response, page.getResult());
		} catch (Exception e) {
			e.printStackTrace();
			ServletUtils.outPrintFail(request, response, "查询开奖结果失败！");
		}
		
	}
	
	
	
	public static void main(String[] args) {
		String url="http://127.0.0.1:8080/yabo/lotteryCenter/getAllGameTypeNewResultList";
		Map<String,String> params=new HashMap<String,String>();
		//params.put("code", "CAKENO");
		params.put("code", "CAKENO");
		//params.put("date", "2016-10-07");
		//params.put("userName", "testou");
		String str = HttpClientUtil.post(url, params);
		System.out.println(str);
	}
}
