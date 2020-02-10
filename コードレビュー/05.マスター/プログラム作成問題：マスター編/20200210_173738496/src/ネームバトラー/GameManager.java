package �l�[���o�g���[;

/*  �ۑ� ���̂����@HP���O�̓G�ɂ͍U�����Ȃ��悤�ɂ��邾��
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
		CharacterMake();// �L�����N�^�[�쐬
		int turnNumber = 1;
		System.out.println("");
		System.out.println("=== �o�g���J�n ===");
		while (true) {
			System.out.println("");
			System.out.println(turnNumber + "�^�[����");
			Status();// �X�e�[�^�X�\��
			// �\�[�g �\�[�g
			for (int i = 0; i < players.size(); i++) {
				for (int j = 0; j < players.size() - i - 1; j++) {
					if (players.get(j).GetAGI() < players.get(j + 1).GetAGI()) {
						Player player = players.get(j);
						players.set(j, players.get(j + 1));
						players.set(j + 1, player);
					}
				}
			}
			Action();// �s��
			if (party1.totalHP() == 0) {
				END(party2);
			} else if (party2.totalHP() == 0) {
				END(party1);
			}
			turnNumber++;
		}
	}

	public void Status() {
		/* �X �X�e�[�^�X�\�� */
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

	/* �E �E�Ƃ������_���Ō��߂� */
	public Player JobSelect(Player player, String name) {
		Random ra = new Random();
		int random = ra.nextInt(4) + 1;
		switch (random) {
		case 1:
			player = new Fighter(name);// ��m
			System.out.println(name + "�͐�m�ɂȂ���");
			break;
		case 2:
			player = new Wizard(name);// ���@�g��
			System.out.println(name + "���@�g���ɂȂ���");
			break;
		case 3:
			player = new Priest(name);// �m��
			System.out.println(name + "�m���ɂȂ���");
			break;
		case 4:
			player = new Brave(name);// �E��
			System.out.println(name + "�E�҂ɂȂ���");
			break;
		}
		return player;
	}

	/* 1 �헪�������_���Ō��߂� */
	public void StrategySelect(Party party) {
		Strategy s;
		Random ra = new Random();
		int random = ra.nextInt(4) + 1;
		switch (random) {
		case 1:
			s = new Strategy1("�����_���ɓG���U��");
			party.SetStrategy(s);
			break;
		case 2:
			s = new Strategy2("HP���Ⴂ�G��G���U��");
			party.SetStrategy(s);
			break;
		case 3:
			s = new Strategy3("�h�䂪�Ⴂ�G��G���U��");
			party.SetStrategy(s);
			break;
		case 4:
			s = new Strategy4("�m����D��I�ɍU������");
			party.SetStrategy(s);
			break;
		case 5:
			s = new Strategy5("����厖��");
			party.SetStrategy(s);
			break;
		}
	}

	public void END(Party party) {
		for (int i = 0; i < party.size(); i++) {
			System.out.print(party.get(i).GetName() + " ");
		}
		System.out.println("�̏���");
		System.exit(0);
	}
}
