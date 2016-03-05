package businesslogic.promotionbl;

import java.util.ArrayList;

import util.ResultMessage;
import vo.SpecialOfferVO;
import businesslogicservice.promotionblservice.SpecialOfferBLService;

public class SpecialOfferController implements SpecialOfferBLService {

	SpecialOfferPromotion sop = new SpecialOfferPromotion();
	
	@Override
	public ResultMessage create(SpecialOfferVO vo) {
		return sop.add(vo);
	}

	@Override
	public ArrayList<SpecialOfferVO> show() {
		return sop.showAll();
	}

	@Override
	public ArrayList<SpecialOfferVO> findByValid(boolean valid) {
		// TODO
		return null;
	}

	@Override
	public ResultMessage update(SpecialOfferVO vo) {
		return sop.update(vo);
	}

	@Override
	public String createId() {
		return sop.createId();
	}

}
