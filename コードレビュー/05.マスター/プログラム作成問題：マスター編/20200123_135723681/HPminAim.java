package name_battlerM6;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class HPminAim extends Tactics{

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

		//回復対処の体力が30％以上の場合回復対象を取らない(空のプレイヤークラスを入れる)
		if (HealTarget.GetHP() > (HealTarget.GetMAXHP() / 10 * 3)) {

			HealTarget = player0;
		}
		return HealTarget;
	}

	//補助魔法の対象を決めるメソッド
	protected Player SupportTargetSelect(List<Player> EnemyList) {

		//補助魔法の対象を決める(攻撃と同じくHPが一番少ないものを探す)
		Player SupportTarget = AttackTargetSelect(EnemyList);

		//相手がすでに状態異常の場合対象を取らない(空のプレイヤークラスを入れる)
		if (!SupportTarget.GetStatus().equals(Player.Status.Nomal)) {

			SupportTarget = player0;
		}

		return SupportTarget;
	}

}
