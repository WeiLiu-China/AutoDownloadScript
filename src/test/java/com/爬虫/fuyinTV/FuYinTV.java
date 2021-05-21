package com.爬虫.fuyinTV;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.爬虫.ClientDownloadThread;
import com.爬虫.CommonThread;
import com.爬虫.fuyinTV.bean.*;
import com.爬虫.fuyinTV.bean.最近更新.Second_最近更新_XiLieBean;
import com.爬虫.fuyinTV.bean.视频茶经.Second_视频茶经_XiLieBean;
import org.apache.commons.lang.StringEscapeUtils;
import org.junit.Test;
import org.springframework.util.ObjectUtils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
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
		try {
			JSONObject.parseObject(getJsonString(returnString), Second_XiLieBean.class);
		} catch (Exception e) {
			System.out.println();
		}
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

	public static void main(String[] args) {
		String result = "{\"movid\":2491,\"title\":\"主祷文-唐崇荣牧师\",\"subtitle\":\"唐崇荣牧师分享主祷文\",\"actor\":\"唐崇荣\",\"area\":\"大陆\",\"director\":\"唐崇荣国际布道团\",\"category\":\"208\",\"pic\":\"https:\\/\\/img.tv.cnfuyin.com\\/upload\\/image\\/movie\\/20141024\\/101b361cd937d18a.jpg\",\"addtime\":1414118928,\"modifytime\":1436328828,\"urlcount_1\":19,\"urlcount_5\":19,\"smil\":0,\"desc\":0,\"content\":\"<p>&nbsp;主祷文<br \\/>\\r\\n我们在天上的父，愿人都尊你的名为圣。<br \\/>\\r\\n愿你的国降临。愿你的旨意行在地上，如同行在天上。<br \\/>\\r\\n我们日用的饮食，今日赐给我们。<br \\/>\\r\\n免我们的债，如同我们免了人的债。<br \\/>\\r\\n不叫我们遇见试探。救我们脱离凶恶。<br \\/>\\r\\n因为国度、权柄、荣耀，全是你的，直到永远。阿们<\\/p>\\r\\n\\r\\n<p>马太福音6章9-13节 请听唐牧师的精彩分享！<\\/p>\\r\\n\\r\\n<p><strong>唐崇荣牧师分享的主祷文系列讲座已更新完结。<\\/strong><br \\/>\\r\\n<strong style=\\\"line-height:1.6em\\\">更新日期:2015-07-8<\\/strong><\\/p>\\r\\n\",\"urls\":[{\"urlid\":37600,\"title\":\"唐牧师分享-主祷文01讲\",\"sort_title\":\"第1集\",\"mp3\":true,\"mp4\":true,\"doc\":false,\"txt\":false},{\"urlid\":37601,\"title\":\"唐牧师分享-主祷文02讲\",\"sort_title\":\"第2集\",\"mp3\":true,\"mp4\":true,\"doc\":false,\"txt\":false},{\"urlid\":37602,\"title\":\"唐牧师分享-主祷文03讲\",\"sort_title\":\"第3集\",\"mp3\":true,\"mp4\":true,\"doc\":false,\"txt\":false},{\"urlid\":37603,\"title\":\"唐牧师分享-主祷文04讲\",\"sort_title\":\"第4集\",\"mp3\":true,\"mp4\":true,\"doc\":false,\"txt\":false},{\"urlid\":37604,\"title\":\"唐牧师分享-主祷文05讲\",\"sort_title\":\"第5集\",\"mp3\":true,\"mp4\":true,\"doc\":false,\"txt\":false},{\"urlid\":37605,\"title\":\"唐牧师分享-主祷文06讲\",\"sort_title\":\"第6集\",\"mp3\":true,\"mp4\":true,\"doc\":false,\"txt\":false},{\"urlid\":37606,\"title\":\"唐牧师分享-主祷文07讲\",\"sort_title\":\"第7集\",\"mp3\":true,\"mp4\":true,\"doc\":false,\"txt\":false},{\"urlid\":38083,\"title\":\"唐牧师分享-主祷文08讲\",\"sort_title\":\"第8集\",\"mp3\":true,\"mp4\":true,\"doc\":false,\"txt\":false},{\"urlid\":38084,\"title\":\"唐牧师分享-主祷文09讲\",\"sort_title\":\"第9集\",\"mp3\":true,\"mp4\":true,\"doc\":false,\"txt\":false},{\"urlid\":38163,\"title\":\"唐牧师分享-主祷文10讲\",\"sort_title\":\"第10集\",\"mp3\":true,\"mp4\":true,\"doc\":false,\"txt\":false},{\"urlid\":38164,\"title\":\"唐牧师分享-主祷文11讲\",\"sort_title\":\"第11集\",\"mp3\":true,\"mp4\":true,\"doc\":false,\"txt\":false},{\"urlid\":38165,\"title\":\"唐牧师分享-主祷文12讲\",\"sort_title\":\"第12集\",\"mp3\":true,\"mp4\":true,\"doc\":false,\"txt\":false},{\"urlid\":38508,\"title\":\"唐牧师分享-主祷文13讲\",\"sort_title\":\"第13集\",\"mp3\":true,\"mp4\":true,\"doc\":false,\"txt\":false},{\"urlid\":38509,\"title\":\"唐牧师分享-主祷文14讲\",\"sort_title\":\"第14集\",\"mp3\":true,\"mp4\":true,\"doc\":false,\"txt\":false},{\"urlid\":42123,\"title\":\"唐牧师分享-主祷文15讲\",\"sort_title\":\"第15集\",\"mp3\":true,\"mp4\":true,\"doc\":false,\"txt\":false},{\"urlid\":42124,\"title\":\"唐牧师分享-主祷文16讲\",\"sort_title\":\"第16集\",\"mp3\":true,\"mp4\":true,\"doc\":false,\"txt\":false},{\"urlid\":42125,\"title\":\"唐牧师分享-主祷文17讲\",\"sort_title\":\"第17集\",\"mp3\":true,\"mp4\":true,\"doc\":false,\"txt\":false},{\"urlid\":42126,\"title\":\"唐牧师分享-主祷文18讲\",\"sort_title\":\"第18集\",\"mp3\":true,\"mp4\":true,\"doc\":false,\"txt\":false},{\"urlid\":42127,\"title\":\"唐牧师分享-主祷文19讲\",\"sort_title\":\"第19集\",\"mp3\":true,\"mp4\":true,\"doc\":false,\"txt\":false}],\"zs\":{\"open\":0}}";

		try {
			JSONObject.parseObject(result, Three_UrlBean.class);
			System.out.println("1");
		} catch (Exception e) {
			System.out.println("0");
		}
	}

	void getUrls(String path, int movid) throws Exception {
		Map<String, String> param = new HashMap<>();
		String url = "https://data-api.cnfuyin.com/api/movie/index?app=fytv&device=mobile&version=1.1.0&movid=" + movid + "&token=2fc55a83f4f7a7e8e32a3dddfeaf68d1";
		url += movid;
		String retrunString = doPostXWwwFormUrlencoded(url, param, "牧人系列");
		List<Three_UrlBean.Url> urls = null;
		try {
			urls = JSONObject.parseObject(retrunString, Three_UrlBean.class).getUrls();
		} catch (Exception e) {
			try {
				System.out.println("第一次转型失败:" + retrunString + "\n");

				retrunString = retrunString.substring(retrunString.indexOf("\"urls\":") + 7, retrunString.length() - 1);
				retrunString = retrunString.substring(0, retrunString.indexOf("]") + 1);
				urls = JSONArray.parseArray(retrunString, Three_UrlBean.Url.class);
			} catch (Exception e1) {
				System.out.println("第二次转型失败:" + retrunString + "\n");
				System.out.println();
			}
		}
		if (!ObjectUtils.isEmpty(urls)) {
			for (int i = 0; i < urls.size(); i++) {
				Three_UrlBean.Url urlBean = urls.get(i);
				try {
					getDetail(i, path, movid, urlBean.getUrlid());
				} catch (Exception e) {
					System.out.println("------------失败------------" + e.getMessage());
					System.out.println("------------失败------------" + urlBean.toString());
				}
			}
		} else {
			System.out.println("------------失败------------" + urls.toString());
		}
	}

	public static String String2Json(String s) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char c = s.toCharArray()[i];
			switch (c) {
				case '\"':
					sb.append("\\\"");
					break;
				case '\\':
					sb.append("\\\\");
					break;
				case '/':
					sb.append("\\/");
					break;
				case '\b':
					sb.append("\\b");
					break;
				case '\f':
					sb.append("\\f");
					break;
				case '\n':
					sb.append("\\n");
					break;
				case '\r':
					sb.append("\\r");
					break;
				case '\t':
					sb.append("\\t");
					break;
				default:
					if ((c >= 0 && c <= 31) || c == 127)// 在ASCⅡ码中，第0～31号及第127号(共33个)是控制字符或通讯专用字符
					{

					} else {
						sb.append(c);
					}
					break;
			}
		}
		return sb.toString();
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
				sleep();
				/*ClientDownloadThread downloadThread = new ClientDownloadThread("fuyin_TV", path, i, four_detailBean.getTitle(),
						".mp4", four_detailBean.getUrl_2());
				pool.submit(downloadThread);*/
			} catch (Exception e) {
				System.out.println("e.getMessage()\n" + e.getMessage());
			}
			//download mp3
			try {
				/*downDetail(i + 1, path + "/mp3", four_detailBean.getTitle(),
						four_detailBean.getUrl_5(), ".mp3");*/
				sleep();
				/*ClientDownloadThread downloadThread = new ClientDownloadThread("fuyin_TV", path, i, four_detailBean.getTitle(),
						".mp3", four_detailBean.getUrl_5());
				pool.submit(downloadThread);*/
			} catch (Exception e) {
				System.out.println(four_detailBean.getTitle() + "------------下载失败------------" + four_detailBean.getUrl_5() + "\n\n------------下载失败------------" + e.getMessage());
			}
		} else {
			System.out.println("------------失败------------" + four_detailBean.toString());
		}
	}


	void sleep() throws InterruptedException {
		if (runThreadNum > 1) {
			Thread.sleep(2 * 1000);
			for (int j = 0; j < 1000; j++) {
				Thread.sleep(10 * 1000);
				if (runThreadNum < 5) {
					break;
				}
			}
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
