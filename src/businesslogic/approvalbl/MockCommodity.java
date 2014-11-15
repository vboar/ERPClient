/**
 * MockCommodity
 * @author oenoneO
 * @date 2014/11/15
 */
package businesslogic.approvalbl;

import businesslogic.commoditybl.Commodity;
import po.CommodityPO;
import util.ResultMessage;

public class MockCommodity extends Commodity{
public ResultMessage updateComodityBySale(String id,int number,double recentSalePrice){
	CommodityPO po=new CommodityPO("","","",100,0,0,0,0,0,false);
	po.setNumber(po.getNumber()-number);
	po.setRecentSalePrice(recentSalePrice);
	po.setTrade(true);
	
	return ResultMessage.SUCCESS;
}

public ResultMessage updateCommodityByPurchase(String id,int number,double recentPurchasePrice){
	CommodityPO po=new CommodityPO("","","",100,0,0,0,0,0,false);
	po.setNumber(po.getNumber()+number);
	po.setRecentPurchasePrice(recentPurchasePrice);

	return ResultMessage.SUCCESS;
}
}
