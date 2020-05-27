﻿package fs05_Master_Refactoring;

//【StrategyDraftクラスを継承した子クラス】_ 作戦其の４

//・「作戦名」～ "この案件、なんかイイ感じにやっといてねん(*´з`)"
//・「作戦詳細」～ ターン事に味方パーティーの作戦が④以外でランダムに変わる ※作戦そのものはゲーム終了まで④として扱う

import java.util.ArrayList;
import java.util.Random;

public class Strategy4 extends StrategyDraft{

	int sloppy;		// 書き換える作戦の乱数生成用

	// コンストラクタ
	public Strategy4(String name){
		super(name);
	}


	// 作戦内容(作戦が毎ターン変わる)に沿って攻撃
	@Override
	public void Strategy(Player attacker , Party Enemy){

		// 書き換え用のリストを用意 ※作戦④は除外
		ArrayList<StrategyDraft> shift = new ArrayList<StrategyDraft>();
		shift.add(new Strategy1("\"敵をミンチにするだけの簡単なオシゴト\""));
		shift.add(new Strategy2("\"急で悪いんだけどコレ、なる早でよろしく(ﾟ∀ﾟ)\""));
		shift.add(new Strategy3("\"..なる早っていつまでですか？タスクが組めないので具体的な納期を提示して下さいこの野郎\""));
		shift.add(new Strategy5("\"..イイ感じってなんですか？具体的にどうして欲しいんですか？怒っていいですか？怒りますよ\""));


		// 作戦④専用変数の値が初期値(１人目が攻撃する時)の場合、作戦を書き換える
		if(attacker.GetMyPartyInformation().countGet()==0){
			// 書き換え用リストで乱数生成
			Random random = new Random();
			sloppy = random.nextInt(shift.size());

			// 作戦が変更された旨のメッセージを表示
			System.out.println
			("※" + attacker.GetName() + "の所属パーティーの作戦がターン終了まで「" + shift.get(sloppy).GetStrategyName() + "」に書き換わりました\n");
		}

		// 書き換えた作戦を実行
		shift.get(sloppy).Strategy(attacker , Enemy);

		// 書き換えた作戦のダメージ処理完了済みメソッドを呼んでTargetへ代入
		// (これをしないと作戦④でのTargetの中身がnullのままでエラー(NullPointerException)が発生する)
		this.Target = shift.get(sloppy).ProcessedDamage();

		// パーティーメンバー毎に攻撃が終了したら作戦④専用変数のカウントを＋１する
		attacker.GetMyPartyInformation().countUp();

		// (ターン事に)パーティーメンバー全員の攻撃が終了したら作戦④専用変数を初期化する
		if(attacker.GetMyPartyInformation().countGet() == attacker.GetMyPartyInformation().GetMembers().size()){
			attacker.GetMyPartyInformation().countSet(0);
		}

	}


	// ダメージ処理完了済みのターゲットを返す
	@Override
	public Player ProcessedDamage(){
		return this.Target;
	}

}
