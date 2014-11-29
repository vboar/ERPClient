/**
 * 商品分类管理业务逻辑接口
 * @author Vboar
 * @date 2014/10/25
 */
package businesslogicservice.commodityblservice;

import java.util.ArrayList;

import util.ResultMessage;
import vo.CategoryVO;

public interface CategoryBLService {

	/**
	 * 添加分类
	 * @param vo
	 * @return 添加成功与否消息
	 */
	public ResultMessage add(CategoryVO vo);
	
	/**
	 * 删除分类
	 * @param vo
	 * @return 删除成功与否消息
	 */
	public ResultMessage delete(CategoryVO vo);
	
	/**
	 * 更新分类信息
	 * @param vo
	 * @return 更新成功与否消息
	 */
	public ResultMessage update(CategoryVO vo);
	
	/**
	 * 根据分类名称模糊查找分类
	 * @param name
	 * @return 分类列表
	 */
	public ArrayList<CategoryVO> fuzzyFind(String name);
	
	/**
	 * 获取所有分类
	 * @return 所有分类
	 */
	public ArrayList<CategoryVO> show();
	
}
