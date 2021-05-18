package com.example.mybatisplus.tianshang_linglaing;


import com.alibaba.fastjson.JSONObject;
import com.example.mybatisplus.CommonThread;
import com.example.mybatisplus.DownloadThread;
import com.example.mybatisplus.tianshang_linglaing.bean.First_AllListBean;
import com.example.mybatisplus.tianshang_linglaing.bean.Four_DetailBean;
import com.example.mybatisplus.tianshang_linglaing.bean.Second_PeopleListBean;
import com.example.mybatisplus.tianshang_linglaing.bean.Three_XiLieBean;
import com.example.util.FileUtil;
import org.junit.Test;
import org.springframework.util.ObjectUtils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.example.mybatisplus.Constant.threadNum;
import static com.example.util.FileUtil.download;
import static com.example.util.HttpUtil.doPostXWwwFormUrlencoded;

public class downloadTianshangLingLiang {

	@Test
	public void test4() {
		String s = "html";
		int index = s.indexOf(".mp3");
		int end = s.indexOf("</a>", index);
		s = s.substring(index, end);
		s = s.substring(s.indexOf(">") + 1);
		System.out.println("");
	}

	String host = "https://www.tingdao.org";

	@Test
	public void downloadMusic() throws Exception {
		CommonThread commonThread = new CommonThread();
		commonThread.start();

		String path = "D:\\爬虫\\天上灵粮";      //保存目录
		getAllList(path);
	}

	//主页获取所有类型
	void getAllList(String path) throws Exception {
		Map<String, String> param = new HashMap<>();
		String url = "/index/Recommend/aggregate";
		param.put("id", "1");
		param.put("page", "1");
		String allList = sendPost(host, url, param, "全部系列");
		First_AllListBean firstAllListBean = JSONObject.parseObject(allList, First_AllListBean.class);
		List<First_AllListBean.Ico> ico = firstAllListBean.getIco();
		if (firstAllListBean.getStatus().equals("1") && !ObjectUtils.isEmpty(ico)) {
			for (int i = 0; i < ico.size(); i++) {
				First_AllListBean.Ico ic = ico.get(i);
				getPeopleList(path + "/" + ic.getLmc(), ic.getId(), "1");
			}
		} else {
			System.out.println("------------error------------" + ico.toString());
		}

	}

	void getPeopleList(String path, String id, String page) throws Exception {
		Map<String, String> param = new HashMap<>();
		param.put("id", id);
		param.put("page", page);
		String url = "/index/Sermon/Sermon";
		String retrunString = sendPost(host, url, param, "牧人列表");
		Second_PeopleListBean secondPeopleListBean = JSONObject.parseObject(retrunString, Second_PeopleListBean.class);
		if (secondPeopleListBean.getStatus().equals("1") && !ObjectUtils.isEmpty(secondPeopleListBean) &&
				!ObjectUtils.isEmpty(secondPeopleListBean.getData())) {
			for (int i = 0; i < secondPeopleListBean.getData().size(); i++) {
				Second_PeopleListBean.User user = secondPeopleListBean.getData().get(i);
				getPeopleXilie(path + "/" + user.getAuthor(), user.getAuthor(), user.getLxid(), "1");
			}
		} else {
			System.out.println("------------error------------" + secondPeopleListBean.toString());
		}
		//继续往下
		if (!ObjectUtils.isEmpty(secondPeopleListBean.getData()) && secondPeopleListBean.getData().size() >= 10) {
			int nextPage = Integer.parseInt(page) + 1;
			getPeopleList(path, id, "" + nextPage);
		}

	}

