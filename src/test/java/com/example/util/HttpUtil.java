package com.example.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;
import org.apache.http.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;
import java.util.concurrent.TimeUnit;

//import com.xdja.pmmc.common.util.SSLClient;

/**
 * Created by administrator on 2017/6/7.
 */
public class HttpUtil {
	private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);
	public static final String UTF_8 = "UTF-8";
	public static final String ASCII = "ASCII";

	public static String doPost(String url, String params) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();

		HttpPost httpPost = new HttpPost(url);// 创建httpPost

		httpPost.setHeader("Accept", "application/json");

		httpPost.setHeader("Content-Type", "application/json");

		String charSet = "UTF-8";

		StringEntity entity = new StringEntity(params, charSet);

		httpPost.setEntity(entity);

		CloseableHttpResponse response = null;

		try {
			response = httpclient.execute(httpPost);
			response.setLocale(new java.util.Locale("en", "US"));
			StatusLine status = response.getStatusLine();

			int state = status.getStatusCode();

			if (state == HttpStatus.SC_OK) {
				HttpEntity responseEntity = response.getEntity();

				String jsonString = EntityUtils.toString(responseEntity);
				jsonString = decodeUnicode(jsonString);
				return jsonString;

			} else {
				logger.error("请求返回:" + state + "(" + url + ")");

			}

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return null;
	}


	public static String doPostXWwwFormUrlencoded(String postURL, Map<String, String> map, String type) throws Exception {
		try {
			PostMethod postMethod = null;
			postMethod = new PostMethod(postURL);
			postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
			//postMethod.setRequestHeader("Transfer-Encoding", "chunked");
			//参数设置，需要注意的就是里边不能传NULL，要传空字符串
			NameValuePair[] data = new NameValuePair[map.size()];
			int i = 0;
			for (String key : map.keySet()) {
				String value = map.get(key);
				data[i] = new NameValuePair(key, value);
				i++;
			}

			postMethod.setRequestBody(data);

			org.apache.commons.httpclient.HttpClient httpClient = new org.apache.commons.httpclient.HttpClient();
			httpClient.executeMethod(postMethod); // 执行POST方法
			String result = postMethod.getResponseBodyAsString();
			result = decodeUnicode(result);
			System.out.println(type + "decodeUnicode(result)" + result + "\n\n\n\n");
			return result;
		} catch (Exception e) {
			System.out.println("postURL:\n" + postURL);
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * http 请求数据返回 json 中中文字符为 unicode 编码转汉字转码
	 *
	 * @param theString
	 * @return
	 */
	public static String decodeUnicode(String theString) {
		char aChar;
		int len = theString.length();
		StringBuffer outBuffer = new StringBuffer(len);
		for (int x = 0; x < len; ) {
			aChar = theString.charAt(x++);
			if (aChar == '\\') {
				aChar = theString.charAt(x++);
				if (aChar == 'u') {
					// Read the xxxx
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = theString.charAt(x++);
						switch (aChar) {
							case '0':
							case '1':
							case '2':
							case '3':
							case '4':
							case '5':
							case '6':
							case '7':
							case '8':
							case '9':
								value = (value << 4) + aChar - '0';
								break;
							case 'a':
							case 'b':
							case 'c':
							case 'd':
							case 'e':
							case 'f':
								value = (value << 4) + 10 + aChar - 'a';
								break;
							case 'A':
							case 'B':
							case 'C':
							case 'D':
							case 'E':
							case 'F':
								value = (value << 4) + 10 + aChar - 'A';
								break;
							default:
								throw new IllegalArgumentException(
										"Malformed   \\uxxxx   encoding.");
						}

					}
					outBuffer.append((char) value);
				} else {
					if (aChar == 't')
						aChar = '\t';
					else if (aChar == 'r')
						aChar = '\r';
					else if (aChar == 'n')
						aChar = '\n';
					else if (aChar == 'f')
						aChar = '\f';
					outBuffer.append(aChar);
				}
			} else
				outBuffer.append(aChar);
		}
		return outBuffer.toString();
	}

	public static HttpResponse sendPOSTHttpResponse(String url, Map<String, String> header, String body) throws IOException {
		logger.debug("send post request: {}, {}, {}", url, JSON.toJSONString(header), body);
		try (CloseableHttpClient hc = HttpClientBuilder.create().build()) {
			HttpPost post = new HttpPost(url);

			for (String key : header.keySet()) {
				post.addHeader(key, header.get(key));
			}

			if (StringUtils.isNotEmpty(body)) {
				post.addHeader("Content-type", "application/json;charset=UTF-8 ");
				post.setEntity(new StringEntity(body, "utf-8"));
			}
			HttpResponse response = hc.execute(post);
			logger.debug("http response: {} {} {}", response.toString());
			post.releaseConnection();
			hc.getConnectionManager().closeIdleConnections(0, TimeUnit.MILLISECONDS);
			return response;
		} catch (Exception e) {
			logger.error("send request error: {}", e.getMessage());
			return null;
		}
	}


	private static X509TrustManager TRUST_ALL = new X509TrustManager() {
		@Override
		public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

		}

		@Override
		public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

		}

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[0];
		}
	};
	private static HostnameVerifier TRUST_ALL_HOSTNAME_VERIFIER = new HostnameVerifier() {
		@Override
		public boolean verify(String s, SSLSession sslSession) {
			return true;
		}
	};


	public static String sendMsg(String accessToken, String userToken, String url, JSONObject msg) {
		logger.info("pushMsg accessToken {} userToken {} url {} msg {}", accessToken, userToken, url, msg);
		try (CloseableHttpClient hc = HttpClientBuilder.create().build()) {
			HttpPost post = new HttpPost(url);
			post.addHeader("Authorization", "Bearer " + accessToken);
			post.addHeader("userToken", userToken);
			post.addHeader("Content-type", "application/json");
			post.setEntity(new StringEntity(msg.toJSONString(), "utf-8"));
			HttpResponse response = hc.execute(post);
			String bodyStr = EntityUtils.toString(response.getEntity());
			JSONObject jsonObject = JSON.parseObject(bodyStr);
//            logger.info("pushMsg bodyStr {}",jsonObject.toJSONString());
			if (response.getStatusLine().getStatusCode() != 200) {
				return jsonObject.toJSONString();
			}
			post.releaseConnection();
			hc.getConnectionManager().closeIdleConnections(0, TimeUnit.MILLISECONDS);
			return "";
		} catch (Exception e) {
			logger.error("pushMsg send request error: {}", e);
			return e.getMessage();
		}
	}
}
