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
	
	private ArrayList<StockInfoVO> stockIntoList = new ArrayList<StockInfoVO>();
	private ArrayList<StockVO> stockList = new ArrayList<StockVO>();
	private MockPresent mp;
	private MockPurchase mpc;
	private MockPurchaseReturn mpcr;
	private MockSale ms;
	private MockSaleReturn msr;
	
	public Stock(MockPresent mp, MockPurchase mpc, MockPurchaseReturn mpcr, MockSale ms, MockSaleReturn msr) {
		this.mp = mp;
		this.mpc = mpc;
		this.mpcr = mpcr;
		this.ms = ms;
		this.msr = msr;
	}

	public Stock() {
	}

	public ArrayList<StockInfoVO> showStockInfo(String time1, String time2) {
		stockIntoList.add(new StockInfoVO());
		stockIntoList.get(0).inNumber += (mpc.getNum() + msr.getNum());
		stockIntoList.get(0).outNumber += (mp.getNum() + mpcr.getNum() + ms.getNum());
		return stockIntoList;
	}
	
	public ArrayList<StockVO> showCheck() {
		stockList.add(new StockVO());
		stockList.get(0).number = stockList.get(0).number - mp.getNum() + mpc.getNum() - mpcr.getNum()
				- ms.getNum() + msr.getNum();
		return stockList;
	}
	
	public ResultMessage createLog(String content) {
		MockLog ml = new MockLog(content);
		return ml.create();
	}
	
}
