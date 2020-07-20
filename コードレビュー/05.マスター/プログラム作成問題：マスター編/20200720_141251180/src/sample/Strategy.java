package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//作戦クラス(各種作戦の基底クラス)
public class Strategy {
	// =======================
	// フィールド変数
	// =======================
	protected String strategyname; // 作戦名

	// =======================
	// コンストラクタ
	// =======================

	// =======================
	// Getter / Setter
	// =======================
	/**
	 * 作戦名を取得する
	 *
	 * @return 作戦名
	 */
	String GetStrategy() {
		return this.strategyname;
	}

	// =======================
	// public メソッド
	// =======================

	/**
	 * 作戦に応じた行動をする
	 *
	 * @param attacker
	 *            : 対象プレイヤー
	 * @param party
	 * @param friend
	 */
	public void action(Player attacker, Party party,Party friend) {
		// 作戦ごとにオーバーライドして処理を記述してください
	}

//作戦1：力持ちを狙え
	class Strategy1 extends Strategy {
		public Strategy1(String s) {
			// TODO 自動生成されたコンストラクター・スタブ
			this.strategyname = "力持ちを狙え";
		}

		@Override
		public void action(Player attacker, Party party,Party friend){
			List<Player> enemy = new ArrayList<Player>();
			for(int i = 0; i <party.size(); i++){
				enemy.add(party.get(i));
			}
			for(int i = 0; i < enemy.size(); i++){
				for(int j = 0; j < party.size() - i - 1; j++){
					if(enemy.get(j).GetSTR() < enemy.get(j + 1).GetSTR()){
						Player a = enemy.get(j);
						enemy.set(j, enemy.get(j + 1));
						enemy.set(j + 1,  a);
					}
				}
			}
			int k = 0;
			for(int i = 0; i < enemy.size(); i++){
				if(enemy.get(k).GetHP() <= 0){
					k++;
					if(k == enemy.size()){
						GameManager.end(party);
					}
				}else if(enemy.get(k).GetHP() > 0){
					attacker.attack(enemy.get(k),attacker,friend,party,strategyname);
					break;
				}else{
					GameManager.end(party);
				}
			}
		}
	}

		// 作戦2：体力のないやつを狙え
		class Strategy2 extends Strategy {
			public Strategy2(String s) {
				// TODO 自動生成されたコンストラクター・スタブ
				this.strategyname = "体力のないやつを狙え";
			}

			@Override
			public void action(Player attacker, Party party,Party friend){
				List<Player> enemy = new ArrayList<Player>();
				for(int i = 0; i <party.size(); i++){
					enemy.add(party.get(i));
				}
				for(int i = 0; i < enemy.size(); i++){
					for(int j = 0; j < party.size() - i - 1; j++){
						if(enemy.get(j).GetMAXHP() < enemy.get(j + 1).GetMAXHP()){
							Player a = enemy.get(j);
							enemy.set(j, enemy.get(j + 1));
							enemy.set(j + 1,  a);
						}
					}
				}
				int k = 0;
				for(int i = 0; i < enemy.size(); i++){
					if(enemy.get(k).GetHP() <= 0){
						k++;
						if(k == enemy.size()){
							GameManager.end(party);
						}
					}else if(enemy.get(k).GetHP() > 0){
						attacker.attack(enemy.get(k),attacker,friend,party,strategyname);
						break;
					}else{
						GameManager.end(party);
					}
				}
			}
		}

		// 作戦3：魔法使いから狙え
		class Strategy3 extends Strategy {
			public Strategy3(String s) {
				// TODO 自動生成されたコンストラクター・スタブ
				this.strategyname = "魔法使いから狙え";
			}

			@Override
			public void action(Player attacker, Party party,Party friend){
				List<Player> enemy = new ArrayList<Player>();
				for(int i = 0; i <party.size(); i++){
					enemy.add(party.get(i));
				}
				for(int i = 0; i < enemy.size(); i++){
					for(int j = 0; j < enemy.size() - i - 1; j++){
						if(enemy.get(j).GetJob() == "魔法使い" ){
							Player a = enemy.get(j);
							enemy.set(j, enemy.get(j + 1));
							enemy.set(j + 1,  a);
						}
					}
				}
				int k = 0;
				for(int i = 0; i < enemy.size(); i++){
					if(enemy.get(k).GetHP() <= 0){
						k++;
						if(k == enemy.size()){
							GameManager.end(party);
						}
					}else if(enemy.get(k).GetHP() > 0){
						attacker.attack(enemy.get(k),attacker,friend,party,strategyname);
						break;
					}else{
						GameManager.end(party);
					}
				}
			}
		}
		// 作戦4：魔法は節約
		class Strategy4 extends Strategy {
			public Strategy4(String s) {
				// TODO 自動生成されたコンストラクター・スタブ
				this.strategyname = "魔法は節約";
			}

			@Override
			public void action(Player attacker, Party party,Party friend){
				List<Player> enemy = new ArrayList<Player>();
				for(int i = 0; i <party.size(); i++){
					enemy.add(party.get(i));
				}

				int k = 0;
				int e = party.size();
				Random random = new Random();
				int re = random.nextInt(e);
				for(int i = 0; i < enemy.size(); i++){
					if(enemy.get(k).GetHP() <= 0){
						k++;
						if(k == enemy.size()){
							GameManager.end(party);
						}
					}else if(enemy.get(k).GetHP() > 0){
						attacker.attack(enemy.get(re),attacker,friend,party,strategyname);
						break;
					}else{
						GameManager.end(party);
					}
				}
			}
		}
		// 作戦5：バランスが大事
				class Strategy5 extends Strategy {
					public Strategy5(String s) {
						// TODO 自動生成されたコンストラクター・スタブ
						this.strategyname = "バランスが大事";
					}

					@Override
					public void action(Player attacker, Party party,Party friend){
						List<Player> enemy = new ArrayList<Player>();
						for(int i = 0; i <party.size(); i++){
							enemy.add(party.get(i));
						}

						int k = 0;
						int e = party.size();
						Random random = new Random();
						int re = random.nextInt(e);
						for(int i = 0; i < enemy.size(); i++){
							if(enemy.get(k).GetHP() <= 0){
								k++;
								if(k == enemy.size()){
									GameManager.end(party);
								}
							}else if(enemy.get(k).GetHP() > 0){
								attacker.attack(enemy.get(re),attacker,friend,party,strategyname);
								break;
							}else{
								GameManager.end(party);
							}
						}
					}
				}
	}

