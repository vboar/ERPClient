/**
 * @author chengcheng
 * @date 2014/11/14
 */

package businesslogic.approvalbl;

import po.CustomerPO;
import util.ResultMessage;
import businesslogic.accountbl.MockLog;

public class Approval {
	
	public ResultMessage createLog(String content){
		MockLog log = new MockLog(content);
		return log.add();	
	}
	
	public double approve(double money,CustomerPO po){
		MockCustomer mcc=new MockCustomer();
				return (mcc.recieveChange(po, money));
	}
	
	public ResultMessage updateAccountByApproval(String name,String account,double total){
		MockAccount ma=new MockAccount();
		return ma.updateAccountByApproval(name, account, total);
	}
	
	public ResultMessage updateCommodityBySale(String id,int number,double recentSalePrice){
		MockCommodity mc=new MockCommodity();
		
		return mc.updateComodityBySale(id, number, recentSalePrice);
	}
	
	public ResultMessage updateCommodityByPurchase(String id,int number,double recentPurchasePrice){
		MockCommodity mc=new MockCommodity();
		
		return mc.updateCommodityByPurchase(id, number, recentPurchasePrice);
	}
	
	public ResultMessage sendMessage(String content){
		MockMessage mm=new MockMessage();
		
		return mm.sendMessage(content);
	}

}
