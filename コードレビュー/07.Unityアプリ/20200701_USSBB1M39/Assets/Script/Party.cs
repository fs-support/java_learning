using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using System.Linq;


public class Party
{
	// =======================
	// フィールド変数
	// =======================
	// パーティーの情報が入ったリスト
	private List<Player> members;
	private Strategy myStrategy;

	// =======================
	// コンストラクタ
	// =======================
	public Party()
	{
		this.members = new List<Player>();
	}

	public Party(Party source)
	{
		this.members = source.members;
		this.myStrategy = source.myStrategy;

		foreach (Player p in this.members)
		{
			p.ResetStatus();
		}

	}


	// =======================
	// Getter / Setter
	// =======================

	/// <summary>
	/// パーティーメンバーを List<Player> で取得する
	/// </summary>
	/// <returns>パーティーメンバー</returns>
	public List<Player> GetMembers()
	{

		return this.members;

	}

	/// <summary>
	/// 指定したインデックスのプレイヤーを返す
	/// </summary>
	/// <param name="index">パーティーメンバーのリスト(members)の要素数</param>
	/// <returns>indexで指定したプレイヤー</returns>
	public Player GetPlayer(int index)
	{
		return members[index];
	}

	// =======================
	// protected メソッド
	// =======================
	// =======================
	// private メソッド
	// =======================
	// =======================
	// public メソッド
	// =======================

	/// <summary>
	/// パーティーにプレイヤーを追加する
	/// </summary>
	/// <param name="player">パーティーに追加するプレイヤー</param>
	public void AppendPlayer(Player player)
	{

		this.members.Add(player);

	}

	/// <summary>
	/// パーティー内のHPのあるプレイヤーをList<Player>で返す
	/// </summary>
	/// <returns>プレイヤーのリスト</returns>
	public List<Player> AttackTarget()
	{
		var result = from x in members
					 where x.GetHP() > 0
					 select x;

		return result.Distinct().ToList();
	}

	/// <summary>
	/// パーティー内でHP回復が必要なメンバーのリストを返す
	/// </summary>
	/// <param name="percent">回復基準</param>
	/// <returns></returns>
	public List<Player> HealMembers(int percent)
	{
		Debug.Log("Party HealMembers");

		var result = from x in members
					 where x.GetHP() < (x.GetDefaultHP() * percent / 100)
					 select x;

		return result.Distinct().ToList();
	}

	/// <summary>
	/// 未行動のPlayerの中で一番早いプレイヤーを返す
	/// </summary>
	/// <returns>agi値が最も高いプレイヤー</returns>
	public List<Player> FastestMembers()
	{
		Debug.Log("Party FastestMembers");
		var result = from x in members
					 where x.GetHP() >0
					 select x;
		
		result = from x in result
				 where x.GetActive()
				 select x;

		result = from a in result
					 orderby a.GetAGI() descending
					 select a;

		return result.Distinct().ToList();
	}

	/// <summary>
	/// 未行動のプレイヤーから、体力の低い順に並べ替えたリストを返す
	/// </summary>
	/// <returns></returns>
	public List<Player> WeakMembers()
	{
		var result = from x in members
					 where x.GetActive()
					 select x;

		result = from a in result
				 orderby a.GetHP()
				 select a;

		return result.Distinct().ToList();
	}

	/// <summary>
	/// パーティーに魔法使いがいる場合、魔法使いのみのリストを返す
	/// パーティーに魔法使いがいない場合、攻撃可能なプレーヤーのリストを返す
	/// </summary>
	/// <returns></returns>
	public List<Player> WizardTarget()
	{
		var result = from x in members
					 where x is Wizard
					 select x;

		result = from a in members
				 orderby a.GetHP() > 0
				 select a;

		result = result.Distinct().ToList();

		if (result.Count() < 0)
		{

			result = AttackTarget();

			return result.Distinct().ToList();

		}

		return result.Distinct().ToList();
	}



	/// <summary>
	/// 対象プレイヤーがパーティーに所属しているかどうか
	/// </summary>
	/// <param name="player">対象プレイヤー</param>
	/// <returns></returns>
	public bool isExists(Player player)
	{
		return members.Contains(player);
	}

	/// <summary>
	/// 行動できる人間がいるかどうか判定する
	/// </summary>
	/// <returns></returns>
	public bool existsActivePlayer()
	{
		var result = from x in members
					 where x.GetActive()
					 select x;
		result = result.Distinct().ToList();

		if (result.Count() >0)
		{
			Debug.Log(string.Format("行動できる人間は {0} 人",result.Count()));
			return true;
		}

		return false;

	}

	/// <summary>
	/// パーティーが全滅しているかどうか判定する
	/// </summary>
	/// <returns>true : 全滅, false : 生存者あり</returns>
	public bool isLose()
	{
		var result = from x in members
					 where x.GetHP() > 0
					 select x;
		result = result.Distinct().ToList();

		if (result.Count() >= 1)
		{
			return false;
		}

		return true;
	}


	/// <summary>
	/// 作戦をパーティーメンバーに伝える
	/// </summary>
	/// <param name="strategy">作戦</param>
	public void ChangeStrategy(Strategy strategy)
	{
		Debug.Log("Party ChangeStrategy");
		foreach (Player p in this.members)
		{
			p.SetStrategy(strategy);
		}
		this.myStrategy = strategy;
	}

	/// <summary>
	/// 麻痺・毒状態ではないプレイヤーのリストを返す
	/// </summary>
	/// <returns></returns>
	public List<Player> DebuffMembers()
	{
		var result = from x in members
					 where !(x.isParalyze()) | !(x.isPoison())
					 select x;

		return result.Distinct().ToList();
	}

	/// <summary>
	/// パーティーの作戦を取得する
	/// </summary>
	/// <returns></returns>
	public Strategy GetStrategy()
    {
		return this.myStrategy; 
    }


}