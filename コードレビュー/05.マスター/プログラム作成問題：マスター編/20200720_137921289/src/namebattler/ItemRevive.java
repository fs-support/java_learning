package namebattler;

import java.util.Random;

public class ItemRevive extends Item{
	
	public ItemRevive() {
		MakeItem();
	}

	protected void MakeItem() {
		// アイテムNO
		this.itemNO = 4;
		// アイテム名
		this.itemName = "蘇生薬";
		// アイテムの効果
		this.itemeffect = "一人のプレイヤーをHP:100，MP:100で蘇生させる";
		// アイテムの所持数
		this.itemcount = 1;

	}

	//アイテムを使えるプレイヤーがいなかったら「0」
		//いたら「1」を返す
		protected int ItemUse(int playerpartyNo){
			Player reviveplayer = null;
			
			if(playerpartyNo == 1 && GameManager.deadparty.GetMembers().size() == 0){
				return 0;
			}else if(playerpartyNo == 2 && GameManager.deadparty2.GetMembers().size() == 0){
				return 0;
			}else{
				System.out.println("戦闘不能プレイヤーをランダムで復活させます");
				Random randNO = new Random();
				
				
				
				if(playerpartyNo == 1){
					int raviveNO1 = randNO.nextInt(GameManager.deadparty.GetMembers().size());
					
					reviveplayer = GameManager.deadparty.GetMembers().get(raviveNO1);
					reviveplayer.hp = 100;
					reviveplayer.mp = 100;
					GameManager.makeparty.AppendPlayer(reviveplayer);
					GameManager.turnorder.add(reviveplayer);
					System.out.println(reviveplayer.name + "を復活させます");
					GameManager.deadparty.RemovePlayer(reviveplayer);
					
				}else if(playerpartyNo == 2){
					int raviveNO2 = randNO.nextInt(GameManager.deadparty2.GetMembers().size());
					
					reviveplayer = GameManager.deadparty2.GetMembers().get(raviveNO2);
					reviveplayer.hp = 100;
					reviveplayer.mp = 100;
					GameManager.makeparty2.AppendPlayer(reviveplayer);
					GameManager.turnorder.add(reviveplayer);
					System.out.println(reviveplayer.name + "を復活させます");
					GameManager.deadparty2.RemovePlayer(reviveplayer);
					
				}
				return 1;
			}
			
		}
		
		
		
}
