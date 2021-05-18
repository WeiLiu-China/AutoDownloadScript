package context;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.ObjectUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static util.StringUtils.isBlank;

/**
 * Created by administrator on 2017/5/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class test {
	private static String LOGIN_TYPE = "2-LOGIN_ID";

	@Test
	public void read() throws Exception {
		DemoSon demoSon1 = new DemoSon("son", 1, "name1");
		DemoSon demoSon2 = new DemoSon("son", 2, "name2");
		System.out.println(demoSon1.equals(demoSon2));
	}

	private byte[] readBytes(BufferedReader br, String encoding) throws IOException {
		String str = null;
		StringBuilder sb = new StringBuilder();
		while ((str = br.readLine()) != null) {
			sb.append(str);
		}
		return sb.toString().getBytes(Charset.forName(encoding));
	}

	@Test
	public void read2() throws Exception {

		System.out.println("||".equals("||"));
	}

	public static Integer getInteger(Object obj) {
		if (isBlank(obj)) {
			return 0;
		}
		if ((obj instanceof Integer)) {
			return (Integer) obj;
		}
		return Integer.valueOf(obj.toString());
	}

	public String calculatePercent(Long fenzi, Long fenmu, Integer weiShu) {
		/**
		 * weiShu:保留几位数
		 */
		String rt = "" + 0;
		double ca = 0;
		ca = ((double) fenzi) / (double) fenmu;
		ca = ca * 100;
		if (ObjectUtils.isEmpty(weiShu)) {
			weiShu = 1;
		}
		String formate = "%." + weiShu + "f";
		if (!ObjectUtils.isEmpty(weiShu)) {
			rt = String.format(formate, ca);
		}
		return rt + "%";
	}

	private List<User> getUsers(String policesString) {
		List<User> result = new ArrayList<>();
		String[] polices = policesString.split("\\|\\|");
		for (int i = 0; i < polices.length; i++) {
			User user = new User();
			String userString = polices[i];
			String[] split1 = userString.split(",");
			if (split1.length == 1) {
				split1 = userString.split("，");
			}
			if (split1 != null && split1.length == 2) {
				user.setId(split1[0]);
				user.setName(split1[1]);
				result.add(user);
			} else {

			}
		}
		return result;
	}

	public static class User {
		private String id;
		private String name;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}

}
