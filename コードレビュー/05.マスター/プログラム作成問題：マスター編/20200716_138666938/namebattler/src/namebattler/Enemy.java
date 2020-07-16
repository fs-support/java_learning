package namebattler;

import java.util.Random;

public class Enemy {
	// =======================
	// フィールド変数
	// =======================
	//ランダムクラス
	Random ran = new Random();
	//敵チームの名前リスト
	String[] enemyList = {"ドリアール","アーミュー","ジャスカー","トバイモン","ジャイシー",
							"ベネテリー","ゲイブラッド","デーヴィッド","ニコラリー","ジョナンド",
							"パトリック","ルフレット","クスタント","ホレス","フェビアン",
							"アーローム","ヴァレッド","ルドウエン","エセルイス","コーニエル"};
	//一人目の番号
	int firstMen;
	//二人目の番号
	int secondMen;
	//三人目の番号
	int thirdMen;
	//戦闘に参加する敵チームの名前
	String[] enemyName = new String[3];
	//敵チームのプレイヤーのJOB番号
	int[] enemyJob = new int[3];

	// =======================
	// コンストラクタ
	// =======================
	public Enemy(){
	}

	// =======================
	// Getter / Setter
	// =======================
	//敵プレイヤー名を取得
	public String GetEnemyName(int enemyNo) {
		return enemyName[enemyNo];
	}

	//敵のJOB番号を取得
	public int GetEnemyJob(int enemyNo){
		return enemyJob[enemyNo];
	}

	// =======================
	// public メソッド
	// =======================
	//メンバー名とjob番号を決める
	public void enemyMenber() {
		//一人目の番号を決める
		firstMen = ran.nextInt(20);
		//二人目の番号を決める*一人目と重複しないように
		do{
			secondMen = ran.nextInt(20);
		}while(firstMen == secondMen);
		//三人目の番号を決める*一人目・二人目と重複しないように
		do{
			thirdMen = ran.nextInt(20);
		}while((firstMen == thirdMen || secondMen == thirdMen));

		//JOB番号をランダムで代入
		for(int i = 0; i < 3; i++) {
			this.enemyJob[i] = ran.nextInt(4) + 1;
		}

		//戦闘に参加する敵メンバーの名前を代入
		enemyName[0] = enemyList[firstMen];
		enemyName[1] = enemyList[secondMen];
		enemyName[2] = enemyList[thirdMen];
	}

}
