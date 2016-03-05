package businesslogicservice.promotionblservice;

import java.util.ArrayList;

import util.ResultMessage;
import vo.TotalGiftVO;

public interface TotalGiftBLService {

	public ResultMessage create(TotalGiftVO vo);
	
	public ArrayList<TotalGiftVO> show();
	
	public ArrayList<TotalGiftVO> findByValid(boolean valid);
	
	public ResultMessage update(TotalGiftVO vo);
	
	public String createId();
	
}
