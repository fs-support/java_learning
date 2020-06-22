package middleTest;

public class Blackjack {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		ManageTrump player = new ManageTrump();

		ManageTrump.Reset();
		ManageTrump.Shuffle();
		player.Draw();
		player.Draw();
	}

}
