/**
 * @author chengcheng
 * @date 2014/11/14
 */

package businesslogic.promotionbl;

import java.util.ArrayList;

import util.ResultMessage;
import vo.CustomerGiftVO;
import vo.SpecialOfferVO;
import vo.TotalGiftVO;
import businesslogicservice.promotionblservice.PromotionBLService;

public class PromotionController implements PromotionBLService {

	@Override
	public ResultMessage createByVip(CustomerGiftVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage createByCommodity(SpecialOfferVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage createByPrice(TotalGiftVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<CustomerGiftVO> findByVip() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<SpecialOfferVO> findByCommodity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<TotalGiftVO> findByPrice() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage update(CustomerGiftVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage update(SpecialOfferVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage update(TotalGiftVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

}
