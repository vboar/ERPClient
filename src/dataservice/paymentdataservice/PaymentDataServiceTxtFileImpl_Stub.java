/**
 * 收付款数据处理桩程序
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

public class PaymentDataServiceTxtFileImpl_Stub implements PaymentDataService {

	@Override
	public void insert(PaymentPO po) throws RemoteException {
		System.out.println("Insert Succeed!");
	}

	@Override
	public void update(PaymentPO po) throws RemoteException {
		if(po.getId().equals("FKD-20141025-00001")){
			System.out.println("Update Succeed!"); return;
		}
		System.out.println("Update Failed!"); 
	}

	@Override
	public ArrayList<PaymentPO> show() throws RemoteException {
		ArrayList<PaymentPO> list = new ArrayList<PaymentPO>();
		ArrayList<TransferLineItemPO> transferList = new ArrayList<TransferLineItemPO>();
		transferList.add(new TransferLineItemPO("工商银行账户1",1000,"无"));
		list.add(new PaymentPO("FKD-20141025-00001","2014/10/25","00002","雷神托尔","金刚狼",
				transferList,700,DocumentStatus.PASSED, false,DocumentType.PAYMENT));
		System.out.println("Show Succeed!");
		return list;
	}

	@Override
	public ArrayList<PaymentPO> findById(String id) {
		ArrayList<PaymentPO> list = new ArrayList<PaymentPO>();
		ArrayList<TransferLineItemPO> transferList = new ArrayList<TransferLineItemPO>();
		transferList.add(new TransferLineItemPO("工商银行账户1",1000,"无"));
		list.add(new PaymentPO(id,"2014/10/25","00002","雷神托尔","金刚狼",
				transferList,700,DocumentStatus.PASSED, false,DocumentType.PAYMENT));
		System.out.println("FindById Succeed!");
		return list;
	}

	@Override
	public ArrayList<PaymentPO> findByTime(String time1, String time2) {
		ArrayList<PaymentPO> list = new ArrayList<PaymentPO>();
		ArrayList<TransferLineItemPO> transferList = new ArrayList<TransferLineItemPO>();
		transferList.add(new TransferLineItemPO("工商银行账户1",1000,"无"));
		list.add(new PaymentPO("FKD-20141025-00001",time1,"00002","雷神托尔","金刚狼",
				transferList,700,DocumentStatus.PASSED, false,DocumentType.PAYMENT));
		System.out.println("FindByTime Succeed!");
		return list;
	}

	@Override
	public ArrayList<PaymentPO> findByCustomer(String customerId) {
		ArrayList<PaymentPO> list = new ArrayList<PaymentPO>();
		ArrayList<TransferLineItemPO> transferList = new ArrayList<TransferLineItemPO>();
		transferList.add(new TransferLineItemPO("工商银行账户1",1000,"无"));
		list.add(new PaymentPO("FKD-20141025-00001","2014/10/25",customerId,"雷神托尔","金刚狼",
				transferList,700,DocumentStatus.PASSED, false,DocumentType.PAYMENT));
		System.out.println("FindByCustomer Succeed!");
		return list;
	}

	@Override
	public ArrayList<PaymentPO> findByOperator(String operator) {
		ArrayList<PaymentPO> list = new ArrayList<PaymentPO>();
		ArrayList<TransferLineItemPO> transferList = new ArrayList<TransferLineItemPO>();
		transferList.add(new TransferLineItemPO("工商银行账户1",1000,"无"));
		list.add(new PaymentPO("FKD-20141025-00001","2014/10/25","00002","雷神托尔",operator,
				transferList,700,DocumentStatus.PASSED, false,DocumentType.PAYMENT));
		System.out.println("FindByOperator Succeed!");
		return list;
	}
	
}
