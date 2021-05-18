package util;


import org.springframework.util.ObjectUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

	/**
	 * 邮箱
	 */
	private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public static String checkBlankParams(Map<String, Object> reqParams, String... paramNames) {
		String result = null;
		if ((reqParams == null) || (reqParams.isEmpty())) {
			return "request params is blank!";
		}
		for (String paramsName : paramNames) {
			if (isBlank(reqParams.get(paramsName))) {
				result = paramsName + " is blank!";
				break;
			}
		}
		return result;
	}

	public static boolean isBlank(Object obj) {
		if (null == obj) {
			return true;
		}
		String str = obj.toString();
		int strLen;
		if ((strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static boolean isNotBlank(Object obj) {
		return !isBlank(obj);
	}

	public static String getString(Object obj) {
		if (isNotBlank(obj)) {
			return obj.toString();
		}
		return "";
	}

	public static Date getDate(Object obj) throws ParseException {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		date = sdf.parse((String) obj);
		return date;
	}

	public static Date getDateTime(Object obj) throws ParseException {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		if (obj != null) {
			date = sdf.parse(obj.toString());
		}
		return date;
	}

	public static Integer getInteger(Object obj) {
		if (isBlank(obj)) {
			return null;
		}
		if ((obj instanceof Integer)) {
			return (Integer) obj;
		}
		if(ObjectUtils.isEmpty(obj)){
			return 0;
		}
		return Integer.valueOf(obj.toString());
	}

	public static boolean getBoolean(Object obj) {
		if (isBlank(obj)) {
			return false;
		}
		if ((obj instanceof Boolean)) {
			return ((Boolean) obj).booleanValue();
		}
		return Boolean.valueOf(obj.toString()).booleanValue();
	}

	public static Long getLong(Object obj) {
		if (isBlank(obj)) {
			return null;
		}
		if ((obj instanceof Long)) {
			return (Long) obj;
		}
		return Long.valueOf(obj.toString());
	}

	public static Integer getInt(Object obj) {
		if (isBlank(obj)) {
			return Integer.valueOf(0);
		}
		if ((obj instanceof Integer)) {
			return (Integer) obj;
		}
		return Integer.valueOf(obj.toString());
	}

	public static Double getDouble(Object obj) {
		if (isNotBlank(obj)) {
			return Double.valueOf(obj.toString());
		}
		return null;
	}

	public static boolean isNotEmpty(CharSequence cs) {
		return !isEmpty(cs);
	}

	public static boolean isEmpty(CharSequence cs) {
		return cs == null || cs.length() == 0;
	}

	/**
	 * 校验手机号是否合法
	 * 13+任意数
	 * 15+除4的任意数
	 * 18+除1和4的任意数
	 * 17+除9的任意数
	 *
	 * @param phone
	 * @return
	 */
	public static boolean isPhone(String phone) {
		String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(phone.trim());
		return m.matches();
	}

	/**
	 * 校验邮箱是否合法
	 *
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		return Pattern.matches(EMAIL_REGEX, email.trim());
	}

	public static String getCurrentIndexName(String prefixIndex, Long time) {
		StringBuilder stringBuilder = new StringBuilder();
		if (time == null) {
			time = System.currentTimeMillis();
		}
		Date date = new Date(time);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String str = sdf.format(date);
		return stringBuilder.append(prefixIndex).append(str).toString();
	}


}
