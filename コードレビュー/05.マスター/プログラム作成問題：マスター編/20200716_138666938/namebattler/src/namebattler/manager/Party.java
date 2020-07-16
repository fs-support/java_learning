package namebattler.manager;

import java.util.ArrayList;
import java.util.Scanner;

import namebattler.Enemy;
import namebattler.player.Brave;
import namebattler.player.Fighter;
import namebattler.player.Player;
import namebattler.player.Priest;
import namebattler.player.Wizard;

public class Party {
	// =======================
	// フィールド変数
	// =======================
	//プレイヤー名の入力値
	protected String inputName;
	//JOB番号の入力値
	protected int inputNum;
	//ゲームに参加するプレイヤーを格納
	private ArrayList<Player> members = new ArrayList<Player>();
	//パーティーの人数
	protected int partyMem = 3;
	//パーティの番号 *playerクラスのpartyNoと一致
	protected int partyNo;
	//パーティー名
	protected String partyName;
	//選択中の作戦番号
	protected int tacticalNo;
	//一斉攻撃の判定
	private boolean intensCh = false;
	//一斉攻撃のターゲット
	private Player IntensTarget;
	//アイテム管理クラス
	protected ItemManager itMana = new ItemManager();
	//離脱したメンバーを格納
	private ArrayList<Player> delmembers = new ArrayList<Player>();;
	//敵メンバーを管理
	protected Enemy enemy = new Enemy();

	// =======================
	// コンストラクタ
	// =======================
	public Party(int partyNo) {
		this.partyNo = partyNo;

		//チーム名の設定
		String[] testName = {"Aチーム", "Bチーム"};
		partyName= testName[partyNo];

		for(int i = 0; i < partyMem; i++) {
			//味方チームの場合
			if(partyNo == 0) {
				//プレイヤーの名前を入力
				System.out.println("プレイヤーの名前を入力してください。");
				Scanner scan = new Scanner(System.in);
				inputName = scan.nextLine();

				//ジョブの選択
				System.out.println("ジョブを選択してください。");
				System.out.println("1:戦士");
				System.out.println("2:魔法使い");
				System.out.println("3:僧侶");
				System.out.println("4:勇者");

				String jobNum = scan.nextLine();
				while(!(jobNum.matches("[1-4]"))) {
					System.out.println("1～4の数字をを選択してください。");
					jobNum = scan.nextLine();
				}

				//選択した数字(preNum)を数値(inputNum)へ変換
				inputNum = Integer.parseInt(jobNum);

			//敵チームの場合
			}else {
				//敵チームの情報を決める処理
				if(i == 0) {
					enemy.enemyMenber();
				}
				inputName = enemy.GetEnemyName(i);
				inputNum = enemy.GetEnemyJob(i);
			}

			if(inputNum == 1){
				Player playerCopy = new Fighter(inputName);
				playerCopy.SetParty(partyNo);
				this.AppendPlayer(playerCopy);
			}else if(inputNum == 2){
				Player playerCopy = new Wizard(inputName);
				playerCopy.SetParty(partyNo);
				this.AppendPlayer(playerCopy);
			}else if(inputNum == 3){
				Player playerCopy = new Priest(inputName);
				playerCopy.SetParty(partyNo);
				this.AppendPlayer(playerCopy);
			}else {
				Player playerCopy = new Brave(inputName);
				playerCopy.SetParty(partyNo);
				this.AppendPlayer(playerCopy);
			}
		}
	}

	// =======================
	// Getter / Setter
	// =======================
	//パーティの番号の取得
	public int GetPartyNo() {
		return this.partyNo;
	}

	//一斉攻撃の判定の取得
	public boolean GetIntensCh() {
		return this.intensCh;
	}

	//一斉攻撃のターゲットを取得
	public Player GetIntensTarget() {
		return this.IntensTarget;
	}

	//選択中の作戦番号の取得
	public int GetTacticalNo() {
		return this.tacticalNo;
	}

	//一斉攻撃の判定の変更
	public void SetIntensCh(boolean intensCh) {
		this.intensCh = intensCh;
	}

	//一斉攻撃の判定の変更
	public void SetIntensTarget(Player IntensTarget) {
		this.IntensTarget = IntensTarget;
	}

	//選択中の作戦番号の変更
	public void SetTacticalNo(int tacticalNo) {
		this.tacticalNo = tacticalNo;
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

	//パーティー名を取得
	public String GetPartyName() {
		return this.partyName;
	}

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
	 * delMenberからプレイヤーを離脱させる
	 * @param player : 離脱させるプレイヤー
	 */
	public void RemoveDelPlayer(Player player) {
		this.delmembers.remove(player);
	}

}
