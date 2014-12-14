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
		return c.add(vo);
	}

	@Override
	public ArrayList<CashVO> show() {
		return c.show();
	}

	@Override
	public ArrayList<CashVO> findByStatus(DocumentStatus status) {
		return c.findByStatus(status.ordinal());
	}
	
	public String createId(){
		return c.createId();
	}

	@Override
	public ArrayList<CashVO> findByTime(String time1, String time2) {
		return c.findByTime(time1, time2);
	}

	@Override
	public ArrayList<CashVO> findById(String id) {
		return c.findById(id);
	}
	
	public CashVO getById(String id){
		return c.getById(id);
	}

}
