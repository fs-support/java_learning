/*
練習04
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
0301
*/
public class encode{
	
	public static void main(String[] args){
		String text = encode("AAAAABBBBBBBBBBCDDDDDDDDEEFFFFFG");
		System.out.println(text);
	}
	
	@param input
	@return
	
	private static String encode(String input){
		StringBuilder sb = new StringBuilder();
		char last = input.charAt(0);
		int count = 1;
		
		for(int i =  1;i < input.length(); i++){
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
		retunr sb.toString();
	}
}

/*
0302
*/
public class decode{
	public static void main(String[] args){
		 String text = decode("A5B10CD9E2F5G")
		 System.out.println(text);
	}
	
	@param input
	@return
	
	private static String decode(String input){
		
		StringBuilder sb = new StringBuilder();
		
		int offset = 0;
		while(offset < input.length())
		{
			String chara = input.substring(offset,offset+1);
			offset++;
			
			int length = 1;
			if(offset < input.length())
			{
				String str = input.substring(offset,offset+1)
				Boolean numCheck = str.matches("[0-9]");
				
				if(numCheck == true){
					numberString += str;
					offset++;
				}else{
					break;
				}
			}
			if(numberString.length() >= 1)
			{
				length = Integer.parseInt(numberSring);
			}
		}
		for(int j = 0;j < length; j++)
		{
			sb.append(chara);
		}
		}
		return sb.toString();
	}
}