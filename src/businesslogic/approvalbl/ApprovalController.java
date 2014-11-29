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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<PurchaseVO> findPurchase(int way, int status,
			String time1, String time2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<SaleVO> findSale(int way, int status, String time1,
			String time2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<PaymentVO> findPayment(int way, int status, String time1,
			String time2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ExceptionVO> findException(int way, int status,
			String time1, String time2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<CashVO> Cash(int way, int status, String time1,
			String time2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage approvePresent(PresentVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage approvePurchase(PurchaseVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage approveSale(SaleVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage approvePayment(PaymentVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage approveException(ExceptionVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage approveCashVO(CashVO vo) {
		// TODO Auto-generated method stub
		return null;
	}



}
