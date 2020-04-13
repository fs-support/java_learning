package syokyuhen;

public class kanjikun {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		
		int totalPrice = 12000 , numberOfMale = 3 , numberOfFemale = 3;
		
		int malePay = 0 , femalePay = 0 ;
		
		malePay = totalPrice/(numberOfMale + numberOfFemale);
		femalePay = totalPrice/(numberOfMale + numberOfFemale);
		
		System.out.println("男性：" + malePay +  "円");
		System.out.println("女性：" + femalePay +  "円");

	}

}
