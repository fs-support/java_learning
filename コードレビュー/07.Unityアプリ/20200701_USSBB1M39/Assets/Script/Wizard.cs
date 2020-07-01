using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

/// <summary>
/// 職業が魔法使いのプレイヤー
/// </summary>
public class Wizard : Player
{

	// =======================
	// フィールド変数
	// =======================
	Magic[] attackMagic = { new Fire(), new Thunder() };

	/// <summary>
	/// コンストラクタ
	/// </summary>
	/// <param name="name">プレイヤー名</param>
	public Wizard(string name) : base(name)
	{
		this.name = name;
		MakeCharacter();
	}

	// =======================
	// Getter / Setter
	// =======================

	// =======================
	// protected メソッド
	// =======================

	/// <summary>
	/// <inheritdoc/>
	/// </summary>
	protected override void MakeCharacter()
	{
		this.defaultHP = (base.GetNumber(0, 10) + 5) * 10;
		this.defaultMP = base.GetNumber(1, 50) + 30;
		this.hp = base.defaultHP;
		this.mp = base.defaultMP;

		this.str = base.GetNumber(2, 50) + 1;
		this.def = base.GetNumber(3, 50) + 1;
		this.luck = base.GetNumber(4, 100) + 1;
		this.agi = base.GetNumber(5, 40) + 20;

		this.paralyze = false;
		this.poison = false;
		this.active = true;


	}

	// =======================
	// private メソッド
	// =======================

	// =======================
	// public メソッド
	// =======================

	/// <summary>
	/// 攻撃側プレイヤー(activePlayer)のMPが魔法の消費MPに足りている場合は
	/// <inheritdoc/>
	/// </summary>
	/// <param name="activePlayer"><inheritdoc/></param>
	/// <param name="passiveMembers"><inheritdoc/></param>
	public override void Attack(Player activePlayer, List<Player> passiveMembers)
	{
		Debug.Log("Wizard Attack");

		//攻撃対象の決定
		Player passivePlayer = passiveMembers[0];

		if (passiveMembers.Count > 1)
		{
			passivePlayer = passiveMembers[UnityEngine.Random.Range(0, passiveMembers.Count - 1)];

		}

		//使用する魔法を決定する
		Magic UseMagic = this.attackMagic[UnityEngine.Random.Range(0, attackMagic.Length)];

		Debug.Log(string.Format("Wizard Attack {0}", UseMagic.GetName()));
		if (UseMagic.GetUseMP() <= this.mp)
		{
			Debug.Log("Wizard Attack 魔法使えるとき");
			UseMagic.effect(activePlayer, passivePlayer);
			return;

		}
		// 与えるダメージを求める
		LogText.AddLog(string.Format("{0} の 攻撃！", activePlayer.GetName()));
		int damage = activePlayer.CalcDamage(passivePlayer);

		// 求めたダメージを対象プレイヤーに与える
		LogText.AddLog(string.Format("{0} に　{1｝ のダメージ！", passivePlayer.GetName(), damage));
		passivePlayer.Damage(damage);

		passivePlayer.Down();

	}
}