	void getPeopleXilie(String path, String author, String id, String page) throws Exception {
		Map<String, String> param = new HashMap<>();
		param.put("id", id);
		param.put("classid", id);
		param.put("page", page);
		param.put("author", author);
		param.put("order", "倒序");
		param.put("tag_id", "");
		param.put("time", "");
		param.put("type", "");

		String url = "/index/Sermon/Authoralbum";
		String retrunString = sendPost(host, url, param, "牧人系列");
		Three_XiLieBean threeXiLieBean = JSONObject.parseObject(retrunString, Three_XiLieBean.class);
		if (threeXiLieBean.getStatus().equals("1") && !ObjectUtils.isEmpty(threeXiLieBean) &&
				!ObjectUtils.isEmpty(threeXiLieBean.getList())) {
			for (int i = 0; i < threeXiLieBean.getList().size(); i++) {
				Three_XiLieBean.XiLie xiLie = threeXiLieBean.getList().get(i);
				getDetail(path + "/" + xiLie.getTitle(), xiLie.getZjid(), "1");
			}
		} else {
			System.out.println("------------error------------" + threeXiLieBean.toString());
		}
		//继续往下
		if (!ObjectUtils.isEmpty(threeXiLieBean.getList()) && threeXiLieBean.getList().size() >= 10) {
			int nextPage = Integer.parseInt(page) + 1;
			getPeopleXilie(path, author, id, "" + nextPage);
		}

	}

	//创建一个具有固定线程数的线程池
	private final static ExecutorService pool = Executors.newFixedThreadPool(threadNum);

	void getDetail(String path, String id, String page) throws Exception {
		Map<String, String> param = new HashMap<>();
		param.put("id", id);
		param.put("page", page);


		String url = "/index/Sermon/details";
		String retrunString = sendPost(host, url, param, "牧人系列");
		Four_DetailBean four_detailBean = JSONObject.parseObject(retrunString, Four_DetailBean.class);
		if (four_detailBean.getStatus().equals("1") && !ObjectUtils.isEmpty(four_detailBean) &&
				!ObjectUtils.isEmpty(four_detailBean.getList())) {
			for (int i = 0; i < four_detailBean.getList().size(); i++) {
				Four_DetailBean.Detail detail = four_detailBean.getList().get(i);
				try {
					//downDetail(i + 1, path, detail.getTitle(), detail.getVideo_url());
					DownloadThread downloadThread = new DownloadThread("tianshang_lingliang", path, i, detail.getTitle(),
							".mp3", detail.getVideo_url());
					pool.submit(downloadThread);
				} catch (Exception e) {
					System.out.println("------------失败------------" + detail.getVideo_url());
				}
			}
		} else {
			System.out.println("------------失败------------" + four_detailBean.toString());
		}
		//继续往下
		if (!ObjectUtils.isEmpty(four_detailBean.getList()) && four_detailBean.getList().size() >= 10) {
			int nextPage = Integer.parseInt(page) + 1;
			getDetail(path, id, "" + nextPage);
		}

	}

	void downDetail(int i, String path, String name, String url) throws Exception {
		FileUtil.makeDir(path);
		//获取URL对象
		URL urlConnection = new URL(url);
		//根据URL打开链接
		URLConnection connection = urlConnection.openConnection();
		//从连接处获取输入流对象
		InputStream inputStream = connection.getInputStream();
		download(path + "/" + i + "_" + name + ".mp3", inputStream);
	}


	String sendPost(String host, String url, Map<String, String> param, String type) throws Exception {
		String rt = doPostXWwwFormUrlencoded(host + url, param, type);
		if (ObjectUtils.isEmpty(rt)) {
			throw new RuntimeException(type + "发送失败");
		}
		return rt;
	}

	/**
	 * 截取字符串str中指定字符 strStart、strEnd之间的字符串
	 *
	 * @return
	 */
	public static String subString(String str, String strStart, String strEnd) {

		/* 找出指定的2个字符在 该字符串里面的 位置 */
		int strStartIndex = str.indexOf(strStart);
		int strEndIndex = str.indexOf(strEnd);

		/* index 为负数 即表示该字符串中 没有该字符 */
		if (strStartIndex < 0) {
			return "字符串 :---->" + str + "<---- 中不存在 " + strStart + ", 无法截取目标字符串";
		}
		if (strEndIndex < 0) {
			return "字符串 :---->" + str + "<---- 中不存在 " + strEnd + ", 无法截取目标字符串";
		}
		/* 开始截取 */
		String result = str.substring(strStartIndex, strEndIndex).substring(strStart.length());
		return result;
	}
}
