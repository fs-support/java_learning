﻿using System.Collections;
using System.Collections.Generic;
using UnityEngine;

/// <summary>
/// 作戦名：まほうつかうな
/// MPを使った攻撃を行わない作戦
/// </summary>
public class SavingStrategy : Strategy
{
	Random random = new Random();

	public SavingStrategy() : base()
	{

	}
	public void StrategyAction(Player activePlayer, Party myParty, Party enemyParty)
	{

		// 能動側が戦士の時
		if (activePlayer is Fighter)
		{
			activePlayer.Attack(activePlayer, enemyParty.AttackTarget());
			return;
		}

		// 能動側が魔術師の時
		if (activePlayer is Wizard)
		{
			//攻撃
			activePlayer.Attack(activePlayer, enemyParty.AttackTarget());
			return;

		}

		if (activePlayer is Priest)
		{
			// 能動側が僧侶の時
			if (myParty.HealMembers(20).Count > 0)
			{
				//　回復する味方がいるとき
				if (activePlayer.HealHP(activePlayer, myParty.HealMembers(20)))
				{
					// 回復魔法を書けることができたとき
					return;
				}
			}

			activePlayer.Attack(activePlayer, enemyParty.AttackTarget());
			return;

		}

		// 能動側が勇者の時
		if (activePlayer is Hero)
		{
			if (myParty.HealMembers(20).Count > 0)
			{

				if (activePlayer.HealHP(activePlayer, myParty.HealMembers(20)))
				{
					// 回復魔法をかける
					return;
				}

			}

			activePlayer.Attack(activePlayer, enemyParty.AttackTarget());
			return;
		}
	}
}