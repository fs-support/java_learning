package MonsterMaster;
import java.util.ArrayList;
import java.util.Scanner;

class Player {
    Scanner sc = new Scanner(System.in);

    /*
    プレイヤーの行動
    (1)ノーマル獲得玉を使う(残りｘ個。捕獲成功率：ｘｘ％)
    (2)スーパー獲得玉を使う(残りｘ個。捕獲成功率：ｘｘ％)
    (3)ミラクル獲得玉を使う(残りｘ個。捕獲成功率：ｘｘ％)
    (4)モンスターを見逃す
    */
    void PlayerAction(int allBallNumber, int nowMonster, ArrayList<Monster> monsterList, ArrayList<CaptureBall> captureBallList, ArrayList<Monster> captureMonsterList)
    {
        String answer;      //行動選択用文字

        while(true)
        {
            for(int i=0;i<captureBallList.size();i++)
            {
                System.out.println("("+(i+1)+")"+captureBallList.get(i).getName()+"を使う(残り"+captureBallList.get(i).getCount()+"個  捕獲成功率:"+captureBallList.get(i).getCorrect()+"％)");
            }
            System.out.println("("+(captureBallList.size()+1)+")モンスターを見逃す\n");

            //paiza.ioだと動かなかった
            answer = sc.next();
            
            //入力番号に応じて行動
            if(answer.equals("1")||answer.equals("2")||answer.equals("3"))
            {
            	//ボールが残っていれば投げる
            	if(captureBallList.get(Integer.parseInt(answer)-1).getCount()>0)
            	{
		            System.out.println(captureBallList.get(Integer.parseInt(answer)-1).getName()+"をなげた!");
		            captureBallList.get(Integer.parseInt(answer)-1).Use();
		          
		            //捕獲判定
		            if(monsterList.get(nowMonster).Judgement(captureBallList.get(Integer.parseInt(answer)-1).getCorrect()))
		            {
		                System.out.println("捕獲成功！！\n");
		                captureMonsterList.add(monsterList.get(nowMonster));	//捕獲したモンスターの情報を記憶する
		                break;
		            } else {
		                System.out.println("捕獲失敗・・・\n");
		            }
            	} else {
            		System.out.println("もうボールがない！\n");
            	}
            } else if(answer.equals("4"))
            {
            	System.out.println("モンスターを見逃した！\n");
            	break;
            }
            //ボール総数更新
            allBallNumber = captureBallList.get(0).getCount()+captureBallList.get(1).getCount()+captureBallList.get(2).getCount();

            if(allBallNumber == 0)
            {
                System.out.println("ボールがなくなった！\n");
                break;
            }
        }
    }
}