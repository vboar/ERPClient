/**
 * @author chengcheng
 * @date 2014/11/14
 */

package businesslogic.promotionbl;

import businesslogic.customerbl.Customer;

public class MockCustomer extends Customer {
	int level=0;
	public MockCustomer(int level){
		this.level=level;
	}
	public int getLevel() {
		return level;
	}
	

}
