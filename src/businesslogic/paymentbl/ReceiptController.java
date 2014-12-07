package businesslogic.paymentbl;

import businesslogicservice.paymentblservice.PaymentBLService;
import util.DocumentStatus;
import util.ResultMessage;
import vo.PaymentVO;

import java.util.ArrayList;

public class ReceiptController implements PaymentBLService {
	Receipt receipt = new Receipt();
	
	@Override
	public ResultMessage create(PaymentVO vo) {
		return receipt.add(vo);
	}

	@Override
	public ArrayList<PaymentVO> findByStatus(DocumentStatus status) {
		return receipt.findByStatus(status.ordinal());
	}

	@Override
	public ArrayList<PaymentVO> show() {
		return receipt.show();
	}

	@Override
	public String createId(){
		return receipt.createId();
	}

	@Override
	public ArrayList<PaymentVO> findByTime(String time1, String time2) {
		return receipt.findByTime(time1, time2);
	}

	@Override
	public ArrayList<PaymentVO> findById(String id) {
		return receipt.findById(id);
	}

	@Override
	public ArrayList<PaymentVO> findByCustomer(String customerId) {
		return receipt.findByCustomer(customerId);
	}

	@Override
	public ArrayList<PaymentVO> findByOperator(String operator) {
		return receipt.findByOperator(operator);
	}
}
