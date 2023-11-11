package problems;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import users.RANK;

public class ProblemRankManager {
	
	// ������ : ���� ������ �ڸ�Ʈ ���� ���, ProblemRankDB�� ������ ��� (11.10)
	
	// Key = ���� ��ȣ, Value = Rank ����Ʈ
	private static HashMap<Integer, List<RANK>> ProblemRankMap = new HashMap<>();
	
	// ���̵� �⿩�� �߰��ϴ� �Լ�
	public static boolean AddProblemRank(int ID, RANK Rank) {
		if (ProblemRankMap.containsKey(ID)) {        
			List<RANK> RankList = ProblemRankMap.get(ID);
            RankList.add(Rank);
        } 
		else {                
            List<RANK> RankList = new ArrayList<>();
            RankList.add(Rank);
            ProblemRankMap.put(ID, RankList);
        }
		return true;
	}
	
	// ���̵��� ����Ͽ� ���̵� �缳���ϴ� �Լ�
	public static boolean CalcProblemRank(int ID) {
		Problem plbm = ProblemDBManager.FindProblem(ID);
		List<RANK> RankList = ProblemRankMap.get(ID);
		
		if (RankList.size() % 10 == 0) {
			int sum = 0;
		    for (RANK rank : RankList) {
		        sum += rank.getRequireRankPoint();
		    }
		    int avg = (Math.round((int)((sum / RankList.size()) * 100))) / 100;
		    
		    if (avg == 0) {
		    	plbm.setProblemRank(RANK.RANK5);
		    }
		    else if(avg == 100) {
		    	plbm.setProblemRank(RANK.RANK4);
		    }
		    else if(avg == 200) {
		    	plbm.setProblemRank(RANK.RANK3);
		    }
		    else if(avg == 300) {
		    	plbm.setProblemRank(RANK.RANK3);
		    }
		    else if(avg == 400) {
		    	plbm.setProblemRank(RANK.RANK2);
		    }
		    else {
		    	plbm.setProblemRank(RANK.RANK1);
		    }
		    ProblemDBManager.ChangeProblem(ID, plbm);
		    ProblemDBManager.AddProblem(ID, plbm);
		    return true;
		}
		else {
			return false;
		}	
	}
	
}