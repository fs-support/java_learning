package namebattler;

import java.util.ArrayList;


public class Operation {

	    //作戦NO
		protected int operationNO;
		//作戦名
		protected String operationName;
		//作戦内容
		protected String operationcontent;
	    //攻撃されるプレイヤー
	    protected Player defender;
	    //使用する魔法
	    protected Magic Usemazic;
	    //魔法の使用有無（1：有、0；無）
		protected int OrNotmagic;
		//コンストラクタ
		Operation(){
		}
		
		
		//GetterSetter
		//攻撃するプレイヤーを取得
		public Player GetDefender(){
			return defender;
		}
		
		//作戦NOを取得
		public int GetOperationNO(){
			return operationNO;
		}
		
		//作戦名を取得
		public String GetOperationName(){
			return operationName;
		}
		//作戦内容を取得
		public String GetOperationContent(){
			return operationcontent;
		}
		//使用する魔法の取得
		public Magic Getusemazic(){
			return Usemazic;
		}
		//魔法の使用有無を取得
		public int GetOrNotmagic(){
			return OrNotmagic;
		}

		//--------------------------------
		// 魔法の種類を格納
		//Wizard用
		ArrayList<Magic> Wizardmaziclist = new ArrayList<Magic>();
		//Priest用
		ArrayList<Magic> Priestmaziclist = new ArrayList<Magic>();
		//Brave用
		ArrayList<Magic> Bravemaziclist = new ArrayList<Magic>();
		//共通回復魔法
		ArrayList<Magic> heellist = new ArrayList<Magic>();
		
		
		Fire fire = new Fire();
		Sander sander = new Sander();
		Poizun poizun = new Poizun();
		Paralysis paralysis = new Paralysis();
		Heel heel = new Heel();

		// 魔法の種類を格納
		public void MakeMazic() {
			Wizardmaziclist.add(fire);
			Wizardmaziclist.add(sander);
			Priestmaziclist.add(poizun);
			Priestmaziclist.add(paralysis);
			Bravemaziclist.add(fire);
			Bravemaziclist.add(sander);
			heellist.add(heel);
		}
		
		
		
		//攻撃する相手を選ぶ
		protected Player Choosedefender(Player attackplayer){
			return defender;
		}
		
		//使う魔法を選ぶ
		protected Magic Choosemagic(Player attackplayer,int trunNumber){
			return Usemazic;
		}
}
