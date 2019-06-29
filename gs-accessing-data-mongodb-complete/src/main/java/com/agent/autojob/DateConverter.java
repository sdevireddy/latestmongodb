package com.agent.autojob;

import org.springframework.core.convert.converter.Converter;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateConverter implements Converter<String, Date> {

	@Override
	public Date convert(String source) {
		System.out.println("converter");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(dateFormat.parse(source));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c.getTime();
	}
}
