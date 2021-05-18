package com.example.mybatisplus.fuyinTV;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.mybatisplus.CommonThread;
import com.example.mybatisplus.DownloadThread;
import com.example.mybatisplus.fuyinTV.bean.Four_DetailBean;
import com.example.mybatisplus.fuyinTV.bean.Three_UrlBean;
import com.example.mybatisplus.fuyinTV.bean.First_PeopleBean;
import com.example.mybatisplus.fuyinTV.bean.Second_XiLieBean;
import com.example.util.FileUtil;
import org.junit.Test;
import org.omg.PortableServer.THREAD_POLICY_ID;
import org.springframework.util.ObjectUtils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.example.mybatisplus.Constant.runThreadNum;
import static com.example.mybatisplus.Constant.threadNum;
import static com.example.util.FileUtil.download;
import static com.example.util.HttpClientUtil.downLoadByHttpClient;
import static com.example.util.HttpUtil.doPostXWwwFormUrlencoded;
import static com.example.util.StringUtil.subString;

public class downloadFuYinTV {

	public static final String 福音_TV = "福音TV";
	public static final String PAGESIZE = "10000";


	String host = "https://www.tingdao.org";

	String getJsonArrayString(String jsString) {
		return "[" + subString(jsString, "([", "])") + "]";
	}

	String getJsonString(String jsString) {
		return "{" + subString(jsString, "({", "})") + "}";
	}

	@Test
	public void downloadTV() throws Exception {
		CommonThread commonThread = new CommonThread();
		commonThread.start();

		String path = "F:\\爬虫\\福音TV";      //保存目录
		getPeopleList(path + "/选项/牧者专栏");

	}


	void getPeopleList(String path) throws Exception {
		Map<String, String> param = new HashMap<>();
		String url = "https://data-api.cnfuyin.com/api/category/mzzl?app=fytv&device=mobile&version=1.1.0&callback=_jsonpku8mk42huei";
		String retrunString = doPostXWwwFormUrlencoded(url, param, "牧人列表");
		List<First_PeopleBean> first_peopleBeans = JSONArray.parseArray(getJsonArrayString(retrunString), First_PeopleBean.class);
		if (!ObjectUtils.isEmpty(first_peopleBeans) && first_peopleBeans.size() > 0) {
			for (int i = 0; i < first_peopleBeans.size(); i++) {
				First_PeopleBean first_peopleBean = first_peopleBeans.get(i);
				getXilie(path + "/" + first_peopleBean.getCategory(), first_peopleBean.getId());
			}
		} else {
			System.out.println("------------error------------" + first_peopleBeans.toString());
		}

	}

	void getXilie(String path, String id) throws Exception {
		Map<String, String> param = new HashMap<>();
		String url = "https://data-api.cnfuyin.com/api/movie/lists?app=fytv&device=mobile&version=1.1.0&catid=" + id +
				"&page=1&pagesize=" + PAGESIZE + "&callback=_jsonprjw69gtz26";
		String retrunString = doPostXWwwFormUrlencoded(url, param, "系列");
		Second_XiLieBean threeXiLieBean = JSONObject.parseObject(getJsonString(retrunString), Second_XiLieBean.class);
		if (!ObjectUtils.isEmpty(threeXiLieBean) && !ObjectUtils.isEmpty(threeXiLieBean.getData())
				&& !ObjectUtils.isEmpty(threeXiLieBean.getData().size() > 0)) {
			for (int i = 0; i < threeXiLieBean.getData().size(); i++) {
				Second_XiLieBean.XiLie xiLie = threeXiLieBean.getData().get(i);
				getUrls(path + "/" + xiLie.getTitle(), xiLie.getMovid());
			}
		} else {
			System.out.println("------------error------------" + threeXiLieBean.toString());
		}

	}

