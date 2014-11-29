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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<PaymentVO> findByStatus(DocumentStatus status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<PaymentVO> show(String time1, String time2) {
		// TODO Auto-generated method stub
		return null;
	}
	


}
