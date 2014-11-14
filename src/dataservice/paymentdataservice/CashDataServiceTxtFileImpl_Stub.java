/**
 * 现金费用单数据处理桩程序
 * @author JanelDQ
 * @date 2014/10/25
 */
package dataservice.paymentdataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CashPO;
import po.ClauseLineItemPO;
import util.DocumentStatus;
import util.DocumentType;

public class CashDataServiceTxtFileImpl_Stub implements CashDataService {

	@Override
	public void insert(CashPO po) throws RemoteException {
		System.out.println("Insert Succeed!");
	}

	@Override
	public void update(CashPO po) throws RemoteException {
		if(po.getId().equals("XJFYD-20141023-00001")){
			System.out.println("Update Succeed!"); return;
		}	
		System.out.println("Update failed!");		
	}

	@Override
	public ArrayList<CashPO> show() throws RemoteException {
		ArrayList<CashPO> list = new ArrayList<CashPO>();
		ArrayList<ClauseLineItemPO> clauseList = new ArrayList<ClauseLineItemPO>();
		clauseList.add(new ClauseLineItemPO("浩克",700,"无"));
		list.add(new CashPO("XJFYD-20141025-00001","2014/10/25","金刚狼","工商银行账户1",clauseList,
				DocumentStatus.PASSED, false,DocumentType.CASH));
		System.out.println("Show Succeed!");
		return list;
	}

	@Override
	public ArrayList<CashPO> findById(String id) {
		ArrayList<CashPO> list = new ArrayList<CashPO>();
		if(id.equals("XJFYD-20141025-00001")){
			ArrayList<ClauseLineItemPO> clauseList = new ArrayList<ClauseLineItemPO>();
			clauseList.add(new ClauseLineItemPO("浩克",700,"无"));
			list.add(new CashPO("XJFYD-20141025-00001","2014/10/25","金刚狼","工商银行账户1",clauseList,
					DocumentStatus.PASSED, false,DocumentType.CASH));
			System.out.println("FindById Succeed!");
			return list;
		}
		System.out.println("FindById Failed!");
		return list;
	}

	@Override
	public ArrayList<CashPO> findByTime(String time1, String time2) {
		ArrayList<CashPO> list = new ArrayList<CashPO>();
		if(time1.equals("2014/10/25")){
			ArrayList<ClauseLineItemPO> clauseList = new ArrayList<ClauseLineItemPO>();
			clauseList.add(new ClauseLineItemPO("浩克",700,"无"));
			list.add(new CashPO("XJFYD-20141025-00001",time1,"金刚狼","工商银行账户1",clauseList,
					DocumentStatus.PASSED, false,DocumentType.CASH));
			System.out.println("FindByTime Succeed!");
			return list;
		}
		System.out.println("FindByTime Failed!");
		return list;
	}
	
}
