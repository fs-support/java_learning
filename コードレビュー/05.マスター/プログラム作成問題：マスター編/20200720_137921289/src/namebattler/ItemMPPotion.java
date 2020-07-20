package namebattler;

import java.util.ArrayList;


public class ItemMPPotion extends Item{
	ArrayList<Player> List = new ArrayList<Player>();

	public ItemMPPotion() {
		MakeItem();
	}

	protected void MakeItem() {
		// アイテムNO
		this.itemNO = 2;
		// アイテム名
		this.itemName = "MPポーション";
		// アイテムの効果
		this.itemeffect = "MPが一番低い者を「30」回復させる";
		// アイテムの所持数
		this.itemcount = 2;

	}

	// 回復させる
	protected int ItemUse(int playerpartyNo) {
		Player useplayer = UsePlayer(playerpartyNo);
		System.out.println(itemName + "を使った");
		System.out.println("効果は" + itemeffect + "\n");

		// 確認用
		//System.out.println(useplayer.name + ":" + useplayer.mp);
		useplayer.mp += 30;
		

		// 確認用
		//System.out.println(useplayer.name + ":" + useplayer.mp);
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
					if (List.get(0).mp > GameManager.makeparty.GetMembers()
							.get(i).mp) {
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
					if (List.get(0).mp > GameManager.makeparty2.GetMembers()
							.get(i).mp) {
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
