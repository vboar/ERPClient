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
	 * 显示全部销售类单据
	 * @param time1
	 * @param time2
	 * @return
	 */
	public ArrayList<SaleVO> show();
	
}
