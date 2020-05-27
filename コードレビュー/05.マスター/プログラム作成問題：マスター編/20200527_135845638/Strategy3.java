package fs05_Master_Refactoring;

//【StrategyDraftクラスを継承した子クラス】_ 作戦其の３

// ・「作戦名」～ "..なる早っていつまでですか？タスクが組めないので具体的な納期を提示して下さいこの野郎"
// ・「作戦詳細」～ 敵パーティの作戦が②の場合、attackerの攻撃が２連続攻撃になる

import java.util.Random;

public class Strategy3 extends StrategyDraft{

	// コンストラクタ
	public Strategy3(String name){
		super(name);
	}


	// 作戦内容(敵の作戦が②の場合、attackerの連続攻撃)に沿って攻撃
	@Override
	public void Strategy(Player attacker , Party Enemy){

		// [麻痺][毒]の扱いについて
		//  → 麻痺 ～ 麻痺になっていた場合、攻撃せずに終了(２回目の攻撃もスキップ)
		//  → 毒   ～ ２連続攻撃でも１回の行動と見なし、毒ダメージは１回分のみ

		Random random = new Random();

		// 攻撃対象を確定
		this.Target = Enemy.GetMembers().get(random.nextInt(Enemy.GetMembers().size()));

		// ターゲットの所属パーティーの作戦がStrategy2のインスタンスの場合、作戦③実行
		if(attacker.GetEnemyPartyInformation().getStrategy() instanceof Strategy2){

			// 麻痺回復のメソッドを呼ぶ
			attacker.paralysisClear();
			// ２連続攻撃
			if(attacker.GetStatus_paralysis()==false){
				for(int i = 0; i<2; i++){
					attacker.Attack(Target);
					// １回目の攻撃でターゲットが戦闘不能になった場合は２回目の攻撃はスキップ
					if(Target.GetHP()<=0){
						break;
					}
				}
			}
			// 毒ダメージのメソッドを呼ぶ
			attacker.poisonDamage();

		} else {
			// 敵の作戦が②でなければ、通常通り１回攻撃
			attacker.Condition(this.Target);
		}

	}


	// ダメージ処理完了済みのターゲットを返す
	@Override
	public Player ProcessedDamage(){
		return this.Target;
	}

}
