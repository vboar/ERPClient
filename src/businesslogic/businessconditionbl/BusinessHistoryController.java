package businesslogic.businessconditionbl;

import java.util.ArrayList;

import util.ResultMessage;
import util.Time;
import vo.CashVO;
import vo.ExceptionVO;
import vo.PaymentVO;
import vo.PurchaseVO;
import vo.RequirementVO;
import vo.SaleVO;
import vo.WarningVO;
import businesslogicservice.businessconditionblservice.HistoryBLService;

public class BusinessHistoryController implements HistoryBLService {
	BusinessHistory bh=new BusinessHistory();
	
	@Override
	public ArrayList<SaleVO> showSale(RequirementVO vo) {
		bh.addLog("经营历程表查看：查看销售单");
		return bh.showSale(vo);
	}

	@Override
	public ArrayList<SaleVO> showSaleReturn(RequirementVO vo) {
		bh.addLog("经营历程表查看：查看销售退货单");
		return bh.showSaleReturn(vo);
	}
	
	@Override
	public ArrayList<PurchaseVO> showPurchase(RequirementVO vo) {
		bh.addLog("经营历程表查看：查看进货单");
		return bh.showPurchase(vo);
	}
	
	@Override
	public ArrayList<PurchaseVO> showPurchaseReturn(RequirementVO vo) {
		bh.addLog("经营历程表查看：查看销售退货单");
		return bh.showPurchaseReturn(vo);
	}

	@Override
	public ArrayList<PaymentVO> showPayment(RequirementVO vo) {
		bh.addLog("经营历程表查看：查看付款单");
		return bh.showPayment(vo);
	}

	@Override
	public ArrayList<PaymentVO> showReceipt(RequirementVO vo) {
		bh.addLog("经营历程表查看：查看收款单");
		return bh.showReceipt(vo);
	}

	@Override
	public ArrayList<CashVO> showCash(RequirementVO vo) {
		bh.addLog("经营历程表查看：查看现金费用单单");
		return bh.showCash(vo);
	}

	@Override
	public ArrayList<ExceptionVO> showOverFlow(RequirementVO vo) {
		bh.addLog("经营历程表查看：查看报溢单");
		return bh.showOverFlow(vo);
	}

	@Override
	public ArrayList<ExceptionVO> showLoss(RequirementVO vo) {
		bh.addLog("经营历程表查看：查看报损单");
		return bh.showLoss(vo);
	}

	@Override
	public ArrayList<WarningVO> showWarning(RequirementVO vo) {
		bh.addLog("经营历程表查看：查看报警单");
		return bh.showWarning(vo);
	}

	@Override
	public ResultMessage exportExcel(String path,RequirementVO vo) {
		switch(vo.type){
		case SALE: case SALERETURN:
			bh.exportSale(path, vo);
			break;
		case PURCHASE: case PURCHASERETURN:
			bh.exportPurchase(path, vo);
			break;
		case PAYMENT: case RECEIPT:
			bh.exportPayment(path, vo);
			break;
		case CASH:
			bh.exportCash(path, vo);
			break;
		case OVERFLOW:case LOSS:
			bh.exportException(path, vo);
			break;
		case WARNING:
			bh.exportWarning(path, vo);
			break;
		}
		return ResultMessage.SUCCESS;
	}

	@Override
	public String getDefaultPath() {
		String name=Time.getCurrentTime()+"经营历程表.xls";
		return name;
	}
}
