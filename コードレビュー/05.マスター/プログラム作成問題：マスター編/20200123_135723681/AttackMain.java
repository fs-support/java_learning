package name_battlerM6;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class AttackMain extends Tactics {

	Random rnd = new Random();

	//攻撃の対象を決めるメソッド
	protected Player AttackTargetSelect(List<Player> EnemyList) {

		//攻撃対象はランダム
		int target = rnd.nextInt(EnemyList.size());

		Player AttackTarget = EnemyList.get(target);

		return AttackTarget;
	}

	//回復の対象を決めるメソッド
	protected Player HealTargetSelect(List<Player> AllyList) {

		//回復対象のHPが一番少ないものを探す
		Map<Integer, Player> AllyHPsort = new TreeMap<Integer, Player>();

		for (Player target : AllyList) {

			AllyHPsort.put(target.GetHP(), target);
		}
		//TreeMapから値をインデックス番号で取得するためにリストに変換
		List<Player> Allytarget = new ArrayList<Player>(AllyHPsort.values());
		Player HealTarget = Allytarget.get(0);

		//回復対象の体力が20％以上の場合回復対象を取らない(空のプレイヤークラスを入れる)
		if (HealTarget.GetHP() > (HealTarget.GetMAXHP() / 10 * 2)) {

			HealTarget = player0;
		}
		return HealTarget;
	}

	//補助魔法の対象を決めるメソッド
	protected Player SupportTargetSelect(List<Player> EnemyList) {

		//※補助魔法は使用しないため、対象を取らない(空のプレイヤークラスを入れる)
		Player SupportTarget = player0;

		return SupportTarget;
	}
}
