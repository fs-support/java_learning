package syokyu;
// 幹事君

/*合計金額と男性女性の人数からそれぞれの支払金額を計算する
* 変数名
* totalPrice		int	合計金額
* numberOfMale		int 男性の数
* numberOFFemale	int 女性の数
*
*
*
*/


public class kadai1 {

	public static void main(String[] args) {
		//支払い合計金額
		int totalPrice = 12000;
		//男性の人数
		int numberOfMale = 3;
		//女性の人数
		int numberOfFemale = 3;
		//多く払う金額
		int mie = 1000;
		//男性の金額
		int priceOfMale = 0;
		//女性の金額
		int priceOfFemale = 0;



		//割り勘処理
		//男性と女性の人数の合計を全額から割る
		priceOfMale = totalPrice / (numberOfMale + numberOfFemale);
		priceOfFemale = totalPrice / (numberOfMale + numberOfFemale);
		System.out.println("課題1：割り勘");
		System.out.println("男性："+priceOfMale+"円");
		System.out.println("女性："+priceOfFemale+"円");

		//男性が女性より多く払う処理
		priceOfMale = priceOfMale + (mie/2);
		priceOfFemale = priceOfFemale - (mie/2);

		System.out.println("課題2：男性が女性より多く支払う");
		System.out.println("男性："+priceOfMale+"円");
		System.out.println("女性："+priceOfFemale+"円");
	}

}
