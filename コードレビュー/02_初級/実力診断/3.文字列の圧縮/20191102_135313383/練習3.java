/*
練習3
0102
*/
public class totalPrice0102{
	
	public static void main(String[] args){
		
		int totalPrice = 12000;
		int number0fMale = 3;
		int number0fFemale = 3;
		final int MALE_MANY_MONEY = 1000;
		
		totalPrice = totalPrice - (MALE_MANY_MONEY * number0fMale);
		
		int price = totalPrice / (number0fMale + number0fFemale);
		
		System.out.println("男性："+(price + MALE_MANY_MONEY));
		System.out.println("女性："+price);
	}
}

/*
0201
*/
public class Comma{
	
	public static void main(String[] args){
		
		System.out.println(commaSeparatedString(123));
		System.out.println(commaSeparatedString(12345));
		System.out.println(commaSeparatedString(1234567));
		System.out.println(commaSeparatedString(-1234567));
		System.out.println(commaSeparatedString(123456789));
		System.out.println(commaSeparatedString(-123456789));
		
	}
	@param number
	
	private static String commaSeparatedString(int number){
		String Bulider sb = new StringBuilder();
		sb.append(Integer.toString(number));
		int offset = sb.length() - 3;
		
		while(offset > 0){
			if(offset != 1 || number > 0){
				sb.insert(offset,",");
			}
			offset -= 3;
		}
		return new String(sb);
	}
}

/*
0301
*/
public class encode{
	
	public static void main(String[] args){
		String text = encode("AAAAABBBBBBBBBBCDDDDDDDDDEEFFFFFG");
		System.out.println(text);
	}
	
	@param input
	@return
	
	private static String encode(String inout){
		StringBuilder sb = new StringBuilder();
		char last = input.charAt(0);
		int count = 1;
		
		for(int i = 1;i < input.length(); i++){
			count++;
		}else{
			sb.append(last);
			
			if(count != 1){
				sb.append(count);
			}
			last = input.charAt(i);
			count = 1;
		}
	}
	sb.append(last);
	if(count != 1){
		sb.append(count);
	}
	return sb.toString();
　}
}
	