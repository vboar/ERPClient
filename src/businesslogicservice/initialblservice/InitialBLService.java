/**
 * 期初建账业务逻辑接口
 * @author Vboar
 * @date 2014/10/26
 */
package businesslogicservice.initialblservice;

import java.util.ArrayList;

import util.ResultMessage;
import vo.AccountVO;
import vo.CommodityVO;
import vo.CustomerVO;
import vo.InitialVO;

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
	 * @param vo
	 * @return 成功与否消息
	 */
	public ResultMessage initialAccount(InitialVO vo);
	
	/**
	 * 返回对应账本商品信息
	 * @param vo
	 * @return 商品列表
	 */
	public ArrayList<CommodityVO> showCommodity(InitialVO vo);
	
	/**
	 * 返回对应账本客户信息
	 * @param vo
	 * @return 客户列表
	 */
	public ArrayList<CustomerVO> showCustomer(InitialVO vo);
	
	/**
	 * 返回对应账本账户信息
	 * @param vo
	 * @return 账户列表
	 */
	public ArrayList<AccountVO> showAccount(InitialVO vo);
	
}
