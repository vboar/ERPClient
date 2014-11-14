/**
 * loss逻辑类
 * @author Vboar
 * @date 2014/11/12
 */

package businesslogic.exceptionbl;

import java.util.ArrayList;

import util.DocumentStatus;
import util.ResultMessage;
import vo.ExceptionVO;
import businesslogicservice.exceptionblservice.ExceptionBLService;

public class Loss implements ExceptionBLService {

	@Override
	public ResultMessage create(ExceptionVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ExceptionVO> show(String time1, String time2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ExceptionVO> findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ExceptionVO> findByStatus(DocumentStatus status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage update(ExceptionVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

}
