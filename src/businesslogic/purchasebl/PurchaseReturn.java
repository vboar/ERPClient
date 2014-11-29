/**
 * PurchaseReturn
 * author oenoenO
 * date 2014/11/14
 */
package businesslogic.purchasebl;

import java.util.ArrayList;

import util.ResultMessage;
import vo.CommodityLineItemVO;
import vo.PurchaseVO;
import vo.SaleVO;

public class PurchaseReturn {
Purchase purchase=new Purchase();
	
	public ResultMessage add(PurchaseVO vo) {
	return purchase.add(vo);
	}
	
	public ResultMessage update(PurchaseVO vo){
		return purchase.update(vo);
	}
	public ArrayList<PurchaseVO> findByTime(String time1, String time2) {
		return purchase.findByTime2(time1, time2);
	}
	
	public ArrayList<PurchaseVO> findByCommodityName(String commodityName) {
	return purchase.findByCommodityName2(commodityName);
	}
	
	public ArrayList<PurchaseVO> findByCustomer(String customer) {
		return purchase.findByCustomer2(customer);
	}
	
	public ArrayList<PurchaseVO> findByStorage(String Storage){
		return purchase.findByStorage2(Storage);
	}
	
	
	public ArrayList<PurchaseVO> show() {
		return purchase.show2();
	}
	//TODO
	public ResultMessage approveSale(SaleVO vo) {
		return null;

	}

	
	
	/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
	
	public ResultMessage updateCommodityByPurchase(
			ArrayList<CommodityLineItemVO> list) {
		MockCommodity mc=new MockCommodity();
		
		return mc.updateCommodityByPurchase(list);
	}
	
	public ResultMessage updateCustomerByPurchase(String name,double total){
		MockCustomer mc=new MockCustomer();
		
		return mc.updateCustomerByPurchase(name, total);
	}
	
	public ResultMessage addLog(String content){
		MockLog ml=new MockLog();
		
		return ml.addLog(content);
	}

}
