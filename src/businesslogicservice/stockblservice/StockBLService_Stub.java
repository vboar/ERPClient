/**
 * 库存管理业务逻辑桩程序
 * @author Vboar
 * @date 2014/10/25
 */
package businesslogicservice.stockblservice;

import java.util.ArrayList;

import util.ResultMessage;
import vo.StockInfoVO;
import vo.StockVO;

public class StockBLService_Stub implements StockBLService {

	@Override
	public ArrayList<StockInfoVO> showStockInfo(String time1, String time2) {
		ArrayList<StockInfoVO> list = new ArrayList<StockInfoVO>();
		list.add(new StockInfoVO("00001-00001", "飞利浦吊灯", "FLP01", 50, 50, 1000, 1500));
		return list;
	}
	
	@Override
	public ResultMessage addCheck(ArrayList<StockVO> list) {
		return ResultMessage.SUCCESS;
	}

	@Override
	public ArrayList<StockVO> showCheck() {
		ArrayList<StockVO> list = new ArrayList<StockVO>();
		list.add(new StockVO("00001-00001", "飞利浦吊灯", "FLP01", 50, 20, "2014/10/25", "00001"));
		return list;
	}

	@Override
	public ArrayList<StockVO> findByDate(String batch, String batchNumber) {
		ArrayList<StockVO> list = new ArrayList<StockVO>();
		list.add(new StockVO("00001-00001", "飞利浦吊灯", "FLP01", 50, 20, batch, batchNumber));
		return list;
	}

	@Override
	public ResultMessage exportExcel(String path) {
		return ResultMessage.SUCCESS;
	}
	
}
