package nameButtler;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Scanner;

public class NameButtler {

	public static void main(String[] args) {

		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("++++++++++++++++   Name  Buttler  +++++++++++++++");
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("");
		System.out.println("キャラ1の名前を決めてください");
		Scanner sc = new Scanner(System.in);
		String chara1 = sc.next();

		System.out.println("");

		System.out.println("キャラ2の名前を決めてください");
		String chara2 = sc.next();

		System.out.println("");

		System.out.println("キャラ1 : " + chara1);
		System.out.println("キャラ2 : " + chara2);

		System.out.println("");

		// 名前からキャラ1のHPと最大攻撃力を決める
		// HPを決める HPが0だった場合0以外になるまで繰り返す
		String name_chara1 = chara1;
		int chara1HP = 0;
		while (chara1HP <= 0) {
			chara1HP = selectStatusFromName(chara1);
		}

		// 最大攻撃力を決める 攻撃力0の場合0以外になるまで繰り返す
		int Max_attack_chara1 = 0;
		while (Max_attack_chara1 <= 0) {
			Max_attack_chara1 = selectStatusFromName(chara1);
		}

		// chara1ノ防御力を決める
		int Def_chara1 = selectStatusFromName(chara1);

		// 名前からキャラ2のHPと最大攻撃力を決める
		// HPを決める　HPが0の場合0以外になるまで繰り返し
		String name_chara2 = chara2;
		int chara2HP = 0;
		while (chara2HP <= 0) {
			chara2HP = selectStatusFromName(chara2);
		}

		// 最大攻撃力を決める 攻撃力0の場合0以外になるまで繰り返す
		int Max_attack_chara2 = 0;
		while (Max_attack_chara2 <= 0) {
			Max_attack_chara2 = selectStatusFromName(chara2);
		}

		// chara2の防御力を決める
		int Def_chara2 = selectStatusFromName(chara2);

		System.out.println(chara1 + "のHP・攻撃力・防御力を設定しました。");
		System.out.println(chara1 + "の最大HP：" + chara1HP);
		System.out.println(chara1 + "の最大攻撃力：" + Max_attack_chara1);
		System.out.println(chara1 + "の防御力：" + Def_chara1);

		System.out.println();

		System.out.println(chara2 + "のHP・攻撃力・防御力を設定しました。");
		System.out.println(chara2 + "の最大HP：" + chara2HP);
		System.out.println(chara2 + "の最大攻撃力：" + Max_attack_chara2);
		System.out.println(chara2 + "の防御力：" + Def_chara2);

		System.out.println();

		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("+++++++++++++++    Name Buttler    ++++++++++++++");
		System.out.println("+++++++++++++++  Buttle × Start!  ++++++++++++++");
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("");

		// ターン数カウント
		int turnCount = 1;

		while (true) {
			System.out.println();
			System.out.println("*******" + turnCount + "ターン目*******");
			System.out.println();
			System.out.println("++++++++++++++++++++++++++++++++++");
			System.out.println(chara1 + "の攻撃    残HP:" + chara1HP);
			System.out.println("++++++++++++++++++++++++++++++++++");

			// chara1の攻撃ターン
			// このターンのchara1の攻撃力を決める
			int chara1Attack = 0;
			chara1Attack = select_Attack(Max_attack_chara1);
			System.out.println("攻撃力：" + chara1Attack);

			// 会心の一撃が出る確率　運を名前から決める
			int Luck_chara1 = 0;
			Luck_chara1 = selectStatusFromNameTheLuck(chara1);

			System.out.println("運：" + Luck_chara1);
			System.out.println("");

			// 最終的に決まった攻撃力を記憶する
			int confirm_chara1Attack = 0;

			// 攻撃結果表示
			// 運が3以上なら防御無視ダメージ　以下なら通常攻撃
			// 通常攻撃ならchara1のこのターンの攻撃力からchara2の防御力をひく
			// 0は回避
			if (isCritical(Luck_chara1)) {
				confirm_chara1Attack = chara1Attack - Def_chara1;

				// 最終攻撃力が0以下の場合は最終攻撃力を0として扱う
				if (confirm_chara1Attack >= 1) {
					System.out.println(chara2 + "に" + confirm_chara1Attack
							+ "hit");
				} else {
					System.out.println("回避された");
				}
			} else {
				confirm_chara1Attack = chara1Attack;
				System.out.println("防御力無視ダメージ！");
				System.out.println(chara2 + "に" + confirm_chara1Attack + "hit");
			}

			// chara2の死亡判定
			chara2HP -= confirm_chara1Attack;
			if (isDead(chara2HP)) {
				resultWinOrLose(chara2, chara1);
				break;
			}
			System.out.println();

			// chara2が攻撃する
			System.out.println("+++++++++++++++++++++++++++++++++");
			System.out.println(chara2 + "の攻撃    残HP:" + chara2HP);
			System.out.println("+++++++++++++++++++++++++++++++++");
			;

			// このターンのchara2の攻撃力を決める
			int chara2Attack = 0;
			chara2Attack = select_Attack(Max_attack_chara2);
			System.out.println("攻撃力：" + chara2Attack);

			// 会心の一撃が出る確率　運を名前から決める
			int Luck_chara2 = 0;
			Luck_chara2 = selectStatusFromNameTheLuck(chara2);

			System.out.println("運：" + Luck_chara2);
			System.out.println("");

			// 最終的に決まった攻撃力を記憶する
			int confirm_chara2Attack = 0;

			// 攻撃結果表示
			// 運が3以上なら防御無視ダメージ　以下なら通常攻撃
			// 通常攻撃ならchara2のこのターンの攻撃力からchara1の防御力をひく
			// 0は回避
			if (isCritical(Luck_chara2)) {
				confirm_chara2Attack = chara2Attack - Def_chara1;

				// 最終攻撃力が以下の場合は回避扱い
				if (confirm_chara2Attack >= 1) {
					System.out.println(chara1 + "に" + confirm_chara2Attack
							+ "hit");
				} else {
					System.out.println("回避された");
				}
			} else {
				confirm_chara2Attack = chara2Attack;
				System.out.println("防御力無視ダメージ！");
				System.out.println(chara1 + "に" + confirm_chara2Attack + "hit");
			}

			// chara1の死亡判定
			chara1HP -= confirm_chara2Attack;
			if (isDead(chara1HP)) {
				resultWinOrLose(chara1, chara2);
				break;
			}
			// ターン数のカウント
			turnCount++;
		}
	}

