package sample;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Random;

//プレイヤークラス(各種ジョブの基底クラス)
public class Player {
	// =======================
	// フィールド変数
	// =======================
	protected String name; // 名前
	protected String job; // 職業
	protected int hp; // HP
	protected int mp; // MP
	protected int str; // 攻撃力
	protected int def; // 防御力
	protected int luck; // 運
	protected int agi; // 素早さ
	protected int maxhp; // 最大HP
	protected String state; // 状態
	protected String item; // 所持品
	static Random rnd = new Random();

	// =======================
	// コンストラクタ
	// =======================
	/**
	 * コンストラクタ
	 *
	 * @param name
	 *            : プレイヤー名
	 */
	public Player(String name) {
		this.name = name;
		int i;

		// キャラクターのパラメータ生成
		makeCharacter();

		// 乱数を使うための準備
		Random random = new Random();

		i = random.nextInt(100);

		if(i <= 50){
			this.item = "薬草";
		}else{
			this.item = "解毒薬";
		}
	}

	// =======================
	// Getter / Setter
	// =======================
	/**
	 * プレイヤー名を取得する
	 *
	 * @return プレイヤー名
	 */
	String GetName() {
		return this.name;
	}

	/**
	 * 職業名を取得する
	 *
	 * @return 職業名
	 */
	String GetJob() {
		return this.job;
	}

	/**
	 * 現在HPを取得する
	 *
	 * @return 現在HP
	 */
	public int GetHP() {
		return this.hp;
	}

	/**
	 * 現在MPを取得する
	 *
	 * @return 現在MP
	 */
	public int GetMP() {
		return this.mp;
	}

	/**
	 * 攻撃力を取得する
	 *
	 * @return 攻撃力
	 */
	public int GetSTR() {
		return this.str;
	}

	/**
	 * 防御力を取得する
	 *
	 * @return 防御力
	 */
	public int GetDEF() {
		return this.def;
	}

	/**
	 * 運を取得する
	 *
	 * @return 運
	 */
	public int GetLUCK() {
		return this.luck;
	}

	/**
	 * 素早さを取得する
	 *
	 * @return 素早さ
	 */
	public int GetAGI() {
		return this.agi;
	}

	/**
	 * 最大HPを取得する
	 *
	 * @return 最大HP
	 */
	public int GetMAXHP() {
		return this.maxhp;
	}

	/**
	 * 現在の状態を取得する
	 *
	 * @return 状態
	 */
	public String GetSTATE() {
		return this.state;
	}

	/**
	 * 現在の所持品を取得する
	 *
	 * @return 所持品
	 */
	public String GetITEM() {
		return this.item;
	}

	// =======================
	// protected メソッド
	// =======================
	/**
	 * 名前(name)からキャラクターに必要なパラメータを生成する
	 */
	protected void makeCharacter() {
		// ジョブごとにオーバーライドして処理を記述してください
	}

