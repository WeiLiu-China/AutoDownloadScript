package com.example.Api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.IOException;

/**
 * JsonUtil
 * 
 * @author jhm
 * 
 */
public class JsonUtil {

	/**
	 * string to class
	 * @param clazz
	 * @param source
	 * @return
	 * @throws IOException
	 */
	public static <T> T unmarshal(Class<T> clazz, String source) throws IOException {
		return (T) JSON.parseObject(source, clazz);
	}

	/**
	 * byte to class
	 * @param clazz
	 * @param source
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T unmarshal(Class<T> clazz, byte[] source) throws IOException {
		return (T) JSON.parseObject(source, clazz, new Feature[0]);
	}

	/**
	 * object to json
	 * @param object
	 * @return
	 * @throws IOException
	 */
	public static String marshal(Object object) throws IOException {
		return JSON.toJSONString(object,SerializerFeature.WriteMapNullValue);
	}

	/**
	 * object to json with dateformat
	 * @param object
	 * @param dateFormat
	 * @return
	 * @throws IOException
	 */
	public static String marshal(Object object, String dateFormat) throws IOException {
		return JSON.toJSONStringWithDateFormat(object, dateFormat, new SerializerFeature[0]);
	}

	/**
	 * objct to jsonbytes
	 * @param object
	 * @return
	 * @throws IOException
	 */
	public static byte[] marshalBytes(Object object) throws IOException {
		return JSON.toJSONBytes(object, new SerializerFeature[0]);
	}
}
