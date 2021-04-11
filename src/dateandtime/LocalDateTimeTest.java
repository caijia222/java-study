package dateandtime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

public class LocalDateTimeTest {
	
	@Test
	public void test() {
		LocalDate date = LocalDate.now();
		LocalTime time = LocalTime.now();
		LocalDateTime dateTime = LocalDateTime.now();
		System.out.println(date);
		System.out.println(time);
		System.out.println(dateTime);
	}
	
	@Test
	public void test2() {
		LocalDateTime dateTime = LocalDateTime.now();
		LocalDate date = dateTime.toLocalDate();
		LocalTime time = dateTime.toLocalTime();
		System.out.println(dateTime+"***"+date+"*"+time);
	}
	
	@Test
	public void test3() {
		LocalDate date = LocalDate.of(2021, 4, 11);
		System.out.println(date);
		LocalTime time = LocalTime.of(22, 4, 3);
		System.out.println(time);
		LocalDateTime dateTime = LocalDateTime.of(2021, 4, 11, 20, 3, 1);
		System.out.println(dateTime);
		LocalDateTime dateTime2 = LocalDateTime.of(date, time);
		System.out.println(dateTime2);
	}
	
	@Test
	public void test4() {
		LocalDateTime dateTime = LocalDateTime.parse("2021-04-11T22:10:22");
		System.out.println(dateTime);
		LocalDate date = LocalDate.parse("2021-04-11");
		System.out.println(date);
		LocalTime time = LocalTime.parse("22:11:22");
		System.out.println(time);
	}
	
	@Test
	public void test5() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		System.out.println(dtf.format(LocalDateTime.now()));
		LocalDateTime dateTime = LocalDateTime.parse("2021/01/11 22:19:22", dtf);
		System.out.println(dateTime);
	}
	
	@Test
	public void test6() {
		LocalDateTime dateTime = LocalDateTime.of(2021, 4, 11, 22, 22, 22);
		LocalDateTime dateTime2 = dateTime.plusDays(1).minusHours(2);
		LocalDateTime dateTime3 = dateTime2.minusMonths(2);
		System.out.println(dateTime);
		System.out.println(dateTime2);
		System.out.println(dateTime3);
		LocalDateTime dateTime4 = dateTime.withHour(23);
		LocalDateTime dateTime5 = dateTime.withMonth(9);
		System.out.println(dateTime4);
		System.out.println(dateTime5);
	}
	
	@Test
	public void test7() {
		LocalDateTime dateTime = LocalDateTime.now().withDayOfMonth(1);
		System.out.println(dateTime);
	}

}
