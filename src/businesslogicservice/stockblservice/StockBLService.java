/**
 * 库存管理业务逻辑接口
 * @author Vboar
 * @date 2014/10/25
 */

package businesslogicservice.stockblservice;

import java.util.ArrayList;

import util.ResultMessage;
import vo.StockInfoVO;
import vo.StockVO;

public interface StockBLService {

	/**
	 * 商品库存查看
	 * @param time1
	 * @param time2
	 * @return 商品库存列表
	 */
	public ArrayList<StockInfoVO> showStockInfo(String time1, String time2);
	
	/**
	 * 增加库存快照信息
	 * @param list
	 * @return 增加成功与否
	 */
	public ResultMessage addCheck(ArrayList<StockVO> list);
	
	/**
	 * 商品库存快照
	 * @return 当天库存快照
	 */
	public ArrayList<StockVO> showCheck();
	
	/**
	 * 根据批次和批号查找库存快照
	 * @param date
	 * @return
	 */
	public ArrayList<StockVO> findByDate(String batch, String batchNumber);
	
	/**
	 * 将快照导出Excel文件至指定路径
	 * @param path
	 * @return 导出成功与否消息
	 */
	public ResultMessage exportExcel(String path,String time);
	
	/**
	 * 获取导出文件默认路径
	 * @return
	 */
	public String getDefaultPath(String time);
	
}
