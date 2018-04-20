package com.nit.wx.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;

import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nit.wx.bean.component.ApiQueryAuthResult;
import com.nit.wx.bean.component.AuthorizerAccessToken;
import com.nit.wx.dao.DisanfangInfoDao;
import com.nit.wx.disanfangutil.ComponentAPI;
import com.nit.wx.model.Disanfanginfo;
import com.nit.wx.util.AesException;
import com.nit.wx.util.CVTicketUtil;
import com.nit.wx.util.WeixinUtil;


@RestController
public class WeiXinController
{
	@Autowired 
	DisanfangInfoDao disanfangInfoDao;
	
	
	/**
	 * 
	 * @Description 授权事件接收
	 * @author 吴佶津
	 * @date 2018年4月16日
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws AesException
	 * @throws DocumentException
	 */   
	@RequestMapping(value="/wechat/author")
   public void acceptAuthorizeEvent(
       HttpServletResponse response,HttpServletRequest request){
	   CVTicketUtil cvTicketUtil = new CVTicketUtil();
           try {
               //处理授权事件
        	   cvTicketUtil.handleAuthorize(request,response);
               PrintWriter pw = response.getWriter();
               pw.write("success");
               pw.flush();
               cvTicketUtil.getpreauthcode();
              
           } catch (Exception e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
           }
   }
   
   @RequestMapping(value="/wechat/test",method={RequestMethod.GET,RequestMethod.POST})
   public void testW(HttpServletResponse response,HttpServletRequest request) throws IOException {
	   Disanfanginfo disanfangInfo = new Disanfanginfo();
 	   disanfangInfo = disanfangInfoDao.findOne("wx3d6a383a2aa2b1e2");
       String componentloginpage = ComponentAPI.componentloginpage(disanfangInfo.getComponentappid(), disanfangInfo.getPreauthcode(), "http://www.juhuaihua.cn/get_authorization_code");
       response.setCharacterEncoding("utf-8");
       response.sendRedirect(componentloginpage);
   }
   
   /**
    * 
    * @Description 公众号用户回调的url，获取授权码（authorization_code）
    * @author 吴佶津
    * @date 2018年4月18日
    * @param request
    * @param response
    * @param res
    * @throws IOException
    */
   @RequestMapping("/get_authorization_code")
   public void get_authorization_code(@RequestParam(name="auth_code", required=false) String auth_code, HttpServletResponse res)
     throws IOException
   {
	   	   System.out.println("得到授权码（authorization_code）"+auth_code);
	   	   SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   Disanfanginfo disanfangInfo = new Disanfanginfo();
	 	   disanfangInfo = disanfangInfoDao.findOne("wx3d6a383a2aa2b1e2");
	 	   ApiQueryAuthResult apiQueryAuthResult = new ApiQueryAuthResult();
	 	   apiQueryAuthResult = ComponentAPI.api_query_auth(disanfangInfo.getComponentaccesstoken(), disanfangInfo.getComponentappid(), auth_code);
	 	   disanfangInfo.setAuthorizeraccesstoken(apiQueryAuthResult.getAuthorization_info().getAuthorizer_access_token());
	 	   disanfangInfo.setAuthorizerrefreshtoken(apiQueryAuthResult.getAuthorization_info().getAuthorizer_refresh_token());
	 	   disanfangInfo.setOuttimeaactoken(df.format(new Date()));
	 	   if(disanfangInfoDao.save(disanfangInfo) != null) {
	 		   System.out.println("回调的url，获取接口调用凭据（authorizer_access_token）成功！"+apiQueryAuthResult.getAuthorization_info().getAuthorizer_access_token());
		 	   System.out.println("回调的url，获取刷新授权码（authorizer_refresh_token）成功！"+apiQueryAuthResult.getAuthorization_info().getAuthorizer_refresh_token());
			   System.out.println("authorizer_access_token,authorizer_refresh_token----保存成功");
			}
	 	   
   }
   
	/**
	 * 
	 * @Description 请求code
	 * @author 吴佶津
	 * @date 2018年4月15日
	 * @param request
	 * @param response
	 * @param res
	 * @throws IOException
	 * @throws ParseException 
	 */
  @RequestMapping("/vote.do")
  public void weixinRedirect(HttpServletRequest request, HttpServletResponse response, HttpServletResponse res)
    throws IOException, ParseException
  {
	  Disanfanginfo disanfangInfo = new Disanfanginfo();
	  disanfangInfo = disanfangInfoDao.findOne("wx3d6a383a2aa2b1e2");
	  String outtime_aactoken = disanfangInfo.getOuttimeaactoken();
	  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  Date d1 = new Date();  
	  Date d2 =  df.parse(outtime_aactoken);  
	  long interval = (d1.getTime() - d2.getTime());
	  if(interval > 7000) {		  
		  AuthorizerAccessToken authorizerAccessToken = new AuthorizerAccessToken();
		  authorizerAccessToken = ComponentAPI.api_authorizer_token(disanfangInfo.getComponentaccesstoken(), disanfangInfo.getComponentappid(), "wx49ccd98a0038211d", disanfangInfo.getAuthorizerrefreshtoken());
		  disanfangInfo.setAuthorizeraccesstoken(authorizerAccessToken.getAuthorizer_access_token());
		  disanfangInfo.setAuthorizerrefreshtoken(authorizerAccessToken.getAuthorizer_refresh_token());
		  disanfangInfo.setOuttimeaactoken(df.format(new Date()));
		  if(disanfangInfoDao.save(disanfangInfo) != null) {
					System.out.println("authorizer_access_token----更新成功");
					System.out.println("authorizer_refresh_token----更新成功");
				}
	  }
	  res.setCharacterEncoding("utf-8");
	  res.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx49ccd98a0038211d&redirect_uri=http://www.juhuaihua.cn/huoqucode?response_type=code&scope=snsapi_userinfo&state=1&component_appid=wx3d6a383a2aa2b1e2#wechat_redirect");
  }
  
