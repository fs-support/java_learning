package syokyuu;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Random;
import java.util.Scanner;

public class NameBattler2 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		Scanner stdin = new Scanner(System.in);
		Random random = new Random();


		//名前を設定する
		System.out.println("プレイヤー1の名前を入力：");
		String py1 = stdin.nextLine();
		System.out.println("プレイヤー2の名前を入力：");
		String py2 = stdin.nextLine();


	    //体力、攻撃力、防御力を設定するよ（体力は5倍に、ラッキーは50まで）
		int py1HP = GetNumber(py1,0)*5;
	    int py1STR= GetNumber(py1,1);
	    int py1DEF= GetNumber(py1,2);
	    int py1LUCK= GetNumber(py1,3)* 50 / 255;

	    int py2HP = GetNumber(py2,0)*5;
	    int py2STR= GetNumber(py2,1);
	    int py2DEF= GetNumber(py2,2);
	    int py2LUCK= GetNumber(py2,3)* 50 / 255;



	    //バトル開始準備
	    System.out.println("プレイヤー１：" + py1+"【HP " +py1HP+ " / STR " +py1STR+" / DEF " +py1DEF+ " / LUCK " +py1LUCK+ "】" );
	    System.out.println("プレイヤー２：" + py2+"【HP " +py2HP+ " / STR " +py2STR+" / DEF " +py2DEF+ " / LUCK " +py2LUCK + "】");
	    System.out.println("=== バトル開始 ===");


		//繰り返し処理開始
	    while(true) {
	    	int power;
	    	int damage;
	    	int LUCK;


			//プレイヤー1の攻撃
	    	System.out.println(py1 +"の攻撃！");
	    	power = random.nextInt(py1STR)+1;

			//プレイヤー2のダメージ
			//会心の1激が発生しているか確認
	    	LUCK = random.nextInt(100);
	    	if(LUCK <= py1LUCK) {
	    		System.out.println("会心の一撃！");
	    		damage = power;
	    	}else{
	    		//ディフェンス
	    		damage = power - py2DEF;
	    	}


			//ダメージが0以下の場合はミス
	    	if(damage <=0) {
	    		System.out.println(py1 +"はミスして攻撃を外した！");
	    	}else{
	    		System.out.println(py2 +"に" + damage +"のダメージ！");
	    		py2HP = py2HP - damage;
	    	}


			//プレイヤー2死亡check
	    	if(py2HP <= 0 ) {
	    		System.out.println(py2 +"は力尽きた…");
	    		break;
	    	}




			//プレイヤー1のターンは終わり、プレイヤー２の攻撃開始
	    	System.out.println(py2 +"の攻撃！");
	    	power = random.nextInt(py2STR)+1;


	    	LUCK = random.nextInt(100);
	    	if(LUCK <= py2LUCK) {
	    		System.out.println("会心の一撃！");
	    		damage = power;
	    	}else{
	    		//ディフェンス
	    		damage = power - py1DEF;
	    	}

			//ダメージが0以下の場合はミス
	    		if(damage <=0) {
		    		System.out.println(py2 +"はミスして攻撃を外した！");
		    	}else{
		    		System.out.println(py1 +"に" + damage +"のダメージ！");
		    		py1HP = py1HP - damage;
		    	}


			//プレイヤー1死亡check
	    	if(py1HP <= 0 ) {
	    		System.out.println(py1 +"は力尽きた…");
	    		break;
	    	}


	    	//テキスト表示
	    	System.out.println("");
	    	System.out.println("-次のターン-");
	    	System.out.println("プレイヤー１：" + py1 + "(HP " + py1HP + ")");
			System.out.println("プレイヤー２：" + py2 + "(HP " + py2HP + ")");
			System.out.println("--------------------------------");


	    }

		//勝ち負けの表示
	    if(py1HP <= 0) {
	    	System.out.println(py2 +"の勝利！");
	    }else {
	    	System.out.println(py1 +"の勝利！");
	    }

}



	//Getnumberのやつだよ。
	public static Integer GetNumber(String name, Integer index) {
		try {
			byte[] result = MessageDigest.getInstance("SHA-1").digest(name.getBytes());
			String digest = String.format("%040x", new BigInteger(1, result));
			String hex = digest.substring(index * 2, index * 2 + 2);
			return Integer.parseInt(hex, 16);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}



}
