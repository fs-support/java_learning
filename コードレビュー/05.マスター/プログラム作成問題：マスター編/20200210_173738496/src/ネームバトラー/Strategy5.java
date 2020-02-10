package ネームバトラー;
//　回復優先
public class Strategy5 extends Strategy{
	public Strategy5(String name) {
		super(name);
	}

	public void Action(Player attacker, Party defence) {
		boolean boo=false;
		int i;
		for( i=0;i<attacker.magicList.size();i++) {
			if(attacker.magicList.get(i) instanceof Cure&&attacker.GetMP()>=attacker.magicList.get(i).GetUseMP()&&attacker.party.totalHP()<attacker.party.totalMAXHP()) {
				boo=true;
				break;
			}
		}
		if(boo) {
			attacker.magicList.get(i).UseMagic(attacker,defence.get(0));
		}
		else {
			Aaction(attacker,defence);
		}
	}
}
