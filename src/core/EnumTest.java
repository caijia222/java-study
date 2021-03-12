package core;

import org.junit.Test;

public class EnumTest {
	@Test
	public void test1() {
		Weekday day = Weekday.SUN;
		if (day == Weekday.SAT || day == Weekday.SUN) {
			System.out.println("Work at home!");
			System.out.println(day.name());
			System.out.println(day.ordinal());
		} else {
			System.out.println("Work at office!");
		}
	}

	@Test
	public void test2() {
		Weekday day = Weekday.SUN;
		switch (day) {
		case MON:
		case TUE:
		case WED:
		case THU:
		case FRI:
			System.out.println("Today is " + day + ". Work at office!");
			break;
		case SAT:
		case SUN:
			System.out.println("Today is " + day + ". Work at home!");
			break;
		default:
			throw new RuntimeException("cannot process " + day);
		}

	}

}

enum Weekday {
	SUN(0, "星期天"), MON(1, "星期一"), TUE(2, "星期二"), WED(3, "星期三"), THU(4, "星期四"), FRI(5, "星期五"), SAT(6, "星期六");

	public final int dayValue;
	public final String chinese;

	private Weekday(int dayValue, String chinese) {
		this.dayValue = dayValue;
		this.chinese = chinese;
	}

	@Override
	public String toString() {
		return this.chinese;
	}
}

enum Weekday_1 {
	SUN, MON, TUE, WED, THU, FRI, SAT;
}

class Weekday_iner {
	public static final Weekday_iner SUN = new Weekday_iner();
	public static final Weekday_iner MON = new Weekday_iner();
	public static final Weekday_iner TUE = new Weekday_iner();
	public static final Weekday_iner WED = new Weekday_iner();
	public static final Weekday_iner THU = new Weekday_iner();
	public static final Weekday_iner FRI = new Weekday_iner();
	public static final Weekday_iner SAT = new Weekday_iner();
}