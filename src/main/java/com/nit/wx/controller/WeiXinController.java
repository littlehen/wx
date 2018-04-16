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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nit.wx.bean.DisanfangInfo;
import com.nit.wx.dao.DisanfangInfoDao;
import com.nit.wx.util.AesException;
import com.nit.wx.util.CVTicketUtil;
import com.nit.wx.util.WeixinUtil;


@RestController
public class WeiXinController
{
	
	
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
   @RequestMapping(value="/wechat/author",method={RequestMethod.GET,RequestMethod.POST})
   public void acceptAuthorizeEvent(
       HttpServletResponse response,HttpServletRequest request){
	   CVTicketUtil cvTicketUtil = new CVTicketUtil();
           try {
               //处理授权事件
        	   cvTicketUtil.handleAuthorize(request,response);
               PrintWriter pw = response.getWriter();
               pw.write("success");
               pw.flush();
           } catch (Exception e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
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
	 */
  @RequestMapping("/vote.do")
  public void weixinRedirect(HttpServletRequest request, HttpServletResponse response, HttpServletResponse res)
    throws IOException
  {
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
   */
  @RequestMapping("/huoqucode")
  public void getweixininfo(@RequestParam(name="code", required=false) String code, @RequestParam(name="state") String state, HttpServletResponse res)
    throws IOException
  {
    System.out.println("-----------------------------收到请求，请求数据为：" + code + "-----------------------" + state);
    String get_access_token_url = "https://api.weixin.qq.com/sns/oauth2/component/access_token?appid=wx49ccd98a0038211d&code=CODE&grant_type=authorization_code&component_appid=wx3d6a383a2aa2b1e2&component_access_token=wssiCh8TqbqHZoq9Up56jtauTtOD85Tt";
   
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
	 */
  @RequestMapping("/vote")
  public void weixinRedirectt(HttpServletRequest request, HttpServletResponse response, HttpServletResponse res)
    throws IOException
  {
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
   */
  @RequestMapping("/huoqucodee")
  public void getweixininfoo(@RequestParam(name="code", required=false) String code, @RequestParam(name="state") String state, HttpServletResponse res)
    throws IOException
  {
    System.out.println("-----------------------------收到请求，请求数据为：" + code + "-----------------------" + state);
    String get_access_token_url = "https://api.weixin.qq.com/sns/oauth2/component/access_token?appid=wx49ccd98a0038211d&code=CODE&grant_type=authorization_code&component_appid=wx3d6a383a2aa2b1e2&component_access_token=wssiCh8TqbqHZoq9Up56jtauTtOD85Tt";    


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
