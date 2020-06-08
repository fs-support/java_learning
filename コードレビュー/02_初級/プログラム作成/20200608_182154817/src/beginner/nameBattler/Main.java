package beginner.nameBattler;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		  var stdin = new Scanner(System.in);
		    System.out.print("プレイヤー１の名前を入力してください : ");
		    Hero hero1 = new Hero(stdin.next());
		    //Hero hero1 = new Hero("勇者");
		    System.out.print("プレイヤー2の名前を入力してください : ");
		    Hero hero2 = new Hero(stdin.next());
		    //Hero hero2 = new Hero("魔王");
		    stdin.close();

		    System.out.println("＊＊＊＊＊＊＊＊＊＊\n---バトル開始---");
		    while(true) {
		    	System.out.println("-ターン : "+Hero.turn+"-");
		    	System.out.println("プレイヤー1 : " + hero1.name+"(HP "+hero1.HP+",ATK "+hero1.ATK+",DFE "+hero1.DFE+")");
		    	System.out.println("プレイヤー2 : " + hero2.name+"(HP "+hero2.HP+",ATK "+hero2.ATK+",DFE "+hero2.DFE+")");
		    	hero2.isAttacked(hero1.name, hero1.HP, hero1.ATK, hero1.LUCK);
		    	hero1.isAttacked(hero2.name, hero2.HP, hero2.ATK, hero2.LUCK);

		    	if(hero1.HP <= 0 || hero2.HP <= 0 || Hero.turn >= 30) {
		    		hero1.isDefeated(hero2.name);
		    		hero2.isDefeated(hero1.name);
		    		Hero.drawGame(hero1.HP, hero2.HP);
		    		break;
		    	}

		    	Hero.turn++;
		    	System.out.print("\n");
		    }
	}

}
