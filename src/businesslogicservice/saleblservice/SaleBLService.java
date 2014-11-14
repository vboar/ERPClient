package businesslogicservice.saleblservice;

import java.util.ArrayList;

import util.ResultMessage;
import vo.SaleVO;

public interface SaleBLService {

	/**
	 * 添加销售类单据
	 * @param vo
	 */
	public ResultMessage add(SaleVO vo);
	
	/**
	 * 按时间区间筛选单据
	 * @param time1
	 * @param time2
	 * @return
	 */
	public ArrayList<SaleVO> findByTime(String time1,String time2);
	
	/**
	 * 按商品名称筛选单据
	 * @param commodityName
	 * @return
	 */
	public ArrayList<SaleVO> findByCommodityName(String commodityName);
	
	/**
	 * 按客户筛选单据
	 * @param customer
	 * @return
	 */
	public ArrayList<SaleVO> findByCustomer(String customer);
	
	/**
	 * 按业务员筛选单据
	 * @param salesman
	 * @return
	 */
	public ArrayList<SaleVO> findBySalesman(String salesman);
	
	/**
	 * 按仓库筛选单据
	 * @param Storage
	 * @return
	 */
	public ArrayList<SaleVO> findByStorage(String Storage);

	
	/**
	 * 显示全部销售类单据
	 * @param time1
	 * @param time2
	 * @return
	 */
	public ArrayList<SaleVO> show();
}
