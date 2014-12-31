/**
 * WriteOff逻辑类
 * @author JaneLDQ
 * @date 2014/11/14
 */
package businesslogic.writeoffbl;

import util.DocumentType;
import util.ResultMessage;
import vo.CashVO;
import vo.ExceptionVO;
import vo.PaymentVO;
import vo.PresentVO;
import vo.PurchaseVO;
import vo.SaleVO;
import businesslogic.approvalbl.Approval;
import businesslogic.exceptionbl.Overflow;
import businesslogic.paymentbl.Cash;
import businesslogic.paymentbl.Payment;
import businesslogic.paymentbl.Receipt;
import businesslogic.presentbl.Present;
import businesslogic.purchasebl.Purchase;
import businesslogic.purchasebl.PurchaseReturn;
import businesslogic.salebl.Sale;

public class Writeoff {
	
	public ResultMessage add(DocumentType type,String id){
		Approval a=new Approval();
		switch(type){
		case PRESENT:
			Present pr=new Present();
			PresentVO present=pr.getById(id);
			present.isWriteoff=true;
			pr.create(present);
			pr.writeoff(present);
			break;
		case PURCHASE:
			Purchase pu=new Purchase();
			PurchaseVO purchase=pu.getById(id);
			
			if(!purchase.canWriteOff)
				return ResultMessage.FAILED;
			
			purchase.canWriteOff = false;
			purchase.canReturn = false;
			pu.update(purchase);
			
			purchase.canWriteOff=true;
			purchase.isWriteOff=true;
			pu.add(purchase);
			pu.writeoff(purchase);
			break;			
		case PURCHASERETURN:
			PurchaseReturn pre=new PurchaseReturn();
			PurchaseVO purchasere=pre.getById(id);
			
			if(!purchasere.canWriteOff)
				return ResultMessage.FAILED;
			
			purchasere.canWriteOff = false;
			pre.update(purchasere);
			
			purchasere.canWriteOff = true;
			purchasere.isWriteOff=true;
			pre.add(purchasere);
			pre.writeoff(purchasere);
			break;
		case SALE:
			Sale s=new Sale();
			SaleVO sale=s.getById(id);
			
			if(!sale.canWriteOff)
				return ResultMessage.FAILED;
			
			sale.canReturn = false;
			sale.canWriteOff = false;
			s.update(sale);
			
			sale.canWriteOff = true;
			sale.isWriteOff=true;
			s.add(sale);
			s.writeoff(sale);
			break;
		case SALERETURN:
			Sale s1=new Sale();
			SaleVO sre=s1.getById(id);
			
			if(!sre.canWriteOff)
				return ResultMessage.FAILED;
			
			sre.canWriteOff = false;
			s1.update(sre);
			
			sre.canWriteOff = true;
			sre.isWriteOff=true;
			s1.add(sre);
			s1.writeoff(sre);
			break;
		case PAYMENT:
			Payment p=new Payment();
			PaymentVO payment=p.getById(id);

			if(!payment.canWriteOff)
				return ResultMessage.FAILED;
			
			payment.canWriteOff = false;
			p.update(payment);

			payment.canWriteOff = true;
			payment.isWriteOff=true;
			p.create(payment);
			p.writeoff(payment);
			break;
		case RECEIPT:
			Receipt r=new Receipt();
			PaymentVO receipt=r.getById(id);
			
			if(!receipt.canWriteOff)
				return ResultMessage.FAILED;
			
			receipt.canWriteOff = false;
			r.update(receipt);
			
			receipt.canWriteOff = true;
			receipt.isWriteOff=true;
			r.add(receipt);
			r.writeoff(receipt);
			break;
		case OVERFLOW:
			Overflow of=new Overflow();
			ExceptionVO overflow=of.getById(id);
			overflow.isWriteoff=true;
			of.create(overflow);
			a.approveOverflow(overflow);
			break;
		case LOSS:
			Overflow of1=new Overflow();
			ExceptionVO loss=of1.getById(id);
			loss.isWriteoff=true;
			of1.create(loss);
			a.approveLoss(loss);
			break;
		case CASH:
			Cash c=new Cash();
			CashVO cash=c.getById(id);
			cash.isWriteOff=true;
			c.add(cash);
			c.writeoff(cash);
			break;
		case PRESENTRETURN:
		case WARNING:
		}
		return ResultMessage.SUCCESS;
	}
	
	public ResultMessage createLog(String content){	
		return null;
	}

}


