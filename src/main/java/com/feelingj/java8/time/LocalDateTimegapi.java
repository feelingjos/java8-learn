package com.feelingj.java8.time;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

import org.junit.Test;

public class LocalDateTimegapi{
	
	
	@Test
	public void test1() {
		LocalDateTime lat=LocalDateTime.now();
		System.out.println(lat);//系统当前时间
		
		LocalDateTime of = LocalDateTime.of(2017, 10, 19, 13, 22, 33);
		System.out.println(of);//指定年月日参数
		
		LocalDateTime plusYears = lat.plusYears(2);
		System.out.println(plusYears);//加2年
		
		LocalDateTime minusMinutes = lat.minusMinutes(2);
		System.out.println(minusMinutes);//减两个月
		
		System.out.println(lat.getYear());//获取年
		System.out.println(lat.getMonth());//获取月
		System.out.println(lat.getMonthValue());//获取月
		
		System.out.println(lat.getHour());//小时
		System.out.println(lat.getMinute());//分钟
		System.out.println(lat.getSecond());//秒
		
	}
	
	/**
	 * Instant : 时间戳。 （使用 Unix 元年  1970年1月1日 00:00:00 所经历的毫秒值）
	 */
	@Test
	public void test2() {
		
		Instant ins = Instant.now();  //默认使用 UTC 时区
		System.out.println(ins);
		
		//偏移量运算
		OffsetDateTime odt = ins.atOffset(ZoneOffset.ofHours(8));
		System.out.println(odt);
		
		System.out.println(ins.getNano());
		
		Instant ins2 = Instant.ofEpochSecond(5);
		System.out.println(ins2);
		
	}
	
	//4. TemporalAdjuster : 时间校正器
	@Test
	public void test4(){
		LocalDateTime ldt = LocalDateTime.now();
		System.out.println(ldt);
		
		LocalDateTime ldt2 = ldt.withDayOfMonth(10);
		System.out.println(ldt2);
		
		LocalDateTime ldt3 = ldt.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
		System.out.println(ldt3);
		
		//自定义：下一个工作日
		LocalDateTime ldt5 = ldt.with((l) -> {
			LocalDateTime ldt4 = (LocalDateTime) l;
			
			DayOfWeek dow = ldt4.getDayOfWeek();
			
			if(dow.equals(DayOfWeek.FRIDAY)){
				return ldt4.plusDays(3);
			}else if(dow.equals(DayOfWeek.SATURDAY)){
				return ldt4.plusDays(2);
			}else{
				return ldt4.plusDays(1);
			}
		});
		
		System.out.println(ldt5);
		
	}
	
	@Test
	public void test5(){
//		DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE;
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss E");
		
		LocalDateTime ldt = LocalDateTime.now();
		String strDate = ldt.format(dtf);
		
		System.out.println(strDate);
		
		LocalDateTime newLdt = ldt.parse(strDate, dtf);
		System.out.println(newLdt);
	}
	
	@Test
	public void test6(){
		Set<String> set = ZoneId.getAvailableZoneIds();
		set.forEach(System.out::println);
	}
	
	@Test
	public void test7(){
		LocalDateTime ldt = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
		System.out.println(ldt);
		
		ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("US/Pacific"));
		System.out.println(zdt);
	}

}
