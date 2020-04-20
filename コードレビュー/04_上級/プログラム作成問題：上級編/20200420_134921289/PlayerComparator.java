package namebatoler;

import java.util.Comparator;

public class PlayerComparator implements Comparator<Player> {
	
	public int compare(Player player1agi,Player player2agi ){
		
		return player2agi.agi < player1agi.agi ? -1 : 1; 
	}

	

}
