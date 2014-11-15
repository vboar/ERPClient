/**
 * 销售退货类
 * @author oneoneO
 * @date 2014/11/14
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


	public ResultMessage updateCustomerBySale(String name,double total) {
		MockCustomer ma=new MockCustomer();
		return ma.updateCustomerBySale(name,total);
	}

	public ResultMessage addlog(String content){
		MockLog ml=new MockLog();
		
		return ml.addlog(content);
	}
}
