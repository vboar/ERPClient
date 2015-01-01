/**
 * WriteOff逻辑类
 * @author JaneLDQ
 * @date 2014/11/14
 */
package businesslogic.writeoffbl;

import util.DocumentStatus;
import util.DocumentType;
import util.ResultMessage;
import vo.CashVO;
import vo.ExceptionVO;
import vo.PaymentVO;
import vo.PresentVO;
import vo.PurchaseVO;
import vo.SaleVO;
import businesslogic.exceptionbl.Loss;
import businesslogic.exceptionbl.Overflow;
import businesslogic.paymentbl.Cash;
import businesslogic.paymentbl.Payment;
import businesslogic.paymentbl.Receipt;
import businesslogic.presentbl.Present;
import businesslogic.purchasebl.Purchase;
import businesslogic.purchasebl.PurchaseReturn;
import businesslogic.salebl.Sale;
import businesslogic.salebl.SaleReturn;

public class Writeoff {
	
	public ResultMessage add(DocumentType type,String id){

		switch(type){
		case PRESENT: 
			return this.writeoffPresent(id);
		case PURCHASE: 
			return this.writeoffPurchase(id);		
		case PURCHASERETURN: 
			return this.writeoffPurchaseReturn(id);
		case SALE: 
			return this.writeoffSale(id);
		case SALERETURN: 
			return this.writeoffSaleReturn(id);
		case PAYMENT:
			return this.writeoffPayment(id);
		case RECEIPT:
			return this.writeoffReceipt(id);
		case OVERFLOW:
			return this.writeoffOver(id);
		case LOSS:
			return this.writeoffLoss(id);
		case CASH:
			return this.writeoffCash(id);
		case PRESENTRETURN:
		case WARNING:
		}
		return ResultMessage.SUCCESS;
	}
	
	private ResultMessage writeoffPresent(String id){
		Present pr=new Present();
		PresentVO present=pr.getById(id);
		
		if(!present.canWriteOff||present.documentStatus!=DocumentStatus.PASSED)
			return ResultMessage.FAILED;
		
		present.canWriteOff = false;
		pr.update(present);
		
		present.isWriteoff=true;
		pr.create(present);
		pr.writeoff(present);
		return ResultMessage.SUCCESS;
	}
	
	private ResultMessage writeoffPurchase(String id){
		Purchase pu=new Purchase();
		PurchaseVO purchase=pu.getById(id);
		
		if(!purchase.canWriteOff||purchase.documentStatus!=DocumentStatus.PASSED)
			return ResultMessage.FAILED;
		
		purchase.canWriteOff = false;
		purchase.canReturn = false;
		pu.update(purchase);
		
		purchase.isWriteOff=true;
		pu.add(purchase);
		pu.writeoff(purchase);
		return ResultMessage.SUCCESS;
	}
	
	private ResultMessage writeoffPurchaseReturn(String id){
		PurchaseReturn pre=new PurchaseReturn();
		PurchaseVO purchasere=pre.getById(id);
		
		if(!purchasere.canWriteOff||purchasere.documentStatus!=DocumentStatus.PASSED)
			return ResultMessage.FAILED;
		
		purchasere.canWriteOff = false;
		pre.update(purchasere);
		
		purchasere.isWriteOff=true;
		ResultMessage result = pre.add(purchasere);
		pre.writeoff(purchasere);
		return result;
	}
	
	private ResultMessage writeoffSale(String id){
		Sale s=new Sale();
		SaleVO sale=s.getById(id);
		
		if(!sale.canWriteOff||sale.approvalState!=DocumentStatus.PASSED)
			return ResultMessage.FAILED;
		
		sale.canReturn = false;
		sale.canWriteOff = false;
		s.update(sale);
		
		sale.isWriteOff=true;
		s.add(sale);
		s.writeoff(sale);
		return ResultMessage.SUCCESS;
	}
	
	private ResultMessage writeoffSaleReturn(String id) {
		Sale s1=new Sale();
		SaleVO sre=s1.getById(id);
		SaleReturn sr = new SaleReturn();
		
		if(!sre.canWriteOff||sre.approvalState!=DocumentStatus.PASSED)
			return ResultMessage.FAILED;
		
		sre.canWriteOff = false;
		s1.update(sre);
		
		sre.isWriteOff=true;
		sr.add(sre);
		sr.writeoff(sre);
		return ResultMessage.SUCCESS;
	}

	private ResultMessage writeoffPayment(String id){
		Payment p=new Payment();
		PaymentVO payment=p.getById(id);

		if(!payment.canWriteOff||payment.approvalState!=DocumentStatus.PASSED)
			return ResultMessage.FAILED;
		
		payment.canWriteOff = false;
		p.update(payment);

		payment.isWriteOff=true;
		p.create(payment);
		p.writeoff(payment);
		return ResultMessage.SUCCESS;
	}
	
	private ResultMessage writeoffReceipt(String id){
		Receipt r=new Receipt();
		PaymentVO receipt=r.getById(id);
		
		if(!receipt.canWriteOff||receipt.approvalState!=DocumentStatus.PASSED)
			return ResultMessage.FAILED;
		
		receipt.canWriteOff = false;
		r.update(receipt);

		receipt.isWriteOff=true;
		r.add(receipt);
		r.writeoff(receipt);
		return ResultMessage.SUCCESS;
	}
	
	private ResultMessage writeoffOver(String id){
		Overflow of=new Overflow();
		ExceptionVO overflow=of.getById(id);
		
		if(!overflow.canWriteoff||overflow.status!=DocumentStatus.PASSED){
			return ResultMessage.FAILED;
		}
		overflow.canWriteoff = false;
		of.update(overflow);

		overflow.isWriteoff=true;
		of.create(overflow);
		of.writeoff(overflow);
		return ResultMessage.SUCCESS;
	}
	
	
	private ResultMessage writeoffLoss(String id){
		Loss l=new Loss();
		ExceptionVO loss=l.getById(id);
		
		if(!loss.canWriteoff||loss.status!=DocumentStatus.PASSED){
			return ResultMessage.FAILED;
		}
		loss.canWriteoff = false;
		l.update(loss);
		
		loss.isWriteoff=true;
		l.add(loss);
		l.writeoff(loss);
		return ResultMessage.SUCCESS;
	}
	
	private ResultMessage writeoffCash(String id){
		Cash c=new Cash();
		CashVO cash=c.getById(id);
		
		if(!cash.canWriteOff||cash.approvalState!=DocumentStatus.PASSED)
			return ResultMessage.FAILED;
		
		cash.canWriteOff = false;
		c.update(cash);
		
		cash.isWriteOff=true;
		c.add(cash);
		c.writeoff(cash);
		return ResultMessage.SUCCESS;
	}
	
	public ResultMessage createLog(String content){	
		//TODO 红冲操作添加日志
		return null;
	}

}


