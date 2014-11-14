/**
 * businesscondition MockPurchase
 * @author JaneLDQ
 * @date 2014/11/14
 */

package businesslogic.businessconditionbl;

import java.util.ArrayList;

import vo.PurchaseVO;
import businesslogic.purchasebl.Purchase;

public class MockPurchase extends Purchase {

	private ArrayList<PurchaseVO> list;
	
	private double total;
	
	public MockPurchase(ArrayList<PurchaseVO> list) {
		this.list = list;
	}
	
	public MockPurchase(double total){
		this.total = total;
	}
	
	public ArrayList<PurchaseVO> findByTime(String time1, String time2){
		return this.list;
	}

	public ArrayList<PurchaseVO> findByOperator(String operator) {
		return this.list;
	}
	
	public double getTotal(){
		return total;
	}

}
