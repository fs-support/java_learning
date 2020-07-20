package fs05.refactoring;

//【StrategyDraftクラスを継承した子クラス】_ 作戦其の１

// ・「作戦名」～ "敵をミンチにするだけの簡単なオシゴト"
// ・「作戦詳細」～ 敵パーティーの中でHPが一番低いプレイヤーを狙う

public class Strategy1 extends StrategyDraft {

	SortOrder so = new SortOrder();

	// コンストラクタ
	public Strategy1(String strategyName) {
		super(strategyName);
	}

	// 作戦内容(HPが１番低いプレイヤーを狙う)に沿って攻撃
	@Override
	public void strategy(Player attacker, Party enemy) {

		// HP低い順に並び替える用のPartyインスタンスを用意
		// （引数で受け取ったものを直接並び替えるとmainメソッドの方にも影響するから）
		Party dammy = new Party();

		// 並び替え用のインスタンスに引数で受け取った中身(敵パーティーメンバー)を追加
		for (int i = 0; i < enemy.getMembers().size(); i++) {
			dammy.appendPlayer(enemy.getMembers().get(i));
		}

		// 敵パーティーメンバーをHPの低い順に並べ替える
		String order = "昇順";
		so.setSortVer(1); // 並べ替え仕様を設定する（これをやらないと、この作成の内容がタイムリーで反映されない）
		so.orderBySort(dammy, order);

		// 敵パーティーの中でHPが１番低いプレイヤーを選出  ※「target」は親クラスから引き継いだフィールド変数
		this.target = dammy.getMembers().get(0);

		// ターゲットを攻撃（PlayerクラスのCondition()メソッドを呼び出す）
		attacker.condition(this.target);

	}

	// ダメージ処理完了済みのターゲットを返す
	@Override
	public Player processedDamage() {
		return this.target;
	}

}