/**
 * 赠送单数据处理驱动程序
 * @author JaneLDQ
 * @date 2014/10/26
 */
package dataservice.presentdataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.PresentLineItemPO;
import po.PresentPO;
import util.DocumentStatus;
import util.DocumentType;

public class PresentDataServiceTxtFileImpl_Driver {

	public void drive(PresentDataService presentDataService) throws RemoteException{
		
		ArrayList<PresentLineItemPO> list2 = new ArrayList<PresentLineItemPO>();
		list2.add(new PresentLineItemPO("00001-00001", "飞利浦吊灯", "FLP01", 50));
		PresentPO po = new PresentPO("ZSD-20141023-00001", "21:29:32", "00001",
				"金刚狼",list2, DocumentStatus.NONCHECKED, DocumentType.PRESENT,false);
		
		presentDataService.insert(po);
		presentDataService.update(po);
		presentDataService.show();
		presentDataService.findById("ZSD-20141023-00001");
		presentDataService.findByOperator("金刚狼");
		presentDataService.findByTime("20140923", "20141024");
		
	}
}
