package com.爬虫.fuyinTV;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.爬虫.ClientDownloadThread;
import com.爬虫.CommonThread;
import com.爬虫.fuyinTV.bean.*;
import com.爬虫.fuyinTV.bean.最近更新.Second_最近更新_XiLieBean;
import com.爬虫.fuyinTV.bean.视频茶经.Second_视频茶经_XiLieBean;
import org.junit.Test;
import org.springframework.util.ObjectUtils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.爬虫.Constant.runThreadNum;
import static com.爬虫.Constant.threadNum;
import static com.爬虫.util.FileUtil.download;
import static com.爬虫.util.HttpUtil.doPostXWwwFormUrlencoded;
import static com.爬虫.util.StringUtil.subString;

public class FuYinTV {

	public static final String 福音_TV = "福音TV";
	public static final String PAGESIZE = "10000";

	String getJsonArrayString(String jsString) {
		return "[" + subString(jsString, "([", "])") + "]";
	}

	String getJsonString(String jsString) {
		return "{" + subString(jsString, "({", "})") + "}";
	}

	@Test
	public void download() throws Exception {
		CommonThread commonThread = new CommonThread();
		commonThread.start();

		String path = "F:\\爬虫\\福音TV\\";      //保存目录

		// 牧者专栏
		getPeopleList(path + "/选项/牧者专栏", "https://data-api.cnfuyin.com/api/category/mzzl?app=fytv&device=mobile&version=1.1.0&callback=_jsonpku8mk42huei");

		//视频茶经
		getFirst_视频茶经(path + "/选项/视频茶经", "https://data-api.cnfuyin.com/api/category/bible?app=fytv&device=mobile&version=1.1.0&callback=_jsonptiujsnm8xlk");

		//最近更新
		second_最近更新_GetXilie(path + "/选项/最近更新", "https://data-api.cnfuyin" +
				".com/api/movie/tops?app=fytv&device=mobile&version=1.1.0&callback=_jsonpep5vass79w");

		//推荐视频
		second_GetXilie(path + "/选项/推荐视频", "https://data-api.cnfuyin.com/api/movie/lists?app=fytv&device=mobile&version");

		//主日信息
		second_GetXilie(path + "/选项/主日信息", "https://data-api.cnfuyin.com/api/movie/lists?app=fytv&device=mobile" +
				"&version=1.1.0&catid=205&page=1&pagesize=10000&callback=_jsonps9lj1bykkql");

		//主页其他内容
		getFirst_IndexCategory(path + "/主页其他内容", "https://data-api.cnfuyin.com/api/misc/indexCategory?app=fytv&device=mobile&version=1.1.0&callback=_jsonptdwtzvce87");


	}

	void getFirst_视频茶经(String path, String url) throws Exception {
		Map<String, String> param = new HashMap<>();
		String retrunString = doPostXWwwFormUrlencoded(url, param, "getFirst_视频茶经");
		List<Second_视频茶经_XiLieBean> second_视频茶经_xiLieBeans = JSONArray.parseArray(getJsonArrayString(retrunString), Second_视频茶经_XiLieBean.class);
		getFirst_视频茶经_内循环(path, second_视频茶经_xiLieBeans);
	}

	void getFirst_视频茶经_内循环(String path, List<Second_视频茶经_XiLieBean> second_视频茶经_xiLieBeans) throws Exception {
		if (!ObjectUtils.isEmpty(second_视频茶经_xiLieBeans) && second_视频茶经_xiLieBeans.size() > 0) {
			for (int i = 0; i < second_视频茶经_xiLieBeans.size(); i++) {
				Second_视频茶经_XiLieBean second_视频茶经_xiLieBean = second_视频茶经_xiLieBeans.get(i);
				if (!ObjectUtils.isEmpty(second_视频茶经_xiLieBean.getBible_id()) && ObjectUtils.isEmpty(second_视频茶经_xiLieBean.getChild())) {
					second_GetXilie(path + "/" + second_视频茶经_xiLieBean.getName(), "https://data-api.cnfuyin" + ".com/api/movie/lists?" + "app=fytv&device=mobile&version=1.1" +
							".0&bible_id=" + second_视频茶经_xiLieBean.getBible_id() +
							"&page=1&pagesize=" + PAGESIZE + "&callback=_jsonp4yhph44ugy");
				} else {
					getFirst_视频茶经_内循环(path + "/" + second_视频茶经_xiLieBean.getName(),
							second_视频茶经_xiLieBeans.get(i).getChild());
				}

			}
		} else {
			System.out.println("------------error------------path: \n" + path + "\n内容:" + second_视频茶经_xiLieBeans.toString() + "\n");
		}
	}

