/**
 * presentcontroller
 * @author Vboar
 * @date 2014/11/14
 */

package businesslogic.presentbl;

import java.util.ArrayList;

import util.DocumentStatus;
import util.DocumentType;
import util.ResultMessage;
import vo.PresentLineItemVO;
import vo.PresentVO;
import businesslogicservice.presentblservice.PresentBLService;

public class PresentController implements PresentBLService {

	private Present present = new Present();
	
	@Override
	public ResultMessage create(PresentVO vo) {
		return present.create(vo);
	}

	@Override
	public ArrayList<PresentVO> show(String time1, String time2) {
		ArrayList<PresentVO> list = new ArrayList<PresentVO>();
		ArrayList<PresentLineItemVO> itemlist = new ArrayList<PresentLineItemVO>();
		PresentLineItemVO linevo = new PresentLineItemVO("0001", "日光灯", "s01", 5);
		PresentLineItemVO linevo2 = new PresentLineItemVO("0002", "吊灯", "s02", 2);
		itemlist.add(linevo);
		itemlist.add(linevo2);
		PresentVO vo = new PresentVO("00001","2014/12/1/17:50","001","小黑",itemlist,
				DocumentStatus.NONCHECKED,DocumentType.PRESENT,false);
		list.add(vo);
		return list;
	}

	@Override
	public ArrayList<PresentVO> findByStatus(DocumentStatus status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PresentVO getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createId() {
		return present.createId();
	}

}
