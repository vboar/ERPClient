/**
 * businesscondition mockPresent
 * @author JaneLDQ
 * @date 2014/11/14
 */

package businesslogic.businessconditionbl;

import java.util.ArrayList;

import vo.PresentVO;
import businesslogic.presentbl.Present;

public class MockPresent extends Present {
	
	private ArrayList<PresentVO> list;
	
	private double total;
	
	public MockPresent(double total) {
		this.total = total;
	}
	
	public MockPresent(ArrayList<PresentVO> list){
		this.list = list;
	}
	
	public ArrayList<PresentVO> findByTime(String time1, String time2){
		return this.list;
	}
	
	public double getTotal(){
		return total;
	}
}
