package businesslogicservice.promotionblservice;

import java.util.ArrayList;

import util.ResultMessage;
import vo.CustomerGiftVO;

public interface CustomerGiftBLService {

	public ResultMessage create(CustomerGiftVO vo);
	
	public ArrayList<CustomerGiftVO> show();
	
	public ArrayList<CustomerGiftVO> findByValid(boolean valid);
	
	public ResultMessage update(CustomerGiftVO vo);
	
}
