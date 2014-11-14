/**
 * 促销策略制定逻辑接口
 * date 2014/10/25
 * @author chengcheng
 */
package businesslogicservice.promotionblservice;

import java.util.ArrayList;

import util.ResultMessage;
import vo.CustomerGiftVO;
import vo.SpecialOfferVO;
import vo.TotalGiftVO;



public interface PromotionBLService {
	/**
	 * 对特殊级别的用户制定促销策略
	 * @param vo
	 * @return 创建成功与否的消息
	 */
	public ResultMessage createByVip (CustomerGiftVO vo);
	
	/**
	 * 制定特价包的促销策略
	 * @param vo
	 * @return 创建成功与否的消息
	 */
	public ResultMessage createByCommodity(SpecialOfferVO vo);
	
	/**
	 * 制定总价折让的促销策略
	 * @param vo
	 * @return 创建成功与否的消息
	 */
	public ResultMessage createByPrice(TotalGiftVO vo);
	
	/**
	 * 查看对不同等级的促销策略列表
	 * @return 策略列表
	 */
	public ArrayList<CustomerGiftVO> findByVip(); 
	
	/**
	 * 查看特价包策略列别
	 * @return 策略列表
	 */
	public ArrayList<SpecialOfferVO> findByCommodity(); 
	
	/**
	 * 查看总价促销的策略列表
	 * @return 策略列表
	 */
	public ArrayList<TotalGiftVO> findByPrice(); 
	
	/**
	 * 更新对不同等级的促销策略
	 * @param vo
	 * @return 创建成功与否的消息
	 */
	public ResultMessage update(CustomerGiftVO vo);
	
	/**
	 * 更新特价包促销策略
	 * @param vo
	 * @return 创建成功与否的消息
	 */
	public ResultMessage update(SpecialOfferVO vo);
	
	/**
	 * 更新总价促销的策略
	 * @param vo
	 * @return 创建成功与否的消息
	 */
	public ResultMessage update(TotalGiftVO vo);
	
	


}
