package businesslogic.paymentbl;

import java.util.ArrayList;

import util.DocumentStatus;
import util.ResultMessage;
import vo.CashVO;
import businesslogicservice.paymentblservice.CashBLService;

public class CashController implements CashBLService {
	Cash c=new Cash();
	
	@Override
	public ResultMessage create(CashVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<CashVO> show(String time1, String time2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<CashVO> findByStatus(DocumentStatus status) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String createId(){
		return c.createId();
	}
}
