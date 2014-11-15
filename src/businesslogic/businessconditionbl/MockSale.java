/**
 * businesscondition mocksale
 * @author Vboar
 * @date 2014/11/14
 */

package businesslogic.businessconditionbl;

import java.util.ArrayList;

import vo.SaleVO;
import businesslogic.salebl.Sale;

public class MockSale extends Sale {

	private ArrayList<SaleVO> list;
	
	private double total;
	
	private double costTotal;
	
	public MockSale(ArrayList<SaleVO> list) {
		this.list = list;
	}
	
	public MockSale(double total,double costTotal){
		this.total = total;
		this.costTotal = costTotal;
	}
	
	public ArrayList<SaleVO> findByTime(String time1, String time2){
		return this.list;
	}

	public ArrayList<SaleVO> findByCustomer(String name) {
		return this.list;
	}
	
	public double getTotal(){
		return total;
	}

	public double getCostTotal() {
		return costTotal;
	}
}
