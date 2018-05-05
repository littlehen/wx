package com.nit.wx.util;

import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nit.wx.bean.component.PreAuthCode;
import com.nit.wx.dao.DisanfangInfoDao;
import com.nit.wx.disanfangutil.ComponentAPI;
import com.nit.wx.model.Disanfanginfo;

public class CVTicketUtil {
	
	


	/** 
     * 获取component_verify_ticket
     * @param request
     * @param response 
     * @throws Exception 
     */
    public String handleAuthorize(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
        //String appId= getAuthorizerAppidFromXml(xml);//从xml中解析     此时加密的xml数据中ToUserName是非加密的，解析xml获取即可  
        WXBizMsgCrypt pc = new WXBizMsgCrypt("wssiCh8TqbqHZoq9Up56jtauTtOD85Tt", "R3L7ap7lTL37ZVvazV8266gV7L57Ll362F5vQZ67752","wx3d6a383a2aa2b1e2");
        xml = pc.decryptMsg(msg_signature, timestamp, nonce, xml);
        System.out.println("解密后的："+xml);
        Map<String, String> parseXml = XMLConverUtil.convertToMap(xml);
        String component_verify_ticket=parseXml.get("ComponentVerifyTicket");
        System.out.println(component_verify_ticket);
        return component_verify_ticket;
        
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
     * @description 将xml字符串转换成map
     * @param xml
     * @return Map
     */
    public static Map<String,String> readStringXmlOut(String xml) {
        Map<String,String> map = new HashMap<String,String>();
        Document doc = null;
        try {
            doc = DocumentHelper.parseText(xml); // 将字符串转为XML
            Element rootElt = doc.getRootElement(); // 获取根节点
            List<Element> list = rootElt.elements();//获取根节点下所有节点
            for (Element element : list) {  //遍历节点
                map.put(element.getName(), element.getText()); //节点的name为map的key，text为map的value
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

 	   
}
