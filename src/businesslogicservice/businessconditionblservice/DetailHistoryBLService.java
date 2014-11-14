/**
 * 销售明细查看和经营历程查看服务接口
 * @author JaneLDQ
 * @date 2014/10/25
 */
package businesslogicservice.businessconditionblservice;

import java.util.ArrayList;

import util.ResultMessage;
import vo.DocumentVO;
import vo.RequirementVO;

public interface DetailHistoryBLService {
	/**
	 * 根据筛选条件查看单据
	 * @param time1
	 * @param time2
	 * @return
	 */
	public ArrayList<DocumentVO> show(RequirementVO vo);
	
	/**
	 * 导出Excel文件
	 * @param path
	 * @return
	 */
	public ResultMessage exportExcel(String path);
}
