/**
 * 商品管理业务逻辑接口
 * @author Vboar
 * @date 2014/10/25
 */
package businesslogicservice.commodityblservice;

import java.util.ArrayList;

import util.ResultMessage;
import vo.CategoryCommodityVO;
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
	 * 模糊查找（名称、型号）
	 * @param keyWord
	 * @return
	 */
	public ArrayList<CommodityVO> fuzzyFind(String keyWord);
	
	/**
	 * 获得所有商品
	 * @return 所有商品
	 */
	public ArrayList<CommodityVO> show();
	
	
	/**
	 * 获取分类商品组合
	 * @return 所有商品分类组合
	 */
	public ArrayList<CategoryCommodityVO> bigShow();
}
