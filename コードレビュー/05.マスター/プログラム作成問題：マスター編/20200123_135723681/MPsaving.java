package name_battlerM6;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class MPsaving extends Tactics {

	Random rnd = new Random();

	//攻撃の対象を決めるメソッド
	protected Player AttackTargetSelect(List<Player> EnemyList) {

		//攻撃対象のHPが一番少ないものを探す
		Map<Integer, Player> EnemyHPsort = new TreeMap<Integer, Player>();

		for (Player target : EnemyList) {

			EnemyHPsort.put(target.GetHP(), target);
		}
		//TreeMapから値をインデックス番号で取得するためにリストに変換
		List<Player> Enemytarget = new ArrayList<Player>(EnemyHPsort.values());
		Player AttackTarget = Enemytarget.get(0);

		//HPが一番少ないものを優先するが、確率で別の相手に攻撃する(狙い撃ち作戦との差別化)
		int selectChange = rnd.nextInt(4);
		int target = rnd.nextInt(Enemytarget.size());
		if(selectChange == 0) {

			AttackTarget = Enemytarget.get(target);
		}

		return AttackTarget;
	}

	//回復の対象を決めるメソッド
	protected Player HealTargetSelect(List<Player> AllyList) {

		//※回復魔法は使用しないため、回復対象を取らない(空のプレイヤークラスを入れる)
		Player HealTarget = player0;

		return HealTarget;
	}

	//補助魔法の対象を決めるメソッド
	protected Player SupportTargetSelect(List<Player> EnemyList) {

		//※補助魔法は使用しないため、補助対象を取らない(空のプレイヤークラスを入れる)
		Player SupportTarget = player0;

		return SupportTarget;
	}

}
