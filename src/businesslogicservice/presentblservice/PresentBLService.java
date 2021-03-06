/**
 * 赠品单业务逻辑接口
 * @author Vboar
 * @date 2014/10/25
 */
package businesslogicservice.presentblservice;

import java.util.ArrayList;

import util.DocumentStatus;
import util.ResultMessage;
import vo.PresentVO;

public interface PresentBLService {
	
	/**
	 * 创建赠送单
	 * @param vo
	 * @return 创建成功与否消息
	 */
	public ResultMessage create(PresentVO vo);
	
	/**
	 * 根据时间段查找赠送单
	 * @param time1
	 * @param time2
	 * @return 赠送单列表
	 */
	public ArrayList<PresentVO> show(String time1, String time2);
	
	/**
	 * 根据单据状态查找赠送单
	 * @param status
	 * @return 赠送单列表
	 */
	public ArrayList<PresentVO> findByStatus(DocumentStatus status);
	
	/**
	 * 精确查找
	 * @param id
	 * @return po
	 */
	public PresentVO getById(String id);
	
	/**
	 * 创建id
	 * @return
	 */
	public String createId();
	
}
