/**
 * businesscondition MockLoss
 * @author JaneLDQ
 * @date 2014/11/14
 */
package businesslogic.businessconditionbl;

import java.util.ArrayList;

import vo.ExceptionVO;
import businesslogic.exceptionbl.Loss;

public class MockLoss extends Loss{
	
	private ArrayList<ExceptionVO> list;
	
	private double total;
	
	public MockLoss(ArrayList<ExceptionVO> list) {
		this.list = list;
	}
	
	public MockLoss(double total){
		this.total = total;
	}
	
	public ArrayList<ExceptionVO> findByTime(String time1, String time2){
		return this.list;
	}
	
	
	public double getTotal(){
		return total;
	}
}
