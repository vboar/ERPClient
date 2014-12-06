package ui.util;

import vo.CommodityLineItemVO;

/**
 * 添加商品信息接口
 * @author JanelDQ
 * @date 2014/12/6
 */
public interface AddCommodityLineItem {

	/**
	 * 添加一条商品信息
	 * @param vo
	 */
	public void addCommodityLineItem(CommodityLineItemVO vo);
}