  /**
   * 
   * @Description 获取code,access_token,openid,用户头像和昵称
   * @author 吴佶津
   * @date 2018年4月15日
   * @param code
   * @param state
   * @param res
   * @throws IOException
 * @throws ParseException 
   */
  @RequestMapping("/huoqucode")
  public void getweixininfo(@RequestParam(name="code", required=false) String code, @RequestParam(name="state") String state, HttpServletResponse res)
    throws IOException, ParseException
  {
    System.out.println("-----------------------------收到请求，请求数据为：" + code + "-----------------------" + state);
    Disanfanginfo disanfangInfo = new Disanfanginfo();
	disanfangInfo = disanfangInfoDao.findOne("wx3d6a383a2aa2b1e2"); 
    String get_access_token_url = "https://api.weixin.qq.com/sns/oauth2/component/access_token?appid=wx49ccd98a0038211d&code=CODE&grant_type=authorization_code&component_appid=wx3d6a383a2aa2b1e2&component_access_token="+disanfangInfo.getComponentaccesstoken();
   
    get_access_token_url = get_access_token_url.replace("CODE", code);
    JSONObject jsonObject = WeixinUtil.httpRequest(get_access_token_url, "GET", null);
    String access_token = jsonObject.getString("access_token");
    String openid = jsonObject.getString("openid");
    String get_userinfo = "https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token + "&openid=" + openid + "&lang=zh_CN";
    JSONObject jsonObject1 = WeixinUtil.httpRequest(get_userinfo, "GET", null);
    String nickname = jsonObject1.getString("nickname");
    String headimgurl = jsonObject1.getString("headimgurl");
    

    res.sendRedirect("index.html?" + nickname + "&" + headimgurl);
  }
  
  	/**
	 * 
	 * @Description 请求code
	 * @author 吴佶津
	 * @date 2018年4月15日
	 * @param request
	 * @param response
	 * @param res
	 * @throws IOException
  	 * @throws ParseException 
	 */
  @RequestMapping("/vote")
  public void weixinRedirectt(HttpServletRequest request, HttpServletResponse response, HttpServletResponse res)
    throws IOException, ParseException
  {
	  Disanfanginfo disanfangInfo = new Disanfanginfo();
	  disanfangInfo = disanfangInfoDao.findOne("wx3d6a383a2aa2b1e2");
	  String outtime_aactoken = disanfangInfo.getOuttimeaactoken();
	  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  Date d1 = new Date();  
	  Date d2 =  df.parse(outtime_aactoken);  
	  long interval = (d1.getTime() - d2.getTime());
	  if(interval > 7000) {		  
		  AuthorizerAccessToken authorizerAccessToken = new AuthorizerAccessToken();
		  authorizerAccessToken = ComponentAPI.api_authorizer_token(disanfangInfo.getComponentaccesstoken(), disanfangInfo.getComponentappid(), "wx49ccd98a0038211d", disanfangInfo.getAuthorizerrefreshtoken());
		  disanfangInfo.setAuthorizeraccesstoken(authorizerAccessToken.getAuthorizer_access_token());
		  disanfangInfo.setAuthorizerrefreshtoken(authorizerAccessToken.getAuthorizer_refresh_token());
		  disanfangInfo.setOuttimeaactoken(df.format(new Date()));
		  if(disanfangInfoDao.save(disanfangInfo) != null) {
					System.out.println("authorizer_access_token----更新成功");
					System.out.println("authorizer_refresh_token----更新成功");
				}
	  }
    res.setCharacterEncoding("utf-8");
    res.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx49ccd98a0038211d&redirect_uri=http://www.juhuaihua.cn/huoqucodee?response_type=code&scope=snsapi_userinfo&state=1&component_appid=wx3d6a383a2aa2b1e2#wechat_redirect");
  }
  
  /**
   * 
   * @Description 获取code,access_token,openid,用户头像和昵称
   * @author 吴佶津
   * @date 2018年4月15日
   * @param code
   * @param state
   * @param res
   * @throws IOException
   */
  @RequestMapping("/huoqucodee")
  public void getweixininfoo(@RequestParam(name="code", required=false) String code, @RequestParam(name="state") String state, HttpServletResponse res)
    throws IOException
  {
    System.out.println("-----------------------------收到请求，请求数据为：" + code + "-----------------------" + state);
    Disanfanginfo disanfangInfo = new Disanfanginfo();
   	disanfangInfo = disanfangInfoDao.findOne("wx3d6a383a2aa2b1e2"); 
    String get_access_token_url = "https://api.weixin.qq.com/sns/oauth2/component/access_token?appid=wx49ccd98a0038211d&code=CODE&grant_type=authorization_code&component_appid=wx3d6a383a2aa2b1e2&component_access_token="+disanfangInfo.getComponentaccesstoken();
    get_access_token_url = get_access_token_url.replace("CODE", code);
    JSONObject jsonObject = WeixinUtil.httpRequest(get_access_token_url, "GET", null);
    String access_token = jsonObject.getString("access_token");
    String openid = jsonObject.getString("openid");
    
    String get_userinfo = "https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token + "&openid=" + openid + "&lang=zh_CN";
    JSONObject jsonObject1 = WeixinUtil.httpRequest(get_userinfo, "GET", null);
    String nickname = jsonObject1.getString("nickname");
    String headimgurl = jsonObject1.getString("headimgurl");
    

    res.sendRedirect("wsindex.html?" + nickname + "&" + headimgurl);
  }
}
