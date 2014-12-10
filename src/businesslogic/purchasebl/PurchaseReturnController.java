/**
 * PurchaseReturnController
 * @author oenoenO
 * @date 2014/11/14
 */
package businesslogic.purchasebl;

import java.util.ArrayList;

import util.DocumentStatus;
import util.ResultMessage;
import vo.PurchaseVO;
import businesslogicservice.purchaseblservice.PurchaseBLService;

public class PurchaseReturnController implements PurchaseBLService {

	PurchaseReturn pr=new PurchaseReturn();
	@Override
	public ResultMessage add(PurchaseVO vo) {
		return pr.add(vo);
	}

	@Override
	public ArrayList<PurchaseVO> findByTime(String time1, String time2) {
		return pr.findByTime(time1, time2);
		}

	@Override
	public ArrayList<PurchaseVO> show() {
		return pr.show();
	}

	@Override
	public ArrayList<PurchaseVO> findByStatus(DocumentStatus status) {
		return pr.findByStatus(status.ordinal());
	}

	@Override
	public ArrayList<PurchaseVO> findByCustomer(String customer) {
		return pr.findByCustomer(customer);
	}

	@Override
	public ArrayList<PurchaseVO> findByStorage(String Storage) {
		return pr.findByStorage(Storage);
	}

	@Override
	public String createId() {
		return pr.createId();
	}

	@Override
	public PurchaseVO getById(String id) {
		return pr.getById(id);
	}

}
