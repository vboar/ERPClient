/**
 * stockcontroller
 * @author Vboar
 * @date 2014/11/14
 */

package businesslogic.stockbl;

import java.util.ArrayList;

import util.ResultMessage;
import vo.StockInfoVO;
import vo.StockVO;
import businesslogicservice.stockblservice.StockBLService;

public class StockController implements StockBLService {

	@Override
	public ArrayList<StockInfoVO> showStockInfo(String time1, String time2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage addCheck(ArrayList<StockVO> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<StockVO> showCheck() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<StockVO> findByDate(String batch, String batchNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage exportExcel(String path) {
		// TODO Auto-generated method stub
		return null;
	}

}
