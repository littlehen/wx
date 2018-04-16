package com.nit.wx.other;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/**
 * 
 * @Description 证书信任管理器
 * @author 吴佶津
 * @date 2018年4月14日
 */
public class MyX509TrustManager implements X509TrustManager{
	public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    }
 
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    }
 
    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}
