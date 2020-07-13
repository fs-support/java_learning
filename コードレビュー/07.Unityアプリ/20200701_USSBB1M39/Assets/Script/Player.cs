using System;
using System.Text;
using System.Collections;
using System.Collections.Generic;
using System.Security.Cryptography;
using UnityEngine;

public class Player
{
	// =======================
	// フィールド変数
	// =======================
	// 名前
	protected string name;
	// HP
	protected int hp;
	protected int defaultHP;
	// MP
	protected int mp;
	protected int defaultMP;
	// 攻撃力
	protected int str;

	// 防御力
	protected int def;

	// 幸運
	protected int luck;

	//速度
	protected int agi;

	// 麻痺
	protected bool paralyze;

	protected int paralyzeTurn;

	// 毒
	protected bool poison;

	//行動できるかどうか
	protected bool active;

	protected Strategy strategy;

	// 所属しているパーティー
	protected Party myParty;

	// =======================
	// コンストラクタ
	// =======================

	/// <summary>
	/// コンストラクタ
	/// </summary>
	/// <param name="name">プレイヤー名</param>
	public Player(string name)
	{
		this.name = name;
		this.strategy = new DefaultStrategy();
		// キャラクターのパラメータ生成
		MakeCharacter();
	}

	// =======================
	// Getter / Setter
	// =======================
	/// <summary>
	/// プレイヤー名を取得する
	/// </summary>
	/// <returns>プレイヤー名</returns>
	public string GetName()
	{
		return this.name;
	}

	/// <summary>
	/// 現在HPを取得する
	/// </summary>
	/// <returns> 現在HP</returns>
	public int GetHP()
	{

		return this.hp;
	}

	/// <summary>
	/// 最大HPを取得する
	/// </summary>
	/// <returns>最大HP</returns>
	public int GetDefaultHP()
	{
		return this.defaultHP;
	}

	/// <summary>
	/// 現在MPを取得する
	/// </summary>
	/// <returns>現在MP</returns>
	public int GetMP()
	{
		return this.mp;
	}

	/// <summary>
	/// 最大MPを取得する
	/// </summary>
	/// <returns>最大MP</returns>
	public int GetDefaultMP()
	{
		return this.defaultMP;
	}

	/// <summary>
	/// 攻撃力を取得する
	/// </summary>
	/// <returns>攻撃力</returns>
	public int GetSTR()
	{
		return this.str;
	}

	/// <summary>
	/// 防御力を取得する
	/// </summary>
	/// <returns></returns>
	public int GetDEF()
	{
		return this.def;
	}

	/// <summary>
	/// 幸運値を取得する
	/// </summary>
	/// <returns></returns>
	public int GetLUCK()
	{
		return this.luck;
	}

	/// <summary>
	/// 速さを取得する
	/// </summary>
	/// <returns>速さ</returns>
	public int GetAGI()
	{
		return this.agi;
	}


	/// <summary>
	/// 麻痺状態になっているかどうか
	/// </summary>
	/// <returns>true：麻痺状態</returns>
	public bool isParalyze()
	{
		return this.paralyze;
	}
	/// <summary>
	/// 行動できるかどうか
	/// </summary>
	/// <returns>true : 行動可能</returns>
	public bool isActive()
	{
		return active;
	}

	/// <summary>
	/// 毒状態になっているかどうか
	/// </summary>
	/// <returns>true : 毒状態</returns>
	public bool isPoison()
	{
		return this.poison;
	}

	/// <summary>
	/// 麻痺状態であるか設定する
	/// </summary>
	/// <param name="paralyze">true：麻痺状態<br></br>false : 麻痺状態でない</param>
	public void SetParalyze(bool paralyze)
	{
		this.paralyze = paralyze;
		this.paralyzeTurn = 5;
	}

	/// <summary>
	/// 麻痺状態が回復する
	/// </summary>
	public void RecoveryParalyze()
	{
		this.paralyze = false;
	}

