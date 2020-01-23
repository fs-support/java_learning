package name_battlerM6;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.TreeMap;

public class WellBalanced extends Tactics {

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

		//回復対処の体力が40％以上の場合回復対象を取らない(空のプレイヤークラスを入れる)
		if (HealTarget.GetHP() > (HealTarget.GetMAXHP() / 10 * 4)) {

			HealTarget = player0;
		}
		return HealTarget;
	}

	//補助魔法の対象を決めるメソッド
	protected Player SupportTargetSelect(List<Player> EnemyList) {

		//補助魔法の対象を決める(HPが一番多いものを探す)
		Map<Integer, Player> EnemyHPsort = new TreeMap<Integer, Player>(new Comparator<Integer>() {
			public int compare(Integer k1, Integer k2) {
				return ((Integer) k1).compareTo(k2) * -1;
			}
		});
		for (Player target : EnemyList) {

			EnemyHPsort.put(target.GetHP(), target);
		}

		//TreeMapから値をインデックス番号で取得するためにリストに変換
		List<Player> Enemytarget = new ArrayList<Player>(EnemyHPsort.values());
		Player SupportTarget = Enemytarget.get(0);

		//相手がすでに状態異常の場合対象を取らない(空のプレイヤークラスを入れる)
		if (!SupportTarget.GetStatus().equals(Player.Status.Nomal)) {

			SupportTarget = player0;
		}

		return SupportTarget;
	}

	//ガード使用の有無を決めるメソッド
	protected void GuardSelect(Player player) {

		Player target[] = TargetSelect(player.party.GetEnemys().GetMembers(), player.party.GetMembers());

		//回復・補助呪文を使わない(使えない)且つ、体力が少ない場合、攻撃よりも優先して行う
		if (player.GetHP() > (player.GetMAXHP() / 10 * 4)) {
			if ((player.GetMP() < 20 || (Objects.equals(target[1], player0)))
					&& (player.GetMP() < 10 || (Objects.equals(target[2], player0)))) {

				int AorG = rnd.nextInt(3);//攻撃：防御 ＝ 1：2

				if (AorG != 0) {
					//NoGuard → Guard 状態になる
					player.guard = Player.Guard.Guard;
				}
			}
		}
	}

}
