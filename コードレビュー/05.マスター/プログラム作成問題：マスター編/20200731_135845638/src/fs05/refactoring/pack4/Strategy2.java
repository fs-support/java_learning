package fs05.refactoring.pack4;

//【StrategyDraftクラスを継承した子クラス】_ 作戦其の２

// ・「作戦名」～ "急で悪いんだけどコレ、なる早でよろしく(ﾟ∀ﾟ)"
// ・「作戦詳細」～ 敵パーティーメンバーでAGIが一番高いプレイヤーを優先的に攻撃する

import fs05.refactoring.pack1.Party;
import fs05.refactoring.pack1.SortOrder;
import fs05.refactoring.pack2.Player;

public class Strategy2 extends StrategyDraft {

	SortOrder so = new SortOrder();

	// コンストラクタ
	public Strategy2(String strategyName) {
		super(strategyName);
	}

	// 作戦内容(AGIが１番高いプレイヤーを狙う)に沿って攻撃
	@Override
	public void strategy(Player attacker, Party enemy) {

		// AGI高い順に並び替える用のPartyインスタンスを用意
		// （引数で受け取ったものを直接並び替えるとmainメソッドの方にも影響するから）
		Party dammy = new Party();

		// 並び替え用のインスタンスに引数で受け取った中身(敵パーティーメンバー)を追加
		for (int i = 0; i < enemy.getMembers().size(); i++) {
			dammy.appendPlayer(enemy.getMembers().get(i));
		}

		// 敵パーティーメンバーをAGIの高い順に並べ替える
		String order = "降順";
		so.orderBySort(dammy, order);

		// 敵パーティーの中で１番AGIの高いプレイヤーを選出  ※「target」は親クラスから引き継いだフィールド変数
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