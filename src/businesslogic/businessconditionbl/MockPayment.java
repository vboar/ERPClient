/**
 * businesscondition MockPayment
 * @author JaneLDQ
 * @date 2014/11/14
 */
package businesslogic.businessconditionbl;

import java.util.ArrayList;

import vo.PaymentVO;
import businesslogic.paymentbl.Payment;

public class MockPayment extends Payment{

	private ArrayList<PaymentVO> list;
	
	public MockPayment(ArrayList<PaymentVO> list) {
		this.list = list;
	}
	
	public ArrayList<PaymentVO> findByTime(String time1, String time2){
		return this.list;
	}
}
