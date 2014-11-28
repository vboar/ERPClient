package businesslogic.paymentbl;

import java.util.ArrayList;

import util.DocumentStatus;
import util.ResultMessage;
import vo.PaymentVO;
import vo.TransferLineItemVO;
import businesslogicservice.paymentblservice.PaymentBLService;

public class PaymentController implements PaymentBLService{
	Payment p=new Payment();
	
	@Override
	public ResultMessage create(PaymentVO vo){		
		return p.create(vo);
	}

	@Override
	public ArrayList<PaymentVO> show(){	
		return p.show();
	}

	@Override
	public ResultMessage update(ArrayList<TransferLineItemVO> transferlist,String id,String customerId,double total){
		return p.update(transferlist,id,customerId,total);
	}

	@Override
	public ArrayList<PaymentVO> findByStatus(DocumentStatus status){
		return p.findByStatus(status.ordinal());
	}

	@Override
	public ArrayList<PaymentVO> findById(String id){
		return p.findById(id);
	}

	@Override
	public ArrayList<PaymentVO> findByTime(String time1, String time2){
		return p.findByTime(time1, time2);
	}

	@Override
	public ArrayList<PaymentVO> findByCustomer(String customerId){
		return p.findByCustomer(customerId);
	}

	@Override
	public ArrayList<PaymentVO> findByOperator(String operator){
		return p.findByOperator(operator);
	}

}
