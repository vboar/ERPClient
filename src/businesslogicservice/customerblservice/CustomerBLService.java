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
	 * 模糊查找（ID和姓名）
	 * @param keyWord
	 * @return
	 */
	public ArrayList<CustomerVO> fuzzyFind(String keyWord);
	
	/**
	 * 显示客户列表
	 * @return 客户列表
	 */
	public ArrayList<CustomerVO> show();
	
	/**
	 * 获得客户ID
	 * @return
	 */
	public String createId();

}
