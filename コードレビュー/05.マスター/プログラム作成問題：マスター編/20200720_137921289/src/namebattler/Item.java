package namebattler;

import java.util.ArrayList;


public class Item {
	// アイテムNO
	protected int itemNO;
	// アイテム名
	protected String itemName;
	// アイテムの効果
	protected String itemeffect;
	//アイテムの所持数
	protected int itemcount;
	// 攻撃するプレイヤー
	protected Player atacker;
	// 攻撃されるプレイヤー
	protected Player defender;

	// コンストラクタ
	Item() {
	}

	// GetterSetter
	// アイテムNOを取得
	public int GetItemNO() {
		return itemNO;
	}

	// アイテム名を取得
	public String GetItemName() {
		return itemName;
	}

	// アイテムの効果を取得
	public String GetItemEffect() {
		return itemeffect;
	}
	
	// アイテムの個数を取得
		public int GetItemCount() {
			return itemcount;
		}

	// 攻撃するプレイヤーを取得
	public Player GetAtacker() {
		return atacker;
	}

	// 攻撃するプレイヤーを取得
	public Player GetDefender() {
		return defender;
	}
//-------------------------------------
	
	//アイテムを使う
	//アイテムを使えるプレイヤーがいなかったら「0」
	//いたら「1」を返す
	protected int ItemUse(int playerpartyNo){
		return 0;
	}
	
	//アイテムを使ったらアイテムBOXからアイテムを消す
	protected void Removeitem(ArrayList<Item> list,Item useitem) {
		int removeNO = list.indexOf(useitem);
			list.remove(removeNO);
		
		

	}
	
}
