/**
 * stock逻辑类
 * @author Vboar
 * @date 2014/11/12
 */

package businesslogic.stockbl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import util.DocumentStatus;
import util.ResultMessage;
import vo.CommodityLineItemVO;
import vo.PresentLineItemVO;
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
		if (time1 == null) {
			time1 = "1970/1/1";
		}
		if (time2 == null) {
			Date date = new Date();
			SimpleDateFormat myFmt = new SimpleDateFormat("yyyy/MM/dd/HH:mm:ss");
			time2 = myFmt.format(date);
		}
		ArrayList<PresentVO> presentList = new Present().findByTime(time1,
				time2);
		ArrayList<SaleVO> saleList = new Sale().findByTime(time1, time2);
		ArrayList<SaleVO> saleReturnList = new SaleReturn().findByTime(time1,
				time2);
		ArrayList<PurchaseVO> purchaseList = new Purchase().findByTime(time1,
				time2);
		ArrayList<PurchaseVO> purchaseReturnList = new PurchaseReturn()
				.findByTime(time1, time2);

		ArrayList<CommodityLineItemVO> inList = new ArrayList<CommodityLineItemVO>();
		ArrayList<CommodityLineItemVO> outList = new ArrayList<CommodityLineItemVO>();
		ArrayList<PresentLineItemVO> listUnknown=new ArrayList<PresentLineItemVO>();

		for (SaleVO salevo : saleList) {
			if (salevo.approvalState != DocumentStatus.PASSED) {
				saleList.remove(salevo);
				// TODO 有点不确定这样行不行
			}
		}
		for (SaleVO salevo : saleReturnList) {
			if (salevo.approvalState != DocumentStatus.PASSED) {
				saleReturnList.remove(salevo);
				
			}
		}
		for (PurchaseVO purchasevo : purchaseList) {
			if (purchasevo.documentStatus != DocumentStatus.PASSED) {
				purchaseList.remove(purchasevo);
				
			}
		}
		for (PurchaseVO purchasevo : purchaseReturnList) {
			if (purchasevo.documentStatus != DocumentStatus.PASSED) {
				purchaseReturnList.remove(purchasevo);
				
			}
		}
		for (PresentVO presentvo : presentList) {
			if (presentvo.documentStatus != DocumentStatus.PASSED) {
				presentList.remove(presentvo);
				
			}
		}
		for(PresentVO presentVO:presentList){
			listUnknown.addAll(presentVO.list);
		}
		for(SaleVO salevo:saleList){
			outList.addAll(salevo.saleList);
		}
		for(SaleVO salereturnvo:saleReturnList){
			inList.addAll(salereturnvo.saleList);
		}
		
		for(PurchaseVO purchasevo:purchaseList){
			inList.addAll(purchasevo.saleList);
		}
		for(PurchaseVO purchasereturnvo:purchaseReturnList){
			outList.addAll(purchasereturnvo.saleList);
		}
		

		


		return null;
	}

	public ArrayList<StockVO> showCheck() {
		return null;
	}

	

}