	void getUrls(String path, int movid) throws Exception {
		Map<String, String> param = new HashMap<>();
		String url = "https://data-api.cnfuyin.com/api/movie/index?app=fytv&device=mobile&version=1.1.0&movid=" + movid + "&token=2fc55a83f4f7a7e8e32a3dddfeaf68d1";
		url += movid;
		String retrunString = doPostXWwwFormUrlencoded(url, param, "牧人系列");
		Three_UrlBean three_urlBean = JSONObject.parseObject(retrunString, Three_UrlBean.class);
		if (!ObjectUtils.isEmpty(three_urlBean) &&
				!ObjectUtils.isEmpty(three_urlBean.getUrls())) {
			for (int i = 0; i < three_urlBean.getUrls().size(); i++) {
				Three_UrlBean.Url urlBean = three_urlBean.getUrls().get(i);
				try {
					getDetail(i, path, movid, urlBean.getUrlid());
				} catch (Exception e) {
					System.out.println("------------失败------------" + urlBean.toString() + "e.getMessage()" + e.getMessage());
				}
			}
		} else {
			System.out.println("------------失败------------" + three_urlBean.toString());
		}
	}


	//创建一个具有固定线程数的线程池
	private final static ExecutorService pool = Executors.newFixedThreadPool(threadNum);

	void getDetail(int i, String path, int movid, int urlid) throws Exception {
		Map<String, String> param = new HashMap<>();
		String url =
				"https://data-api.cnfuyin.com/api/url/index?app=fytv&device=mobile&version=1.1.0&" + "urlid=" + urlid + "&movid=" + movid;
		String retrunString = doPostXWwwFormUrlencoded(url, param, "牧人系列");
		Four_DetailBean four_detailBean = JSONObject.parseObject(retrunString, Four_DetailBean.class);
		if (!ObjectUtils.isEmpty(four_detailBean) &&
				!ObjectUtils.isEmpty(four_detailBean.getServer())) {
			try {
				//download mp4

				/*download(path + "/mp4", "/" + i + "_" + four_detailBean.getTitle() + ".mp4", four_detailBean.getUrl_2());
				 */

				if (runThreadNum > 3) {
					for (int j = 0; i < 100; j++) {
						if (runThreadNum > 3) {
							Thread.sleep(10 * 1000);
						}
					}
				}
				DownloadThread downloadThread = new DownloadThread("fuyin_TV", path, i, four_detailBean.getTitle(),
						".mp4", four_detailBean.getUrl_2());
				pool.submit(downloadThread);
			} catch (Exception e) {
				System.out.println("e.getMessage()\n" + e.getMessage());
			}
			//download mp3
			try {
				/*downDetail(i + 1, path + "/mp3", four_detailBean.getTitle(),
						four_detailBean.getUrl_5(), ".mp3");*/

				DownloadThread downloadThread = new DownloadThread("fuyin_TV", path, i, four_detailBean.getTitle(),
						".mp3", four_detailBean.getUrl_5());
				pool.submit(downloadThread);

				/*download(path + "/mp3", "/" + i + "_" + four_detailBean.getTitle() + ".mp3",
						four_detailBean.getUrl_5());*/

			} catch (Exception e) {
				System.out.println(four_detailBean.getTitle() + "------------下载失败------------" + four_detailBean.getUrl_5() + "\n\n------------下载失败------------" + e.getMessage());
			}
		} else {
			System.out.println("------------失败------------" + four_detailBean.toString());
		}
	}

	void downDetail(int i, String path, String name, String downloadUrl, String type) throws Exception {
		FileUtil.makeDir(path);
		File file = new File(path + "/" + i + "_" + name + type);
		if (!file.exists()) {
			try {
				// http请求
				OutputStream output = new FileOutputStream(file);
				downLoadByHttpClient(downloadUrl, output);
			} catch (IOException e) {
				System.out.println("下载失败---e.getMessage()----------" + e.getMessage());
				e.printStackTrace();
			}
		} else {
			System.out.println("文件已存在--------" + path + "/" + i + "_" + name + type);
		}
	}


	/**
	 * 向指定URL发送GET方法的请求
	 *
	 * @param url   发送请求的URL
	 * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 *
	 * @param url   发送请求的 URL
	 * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		//使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	String sendPost(String host, String url, Map<String, String> param, String type) throws Exception {
		String rt = doPostXWwwFormUrlencoded(host + url, param, type);
		if (ObjectUtils.isEmpty(rt)) {
			throw new RuntimeException(type + "发送失败");
		}
		return rt;
	}


}
