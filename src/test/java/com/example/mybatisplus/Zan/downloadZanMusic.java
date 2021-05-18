package com.example.mybatisplus.Zan;


import com.example.Bean.Bean;
import com.example.pmmc.GroupBy;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.springframework.util.ObjectUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class downloadZanMusic {
	private static String nb = "1";

	@Test
	public void download() throws IOException {
		//https://play.zanmeishi.com/song/p/
		//https://www.zanmeishi.com/song/20123.html
		String photoRealUrl = "https://play.zanmeishi.com/song/p/";//   219318/1.jpg";   //文件URL地址
		String htmlUrl = "https://www.zanmeishi.com/song/";

		String filemusicpath = "F:\\爬虫\\赞\\music";      //保存目录
		String fileHtmlPath = "F:\\爬虫\\赞\\html";      //保存目录
		int number = 28000;
		for (int j = 281; j < 20100; j++) {
			for (int i = 1; i < 101; i++) {
				try {
					number++;
					try {
						down(j, i, photoRealUrl, htmlUrl, filemusicpath, fileHtmlPath, number);
					} catch (Exception e) {
						System.out.println("\n第" + number + "首第一次下载失败");
						down(j, i, photoRealUrl, htmlUrl, filemusicpath, fileHtmlPath, number);
					}
				} catch (Exception e) {
					System.out.println("\n第" + number + "首第二次下载失败");
				}
			}

		}
	}

	void down(int j, int i, String photoRealUrl, String htmlUrl, String filemusicpath, String fileHtmlPath
			, int number) throws Exception {
		int numMusi = j * 100 - 100 + i;
		String musicUrl = photoRealUrl + numMusi + ".mp3";
		String htmlDocumentUrl = htmlUrl + numMusi + ".html";
		Document doc = Jsoup.parse(new URL(htmlDocumentUrl), (4000));
		String html = doc.toString();
		String name = "";
		String s = html;
		int index = s.indexOf(".mp3");
		int end = s.indexOf("</a>", index);
		s = s.substring(index, end);
		name = s.substring(s.indexOf(">") + 1);
		String url = musicUrl;
		//创建不同的文件夹目录
		File file = new File(filemusicpath);
		//判断文件夹是否存在
		if (!file.exists()) {
			//如果文件夹不存在，则创建新的的文件夹
			file.mkdirs();
		}

		File file2 = new File(fileHtmlPath);
		//判断文件夹是否存在
		if (!file2.exists()) {
			//如果文件夹不存在，则创建新的的文件夹
			file2.mkdirs();
		}

		if (!fileHtmlPath.endsWith("/")) {
			fileHtmlPath += "/";
		}
		FileOutputStream fileOut = null;
		HttpURLConnection conn = null;
		InputStream inputStream = null;
		// 建立链接
		URL httpUrl = new URL(url);
		conn = (HttpURLConnection) httpUrl.openConnection();
		//以Post方式提交表单，默认get方式
		conn.setDoInput(true);
		conn.setDoOutput(true);
		// post方式不能使用缓存
		conn.setUseCaches(false);
		//连接指定的资源
		conn.connect();
		conn.setConnectTimeout(5000);
		//获取网络输入流
		inputStream = conn.getInputStream();
		BufferedInputStream bis = new BufferedInputStream(inputStream);
		//判断文件的保存路径后面是否以/结尾
		if (!filemusicpath.endsWith("/")) {
			filemusicpath += "/";
		}
		//写入到文件（注意文件保存路径的后面一定要加上文件的名称）
		int i_s = j * 100 - 99;
		int i_e = j * 100;
		String targetMusicDir = filemusicpath + i_s + "-" + i_e;
		makeDir(targetMusicDir);
		String htmlPre = fileHtmlPath + i_s + "-" + i_e;
		String musicPre = filemusicpath + i_s + "-" + i_e + "/" + name + "_" + number;
		fileOut = new FileOutputStream(musicPre + ".mp3");
		BufferedOutputStream bos = new BufferedOutputStream(fileOut);

		byte[] buf = new byte[4096];
		int length = bis.read(buf);
		//保存文件
		while (length != -1) {
			bos.write(buf, 0, length);
			length = bis.read(buf);
		}
		bos.close();
		bis.close();
		conn.disconnect();

		makeDir(htmlPre);
		WriteStringToFile(htmlPre + "/" + name + "_" + number + ".html", html);
		System.out.println("第" + number + "首" + name + "音频下载成功");
	}

	public void WriteStringToFile(String filePath, String s) {
		try {
			FileOutputStream fos = new FileOutputStream(filePath);
			fos.write(s.getBytes());
			fos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void makeDir(String path) {
		File file = new File(path);
		//如果文件夹不存在则创建
		if (!file.exists() && !file.isDirectory()) {
			System.out.println("//不存在 创建！");
			file.mkdir();
		} else {
		}
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