	/**
	 * ハッシュダイジェストから数値を取り出す
	 *
	 * @param name
	 *            ：名前
	 * @param index
	 *            : 何番目の数値を取り出すか
	 * @return 数値(0～255)
	 */
	public static Integer getNumber(String name, Integer index) {
		try {
			byte[] result = MessageDigest.getInstance("SHA-1").digest(
					name.getBytes());
			String digest = String.format("%040x", new BigInteger(1, result));
			String hex = digest.substring(index * 2, index * 2 + 2);
			return Integer.parseInt(hex, 16);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	// =======================
	// public メソッド
	// =======================
	/**
	 * 現在のステータスを System.out で表示する
	 */
	public void printStatus() {
		System.out
		.printf("%s (HP=%3d : MP=%3d : STR=%3d : DEF=%3d : LUCK=%3d : AGI=%3d)\n",
				this.GetName(), this.GetHP(), this.GetMP(),
				this.GetSTR(), this.GetDEF(), this.GetLUCK(),
				this.GetAGI());
		System.out.println("状態 : " + GetSTATE());
		System.out.println("所持品 : " + GetITEM());
	}

	/**
	 * 対象プレイヤーに攻撃を行う
	 *
	 * @param defender
	 *            : 対象プレイヤー
	 * @param party
	 * @param mikata
	 */
	public void attack(Player defender, Player attacker, Party mikata, Party party, String strategyname) {
		// ジョブごとにオーバーライドして処理を記述してください
	}

	/**
	 * 対象プレイヤー(target)に対して与える直接ダメージを計算する
	 *
	 * @param target
	 *            : 対象プレイヤー
	 * @return ダメージ値(0～)
	 */
	protected int CalcDamage(Player target) {
		int damage;
		int critical;
		int guard;

		// 乱数を使うための準備
		Random random = new Random();

		guard = random.nextInt(100);

		critical = random.nextInt(100);

		if (GetJob() == "戦士" && guard >= 70) {	// 相手の職業が戦士の場合、ガードが発動したか
			System.out.println("ガード成功!");
			damage = (GetSTR() - target.GetDEF()) / 2;	// ガードした場合、会心の一撃にならず、ダメージが2分の1に!
			} else {
			if (critical <= GetLUCK()) {	// 会心の一撃が出たか
				System.out.println("会心の一撃!");
				damage = GetSTR();	// 会心の一撃は攻撃力がそのままダメージに!
			} else {
				damage = GetSTR() - target.GetDEF();
			}

			if (damage < 0) {
				damage = 0;
			}
		}

		return damage;
	}

	/**
	 * 対象プレイヤー(target)に対して与える魔法ダメージを計算する
	 *
	 * @param target
	 *            : 対象プレイヤー
	 * @return ダメージ値(0～)
	 */
	protected int MagiDamage(Player target) {
		int damage;
		int guard;

		// 乱数を使うための準備
		Random random = new Random();

		guard = random.nextInt(100);

		// 魔法によるダメージ計算
		if (GetJob() == "戦士" && guard >= 70) {	// 相手の職業が戦士の場合、ガードが発動したか
			System.out.println("ガード成功!");
			damage = (GetSTR() - target.GetDEF()) / 2;	// ガードした場合、ダメージが2分の1に!
		} else {
			damage = random.nextInt(21) + 10;
		}

		return damage;
	}

	/**
	 * ダメージを受ける
	 *
	 * @param damage
	 *            damage : ダメージ値
	 */
	protected void Damage(int damage) {
		// ダメージ値分、HPを減少させる
		this.hp = Math.max(this.GetHP() - damage, 0);
	}

	/**
	 * 対象プレイヤー(target)に対して、ステータスを麻痺状態にする target : 対象プレイヤー
	 */
	protected void Paralysys(Player target) {
		// ステータスを麻痺状態にする
		this.state = "麻痺";
	}

	/**
	 * 対象プレイヤー(target)に対して、ステータスを毒状態にする target : 対象プレイヤー
	 */
	protected void Poisn(Player target) {
		// ステータスを毒状態にする
		this.state = "毒";
	}

	/**
	 * 自分(player)に対して、ステータスを通常状態にする player : 自分
	 */
	protected void Health(Player player) {
		System.out.println(this.name + "は、健康になった!");
		// ステータスを通常状態にする
		this.state = "通常";
	}

	/**
	 * 自分(player)に対して、所持品を空の状態にする player : 自分
	 */
	protected void Itembox(Player player) {
		// 所持品を空の状態にする
		this.item = "なし";
	}


	/**
	 * 対象プレイヤー(target)に対して、ステータスを死亡状態にする target : 対象プレイヤー
	 */
	protected void Dead(Player target) {
		// ステータスを死亡状態にする
		this.state = "死亡";
	}

	/**
	 * 対象プレイヤーが麻痺状態のとき
	 */
	protected int Freeze() {
		int freeze;

		// 乱数を使うための準備
		Random random = new Random();

		freeze = random.nextInt(100);

		return freeze;
	}

}

// プレイヤー：戦士
class Fighter extends Player {

	// =======================
	// コンストラクタ
	// =======================
	public Fighter(String name) {
		super(name);
	}

	/**
	 * 名前(name)からキャラクターに必要なパラメータを生成する
	 */
	@Override
	protected void makeCharacter() {
		// 戦士のパラメータを名前から生成する
		this.job = "戦士";
		this.hp = getNumber(name, 0) * 200 / 255 + 100;
		this.mp = getNumber(name, 1) * 0;
		this.str = getNumber(name, 2) * 70 / 255 + 30;
		this.def = getNumber(name, 3) * 70 / 255 + 30;
		this.luck = getNumber(name, 4) * 100 / 255;
		this.agi = getNumber(name, 5) * 49 / 255 + 1;
		this.maxhp = this.hp;
		this.state = "通常";
	}

	/**
	 * 対象プレイヤーに攻撃を行う
	 *
	 * @param defender
	 *            : 対象プレイヤー
	 */
	@Override
	public void attack(Player defender, Player attacker, Party friend, Party party, String strategyname) {

		// ステータスが毒または麻痺状態で、所持品に解毒薬がある場合
		if (attacker.GetSTATE() == "毒" && attacker.GetSTATE() == "麻痺") {
			if (attacker.GetITEM() == "解毒薬") {
				System.out.println(attacker.GetName() + "は、解毒薬を使った！");
				attacker.Health(attacker);
				attacker.Itembox(attacker);
				return;
			}
		}

		// 体力が減っていて、所持品が薬草の場合
		if (attacker.GetHP() < attacker.GetMAXHP() && attacker.GetITEM() == "薬草" ) {
			System.out.println(attacker.GetName() + "は、薬草を使った！");
			System.out.println(attacker.GetName() + "は、HPを30回復！");
			this.hp = Math.min(this.hp += 30, GetMAXHP()); // 上限を超えた場合は上限値に
			attacker.Itembox(attacker);
			return;
		}

		// ステータスが麻痺の場合、20%の確率で攻撃を行わない
		if (attacker.GetSTATE() == "麻痺") {
			int freeze = Freeze();

			if (0 <= freeze && freeze <= 19) {
				System.out.println(attacker.GetName() + "は、体がしびれて動けない!");
				return;
			}
		}

		// 与えるダメージを求める
		System.out.println(attacker.GetName() + "の攻撃！");
		int damage = CalcDamage(defender);

		// 求めたダメージを対象プレイヤーに与える
		System.out.println(defender.GetName() + "に" + damage + "のダメージ！");
		defender.Damage(damage);

		// ステータスが毒の場合
		if (attacker.GetSTATE() == "毒") {
			System.out.println(this.name + "は毒により、20ダメージ!");
			this.hp -= 20;
			if (attacker.GetHP() <= 0) {
				attacker.Dead(attacker);
			}
		}

		// 倒れた判定
		if (defender.GetHP() <= 0) {
			System.out.println(defender.GetName() + "は力尽きた...");
			defender.Dead(defender);
		}
	}
}

// プレイヤー：魔法使い
class Wizard extends Player {
	private static ArrayList<Magic> magicList = new ArrayList<Magic>(); // インスタンス化した魔法クラスを格納しておくリスト
	private static Magic[] getMagic = new Magic[1];
	static Random rnd = new Random();

	// =======================
	// コンストラクタ
	// =======================
	public Wizard(String name) {
		super(name);
	}

	// =======================
	// protected メソッド
	/**
	 * 名前(name)からキャラクターに必要なパラメータを生成する
	 */
	@Override
	protected void makeCharacter() {
		// 魔法使いのパラメータを名前から生成する
		this.job = "魔法使い";
		this.hp = getNumber(name, 0) * 100 / 255 + 50;
		this.mp = getNumber(name, 1) * 50 / 255 + 30;
		this.str = getNumber(name, 2) * 50 / 255;
		this.def = getNumber(name, 3) * 50 / 255;
		this.luck = getNumber(name, 4) * 100 / 255;
		this.agi = getNumber(name, 5) * 40 / 255 + 20;
		this.maxhp = this.hp;
		this.state = "通常";
	}
	// =======================

	/**
	 * 対象プレイヤーに攻撃を行う
	 *
	 * @param defender
	 *            : 対象プレイヤー
	 */
	@Override
	public void attack(Player defender, Player attacker, Party friend, Party party, String strategyname) {
		makeMagicList();

		// 乱数を使うための準備
		Random random = new Random();

		// 攻撃の種類を計算
		int t = random.nextInt(99) + 1;

		// ステータスが毒または麻痺状態で、所持品に解毒薬がある場合
		if (attacker.GetSTATE() == "毒" && attacker.GetSTATE() == "麻痺") {
			if (attacker.GetITEM() == "解毒薬") {
				System.out.println(attacker.GetName() + "は、解毒薬を使った！");
				attacker.Health(attacker);
				attacker.Itembox(attacker);
				return;
			}
		}

		// 体力が減っていて、所持品が薬草の場合
		if (attacker.GetHP() < attacker.GetMAXHP() && attacker.GetITEM() == "薬草" ) {
			System.out.println(attacker.GetName() + "は、薬草を使った！");
			System.out.println(attacker.GetName() + "は、HPを30回復！");
			this.hp = Math.min(this.hp += 30, GetMAXHP()); // 上限を超えた場合は上限値に
			attacker.Itembox(attacker);
			return;
		}

		// ステータスが麻痺の場合、20%の確率で攻撃を行わない
		if (attacker.GetSTATE() == "麻痺") {
			int freeze = Freeze();

			if (0 <= freeze && freeze <= 19) {
				System.out.println(attacker.GetName() + "は、体がしびれて動けない!");
				return;
			}
		}

		// 作戦が4の場合
		if (strategyname == "魔法は節約"){
			// MPがあれば、3分の1の確率で魔法攻撃する
			if (attacker.GetMP() >= 10 && t <= 33) {
				getMagic[0] = magicList.get(rnd.nextInt(magicList.size()));
				System.out.println(attacker.GetName() + "は" + getMagic[0].MagiName()
						+ "を唱えた!");
				int damage = MagiDamage(defender);

				// 求めたダメージを対象プレイヤーに与える
				System.out.println(defender.GetName() + "に" + damage + "のダメージ！");
				defender.Damage(damage);

				// 唱えた魔法に対しての、MPを消費する
				this.mp -= getMagic[0].ConsMP();

			} else { // MPがなければ、通常攻撃を行う
				System.out.println(attacker.GetName() + "の攻撃！");
				int damage = CalcDamage(defender);

				// 求めたダメージを対象プレイヤーに与える
				System.out.println(defender.GetName() + "に" + damage + "のダメージ！");
				defender.Damage(damage);
			}
		}else if(strategyname == "バランスが大事"){	// 作戦が5の場合
			// MPがあれば、2分の1の確率で魔法攻撃する
			if (attacker.GetMP() >= 10 && t <= 50) {
				getMagic[0] = magicList.get(rnd.nextInt(magicList.size()));
				System.out.println(attacker.GetName() + "は" + getMagic[0].MagiName()
						+ "を唱えた!");
				int damage = MagiDamage(defender);

				// 求めたダメージを対象プレイヤーに与える
				System.out.println(defender.GetName() + "に" + damage + "のダメージ！");
				defender.Damage(damage);

				// 唱えた魔法に対しての、MPを消費する
				this.mp -= getMagic[0].ConsMP();

			} else { // MPがなければ、通常攻撃を行う
				System.out.println(attacker.GetName() + "の攻撃！");
				int damage = CalcDamage(defender);

				// 求めたダメージを対象プレイヤーに与える
				System.out.println(defender.GetName() + "に" + damage + "のダメージ！");
				defender.Damage(damage);
		}
		}else { // 作戦がそれ以外の場合
			// MPがあれば魔法で攻撃する
			if (attacker.GetMP() >= 10) {
				getMagic[0] = magicList.get(rnd.nextInt(magicList.size()));
				System.out.println(attacker.GetName() + "は" + getMagic[0].MagiName()
						+ "を唱えた!");
				int damage = MagiDamage(defender);

				// 求めたダメージを対象プレイヤーに与える
				System.out.println(defender.GetName() + "に" + damage + "のダメージ！");
				defender.Damage(damage);

				// 唱えた魔法に対しての、MPを消費する
				this.mp -= getMagic[0].ConsMP();

			} else { // MPがなければ、通常攻撃を行う
				System.out.println(attacker.GetName() + "の攻撃！");
				int damage = CalcDamage(defender);

				// 求めたダメージを対象プレイヤーに与える
				System.out.println(defender.GetName() + "に" + damage + "のダメージ！");
				defender.Damage(damage);
		}
		}

		// ステータスが毒の場合
		if (attacker.GetSTATE() == "毒") {
			System.out.println(this.name + "は毒により、20ダメージ!");
			this.hp -= 20;
			if (attacker.GetHP() <= 0) {
				attacker.Dead(attacker);
			}
		}

		// 倒れた判定
		if (defender.GetHP() <= 0) {
			System.out.println(defender.GetName() + "は力尽きた...");
			defender.Dead(defender);
		}
	}

	// =======================
	// private メソッド
	/**
	 * 魔法一覧を作成するメソッド magicListにMagicをインスタンス化して詰め込む
	 */
	private static void makeMagicList() {
		magicList.add(new Magic("ファイア", 10, 10));
		magicList.add(new Magic("サンダー", 10, 20));
	}
	// =======================
}

// プレイヤー：僧侶
class Priest extends Player {
	private static ArrayList<Magic> magicList = new ArrayList<Magic>(); // インスタンス化した魔法クラスを格納しておくリスト
	private static Magic[] getMagic = new Magic[1];
	static Random rnd = new Random();


	// =======================
	// コンストラクタ
	// =======================
	public Priest(String name) {
		super(name);
	}

	// =======================
	// protected メソッド
	/**
	 * 名前(name)からキャラクターに必要なパラメータを生成する
	 */
	@Override
	protected void makeCharacter() {
		// 僧侶のパラメータを名前から生成する
		this.job = "僧侶";
		this.hp = getNumber(name, 0) * 120 / 255 + 80;
		this.mp = getNumber(name, 1) * 30 / 255 + 20;
		this.str = getNumber(name, 2) * 60 / 255 + 10;
		this.def = getNumber(name, 3) * 60 / 255 + 10;
		this.luck = getNumber(name, 4) * 100 / 255;
		this.agi = getNumber(name, 5) * 40 / 255 + 20;
		this.maxhp = this.hp;
		this.state = "通常";
	}
	// =======================

	/**
	 * 対象プレイヤーに攻撃を行う
	 *
	 * @param defender
	 *            : 対象プレイヤー
	 */
	@Override
	public void attack(Player defender, Player attacker, Party friend, Party party, String strategyname) {
		makeMagicList();

		// ステータスが毒または麻痺状態で、所持品に解毒薬がある場合
		if (attacker.GetSTATE() == "毒" && attacker.GetSTATE() == "麻痺") {
			if (attacker.GetITEM() == "解毒薬") {
				System.out.println(attacker.GetName() + "は、解毒薬を使った！");
				attacker.Health(attacker);
				attacker.Itembox(attacker);
				return;
			}
		}

		// 体力が減っていて、所持品が薬草の場合
		if (attacker.GetHP() < attacker.GetMAXHP() && attacker.GetITEM() == "薬草" ) {
			System.out.println(attacker.GetName() + "は、薬草を使った！");
			System.out.println(attacker.GetName() + "は、HPを30回復！");
			this.hp = Math.min(this.hp += 30, GetMAXHP()); // 上限を超えた場合は上限値に
			attacker.Itembox(attacker);
			return;
		}

		// ステータスが麻痺の場合、20%の確率で攻撃を行わない
		if (attacker.GetSTATE() == "麻痺") {
			int freeze = Freeze();

			if (0 <= freeze && freeze <= 19) {
				System.out.println(attacker.GetName() + "は、体がしびれて動けない!");
				return;
			}
		}

		int x = 0;

		// パーティ内で最もHPが減っている人を算出
		for(int i = 0; i < friend.size(); i++){
			if(friend.get(i).GetHP() < friend.get(i).GetMAXHP()){
				if(friend.get(i).GetHP() < friend.get(x).GetHP()){
					x = i;
				}
			}
		}

		// パーティ内でHPが減っていれば魔法で回復する
		if (friend.get(x).GetHP() < friend.get(x).GetMAXHP() && attacker.GetMP() >= 10) {

			getMagic[0] = magicList.get(rnd.nextInt(magicList.size()));
			System.out.println(attacker.GetName() + "は" + getMagic[0].MagiName()
					+ "を唱えた!");
			String nowmagic = getMagic[0].MagiName();

			// ヒールの場合、自パーティ1人のHPを回復する
			if (nowmagic == "ヒール") {
				System.out.println(friend.get(x).GetName() + "は、HPを" + getMagic[0].Effect()
						+ "回復！");
				friend.get(x).hp = Math.min(friend.get(x).hp + getMagic[0].Effect(), GetMAXHP()); // 上限を超えた場合は上限値に
			} else if (nowmagic == "パライズ") {
				System.out.println(defender.GetName() + "は麻痺した!");
				defender.Paralysys(defender);
			} else {
				System.out.println(defender.GetName() + "は毒に侵された!");
				defender.Poisn(defender);
			}

			// 唱えた魔法に対しての、MPを消費する
			this.mp -= getMagic[0].ConsMP();
		} else { // HPが減っていなければ、通常攻撃を行う
			System.out.println(attacker.GetName() + "の攻撃！");
			int damage = CalcDamage(defender);

			// 求めたダメージを対象プレイヤーに与える
			System.out.println(defender.GetName() + "に" + damage + "のダメージ！");
			defender.Damage(damage);
		}

		// ステータスが毒の場合
		if (attacker.GetSTATE() == "毒") {
			System.out.println(this.name + "は毒により、20ダメージ!");
			this.hp -= 20;
			if (attacker.GetHP() <= 0) {
				attacker.Dead(attacker);
			}
		}

		// 倒れた判定
		if (defender.GetHP() <= 0) {
			System.out.println(defender.GetName() + "は力尽きた...");
			defender.Dead(defender);
		}
	}

	// =======================
	// private メソッド
	/**
	 * 魔法一覧を作成するメソッド magicListにMagicをインスタンス化して詰め込む
	 */
	private static void makeMagicList() {
		magicList.add(new Magic("ヒール", 50, 20));
		magicList.add(new Magic("パライズ", 20, 10));
		magicList.add(new Magic("ポイズン", 20, 10));
	}
	// =======================

}

// プレイヤー：勇者
class Brave extends Player {
	private static ArrayList<Magic> magicList = new ArrayList<Magic>(); // インスタンス化した魔法クラスを格納しておくリスト
	private static Magic[] getMagic = new Magic[1];
	static Random rnd = new Random();

	// =======================
	// コンストラクタ
	public Brave(String name) {
		super(name);
	}
	// =======================

	// =======================
	// protected メソッド
	/**
	 * 名前(name)からキャラクターに必要なパラメータを生成する
	 */
	@Override
	protected void makeCharacter() {
		// 勇者のパラメータを名前から生成する
		this.job = "勇者";
		this.hp = getNumber(name, 0) * 100 / 255 + 100;
		this.mp = getNumber(name, 1) * 50 / 255 + 30;
		this.str = getNumber(name, 2) * 50 / 255 + 30;
		this.def = getNumber(name, 3) * 50 / 255 + 30;
		this.luck = getNumber(name, 4) * 100 / 255;
		this.agi = getNumber(name, 5) * 40 / 255 + 20;
		this.maxhp = this.hp;
		this.state = "通常";
	}
	// =======================

	/**
	 * 対象プレイヤーに攻撃を行う
	 *
	 * @param defender
	 *            : 対象プレイヤー
	 */
	@Override
	public void attack(Player defender, Player attacker, Party friend, Party party, String strategyname) {
		makeMagicList();

		// 乱数を使うための準備
		Random random = new Random();

		// 攻撃の種類を計算
		int t = random.nextInt(99) + 1;

		// ステータスが毒または麻痺状態で、所持品に解毒薬がある場合
		if (attacker.GetSTATE() == "毒" && attacker.GetSTATE() == "麻痺") {
			if (attacker.GetITEM() == "解毒薬") {
				System.out.println(attacker.GetName() + "は、解毒薬を使った！");
				attacker.Health(attacker);
				attacker.Itembox(attacker);
				return;
			}
		}

		// 体力が減っていて、所持品が薬草の場合
		if (attacker.GetHP() < attacker.GetMAXHP() && attacker.GetITEM() == "薬草" ) {
			System.out.println(attacker.GetName() + "は、薬草を使った！");
			System.out.println(attacker.GetName() + "は、HPを30回復！");
			this.hp = Math.min(this.hp += 30, GetMAXHP()); // 上限を超えた場合は上限値に
			attacker.Itembox(attacker);
			return;
		}

		// ステータスが麻痺の場合、20%の確率で攻撃を行わない
		if (attacker.GetSTATE() == "麻痺") {
			int freeze = Freeze();

			if (0 <= freeze && freeze <= 19) {
				System.out.println(attacker.GetName() + "は、体がしびれて動けない!");
				return;
			}
		}

		// 作戦が4の場合
		if (strategyname == "魔法は節約"){
			// MPがあれば、3分の1の確率で魔法攻撃する
			if (attacker.GetMP() >= 10 && t <= 33) {
				getMagic[0] = magicList.get(rnd.nextInt(magicList.size()));
				System.out.println(attacker.GetName() + "は" + getMagic[0].MagiName()
						+ "を唱えた!");
				int damage = MagiDamage(defender);

				// 求めたダメージを対象プレイヤーに与える
				System.out.println(defender.GetName() + "に" + damage + "のダメージ！");
				defender.Damage(damage);

				// 唱えた魔法に対しての、MPを消費する
				this.mp -= getMagic[0].ConsMP();

			} else { // MPがなければ、通常攻撃を行う
				System.out.println(attacker.GetName() + "の攻撃！");
				int damage = CalcDamage(defender);

				// 求めたダメージを対象プレイヤーに与える
				System.out.println(defender.GetName() + "に" + damage + "のダメージ！");
				defender.Damage(damage);
			}
		}else if(strategyname == "バランスが大事"){	// 作戦が5の場合
			// MPがあれば、2分の1の確率で魔法攻撃する
			if (attacker.GetMP() >= 10 && t <= 50) {
				getMagic[0] = magicList.get(rnd.nextInt(magicList.size()));
				System.out.println(attacker.GetName() + "は" + getMagic[0].MagiName()
						+ "を唱えた!");
				int damage = MagiDamage(defender);

				// 求めたダメージを対象プレイヤーに与える
				System.out.println(defender.GetName() + "に" + damage + "のダメージ！");
				defender.Damage(damage);

				// 唱えた魔法に対しての、MPを消費する
				this.mp -= getMagic[0].ConsMP();

			} else { // MPがなければ、通常攻撃を行う
				System.out.println(attacker.GetName() + "の攻撃！");
				int damage = CalcDamage(defender);

				// 求めたダメージを対象プレイヤーに与える
				System.out.println(defender.GetName() + "に" + damage + "のダメージ！");
				defender.Damage(damage);
		}
		}else { // 作戦がそれ以外の場合
			// MPがあれば魔法で攻撃する
			if (attacker.GetMP() >= 10) {
				getMagic[0] = magicList.get(rnd.nextInt(magicList.size()));
				System.out.println(defender.GetName() + "は" + getMagic[0].MagiName()
						+ "を唱えた!");
				int damage = MagiDamage(defender);

				// 求めたダメージを対象プレイヤーに与える
				System.out.println(defender.GetName() + "に" + damage + "のダメージ！");
				defender.Damage(damage);

				// 唱えた魔法に対しての、MPを消費する
				this.mp -= getMagic[0].ConsMP();

			} else { // MPがなければ、通常攻撃を行う
				System.out.println(attacker.GetName() + "の攻撃！");
				int damage = CalcDamage(defender);

				// 求めたダメージを対象プレイヤーに与える
				System.out.println(defender.GetName() + "に" + damage + "のダメージ！");
				defender.Damage(damage);
		}
		}

		// ステータスが毒の場合
		if (attacker.GetSTATE() == "毒") {
			System.out.println(this.name + "は毒により、20ダメージ!");
			this.hp -= 20;
			if (attacker.GetHP() <= 0) {
				attacker.Dead(attacker);
			}
		}

		// 倒れた判定
		if (defender.GetHP() <= 0) {
			System.out.println(defender.GetName() + "は力尽きた...");
			defender.Dead(defender);
		}
	}

	// =======================
	// private メソッド
	/**
	 * 魔法一覧を作成するメソッド magicListにMagicをインスタンス化して詰め込む
	 */
	private static void makeMagicList() {
		magicList.add(new Magic("ファイア", 10, 10));
	}
	// =======================
}
