package Data1;
//履歴クラス
public class EatItemH {
	// ---------------------------------------------------------
	// フィールド変数
	// ---------------------------------------------------------
	private int hp;//体力
	private String name; // アイテム名
	private int danger; // 危険度
	private String eat; //食べたかどうか
	// コンストラクタ
	EatItemH(int hp, String name, int danger, String eat) {
	this.hp = hp;
	this.name = name;
	this.danger = danger;
	this.eat = eat;
	}
	// ---------------------------------------------------------
	// メソッド
	// ---------------------------------------------------------
	// 体力
	int Hp()
	{
	return hp;
	}
	// アイテム名を取得する
	String Name()
	{
	return name;
	}
	// 危険度を取得する
	int Danger()
	{
	return danger;
	}
	// 食べたかどうか
	String Eat()
	{
	return eat;
	}

}
