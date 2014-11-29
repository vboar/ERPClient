/**
 * 单据审批逻辑接口
 * date 2014/10/25
 * @author chengcheng
 */
package businesslogicservice.approvalblservice;

import java.util.ArrayList;

import util.ResultMessage;
import vo.CashVO;
import vo.ExceptionVO;
import vo.PaymentVO;
import vo.PresentVO;
import vo.PurchaseVO;
import vo.SaleVO;

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
	
	public ArrayList<SaleVO> findSale(int way, int status, String time1, String time2);
	
	public ArrayList<PaymentVO> findPayment(int way, int status, String time1, String time2);
	
	public ArrayList<ExceptionVO> findException(int way, int status, String time1, String time2);
	
	public ArrayList<CashVO> Cash(int way, int status, String time1, String time2);
	
	/**
	 * 审批单据
	 * @param vo
	 * @return
	 */
	public ResultMessage approvePresent(PresentVO vo);
	
	public ResultMessage approvePurchase(PurchaseVO vo);
	
	public ResultMessage approveSale(SaleVO vo);
	
	public ResultMessage approvePayment(PaymentVO vo);
	
	public ResultMessage approveException(ExceptionVO vo);
	
	public ResultMessage approveCashVO(CashVO vo);
	
	
}
