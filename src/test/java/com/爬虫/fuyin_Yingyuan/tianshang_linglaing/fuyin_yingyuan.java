package com.爬虫.fuyin_Yingyuan.tianshang_linglaing;


import com.alibaba.fastjson.JSONObject;
import com.爬虫.CommonThread;
import com.爬虫.fuyin_Yingyuan.tianshang_linglaing.bean.Four_DetailBean;
import com.爬虫.fuyin_Yingyuan.tianshang_linglaing.bean.Second_PeopleListBean;
import com.爬虫.fuyin_Yingyuan.tianshang_linglaing.bean.Three_XiLieBean;
import com.爬虫.util.FileUtil;
import org.junit.Test;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.爬虫.util.HttpUtil.*;

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

	void getListHtmlUrlsAndGoOn(String path, String parentHtmlString) {
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
			secondHtml(path, url);
		}


	}

	void secondHtml(String path, String url) {
		String originUrl = url;
		try {
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
				s = s.substring(indexList.get(i) + 24, indexList.get(i) + 34 + 30);
				if (s.contains("img")) {
					continue;
				}
				String htmlUrl = s + "";
				htmlUrl = htmlUrl.substring(0, htmlUrl.indexOf("html") + 14);
				if (list.contains(htmlUrl)) {
					break;
				}
				list.add(htmlUrl);
				String name = s + "";
				name = name.substring(name.indexOf(".html>") + 6, name.indexOf("</a>"));

				url = htmlUrl;
				path += "/" + name;
				threeDetailHtml(path, url);
			}
			//判断是否有下一页
			String isExistNextPage = parentHtmlString + "";
			isExistNextPage = isExistNextPage.substring(isExistNextPage.indexOf("下一页") + 4,
					isExistNextPage.indexOf("下一页") + 15);
			if (isExistNextPage.contains("下一页")) {
				String nextPageUrl = parentHtmlString + "";
				nextPageUrl = nextPageUrl.substring(nextPageUrl.indexOf("下一页") - 24, nextPageUrl.indexOf("下一页"));
				nextPageUrl = nextPageUrl.substring(nextPageUrl.indexOf(".html") - 4, nextPageUrl.indexOf(".html") + 5);

				String substring = originUrl.substring(0, originUrl.indexOf(".html") - 4);
				String nextUrl = substring + nextPageUrl;
				secondHtml(path, nextUrl);
			}
		} catch (Exception e) {
			System.out.println("------------------------错误:\n" + e.getMessage() + "\n");

		}

	}

	void threeDetailHtml(String path, String url) {
		String parentHtmlString = sendGet(host + url, null);

		String key = ".mp4";
		List<Integer> indexList = new ArrayList<>();
		for (int i = -1; i <= parentHtmlString.lastIndexOf(key); ++i) {
			i = parentHtmlString.indexOf(key, i);
			indexList.add(i);
		}

		List<String> list = new ArrayList<>();
		for (int i = 0; i < indexList.size(); i++) {
			String s = parentHtmlString + "";
			s = s.substring(indexList.get(i) - 44, indexList.get(i) + 4);

			String videoUrl = s + "";
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
				FileUtil.download(path, "/" + name + type, videoUrl);
			} catch (Exception e) {
				System.out.println("------------失败------------" + videoUrl);
				System.out.println("------------失败原因------------" + e.getMessage());

			}
		}
	}

	@Test
	public void download() throws Exception {
		CommonThread commonThread = new CommonThread();
		commonThread.start();

		String path = "E:\\爬虫\\福音影院";      //保存目录
		getAllList(path);
	}

	String host = "http://ww.fyyy.tv";

	//主页获取所有类型
	void getAllList(String path) throws Exception {
		String url = "/index/index.htm";
		String htmlString = sendGet(host + url, null);
		getListHtmlUrlsAndGoOn(path, htmlString);
	}


	void getDetail(String path, String id, String page, Integer j) throws Exception {
		Map<String, String> param = new HashMap<>();
		param.put("id", id);
		param.put("page", page);


		String url = "/index/Sermon/details";
		String retrunString = sendGet(host + url, null);
		Four_DetailBean four_detailBean = JSONObject.parseObject(retrunString, Four_DetailBean.class);
		if (!ObjectUtils.isEmpty(four_detailBean) && four_detailBean.getStatus().equals("1") &&
				!ObjectUtils.isEmpty(four_detailBean.getList())) {
			for (int i = 0; i < four_detailBean.getList().size(); i++) {
				Four_DetailBean.Detail detail = four_detailBean.getList().get(i);
				try {
					//downDetail(i + 1, path, detail.getTitle(), detail.getVideo_url());
					int name_num = i + 1 + j;
					FileUtil.download(path, "/" + name_num + "_" + detail.getTitle() + ".mp3", detail.getVideo_url());

				} catch (Exception e) {
					System.out.println("------------失败------------" + detail.getVideo_url());
					System.out.println("------------失败原因------------" + e.getMessage());

				}
			}
		} else {
			System.out.println("------------失败------------" + four_detailBean.toString());
		}
		//继续往下
		if (!ObjectUtils.isEmpty(four_detailBean.getList()) && four_detailBean.getList().size() >= 10) {
			int nextPage = Integer.parseInt(page) + 1;
			getDetail(path, id, "" + nextPage, four_detailBean.getList().size() + j);
		}

	}


	String sendPost(String host, String url) throws Exception {
		String rt = null;
		try {
			rt = doPost(host + url, null);
		} catch (Exception e) {
			System.out.println("-------------------------\ndoPostXWwwFormUrlencoded rt为null:\t" + host + url + "\n");
			System.out.println("-----------错误信息：" + e.getMessage());
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
