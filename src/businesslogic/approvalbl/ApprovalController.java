/**
 * @author chengcheng
 * @date 2014/11/14
 */

package businesslogic.approvalbl;

import java.util.ArrayList;

import util.DocumentStatus;
import util.DocumentType;
import util.ResultMessage;
import vo.CashVO;
import vo.DocumentVO;
import vo.ExceptionVO;
import vo.PaymentVO;
import vo.PresentVO;
import vo.PurchaseVO;
import vo.SaleVO;
import businesslogic.exceptionbl.Overflow;
import businesslogic.paymentbl.Cash;
import businesslogic.paymentbl.Payment;
import businesslogic.paymentbl.Receipt;
import businesslogic.presentbl.Present;
import businesslogic.purchasebl.Purchase;
import businesslogic.purchasebl.PurchaseReturn;
import businesslogic.salebl.Sale;
import businesslogicservice.approvalblservice.ApprovalBLService;

public class ApprovalController implements ApprovalBLService {
	
	Approval approval = new Approval();

	@Override
	public ArrayList<PresentVO> findPresent(int way, DocumentStatus status, String time1,
			String time2) {
		approval.addLog("按方法"+way+"查找赠品单");
		return approval.findPresent(way,status,time1,time2);
		}


	@Override
	public ArrayList<PurchaseVO> findPurchase(int way, DocumentStatus status,
			String time1, String time2) {
		approval.addLog("按方法"+way+"查找进货单");
		return approval.findPurchase(way, status, time1, time2);
	}

	@Override
	public ArrayList<PurchaseVO> findPurchaseReturn(int way, DocumentStatus status,
			String time1, String time2) {
		approval.addLog("按方法"+way+"查找进货退货单");
		return approval.findPurchaseReturn(way,status,time1,time2);
	}

	@Override
	public ArrayList<SaleVO> findSale(int way, DocumentStatus status, String time1,
			String time2) {
		approval.addLog("按方法"+way+"查找销售单");
		return approval.findSale(way,status,time1,time2);
	}

	@Override
	public ArrayList<SaleVO> findSaleReturn(int way, DocumentStatus status, String time1,
			String time2) {
		approval.addLog("按方法"+way+"查找销售退货单");
		return approval.findSaleReturn(way,status,time1,time2);
	}

	@Override
	public ArrayList<PaymentVO> findPayment(int way, DocumentStatus status, String time1,
			String time2) {
		approval.addLog("按方法"+way+"查找付款单");
		return approval.findPayment(way,status,time1,time2);
	}

	@Override
	public ArrayList<PaymentVO> findReceipt(int way, DocumentStatus status, String time1,
			String time2) {
		approval.addLog("按方法"+way+"查找收款单");
		return approval.findReceipt(way,status,time1,time2);
	}

	@Override
	public ArrayList<ExceptionVO> findOverflow(int way, DocumentStatus status,
			String time1, String time2) {
		approval.addLog("按方法"+way+"查找报溢单");
		return approval.findOverflow(way, status, time1, time2);
	}

	@Override
	public ArrayList<ExceptionVO> findLoss(int way, DocumentStatus status, String time1,
			String time2) {
		approval.addLog("按方法"+way+"查找报损单");
		return approval.findLoss(way, status, time1, time2);
	}

	@Override
	public ArrayList<CashVO> findCash(int way, DocumentStatus status, String time1,
			String time2) {
		approval.addLog("按方法"+way+"查找现金费用单");
		return approval.findCash(way, status, time1, time2);
	}

	@Override
	public ArrayList<DocumentVO> show(DocumentStatus status,
									  String time1, String time2) {
		return approval.show(status, time1, time2);
	}

	@Override
	public ResultMessage approvePresent(PresentVO vo) {
		approval.addLog("审批赠品单");
		return approval.approvePresent(vo);
	}

