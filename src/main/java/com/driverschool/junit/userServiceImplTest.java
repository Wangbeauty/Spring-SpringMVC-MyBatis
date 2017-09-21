package com.driverschool.junit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.driverschool.model.subscribe;
import com.driverschool.service.subscribeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-mybatis.xml"})
public class userServiceImplTest {
	
	@Resource
	private subscribeService dao;
	
	@Test
	public void test() {
		Calendar today = Calendar.getInstance();
		Date date = today.getTime();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String str = sdf.format(date);
//		
//		try {
//			sdf.setLenient(false);
//			date = sdf.parse(str);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		System.out.println("ss="+date);
	}

}
