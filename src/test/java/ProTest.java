/*
import com.alibaba.fastjson.JSON;
import com.example.Bean.Bean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.ObjectUtils;
import util.StringUtils;

import java.util.*;

import static util.StringUtils.isBlank;

*/
/**
 * Created by administrator on 2017/5/25.
 *//*

@RunWith(SpringJUnit4ClassRunner.class)
public class ProTest {
	private static String LOGIN_TYPE = "2-LOGIN_ID";

	@Test
	public void read() throws Exception {
		Integer type = 1;

		System.out.println("LIST:"+JSON.toJSONString(type.toString()));
	}

	private void setParam(List<Bean> list) {
		for (int i = 0; i < list.size(); i++) {
			Bean bean = list.get(i);
			bean.setNum(2);
		}
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
		*/
/**
		 * weiShu:保留几位数
		 *//*

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
*/
