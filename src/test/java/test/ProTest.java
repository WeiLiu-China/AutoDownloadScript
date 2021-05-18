package test;

import com.alibaba.fastjson.JSON;
import com.example.Bean.Bean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import test.bean.Status;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProTest {

	@Test
	public void read() throws Exception {
		Long pageNo = 5L;
		pageNo = (pageNo == null || pageNo < 1) ? 1 : pageNo;

		System.out.println(pageNo);
	}

}
