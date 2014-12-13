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
	public ResultMessage exportExcel(String path,String time) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDefaultPath(String time) {
		String path=null;
	    time=time.replace("/", "");
		path = "库存查看"+time+".xls";
		return path;
	}
//	public static void main(String[] args) {
//		String s="1970/01/01";
//		s=s.replace("/","");
//		System.out.println(s);
//	}

}
