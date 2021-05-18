package com.example.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.io.OutputStream;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

//import com.xdja.pmmc.common.util.SSLClient;

/**
 * 模拟浏览器进行下载
 */
public class HttpClientUtil {

	public static void downLoadByHttpClient(String url, OutputStream out) throws IOException {

		//创建httpclient实例，采用默认的参数配置
		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpGet get = new HttpGet(url);   //使用Get方法提交

		//请求的参数配置，分别设置连接池获取连接的超时时间，连接上服务器的时间，服务器返回数据的时间
		RequestConfig config = RequestConfig.custom()
				.setConnectionRequestTimeout(3000)
				.setConnectTimeout(3000)
				.setSocketTimeout(3000)
				.build();
		//配置信息添加到Get请求中
		get.setConfig(config);
		//通过httpclient的execute提交 请求 ，并用CloseableHttpResponse接受返回信息
		CloseableHttpResponse response = httpClient.execute(get);
		//服务器返回的状态
		int statusCode = response.getStatusLine().getStatusCode();
		//判断返回的状态码是否是200 ，200 代表服务器响应成功，并成功返回信息
		if (statusCode == HttpStatus.SC_OK) {
			IOUtils.copy(response.getEntity().getContent(), out);
			out.flush();
			out.close();
		} else {
			System.out.println("Demo.example -------->" + "获取信息失败");
		}

	}
}
