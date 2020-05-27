package fs05_Master_Refactoring;

//【StrategyDraftクラスを継承した子クラス】_ 作戦其の５

// ・「作戦名」～ "..イイ感じってなんですか？具体的にどうして欲しいんですか？怒っていいですか？怒りますよ"
// ・「作戦詳細」～ 敵パーティの作戦が④の場合、敵プレイヤーへの物理攻撃がクリティカル判定になる

import java.util.Random;

public class Strategy5 extends StrategyDraft{

	// コンストラクタ
	public Strategy5(String name){
		super(name);
	}


	// 作戦内容(敵の作戦が④の場合、攻撃がクリティカル判定)に沿って攻撃
	@Override
	public void Strategy(Player attacker , Party Enemy){

		Random random = new Random();

		// 攻撃対象を確定
		this.Target = Enemy.GetMembers().get(random.nextInt(Enemy.GetMembers().size()));

		// 下記のif文処理でLUCK値を書き換える前にアタッカーのLUCK元値をkeep用変数に逃がす
		int keepLuck = attacker.luck;

		// 敵の作戦が④の場合、攻撃がクリティカル判定になる様にアタッカーのLUCK値を操作
		if(attacker.GetEnemyPartyInformation().getStrategy() instanceof Strategy4){
			// クリティカル確定にするためにアタッカーのLUCKを「ターゲットのLUCK+1」にする
			attacker.luck = Target.GetLUCK()+1;
		}

		// 攻撃（LUCKが操作されていればクリティカル確定、されていなければ通常通りに攻撃）
		attacker.Condition(this.Target);

		// 攻撃が終了したらアタッカーのLUCK値を元に戻す
		attacker.luck = keepLuck;
	}


	// ダメージ処理完了済みのターゲットを返す
	@Override
	public Player ProcessedDamage(){
		return this.Target;
	}

}
