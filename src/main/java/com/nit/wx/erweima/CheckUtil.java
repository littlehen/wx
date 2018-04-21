package com.nit.wx.erweima;

import com.nit.wx.model.Message;
import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import java.io.InputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class CheckUtil {
    public static String sha1(String str) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(str.getBytes());
            byte messageDigest[] = digest.digest();
            StringBuffer hexString = new StringBuffer();
            //字节数组转换为十六进制数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();
        }catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String sort(String token, String timestamp, String nonce) {
        String[] strArray = {token,timestamp,nonce};
        Arrays.sort(strArray);
        StringBuilder sb = new StringBuilder();
        for(String str : strArray) {
            sb.append(str);
        }
        return sb.toString();
    }

    /**
     * 解析微信发来的请求（xml）
     *
     * @param request
     * @return
     * @throws IOException
     * @throws DocumentException
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    //屏蔽某些编译时的警告信息(在强制类型转换的时候编译器会给出警告)
    public static Map<String , String> parseXml(HttpServletRequest request) throws IOException, DocumentException{
        //将解析结果储存在HashMap上
        Map<String, String> map = new HashMap<>();

        // 从request中取得输入流
        InputStream inputStream = request.getInputStream();

        //读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);

        //得到xml根元素
        Element root = document.getRootElement();

        //得到根元素的所有子节点
        List<Element> elementList = root.elements();
        //遍历所有子节点
        for (Element e : elementList)
            map.put(e.getName(), e.getText());
        // 释放资源
        inputStream.close();
        inputStream = null;

        return map;
    }

    /**
     * 文本消息对象转换成xml
     *
     * @param textMessage 文本消息对象
     * @return xml
     */

    public static String textMessageToXml(Message textMessage) {
        XStream xstream = new XStream();
        xstream.alias("xml",textMessage.getClass());
        return xstream.toXML(textMessage);
    }
}
