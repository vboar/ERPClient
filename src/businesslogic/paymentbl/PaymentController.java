package businesslogic.paymentbl;

import java.util.ArrayList;

import util.DocumentStatus;
import util.ResultMessage;
import vo.PaymentVO;
import businesslogicservice.paymentblservice.PaymentBLService;

public class PaymentController implements PaymentBLService{
	
	Payment payment = new Payment();

	@Override
	public ResultMessage create(PaymentVO vo) {
		return payment.create(vo);
	}

	@Override
	public ArrayList<PaymentVO> findByStatus(DocumentStatus status) {
		return payment.findByStatus(status.ordinal());
	}

	@Override
	public ArrayList<PaymentVO> show() {
		return payment.show();
	}
	
	public String createId(){
		return payment.createId();
	}

	@Override
	public ArrayList<PaymentVO> findByTime(String time1, String time2) {
		return payment.findByTime(time1, time2);
	}

	@Override
	public ArrayList<PaymentVO> findById(String id) {
		return payment.findById(id);
	}

	@Override
	public ArrayList<PaymentVO> findByCustomer(String customerId) {
		return payment.findByCustomer(customerId);
	}

	@Override
	public ArrayList<PaymentVO> findByOperator(String operator) {
		return payment.findByOperator(operator);
	}

}
