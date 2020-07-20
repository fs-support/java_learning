package fs05.refactoring;

//【StrategyDraftクラスを継承した子クラス】_ 作戦其の３

// ・「作戦名」～ "..なる早っていつまでですか？タスクが組めないので具体的な納期を提示して下さいこの野郎"
// ・「作戦詳細」～ 敵パーティの作戦が②の場合、attackerの攻撃が２連続攻撃になる

import java.util.Random;

public class Strategy3 extends StrategyDraft {

	// コンストラクタ
	public Strategy3(String strategyName) {
		super(strategyName);
	}

	// 作戦内容(敵の作戦が②の場合、attackerの連続攻撃)に沿って攻撃
	@Override
	public void strategy(Player attacker, Party enemy) {

		// [麻痺][毒]の扱いについて
		//  → 麻痺 ～ 麻痺になっていた場合、攻撃せずに終了(２回目の攻撃もスキップ)
		//  → 毒   ～ ２連続攻撃でも１回の行動と見なし、毒ダメージは１回分のみ

		Random random = new Random();

		// 攻撃対象を確定
		this.target = enemy.getMembers().get(
				random.nextInt(enemy.getMembers().size()));

		// ターゲットの所属パーティーの作戦がStrategy2のインスタンスの場合、作戦③実行
		if (attacker.getEnemyPartyInformation().getStrategy() instanceof Strategy2) {

			// 麻痺回復のメソッドを呼ぶ
			attacker.paralysisClear();
			// ２連続攻撃(attacker.isGetStatusParalysis()==false の場合)
			if (!attacker.isGetStatusParalysis()) {
				for (int i = 0; i < 2; i++) {
					attacker.attack(target);
					// １回目の攻撃でターゲットが戦闘不能になった場合は２回目の攻撃はスキップ
					if (target.getHp() <= 0) {
						break;
					}
				}
			}
			// 毒ダメージのメソッドを呼ぶ
			attacker.poisonDamage();

		} else {
			// 敵の作戦が②でなければ、通常通り１回攻撃
			attacker.condition(this.target);
		}

	}

	// ダメージ処理完了済みのターゲットを返す
	@Override
	public Player processedDamage() {
		return this.target;
	}

}