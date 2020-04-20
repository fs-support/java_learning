package namebatoler;

import java.util.Random;

public class Pulandefo extends Operation{

	protected Pulandefo(){
		
	}
	
	protected void MakeOperation(){
		//作戦NO
		this.operationNO = 1;
		//作戦名
		this.operationName = "お任せ";
		//作戦内容
		this.operationcontent = "お任せ";
	}
	
	protected Player Choosedefender(Player attackplayer){
		Player choosedefender = null;

		Random attack = new Random();

		int defenceNO = 0;

		if (attackplayer.partynum == 1) {
			defenceNO = attack.nextInt(GameManager.makeparty2.GetMembers().size());
		} else if (attackplayer.partynum == 2) {
			defenceNO = attack.nextInt(GameManager.makeparty.GetMembers().size());
		}

		if (attackplayer.partynum == 1) {
			choosedefender = GameManager.makeparty2.GetMembers().get(defenceNO);
		} else if (attackplayer.partynum == 2) {
			choosedefender = GameManager.makeparty.GetMembers().get(defenceNO);
		}

		return choosedefender;

	}
	
	
	
}
