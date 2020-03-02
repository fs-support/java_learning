package namebattler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class BattleManager {
	// =======================
	// フィールド変数
	// =======================
	//ランダムクラス
	protected Random ran = new Random();
	//バトルに参加するプレイヤーを格納
	protected ArrayList<Player> btMember = new ArrayList<Player>();
	//各プレイヤーのagiを格納
	protected ArrayList<Integer> agiArray = new ArrayList<Integer>();
	//agiを最大値順に格納
	protected ArrayList<Integer> maxAagiArray = new ArrayList<Integer>();
	//プレイヤーを攻撃順に格納（仮）
	protected ArrayList<Player> preAttackTum = new ArrayList<Player>();
	//プレイヤーを攻撃順に格納
	protected ArrayList<Player> attackTum = new ArrayList<Player>();
//	//ターゲットパーティー
//	protected Party targetP;
//	//ターゲットメンバー
//	protected Player targetM;


	// =======================
	// コンストラクタ
	// =======================
	//バトル参加プレイヤーをbtMemberへ格納
	public BattleManager(Party A, Party B) {

		this.btMember.clear();

		for(int i = 0; i < A.GetMembers().size(); i++) {
			this.btMember.add(A.GetMembers().get(i));
		}

		for(int i = 0; i < B.GetMembers().size(); i++) {
			this.btMember.add(B.GetMembers().get(i));
		}

	}

	// =======================
	// public メソッド
	// =======================
	//バトル参加プレイヤーをagiの高い順に並べる処理
	public void agiCheck() {
		//各リストをクリア
		this.agiArray.clear();
		this.maxAagiArray.clear();
		this.attackTum.clear();

		//バトル参加プレイヤーのagiをagiArrayへ格納
		for(int i = 0; i < this.btMember.size(); i++) {
			this.agiArray.add(this.btMember.get(i).agi);
		}

		//agiの高い順にmaxAagiArrayへ格納
		//チェックする回数
		int checkCou = this.btMember.size();
		for(int i = 0; i < checkCou; i++) {
			//agiArrayのagiの最大値
			int maxAgi = this.agiArray.get(0);
			if(this.agiArray.size() > 1) {
				for(int a = 1; a < this.agiArray.size(); a++) {
					if(maxAgi < this.agiArray.get(a)) {
						maxAgi = this.agiArray.get(a);
					}
				}
			}

			//最大値をmaxAagiArrayへ格納
			this.maxAagiArray.add(maxAgi);
			//agiArrayの最大値の要素数
			int maxNo = this.agiArray.indexOf(maxAgi);
			//最大値に対応するプレイヤーをattackTumへ格納
			this.attackTum.add(this.btMember.get(maxNo));
			//agiArrayの最大値を除く
			this.agiArray.remove(maxNo);
			//対応するbtMemberの要素を削除
			this.btMember.remove(maxNo);
		}

		//////////////////////////////↓↓agiが同じ数値の場合どうするかも追加↓↓//////////////////////////////

		//agiが同じ場合の順番決めの処理、重複がなくなるまでループ
		//agiの重複をカウント
		int agiArrayCh;
		//重複した最初の要素の要素数を格納
		int duplS = 0;
		//チェックした要素の要素数を格納
		int duplL = 1;
		//次にチェックする要素の要素数を格納
		int checkNo = 1;

//		System.out.println("ここまでOK");

		do{

			//preAttackTumのクリア
			this.preAttackTum.clear();

			//カウント0に
			agiArrayCh = 0;

			//重複をチェック
			for(int i = checkNo; i < this.maxAagiArray.size(); i++) {
				if(this.maxAagiArray.get(i - 1) == this.maxAagiArray.get(i)) {
					duplS = i - 1;
					agiArrayCh++;
					//1対1でagiが同じ場合はループを抜けなくなるので、checkNoを2に変更
					if( this.maxAagiArray.size() == 2 ) {
						checkNo = 2;
					}
				//重複しなかったらチェックを抜ける
				}else {
					checkNo = i + 1;
					break;
				}
			}

			duplL = duplS + agiArrayCh;

			if(agiArrayCh > 0) {
				for(int i = duplS; i <= duplL; i++) {

					this.preAttackTum.add(this.attackTum.get(i));

				}

				//preAttackTumをシャッフル    ※とりあえずランダムで
				Collections.shuffle(this.preAttackTum);

				//
				for(int i = 0; i <= agiArrayCh ; i++) {
					this.attackTum.set((duplS + i),this.preAttackTum.get(i));
				}

			}

		}while(agiArrayCh != 0);

	//////////////////////////////↑↑agiが同じ数値の場合どうするかも追加↑↑//////////////////////////////

	}
}