	/// <summary>
	/// 麻痺状態が残り何ターン続くか
	/// </summary>
	/// <returns>残り何ターン数</returns>
	public int GetParalyzeTurn()
	{
		return this.paralyzeTurn;
	}

	/// <summary>
	/// 麻痺状態の残りターン数を減らす
	/// </summary>
	public void ChangeParalyzeTurn()
	{
		if (paralyzeTurn > 0)
		{
			this.paralyzeTurn--;
		}
	}

	/// <summary>
	/// 毒状態であるか設定する
	/// </summary>
	/// <param name="poison">true：毒状態<br></br>false : 毒状態でない</param>
	public void SetPoison(bool poison)
	{
		this.poison = poison;
	}




	/// <summary>
	/// 自分のパーティーを取得する
	/// </summary>
	/// <returns>自分のパーティー</returns>
	public Party GetMyParty()
	{
		return this.myParty;
	}


	/// <summary>
	/// 自分がどのパーティーに所属しているか設定する
	/// </summary>
	/// <param name="myParty">自身の属するパーティー</param>
	public void SetMyParty(Party myParty)
	{
		this.myParty = myParty;

	}

	/// <summary>
	/// 自分のパーティーの作戦を設定する
	/// </summary>
	/// <param name="strategy">自パーティーの作戦</param>
	public void SetStrategy(Strategy strategy)
	{
		this.strategy = strategy;
	}




	// =======================
	// protected メソッド
	// =======================
	/// <summary>
	/// 名前(name)からキャラクターに必要なパラメータを生成する<br></br>
	/// ジョブごとにオーバーライド
	/// </summary>
	protected virtual void MakeCharacter()
	{
		// ジョブごとにオーバーライドして処理を記述してください
	}

	/// <summary>
	/// 名前(name)からハッシュ値を生成し、指定された位置の数値を取り出す
	/// </summary>
	/// <param name="index">指定位置</param>
	/// <param name="max">最大値(内部的に0～255の値を生成するが、0～maxまでの値に補正)</param>
	/// <returns>数値(0～max) ※maxも含む</returns>
	protected int GetNumber(int index, int max)
	{
		Debug.Log("GetNumberが呼び出されました");
		try
		{
			Debug.Log(this.name);

			// バイト配列に
			byte[] byteValue = Encoding.UTF8.GetBytes(this.name);

			// ハッシュ値の取得
			SHA1 crypto = new SHA1CryptoServiceProvider();
			byte[] hashValue = crypto.ComputeHash(byteValue);

			StringBuilder hashedText = new StringBuilder();
			for (int i = 0; i < hashValue.Length; i++)
			{
				hashedText.AppendFormat("{0:x2}", hashValue[i]);
			}
			Debug.Log("ハッシュ値：" + hashedText.ToString());
			Debug.Log("文字数：" + hashedText.Length.ToString());

			String hex = hashedText.ToString().Substring(index * 2, 2);
			Debug.Log(hex);

			int value = Int16.Parse(hex, System.Globalization.NumberStyles.HexNumber);
			Debug.Log(value);
			return value * max / 255;
		}
		catch (Exception e)
		{
			// エラー
			Console.WriteLine("例外をキャッチしました");
			Debug.Log("例外をキャッチしました");
			Console.WriteLine(e);
		}
		return 0;
	}

	// =======================
	// private メソッド
	// =======================

	// =======================
	// public メソッド
	// =======================

	/// <summary>
	/// 現在のステータスを表示する
	/// </summary>
	/// <returns></returns>
	public string PrintStatus()
	{
		string status = "";
		status = string.Format("{0}(HP={1}/{2} : MP={3}/{4} : STR={5} : DEF={6} : LUCK={7} : AGI={8})", this.name, this.hp, this.defaultHP, this.mp, this.defaultMP, this.str, this.def, this.luck, this.agi);

		return status;
	}

	/// <summary>
	/// Playerの行動を作戦に応じて決定し実行する
	/// </summary>
	/// <param name="activePlayer"></param>
	/// <param name="enemyParty"></param>
	public void Action(Player activePlayer, Party enemyParty)
	{
		Debug.Log("Player Action ");
		this.strategy.StrategyAction(activePlayer, activePlayer.GetMyParty(), enemyParty);

	}

