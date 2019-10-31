/*練習
0101
*/
public class totalPrice0101{
	
	public static void main(string[] args){
		
		int totalPrice = 12000;
		int number0fMale = 3;
		int number0fFemale = 3;
		
		int price = totalPrice / (number0fMale + number0fFemale);
		
		System.out.println("男性："+price);
		System.out.println("女性："+price);
		
	}
}

/*
0102
*/
public class totalPrice0102{
	
	public static void main(String[] args){
		
		int totalPrice = 12000;
		int number0fMale = 3;
		int number0fFemale = 3;
		
		final int MALE_MANY_MONEY = 1000;
		
		totalPrice = toralPrice - (MALE_MANY_MONEY * number0fMale);
		int price = totalPrice / (number0fMale + number0fFemale);
		
		System.out.println("男性："+(price +MALE_MANY_MONEY));
		System.out.println("女性"+price);
		
	}
}

/*
0201
※書き方を練習
*/
public class separate0201{
	
	public static void main(String[] args){
		
		System.out.println(commaSeparatedString(123));
		System.out.println(commaSeparatedString(12345));
		System.out.println(commaSeparatedString(1234567));
		System.out.println(commaSeparatedString(-1234567));
		System.out.println(commaSeparatedString(123456789));
		System.out.println(commaSeparatedString(-123456789));
	}
	
	@param number
	
	private static Striing commaSeparatedString(int number){
		StringBuilder sb = new StringBuilder();
		
		sb.append(Integer.toString(number));
		
		int offset = sb.length() - 3;
		
		wfile(offset > 0){
			if(offset != 1 || number > 0)
			{
				sb.insert(offset, ",");
			}
			offset -= 3;
		}
		
		return new String(sb);

	}	
}
		
