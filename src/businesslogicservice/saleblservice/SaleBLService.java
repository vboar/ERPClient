package businesslogicservice.saleblservice;

import java.util.ArrayList;

import util.DocumentStatus;
import util.ResultMessage;
import vo.PromotionVO;
import vo.SaleVO;

public interface SaleBLService {

	/**
	 * 添加销售类单据
	 * 
	 * @param vo
	 */
	public ResultMessage add(SaleVO vo);

	/**
	 * 按时间区间筛选单据
	 * 
	 * @param time1
	 * @param time2
	 * @return
	 */
	public ArrayList<SaleVO> findByTime(String time1, String time2);

	/**
	 * 按单据状态筛选数据
	 * 
	 * @param status
	 * @return
	 */
	public ArrayList<SaleVO> findByStatus(DocumentStatus status);

	/**
	 * 按客户筛选单据
	 * 
	 * @param customer
	 * @return
	 */
	public ArrayList<SaleVO> findByCustomer(String customer);

	/**
	 * 显示全部销售类单据
	 * 
	 * @param time1
	 * @param time2
	 * @return
	 */
	public ArrayList<SaleVO> show();

	public String createId();

	public SaleVO getById(String id);
	
	/**
	 * 计算可以选择的vip促销策略
	 * @param VIP
	 * @return
	 */
	public ArrayList<PromotionVO> calCustomerPromotion(int VIP);
	
	
	/**
	 * 计算可以选择的总价促销策略
	 * @param price
	 * @return
	 */
	public ArrayList<PromotionVO> calTotalGiftPromotion(double price);
	
	
	/**
	 * 计算折让
	 * @param vo
	 * @param promotionList
	 * @return 促销计算后的促销vo
	 */
	public SaleVO calAfterPrice(String customerGiftId,String totalGiftId,SaleVO vo);
}
