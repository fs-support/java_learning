package fs05_Master_Refactoring;

//【StrategyDraftクラスを継承した子クラス】_ 作戦其の２

// ・「作戦名」～ "急で悪いんだけどコレ、なる早でよろしく(ﾟ∀ﾟ)"
// ・「作戦詳細」～ 敵パーティーメンバーでAGIが一番高いプレイヤーを優先的に攻撃する

public class Strategy2 extends StrategyDraft{

	// コンストラクタ
	public Strategy2(String name){
		super(name);
	}


	// 作戦内容(AGIが１番高いプレイヤーを狙う)に沿って攻撃
	@Override
	public void Strategy(Player attacker , Party Enemy){

		// AGI高い順に並び替える用のPartyインスタンスを用意
		// （引数で受け取ったものを直接並び替えるとmainメソッドの方にも影響するから）
		Party dammy = new Party();

		// 並び替え用のインスタンスに引数で受け取った中身(敵パーティーメンバー)を追加
		for(int i = 0; i<Enemy.GetMembers().size(); i++){
			dammy.AppendPlayer(Enemy.GetMembers().get(i));
		}

		// 敵パーティーメンバーをAGIの高い順に並べ替える(バブルソート使用)
		for(int i = 0; i<dammy.GetMembers().size()-1; i++){
			for(int j = 0; j<dammy.GetMembers().size()-i-1; j++){
				if(dammy.GetMembers().get(j).GetAGI() < dammy.GetMembers().get(j+1).GetAGI()){
					Player keep = dammy.GetMembers().get(j);
					dammy.GetMembers().set(j, dammy.GetMembers().get(j+1));
					dammy.GetMembers().set(j+1, keep);
				}
			}
		}

		// 敵パーティーの中で１番AGIの高いプレイヤーを選出  ※「Target」は親クラスから引き継いだフィールド変数
		this.Target = dammy.GetMembers().get(0);

		// ターゲットを攻撃（PlayerクラスのCondition()メソッドを呼び出す）
		attacker.Condition(this.Target);

	}


	// ダメージ処理完了済みのターゲットを返す
	@Override
	public Player ProcessedDamage(){
		return this.Target;
	}

}
