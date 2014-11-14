/**
 * businesscondition MockOver
 * @author JaneLDQ
 * @date 2014/11/14
 */
package businesslogic.businessconditionbl;

import java.util.ArrayList;

import vo.ExceptionVO;

public class MockOver {
	
	private ArrayList<ExceptionVO> list;
	
	private double total;
	
	public MockOver(ArrayList<ExceptionVO> list) {
		this.list = list;
	}
	
	public MockOver(double total){
		this.total = total;
	}
	
	public ArrayList<ExceptionVO> findByTime(String time1, String time2){
		return this.list;
	}
	
	public double getTotal(){
		return total;
	}
}