package namebattler;

import java.util.ArrayList;

public class Party {
	// =======================
	// フィールド変数
	// =======================
	private ArrayList<Player> members;
	//パーティーの人数
	int partyMem = 3;
	//パーティの番号
	int partyNo;
	//一斉攻撃の判定
	boolean intensCh = false;
	//一斉攻撃のターゲット
	protected Player IntensTarget;
	//アイテム管理クラス
	protected ItemManager itMana = new ItemManager();
	//離脱したメンバーを格納
	private ArrayList<Player> delmembers;

	// =======================
	// コンストラクタ
	// =======================
	public Party() {

	}

	public Party(/*＝＝＝＝＝＝＝＝＝＝後で消す＝＝＝＝＝＝＝＝＝＝*/String[] S, int[] I, int P) {
		members = new ArrayList<Player>();
		delmembers = new ArrayList<Player>();
		partyNo = P;

		for(int i = 0; i < partyMem; i++) {
//			//プレイヤーの名前を入力
//			System.out.println("プレイヤーの名前を入力してください。");
//			Scanner scan = new Scanner(System.in);
//			String inputName = scan.nextLine();


								//==================================
								//仮ステータス
								String inputName = S[i];
								//==================================


//			//ジョブの選択
//			System.out.println("ジョブを選択してください。");
//			System.out.println("1:戦士");
//			System.out.println("2:魔法使い");
//			System.out.println("3:僧侶");
//			System.out.println("4:勇者");
//
//			String jobNum = scan.nextLine();
//			while(!(jobNum.matches("[1-4]"))) {
//				System.out.println("1～4の数字をを選択してください。");
//				jobNum = scan.nextLine();
//			}
//
//			//選択した数字(preNum)を数値(inputNum)へ変換
//			int inputNum = Integer.parseInt(jobNum);


								//==================================
								//仮ステータス
								int inputNum = I[i];
								//==================================


			if(inputNum == 1){
				Player playerCopy = new Fighter(inputName);
				playerCopy.party = P;
				this.AppendPlayer(playerCopy);
			}else if(inputNum == 2){
				Player playerCopy = new Wizard(inputName);
				playerCopy.party = P;
				this.AppendPlayer(playerCopy);
			}else if(inputNum == 3){
				Player playerCopy = new Priest(inputName);
				playerCopy.party = P;
				this.AppendPlayer(playerCopy);
			}else {
				Player playerCopy = new Brave(inputName);
				playerCopy.party = P;
				this.AppendPlayer(playerCopy);
			}
		}

	}


	// =======================
	// Getter / Setter
	// =======================
	/**
	 * パーティーメンバーを ArrayList で取得する
	 */
	public ArrayList<Player> GetMembers() {
		return this.members;
	}

	/**
	 * 離脱したパーティーメンバーを ArrayList で取得する
	 */
	public ArrayList<Player> GetDelMembers() {
		return this.delmembers;
	}


	// =======================
	// protected メソッド
	// =======================

	// =======================
	// private メソッド
	// =======================

	// =======================
	// public メソッド
	// =======================
	/**
	 * パーティーにプレイヤーを追加する
	 * @param player : 追加するプレイヤー
	 */
	public void AppendPlayer(Player player) {
		this.members.add(player);
	}

	/**
	 * パーティーからプレイヤーを離脱させる
	 * @param player : 離脱させるプレイヤー
	 */
	public void RemovePlayer(Player player) {
		this.members.remove(player);
	}

	/**
	 * delMenberに離脱したプレイヤーを追加する
	 * @param player : 追加するプレイヤー
	 */
	public void AppendDelPlayer(Player player) {
		this.delmembers.add(player);
	}

	/**
	 *  delMenberからプレイヤーを離脱させる
	 * @param player : 離脱させるプレイヤー
	 */
	public void RemoveDelPlayer(Player player) {
		this.delmembers.remove(player);
	}
















}