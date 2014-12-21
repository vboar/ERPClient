/**
 * initial逻辑类
 * @author Vboar
 * @date 2014/11/14
 */

package businesslogic.initialbl;

import dataservice.datafactoryservice.DataFactoryImpl;
import dataservice.initialdataservice.InitialDataService;
import po.InitialPO;
import util.ResultMessage;
import vo.InitialVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Initial {

	private InitialDataService data;

	public Initial() {
		try {
			data = DataFactoryImpl.getInstance().getInitialData();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public ResultMessage createLog(String content) {
		//TODO
		return null;
	}

	public ArrayList<InitialVO> show() {
		try {
			return poToVoAll(data.show());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	private InitialVO poToVo(InitialPO po) {
		InitialVO vo = new InitialVO(po.getId(), po.getName());
		return vo;
	}

	private InitialPO voToPo(InitialVO vo) {
		return new InitialPO(vo.id, vo.name);
	}

	private ArrayList<InitialVO> poToVoAll(ArrayList<InitialPO> poList) {
		ArrayList<InitialVO> list = new ArrayList<InitialVO>();
		for(InitialPO po: poList) {
			list.add(poToVo(po));
		}
		return list;
	}

	private ArrayList<InitialPO> voToPoAll(ArrayList<InitialVO> voList) {
		ArrayList<InitialPO> list = new ArrayList<InitialPO>();
		for(InitialVO vo: voList) {
			list.add(voToPo(vo));
		}
		return list;
	}
	
}
