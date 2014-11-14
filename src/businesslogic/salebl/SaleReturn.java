/**
 * 销售退货类
 * @author oneoneO
 */
package businesslogic.salebl;

import java.util.ArrayList;

import util.ResultMessage;
import vo.CommodityLineItemVO;
import vo.SaleVO;

public class SaleReturn {

	
	public ResultMessage add(SaleVO vo) {
		
		return null;
	}

	
	public ArrayList<SaleVO> findByTime(String time1, String time2) {
		
		return null;
	}

	
	public ArrayList<SaleVO> findByCommodityName(String commodityName) {
		
		return null;
	}

	
	public ArrayList<SaleVO> findByCustomer(String customer) {
		
		return null;
	}


	public ArrayList<SaleVO> findBySalesman(String salesman) {
		
		return null;
	}


	public ArrayList<SaleVO> findByStorage(String Storage) {
		
		return null;
	}

	
	public ArrayList<SaleVO> show() {
		
		return null;
	}

	
	public ResultMessage updateCommodityBySale(
			ArrayList<CommodityLineItemVO> list) {
		MockCommodity mc=new MockCommodity();
	
		return mc.updateCommodityBySale(list);
	}


	public ResultMessage updateAccountBySale(String name,double total) {
		MockAccount ma=new MockAccount();
		return ma.updateAccountBySale(name,total);
	}

}
