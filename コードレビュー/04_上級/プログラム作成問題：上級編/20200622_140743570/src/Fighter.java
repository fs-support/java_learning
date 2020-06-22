public class Fighter extends Player {

	public Player Fighter_status(String name){
		//ステイタス
		Player  Player12 = null ;
		String job; // 名前
		int hp; // 体力
		int mp; //魔力
		int str; // 力
		int def; // 防御
		int luck; // 幸運
		int agi; // 敏捷性

		job="戦士";
		//hp
		hp=GetNumber(name,1)+100;

		while (hp >= 300) {

			hp=hp-1;
		}
		//mp
		mp=0;
		//str
		str=GetNumber(name,2)+30;
		while (str >= 100) {
			str=str-1;
		}
		//def
		def=GetNumber(name,3)+30;
		while (def>=100) {
			def=def-1;
		}
		//luck
		luck=GetNumber(name,4)+1;
		while (luck>=100) {
			luck=luck-1;
		}
		//agi
		agi=GetNumber(name,5)+1;
		while (agi>=50) {
			agi=agi-1;
		}
		return Player12=(new Player( job,name, hp,mp ,str, def, luck,  agi));
	}

	public static void Fighter_Attack(Player Player1, Player Player2) {
		// TODO 自動生成されたメソッド・スタブ
		Attack(Player1,Player2);

	}
}