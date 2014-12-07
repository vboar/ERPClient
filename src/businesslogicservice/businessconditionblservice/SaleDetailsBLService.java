package businesslogicservice.businessconditionblservice;

import java.util.ArrayList;

import util.ResultMessage;
import vo.RequirementVO;
import vo.SaleDetailsVO;

/**
 * 销售明细表查看接口
 * @author JanelDQ
 * @date 2014/12/6
 */
public interface SaleDetailsBLService {

	/**
	 * 根据筛选条件查看单据
	 * @param time1
	 * @param time2
	 * @return
	 */
	public ArrayList<SaleDetailsVO> show(RequirementVO vo);
	
	/**
	 * 导出Excel文件
	 * @param path
	 * @return
	 */
	public ResultMessage exportExcel(String path);
	
}
