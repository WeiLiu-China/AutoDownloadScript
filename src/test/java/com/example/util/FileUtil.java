package com.example.util;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

/**
 * Copyright 2004-2020 by xdja.com  All rights reserved.
 *
 * @author xxx
 * @version 1.0.0
 * @ClassName fileUtil.java
 * @Description TODO
 * @createTime 2021/05/15 14:43:08
 */
public class FileUtil {
	public static void makeDir(String path) {
		File file = new File(path);
		//如果文件夹不存在则创建
		if (!file.exists() && !file.isDirectory()) {
			System.out.println(path + "//不存在 创建！");
			file.mkdirs();
		} else {
		}
	}


	/**
	 * @MethodName 下载
	 * path  :  D:\爬虫\天上灵粮\初信造就\陈希曾\2002年12月06-18日 也谈雅比斯的祷告 中英双语版（陈希曾）
	 * name  :  /1_2002年12月06日 也谈雅比斯的祷告 01 陈希曾.mp3
	 */
	public static void download(String path, String name, String url) throws Exception {
		FileUtil.makeDir(path);
		//获取URL对象
		URL urlConnection = new URL(url);
		//根据URL打开链接
		URLConnection connection = urlConnection.openConnection();
		//从连接处获取输入流对象
		InputStream inputStream = connection.getInputStream();
		path += name;
		FileOutputStream fs;
		try {
			File f = new File(path);
			if (!f.exists()) {
				path = path.replaceAll("\t", "");
				fs = new FileOutputStream(path);
				byte[] buffer = new byte[1204];

				int byteread = 0;

				while ((byteread = inputStream.read(buffer)) != -1) {
					fs.write(buffer, 0, byteread);
				}
				System.out.println(path + "保存成功！");

			} else {
				System.out.println(path + "File already exists");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param path     需要下载的文件路径，包括后缀名
	 * @param inStream 输入流
	 */
	public static void download(String path, InputStream inStream) {
		FileOutputStream fs;
		try {
			File f = new File(path);
			if (!f.exists()) {
				path = path.replaceAll("\t", "");
				fs = new FileOutputStream(path);
				byte[] buffer = new byte[1204];

				int byteread = 0;

				while ((byteread = inStream.read(buffer)) != -1) {
					fs.write(buffer, 0, byteread);
				}
				System.out.println(path + "保存成功！");

			} else {
				System.out.println(path + "File already exists");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
