/**
 * 销售明细查看和经营历程查看服务接口
 * @author JaneLDQ
 * @date 2014/10/25
 */
package businesslogicservice.businessconditionblservice;

import java.util.ArrayList;

import util.ResultMessage;
import vo.CashVO;
import vo.ExceptionVO;
import vo.PaymentVO;
import vo.PresentVO;
import vo.PurchaseVO;
import vo.RequirementVO;
import vo.SaleVO;
import vo.WarningVO;

public interface HistoryBLService {
	/**
	 * 根据筛选条件查看销售单
	 * @param vo
	 * @return
	 */
	public ArrayList<SaleVO> showSale(RequirementVO vo);
	
	/**
	 * 根据筛选条件查看销售退货单
	 * @param vo
	 * @return
	 */
	public ArrayList<SaleVO> showSaleReturn(RequirementVO vo);
	
	/**
	 * 根据筛选条件查看进货类单据
	 * @param vo
	 * @return
	 */
	public ArrayList<PurchaseVO> showPurchase(RequirementVO vo);
	
	/**
	 * 根据筛选条件查看进货退货单
	 * @param vo
	 * @return
	 */
	public ArrayList<PurchaseVO> showPurchaseReturn(RequirementVO vo);
	
	/**
	 * 根据筛选条件查看付款单
	 * @param vo
	 * @return
	 */
	public ArrayList<PaymentVO> showPayment(RequirementVO vo);
	
	/**
	 * 根据筛选条件查看收款单
	 * @param vo
	 * @return
	 */
	public ArrayList<PaymentVO> showReceipt(RequirementVO vo);

	/**
	 * 根据筛选条件查看现金费用单
	 * @param vo
	 * @return
	 */
	public ArrayList<CashVO> showCash(RequirementVO vo);
	
	/**
	 * 根据筛选条件查看报溢单
	 * @param vo
	 * @return
	 */
	public ArrayList<ExceptionVO> showOverFlow(RequirementVO vo);
	
	/**
	 * 根据筛选条件查看报损单
	 * @param vo
	 * @return
	 */
	public ArrayList<ExceptionVO> showLoss(RequirementVO vo);
	
	/**
	 * 根据筛选条件查看报警单
	 * @param vo
	 * @return
	 */
	public ArrayList<WarningVO> showWarning(RequirementVO vo);
	
	/**
	 * 导出Excel文件
	 * @param path
	 * @return
	 */
	public ResultMessage exportExcel(String path,RequirementVO vo);
	
	/**
	 * 获取导出文件默认路径
	 * @return
	 */
	public String getDefaultPath();
	
	/**
	 * 根据条件筛选赠送单
	 * @param vo
	 * @return
	 */
	public ArrayList<PresentVO> showPresent(RequirementVO vo);
}
