/**
 * @author chengcheng
 * @date 2014/11/14
 */

package businesslogic.approvalbl;

import java.util.ArrayList;

import util.ResultMessage;
import vo.CashVO;
import vo.ExceptionVO;
import vo.PaymentVO;
import vo.PresentVO;
import vo.PurchaseVO;
import vo.SaleVO;
import businesslogicservice.approvalblservice.ApprovalBLService;

public class ApprovalController implements ApprovalBLService{
	
	Approval approval = new Approval();

	@Override
	public ArrayList<PresentVO> findPresent(int way, int status, String time1,
			String time2) {
		return approval.findPresent(way,status,time1,time2);
	}

	@Override
	public ArrayList<PurchaseVO> findPurchase(int way, int status,
			String time1, String time2) {
		return approval.findPurchase(way, status, time1, time2);
	}

	@Override
	public ArrayList<PurchaseVO> findPurchaseReturn(int way, int status,
			String time1, String time2) {
		return approval.findPurchaseReturn(way,status,time1,time2);
	}

	@Override
	public ArrayList<SaleVO> findSale(int way, int status, String time1,
			String time2) {
		return approval.findSale(way,status,time1,time2);
	}

	@Override
	public ArrayList<SaleVO> findSaleReturn(int way, int status, String time1,
			String time2) {
		return approval.findSaleReturn(way,status,time1,time2);
	}

	@Override
	public ArrayList<PaymentVO> findPayment(int way, int status, String time1,
			String time2) {
		return approval.findPayment(way,status,time1,time2);
	}

	@Override
	public ArrayList<PaymentVO> findReceipt(int way, int status, String time1,
			String time2) {
		return approval.findReceipt(way,status,time1,time2);
	}

	@Override
	public ArrayList<ExceptionVO> findOverflow(int way, int status,
			String time1, String time2) {
		return approval.findLoss(way, status, time1, time2);
	}

	@Override
	public ArrayList<ExceptionVO> findLoss(int way, int status, String time1,
			String time2) {
		return approval.findLoss(way, status, time1, time2);
	}

	@Override
	public ArrayList<CashVO> findCash(int way, int status, String time1,
			String time2) {
		return approval.fingCash(way, status, time1, time2);
	}

	@Override
	public ResultMessage approvePresent(PresentVO vo) {
		return approval.approvePresent(vo);
	}

	@Override
	public ResultMessage approvePurchase(PurchaseVO vo) {
		return approval.approvePurchase(vo);
	}

	@Override
	public ResultMessage approvePurchaseReturn(PurchaseVO vo) {
		return approval.approvePurchaseReturn(vo);
	}

	@Override
	public ResultMessage approveSale(SaleVO vo) {
		return approval.approveSale(vo);
	}

	@Override
	public ResultMessage approveSaleReturn(SaleVO vo) {
		return approval.approveSaleReturn(vo);
	}

	@Override
	public ResultMessage approvePayment(PaymentVO vo) {
		return approval.approvePayment(vo.transferList, vo.id, vo.customerId, vo.total);
	}

	@Override
	public ResultMessage approveReceipt(PaymentVO vo) {
		return approval.approveReceipt(vo.transferList,vo.id,vo.customerId,vo.total);
	}

	@Override
	public ResultMessage approveOverflow(ExceptionVO vo) {
		return approval.approveOverflow(vo);
	}

	@Override
	public ResultMessage approveLoss(ExceptionVO vo) {
		return approval.approveLoss(vo);
	}

	@Override
	public ResultMessage approveCash(CashVO vo) {
		return approval.approveCash(vo);
	}

}
