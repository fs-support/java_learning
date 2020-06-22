import java.util.Random;

public class Priest extends Player {

	public Player Priest_status(String name){
		//ステイタス
		Player  Player12 = null ;
		String job; // 名前
		int hp; // 体力
		int mp; //魔力
		int str; // 力
		int def; // 防御
		int luck; // 幸運
		int agi; // 敏捷性
		//僧侶
		job="僧侶";
		//hp
		hp=GetNumber(name,1)+80;
		while (hp>=200) {
			hp=hp-1;
		}
		//mp
		mp=GetNumber(name,6)+20;
		while (mp>=50) {
			mp=mp-1;
		}
		//str
		str=GetNumber(name,2)+10;
		while (str>=70) {
			str=str-1;
		}
		//def
		def=GetNumber(name,3)+10;
		while (def>=70) {
			def=def-1;
		}
		//luck
		luck=GetNumber(name,4)+1;
		while (luck>=100) {
			luck=luck-1;
		}
		//agi
		agi=GetNumber(name,5)+20;
		while (agi>=60) {
			agi=agi-1;
		}
		return Player12=(new Player( job,name, hp,mp ,str, def, luck,  agi));
	}
	protected static void Magic (Player Player1,Player Player2) {


		Random ra=new Random();
		int a;

		if(Player1.Hp_ms() > Player1.Hp() && Player1.Mp() >= 20 ) {
			 a=ra.nextInt(2)+1;

		}else if(Player1.Mp()>=10) {
			a=2;
		}else {
			a=3;
		}

		switch (a) {
		case 1:
			System.out.println("体力を回復しました。");
			Player1.Healing_state();
			Player1.mp_Consumption(20);

			break;

		case 2:

			//ポイズン
			if(Player2.State_p()=="") {
				System.out.println("相手に毎ターン20の毒ダメージを与える");
				Player2.State_P("毒");
				Player1.mp_Consumption(10);
			}else {
			//パライズ
				int d=ra.nextInt(100);
				if(d<=20) {
					System.out.println("パライズ成功！！");
					Player1.mp_Consumption(10);
					Player2.State_S("麻痺");
				}else{
					System.out.println("パライズ失敗！！");
				}
			}
			break;
		case 3:
			Attack(Player1, Player2);
			break;
		}


	}


}
