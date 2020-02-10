package ネームバトラー;
//　僧侶がいたら優先

import java.util.*;
public class Strategy4 extends Strategy{
	public Strategy4(String name) {
		super(name);
	}

	public void Action(Player attacker, Party defence) {
		List<Player> enemy=new ArrayList<Player>();
		for(int i=0;i<defence.size();i++) {
			if(defence.get(i) instanceof Priest&&defence.get(i).GetHP()>0) {
					enemy.add(defence.get(i));
			}
		}
		if(enemy.size()!=0) {
			Random random=new Random();
			int ran=random.nextInt(enemy.size());
			AAaction(attacker,enemy.get(ran));
		}
		else {
			Aaction(attacker,defence);
		}
	}
}