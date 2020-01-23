package name_battlerM6;

import java.util.List;
import java.util.Scanner;

public class Tactics {

	Scanner stdin = new Scanner(System.in);
	Player player0 = null;

	protected enum TacticsType {

		HPminAim, Support, AttackMain, MPsaving, WellBalanced
	};

	//作戦内容に沿った各行動の対象を返す
	public Player[] TargetSelect(List<Player> EnemyList, List<Player> AllyList) {

		//攻撃、補助、回復の対象をそれぞれ入れたリスト
		Player targetlist[] = { AttackTargetSelect(EnemyList), HealTargetSelect(AllyList),
				SupportTargetSelect(EnemyList) };

		return targetlist;
	}

	//攻撃の対象を決めるメソッド
	protected Player AttackTargetSelect(List<Player> EnemyList) {

		//各作戦ごとにオーバーライドして処理を記述
		return null;
	}

	//回復の対象を決めるメソッド
	protected Player HealTargetSelect(List<Player> AllyList) {

		//各作戦ごとにオーバーライドして処理を記述
		return null;
	}

	//補助魔法の対象を決めるメソッド
	protected Player SupportTargetSelect(List<Player>EnemyList) {

		//各作戦ごとにオーバーライドして処理を記述
		return null;
	}
	protected void GuardSelect(Player player,Player[] target) {

		return;
	}

}
