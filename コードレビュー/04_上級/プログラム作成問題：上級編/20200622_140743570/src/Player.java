import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Random;

public class Player {
	private String job; // 名前
	private String name; // 名前
	private int hp; // 体力
	private int mp; //魔力
	private int str; // 力
	private int def; // 防御
	private int luck; // 幸運
	private int agi; // 敏捷性
	private String State_P;//毒状態
	private String State_S;//麻痺状態
	private int hp_m; // 体力最大値
	private int mp_m; //魔力最大値
	Player()
	{
	}
	Player(String job,String name, int hp, int mp , int str, int def, int luck, int agi)
	{

		this.job=job;
		this.name=name;
		this.hp=hp;
		this.mp=mp;
		this.str=str;
		this.def=def;
		this.luck=luck;
		this.agi=agi;

	}
	// 各種ステータスを取得
	String Job()
	{
	return job;
	}

	String Name()
	{
	return name;
	}

	int Hp()
	{
	return hp;
	}

	int Mp()
	{
	return mp;
	}

	int Str()
	{
	return str;
	}

	int Def()
	{
	return def;
	}

	int Luck()
	{
	return luck;
	}

	int Agi()
	{
	return agi;
	}

	//魔力消費
	void mp_Consumption(int i )
	{
		mp=mp-i;
	}

	//回復
	void Healing_state( )
	{
		hp=hp+50;
	}

	//毒
	void Poison_state( )
	{
		hp=hp-20;
	}

	//毒状態
	void State_P(String s)
	{
		State_P=s;
	}

	//麻痺状態
	void State_S(String s)
	{
		State_S=s;
	}


	//毒状態取得
	String State_p()
	{
		return State_P;
	}

	//麻痺状態取得
	String State_s()
	{
		return State_S;
	}

	// 体力最大値
	void Hp_m(int s)
	{
		hp_m=s;
	}

	// 魔力最大値
	void Mp_m(int s)
	{
		mp_m=s;
	}

	//魔力最大値取得
	int Mp_ms()
	{
		return mp_m;
	}

	//体力最大値得
	int Hp_ms()
	{
		return hp_m;
	}





	//攻撃
	void Attack_d(int Damage )
	{

		hp=hp-Damage;
	}

	// 先手判断　取得
	protected static void Attack_S(Player Player1,Player Player2){
		if(Player1.agi>Player2.agi) {
			//麻痺判定
			if(Player1.State_s()=="麻痺") {
				System.out.println(Player1.Name()+"は行動不能になり攻撃できない！！");
				Player1.State_S("");

			}else {
				System.out.println("\n"+Player1.Name()+"のターン");
				PlayerJobAttack(Player1.Job(),Player1, Player2);
			}


			if(Player2.State_s()=="麻痺") {
				System.out.println("\n"+Player2.Name()+"は行動不能になり攻撃できない！！");
				Player1.State_S("");
			}else {
				System.out.println("\n"+Player2.Name()+"のターン");
				PlayerJobAttack(Player2.Job(),Player2, Player1);
			}

		}else {

			if(Player2.State_s()=="麻痺") {
				System.out.println("\n"+Player2.Name()+"は行動不能になり攻撃できない！！");
				Player1.State_S("");
			}else {
				System.out.println("\n"+Player2.Name()+"のターン");
				PlayerJobAttack(Player2.Job(),Player2, Player1);
			}


			if(Player1.State_s()=="麻痺") {
				System.out.println("\n"+Player1.Name()+"は行動不能になり攻撃できない！！");
				Player1.State_S("");
			}else {
				System.out.println("\n"+Player1.Name()+"のターン");
				PlayerJobAttack(Player1.Job(),Player1, Player2);
			}

		}
	}

// 攻撃　取得
protected static void Attack(Player Player1,Player Player2){
	int 	Damage;
	Random ra=new Random();
	int d=ra.nextInt(100);
	System.out.println("\n\n通常攻撃！！");

	//会心の一撃判定
	if(Player1.luck >= d) {
		Player2.Attack_d(Player1.str);
		System.out.println(Player1.name+"が"+Player2.name+"に会心の一撃を決め"+Player1.str+"のダメージを与えた！！");

	}else {

		//通常攻撃判定
		if(Player2.def-Player1.str>0) {
			Damage=	Player2.def-Player1.str;
			System.out.println(Player1.name+"が"+Player2.name+"に"+(Player2.def-Player1.str)+"のダメージを与えた！！");
			Player2.Attack_d(Damage);

		}else {
				System.out.println(Player1.name+"の攻撃がはじかれた！！");
			}
		}
	}
//職業別攻撃判定
public static void PlayerJobAttack(String job,Player Player1,Player Player2) {
	switch (job) {
	case "戦士":

		Fighter.Fighter_Attack(Player1, Player2);
		break;

	case "魔法使い":


		Wizard.Magic(Player1,Player2);
		break;

	case "僧侶":


		Priest.Magic(Player1,Player2);
		break;


	case "盾使い":

		Shield.Shield_Attack(Player1, Player2);
		break;
	}

}

//ハッシュ値

static Integer GetNumber (String name, Integer index)
		{
			try {
				byte[] result =MessageDigest.getInstance ("SHA-1").digest( name.getBytes());
				String digest = String.format ("%040x", new BigInteger (1,result));
				String hex =digest.substring (index * 2, index * 2 +2);
				return Integer.parseInt (hex,16);
			} catch (Exception e){
				e.printStackTrace();
			}
			return 0;
		}
}
