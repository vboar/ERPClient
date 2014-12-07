/**
 * @author chengcheng
 * @date 2014/11/14
 */

package businesslogic.customerbl;

import businesslogicservice.customerblservice.CustomerBLService;
import util.ResultMessage;
import vo.CustomerVO;

import java.util.ArrayList;

public class CustomerController implements CustomerBLService {

	Customer customer = new Customer();

	@Override
	public ResultMessage add(CustomerVO vo) {
		return customer.add(vo);
	}

	@Override
	public ResultMessage delete(CustomerVO vo) {
		return customer.delete(vo);
	}

	@Override
	public ResultMessage update(CustomerVO vo) {
		return customer.update(vo);
	}

	@Override
	public ArrayList<CustomerVO> fuzzyFind(String keyWord) {
		return customer.fuzzyFind(keyWord);
	}

	@Override
	public ArrayList<CustomerVO> show() {
		return customer.show();
	}
	
	public ResultMessage updateByPayment(String customerId,double total){
		return customer.updateByPayment(customerId, total);
	}

	@Override
	public String createId() {
		return customer.createId();
	}

}
