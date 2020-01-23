package jp.co.FSsakusei3;

public class Magic {

	// 技名
	String name;
	// // MP
	int mpcost;
	// // 最大ダメージ
	int maxdamage;
	// // 最小ダメージ
	int mindamage;
	// // 回復量
	int heelhp;
	// //状態異常
	String state;

	Magic(String name, int mpcost, int maxdamage, int mindamage) {

		this.name = name;
		this.mpcost = mpcost;
		this.maxdamage = maxdamage;
		this.mindamage = mindamage;

	}

	Magic(String name, int mpcost, int heelhp){
		this.name = name;
		this.mpcost = mpcost;
		this.heelhp= heelhp;
	}

	Magic(String name, int mpcost, String state){
		this.name = name;
		this.mpcost = mpcost;
		this.state= state;
	}
	//void Magic1(String name, int mpcost, int state){

	//}

	String Name() {
		return name;
	}

	int mpcost() {
		return mpcost;
	}

	int maxdamage() {
		return maxdamage;
	}

	int mindamage() {
		return mpcost;
	}

	int heelhp() {
		return heelhp;
	}

	String state() {
		return state;
	}
}
