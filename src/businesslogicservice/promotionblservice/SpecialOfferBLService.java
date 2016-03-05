package businesslogicservice.promotionblservice;

import java.util.ArrayList;

import util.ResultMessage;
import vo.SpecialOfferVO;

public interface SpecialOfferBLService {

	public ResultMessage create(SpecialOfferVO vo);
	
	public ArrayList<SpecialOfferVO> show();
	
	public ArrayList<SpecialOfferVO> findByValid(boolean valid);
	
	public ResultMessage update(SpecialOfferVO vo);
	
	public String createId();
	
}
