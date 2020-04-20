package namebatoler;

import java.util.ArrayList;

public class Operation {
	
	Pulandefo pulandefo = new Pulandefo();
	
	    //攻撃するプレイヤー
	    protected Player attackplayer;
	    //作戦NO
		protected int operationNO;
		//作戦名
		protected String operationName;
		//作戦内容
		protected String operationcontent;
		
		protected ArrayList<Operation> pulansList;
		
		//コンストラクタ
		Operation(){
			pulansList = new ArrayList<Operation>();
		}
		
		
		//GetterSetter
		//攻撃するプレイヤーを取得
		public Player GetAttackPlayer(){
			return attackplayer;
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
		
		//作戦をArrsyListで取得する
		ArrayList<Operation> GetPulans(){
			pulansList.add(pulandefo);
			return pulansList;
		}
		
		//---------------------------------------
//		//作戦の作成
//		protected void MakeOperation(){
//			pulansList.add(pulandefo);
//		}
//		
		
		//攻撃する相手を選ぶ
		protected Player Choosedefender(Player attackplayer){
			return attackplayer;
		}
		
		//使う魔法を選ぶ
		protected void Choosemagic(){
			
		}
}
