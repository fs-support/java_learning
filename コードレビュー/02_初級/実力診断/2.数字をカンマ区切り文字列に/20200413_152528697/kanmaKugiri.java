package syokyuhen;

public class kanmaKugiri {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		
		System.out.println(commaSeparatedString(123));
		System.out.println(commaSeparatedString(12345));
		System.out.println(commaSeparatedString(1234567));
		System.out.println(commaSeparatedString(-1234567));
		System.out.println(commaSeparatedString(123456789));
		System.out.println(commaSeparatedString(-123456789));

	}
	
	public static StringBuilder commaSeparatedString(int number) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(Integer.toString(number));
		
		int offset = sb.length() - 3;
		
		while(offset > 0) {
			if(offset != 1 || number > 0) {
				sb.insert(offset, ",");
			}
			
			offset -= 3;
		}
		
		
		return sb;
	}

}
