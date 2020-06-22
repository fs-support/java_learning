package middleTest;

public class Trump {
	private static enum mark {
		Diamonds, Hearts, Clubs, Spades
	}

	private static String[] numbers = { "A", "2", "3", "4", "5", "6",
			"7", "8", "9", "10", "J", "Q", "K" };
	
	mark m;
	String number;
	Trump(mark m, String str) {
		this.m = m;
		number = str;
	}
	
	public static String getNumber(int i) {
		int num = i-1;
		if(i >= 1 && i <= 13) {
			return numbers[num];
		} else {
			return "-1";
		}
	}
	
	public static mark getMark(int i) {
		switch(i) {
		case 1:
			return mark.Diamonds;
		case 2:
			return mark.Hearts;
		case 3:
			return mark.Clubs;
		case 4:
			return mark.Spades;
		default:
			return mark.Diamonds;
		}
	}
}
