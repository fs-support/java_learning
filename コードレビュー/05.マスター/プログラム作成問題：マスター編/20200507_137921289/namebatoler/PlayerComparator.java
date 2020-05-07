package namebatoler;

import java.util.Comparator;

public class PlayerComparator implements Comparator<Player> {
	//素早さ順に並び変える
	public int compare(Player player1agi,Player player2agi ){
		
		return player2agi.agi < player1agi.agi ? -1 : 1; 
	}

	

}
