/**
 * 经营情况表
 * @author JaneLDQ
 * @date 2014/10/25
 */
package businesslogicservice.businessconditionblservice;

import util.ResultMessage;
import vo.BusinessConditionVO;

public interface BusinessConditionBLService {

	/**
	 * 根据筛选条件显示单据
	 * @param vo
	 * @return
	 */
	public BusinessConditionVO show(String time1, String time2);
	
	/**
	 * 导出Excel文件
	 * @param path
	 * @return
	 */
	public ResultMessage exportExcel(String path);
	
	/**
	 * 获取导出文件默认路径
	 * @return
	 */
	public String getDefaultPath();
	
}
