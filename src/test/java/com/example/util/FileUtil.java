package com.example.util;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

import static com.example.util.HttpClientUtil.downLoadByHttpClient;

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

	public static final int TWO_MIN = 2 * 60 * 1000;

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
		connection.setConnectTimeout(TWO_MIN);
		connection.setReadTimeout(TWO_MIN);
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
			delete(path);
			e.printStackTrace();
		}
	}

	/**
	 * 删除文件，可以是文件或文件夹
	 *
	 * @param fileName 要删除的文件名
	 * @return 删除成功返回true，否则返回false
	 */
	public static boolean delete(String fileName) {
		File file = new File(fileName);
		if (!file.exists()) {
			System.out.println("删除文件失败:" + fileName + "不存在！");
			return false;
		} else {
			if (file.isFile())
				return deleteFile(fileName);
			else
				return deleteDirectory(fileName);
		}
	}

	/**
	 * 删除单个文件
	 *
	 * @param fileName 要删除的文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		// 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
		if (file.exists() && file.isFile()) {
			if (file.delete()) {
				System.out.println("删除单个文件" + fileName + "成功！");
				return true;
			} else {
				System.out.println("删除单个文件" + fileName + "失败！");
				return false;
			}
		} else {
			System.out.println("删除单个文件失败：" + fileName + "不存在！");
			return false;
		}
	}

	/**
	 * 删除目录及目录下的文件
	 *
	 * @param dir 要删除的目录的文件路径
	 * @return 目录删除成功返回true，否则返回false
	 */
	public static boolean deleteDirectory(String dir) {
		// 如果dir不以文件分隔符结尾，自动添加文件分隔符
		if (!dir.endsWith(File.separator))
			dir = dir + File.separator;
		File dirFile = new File(dir);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
			System.out.println("删除目录失败：" + dir + "不存在！");
			return false;
		}
		boolean flag = true;
		// 删除文件夹中的所有文件包括子目录
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
			// 删除子目录
			else if (files[i].isDirectory()) {
				flag = deleteDirectory(files[i]
						.getAbsolutePath());
				if (!flag)
					break;
			}
		}
		if (!flag) {
			System.out.println("删除目录失败！");
			return false;
		}
		// 删除当前目录
		if (dirFile.delete()) {
			System.out.println("删除目录" + dir + "成功！");
			return true;
		} else {
			return false;
		}
	}

	public static void clientDownload(String path, String name, String downloadUrl) throws Exception {
		FileUtil.makeDir(path);
		File file = new File(path + name);
		if (!file.exists()) {
			try {
				// http请求
				OutputStream output = new FileOutputStream(file);
				downLoadByHttpClient(downloadUrl, output);
			} catch (IOException e) {
				delete(path);
				System.out.println("下载失败---e.getMessage()----------" + e.getMessage());
				e.printStackTrace();
			}
		} else {
			System.out.println("文件已存在--------" + path + name);
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
