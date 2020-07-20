package namebattler;

import java.util.ArrayList;
import java.util.Scanner;

public class UseItem {
	// パーティ1用アイテムリスト
	MakeListItem ItemList = new MakeListItem();
	// パーティ1用アイテムを個数分入れる
	ArrayList<Item> ItemBOX1 = new ArrayList<Item>();
	// パーティ2用アイテムリスト
	MakeListItem ItemList2 = new MakeListItem();
	// パーティ2用アイテムを個数分入れる
	ArrayList<Item> ItemBOX2 = new ArrayList<Item>();

	// アイテムの呼び出し
	ItemHPPortion HPportion = new ItemHPPortion();
	ItemMPPotion MPportion = new ItemMPPotion();
	ItemAntidote antidote = new ItemAntidote();
	ItemRevive revive = new ItemRevive();
	
	
	public UseItem() {
		Makeitemlist();
	}

	public void Itemuse() {

		Scanner stdinitem = new Scanner(System.in);
		Scanner stdinitem2 = new Scanner(System.in);
		Scanner stdinuse = new Scanner(System.in);
		Scanner stdinuse2 = new Scanner(System.in);

		// パーティ1用
		System.out.println("パーティ1はアイテムを使いますか？");

		outsid: while (true) {
			if (ItemBOX1.size() > 0) {

				System.out.println("使う場合は「1」を、使わない場合は「2」を入力してください");
				int itemOrNot = stdinitem.nextInt();

				switch (itemOrNot) {
				case 1:
					System.out.println("使うアイテムを選んでください");
					if (ItemList.Getlistitem().size() > 0) {
						ItemList.Printtemlist(ItemBOX1);
					} else {
						System.out.println("アイテムは残っていません");
						break outsid;
					}

					int itemuse = stdinuse.nextInt();

					Item item = ItemList.Getlistitem().get(itemuse - 1);

					System.out.println("");

					if (ItemBOX1.indexOf(item) != -1) {
						int oritem = item.ItemUse(1);
						if (oritem == 1) {
							item.Removeitem(ItemBOX1, item);

						} else {
							System.out.println("アイテムの効果がありません");
							break;
						}

						break outsid;
					} else {
						// アイテムの残りがなければ戻る
						System.out.println("アイテムが残っていません");
						break;
					}

				case 2:
					System.out.println("アイテムは使いませんでした\n");
					break outsid;
				default:
					System.out.println("「1」か「2」を入力してください");
					itemOrNot = stdinitem.nextInt();
				}
			} else {
				System.out.println("手持ちのアイテムは残ってません……");
				break;
			}
		}

		// パーティ2用
		System.out.println("パーティ2はアイテムを使いますか？");

		outsid: while (true) {
			if (ItemBOX2.size() > 0) {

				System.out.println("使う場合は「1」を、使わない場合は「2」を入力してください");
				int itemOrNot = stdinitem.nextInt();

				switch (itemOrNot) {
				case 1:
					System.out.println("使うアイテムを選んでください");
					if (ItemList2.Getlistitem().size() > 0) {
						ItemList.Printtemlist(ItemBOX2);
					} else {
						System.out.println("アイテムは残っていません");
						break outsid;
					}

					int itemuse = stdinuse.nextInt();

					Item item = ItemList2.Getlistitem().get(itemuse - 1);

					System.out.println("");

					if (ItemBOX2.indexOf(item) != -1) {
						int oritem2 = item.ItemUse(2);
						if(oritem2 == 1){
							item.Removeitem(ItemBOX2, item);

						}else{
							System.out.println("アイテムの効果がありません");
							break;
						}
						break outsid;
					} else {
						// アイテムの残りがなければ戻る
						System.out.println("アイテムが残っていません");
						break;
					}

					
				case 2:
					System.out.println("アイテムは使いませんでした\n");
					break outsid;
				default:
					System.out.println("「1」か「2」を入力してください");
					itemOrNot = stdinitem.nextInt();
				}

			}else{
				System.out.println("手持ちのアイテムは残ってません……");
				break;
			}
		}

	}

	public void Makeitemlist() {
		ItemList.AppendItem(HPportion);
		ItemList.AppendItem(MPportion);
		ItemList.AppendItem(antidote);
		ItemList.AppendItem(revive);
		ItemList2.AppendItem(HPportion);
		ItemList2.AppendItem(MPportion);
		ItemList2.AppendItem(antidote);
		ItemList2.AppendItem(revive);
		
		for (int i = 0; i < HPportion.itemcount; i++) {
			ItemBOX1.add(HPportion);
			ItemBOX2.add(HPportion);
		}

		
		for (int i = 0; i < MPportion.itemcount; i++) {
			ItemBOX1.add(MPportion);
			ItemBOX2.add(MPportion);
		}
		
		for(int i = 0;i < antidote.itemcount;i++){
			ItemBOX1.add(antidote);
			ItemBOX2.add(antidote);
		}

		for(int i = 0;i < revive.itemcount;i++){
			ItemBOX1.add(revive);
			ItemBOX2.add(revive);
		}

		
	}

}
