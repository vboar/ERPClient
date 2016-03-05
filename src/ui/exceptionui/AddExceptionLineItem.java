package ui.exceptionui;

import vo.ExceptionLineItemVO;

/**
 * 添加报溢报损单条目接口
 * @author JanelDQ
 * @date 2014/12/5
 */
public interface AddExceptionLineItem {

	/**
	 * 添加报溢报损单条目
	 * @param itemVO
	 */
	public void addLineItem(ExceptionLineItemVO itemVO);
}
