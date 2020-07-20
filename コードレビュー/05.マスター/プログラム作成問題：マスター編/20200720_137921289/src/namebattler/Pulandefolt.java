package namebattler;

import java.util.Random;

public class Pulandefolt extends Operation{

	protected Pulandefolt() {
		MakeOperation();
		MakeMazic();
	}

	protected void MakeOperation() {
		// 作戦NO
		this.operationNO = 1;
		// 作戦名
		this.operationName = "バランスよく";
		// 作戦内容
		this.operationcontent = "魔法、物理攻撃を交互に行う";
		//魔法の使用有無
		this.OrNotmagic = 0;


	}

	protected Player Choosedefender(Player attackplayer) {
		Player choosedefender = null;

		Random attack = new Random();

		int defenceNO = 0;

		if (attackplayer.partynum == 1) {
			defenceNO = attack.nextInt(GameManager.makeparty2.GetMembers()
					.size());
		} else if (attackplayer.partynum == 2) {
			defenceNO = attack.nextInt(GameManager.makeparty.GetMembers()
					.size());
		}

		if (attackplayer.partynum == 1) {
			choosedefender = GameManager.makeparty2.GetMembers().get(defenceNO);
		} else if (attackplayer.partynum == 2) {
			choosedefender = GameManager.makeparty.GetMembers().get(defenceNO);
		}

		return choosedefender;

	}
	
	//------------------------------
	//魔法による攻撃
	//------------------------------
	
	//使用する魔法を選ぶ
	public Magic Choosemagic(Player attackplayer,int trunNumber) {
		Random userand = new Random();
		Magic usemazic = null;

		//偶数の時魔法を使う
		if(trunNumber % 2 == 0){
			this.OrNotmagic = 1;
		}else{
			this.OrNotmagic = 0;
		}

		
		switch(attackplayer.job){
		case 2:
			int listno = userand.nextInt(Wizardmaziclist.size());
			usemazic = Wizardmaziclist.get(listno);
			break;
		
		case 3:
			if(attackplayer.hp < (attackplayer.Max_hp / 2) + (attackplayer.Max_hp / 4)){
				listno = userand.nextInt(heellist.size());
				usemazic = heellist.get(listno);
			}else{
				listno = userand.nextInt(Priestmaziclist.size());
				usemazic = Priestmaziclist.get(listno);
			}
			break;
		case 4:
			if(attackplayer.hp < (attackplayer.Max_hp / 2) + (attackplayer.Max_hp / 4)){
				listno = userand.nextInt(heellist.size());
				usemazic = heellist.get(listno);
			}else{
				listno = userand.nextInt(Bravemaziclist.size());
				usemazic = Bravemaziclist.get(listno);
			}
			break;
			
		}
		

		return usemazic;
	}
	

}
