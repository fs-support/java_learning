﻿package fs05_Master_Refactoring;

//【ExtraFunctionクラスを継承した子クラス】

//・「機能詳細」～ 攻撃時に一定確率で即死効果を付与

import java.util.Random;

public class InstantDeathAttack extends ExtraFunction {

	// コンストラクタ
	public InstantDeathAttack(){
		// 空のコンストラクタ
	}

	// 追加機能を実行する
	@Override
	public void Extrafunction(Player Target){

		Random random = new Random();
		int kill = random.nextInt(100)+1;

		// 一定確率で即死効果付与　※Targetのジョブによって確率は異なる

		// Targetのジョブが「戦士」の場合 ～ 確率：10%
		if(Target instanceof Fighter && kill<=10){
			Target.hp = 0;
		}

		// Targetのジョブが「魔法使い」or「僧侶」の場合 ～ 確率：20%
		if((Target instanceof Wizard || Target instanceof Priest) && kill<=20){
			Target.hp = 0;
		}

		// Targetのジョブが「母ちゃん」の場合 ～ 確率：30%
		if(Target instanceof Mother && kill<=30){
			Target.hp = 0;
		}


		// 上記if文のいずれかに該当した場合にメッセージを表示
		if(Target.hp==0){
			System.out.println("\n((攻撃に即死効果が付与されました))");
			System.out.println("\n\n\n     「「「 あンた、背中が煤けてるぜ 」」」\n\n\n");
		}

	}

}
