/*
診断
01
*/
package novice;

public class FsNovice0101{
	
	public static void main(String[] args){
		
		int totalPrice = 12000;
		
		int number0fMale = 3;
		
		int number0fFemale = 3;
		
		int price = totalPrice / (number0fMale + number0fFemale);
		
		System.out.println("男性：" + price);
		System.out.println("女性：" + price);
		
	}
}

/*
02
*/
package novice;

public class FsNovice0102{
	
	public static void main(String[] args){
		
		int totalPrice = 12000;
		
		int number0fMale = 3;
		
		int number0fFemale = 3;
		
		final int MALE_MANY_MONEY = 1000;
		
		totalPrice = totalPrice - (MALE_MANY_MONEY * number0fMale);
		
		int price = totalPrice / (number0fMale + number0fFemale);
		
		System.out.println("男性：" + (price + MALE_MANY_MONEY));
		System.out.println("女性：" + price);
		
	}
}