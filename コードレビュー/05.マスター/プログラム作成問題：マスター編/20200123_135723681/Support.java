package name_battlerM6;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.TreeMap;

public class Support extends Tactics {

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

		//回復対象の体力が50％以上の場合回復対象を取らない(空のプイヤークラスを入れる)
		if (HealTarget.GetHP() > (HealTarget.GetMAXHP() / 10 * 5)) {

			HealTarget = player0;
		}
		return HealTarget;
	}

	//補助魔法の対象を決めるメソッド
	protected Player SupportTargetSelect(List<Player> EnemyList) {

		//補助魔法対象はランダム
		int target = rnd.nextInt(EnemyList.size());
		Player SupportTarget = EnemyList.get(target);

		//相手がすでに状態異常の場合対象を取らない(空のプレイヤークラスを入れる)
		if (!SupportTarget.GetStatus().equals(Player.Status.Nomal)) {

			SupportTarget = player0;
		}

		return SupportTarget;
	}

	//ガード使用の有無を決めるメソッド
	protected void GuardSelect(Player player) {

		Player target[] = TargetSelect(player.party.GetEnemys().GetMembers(), player.party.GetMembers());

		//回復・補助呪文を使わない(使えない)場合、攻撃よりも優先して行う
		if((player.GetMP() < 20 || (Objects.equals(target[1],player0))) && (player.GetMP() < 10 || (Objects.equals(target[2],player0)))){

			int AorG = rnd.nextInt(4);//攻撃：防御 ＝ 1：3

			if(AorG != 0) {
			//NoGuard → Guard 状態になる
			player.guard = Player.Guard.Guard;
			}
		}
	}
}
