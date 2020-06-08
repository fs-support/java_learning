package beginner.nameBattler;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Random;

public class Hero {
	  String name = "";
	  int HP = 10;
	  int ATK, DFE, LUCK;
	  static int turn = 1;
	  Hero(String str){
	      name = str;
	      HP = GetNumber(name, 4)/3 +1;
	      ATK = GetNumber(name, 6)/5 +1;
	      DFE = GetNumber(name, 8)/5 + 1;
	      LUCK = GetNumber(name, 12); //0~255
	  }
	  
	 public void isAttacked(String enemyName, int enemyHP, int enemyATK, int enemyLUCK){
		 if(enemyHP <= 0) return;
		  System.out.println(enemyName+"の攻撃！");
		  
		  if(isCriticaled(enemyLUCK) == true) {	//会心の一撃
			  System.out.println("会心の一撃!!!\n"+name+"に"+enemyATK+"のダメージ！");
			  HP -= enemyATK;
			  return;
		  }
		  
		  int damage = enemyATK - DFE;
		  if(damage > 0) {
			  System.out.println(name+"に"+damage+"のダメージ！");
			  HP -= damage;
		  } else {
			 System.out.println(enemyName+"の攻撃がミス");
		  }	  
	  }
	 
	 public boolean isCriticaled(int enemyLUCK) {
		 var rnd = new Random();
		 int r = rnd.nextInt(256);
		 if(enemyLUCK > r) {
			 return true;
		 } else {
			 return false;
		 }
	 }
	 
	 public void isDefeated(String enemyName) {
		 if(HP > 0) return;
		 System.out.println(name+"は力尽きた・・・");	 
		 System.out.println(enemyName + "の勝利！！");
	 }
	 
	public static void drawGame(int p1HP, int p2HP) {
		if(p1HP > 0 && p2HP > 0) {
			System.out.println("---引き分け---");
		}
	}

	// ハッシュダイジェストから数値を取り出す // name : 名前
	// index : 何番目の数値を取り出すか
	// return 数値(0~255)
	public static Integer GetNumber(String name, Integer index) {
	try {
	byte[] result = MessageDigest.getInstance("SHA-1").digest(name.getBytes()); String digest = String.format("%040x", new BigInteger(1, result));
	String hex = digest.substring(index * 2, index * 2 + 2);
	return Integer.parseInt(hex, 16);
	} catch (Exception e){ e.printStackTrace();
	}
	return 0; }
	 

}
