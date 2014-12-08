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
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ArrayList<SaleVO> findByCustomer(String customer) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public String createId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SaleVO getById(String id) {
		// TODO Auto-generated method stub
		return null;
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
