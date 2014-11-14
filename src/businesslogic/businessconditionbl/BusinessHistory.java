/**
 * BusinessHistory逻辑类
 * @author JaneLDQ
 * @date 2014/11/14
 */
package businesslogic.businessconditionbl;

import java.util.ArrayList;

import vo.ExceptionVO;
import vo.PaymentVO;
import vo.PresentVO;
import vo.PurchaseVO;
import vo.SaleVO;

public class BusinessHistory {
	
	private MockPresent mpre;
	
	private MockPurchase mpur;
	
	private MockSale ms;
	
	private MockLoss me;
	
	private MockPayment mpay;

	public BusinessHistory(MockPresent mpre, MockPurchase mpur, MockSale ms,
			MockLoss me, MockPayment mpay) {
		super();
		this.mpre = mpre;
		this.mpur = mpur;
		this.ms = ms;
		this.me = me;
		this.mpay = mpay;
	}
	
	public BusinessHistory(MockSale ms){
		this.ms = ms;
	}
	
	public BusinessHistory(MockPresent mpre){
		this.mpre = mpre;
	}
	
	public BusinessHistory(MockPurchase mpur){
		this.mpur = mpur;
	}
	
	public BusinessHistory(MockLoss me){
		this.me = me;
	}
	
	public BusinessHistory(MockPayment mpay){
		this.mpay = mpay;
	}
	
	
	public ArrayList<SaleVO> showSaleByCustomer(String name){
		ArrayList<SaleVO> list = this.ms.findByCustomer(name);
		return list;
	}

	public ArrayList<PurchaseVO> showPurchaseByOperator(String operator){
		ArrayList<PurchaseVO> list = this.mpur.findByOperator(operator);
		return list;
	}
	
	public ArrayList<PaymentVO> showPaymentByTime(String time1, String time2){
		ArrayList<PaymentVO> list = this.mpay.findByTime(time1, time2);
		return list;
	}
	
	public ArrayList<ExceptionVO> showExceptionByTime(String time1, String time2){
		ArrayList<ExceptionVO> list = this.me.findByTime(time1, time2);
		return list;
	}
	
	public ArrayList<PresentVO> showPresentByTime(String time1, String time2){
		ArrayList<PresentVO> list = this.mpre.findByTime(time1, time2);
		return list;
	}
}
