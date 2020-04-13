package syokyuhen;

public class kanjikun2 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
int totalPrice = 12000 , numberOfMale = 9 , numberOfFemale = 3;
		
		int malePay = 0 , femalePay = 0 ;
		
		malePay = ((totalPrice - 1000 * numberOfMale) / (numberOfMale + numberOfFemale)) + 1000 ;
		femalePay = (totalPrice - malePay * numberOfMale)/numberOfFemale;
		
		System.out.println("男性：" + malePay +  "円");
		System.out.println("女性：" + femalePay +  "円");

	}

}
