package businesslogic.salebl;

import java.util.ArrayList;

import po.CommodityPO;
import util.ResultMessage;
import vo.CommodityLineItemVO;

public class MockCommodity {
		public ResultMessage updateCommodityBySale(ArrayList<CommodityLineItemVO> list){
		CommodityPO c=new CommodityPO("","","",50,0,0,0,0,0,false);
		c.setNumber(c.getNumber()-list.get(0).number);
		return ResultMessage.SUCCESS;	
		}	
}
