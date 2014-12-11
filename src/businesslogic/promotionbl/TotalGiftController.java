package businesslogic.promotionbl;

import java.util.ArrayList;

import util.ResultMessage;
import vo.TotalGiftVO;
import businesslogicservice.promotionblservice.TotalGiftBLService;

public class TotalGiftController implements TotalGiftBLService {

	TotalGiftPromotion tgp = new TotalGiftPromotion();
	
	@Override
	public ResultMessage create(TotalGiftVO vo) {
		return tgp.add(vo);
	}

	@Override
	public ArrayList<TotalGiftVO> show() {
		return tgp.showAll();
	}

	@Override
	public ArrayList<TotalGiftVO> findByValid(boolean valid) {
		// TODO 未实现
		return null;
	}

	@Override
	public ResultMessage update(TotalGiftVO vo) {
		return tgp.update(vo);
	}

	@Override
	public String createId() {
		return tgp.createId();
	}

}
