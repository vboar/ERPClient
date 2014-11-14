/**
 * @author chengcheng
 * @date 2014/11/14
 */

package businesslogic.approvalbl;

import po.CustomerPO;

public class MockCustomer {
	private double receivables;
	public double recieveChange(CustomerPO po,double money){
		receivables=po.getReceivables();
		po.setReceivables(receivables-money);
		return po.getReceivables();		
	}

}
