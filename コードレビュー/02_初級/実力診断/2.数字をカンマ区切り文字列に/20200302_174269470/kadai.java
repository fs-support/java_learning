package kadai;

public class kadai {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		System.out.println(comma(123));

		System.out.println(comma(12345));

		System.out.println(comma(1234567));

		System.out.println(comma(-1234567));

		System.out.println(comma(123456789));

		System.out.println(comma(-123456789));

	}

	private static String comma(int num) {

		StringBuilder as = new StringBuilder();

		as.append(Integer.toString(num));

	    int com = as.length()-3;
	    while(com<0) {
	    if(com!=1||num>0) {
	    	as.insert(com,",");
	    }
	    com=-3;
	    }

	return new String(as);
    }
}


