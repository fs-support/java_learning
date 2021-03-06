package namebattler_2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Party {
	// =======================
	// フィールド変数
	// =======================
	private ArrayList<Player> members;

	// =======================
	// コンストラクタ
	// =======================
	Party() {
		members = new ArrayList<Player>();
	}

	// =======================
	// Getter / Setter
	// =======================
	/**
	 * パーティーメンバーを ArrayList で取得する
	 */
	ArrayList<Player> GetMembers() {
		return members;
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
	/**
	 * パーティーにプレイヤーを追加する
	 * @param player : 追加するプレイヤー
	 */
	public void AppendPlayer(Player player) {
		members.add(player);
	}

	/** * パーティーからプレイヤーを離脱させる
	 * @param player : 離脱させるプレイヤー
	 */
	public void RemovePlayer(Player player) {
		members.remove(player);
	}

	/**
	 * AGI値が高い順にソートする
	 */
	public void SortAgi() {
		Collections.sort(members, new Comparator<Player>() {
			@Override
			public int compare(Player personFirst, Player personSecond) {
				return Integer.compare(personSecond.GetAGI(),
						personFirst.GetAGI());
			}
		});
	}

	/**
	 * HP値が0でないなかで最も低いPlayerを特定する
	 */
	public Player GetWeakestPlayer(ArrayList<Player> defparty){
		List<Player> defp = defparty.stream()
		        .filter(s -> s.GetHP() > 0)
		        .sorted((a,b) -> a.hp - b.hp)
		        .collect(Collectors.toList());

		return defp.get(0);
	}
}
