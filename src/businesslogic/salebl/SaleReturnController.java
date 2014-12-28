/**
 * SaleReturnController
 * @author oneoneO
 * @date 2014/11/14
 */
package businesslogic.salebl;

import java.util.ArrayList;

import util.DocumentStatus;
import util.ResultMessage;
import vo.PromotionVO;
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
	public ArrayList<SaleVO> findByStatus(DocumentStatus status) {
		return sr.findByStatus(status.ordinal());
	}

	@Override
	public ArrayList<SaleVO> findByCustomer(String customer) {
		return sr.findByCustomer(customer);
	}

	@Override
	public String createId() {
		return sr.createId();		
	}

	@Override
	public SaleVO getById(String id) {
		return new Sale().getById(id);
	}

	@Override
	public ArrayList<PromotionVO> calCustomerPromotion(int VIP) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<PromotionVO> calTotalGiftPromotion(double price) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SaleVO calAfterPrice(String customerGiftId, String totalGiftId,
			SaleVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
