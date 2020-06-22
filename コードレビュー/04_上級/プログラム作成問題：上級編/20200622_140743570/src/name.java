import java.util.Scanner;
public class name {


	public static void main(String[] args ){
		// TODO 自動生成されたメソッド・スタブ


		Player Player1 ;
		Player Player2 ;

		int JobNo;
		String naem;
		Scanner ac=new Scanner(System.in);

		//自分
		System.out.print("プレイヤー1（あなた）の名前を入力してください。:");
		naem=ac.next();
		System.out.println("\n職業を選択してくください。");
		System.out.print("\n1：戦士\n2：魔法使い\n3：僧侶\n入力：");
		JobNo=ac.nextInt();

		//職業選択
		Player1=PlayerJob(JobNo,naem);
		System.out.println("\nステータス");
		System.out.println("\n職業："+Player1.Job()+"\n名前："+Player1.Name()+"\n体力："+Player1.Hp()+"\n魔力："+Player1.Mp()+"\n力："+Player1.Str()+"\n防御力："+Player1.Def()+"\n幸運："+Player1.Luck()+"\n俊敏性："+Player1.Agi());
		Player1.Hp_m( (Player1.Hp()));
		Player1.Mp_m( (Player1.Mp()));
		Player1.State_P("");

		//相手
		System.out.print("\n\nプレイヤー2（NPC）の名前を入力してください。:");
		naem=ac.next();
		System.out.println("職業を選択してくください。");
		System.out.print("\n1：戦士\n2：魔法使い\n3：僧侶\n入力：");
		JobNo=ac.nextInt();

		//職業選択
		Player2=PlayerJob(JobNo,naem);
		System.out.println("\nステータス");
		System.out.println("\n職業："+Player2.Job()+"\n名前："+Player2.Name()+"\n体力："+Player2.Hp()+"\n魔力："+Player2.Mp()+"\n力："+Player2.Str()+"\n防御力："+Player2.Def()+"\n幸運："+Player2.Luck()+"\n俊敏性："+Player2.Agi());
		System.out.println("\n\n===バトル開始===");
		Player2.Hp_m( (Player2.Hp()));
		Player2.Mp_m( (Player2.Mp()));
		Player2.State_P("");
		int i=0;
		//バトル
		while (i <= 200) {

			i++;
			System.out.println("\n\n✨✨✨✨✨✨✨"+i+"ターン目✨✨✨✨✨✨✨\n");
			Player.Attack_S(Player1,Player2);

			//毒判定
			if(Player1.State_p()=="毒") {
				System.out.println("\n\n☠☠☠"+Player1.Name()+"は毒により20のダメージを受ける☠☠☠");
				Player1.Poison_state();
			}

			//毒判定
			if(Player2.State_p()=="毒"){
				Player2.Poison_state();
				System.out.println("\n\n☠☠☠"+Player2.Name()+"は毒により20のダメージを受ける☠☠☠");
			}

			//勝敗判定
			if(Player1.Hp()<=0&&Player2.Hp()<=0){
				System.out.println("\n"+Player2.Name()+"と"+Player1.Name()+"が力尽きた.....");
				System.out.println("\n引き分け");
				break;
			}else if(Player1.Hp()<=0){
				System.out.println("\n"+Player2.Name()+"の一撃により"+Player1.Name()+"は滅びた...");
				System.out.println("\n"+Player2.Name()+"の勝利");
				break;
			}else if(Player2.Hp()<=0){
				System.out.println("\n"+Player1.Name()+"の一撃により"+Player2.Name()+"は滅びた...");
				System.out.println("\n"+Player1.Name()+"の勝利");
				break;
			}
			System.out.println("\n\n＝＝＝＝＝"+i+"ターン目終了＝＝＝＝＝＝\n");

			System.out.println("\n"+Player1.Name()+"のステータス\n");
			System.out.println("\n職業："+Player1.Job()+"\n体力："+Player1.Hp_ms()+"/"+Player1.Hp()+"\n魔力："+Player1.Mp_ms()+"/"+Player1.Mp());


			System.out.println("\n\n"+Player2.Name()+"のステータス\n");
			System.out.println("\n職業："+Player2.Job()+"\n体力："+Player2.Hp_ms()+"/"+Player2.Hp()+"\n魔力："+Player2.Mp_ms()+"/"+Player2.Mp());


		}
	}



	//職業選択
	public static Player PlayerJob(int JobNo ,String naem) {
		Player Player_D=null;
		switch (JobNo) {
		case 1:

			Fighter fighter=new Fighter();
			Player_D=fighter.Fighter_status(naem);
			break;

		case 2:

			Wizard wizard = new Wizard();
			Player_D=wizard.Wizard_status(naem);
			break;

		case 3:

			Priest priest=new Priest();
			Player_D =priest.Priest_status(naem);
			break;

		case 4:

			Shield shield=new Shield();
			Player_D=shield.Shield_status(naem);
			break;
			}
	return Player_D;
	}

}


