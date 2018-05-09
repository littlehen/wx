package com.nit.wx.controller;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nit.wx.service.IsmemberService;
import com.nit.wx.util.MessageUtil;
import net.sf.json.JSONObject;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nit.wx.bean.component.ApiQueryAuthResult;
import com.nit.wx.bean.component.AuthorizerAccessToken;
import com.nit.wx.bean.component.PreAuthCode;
import com.nit.wx.dao.DisanfangInfoDao;
import com.nit.wx.dao.UserListDao;
import com.nit.wx.disanfangutil.ComponentAPI;
import com.nit.wx.model.Disanfanginfo;
import com.nit.wx.model.UserList;
import com.nit.wx.service.DisanfangInfoService;
import com.nit.wx.util.AesException;
import com.nit.wx.util.CVTicketUtil;
import com.nit.wx.util.WeixinUtil;


@RestController
public class WeiXinController
{
	@Autowired 
	DisanfangInfoService disanfangInfoService;
	
	@Autowired 
	DisanfangInfoDao disanfangInfoDao;

	@Autowired
	IsmemberService ismemberService;
	
	@Autowired
	UserListDao userListDao;



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
	@SuppressWarnings("null")
	@RequestMapping(value="/wechat/author")
   public void acceptAuthorizeEvent(
       HttpServletResponse response,HttpServletRequest request){
	   CVTicketUtil cvTicketUtil = new CVTicketUtil();
           try {
               //处理授权事件
        	   String component_verify_ticket = cvTicketUtil.handleAuthorize(request,response);
        	   Disanfanginfo disanfangInfo = new Disanfanginfo();
        	   disanfangInfo = disanfangInfoDao.findOne("wx3d6a383a2aa2b1e2");
        	   if(disanfangInfo == null) {
   	       		disanfangInfo.setComponentappid("wx3d6a383a2aa2b1e2");
   	       		disanfangInfo.setComponentappsecret("04bbb9b44af5f4f9bbf53e232fc89825");
   	       		disanfangInfo.setComponentverifyticket(component_verify_ticket);
   	       	    System.out.println("component_verify_ticket存入成功2");
   	       	    disanfangInfoDao.save(disanfangInfo);
   	       	   }
        	   else {
	       		disanfangInfo.setComponentappid(disanfangInfo.getComponentappid());
	       		disanfangInfo.setComponentverifyticket(component_verify_ticket);
	       	    System.out.println("component_verify_ticket存入成功2");
	       	    disanfangInfoDao.save(disanfangInfo);
	       	   }
	     	   
               
               PrintWriter pw = response.getWriter();
               pw.write("success");
               pw.flush();
               getcomponent_access_token();
               getpreauthcode();
              
           } catch (Exception e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
           }
   }
   
	 /**
     * 
     * @Description 获取component_access_token
     * @author 吴佶津
     * @date 2018年4月16日
     * @throws Exception
     */
    public void getcomponent_access_token() throws Exception { 
 	   String url = "https://api.weixin.qq.com/cgi-bin/component/api_component_token";
 	   
 	   Disanfanginfo disanfangInfo = new Disanfanginfo();
 	   disanfangInfo = disanfangInfoDao.findOne("wx3d6a383a2aa2b1e2");
 	   if(disanfangInfo != null) {
 		  JSONObject json = new JSONObject();
 	       json.accumulate("component_appid",disanfangInfo.getComponentappid());
 	       json.accumulate("component_appsecret",disanfangInfo.getComponentappsecret());
 	       json.accumulate("component_verify_ticket",disanfangInfo.getComponentverifyticket());
 	       String outtime_cactoken = disanfangInfo.getOuttimecactoken();
 	       if(outtime_cactoken !=null && outtime_cactoken != "") {
 	    	   SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 	    	   Date d1 = new Date();  
 	    	   Date d2 =  df.parse(outtime_cactoken);  
 	    	   long interval = (d1.getTime() - d2.getTime());
 	    	   if(interval > 7000) {
 	    		   JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", json.toString());
 	    		   if(jsonObject != null) {
 	   			    String component_access_token = jsonObject.getString("component_access_token");
 	   				System.out.println("component_access_token:----"+component_access_token);
 	   				disanfangInfo.setComponentappid(disanfangInfo.getComponentappid());
 	   				disanfangInfo.setComponentaccesstoken(component_access_token);
 	   				disanfangInfo.setOuttimecactoken(df.format(new Date()));
 	   				if(disanfangInfoDao.save(disanfangInfo) != null) {
 	   					System.out.println("component_access_token----更新成功");
 	   				}
 	   			}
 	    	   }
 	       }
 	      if(outtime_cactoken == null || outtime_cactoken == "") {
 	    	   JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", json.toString());
 	    	  System.out.println(jsonObject);
 	    	 System.out.println(jsonObject.toString());
 	    	   if(jsonObject != null) {
	 	    		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
 	   			    String component_access_token = jsonObject.getString("component_access_token");
 	   				System.out.println("component_access_token:----"+component_access_token);
 	   				disanfangInfo.setComponentappid(disanfangInfo.getComponentappid());
 	   				disanfangInfo.setComponentaccesstoken(component_access_token);
 	   				disanfangInfo.setOuttimecactoken(df.format(new Date()));
 	   				if(disanfangInfoDao.save(disanfangInfo) != null) {
 	   					System.out.println("component_access_token----保存成功");
 	   				}
 	   			}
 	       } 		  
 	    }
 	  }
	
    
    
