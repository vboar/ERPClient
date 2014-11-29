/**
 * stock逻辑类
 * @author Vboar
 * @date 2014/11/12
 */

package businesslogic.stockbl;

import java.util.ArrayList;

import util.ResultMessage;
import vo.StockInfoVO;
import vo.StockVO;


public class Stock {
	
	
	public Stock() {
	}

	public ArrayList<StockInfoVO> showStockInfo(String time1, String time2) {
		
		return null;
	}
	
	public ArrayList<StockVO> showCheck() {
		return null;
	}
	
	public ResultMessage createLog(String content) {
		MockLog ml = new MockLog(content);
		return ml.create();
	}
	
}
