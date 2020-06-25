package project1;



import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;



public class brackjackgame001 {

	public static void main(String[] args) throws Exception{

		Scanner stdin = new Scanner(System.in);
		String[] card = {"A","1","2","3","4","5","6","7","8","9","10","11","12"};
		Random random = new Random();
		//自分用
		int myscore = 0;
		ArrayList<String> mycard = new ArrayList<>();

		//相手用
		int aitescore = 0;
		ArrayList<String> aitecard = new ArrayList<>();


		//まず2枚カードを引く
			for(int i=0; i<2; i++) {
				int no = 0;

				//まず自分からだよ
				no = random.nextInt(13);
				System.out.println("あなたに「"+card[no]+"」が配られました");
				mycard.add(card[no]);

				//ディーラー用のだよ
				no = random.nextInt(13);
				System.out.println("ディーラーに「"+card[no]+"」が配られました");
				aitecard.add(card[no]);
			}

			myscore=Getscore(mycard);
			aitescore = Getscore(aitecard);



			System.out.println("");
			System.out.println("ディーラーの合計は"+aitescore+"です");
			System.out.println("自分の合計は"+myscore+"です");


			//21以下なら
			while(myscore<21) {

			System.out.println("もう一枚カードを引きますか？（Y/N）");

			if(stdin.next().equals("Y")) {
				int no =random.nextInt(13);
				System.out.println("あなたに「"+card[no]+"」が配られました");
				mycard.add(card[no]);


				myscore=Getscore(mycard);
				System.out.println("自分の合計は"+myscore+"です");

			}else {
				break;
			}

			System.out.println("");

			//ディーラーが17以下なら
			while(myscore<17) {
					int no =random.nextInt(13);
					System.out.println("ディーラーに「"+card[no]+"」が配られました");
					aitecard.add(card[no]);


					aitescore=Getscore(aitecard);
					System.out.println("ディーラーの合計は"+aitescore+"です");
				}



			//バーストしているかどうか、引き終わった後のスコア処理
			if(myscore>21) {
				myscore = -1;
			}

			if(aitescore>21) {
				myscore = -1;
			}


			if(myscore > aitescore) {
				System.out.println("あなたの勝ちです！");

			}else if(myscore == aitescore) {
				System.out.println("引き分けです");

			}else{
				System.out.println("ディーラーの勝ちです…><");
			}


			stdin.close();
			}
	}





		//自分と相手のカードの処理を書くよ
		static int Getscore(ArrayList<String>card) {

				int score = 0;
				boolean cardA = false;


				//カードの種類確認
				for (String str: card) {
					switch(str){
					case "A":
						score += 1;

						//Aのカード所持してるサイン入れる。
						cardA = true;
						break;

					case"J":
					case"Q":
					case"K":
						score += 10;
						break;


					//２～１０までの文字を数字にかえるよ
					default:
						score += Integer.parseInt(str);
					}
				}


					//Aを持っているか確認
					if(cardA == true && score <=11) {
						score += 10;
					}

				return score;

		}

}
