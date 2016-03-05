/**
 * 期初建账业务逻辑接口
 * @author Vboar
 * @date 2014/10/26
 */
package businesslogicservice.initialblservice;

import java.util.ArrayList;

import businesslogic.commoditybl.Category;
import util.ResultMessage;
import vo.*;

public interface InitialBLService {
	
	/**
	 * 根据编号查找账本
	 * @param id
	 * @return 账本列表
	 */
	public ArrayList<InitialVO> findById(String id);
	
	/**
	 * 返回所有账本
	 * @return 账本列表
	 */
	public ArrayList<InitialVO> show();

	/**
	 * 初始化建账
	 * @return 成功与否消息
	 */
	public ResultMessage initialAccount();
	
	/**
	 * 返回对应账本商品信息
	 * @param id
	 * @return 商品列表
	 */
	public ArrayList<CategoryCommodityVO> showCommodity(String id);
	
	/**
	 * 返回对应账本客户信息
	 * @param id
	 * @return 客户列表
	 */
	public ArrayList<CustomerVO> showCustomer(String id);
	
	/**
	 * 返回对应账本账户信息
	 * @param id
	 * @return 账户列表
	 */
	public ArrayList<AccountVO> showAccount(String id);
	
}
