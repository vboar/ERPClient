/**
 * @author chengcheng
 * @date 2014/11/14
 */

package businesslogic.approvalbl;

import businesslogicservice.approvalblservice.ApprovalBLService;
import util.ResultMessage;
import vo.*;

import java.util.ArrayList;

public class ApprovalController implements ApprovalBLService {
	
	Approval approval = new Approval();

	@Override
	public ArrayList<PresentVO> findPresent(int way, int status, String time1,
			String time2) {
		approval.addLog("按方法"+way+"查找赠品单");
		return approval.findPresent(way,status,time1,time2);
	}

	@Override
	public ArrayList<PurchaseVO> findPurchase(int way, int status,
			String time1, String time2) {
		approval.addLog("按方法"+way+"查找进货单");
		return approval.findPurchase(way, status, time1, time2);
	}

	@Override
	public ArrayList<PurchaseVO> findPurchaseReturn(int way, int status,
			String time1, String time2) {
		approval.addLog("按方法"+way+"查找进货退货单");
		return approval.findPurchaseReturn(way,status,time1,time2);
	}

	@Override
	public ArrayList<SaleVO> findSale(int way, int status, String time1,
			String time2) {
		approval.addLog("按方法"+way+"查找销售单");
		return approval.findSale(way,status,time1,time2);
	}

	@Override
	public ArrayList<SaleVO> findSaleReturn(int way, int status, String time1,
			String time2) {
		approval.addLog("按方法"+way+"查找销售退货单");
		return approval.findSaleReturn(way,status,time1,time2);
	}

	@Override
	public ArrayList<PaymentVO> findPayment(int way, int status, String time1,
			String time2) {
		approval.addLog("按方法"+way+"查找付款单");
		return approval.findPayment(way,status,time1,time2);
	}

	@Override
	public ArrayList<PaymentVO> findReceipt(int way, int status, String time1,
			String time2) {
		approval.addLog("按方法"+way+"查找收款单");
		return approval.findReceipt(way,status,time1,time2);
	}

	@Override
	public ArrayList<ExceptionVO> findOverflow(int way, int status,
			String time1, String time2) {
		approval.addLog("按方法"+way+"查找报溢单");
		return approval.findLoss(way, status, time1, time2);
	}

	@Override
	public ArrayList<ExceptionVO> findLoss(int way, int status, String time1,
			String time2) {
		approval.addLog("按方法"+way+"查找报损单");
		return approval.findLoss(way, status, time1, time2);
	}

	@Override
	public ArrayList<CashVO> findCash(int way, int status, String time1,
			String time2) {
		approval.addLog("按方法"+way+"查找现金费用单");
		return approval.fingCash(way, status, time1, time2);
	}

	@Override
	public DocumentVO show(int status, String time1, String time2) {
		return null;
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
		return approval.approvePayment(vo.transferList, vo.id, vo.customerId, vo.total);
	}

	@Override
	public ResultMessage approveReceipt(PaymentVO vo) {
		approval.addLog("审批收款单");
		return approval.approveReceipt(vo.transferList,vo.id,vo.customerId,vo.total);
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
	
	public Object getById(String id){
		approval.addLog("通过id唯一查找");
		return approval.getById(id);
	}

}
