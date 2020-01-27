import java.util.Random;
// 回復優先メソッド
public class SavingStrategy extends Strategy{



	Random random = new Random();


	public void StartegyAction(Player activePlayer, Party myParty,Party enemyParty){

		// 能動側が戦士の時
		if (activePlayer instanceof Fighter) {
			activePlayer.Attack(activePlayer, enemyParty.AttackTarget());
			return;
		}

		// 能動側が魔術師の時
		if (activePlayer instanceof Wizard) {
			//攻撃
			activePlayer.Attack(activePlayer, enemyParty.AttackTarget());
			return;

		}

		if (activePlayer instanceof Priest) {
		// 能動側が僧侶の時
			if (!myParty.HealMembers(20).isEmpty()) {
				//　回復する味方がいるとき
				if 	(activePlayer.Heal(activePlayer, myParty.HealMembers(20))) {
					// 回復魔法を書けることができたとき
					return;
				}
			}

			if (!myParty.DebuffMembers().isEmpty()) {
				//　状態異常魔法をかけられる敵がいるとき
				Player passivePlayer = myParty.DebuffMembers().get(RandomGenerator.RandomRange(0, myParty.DebuffMembers().size()));

				if (activePlayer.Debuff(activePlayer, passivePlayer)) {
					return;
				}
			}



			activePlayer.Attack(activePlayer, enemyParty.AttackTarget());
			return;

		}

		// 能動側が勇者の時
		if (activePlayer instanceof Hero) {
			if (!myParty.HealMembers(20).isEmpty()) {

				if (activePlayer.Heal(activePlayer, myParty.HealMembers(20))) {
					// 回復魔法をかける
					return;
				}

			}

			activePlayer.Attack(activePlayer, enemyParty.AttackTarget());
			return;
		}
	}

}
