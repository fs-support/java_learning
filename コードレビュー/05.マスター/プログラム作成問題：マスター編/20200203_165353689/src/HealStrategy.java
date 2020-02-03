import java.util.Random;
// いのちだいじに
public class HealStrategy extends Strategy{



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

			if (!myParty.DebuffMembers().isEmpty()) {
				if (activePlayer.HealDebuff(activePlayer, myParty.DebuffMembers().get(0))) {
					return;
				}
			}
			//　回復する味方がいるとき
			if (!myParty.HealMembers(90).isEmpty()) {
				// 回復魔法を書けることができたとき
				if (activePlayer.HealHP(activePlayer, myParty.HealMembers(90))) {
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
			if (!myParty.HealMembers(90).isEmpty()) {

				if (activePlayer.HealHP(activePlayer, myParty.HealMembers(90))) {
					// 回復魔法をかける
					return;
				}

			}

			activePlayer.Attack(activePlayer, enemyParty.AttackTarget());
			return;
		}
	}

}
