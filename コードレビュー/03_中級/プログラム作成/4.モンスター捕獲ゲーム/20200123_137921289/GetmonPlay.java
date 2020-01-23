package getmonster;

public class GetmonPlay {

	public static void main(String[] args) {
		System.out.println("あなたは最強のモンスターマスターを目指しています");
		System.out.println("10匹のモンスターが次々現れますので捕獲玉を使って捕獲しよう");
		System.out.println("モンスターを捕獲できれば、モンスター毎に設定されたポイントを取得できます");
		System.out.println("３種類ある捕獲玉を使って、高得点を目指そう");
		System.out.println("--------------------------------------------------------------------------");
		
		Adventure adventure = new Adventure();
		adventure.Input_Monster();
		adventure.Input_Ball();
		adventure.SubAction();
		

	}

}