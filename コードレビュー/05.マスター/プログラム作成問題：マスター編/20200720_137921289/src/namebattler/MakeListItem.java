package namebattler;

import java.util.ArrayList;


public class MakeListItem {
	private ArrayList<Item> ItemList;

	public MakeListItem() {
		ItemList = new ArrayList<Item>();
	}

	public ArrayList<Item> Getlistitem() {
		return ItemList;
	}

	// リストに追加
	public void AppendItem(Item item) {
		ItemList.add(item);
	}

	// アイテムの表示
	public void Printtemlist(ArrayList<Item> ItemBOX) {
			for (int i = 0; i < ItemList.size(); i++) {
				System.out.printf(
						"アイテムNO=%3d\nアイテム名=%s\nアイテムの効果=%s\nアイテムの最大所持数=%3d\n",
						ItemList.get(i).itemNO, ItemList.get(i).itemName,
						ItemList.get(i).itemeffect, ItemList.get(i).itemcount);

				// 所持数をここにfor文で記載
				int count = 0;
				Item restitem = ItemList.get(i);

				for (int j = 0; j < ItemBOX.size(); j++) {
					if (ItemBOX.get(j) == restitem) {
						count++;
					}
				}
				System.out.println("残数＝" + count);
				System.out.println("-----------------------");
				//getitembox.clear();

			}
		
	}

}
