/**
 * 收付款单数据处理驱动程序
 * @author JaneLDQ
 * @date 2014/10/25
 */
package dataservice.paymentdataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.PaymentPO;
import po.TransferLineItemPO;
import util.DocumentStatus;
import util.DocumentType;

public class PaymentDataServiceTxtFileImpl_Driver {

	public void drive(PaymentDataService paymentDataService) throws RemoteException{
		
		ArrayList<TransferLineItemPO> transferList = new ArrayList<TransferLineItemPO>();
		transferList.add(new TransferLineItemPO("工商银行账户1",1000,"无"));
		PaymentPO po = new PaymentPO("FKD-20141025-00001","2014/10/25","00002","雷神托尔","金刚狼",
				transferList,700,DocumentStatus.PASSED, false,DocumentType.PAYMENT);
		
		System.out.println("收付款单数据处理消息：\n");
		paymentDataService.init();
		paymentDataService.insert(po);
		paymentDataService.update(po);
		paymentDataService.show();
		paymentDataService.findById("FKD-20141025-00001");
		paymentDataService.findByCustomer("00002");
		paymentDataService.findByOperator("王小明");
		paymentDataService.findByTime("2014/09/25", "2014/10/25");
		paymentDataService.finish();
	}
}
