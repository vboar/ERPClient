/**
 * @author chengcheng
 * @date 2014/11/14
 */

package businesslogic.approvalbl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import po.CustomerPO;
import util.ResultMessage;

public class ApprovalTest {

	
	@Test
	public void testapprove() {
		Approval approval = new Approval();
		assertEquals(ResultMessage.SUCCESS, approval.createLog("2014/11/13 approve!"));
		
		CustomerPO po= new CustomerPO("00001", 0, 1, "金刚狼", "12345678901", 
				"美国加利福尼州", "210000", "Ironman@gmail.com", 10000, 20000, 0, "美队", false);		
		approval.approve(10.0,po);
		assertEquals(20000-10, (int)po.getReceivables());
		
		
		assertEquals(ResultMessage.SUCCESS,approval.updateAccountByApproval("飞利浦", "12345678", 100));
		
		assertEquals(ResultMessage.SUCCESS,approval.updateCommodityBySale("123456",10,100));
		
		assertEquals(ResultMessage.SUCCESS,approval.updateCommodityByPurchase("123456",10, 100));
		
		assertEquals(ResultMessage.SUCCESS,approval.sendMessage("付款单通过审批"));
	}
	
	
}
