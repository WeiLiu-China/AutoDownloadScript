package com.爬虫.fuyin_Yingyuan;


import com.alibaba.fastjson.JSONObject;
import com.爬虫.ClientDownloadThread;
import com.爬虫.CommonThread;
import com.爬虫.DownloadThread;
import com.爬虫.fuyin_Yingyuan.bean.Four_DetailBean;
import com.爬虫.util.FileUtil;
import org.junit.Test;
import org.springframework.util.ObjectUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.爬虫.Constant.threadNum;
import static com.爬虫.fuyinTV.FuYinTV.sleep;
import static com.爬虫.util.HttpUtil.doPost;
import static com.爬虫.util.HttpUtil.sendGet;

public class fuyin_yingyuan {

	public static void main(String args[]) {
		String parentHtmlString = "<a href=\"../mlist/16_1.html\"><span class=\"t_dh\">初信1</span></a>bkjbi<a href=\"." +
				"./mlist/16_1.html\"><span class=\"t_dh\">初信12</span></a>kjnkjn<a href=\"../mlist/16_1.html\"><span " +
				"class=\"t_dh\">初信123</span></a>nkj<a href=\"../mlist/16_1.html\"><span " +
				"class=\"t_dh\">初信1234</span></a>awdawdawfafa";
		String key = "mlist";
		List<Integer> indexList = new ArrayList<>();
		for (int i = -1; i <= parentHtmlString.lastIndexOf(key); ++i) {
			i = parentHtmlString.indexOf(key, i);
			indexList.add(i);
		}

		for (int i = 0; i < indexList.size(); i++) {
			String s = parentHtmlString + "";
			s = s.substring(indexList.get(i), indexList.get(i) + 53);
			String htmlUrl = s + "";
			htmlUrl = htmlUrl.substring(0, htmlUrl.indexOf("html") + 4);
			String name = s + "";
			name = name.substring(name.indexOf("t_dh") + 6, name.indexOf("</span>"));
		}

	}

	void getListHtmlUrlsAndGoOn(String path, String parentHtmlString) throws UnsupportedEncodingException {
		String key = "mlist";
		List<Integer> indexList = new ArrayList<>();
		for (int i = -1; i <= parentHtmlString.lastIndexOf(key); ++i) {
			i = parentHtmlString.indexOf(key, i);
			indexList.add(i);
		}

		List<String> list = new ArrayList<>();
		for (int i = 0; i < indexList.size(); i++) {
			String s = parentHtmlString + "";
			s = s.substring(indexList.get(i), indexList.get(i) + 53);
			String htmlUrl = s + "";
			htmlUrl = htmlUrl.substring(0, htmlUrl.indexOf("html") + 4);
			if (list.contains(htmlUrl)) {
				break;
			}
			list.add(htmlUrl);
			String name = s + "";
			name = name.substring(name.indexOf("t_dh") + 6, name.indexOf("</span>"));

			String url = "/" + htmlUrl;
			path += "/" + name;
			System.out.println("firstHtml \n" + url + "\n");

			secondHtml(path, url);
		}


	}

