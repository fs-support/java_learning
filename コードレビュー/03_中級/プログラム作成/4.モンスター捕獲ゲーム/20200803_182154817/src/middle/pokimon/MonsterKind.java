package middle.pokimon;

public enum MonsterKind {
	MONSTER1("ザコモン", 30, 20, 20, 30, 72), 
	MONSTER2("フツモン", 50, 20, 30, 30, 50), 
	MONSTER3("ツヨモン", 100, 50, 30, 25,28), 
	MONSTER4("ボスモン", 100, 50, 50, 10, 25), 
	MONSTER5("レアモン", 150, 100, 100, 5, 14);

	private String name;
	private int hp, power, defense, encount, capture;

	MonsterKind(String name, int hp, int power, int defense, int encount, int capture) {
		this.name = name;
		this.hp = hp;
		this.power = power;
		this.defense = defense;
		this.encount = encount;
		this.capture = capture;
	}

	public String getName() {
		return name;
	}

	public int getHp() {
		return hp;
	}

	public int getPower() {
		return power;
	}

	public int getDefense() {
		return defense;
	}

	public int getEncount() {
		return encount;
	}

	public int getCapture() {
		return capture;
	}

	public static MonsterKind getEncount(int i) {
		int sum1 = MonsterKind.MONSTER1.getEncount() + MonsterKind.MONSTER2.getEncount();
		int sum2 = sum1 + MonsterKind.MONSTER3.getEncount();
		int sum3 = sum2 + MonsterKind.MONSTER4.getEncount();
		if (i <= MonsterKind.MONSTER1.getEncount()) {
			return MONSTER1;
		} else if (i <= sum1) {
			return MONSTER2;
		} else if (i <= sum2) {
			return MONSTER3;
		} else if (i <= sum3) {
			return MONSTER4;
		} else {
			return MONSTER5;
		}
	}
}
