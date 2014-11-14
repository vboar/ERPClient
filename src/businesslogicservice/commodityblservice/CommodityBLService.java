/**
 * 商品管理业务逻辑接口
 * @author Vboar
 * @date 2014/10/25
 */
package businesslogicservice.commodityblservice;

import java.util.ArrayList;

import util.ResultMessage;
import vo.CommodityVO;

public interface CommodityBLService {

	/**
	 * 添加商品
	 * @param vo
	 * @return 添加成功与否消息
	 */
	public ResultMessage add(CommodityVO vo);
	
	/**
	 * 删除商品
	 * @param vo
	 * @return 删除成功与否消息
	 */
	public ResultMessage delete(CommodityVO vo);
	
	/**
	 * 更新商品信息
	 * @param vo
	 * @return 更新商品成功与否消息
	 */
	public ResultMessage update(CommodityVO vo);
	
	/**
	 * 根据商品编号查找商品
	 * @param id
	 * @return 商品列表
	 */
	public ArrayList<CommodityVO> findById(String id);
	
	/**
	 * 根据商品名称查找商品
	 * @param name
	 * @return 商品列表
	 */
	public ArrayList<CommodityVO> findByName(String name);
	
	/**
	 * 根据商品型号查找商品
	 * @param name
	 * @return 商品列表
	 */
	public ArrayList<CommodityVO> findByModel(String model);
	
	/**
	 * 获得所有商品
	 * @return 所有商品
	 */
	public ArrayList<CommodityVO> show();
}
