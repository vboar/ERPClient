/**
 * PurchaseController
 * @author oneoneO
 * @date 2014/11/14
 */
package businesslogic.purchasebl;

import java.util.ArrayList;

import util.ResultMessage;
import vo.PurchaseVO;
import businesslogicservice.purchaseblservice.PurchaseBLService;

public class PurchaseController implements PurchaseBLService {

	Purchase purchase=new Purchase();
	@Override
	public ResultMessage add(PurchaseVO vo) {
		return add(vo);
		}

	@Override
	public ArrayList<PurchaseVO> findByTime(String time1, String time2) {
		return purchase.findByTime(time1, time2);
	}

	@Override
	public ArrayList<PurchaseVO> show() {
		return purchase.show();
	}

}
