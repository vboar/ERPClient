/**
 * @author chengcheng
 * @date 2014/11/14
 */

package businesslogic.customerbl;

import java.util.ArrayList;

import util.ResultMessage;
import vo.CustomerVO;
import businesslogicservice.customerblservice.CustomerBLService;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<CustomerVO> show() {
		return customer.show();
	}
	
	public ResultMessage updateByPayment(String customerId,double total){
		return customer.updateByPayment(customerId, total);
	}

}
