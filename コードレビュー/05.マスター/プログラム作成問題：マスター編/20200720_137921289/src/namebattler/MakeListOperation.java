package namebattler;

import java.util.ArrayList;

public class MakeListOperation {
	
	ArrayList<Operation> pulansList = new ArrayList<Operation>();
	
	//作戦
	Pulandefolt pulandefolt = new Pulandefolt();
	PulanMagicpriority pulanmagicpriority = new PulanMagicpriority();
	PulanPhysicalPriority pulanphysicalpriority = new PulanPhysicalPriority();
	PulantargetHighHP pulantargethighhp = new PulantargetHighHP();
	PulantargetLowHP pulantargetlowhp = new PulantargetLowHP();
	
	MakeListOperation(){
	 
		pulansList = new ArrayList<Operation>();
		Inputoperationlist();
	}
	
	ArrayList<Operation> GetoprationList(){	
		return pulansList;
	}
	
	public void Inputoperationlist(){
		pulansList.add(pulandefolt);
		pulansList.add(pulanmagicpriority);
		pulansList.add(pulanphysicalpriority);
		pulansList.add(pulantargethighhp);
		pulansList.add(pulantargetlowhp);
	}
	
	public void Printoperatiomlist(){
		for(int i = 0;i < pulansList.size();i++){
			System.out.printf(
					"作戦NO=%3d : 作戦名=%s : 作戦内容=%s\n",
					pulansList.get(i).operationNO, pulansList.get(i).operationName,pulansList.get(i).operationcontent);
		}
	}

}
