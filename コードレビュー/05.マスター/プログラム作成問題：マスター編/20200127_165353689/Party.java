import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Party {
	// =======================
	// フィールド変数
	// =======================
	// パーティーの情報が入ったリスト
	private ArrayList<Player> members;

	// =======================
	// コンストラクタ
	// =======================
	Party() {
		this.members = new ArrayList<Player>();
	}

	// =======================
	// Getter / Setter
	// =======================
	/**
	 * パーティーメンバーを ArrayList で取得する
	 */
	ArrayList<Player> GetMembers() {

		return this.members;

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
	 *
	 * @param player
	 *            : 追加するプレイヤー
	 */
	public void AppendPlayer(Player player) {

		this.members.add(player);

	}

	/**
	 * パーティーからプレイヤーを離脱させる
	 *
	 * @param player
	 *            : 離脱させるプレイヤー
	 */
	public void RemovePlayer(Player player) {

		this.members.remove(player);
	}

	/**
	 * パーティー内のHPのあるプレイヤーをArrayListで返す
	 *
	 * @return　：攻撃可能なプレイヤーのList
	 */
	public ArrayList<Player> AttackTarget() {
		ArrayList<Player> targetList = new ArrayList<>();

		for (Player p : this.members) {
			if (p.GetHP() > 0) {
				targetList.add(p);
			}
		}

		return targetList;
	}

	/**
	 * パーティー内にHP回復が必要なメンバーのリストを返す
	 *
	 * @return targetList : HP回復が必要なメンバのList
	 */
	public ArrayList<Player> HealMembers(int percent) {

		ArrayList<Player> result = this.members.stream()
				.filter(player -> player.GetHP() < (player.GetMaxHP()*(percent/100)))
				.collect(Collectors.toCollection(ArrayList::new));

		return result;
	}

	/**
	 * 未行動のPlayerの中で一番早いPlayerを返す
	 * @return
	 */
	public Player FastestPlayer() {

		Comparator<Player> comparator = Comparator.comparing(Player::GetAGI).reversed();
		ArrayList<Player> result = members.stream()
		// リストを条件式でフィルター
				.filter(player -> player.GetActive())
				.sorted(comparator)
				// 結果をリストに変換
				.collect(Collectors.toCollection(ArrayList::new));


return result.get(0);
	}

	/**
	 * 未行動のPlayerの中で一番体力の低いPlayerを返す
	 * @return　体力の低い順に並んだリスト
	 */
	public ArrayList<Player> WeakMembers() {

		ArrayList<Player> result = members.stream()
				// リストを条件式でフィルター
				.filter(player -> player.GetActive())
				.sorted((Comparator.comparingInt(player -> player.GetHP())))
				// 結果をリストに変換
				.collect(Collectors.toCollection(ArrayList::new));

		return result;
	}


	/**
	 * パーティーに魔法使いがいる場合、魔法使いのみのリストを返す
	 * パーティーに魔法使いがいない場合、攻撃可能なプレーヤーのリストを返す
	 * @return
	 */
	public ArrayList<Player> WizardTarget() {

		ArrayList<Player> result = members.stream()
				// リストを条件式でフィルター
				.filter(player -> player instanceof Wizard)
				.filter(player -> (player.GetHP() > 0))
				// 結果をリストに変換
				.collect(Collectors.toCollection(ArrayList::new));

		if (result.isEmpty()) {

			result = AttackTarget();

			return result;

		}


		return result;
	}



	/**
	 *
	 * @param player
	 * @return
	 */
	public boolean isExists(Player player) {

		return members.contains(player);
	}

	/**
	 * 行動できる人間がいるかどうか
	 */
	public boolean isActive() {
		List<Player> result = members.stream()
		// リストを条件式でフィルター
				.filter(player -> player.GetActive()==true)
				// 結果をリストに変換
				.collect(Collectors.toList());
		;

		if (result.size() >= 1) {
			return true;
		}

		return false;

	}

	/**
	 * パーティー内のプレイヤーが全滅しているかどうか判定する
	 *
	 * @return ： パーティーが全滅しているかどうか
	 */
	public boolean isLose() {
		List<Player> result = members.stream()
		// リストを条件式でフィルター
				.filter(player -> player.GetHP() > 0)
				// 結果をリストに変換
				.collect(Collectors.toList());
		;

		if (result.size() >= 1) {
			return false;
		}

		return true;
	}


	/**
	 * 作戦をパーティーメンバーに伝える
	 */
	public void ChangeStrategy(Strategy strategy){
		for (Player p:this.members){
			p.SetStrategy(strategy);
		}
	}

	public ArrayList<Player> DebuffMembers(){

		ArrayList<Player> result = this.members.stream()
				.filter(player ->( !player.GetParalyze() || !player.GetPoison()))
				.collect(Collectors.toCollection(ArrayList::new));

		return result;
	}





}