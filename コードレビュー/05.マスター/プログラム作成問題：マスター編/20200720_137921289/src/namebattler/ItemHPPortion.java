package namebattler;

import java.util.ArrayList;


public class ItemHPPortion extends Item{
	ArrayList<Player> List = new ArrayList<Player>();

	public ItemHPPortion() {
		MakeItem();
	}

	protected void MakeItem() {
		// アイテムNO
		this.itemNO = 1;
		// アイテム名
		this.itemName = "HPポーション";
		// アイテムの効果
		this.itemeffect = "HPが一番低い者を「100」回復させる";
		// アイテムの所持数
		this.itemcount = 2;

	}

	// 回復させる
	protected int ItemUse(int playerpartyNo) {
		Player useplayer = UsePlayer(playerpartyNo);
		System.out.println(itemName + "を使った");
		System.out.println("効果は" + itemeffect + "\n");

		// 確認用
		//System.out.println(useplayer.name + ":" + useplayer.hp);
		useplayer.hp += 100;
		

		// 確認用
		//System.out.println(useplayer.name + ":" + useplayer.hp);
		return 1;
	}

	// 回復するプレイヤーを選ぶ
	public Player UsePlayer(int playerpartyNo) {
		Player useplayer = null;

		if (playerpartyNo == 1) {
			for (int i = 0; i < GameManager.makeparty.GetMembers().size(); i++) {
				if (i == 0) {
					List.add(GameManager.makeparty.GetMembers().get(i));
				} else {
					if (List.get(0).hp > GameManager.makeparty.GetMembers()
							.get(i).hp) {
						List.remove(0);
						List.add(GameManager.makeparty.GetMembers().get(i));
					}
				}
			}

		} else {
			for (int i = 0; i < GameManager.makeparty2.GetMembers().size(); i++) {
				if (i == 0) {
					List.add(GameManager.makeparty2.GetMembers().get(i));
				} else {
					if (List.get(0).hp > GameManager.makeparty2.GetMembers()
							.get(i).hp) {
						List.remove(0);
						List.add(GameManager.makeparty2.GetMembers().get(i));
					}
				}
			}

		}

		useplayer = List.get(0);
		return useplayer;
	}

}
