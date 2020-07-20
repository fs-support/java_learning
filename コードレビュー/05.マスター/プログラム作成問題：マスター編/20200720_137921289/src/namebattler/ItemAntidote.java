package namebattler;

import java.util.ArrayList;


public class ItemAntidote extends Item{

	ArrayList<Player> playerlist = new ArrayList<Player>();
	
	public ItemAntidote() {
		MakeItem();
	}

	protected void MakeItem() {
		// アイテムNO
		this.itemNO = 3;
		// アイテム名
		this.itemName = "毒消し薬";
		// アイテムの効果
		this.itemeffect = "毒状態をなくす";
		// アイテムの所持数
		this.itemcount = 2;

	}
	

	//アイテムを使えるプレイヤーがいなかったら「0」
	//いたら「1」を返す
	protected int ItemUse(int playerpartyNo){
		UsePlayer(playerpartyNo);
		
		//毒状態をなくす
		if(playerlist.size() != 0){
			for(int i = 0;i < playerlist.size();i++){
				playerlist.get(i).condition = 0;
				System.out.println("毒は綺麗さっぱりなくなった！");
			}
			return 1;
		}else{
			return 0;
		}
		
		
	}
	
	public void UsePlayer(int playerpartyNo){
		playerlist.clear();
		
		if (playerpartyNo == 1) {
			for (int i = 0; i < GameManager.makeparty.GetMembers().size(); i++) {
				if(GameManager.makeparty.GetMembers().get(i).condition == 2){
					playerlist.add(GameManager.makeparty.GetMembers().get(i));
				}
			}

		} else {
			for (int i = 0; i < GameManager.makeparty2.GetMembers().size(); i++) {
				if(GameManager.makeparty2.GetMembers().get(i).condition == 2){
					playerlist.add(GameManager.makeparty2.GetMembers().get(i));
				}
			}

		}

	}

}
