package data1A;

import java.util.ArrayList;

public class Trump {
		// TODO 自動生成されたメソッド・スタブ
		ArrayList<Card>Cardlist=new ArrayList<Card>();
	public  void Reset(){
		Card.Mark[] m_k= {Card.Mark.Cluba,Card.Mark.Hearts,Card.Mark.Diamonds,Card.Mark.Spades};

		for(int m=1;m<=4;m++) {
			for(int i=1;i<=13;i++) {
				Cardlist.add(new Card(i,m_k[m]));
			}
		}
	}
}
