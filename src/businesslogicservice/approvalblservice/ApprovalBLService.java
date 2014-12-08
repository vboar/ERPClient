/**
 * 单据审批逻辑接口
 * date 2014/10/25
 * @author chengcheng
 */
package businesslogicservice.approvalblservice;

import util.ResultMessage;
import vo.*;

import java.util.ArrayList;

public interface ApprovalBLService {
	
	/**
	 * 查找单据
	 * @param way 0-所有，1-根据状态，2-根据日期时间段
	 * @param status
	 * @param time1
	 * @param time2
	 * @return
	 */
	public ArrayList<PresentVO> findPresent(int way, int status, String time1, String time2);
	
	public ArrayList<PurchaseVO> findPurchase(int way, int status, String time1, String time2);
	
	public ArrayList<PurchaseVO> findPurchaseReturn(int way, int status, String time1, String time2);
	
	public ArrayList<SaleVO> findSale(int way, int status, String time1, String time2);
	
	public ArrayList<SaleVO> findSaleReturn(int way, int status, String time1, String time2);
	
	public ArrayList<PaymentVO> findPayment(int way, int status, String time1, String time2);
	
	public ArrayList<PaymentVO> findReceipt(int way, int status, String time1, String time2);
	
	public ArrayList<ExceptionVO> findOverflow(int way, int status, String time1, String time2);
	
	public ArrayList<ExceptionVO> findLoss(int way, int status, String time1, String time2);
	
	public ArrayList<CashVO> findCash(int way, int status, String time1, String time2);

	public DocumentVO show(int status, String time1, String time2);
	
	/**
	 * 审批单据
	 * @param vo
	 * @return
	 */
	public ResultMessage approvePresent(PresentVO vo);
	
	public ResultMessage approvePurchase(PurchaseVO vo);
	
	public ResultMessage approvePurchaseReturn(PurchaseVO vo);
	
	public ResultMessage approveSale(SaleVO vo);
	
	public ResultMessage approveSaleReturn(SaleVO vo);
	
	public ResultMessage approvePayment(PaymentVO vo);
	
	public ResultMessage approveReceipt(PaymentVO vo);
	
	public ResultMessage approveOverflow(ExceptionVO vo);
	
	public ResultMessage approveLoss(ExceptionVO vo);
	
	public ResultMessage approveCash(CashVO vo);
	
}
