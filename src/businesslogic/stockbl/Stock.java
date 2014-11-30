/**
 * stock逻辑类
 * @author Vboar
 * @date 2014/11/12
 */

package businesslogic.stockbl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import util.ResultMessage;
import vo.PresentVO;
import vo.PurchaseVO;
import vo.SaleVO;
import vo.StockInfoVO;
import vo.StockVO;
import businesslogic.presentbl.Present;
import businesslogic.purchasebl.Purchase;
import businesslogic.purchasebl.PurchaseReturn;
import businesslogic.salebl.Sale;
import businesslogic.salebl.SaleReturn;


public class Stock {
	
	
	public Stock() {
	}

	public ArrayList<StockInfoVO> showStockInfo(String time1, String time2) {
		if(time1==null){
			time1="1970/1/1";
		}
		if(time2==null){
			Date date=new Date();
			SimpleDateFormat myFmt=new SimpleDateFormat("yyyy/MM/dd/HH:mm:ss");
			time2=myFmt.format(date);
		}
		ArrayList<PresentVO> presentList=new Present().findByTime(time1, time2);
		ArrayList<SaleVO> saleList=new Sale().findByTime(time1, time2);
		ArrayList<SaleVO> saleReturnList=new SaleReturn().findByTime(time1, time2);
		ArrayList<PurchaseVO> purchaseList=new Purchase().findByTime(time1, time2);
		ArrayList<PurchaseVO> purchaseReturnList=new PurchaseReturn().findByTime(time1, time2);
		
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
