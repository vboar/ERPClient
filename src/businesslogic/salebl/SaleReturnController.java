/**
 * SaleReturnController
 * @author oneoneO
 * @date 2014/11/14
 */
package businesslogic.salebl;

import java.util.ArrayList;

import util.DocumentStatus;
import util.ResultMessage;
import vo.PurchaseVO;
import vo.SaleVO;
import businesslogicservice.saleblservice.SaleBLService;

public class SaleReturnController implements SaleBLService {
	
	SaleReturn sr=new SaleReturn();

	@Override
	public ResultMessage add(SaleVO vo) {
		
		return sr.add(vo);
	}

	@Override
	public ArrayList<SaleVO> findByTime(String time1, String time2) {
		
		return sr.findByTime(time1, time2);
	}

	@Override
	public ArrayList<SaleVO> show() {
		
		return sr.show();
	}

	@Override
	public ArrayList<PurchaseVO> findByStatus(DocumentStatus status) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ArrayList<PurchaseVO> findByCustomer(String customer) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public String createId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PurchaseVO getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}
}
