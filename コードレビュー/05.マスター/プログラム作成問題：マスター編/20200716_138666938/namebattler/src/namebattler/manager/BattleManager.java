package namebattler.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import namebattler.player.Player;

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
	//プレイヤーを攻撃順に格納（暫定）
	protected ArrayList<Player> preAttackTum = new ArrayList<Player>();
	//プレイヤーを攻撃順に格納（確定）
	protected ArrayList<Player> attackTum = new ArrayList<Player>();

	// =======================
	// コンストラクタ
	// =======================
	//バトル参加プレイヤーをbtMemberへ格納
	public BattleManager(ArrayList<Party> battleParty) {
		//btMemberをクリア
		this.btMember.clear();

		for(int i = 0; i < battleParty.size(); i++) {
			for(int j = 0; j < battleParty.get(i).GetMembers().size(); j++) {
				this.btMember.add(battleParty.get(i).GetMembers().get(j));
			}
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
			this.agiArray.add(this.btMember.get(i).GetAGI());
		}

		//agiの高い順にmaxAagiArrayへ格納
		//チェックする回数
		int checkCount = this.btMember.size();
		for(int i = 0; i < checkCount; i++) {
			//agiArrayのagiの最大値
			int maxAgi = this.agiArray.get(0);
			if(this.agiArray.size() > 1) {
				for(int j = 1; j < this.agiArray.size(); j++) {
					if(maxAgi < this.agiArray.get(j)) {
						maxAgi = this.agiArray.get(j);
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

		//agiが同じ場合の順番決めの処理
		//agiの重複をカウント
		int agiDupeCount;

		//重複した最初の要素の要素番号を格納
		int dupeS;

		//チェックが終了した要素の要素番号を格納
		int dupeL = 1;

		//次にチェックする要素の要素番号を格納
		int checkNo = 1;

		//重複チェックと重複がある場合の順番替えの処理
		do{
			//preAttackTumのクリア
			this.preAttackTum.clear();

			//agiDupeCountをカウント0に
			agiDupeCount = 0;

			//dupeSに仮の初期値として-1を代入
			dupeS = -1;

			//maxAagiArrayの重複をチェック
			for(int i = checkNo; i < this.maxAagiArray.size(); i++) {
				//重複がある場合
				if(this.maxAagiArray.get(i - 1) == this.maxAagiArray.get(i)) {
					//dupeSが決まっていない場合、重複が始まった要素番号を格納
					if(dupeS == -1) {
						dupeS = i - 1;
					}

					//重複数をカウント
					agiDupeCount++;
					//1対1の状態でagiが同じ場合はチェックを抜ける
					if( this.maxAagiArray.size() == 2 ) {
						break;
					}

					//チェックした要素が最後でなければcheckNoを進める
					if( (i + 1) != this.maxAagiArray.size() ) {
						checkNo++;
					}

				//重複しなかったらチェックを抜ける
				}else {
					//チェックした要素が最後でなければcheckNoを進める
					if( (i + 1) != this.maxAagiArray.size() ) {
						checkNo++;
					}
					break;
				}
			}

			//maxAagiArray内で重複がある場合
			if(agiDupeCount > 0) {
				//重複が確認できた最後の要素番号を計算
				dupeL = dupeS + agiDupeCount;

				for(int i = dupeS; i <= dupeL; i++) {
					//重複している要素をpreAttackTumへ格納
					this.preAttackTum.add(this.attackTum.get(i));
				}

				//preAttackTumをシャッフル　※とりあえずランダムで
				Collections.shuffle(this.preAttackTum);

				//preAttackTumの要素順にattackTumを上書き
				for(int i = 0; i <= agiDupeCount ; i++) {
					this.attackTum.set((dupeS + i),this.preAttackTum.get(i));
				}
			}

		//全ての重複チェックが終わったらループを抜ける
		}while( !((checkNo + 1) == this.maxAagiArray.size()) );
	}

}
