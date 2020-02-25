package Data1;

import java.util.Random;

//アイテム名,危険度,食べたときのHP回復量,死因　情報
public class Database {
	Random ran=new Random();
	//アイテム名
	String D_name(int a){
		String A="";
		switch (a) {
			case 1:
			A="金リンゴ";
				break;
			case 2:
			A="オリハルコンリンゴ";
				break;
			case 3:
			A="アムリタ";
				break;
			case 4:
			A="スラー酒";
				break;
			case 5:
			A="八塩折之酒";
				break;
		}
		return A;
	}

	//危険度
	int D_danger(int b) {
		int B=0;
		switch (b) {
			case 1:
			//"金リンゴ"
			B=35;
				break;
			case 2:
			//"オリハルコンリンゴ"
			B=30;
				break;
			case 3:
			//	"アムリタ"
			B=20;
				break;
			case 4:
			//"スラー酒"
			B=15;
				break;
			case 5:
			//"八塩折之酒"
				B=10;
				break;
		}
		return B;
	}

	// 食べたときのHP回復量
	int D_heelHP(int c) {
		int C=0;
		switch (c) {
			case 1:
			//"金リンゴ"
			C=50;
				break;
			case 2:
			//"オリハルコンリンゴ"
			C=40;
				break;
			case 3:
			//	"アムリタ"
			C=30;
				break;
			case 4:
			//"スラー酒"
			C=20;
				break;
			case 5:
			//"八塩折之酒"
			C=10;
				break;
		}
	return C;
	}

	// 死因
	String D_coroner(int d ) {
		String D="";
		switch (d) {
			case 1:
			D="ヘラクレスやファト・フルモスに襲われる";
				break;
			case 2:
			D="ヘラクレスに強奪され餓死";
				break;
			case 3:
			D="不死になれず死亡";
				break;
			case 4:
			D="悪性の酔いにより狂乱死";
				break;
			case 5:
			D="飲み過ぎて泥酔して池に落ちて溺死";
				break;
		}
		return D;
	}
	boolean D_judgement(int danger){
		Random rnd =new Random();
		int Determine =rnd.nextInt();
		boolean Meal;
		if(danger<Determine) {
			Meal=true;
		}else {
			Meal=false;
		}
	return Meal;
	}
}
