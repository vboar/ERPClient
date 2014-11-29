/**
 * @author chengcheng
 * @date 2014/11/14
 */

package businesslogic.approvalbl;

import java.util.ArrayList;

import po.CustomerPO;
import util.ResultMessage;
import vo.TransferLineItemVO;
import businesslogic.accountbl.MockLog;
import businesslogic.messagebl.MessageController;
import businesslogic.paymentbl.PaymentController;
//审批通过后：1、发消息到收件箱  2、修改相应数据
public class Approval {
	MessageController mc=new MessageController();
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
		//TODO
		//mc.send(new MessageVO());
		return ResultMessage.SUCCESS;
	}

	public ResultMessage approvePayment(ArrayList<TransferLineItemVO> transferlist,String id,String customerId,double total){
		PaymentController p=new PaymentController();
		return p.update(transferlist,id,customerId,total);
	}
	
}
