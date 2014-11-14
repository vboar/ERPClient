/**
 * 现金费用单数据处理驱动程序
 * @author JaneLDQ
 * @date 2014/10/25
 */
package dataservice.paymentdataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CashPO;
import po.ClauseLineItemPO;
import util.DocumentStatus;
import util.DocumentType;

public class CashDataServiceTxtFileImpl_Driver {

	public void drive(CashDataService cashDataService) throws RemoteException{
		
		ArrayList<ClauseLineItemPO> clauseList = new ArrayList<ClauseLineItemPO>();
		clauseList.add(new ClauseLineItemPO("浩克",700,"无"));
		CashPO po = new CashPO("XJFYD-20141025-00001","2014/10/25","金刚狼","工商银行账户1",clauseList,
				DocumentStatus.PASSED, false,DocumentType.CASH);
		
		System.out.println("现金费用单数据处理消息：\n");
		cashDataService.insert(po);
		cashDataService.update(po);
		cashDataService.show();
		cashDataService.findById("XJFYD-20141025-00001");
		cashDataService.findById("XJFYD-20141025-00002");
		cashDataService.findByTime("2014/10/25", "2014/10/26");
	}
}
