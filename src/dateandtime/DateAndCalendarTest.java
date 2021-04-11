package dateandtime;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.junit.Test;

public class DateAndCalendarTest {
	
	@Test
	public void test() {
		int n = 123400;
		System.out.println(n);
		System.out.println(Integer.toHexString(n));
		System.out.println(NumberFormat.getCurrencyInstance(Locale.US).format(n));
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void test2() {
		System.out.println(System.currentTimeMillis());
		Date date = new Date();
		System.out.println(date.getYear() + 1900);
		System.out.println(date.getMonth() + 1);
		System.out.println(date.getDate());
		System.out.println(date.toString());
		System.out.println(date.toGMTString());
		System.out.println(date.toLocaleString());
	}
	
	@Test
	public void test3() {
		Date date = new Date();
		var sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(date));
	}
	
	@Test
	public void test4() {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		int millisecond = calendar.get(Calendar.MILLISECOND);
		int week = calendar.get(Calendar.DAY_OF_WEEK);
		System.out.println(year +"-"+ month+"-" + day+" " + hour+":" + minute+":" + second +"."+ millisecond+" "+week);
	}
	
	@Test
	public void test5() {
		Calendar c = Calendar.getInstance();
		c.clear();
		c.set(Calendar.YEAR, 2021);
		c.set(2021, 3, 11, 20, 20, 20);
		c.add(Calendar.MONTH, 1);
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime()));
	}
	
	@Test
	public void test6() {
		TimeZone tzDefault = TimeZone.getDefault();
		TimeZone tzGMT9 = TimeZone.getTimeZone("GMT+09:00");
		TimeZone tzNY = TimeZone.getTimeZone("America/New_York");
		System.out.println(tzDefault.getID());
		System.out.println(tzGMT9.getID());
		System.out.println(tzNY.getID());
		String[] availableIDs = TimeZone.getAvailableIDs();
		for (String id : availableIDs) System.out.println(id);
	}
	
	@Test
	public void test7() {
		Calendar c = Calendar.getInstance();
		c.clear();
		c.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		c.set(2021, 3, 11, 21, 40, 0);
		var sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(c.getTime()));
		sdf.setTimeZone(TimeZone.getTimeZone("America/New_York"));
		System.out.println(sdf.format(c.getTime()));
	}
	
	@Test
	public void test8() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, 2);
		c.add(Calendar.HOUR, -3);
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime()));
	}

}
