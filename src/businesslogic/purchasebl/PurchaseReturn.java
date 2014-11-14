package businesslogic.purchasebl;

import java.util.ArrayList;

import util.ResultMessage;
import vo.CommodityLineItemVO;
import vo.PurchaseVO;
import businesslogicservice.purchaseblservice.PurchaseBLService;

public class PurchaseReturn implements PurchaseBLService{

	@Override
	public ResultMessage add(PurchaseVO vo) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ArrayList<PurchaseVO> findByTime(String time1, String time2) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ArrayList<PurchaseVO> findByCommodityName(String commodityName) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ArrayList<PurchaseVO> findByCustomer(String customer) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ArrayList<PurchaseVO> findBySalesman(String salesman) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ArrayList<PurchaseVO> findByStorage(String Storage) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ArrayList<PurchaseVO> show() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ResultMessage updateCommodityByPurchase(
			ArrayList<CommodityLineItemVO> list) {
		MockCommodity mc=new MockCommodity();
		
		return mc.updateCommodityByPurchase(list);
	}

}