	void getFirst_IndexCategory(String path, String url) throws Exception {
		Map<String, String> param = new HashMap<>();
		String retrunString = doPostXWwwFormUrlencoded(url, param, "主页");
		List<First_IndexCategory> first_indexCategories = JSONArray.parseArray(getJsonArrayString(retrunString), First_IndexCategory.class);
		if (!ObjectUtils.isEmpty(first_indexCategories) && first_indexCategories.size() > 0) {
			for (int i = 0; i < first_indexCategories.size(); i++) {
				First_IndexCategory first_indexCategory = first_indexCategories.get(i);
				second_GetXilie(path + "/" + first_indexCategory.getCatname(), "https://data-api.cnfuyin" + ".com/api/movie/lists?" + "app=fytv&device=mobile&version=1.1" +
						".0&catid=" + first_indexCategory.getCatid() +
						"&page=1&pagesize=" + PAGESIZE + "&callback=_jsonp9d75jtcn9tk");
			}
		} else {
			System.out.println("------------error------------" + first_indexCategories.toString());
		}
	}

	void getPeopleList(String path, String url) throws Exception {
		Map<String, String> param = new HashMap<>();
		String returnString = doPostXWwwFormUrlencoded(url, param, "牧人列表");
		List<First_PeopleBean> first_peopleBeans = JSONArray.parseArray(getJsonArrayString(returnString), First_PeopleBean.class);
		if (!ObjectUtils.isEmpty(first_peopleBeans) && first_peopleBeans.size() > 0) {
			for (int i = 0; i < first_peopleBeans.size(); i++) {
				First_PeopleBean first_peopleBean = first_peopleBeans.get(i);
				second_GetXilie(path + "/" + first_peopleBean.getCategory(), "https://data-api.cnfuyin" +
						".com/api/movie/lists?" + "app=fytv&device=mobile&version=1.1" +
						".0&catid=" + first_peopleBean.getId() +
						"&page=1&pagesize=" + PAGESIZE + "&callback=_jsonprjw69gtz26");
			}
		} else {
			System.out.println("------------error------------" + first_peopleBeans.toString());
		}
	}

	void second_GetXilie(String path, String url) throws Exception {
		Map<String, String> param = new HashMap<>();
		String returnString = doPostXWwwFormUrlencoded(url, param, "系列");
		Second_XiLieBean threeXiLieBean = JSONObject.parseObject(getJsonString(returnString), Second_XiLieBean.class);
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

	void second_最近更新_GetXilie(String path, String url) throws Exception {
		Map<String, String> param = new HashMap<>();
		String returnString = doPostXWwwFormUrlencoded(url, param, "最近更新");
		Second_最近更新_XiLieBean second_最近更新_xiLieBean = JSONObject.parseObject(getJsonString(returnString), Second_最近更新_XiLieBean.class);
		if (!ObjectUtils.isEmpty(second_最近更新_xiLieBean) && !ObjectUtils.isEmpty(second_最近更新_xiLieBean.getUpdate_list())
				&& !ObjectUtils.isEmpty(second_最近更新_xiLieBean.getUpdate_list().size() > 0)) {
			for (int i = 0; i < second_最近更新_xiLieBean.getUpdate_list().size(); i++) {
				Second_最近更新_XiLieBean.XiLie xiLie = second_最近更新_xiLieBean.getUpdate_list().get(i);
				getUrls(path + "/" + xiLie.getTitle(), xiLie.getMovid());
			}
		} else {
			System.out.println("------------error------------" + second_最近更新_xiLieBean.toString());
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
				/*downDetail(i + 1, path + "/mp4", four_detailBean.getTitle(),
						four_detailBean.getUrl_2(), ".mp4");*/
				if (runThreadNum > 1) {
					for (int j = 0; j < 1000; j++) {
						Thread.sleep(2 * 1000);
						if (runThreadNum < 5) {
							break;
						}
					}
				}
				ClientDownloadThread downloadThread = new ClientDownloadThread("fuyin_TV", path, i, four_detailBean.getTitle(),
						".mp4", four_detailBean.getUrl_2());
				pool.submit(downloadThread);
			} catch (Exception e) {
				System.out.println("e.getMessage()\n" + e.getMessage());
			}
			//download mp3
			try {
				/*downDetail(i + 1, path + "/mp3", four_detailBean.getTitle(),
						four_detailBean.getUrl_5(), ".mp3");*/
				if (runThreadNum > 1) {
					for (int j = 0; j < 1000; j++) {
						Thread.sleep(2 * 1000);
						if (runThreadNum < 5) {
							break;
						}
					}
				}
				ClientDownloadThread downloadThread = new ClientDownloadThread("fuyin_TV", path, i, four_detailBean.getTitle(),
						".mp3", four_detailBean.getUrl_5());
				pool.submit(downloadThread);
			} catch (Exception e) {
				System.out.println(four_detailBean.getTitle() + "------------下载失败------------" + four_detailBean.getUrl_5() + "\n\n------------下载失败------------" + e.getMessage());
			}
		} else {
			System.out.println("------------失败------------" + four_detailBean.toString());
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
