package com.爬虫.fuyinTV;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.爬虫.ClientDownloadThread;
import com.爬虫.CommonThread;
import com.爬虫.fuyinTV.bean.*;
import com.爬虫.fuyinTV.bean.最近更新.Second_最近更新_XiLieBean;
import com.爬虫.fuyinTV.bean.视频茶经.Second_视频茶经_XiLieBean;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.util.ObjectUtils;

import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.*;

import static com.爬虫.Constant.runThreadNum;
import static com.爬虫.Constant.threadNum;
import static com.爬虫.util.FileUtil.clientDownload;
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


	void printMessage(Long MP3_NUM, Long MP4_NUM, String type, int errorListSize) {
		System.out.println("\n\n类型：" + type);
		System.out.println("mp3累计：" + MP3_NUM);
		System.out.println("mp4累计：" + MP4_NUM);
		Long time = (MP4_NUM * 4 + MP3_NUM * 2);
		System.out.println("单线程需要下载：" + time + "分钟");
		System.out.println("单线程需要下载：" + time / 60 + "小时");
		System.out.println("单线程需要下载：" + time / 60 / 24 + "天 ");
		System.out.println("预计大小：" + (MP3_NUM * 50 + MP4_NUM * 100) / 1000 + "Gb ");
		System.out.println("预计大小：" + (MP3_NUM * 50 + MP4_NUM * 100) / 1000 / 1000 + "Tb " + "\n");
		System.out.println("失败：" + errorList.size() + "条 ");
	}

	Map<String, String> xilieToPath = new HashMap<>();
	List<String> xilieList = new ArrayList<>();
	Map<String, String> chongfu_XilieToPath = new HashMap<>();

	@Test
	public void download() throws Exception {

		Logger.getLogger("org.apache.spark").setLevel(Level.ERROR);
		Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF);
		String path = "G:\\爬虫\\福音TV";      //保存目录

		// 1.牧者专栏
		getPeopleList(path + "/选项/牧者专栏", "https://data-api.cnfuyin.com/api/category/mzzl?app=fytv&device=mobile" +
				"&version=1.1.0&callback=_jsonpku8mk42huei");

		//2.视频茶经
		/*getFirst_视频茶经(path + "/选项/视频茶经", "https://data-api.cnfuyin.com/api/category/bible?app=fytv&device=mobile" +
				"&version=1.1.0&callback=_jsonptiujsnm8xlk");


		//3.最近更新
		second_最近更新_GetXilie(path + "/选项/最近更新", "https://data-api.cnfuyin" +
				".com/api/movie/tops?app=fytv&device=mobile&version=1.1.0&callback=_jsonpep5vass79w");

		//4.推荐视频
		second_GetXilie(path + "/选项/推荐视频", "https://data-api.cnfuyin.com/api/movie/lists?app=fytv&device=mobile" +
				"&version");*/

		//5.主日信息
		/*second_GetXilie(path + "/选项/主日信息", "https://data-api.cnfuyin.com/api/movie/lists?app=fytv&device=mobile" +
				"&version=1.1.0&catid=205&page=1&pagesize=10000&callback=_jsonps9lj1bykkql");*/
		//6.主页其他内容
		/*getFirst_IndexCategory(path + "/主页其他内容", "https://data-api.cnfuyin.com/api/misc/indexCategory?app=fytv&device" + "=mobile&version=1.1.0&callback=_jsonptdwtzvce87");*/

		System.out.println("\n\nxilieList.size：" + xilieList);
		System.out.println("\n\nchongfu_XilieToPath Map：" + chongfu_XilieToPath);

		printMessage(MP3_NUM, MP4_NUM, ":累计", errorList.size());

		if (!ObjectUtils.isEmpty(errorList) && errorList.size() > 0) {
			for (int i = 0; i < errorList.size(); i++) {
				ErrorBean errorBean = errorList.get(i);
				System.out.println("错误原因：" + errorBean.getErrorMessage());
			}
			for (int i = 0; i < errorList.size(); i++) {
				ErrorBean errorBean = errorList.get(i);
				clientDownload(errorBean.getPath(), errorBean.getName(), errorBean.getUrl());
			}
		}
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
		//TODO 去重，避免重复下载
		Map<String, String> param = new HashMap<>();
		String returnString = doPostXWwwFormUrlencoded(url, param, "系列");
		Second_XiLieBean threeXiLieBean = null;
		try {
			threeXiLieBean = JSONObject.parseObject(getJsonString(returnString), Second_XiLieBean.class);

		} catch (Exception e) {
			try {
				threeXiLieBean = JSONObject.parseObject(returnString, Second_XiLieBean.class);
			} catch (Exception e1) {
				System.out.println("失败-------------：\n" + e1.getMessage());
			}
		}
		if (!ObjectUtils.isEmpty(threeXiLieBean) && !ObjectUtils.isEmpty(threeXiLieBean.getData())
				&& !ObjectUtils.isEmpty(threeXiLieBean.getData().size() > 0)) {
			for (int i = 0; i < threeXiLieBean.getData().size(); i++) {
				Second_XiLieBean.XiLie xiLie = threeXiLieBean.getData().get(i);
				getUrls(path + "/" + xiLie.getTitle(), xiLie.getMovid());
			}
		} else {
			System.out.println("------------error------------" + threeXiLieBean);
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

	static Long MP3_NUM = 0L;
	static Long MP4_NUM = 0L;
	static List<ErrorBean> errorList = new ArrayList();

	void getUrls(String path, int movid) throws Exception {
		Map<String, String> param = new HashMap<>();
		String url = "https://data-api.cnfuyin.com/api/movie/index?app=fytv&device=mobile&version=1.1.0&movid=" + movid + "&token=2fc55a83f4f7a7e8e32a3dddfeaf68d1";
		url += movid;
		try {
			doPostXWwwFormUrlencoded(url, param, "牧人系列");
		} catch (Exception e) {
			System.out.println();

		}
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
			System.out.println("------------失败------------" + urls);
		}
	}

	//创建一个具有固定线程数的线程池
	private final static ExecutorService pool = Executors.newFixedThreadPool(threadNum);
	BlockingQueue<Runnable> bq = new ArrayBlockingQueue<Runnable>(1000000);
	ThreadPoolExecutor tpe = new ThreadPoolExecutor(6, 6, 50, TimeUnit.MILLISECONDS, bq);

	void getDetail(int i, String path, int movid, int urlid) throws Exception {
		Map<String, String> param = new HashMap<>();
		String url =
				"https://data-api.cnfuyin.com/api/url/index?app=fytv&device=mobile&version=1.1.0&" + "urlid=" + urlid + "&movid=" + movid;
		String retrunString = doPostXWwwFormUrlencoded(url, param, "牧人系列");
		Four_DetailBean four_detailBean = JSONObject.parseObject(retrunString, Four_DetailBean.class);
		if (!ObjectUtils.isEmpty(four_detailBean) &&
				!ObjectUtils.isEmpty(four_detailBean.getServer())) {
			try {

				MP4_NUM++;
				//download mp4

				sleep();
				if (!ObjectUtils.isEmpty(four_detailBean.getUrl_2())) {
					ClientDownloadThread downloadThread = new ClientDownloadThread("fuyin_TV", path + "/mp4", i + 1,
							four_detailBean.getTitle(),
							".mp4", four_detailBean.getUrl_2());
					tpe.execute(downloadThread);
				}
			} catch (Exception e) {
				ErrorBean errorBean = new ErrorBean("fuyin_TV", path + "/mp4",
						"/" + Integer.parseInt(String.valueOf(i + 1)) + "_" + four_detailBean.getTitle() +
								".mp4", four_detailBean.getUrl_2(), e.getMessage());
				errorList.add(errorBean);
				System.out.println("e.getMessage()\n" + e.getMessage());
			}
			//download mp3
			try {
				MP3_NUM++;

				sleep();
				if (!ObjectUtils.isEmpty(four_detailBean.getUrl_5())) {
					ClientDownloadThread downloadThread = new ClientDownloadThread("fuyin_TV", path + "/mp3", i + 1,
							four_detailBean.getTitle(),
							".mp3", four_detailBean.getUrl_5());
					tpe.execute(downloadThread);
				}
			} catch (Exception e) {
				ErrorBean errorBean = new ErrorBean("fuyin_TV", path + "/mp3", "/" + Integer.parseInt(String.valueOf(i + 1)) + "_" + four_detailBean.getTitle() + ".mp3", four_detailBean.getUrl_5(), e.getMessage());
				errorList.add(errorBean);
				System.out.println(four_detailBean.getTitle() + "------------下载失败------------" + four_detailBean.getUrl_5() + "\n\n------------下载失败------------" + e.getMessage());
			}
		} else {
			System.out.println("------------失败------------" + four_detailBean.toString());
		}
	}


	public static void sleep() throws InterruptedException {
		double v = new Random(1).nextDouble();
		DecimalFormat df = new DecimalFormat("######0.00");
		long v2 = (long) (Double.parseDouble(df.format(v)) * 200);
		Thread.sleep(v2);
		if (runThreadNum > 1) {
			for (int j = 0; j < 1000; j++) {
				if (runThreadNum < 5) {
					break;
				}
				Thread.sleep(10 * 1000);
			}
		}
	}


}
