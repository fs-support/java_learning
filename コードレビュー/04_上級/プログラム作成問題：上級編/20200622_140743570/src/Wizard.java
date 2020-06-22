import java.util.Random;

public class Wizard  extends Player{


	public Player Wizard_status(String name){
		//ステイタス
		Player  Player12  ;
		String job; // 名前
		int hp; // 体力
		int mp; //魔力
		int str; // 力
		int def; // 防御
		int luck; // 幸運
		int agi; // 敏捷性

		//魔法使い
		job="魔法使い";
		//hp
		hp=GetNumber(name,1)+50;
		while (hp>=150) {
			hp=hp-1;
		}
		//mp
		mp=GetNumber(name,6)+30;
		while (mp>=80) {
			mp=mp-1;
		}
		//str
		str=GetNumber(name,2)+1;
		while (str>=50) {
			str=str-1;
		}
		//def
		def=GetNumber(name,3)+1;
		while (def>=50) {
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
		if(Player1.Mp()>=20) {
			 a=ra.nextInt(2)+1;
		}else if(Player1.Mp()>=10) {
			a=1;
		}else {
			a=3;
		}

		int d=ra.nextInt(20)+10;

		if (a==1) {
			System.out.println("ファイアで攻撃！！");
			Player2.Attack_d(d);
			Player1.mp_Consumption(10);
			System.out.println(Player1.Name()+"が"+Player2.Name()+"に"+d+"のダメージを与えた！！");
		}else if(a==2) {
			System.out.println("サンダーで攻撃！！");
			Player2.Attack_d(d);
			Player1.mp_Consumption(20);
			System.out.println(Player1.Name()+"が"+Player2.Name()+"に"+d+"のダメージを与えた！！");
		}else {
			Attack(Player1,Player2);
		}
	}

}