	void secondHtml(String path, String url) throws UnsupportedEncodingException {
		String originPath = path + "";
		String originUrl = url + "";
		String parentHtmlString = sendGet(host + url, null);
		String key = "<a target=_blank href=../html/";
		List<Integer> indexList = new ArrayList<>();
		for (int i = -1; i <= parentHtmlString.lastIndexOf(key); ++i) {
			i = parentHtmlString.indexOf(key, i);
			indexList.add(i);
		}

		List<String> list = new ArrayList<>();
		for (int i = 0; i < indexList.size(); i++) {
			String s = parentHtmlString + "";
			/*System.out.println("secondHtmlss--------\n" + s);
			System.out.println("indexList.get(i)--------\n" + indexList.get(i));*/
			if (indexList.get(i) == -1) {
				continue;
			}
			s = s.substring(indexList.get(i) + 24, indexList.get(i) + 34 + 30);
			if (s.contains("img")) {
				continue;
			}
			String htmlUrl = s + "";
			List<Integer> htmlIndexList = new ArrayList<>();
			for (int j = -1; j <= htmlUrl.lastIndexOf("html"); ++j) {
				j = htmlUrl.indexOf("html", j);
				htmlIndexList.add(j);
			}

			htmlUrl = htmlUrl.substring(0, htmlIndexList.get(htmlIndexList.size() - 1) + 4);
			if (list.contains(htmlUrl)) {
				break;
			}
			list.add(htmlUrl);
			String name = s + "";
			name = name.substring(name.indexOf(".html>") + 6, name.indexOf("</a>"));

			url = htmlUrl;
			String nextPath = originPath + "/" + name;
			System.out.println("secondHtml \n" + url + "\n");
			threeDetailHtml(nextPath, url);
		}

		if (indexList.get(0) != -1) {

			//判断是否有下一页
			String isExistNextPage = parentHtmlString + "";
			isExistNextPage = isExistNextPage.substring(isExistNextPage.indexOf("下一页") + 4,
					isExistNextPage.indexOf("下一页") + 15);
			if (isExistNextPage.contains("下一页")) {
				String nextPageUrl = parentHtmlString + "";
				nextPageUrl = nextPageUrl.substring(nextPageUrl.indexOf("下一页") - 24, nextPageUrl.indexOf("下一页"));

				if (nextPageUrl.contains("7_10.html")) {
					System.out.println("");
				}
				nextPageUrl = nextPageUrl.substring(nextPageUrl.indexOf(".html") - 6, nextPageUrl.indexOf(".html") + 5);
				nextPageUrl = nextPageUrl.substring(nextPageUrl.indexOf("=") + 1, nextPageUrl.indexOf(".html") + 5);
				nextPageUrl = nextPageUrl.substring(nextPageUrl.indexOf(".html") - 4, nextPageUrl.indexOf(".html") + 5);

				String substring = originUrl.substring(0, originUrl.indexOf(".html") - 4);
				String nextUrl = substring + nextPageUrl;


				secondHtml(originPath, nextUrl);

			}
		}
	}

	void threeDetailHtml(String path, String url) throws UnsupportedEncodingException {
		int num = 1;
		System.out.println("-------------\nnum:" + num + "\n");

		String parentHtmlString = sendGet(host + url, null);
		String key = ".mp4";
		List<Integer> indexList = new ArrayList<>();
		for (int i = -1; i <= parentHtmlString.lastIndexOf(key); ++i) {
			i = parentHtmlString.indexOf(key, i);
			indexList.add(i);
		}
		if (indexList.size() > 0) {
			List<String> list = new ArrayList<>();
			for (int i = 0; i < indexList.size(); i++) {
				String s = parentHtmlString + "";
				if (indexList.get(i) < 80) {
					System.out.println("");
				}
				if (indexList.get(i) >= 80) {

					s = s.substring(indexList.get(i) - 80, indexList.get(i) + 4);
					String videoUrl = s + "";
					if (!videoUrl.contains("http")) {
						continue;
					}
					videoUrl = videoUrl.substring(videoUrl.indexOf("http"),
							videoUrl.indexOf(".mp4") + 4);
					if (list.contains(videoUrl)) {
						break;
					}
					list.add(videoUrl);
					String name = videoUrl + "";
					List<Integer> nameIndexList = new ArrayList<>();
					for (int j = -1; j <= name.lastIndexOf("/"); ++j) {
						j = name.indexOf("/", j);
						nameIndexList.add(j);
					}
					name = name.substring(nameIndexList.get(nameIndexList.size() - 1) + 1, name.indexOf(".mp4"));
					try {
						String type = videoUrl + "";
						type = type.substring(videoUrl.length() - 4, videoUrl.length());
						System.out.println("-------------\nvideoUrl:" + videoUrl + "\n");
						sleep();
						DownloadThread downloadThread = new DownloadThread("福音影视", path, i + 1,
								name, type, videoUrl);
						pool.submit(downloadThread);
						//FileUtil.download(path, "/" + name + type, videoUrl);
						//稳定，速度极慢，日志多
						// FileUtil.clientDownload(path, "/" + name + type, videoUrl);
					} catch (Exception e) {
						System.out.println("------------失败原因------------" + e.getMessage());
						System.out.println("------------失败------------" + videoUrl);
					}
				}
			}


		}

	}

	private final static ExecutorService pool = Executors.newFixedThreadPool(threadNum);

	@Test
	public void download() throws Exception {
		CommonThread commonThread = new CommonThread();
		commonThread.start();

		String path = "D:\\爬虫\\福音影院";      //保存目录
		getAllList(path);
	}

	String host = "http://ww.fyyy.tv";

	//主页获取所有类型
	void getAllList(String path) throws Exception {
		String url = "/index/index.htm";
		String htmlString = sendGet(host + url, null);
		getListHtmlUrlsAndGoOn(path, htmlString);
	}

}
