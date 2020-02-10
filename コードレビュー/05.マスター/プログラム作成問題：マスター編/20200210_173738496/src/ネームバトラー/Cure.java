package ネームバトラー;

public class Cure extends Magic{
	
	public Cure(String name,int mp) {
		super(name,mp);
	}
	
	public void UseMagic(Player attacker,Player affected) {
		int mp=attacker.GetMP();
		int aaa=0;
		attacker.SetMP(mp-useMP);
		Player a;
		for(int i=0;i<attacker.party.size()-1;i++) {
			
		a=attacker.party.get(i);
		if(a.GetHP()>attacker.party.get(i+1).GetHP()&&attacker.party.get(i+1).GetHP()>0) {
			a=attacker.party.get(i+1);
			aaa=i+1;
		}
		}
		System.out.println(attacker.GetName()+"の"+GetName());
		attacker.party.get(aaa).SetHP(attacker.party.get(aaa).GetHP()+50);
		if(attacker.party.get(aaa).GetHP()>attacker.party.get(aaa).GetMaxHP()) {
			attacker.party.get(aaa).SetHP(attacker.party.get(aaa).GetMaxHP());
			System.out.println(attacker.party.get(aaa).GetName()+"のHPが満タンになった");
		}
		else {
			System.out.println(attacker.party.get(aaa).GetName()+"のHPが50回復した");
		}
	}
}
