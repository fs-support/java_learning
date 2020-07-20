package namebattler;

import java.util.ArrayList;
import java.util.Random;

public class PulantargetLowHP extends Operation{

	ArrayList<Player> List = new ArrayList<Player>();

	public PulantargetLowHP() {
		MakeOperation();
		MakeMazic();
	}

	// 作戦の作成
	protected void MakeOperation() {
		// 作戦NO
		this.operationNO = 5;
		// 作戦名
		this.operationName = "低HP攻撃";
		// 作戦内容
		this.operationcontent = "HPが一番低い者を攻撃！";
		// 魔法の使用有無
		this.OrNotmagic = 1;
	}

	protected Player Choosedefender(Player attackplayer) {
		Player choosedefender = null;
		
		if (attackplayer.partynum == 1) {
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
				choosedefender = List.get(0);
			}
			
		} else if (attackplayer.partynum == 2) {
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
				choosedefender = List.get(0);
			}
		}

		List.remove(0);
		return choosedefender;

	}

	// 使用する魔法を選ぶ
	public Magic Choosemagic(Player attackplayer,int trunNumber) {
		Random userand = new Random();
		Magic usemazic = null;

		switch (attackplayer.job) {
		case 2:
			int listno = userand.nextInt(Wizardmaziclist.size());
			usemazic = Wizardmaziclist.get(listno);
			break;

		case 3:
			if (attackplayer.hp < (attackplayer.Max_hp / 2) + (attackplayer.Max_hp / 4)) {
				listno = userand.nextInt(heellist.size());
				usemazic = heellist.get(listno);
			} else {
				listno = userand.nextInt(Priestmaziclist.size());
				usemazic = Priestmaziclist.get(listno);
			}
			break;
		case 4:
			if (attackplayer.hp < (attackplayer.Max_hp / 2) + (attackplayer.Max_hp / 4)) {
				listno = userand.nextInt(heellist.size());
				usemazic = heellist.get(listno);
			} else {
				listno = userand.nextInt(Bravemaziclist.size());
				usemazic = Bravemaziclist.get(listno);
			}
			break;

		}

		return usemazic;
	}


}