	/// <summary>
	/// 対象パーティー(passiveParty)に攻撃を行う
	/// </summary>
	/// <param name="activePlayer">能動側プレイヤー</param>
	/// <param name="passiveParty">受動側パーティー</param>
	public virtual void Attack(Player activePlayer, List<Player> passiveParty)
	{
		// ジョブごとにオーバーライドして処理を記述してください
	}

	/// <summary>
	/// 対象パーティー(passiveParty)に回復を行う
	/// </summary>
	/// <param name="activePlayer">能動側プレイヤー</param>
	/// <param name="passiveParty">受動側パーティー</param>
	/// <returns></returns>
	public virtual bool HealHP(Player activePlayer, List<Player> passiveParty)
	{
		// ジョブごとにオーバーライドして処理を記述してください
		return false;
	}

	public virtual bool HealDebuff(Player ActivePlayer, Player passivePlayer)
	{
		// ジョブごとにオーバーライドして処理を記述してください
		return false;
	}

	/// <summary>
	/// 対象プレイヤー(passivePlayer)に状態異常魔法を使う
	/// </summary>
	/// <param name="activePlayer">能動側プレイヤー</param>
	/// <param name="passivePlayer">受動側プレイヤー</param>
	/// <returns></returns>
	public bool Debuff(Player activePlayer, Player passivePlayer)
	{

		return false;
	}


	/// <summary>
	/// 対象プレイヤー(target)に対して与えるダメージを計算する
	/// </summary>
	/// <param name="target">対象プレイヤー</param>
	/// <returns>ダメージ値(0～)</returns>
	public int CalcDamage(Player target)
	{
		int critical = UnityEngine.Random.Range(1, 100);
		int damage;
		// 剣を持っているかどうか

		// 会心の一撃かどうか
		if (GetLUCK() > critical)
		{

			LogText.AddLog("会心の一撃！");

			return GetSTR();

		}

		damage = str - target.GetDEF();

		if (damage < 0)
		{
			return 0;
		}
		return damage;

	}

	/// <summary>
	/// ダメージを受ける
	/// </summary>
	/// <param name="damage">ダメージ値</param>
	public void Damage(int damage)
	{
		// ダメージ値分、HPを減少させる
		this.hp = Math.Max(this.hp - damage, 0);
	}


	/// <summary>
	/// HPを回復する
	/// </summary>
	/// <param name="recoverHP">回復値</param>
	public void RecoverHP(int recoverHP)
	{
		// 回復量分、HPを上昇させる
		this.hp = Math.Min(this.hp + recoverHP, this.defaultHP);

	}



	/// <summary>
	/// MPを消費する
	/// </summary>
	/// <param name="usemp"> 消費するMPの値</param>
	public void UseMP(int usemp)
	{

		// 消費MP分、MPを減少させる
		this.mp = (Math.Max(this.mp - usemp, 0));
	}


	/// <summary>
	/// 毒状態になっている場合の処理
	/// </summary>
	public void ProcessPoison()
	{
		if (this.poison)
		{
			this.Damage(20);
		}
	}


	/// <summary>
	/// PlayerのHPがゼロになったときに表示する
	/// </summary>
	public void Down()
	{
		if (this.hp == 0)
		{
			LogText.AddLog(string.Format("{0}は力尽きた...", this.name));
		}
	}

	/// <summary>
	/// 行動できる状態にあるか確認する
	/// </summary>
	/// <returns>true：行動可能<br></br>false : 行動不能</returns>
	public bool GetActive()
	{
		return this.active;
	}


	public void ChangeActive(bool active)
	{
		this.active = active;
	}

	/// <summary>
	/// ステータスを初期状態に戻す
	/// </summary>
	public void ResetStatus()
	{
		this.hp = this.defaultHP;
		this.mp = this.defaultMP;
		this.paralyze = false;
		this.paralyzeTurn = 0;
		this.poison = false;

		this.active = true;
	}
}