package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GameManager {
	Scanner sc = new Scanner(System.in);

	// ==================================================
	// バトル開始準備
	// ==================================================
	// キーボード入力するための Scanner の準備
	Scanner stdin = new Scanner(System.in);

	List<Player>member = new ArrayList <Player>();

	static int team = 6;

	public void Start() {
		// ゲームの流れ
		Party party1 = new Party("Aチーム");
		Party party2 = new Party("Bチーム");

		Player player1 = new Player("red");
		player1 = jobSelect(player1,"red");
		Player player2 = new Player("blue");
		player2 = jobSelect(player2,"blue");
		Player player3 = new Player("yerrow");
		player3 = jobSelect(player3,"yerrow");
		Player player4 = new Player("1号");
		player4 = jobSelect(player4,"1号");
		Player player5 = new Player("2号");
		player5= jobSelect(player5,"2号");
		Player player6 = new Player("3号");
		player6= jobSelect(player6,"3号");

		party1.appendPlayer(player1);
		party1.appendPlayer(player2);
		party1.appendPlayer(player3);
		party2.appendPlayer(player4);
		party2.appendPlayer(player5);
		party2.appendPlayer(player6);

		member.add(player1);
		member.add(player2);
		member.add(player3);
		member.add(player4);
		member.add(player5);
		member.add(player6);

		Strategy strategy1 = new Strategy();
		strategy1 = strategySelect(strategy1,"Aチーム");
		Strategy strategy2 = new Strategy();
		strategy2 = strategySelect(strategy2,"Bチーム");


		// 3. バトル
		// ==================================================
		// バトル処理
		// ==================================================
		// バトル開始の表示
		player1.printStatus();
		player2.printStatus();
		player3.printStatus();
		player4.printStatus();
		player5.printStatus();
		player6.printStatus();
		System.out.println("");
		System.out.println("=== バトル開始 ===");

		int turnNumber = 1, count1 = 0, count2 = 0;
		// 最大でも20ターンまで
		while (turnNumber <= 20) {
			for(int i = 0; i < party1.size(); i++){
				if(party1.get(i).GetHP()<= 0){
					count1++;
				}
			}
			if(count1 == party1.size()){
				for(int i = 0; i < party1.size(); i++){
					System.out.println(party1.get(i).GetName() + " ");
				}
				System.out.println("の敗北");
				System.out.println(0);
				}
			for(int i = 0; i < party2.size(); i++){
				if(party2.get(i).GetHP() <= 0){
					count2++;
				}
			}
			if(count2 == party1.size()){
				for(int i = 0; i < party2.size(); i++){
					System.out.println(party2.get(i).GetName() + " ");
				}
				System.out.println("の敗北");
				System.out.println(0);
				}

			System.out.println("--------------------------------");
			System.out.printf("- ターン%d -\n", turnNumber);
			battle(party1,party2,strategy1,strategy2);
			System.out.println("");

			// ターン終了時のステータスを表示
			player1.printStatus();
			player2.printStatus();
			player3.printStatus();
			player4.printStatus();
			player5.printStatus();
			player6.printStatus();

			// 次のターン
			turnNumber++;

			}
		}

	public void battle(Party p1, Party p2, Strategy strategy1, Strategy strategy2){
		int count1 = member.size();
		int count2 = member.size();

		for(int j = 0; j < count2; j++){
			for(int i = 0; i < count1 - 1; i++){
				if(member.get(i).GetAGI() < member.get(i + 1).GetAGI()){
					Player a = member.get(i);
					member.set(i, member.get(i + 1));
					member.set(i + 1, a);
				}
			}
			count1--;
		}

		//キャラクターのHPが0になったかの表示
		for(int i = 0; i < team; i++){
			if(member.get(i).GetHP() <= 0){
				p1.removePlayer(member.get(i));
				p2.removePlayer(member.get(i));
			}else{
				for(int k = 0; k < p1.size(); k++){
					if(member.get(i) == (p1).get(k)){
						strategy1.action(member.get(i),p2,p1);
					}
				}
				for(int l = 0; l < p2.size(); l++){
					if(member.get(i) == (p2).get(l)){
						strategy2.action(member.get(i),p1,p2);
					}
				}
			}
		}
	}

	public static void end(Party party) {
		// TODO 自動生成されたメソッド・スタブ
		for(int i =0; i < party.size(); i++){
			System.out.print(party.GetName() + "");
		}
		System.out.println("の負け");
		System.exit(0);
	}

	public static Player jobSelect(Player player,String s){
		System.out.println("職業を選択して下さい。");
		System.out.println("1.戦士、2.魔法使い、3.僧侶、4.勇者");
		Random ra = new Random();
		int a = ra.nextInt(4) + 1;
		switch(a){
		case 1:
			player = new Fighter(s);
			System.out.println(s + "は戦士になった。");
			break;

		case 2:
			player = new Wizard(s);
			System.out.println(s + "は魔法使いになった。");
			break;

		case 3:
			player = new Priest(s);
			System.out.println(s + "は僧侶になった。");
			break;

		case 4:
			player = new Brave(s);
			System.out.println(s + "は勇者になった。");
			break;
		}
		return player;
	}

	public static Strategy strategySelect(Strategy strategy,String s){
		System.out.println("作戦を選択して下さい。");
		System.out.println("1.力持ちを狙え、2.体力のないやつを狙え、3.魔法使いから狙え、4.魔法は節約、5.バランスが大事");
		Random ra = new Random();
		int a = ra.nextInt(5) + 1;
		switch(a){
		case 1:
			strategy = strategy.new Strategy1(s);
			System.out.println(s + "の作戦は1になった。");
			break;

		case 2:
			strategy = strategy.new Strategy2(s);
			System.out.println(s + "の作戦は2になった。");
			break;

		case 3:
			strategy = strategy.new Strategy3(s);
			System.out.println(s + "の作戦は3になった。");
			break;

		case 4:
			strategy = strategy.new Strategy4(s);
			System.out.println(s + "の作戦は4になった。");
			break;

		case 5:
			strategy = strategy.new Strategy5(s);
			System.out.println(s + "の作戦は5になった。");
			break;
		}

		return strategy;
	}

}
