package businesslogic.paymentbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import util.DocumentStatus;
import util.ResultMessage;
import vo.PaymentVO;
import vo.TransferLineItemVO;
import businesslogicservice.paymentblservice.PaymentBLService;

public class ReceiptController implements PaymentBLService {

	@Override
	public ResultMessage create(PaymentVO vo) throws RemoteException {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ArrayList<PaymentVO> show() throws RemoteException {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ResultMessage update(ArrayList<TransferLineItemVO> transferlist,
			String id, String customerId, double total) throws RemoteException {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ArrayList<PaymentVO> findByStatus(DocumentStatus status)
			throws RemoteException {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ArrayList<PaymentVO> findById(String id) throws RemoteException {
		// TODO 自动生成的方法存根
		return null;
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


}
