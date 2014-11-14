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

}
