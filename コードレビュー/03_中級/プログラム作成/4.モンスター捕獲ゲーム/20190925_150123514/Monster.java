package MonsterMaster;
import java.util.Random;

class Monster {
    private String name; // 名前
    private int hp; // HP
    private int power; // 攻撃
    private int defense; // 防御
    private int encount; // 出現率
    private int capture; // 捕獲率

// コンストラクタ
    Monster(String name, int hp, int power, int defense, int encount, int capture)
    {
        this.name = name;
        this.hp = hp;
        this.power = power;
        this.defense = defense;
        this.encount = encount;
        this.capture = capture;
    }
    // 各種ステータスを取得
    String getName()
    {
        return name;
    }
    
    int getHP()
    {
        return hp;
    }

    int getPower()
    {
        return power;
    }

    int getDefense()
    {
        return defense;
    }

    int getEncount()
    {
        return encount;
    }

    int getCapture()
    {
        return capture;
    }
    
    /**
    * 捕獲ポイントを取得（式：(HP+攻撃+防御)*10）
    * @return 捕獲ポイント
    */
    int getPoint()
    {
        return (hp + power + defense)*10;
    }
    
    /**
    * 捕獲できたかどうかの判定を行う
    * @param correct 捕獲率の補正値
    * @return true: 捕獲成功 / false: 捕獲失敗
    */
    Boolean Judgement(int ballCorrect)
    {
        Random percent = new Random();
        if(capture+ballCorrect >= percent.nextInt(100))
        {
            return true;
        } else {
            return false;
        }
    }
    
    //モンスターの情報(名前、HP、攻撃、防御)を表示する
    void MonsterDataPrint()
    {
        System.out.println(name+"(HP:"+hp+"  攻撃:"+power+"  防御:"+defense+")が現れた\n");
    }
}