    public void getpreauthcode() {
    	System.out.println("999999999999");
    	Disanfanginfo disanfangInfo = new Disanfanginfo();
    	PreAuthCode preAuthCode = new PreAuthCode();
  	    disanfangInfo = disanfangInfoDao.findOne("wx3d6a383a2aa2b1e2");
  	    if(disanfangInfo != null) {
  	    	preAuthCode = ComponentAPI.api_create_preauthcode(disanfangInfo.getComponentaccesstoken(), disanfangInfo.getComponentappid());
  	    	disanfangInfo.setComponentappid(disanfangInfo.getComponentappid());
  	    	disanfangInfo.setPreauthcode(preAuthCode.getPre_auth_code());
  	    	disanfangInfoDao.save(disanfangInfo);
  	    	System.out.println("获取预授权码成功!"+preAuthCode.getPre_auth_code());
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
	 	   disanfangInfo.setComponentappid(disanfangInfo.getComponentappid());
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
	 * @param res
	 * @throws IOException
	 * @throws ParseException 
	 */
  @RequestMapping("/vote.do")
  public void weixinRedirect(HttpServletRequest request,  HttpServletResponse res)
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
		  authorizerAccessToken = ComponentAPI.api_authorizer_token(disanfangInfo.getComponentaccesstoken(), disanfangInfo.getComponentappid(), "wxf00dadf1b0a45c61", disanfangInfo.getAuthorizerrefreshtoken());
		  disanfangInfo.setComponentappid(disanfangInfo.getComponentappid());
		  disanfangInfo.setAuthorizeraccesstoken(authorizerAccessToken.getAuthorizer_access_token());
		  disanfangInfo.setAuthorizerrefreshtoken(authorizerAccessToken.getAuthorizer_refresh_token());
		  disanfangInfo.setOuttimeaactoken(df.format(new Date()));
		  if(disanfangInfoDao.save(disanfangInfo) != null) {
					System.out.println("authorizer_access_token----更新成功");
					System.out.println("authorizer_refresh_token----更新成功");
				}
	  }
	  res.setCharacterEncoding("utf-8");
	  res.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxf00dadf1b0a45c61&redirect_uri=http://www.juhuaihua.cn/huoqucode?response_type=code&scope=snsapi_userinfo&state=1&component_appid=wx3d6a383a2aa2b1e2#wechat_redirect");
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
    String get_access_token_url = "https://api.weixin.qq.com/sns/oauth2/component/access_token?appid=wxf00dadf1b0a45c61&code=CODE&grant_type=authorization_code&component_appid=wx3d6a383a2aa2b1e2&component_access_token="+disanfangInfo.getComponentaccesstoken();
   
    get_access_token_url = get_access_token_url.replace("CODE", code);
    JSONObject jsonObject = WeixinUtil.httpRequest(get_access_token_url, "GET", null);
    String access_token = jsonObject.getString("access_token");
    String openid = jsonObject.getString("openid");
    String get_userinfo = "https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token + "&openid=" + openid + "&lang=zh_CN";
    JSONObject jsonObject1 = WeixinUtil.httpRequest(get_userinfo, "GET", null);
    String nickname = jsonObject1.getString("nickname");
    String headimgurl = jsonObject1.getString("headimgurl");
    UserList userList = new UserList();
    userList = userListDao.findByOpenid(openid);
    if(userList == null) {
    	userList = new UserList();
		userList.setOpenid(openid);
		userListDao.save(userList);
	}
    String qrCode = disanfangInfo.getQrCode();
	if ("".equals(qrCode) || qrCode == null) {
		String uuu = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxf00dadf1b0a45c61&secret=4a691c01b20290399f45e7d8352e9782";
		JSONObject jsonObject3 = WeixinUtil.httpRequest(uuu,"GET",null);
		String get_qrCode = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + jsonObject3.getString("access_token");
		String jsonStr = "{\"action_name\": \"QR_LIMIT_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": \"test\"}}}";
		JSONObject jsonObject2 = WeixinUtil.httpRequest(get_qrCode, "POST", jsonStr);
		String url = jsonObject2.getString("url");
		disanfangInfo.setQrCode(url);
		disanfangInfoDao.save(disanfangInfo);
	}
    res.sendRedirect("index.html?" + nickname + "&" + headimgurl + "&" + openid);
  }
  
