﻿package fs05_Master_Refactoring;

//【Skillクラスを継承した子クラス】_ ヒールの魔法を管理するクラス

public class Heal_Magic extends Skill {

	// コンストラクタ
	public Heal_Magic(String name , int cost){
		super(name , cost);
	}

	// 魔法を使用する
	@Override
	public void Magic(Player User , Player Target){
		System.out.println(User.name + "は" + this.name + "を唱えた");
		System.out.println(Target.name + "のHPが50回復した");
		User.mp-=this.cost;
		Target.hp+=50;

		// 回復後のHPが最大HPを超えた場合、回復後のHPを最大HPにする
		if(Target.hp>Target.GetMAXHP()){
			Target.hp = Target.GetMAXHP();
		}
	}

}