	@Override
	public ResultMessage approvePurchase(PurchaseVO vo) {
		approval.addLog("审批进货单");
		return approval.approvePurchase(vo);
	}

	@Override
	public ResultMessage approvePurchaseReturn(PurchaseVO vo) {
		approval.addLog("审批进货退货单");
		return approval.approvePurchaseReturn(vo);
	}

	@Override
	public ResultMessage approveSale(SaleVO vo) {
		approval.addLog("审批销售单");
		return approval.approveSale(vo);
	}

	@Override
	public ResultMessage approveSaleReturn(SaleVO vo) {
		approval.addLog("审批销售退货单");
		return approval.approveSaleReturn(vo);
	}

	@Override
	public ResultMessage approvePayment(PaymentVO vo) {
		approval.addLog("审批付款单");
		return approval.approvePayment(vo);
	}

	@Override
	public ResultMessage approveReceipt(PaymentVO vo) {
		approval.addLog("审批收款单");
		return approval.approveReceipt(vo);
	}

	@Override
	public ResultMessage approveOverflow(ExceptionVO vo) {
		approval.addLog("审批库存报溢单");
		return approval.approveOverflow(vo);
	}

	@Override
	public ResultMessage approveLoss(ExceptionVO vo) {
		approval.addLog("审批库存报损单");
		return approval.approveLoss(vo);
	}

	@Override
	public ResultMessage approveCash(CashVO vo) {
		approval.addLog("审批现金费用单");
		return approval.approveCash(vo);
	}

	@Override
	public ResultMessage approveDocument(DocumentVO vo) {
		DocumentType type=vo.getType();
		switch(type){
		case PRESENT:
			Present pr=new Present();
			PresentVO present=pr.getById(vo.getId());
			present.setStatus(vo.getStatus());
			approvePresent(present);
			break;
		case PURCHASE:
			Purchase pu=new Purchase();
			PurchaseVO purchase=pu.getById(vo.getId());
			purchase.setStatus(vo.getStatus());
			approvePurchase(purchase);
			break;			
		case PURCHASERETURN:
			PurchaseReturn pre=new PurchaseReturn();
			PurchaseVO purchasere=pre.getById(vo.getId());
			purchasere.setStatus(vo.getStatus());
			approvePurchaseReturn(purchasere);
			break;
		case SALE:
			Sale s=new Sale();
			SaleVO sale=s.getById(vo.getId());
			sale.setStatus(vo.getStatus());
			approveSale(sale);
			break;
		case SALERETURN:
			Sale s1=new Sale();
			SaleVO sre=s1.getById(vo.getId());
			sre.setStatus(vo.getStatus());
			approveSaleReturn(sre);
			break;
		case PAYMENT:
			Payment p=new Payment();
			PaymentVO payment=p.getById(vo.getId());
			payment.setStatus(vo.getStatus());
			approvePayment(payment);
			break;
		case RECEIPT:
			Receipt r=new Receipt();
			PaymentVO receipt=r.getById(vo.getId());
			receipt.setStatus(vo.getStatus());
			approveReceipt(receipt);
			break;
		case OVERFLOW:
			Overflow of=new Overflow();
			ExceptionVO overflow=of.getById(vo.getId());
			overflow.setStatus(vo.getStatus());
			approveOverflow(overflow);
			break;
		case LOSS:
			Overflow of1=new Overflow();
			ExceptionVO loss=of1.getById(vo.getId());
			loss.setStatus(vo.getStatus());
			approveLoss(loss);
			break;
		case CASH:
			Cash c=new Cash();
			CashVO cash=c.getById(vo.getId());
			cash.setStatus(vo.getStatus());
			approveCash(cash);
			break;
		case PRESENTRETURN:
		case WARNING:
		}
		return ResultMessage.SUCCESS;
	}

	//嫖人的不知道能不能用
	public Object getById(String id){
		approval.addLog("通过id唯一查找");
		return approval.getById(id);
	}

}
