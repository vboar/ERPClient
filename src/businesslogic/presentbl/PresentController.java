/**
 * presentcontroller
 * @author Vboar
 * @date 2014/11/14
 */

package businesslogic.presentbl;

import java.util.ArrayList;

import util.DocumentStatus;
import util.ResultMessage;
import vo.PresentVO;
import businesslogicservice.presentblservice.PresentBLService;

public class PresentController implements PresentBLService {

	@Override
	public ResultMessage create(PresentVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<PresentVO> show(String time1, String time2) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

}
