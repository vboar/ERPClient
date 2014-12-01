/**
 * @author chengcheng
 * @date 2014/11/14
 */

package businesslogic.approvalbl;

import java.util.ArrayList;

import util.DocumentStatus;
import util.ResultMessage;
import vo.ExceptionVO;
import vo.PresentVO;
import vo.PurchaseVO;
import vo.SaleVO;
import vo.TransferLineItemVO;
import businesslogic.exceptionbl.Loss;
import businesslogic.exceptionbl.Overflow;
import businesslogic.paymentbl.Payment;
import businesslogic.paymentbl.Receipt;
import businesslogic.presentbl.Present;
import businesslogic.purchasebl.Purchase;
import businesslogic.purchasebl.PurchaseReturn;
import businesslogic.salebl.Sale;
import businesslogic.salebl.SaleReturn;
//审批通过后：1、发消息到收件箱  2、修改相应数据
public class Approval {
	
	public ResultMessage sendMessage(String content){
		//TODO
		return ResultMessage.SUCCESS;
	}

	public ResultMessage approvePayment(ArrayList<TransferLineItemVO> transferlist,String id,String customerId,double total){
		Payment p=new Payment();
		return p.approve(transferlist,id,customerId,total);
	}
	
	public ResultMessage approveReceipt(ArrayList<TransferLineItemVO> transferlist,String id,String customerId,double total){
		Receipt r=new Receipt();
		return r.approve(transferlist, id, customerId, total);
	}
	
	public ResultMessage approveSale(SaleVO vo){
		Sale s=new Sale();
		return s.approve(vo);
	}
	
	public ResultMessage approveSaleReturn(SaleVO vo){
		SaleReturn sr=new SaleReturn();
		return sr.approve(vo);
	}
	
	public ResultMessage approvePurchase(PurchaseVO vo){
		Purchase p=new Purchase();
		return p.approve(vo);
	}
	
	public ResultMessage approvePurchaseReturn(PurchaseVO vo){
		PurchaseReturn pr=new PurchaseReturn();
		return pr.approve(vo);
	}
	
	public ResultMessage approveOverflow(ExceptionVO vo){
		Overflow of=new Overflow();
		return of.approve(vo);
	}
	
	public ResultMessage approveLoss(ExceptionVO vo){
		Loss l=new Loss();
		return l.approve(vo);
	}
	
	public ArrayList<PresentVO> findPresent(int way,int status,String time1,String time2){
		ArrayList<PresentVO> result=new ArrayList<PresentVO>();
		Present p=new Present();
		switch(way){
		case 0:
			result=p.show();
			break;
		case 1:
			result=p.findByStatus(DocumentStatus.values()[status]);
			break;
		case 2:
			result=p.findByTime(time1, time2);
		}
		
		return result;
	}
	
	public ArrayList<PurchaseVO> findPurchase(int way,int status,String time1,String time2){
		ArrayList<PurchaseVO> result=new ArrayList<PurchaseVO>();
		Purchase p=new Purchase();
		switch(way){
		case 0:
			result=p.show();
		case 1:
			result=p.findByStatus(status);
		case 2:
			result=p.findByTime(time1, time2);
		}
		
		return result;
	}
}
