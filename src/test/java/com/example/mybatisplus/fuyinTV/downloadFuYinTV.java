package com.example.mybatisplus.fuyinTV;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.mybatisplus.CommonThread;
import com.example.mybatisplus.DownloadThread;
import com.example.mybatisplus.fuyinTV.bean.*;
import com.example.mybatisplus.fuyinTV.bean.最近更新.Second_最近更新_XiLieBean;
import com.example.mybatisplus.fuyinTV.bean.视频茶经.Second_视频茶经_XiLieBean;
import com.example.util.FileUtil;
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

import static com.example.mybatisplus.Constant.threadNum;
import static com.example.util.FileUtil.download;
import static com.example.util.HttpClientUtil.downLoadByHttpClient;
import static com.example.util.HttpUtil.doPostXWwwFormUrlencoded;
import static com.example.util.StringUtil.subString;

public class downloadFuYinTV {

	public static final String 福音_TV = "福音TV";
	public static final String PAGESIZE = "10000";

	@Test
	public void test4() {
		String s = "_jsonpku8mk42huei([{\"id\":301,\"category\":\"刘东崐\",\"parentid\":109,\"sort\":2,\"sort2\":1,\"moviecount\":13,\"nodes\":0,\"thumb\":\"https:\\/\\/img.tv.cnfuyin.com\\/upload\\/image\\/mzzl\\/301.jpg\"},{\"id\":96,\"category\":\"唐崇荣\",\"parentid\":109,\"sort\":3,\"sort2\":0,\"moviecount\":215,\"nodes\":10,\"thumb\":\"https:\\/\\/img.tv.cnfuyin.com\\/upload\\/image\\/mzzl\\/96.jpg\"},{\"id\":191,\"category\":\"江守道\",\"parentid\":109,\"sort\":4,\"sort2\":4,\"moviecount\":24,\"nodes\":0,\"thumb\":\"https:\\/\\/img.tv.cnfuyin.com\\/upload\\/image\\/mzzl\\/191.jpg\"},{\"id\":297,\"category\":\"汪川生\",\"parentid\":109,\"sort\":5,\"sort2\":1,\"moviecount\":26,\"nodes\":0,\"thumb\":\"https:\\/\\/img.tv.cnfuyin.com\\/upload\\/image\\/mzzl\\/297.jpg\"},{\"id\":125,\"category\":\"林慈信\",\"parentid\":109,\"sort\":6,\"sort2\":0,\"moviecount\":14,\"nodes\":0,\"thumb\":\"https:\\/\\/img.tv.cnfuyin.com\\/upload\\/image\\/mzzl\\/125.jpg\"},{\"id\":122,\"category\":\"康来昌\",\"parentid\":109,\"sort\":7,\"sort2\":0,\"moviecount\":20,\"nodes\":0,\"thumb\":\"https:\\/\\/img.tv.cnfuyin.com\\/upload\\/image\\/mzzl\\/122.jpg\"},{\"id\":101,\"category\":\"陈希曾\",\"parentid\":109,\"sort\":8,\"sort2\":0,\"moviecount\":72,\"nodes\":0,\"thumb\":\"https:\\/\\/img.tv.cnfuyin.com\\/upload\\/image\\/mzzl\\/101.jpg\"},{\"id\":99,\"category\":\"刘志雄\",\"parentid\":109,\"sort\":9,\"sort2\":0,\"moviecount\":119,\"nodes\":1,\"thumb\":\"https:\\/\\/img.tv.cnfuyin.com\\/upload\\/image\\/mzzl\\/99.jpg\"},{\"id\":97,\"category\":\"吴勇\",\"parentid\":109,\"sort\":10,\"sort2\":0,\"moviecount\":31,\"nodes\":1,\"thumb\":\"https:\\/\\/img.tv.cnfuyin.com\\/upload\\/image\\/mzzl\\/97.jpg\"},{\"id\":98,\"category\":\"于宏洁\",\"parentid\":109,\"sort\":11,\"sort2\":0,\"moviecount\":82,\"nodes\":0,\"thumb\":\"https:\\/\\/img.tv.cnfuyin.com\\/upload\\/image\\/mzzl\\/98.jpg\"},{\"id\":177,\"category\":\"程蒙恩专辑\",\"parentid\":109,\"sort\":12,\"sort2\":1,\"moviecount\":90,\"nodes\":2,\"thumb\":\"https:\\/\\/img.tv.cnfuyin.com\\/upload\\/image\\/mzzl\\/177.jpg\"},{\"id\":189,\"category\":\"寇世远\",\"parentid\":109,\"sort\":13,\"sort2\":2,\"moviecount\":33,\"nodes\":1,\"thumb\":\"https:\\/\\/img.tv.cnfuyin.com\\/upload\\/image\\/mzzl\\/189.jpg\"},{\"id\":92,\"category\":\"曾霖芳\",\"parentid\":109,\"sort\":14,\"sort2\":0,\"moviecount\":7,\"nodes\":0,\"thumb\":\"https:\\/\\/img.tv.cnfuyin.com\\/upload\\/image\\/mzzl\\/92.jpg\"},{\"id\":193,\"category\":\"冯秉诚\",\"parentid\":109,\"sort\":15,\"sort2\":6,\"moviecount\":14,\"nodes\":0,\"thumb\":\"https:\\/\\/img.tv.cnfuyin.com\\/upload\\/image\\/mzzl\\/193.jpg\"},{\"id\":204,\"category\":\"王明道\",\"parentid\":109,\"sort\":16,\"sort2\":2,\"moviecount\":2,\"nodes\":0,\"thumb\":\"https:\\/\\/img.tv.cnfuyin.com\\/upload\\/image\\/mzzl\\/204.jpg\"},{\"id\":190,\"category\":\"吉牧师\",\"parentid\":109,\"sort\":17,\"sort2\":3,\"moviecount\":9,\"nodes\":0,\"thumb\":\"https:\\/\\/img.tv.cnfuyin.com\\/upload\\/image\\/mzzl\\/190.jpg\"},{\"id\":126,\"category\":\"徐兴言\",\"parentid\":109,\"sort\":18,\"sort2\":0,\"moviecount\":12,\"nodes\":0,\"thumb\":\"https:\\/\\/img.tv.cnfuyin.com\\/upload\\/image\\/mzzl\\/126.jpg\"},{\"id\":100,\"category\":\"寇绍恩\",\"parentid\":109,\"sort\":19,\"sort2\":0,\"moviecount\":20,\"nodes\":0,\"thumb\":\"https:\\/\\/img.tv.cnfuyin.com\\/upload\\/image\\/mzzl\\/100.jpg\"},{\"id\":201,\"category\":\"彭动平\",\"parentid\":109,\"sort\":20,\"sort2\":2,\"moviecount\":7,\"nodes\":0,\"thumb\":\"https:\\/\\/img.tv.cnfuyin.com\\/upload\\/image\\/mzzl\\/201.jpg\"},{\"id\":218,\"category\":\"吴献章\",\"parentid\":109,\"sort\":21,\"sort2\":1,\"moviecount\":13,\"nodes\":0,\"thumb\":\"https:\\/\\/img.tv.cnfuyin.com\\/upload\\/image\\/mzzl\\/218.jpg\"},{\"id\":223,\"category\":\"庄祖鲲\",\"parentid\":109,\"sort\":22,\"sort2\":1,\"moviecount\":15,\"nodes\":0,\"thumb\":\"https:\\/\\/img.tv.cnfuyin.com\\/upload\\/image\\/mzzl\\/223.jpg\"},{\"id\":217,\"category\":\"王永信\",\"parentid\":109,\"sort\":23,\"sort2\":3,\"moviecount\":1,\"nodes\":0,\"thumb\":\"https:\\/\\/img.tv.cnfuyin.com\\/upload\\/image\\/mzzl\\/217.jpg\"},{\"id\":199,\"category\":\"李秀全\",\"parentid\":109,\"sort\":24,\"sort2\":3,\"moviecount\":6,\"nodes\":0,\"thumb\":\"https:\\/\\/img.tv.cnfuyin.com\\/upload\\/image\\/mzzl\\/199.jpg\"},{\"id\":194,\"category\":\"焦源濂\",\"parentid\":109,\"sort\":25,\"sort2\":1,\"moviecount\":3,\"nodes\":0,\"thumb\":\"https:\\/\\/img.tv.cnfuyin.com\\/upload\\/image\\/mzzl\\/194.jpg\"},{\"id\":288,\"category\":\"张路加\",\"parentid\":109,\"sort\":26,\"sort2\":1,\"moviecount\":10,\"nodes\":0,\"thumb\":\"https:\\/\\/img.tv.cnfuyin.com\\/upload\\/image\\/mzzl\\/288.jpg\"},{\"id\":200,\"category\":\"楼建华\",\"parentid\":109,\"sort\":27,\"sort2\":1,\"moviecount\":6,\"nodes\":0,\"thumb\":\"https:\\/\\/img.tv.cnfuyin.com\\/upload\\/image\\/mzzl\\/200.jpg\"},{\"id\":196,\"category\":\"李健\",\"parentid\":109,\"sort\":28,\"sort2\":1,\"moviecount\":18,\"nodes\":0,\"thumb\":\"https:\\/\\/img.tv.cnfuyin.com\\/upload\\/image\\/mzzl\\/196.jpg\"},{\"id\":203,\"category\":\"藤近辉\",\"parentid\":109,\"sort\":29,\"sort2\":1,\"moviecount\":3,\"nodes\":0,\"thumb\":\"https:\\/\\/img.tv.cnfuyin.com\\/upload\\/image\\/mzzl\\/203.jpg\"},{\"id\":202,\"category\":\"沈保罗\",\"parentid\":109,\"sort\":30,\"sort2\":3,\"moviecount\":4,\"nodes\":0,\"thumb\":\"https:\\/\\/img.tv.cnfuyin.com\\/upload\\/image\\/mzzl\\/202.jpg\"},{\"id\":192,\"category\":\"李慕圣\",\"parentid\":109,\"sort\":31,\"sort2\":5,\"moviecount\":6,\"nodes\":0,\"thumb\":\"https:\\/\\/img.tv.cnfuyin.com\\/upload\\/image\\/mzzl\\/192.jpg\"},{\"id\":198,\"category\":\"刘同苏\",\"parentid\":109,\"sort\":32,\"sort2\":2,\"moviecount\":3,\"nodes\":0,\"thumb\":\"https:\\/\\/img.tv.cnfuyin.com\\/upload\\/image\\/mzzl\\/198.jpg\"},{\"id\":219,\"category\":\"杨培兹\",\"parentid\":109,\"sort\":33,\"sort2\":1,\"moviecount\":7,\"nodes\":0,\"thumb\":\"https:\\/\\/img.tv.cnfuyin.com\\/upload\\/image\\/mzzl\\/219.jpg\"},{\"id\":220,\"category\":\"杨心斐\",\"parentid\":109,\"sort\":34,\"sort2\":1,\"moviecount\":2,\"nodes\":0,\"thumb\":\"https:\\/\\/img.tv.cnfuyin.com\\/upload\\/image\\/mzzl\\/220.jpg\"},{\"id\":221,\"category\":\"于力工\",\"parentid\":109,\"sort\":35,\"sort2\":1,\"moviecount\":4,\"nodes\":0,\"thumb\":\"https:\\/\\/img.tv.cnfuyin.com\\/upload\\/image\\/mzzl\\/221.jpg\"},{\"id\":252,\"category\":\"保罗华许\",\"parentid\":109,\"sort\":36,\"sort2\":1,\"moviecount\":4,\"nodes\":0,\"thumb\":\"https:\\/\\/img.tv.cnfuyin.com\\/upload\\/image\\/mzzl\\/252.jpg\"},{\"id\":263,\"category\":\"约翰派博\",\"parentid\":109,\"sort\":37,\"sort2\":1,\"moviecount\":2,\"nodes\":0,\"thumb\":\"https:\\/\\/img.tv.cnfuyin.com\\/upload\\/image\\/mzzl\\/263.jpg\"},{\"id\":264,\"category\":\"约翰麦克阿瑟\",\"parentid\":109,\"sort\":38,\"sort2\":2,\"moviecount\":2,\"nodes\":0,\"thumb\":\"https:\\/\\/img.tv.cnfuyin.com\\/upload\\/image\\/mzzl\\/264.jpg\"},{\"id\":281,\"category\":\"李健安\",\"parentid\":109,\"sort\":39,\"sort2\":3,\"moviecount\":10,\"nodes\":0,\"thumb\":\"https:\\/\\/img.tv.cnfuyin.com\\/upload\\/image\\/mzzl\\/281.jpg\"},{\"id\":280,\"category\":\"曾劭恺\",\"parentid\":109,\"sort\":41,\"sort2\":1,\"moviecount\":10,\"nodes\":0,\"thumb\":\"https:\\/\\/img.tv.cnfuyin.com\\/upload\\/image\\/mzzl\\/280.jpg\"},{\"id\":107,\"category\":\"其他讲员\",\"parentid\":109,\"sort\":43,\"sort2\":0,\"moviecount\":104,\"nodes\":0,\"thumb\":\"https:\\/\\/img.tv.cnfuyin.com\\/upload\\/image\\/mzzl\\/107.jpg\"}]);";
		String json = "[" + subString(s, "([", "])") + "]";
		List<First_PeopleBean> first_peopleBeans = JSONArray.parseArray(json, First_PeopleBean.class);
		System.out.println(first_peopleBeans);
		System.out.println("");
	}

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

		String path = "D:\\爬虫\\";      //保存目录
		path += 福音_TV;

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
