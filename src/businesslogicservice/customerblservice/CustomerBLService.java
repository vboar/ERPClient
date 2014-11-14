/**
 * 客户管理接口
 * date 2014/10/25
 * @author chengcheng
 */
package businesslogicservice.customerblservice;

import java.util.ArrayList;

import util.ResultMessage;
import vo.CustomerVO;

public interface CustomerBLService {
	/**
	 * 添加客户
	 * @param vo
	 * @return 创建成功与否的消息
	 */
	public ResultMessage add(CustomerVO vo);
	
	/**
	 * 删除客户
	 * @param vo
	 * @return 删除成功与否的消息
	 */
	public ResultMessage delete(CustomerVO vo);
	
	/**
	 * 更新客户信息
	 * @param vo
	 * @return 更新成功与否的消息
	 */
	public ResultMessage update(CustomerVO vo);
	
	/**
	 * 按照姓名查找客户
	 * @param name
	 * @return 客户列表
	 */
	public ArrayList<CustomerVO> findByName(String name);
	
	/**
	 * 按照id查找客户
	 * @param Id
	 * @return 客户列表
	 */
	public ArrayList<CustomerVO> findById(String Id);
	
	/**
	 * 显示客户列表
	 * @return 客户列表
	 */
	public ArrayList<CustomerVO> show();
	


}
