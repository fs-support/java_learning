package fs05.refactoring;

//【StrategyDraftクラスを継承した子クラス】_ 作戦其の５

// ・「作戦名」～ "..イイ感じってなんですか？具体的にどうして欲しいんですか？怒っていいですか？怒りますよ"
// ・「作戦詳細」～ 敵パーティの作戦が④の場合、敵プレイヤーへの物理攻撃(通常攻撃)がクリティカル判定になる

import java.util.Random;

public class Strategy5 extends StrategyDraft {

	// コンストラクタ
	public Strategy5(String strategyName) {
		super(strategyName);
	}

	// 作戦内容(敵の作戦が④の場合、通常攻撃がクリティカル判定)に沿って攻撃
	@Override
	public void strategy(Player attacker, Party enemy) {

		Random random = new Random();

		// 攻撃対象を確定
		this.target = enemy.getMembers().get(
				random.nextInt(enemy.getMembers().size()));

		// 下記のif文処理でLUCK値を書き換える前にアタッカーのLUCK元値をkeep用変数に逃がす
		int keepLuck = attacker.luck;

		// 敵の作戦が④の場合、攻撃がクリティカル判定になる様にアタッカーのLUCK値を操作
		if (attacker.getEnemyPartyInformation().getStrategy() instanceof Strategy4) {
			// クリティカル確定にするためにアタッカーのLUCKを「ターゲットのLUCK+1」にする
			attacker.luck = target.getLuck() + 1;
		}

		// 攻撃（LUCKが操作されていればクリティカル確定、されていなければ通常通りに攻撃）
		attacker.condition(this.target);

		// 攻撃が終了したらアタッカーのLUCK値を元に戻す
		attacker.luck = keepLuck;
	}

	// ダメージ処理完了済みのターゲットを返す
	@Override
	public Player processedDamage() {
		return this.target;
	}

}