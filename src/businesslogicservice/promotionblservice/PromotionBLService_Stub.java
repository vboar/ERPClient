/**
 * 促销策略制定逻辑桩
 * date 2014/10/25
 * @author chengcheng
 */
package businesslogicservice.promotionblservice;

import java.util.ArrayList;

import util.ResultMessage;
import vo.CustomerGiftVO;
import vo.SpecialOfferVO;
import vo.TotalGiftVO;

public class PromotionBLService_Stub implements PromotionBLService {

	@Override
	public ResultMessage createByVip(CustomerGiftVO vo) {
		
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage createByCommodity(SpecialOfferVO vo) {
		
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage createByPrice(TotalGiftVO vo) {
		
		return ResultMessage.SUCCESS;
	}

	@Override
	public ArrayList<CustomerGiftVO> findByVip() {
		ArrayList<CustomerGiftVO> voList=new ArrayList<CustomerGiftVO>();
		voList.add(new CustomerGiftVO("00001",5,null,100.0,10.0,"2014/10/23","2014/10/25",false));
		
		return voList;
	}

	@Override
	public ArrayList<SpecialOfferVO> findByCommodity() {
		ArrayList<SpecialOfferVO> voList=new ArrayList<SpecialOfferVO>();
		voList.add(new SpecialOfferVO("00002", null, 100.0, "2014/10/23","2014/10/25",false));
		
		return voList;
	}

	@Override
	public ArrayList<TotalGiftVO> findByPrice() {
		ArrayList<TotalGiftVO> voList=new ArrayList<TotalGiftVO>();
		voList.add(new TotalGiftVO("00002",10000.0,null,10.0,10.0,"2014/10/23","2014/10/25",false));
		return voList;
	}

	@Override
	public ResultMessage update(CustomerGiftVO vo) {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage update(SpecialOfferVO vo) {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage update(TotalGiftVO vo) {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCESS;
	}

}
