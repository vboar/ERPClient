/**
 * @author chengcheng
 * @date 2014/11/14
 */

package businesslogic.approvalbl;

import java.util.ArrayList;

import util.ResultMessage;
import vo.ExceptionVO;
import vo.PurchaseVO;
import vo.SaleVO;
import vo.TransferLineItemVO;
import businesslogic.exceptionbl.Loss;
import businesslogic.exceptionbl.Overflow;
import businesslogic.paymentbl.Payment;
import businesslogic.paymentbl.Receipt;
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
}
