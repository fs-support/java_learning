package ネームバトラー;

/*  課題 死体うち　HPが０の敵には攻撃しないようにするだけ
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */
import java.util.*;

public class GameManager {
	private Party party1 = new Party();
	private Party party2 = new Party();
	private List<Player> players = new ArrayList<Player>();

	public void Start() {
		StrategySelect(party1);
		StrategySelect(party2);
		CharacterMake();// キャラクター作成
		int turnNumber = 1;
		System.out.println("");
		System.out.println("=== バトル開始 ===");
		while (true) {
			System.out.println("");
			System.out.println(turnNumber + "ターン目");
			Status();// ステータス表示
			// ソート ソート
			for (int i = 0; i < players.size(); i++) {
				for (int j = 0; j < players.size() - i - 1; j++) {
					if (players.get(j).GetAGI() < players.get(j + 1).GetAGI()) {
						Player player = players.get(j);
						players.set(j, players.get(j + 1));
						players.set(j + 1, player);
					}
				}
			}
			Action();// 行動
			if (party1.totalHP() == 0) {
				END(party2);
			} else if (party2.totalHP() == 0) {
				END(party1);
			}
			turnNumber++;
		}
	}

	public void Status() {
		/* ス ステータス表示 */
		for (int i = 0; i < party1.size(); i++) {
			Player p = party1.get(i);
			p.PrintStatus();
		}
		for (int i = 0; i < party2.size(); i++) {
			Player p = party2.get(i);
			p.PrintStatus();
		}
	}

	public void Action() {
		Party enemy;
		for (int i = 0; i < players.size(); i++) {
			if (party1.totalHP() == 0) {
				END(party2);
			} else if (party2.totalHP() == 0) {
				END(party1);
			}
			if (players.get(i).GetHP() > 0) {
				if (players.get(i).GetParty() == party1) {
					enemy = party2;
				} else {
					enemy = party1;
				}
				players.get(i).Action1(players.get(i), enemy);
			}
		}
	}

	public void CharacterMake() {
		Player p1 = new Player("aaa");
		Player p2 = new Player("bbb");
		Player p3 = new Player("ccc");
		Player p4 = new Player("ddd");
		Player p5 = new Player("eee");
		Player p6 = new Player("fff");
		p1 = JobSelect(p1, p1.GetName());
		p2 = JobSelect(p2, p2.GetName());
		p3 = JobSelect(p3, p3.GetName());
		p4 = JobSelect(p4, p4.GetName());
		p5 = JobSelect(p5, p5.GetName());
		p6 = JobSelect(p6, p6.GetName());
		party1.AppendPlayer(p1);
		party1.AppendPlayer(p2);
		party1.AppendPlayer(p3);
		party2.AppendPlayer(p4);
		party2.AppendPlayer(p5);
		party2.AppendPlayer(p6);
		p1.SetParty(party1);
		p2.SetParty(party1);
		p3.SetParty(party1);
		p4.SetParty(party2);
		p5.SetParty(party2);
		p6.SetParty(party2);
		players.add(p1);
		players.add(p2);
		players.add(p3);
		players.add(p4);
		players.add(p5);
		players.add(p6);
	}

	/* 職 職業をランダムで決める */
	public Player JobSelect(Player player, String name) {
		Random ra = new Random();
		int random = ra.nextInt(4) + 1;
		switch (random) {
		case 1:
			player = new Fighter(name);// 戦士
			System.out.println(name + "は戦士になった");
			break;
		case 2:
			player = new Wizard(name);// 魔法使い
			System.out.println(name + "魔法使いになった");
			break;
		case 3:
			player = new Priest(name);// 僧侶
			System.out.println(name + "僧侶になった");
			break;
		case 4:
			player = new Brave(name);// 勇者
			System.out.println(name + "勇者になった");
			break;
		}
		return player;
	}

	/* 1 戦略をランダムで決める */
	public void StrategySelect(Party party) {
		Strategy s;
		Random ra = new Random();
		int random = ra.nextInt(4) + 1;
		switch (random) {
		case 1:
			s = new Strategy1("ランダムに敵を攻撃");
			party.SetStrategy(s);
			break;
		case 2:
			s = new Strategy2("HPが低い敵を敵を攻撃");
			party.SetStrategy(s);
			break;
		case 3:
			s = new Strategy3("防御が低い敵を敵を攻撃");
			party.SetStrategy(s);
			break;
		case 4:
			s = new Strategy4("僧侶を優先的に攻撃する");
			party.SetStrategy(s);
			break;
		case 5:
			s = new Strategy5("命を大事に");
			party.SetStrategy(s);
			break;
		}
	}

	public void END(Party party) {
		for (int i = 0; i < party.size(); i++) {
			System.out.print(party.get(i).GetName() + " ");
		}
		System.out.println("の勝利");
		System.exit(0);
	}
}
