/**
 * 付款服务桩程序
 * @author JanelDQ
 *
 */
package businesslogicservice.paymentblservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import util.DocumentStatus;
import util.DocumentType;
import util.ResultMessage;
import vo.PaymentVO;
import vo.TransferLineItemVO;

public class PaymentBLService_Stub implements PaymentBLService {

	@Override
	public ResultMessage create(PaymentVO vo) {
		return ResultMessage.FAILED;
	}

	@Override
	public ArrayList<PaymentVO> show() {
		ArrayList<PaymentVO> list = new ArrayList<PaymentVO>();
		ArrayList<TransferLineItemVO> transferList = new ArrayList<TransferLineItemVO>();
		transferList.add(new TransferLineItemVO("工商银行账户1",1000,"无"));
		list.add(new PaymentVO("FKD-"+"-00001","00002","雷神托尔","金刚狼",
				transferList,700,DocumentStatus.PASSED, DocumentType.PAYMENT));
		return list;
	}

	@Override
	public ArrayList<PaymentVO> findByStatus(DocumentStatus status) {
		ArrayList<PaymentVO> list = new ArrayList<PaymentVO>();
		ArrayList<TransferLineItemVO> transferList = new ArrayList<TransferLineItemVO>();
		transferList.add(new TransferLineItemVO("工商银行账户1",1000,"无"));
		list.add(new PaymentVO("FKD-20141025-00001","00002","雷神托尔","金刚狼",
				transferList,700,status, DocumentType.PAYMENT));
		return list;
	}

	@Override
	public ArrayList<PaymentVO> findById(String id) {
		ArrayList<PaymentVO> list = new ArrayList<PaymentVO>();
		ArrayList<TransferLineItemVO> transferList = new ArrayList<TransferLineItemVO>();
		transferList.add(new TransferLineItemVO("工商银行账户1",1000,"无"));
		list.add(new PaymentVO(id,"00002","雷神托尔","金刚狼",
				transferList,700,DocumentStatus.PASSED, DocumentType.PAYMENT));
		return list;
	}

	@Override
	public ArrayList<PaymentVO> findByTime(String time1, String time2)
			throws RemoteException {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ArrayList<PaymentVO> findByCustomer(String customerId)
			throws RemoteException {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ArrayList<PaymentVO> findByOperator(String operator)
			throws RemoteException {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ResultMessage update(ArrayList<TransferLineItemVO> transferlist,
			String id, String customerId, double total) throws RemoteException {
		return ResultMessage.SUCCESS;
	}

}
