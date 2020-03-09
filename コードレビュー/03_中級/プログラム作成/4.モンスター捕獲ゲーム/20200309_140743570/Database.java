package k;

public class Database {
	//捕獲玉の配列
	static	CaptureBall CaptureD[]=new CaptureBall[3];
	//捕獲玉の情報
	public static void DataCapture(){
		CaptureD[0]=(new CaptureBall("ノーマル捕獲玉",0,6));
		CaptureD[1]=(new CaptureBall("スーパー捕獲玉",20,3));
		CaptureD[2]=(new CaptureBall("ミラクル捕獲玉",50,1));
	}
	//モンスターの配列
		static	Monster MonsterD[]=new Monster[5];
	//モンスターの出現率リスト
		static	Monster Monsterl[]=new Monster[100];
		static int o=0;
	public static void	Monster() {
		//モンスターの情報
		MonsterD[0]=(new Monster("ザコモン",30,20,20,30,72));
		MonsterD[1]=(new Monster("フツモン",50,20,30,30,50));
		MonsterD[2]=(new Monster("ツヨモン",100,50,30,25,28));
		MonsterD[3]=(new Monster("ボスモン",100,50,50,10,25));
		MonsterD[4]=(new Monster("レアモン",150,100,100,5,14));
		for(int i=0;4>=i;i++) {
			for(int l=0;MonsterD[i].Encount()>l;l++){
				Monsterl[l+o]=MonsterD[i];
			}
			o=o+MonsterD[i].Encount();
		}

	}

}
