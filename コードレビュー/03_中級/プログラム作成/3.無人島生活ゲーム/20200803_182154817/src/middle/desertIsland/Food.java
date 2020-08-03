package middle.desertIsland;

public enum Food {
	FOOD1(1, "毒蛇" ,15, 30, "毒蛇の毒に負けた"),
	FOOD2(2, "漂流物缶詰" ,30, 50, "歯では開けられなかった。歯が全部折れて出血死"),
	FOOD3(3, "流木", 8, 20, "バイキンだらけだった..."),
	FOOD4(4, "落ち葉",5, 20, "口の中の水分を全部持っていかれた"),
	FOOD5(5, "毒々しいキノコ",10, 30, "笑いが止まらず疲れて死んだ・・・"),
	FOOD6(6, "カラフルフルーツ",5, 30, "タネが喉に詰まった・・・"),
	FOOD7(7, "何かの卵",20, 35, "親鳥に見つかり、ついばまれた"),
	FOOD8(8, "鹿の肉",70, 80, "ハイエナに見つかり、噛まれて死んだ"),
	FOOD9(9, "幼虫", 10, 15, "噛んだ感触が気持ち悪く嘔吐しそのまま倒れた"),
	FOOD10(10, "フルーツ",5, 20, "腐っていた。腹を壊して動けず死亡");

	private int id ;
	private String name;
	private int danger;
	private int heelHP;
	private String coroner;

	Food(int i, String n, int d, int h, String c) {
		this.id = i;
		this.name = n;
		this.danger = d;
		this.heelHP = h;
		this.coroner = c;
	}

	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}

	public int getDanger() {
		return this.danger;
	}

	public int getHeelHP() {
		return this.heelHP;
	}

	public String getCoroner() {
		return this.coroner;
	}
}
