package MonsterMaster;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws Exception {

        Random random = new Random();
        int nowMonster;      //現在出現中のモンスターの番号
        int allBallNumber;  //ボール総数

        //モンスターの情報を入力(名前,HP,攻撃,防御,出現率,捕獲率)
        ArrayList<Monster> monsterList = new ArrayList<Monster>();
        Monster monster = new Monster("ザコモン",30,20,20,30,72);
        monsterList.add(monster);
        monsterList.add(new Monster("フツモン",50,20,30,30,50));
        monsterList.add(new Monster("ツヨモン",100,50,30,25,28));
        monsterList.add(new Monster("ボスモン",100,50,50,10,25));
        monsterList.add(new Monster("レアモン",150,100,100,5,14));

        //ボールの情報を入力(名前,捕獲率,所持数)
        ArrayList<CaptureBall> captureBallList = new ArrayList<CaptureBall>();
        CaptureBall captureBall = new CaptureBall("ノーマル捕獲玉",0,6);
        captureBallList.add(captureBall);
        captureBallList.add(new CaptureBall("スーパー捕獲玉",20,3));
        captureBallList.add(new CaptureBall("ミラクル捕獲玉",50,1));

        //捕獲したモンスターの情報を記憶する
        ArrayList<Monster> captureMonsterList = new ArrayList<Monster>();

        //ボール総数を記憶する
        allBallNumber = captureBallList.get(0).getCount()+captureBallList.get(1).getCount()+captureBallList.get(2).getCount();

        //所持ボールが0個になるか10匹目を捕獲か見逃すと結果に移動
        for(int i = 1; i<=10; i++)
        {
            //出現するモンスター(Hp,攻撃,防御)をランダムに決める
            System.out.print(i+"匹目・・・");
            nowMonster = random.nextInt(monsterList.size());
            monsterList.get(nowMonster).MonsterDataPrint();

            //プレイヤーの行動
            Player player = new Player();
            player.PlayerAction(allBallNumber, nowMonster, monsterList, captureBallList, captureMonsterList);
            
            //ボール総数更新
            allBallNumber = captureBallList.get(0).getCount()+captureBallList.get(1).getCount()+captureBallList.get(2).getCount();

            //ボール総数が0だと終了
            if(allBallNumber <= 0)
            {
                System.out.println("ボールがなくなった！\n");
                break;
            }
        }
        System.out.println("捕獲したモンスター一覧");
        
        //合計捕獲ポイント計算
        int point = 0;
        
        for(int i=0;i<captureMonsterList.size();i++)
        {
            System.out.println(captureMonsterList.get(i).getName()+" 獲得ポイント:"+(captureMonsterList.get(i).getPoint()));
            point += captureMonsterList.get(i).getPoint();
        }
        System.out.println("合計捕獲ポイント："+point);
    }
}

