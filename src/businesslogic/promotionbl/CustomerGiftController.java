package businesslogic.promotionbl;

import java.util.ArrayList;

import util.ResultMessage;
import vo.CustomerGiftVO;
import businesslogicservice.promotionblservice.CustomerGiftBLService;

public class CustomerGiftController implements CustomerGiftBLService {

	CustomerGiftPromotion cgp = new CustomerGiftPromotion();
	
	@Override
	public ResultMessage create(CustomerGiftVO vo) {
		ResultMessage result = cgp.add(vo);
		System.out.println(result);
		return result;
	}

	@Override
	public ArrayList<CustomerGiftVO> show() {
		return cgp.showAll();
	}

	@Override
	public ArrayList<CustomerGiftVO> findByValid(boolean valid) {
		// TODO 
		return null;
	}

	@Override
	public ResultMessage update(CustomerGiftVO vo) {
		return cgp.update(vo);
	}

	@Override
	public String createId() {
		return cgp.createId();
	}

}
