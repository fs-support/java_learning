using System.Collections;
using System.Collections.Generic;
using UnityEngine;

/// <summary>
/// 作戦
/// </summary>
public interface Strategy
{
    /// <summary>
    /// 作戦に応じた行動を能動側プレイヤーに指示する
    /// </summary>
    /// <param name="activePlayer">能動側プレイヤー</param>
    /// <param name="myParty">能動側プレイヤーの所属パーティー</param>
    /// <param name="enemyParty">敵パーティー</param>
    void StrategyAction(Player activePlayer, Party myParty, Party enemyParty);

}