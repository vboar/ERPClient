/**
 * SaleDetails逻辑类
 * @author JaneLDQ
 * @date 2014/11/14
 */
package businesslogic.businessconditionbl;

import java.util.ArrayList;

import vo.SaleVO;

public class SaleDetails {
	
	private MockSale ms;

	private ArrayList<SaleVO> salelist;
	
	public SaleDetails(MockSale ms) {
		this.ms = ms;
	}
	
	public ArrayList<SaleVO> showByTime(String time1, String time2){
		this.salelist = this.ms.findByTime(time1, time2);
		return this.salelist;
	}
	
}
