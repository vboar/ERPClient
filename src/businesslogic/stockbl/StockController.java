/**
 * stockcontroller
 * @author Vboar
 * @date 2014/11/14
 */

package businesslogic.stockbl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import util.ResultMessage;
import vo.StockInfoVO;
import vo.StockVO;
import businesslogicservice.stockblservice.StockBLService;

public class StockController implements StockBLService {

	Stock st = new Stock();
	
	@Override
	public ArrayList<StockInfoVO> showStockInfo(String time1, String time2) {
		return st.showStockInfo(time1, time2);
	}

	@Override
	public ResultMessage addCheck(ArrayList<StockVO> list) {
		// TODO 添加库存快照
		return null;
	}

	@Override
	public ArrayList<StockVO> showCheck() {
		return st.showCheck();
	}

	@Override
	public ArrayList<StockVO> findByDate(String batch, String batchNumber) {
		// TODO
		return null;
	}

	@Override
	public ResultMessage exportExcel(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDefaultPath() {
		String batch=null;
		Date date = new Date();
		SimpleDateFormat myFmt = new SimpleDateFormat("yyyyMMddHHmmss");
		batch = "库存查看"+myFmt.format(date)+".xsl";
		return batch;
	}

}