  	/**
	 * 
	 * @Description 请求code
	 * @author 吴佶津
	 * @date 2018年4月15日
	 * @param request
	 * @param res
	 * @throws IOException
  	 * @throws ParseException 
	 */
  @RequestMapping("/vote")
  public void weixinRedirectt(HttpServletRequest request,  HttpServletResponse res)
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
		  authorizerAccessToken = ComponentAPI.api_authorizer_token(disanfangInfo.getComponentaccesstoken(), disanfangInfo.getComponentappid(), "wxf00dadf1b0a45c61", disanfangInfo.getAuthorizerrefreshtoken());
		  disanfangInfo.setComponentappid(disanfangInfo.getComponentappid());
		  disanfangInfo.setAuthorizeraccesstoken(authorizerAccessToken.getAuthorizer_access_token());
		  disanfangInfo.setAuthorizerrefreshtoken(authorizerAccessToken.getAuthorizer_refresh_token());
		  disanfangInfo.setOuttimeaactoken(df.format(new Date()));
		  if(disanfangInfoDao.save(disanfangInfo) != null) {
					System.out.println("authorizer_access_token----更新成功");
					System.out.println("authorizer_refresh_token----更新成功");
				}
	  }
    res.setCharacterEncoding("utf-8");
    res.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxf00dadf1b0a45c61&redirect_uri=http://www.juhuaihua.cn/huoqucodee?response_type=code&scope=snsapi_userinfo&state=1&component_appid=wx3d6a383a2aa2b1e2#wechat_redirect");
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
    String get_access_token_url = "https://api.weixin.qq.com/sns/oauth2/component/access_token?appid=wxf00dadf1b0a45c61&code=CODE&grant_type=authorization_code&component_appid=wx3d6a383a2aa2b1e2&component_access_token="+disanfangInfo.getComponentaccesstoken();
    get_access_token_url = get_access_token_url.replace("CODE", code);
    JSONObject jsonObject = WeixinUtil.httpRequest(get_access_token_url, "GET", null);
    String access_token = jsonObject.getString("access_token");
    String openid = jsonObject.getString("openid");
    
    String get_userinfo = "https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token + "&openid=" + openid + "&lang=zh_CN";
    JSONObject jsonObject1 = WeixinUtil.httpRequest(get_userinfo, "GET", null);
    String nickname = jsonObject1.getString("nickname");
    String headimgurl = jsonObject1.getString("headimgurl");
 	String qrCode = disanfangInfo.getQrCode();
  	if ("".equals(qrCode) && qrCode == null) {
  		String uuu = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxf00dadf1b0a45c61&secret=4a691c01b20290399f45e7d8352e9782";
  		JSONObject jsonObject3 = WeixinUtil.httpRequest(uuu,"GET",null);
		String get_qrCode = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + jsonObject3.getString("access_token");
		String jsonStr = "{\"action_name\": \"QR_LIMIT_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": \"test\"}}}";
		JSONObject jsonObject2 = WeixinUtil.httpRequest(get_qrCode, "POST", jsonStr);
		String url = jsonObject2.getString("url");
		disanfangInfo.setQrCode(url);
		disanfangInfoDao.save(disanfangInfo);
  	}
    res.sendRedirect("wsindex.html?" + nickname + "&" + headimgurl + "&" + openid);
  }


  @SuppressWarnings("unused")
  @RequestMapping("/wechat/messageReceive/{appid}/callback")
  public void MessageReceive(HttpServletRequest request,HttpServletResponse response){
	  String msgSignature = request.getParameter("msg_signature");
	  try{
		  StringBuilder sb = new StringBuilder();
		  BufferedReader in = request.getReader();
		  String line;
		  while ((line = in.readLine())!= null){
			sb.append(line);
		  }
		  in.close();
		  String xml = sb.toString();
		  Document document = DocumentHelper.parseText(xml);
		  Element rootElt = document.getRootElement();
		  try {
			  String FromName = rootElt.elementText("FromUserName");
			  String MsgType = rootElt.elementText("MsgType");
			  String evenType = rootElt.elementText("Event");
			  if (MsgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)&&MsgType==""&&MsgType==null){
				if (evenType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE) || evenType.equals(MessageUtil.EVENT_TYPE_SCAN)){
					ismemberService.findUserState(FromName);
				}
			  }else {
			  	System.out.println("123");
			  }
		  }catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  PrintWriter pw = response.getWriter();
		  pw.write("success");
		  pw.flush();
	  } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
}
