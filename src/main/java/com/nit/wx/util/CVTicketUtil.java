package com.nit.wx.util;

import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;

import com.nit.wx.bean.DisanfangInfo;
import com.nit.wx.dao.DisanfangInfoDao;
import com.nit.wx.service.DisanfangInfoService;

import net.sf.json.JSONObject;

public class CVTicketUtil {
	@Autowired
	DisanfangInfoService disanfangInfoService;
	@Autowired 
	DisanfangInfoDao disanfangInfoDao;

	/** 
     * 获取component_verify_ticket
     * @param request
     * @param response 
     * @throws Exception 
     */
    public void handleAuthorize(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // TODO Auto-generated method stub
        String timestamp=request.getParameter("timestamp");
        String encrypt_type=request.getParameter("encrypt_type");
        String nonce=request.getParameter("nonce");
        String msg_signature=request.getParameter("msg_signature");
        System.out.println("timestamp:"+timestamp);
        System.out.println("encrypt_type:"+encrypt_type);
        System.out.println("nonce:"+nonce);
        System.out.println("msg_signature:"+msg_signature);
        //验证通过后

        StringBuilder sb = new StringBuilder();
        BufferedReader in = request.getReader();
        String line;
        while ((line = in.readLine()) != null) {
            sb.append(line);
        }
        String xml = sb.toString();
        System.out.println("微信推送的原生："+xml);
        String appId= getAuthorizerAppidFromXml(xml);//从xml中解析     此时加密的xml数据中ToUserName是非加密的，解析xml获取即可  
        WXBizMsgCrypt pc = new WXBizMsgCrypt("wssiCh8TqbqHZoq9Up56jtauTtOD85Tt", "R3L7ap7lTL37ZVvazV8266gV7L57Ll362F5vQZ67752",appId);
        xml = pc.decryptMsg(msg_signature, timestamp, nonce, xml);
        System.out.println("解密后的："+xml);
        Map<String, String> parseXml = XMLConverUtil.convertToMap(xml);
        String component_verify_ticket=parseXml.get("ComponentVerifyTicket");
        System.out.println(component_verify_ticket);
        disanfangInfoService.savedisanfangInfo(component_verify_ticket);
        getcomponent_access_token();
    }
      //这里的WeChatContants.token和encodingAesKey就是在开发者资料填写的token和key。 第三方平台组件加密密钥
    
    String getAuthorizerAppidFromXml(String xml) {  
        Document doc;  
        try {  
            doc = DocumentHelper.parseText(xml);  
            Element rootElt = doc.getRootElement();  
            String toUserName = rootElt.elementText("ToUserName");  
            return toUserName;  
        } catch (DocumentException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        return null;  
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
 	   
 	   DisanfangInfo disanfangInfo = new DisanfangInfo();
 	   disanfangInfo = disanfangInfoDao.findOne("wx3d6a383a2aa2b1e2");
 	   
 	   if(disanfangInfo != null) {
 		   
 		   JSONObject json = new JSONObject();
 	       json.accumulate("component_appid",disanfangInfo.getComponent_appid());
 	       json.accumulate("component_appsecret",disanfangInfo.getComponent_appsecret());
 	       json.accumulate("component_verify_ticket",disanfangInfo.getComponent_verify_ticket());
 	       
 	       String outtime_cactoken = disanfangInfo.getOuttime_cactoken();
 	       if(outtime_cactoken !=null || outtime_cactoken != "") {
 	    	   SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 	    	   Date d1 = new Date();  
 	    	   Date d2 =  df.parse(outtime_cactoken);  
 	    	   long interval = (d1.getTime() - d2.getTime());
 	    	   if(interval > 7000) {
 	    		   JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", json.toString());
 	    		   if(jsonObject != null) {
 	   			    String component_access_token = jsonObject.getString("component_access_token");
 	   				System.out.println("component_access_token:----"+component_access_token);
 	   				disanfangInfo.setComponent_access_token(component_access_token);
 	   				if(disanfangInfoDao.save(disanfangInfo) != null) {
 	   					System.out.println("component_access_token----保存成功");
 	   				}
 	   			}
 	    	   }
 	       }
 	      if(outtime_cactoken == null || outtime_cactoken == "") {
 	    	   JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", json.toString());
 	    	   if(jsonObject != null) {
 	   			    String component_access_token = jsonObject.getString("component_access_token");
 	   				System.out.println("component_access_token:----"+component_access_token);
 	   				disanfangInfo.setComponent_access_token(component_access_token);
 	   				if(disanfangInfoDao.save(disanfangInfo) != null) {
 	   					System.out.println("component_access_token----保存成功");
 	   				}
 	   			}
 	       } 		  
 	    }
 	  }
 	   
}
