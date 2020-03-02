package fs03_Intermediate_ProgramCreation;

import java.util.Random;

public class EatItem {

	private String name;		// アイテム名
	private int danger;			// 危険度
	private int heelHP;			// 食べたときのHP回復量
	private String coroner;		// 死因

	Random random = new Random();

	// コンストラクタ
	EatItem(String name , int danger , int heelHP , String coroner){
		this.name = name;
		this.danger = danger;
		this.heelHP = heelHP;
		this.coroner = coroner;
	}

	// アイテム名を取得する
	String Name(){
		return this.name;
	}

	// 危険度を取得する
	int Danger(){
		return this.danger;
	}

	// 食べたときのHP回復量を取得する
	int HeelHP(){
		return this.heelHP;
	}

	// 死因を取得する
	String Coroner(){
		return this.coroner;
	}

	// 食べることができたかどうかの判定(dangerと乱数の値で判定を行う)
	// 戻り値： true:食べることができた / false:食べることができなかった
	boolean judgement(){
		int x = random.nextInt(101);
		if(danger<x){
			return true;
		} else {
			return false;
		}

	}

}