	// 名前からステータスを決める（HP・攻撃・防御力用）
	private static Integer selectStatusFromName(String name) {
		int Status = 0;
		int selectStatus = GetNumber(name, (int) (Math.random() * 10));
		selectStatus /= 2;
		Status = selectStatus;

		return Status;
	}

	// 名前からステータスを決める（運用）
	private static Integer selectStatusFromNameTheLuck(String name) {
		int Status = 0;
		int selectStatus = GetNumber(name, (int) (Math.random() * 10));
		selectStatus /= 20;
		Status = selectStatus;

		return Status;
	}

	// このターンの攻撃力を決定する
	private static Integer select_Attack(int Max_attack) {
		int Attack = (int) (Math.random() * Max_attack);
		return Attack;
	}

	// 通常攻撃かクリティカルか判定する
	private static boolean isCritical(int Luck) {
		return Luck <= 3;
	}

	// 死亡判定
	private static boolean isDead(int hp) {
		return hp <= 0;
	}

	// 勝敗判定表示
	private static void resultWinOrLose(String lose, String win) {
		System.out.println();
		System.out.println(lose + "は力尽きた...");
		System.out.println(win + "の勝利！");
	}

	// ハッシュダイジェストから数値を取り出す
	// name : 名前
	// index : 何番目の数値を取り出すか
	// return 数値(0~255)
	public static Integer GetNumber(String name, Integer index) {
		try {
			byte[] result = MessageDigest.getInstance("SHA-1").digest(
					name.getBytes());
			String digest = String.format("%040x", new BigInteger(1, result));
			String hex = digest.substring(index * 2, index * 2 + 2);
			return Integer.parseInt(hex, 16);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
