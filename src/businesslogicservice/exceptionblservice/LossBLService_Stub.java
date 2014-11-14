/**
 * 报损单业务逻辑桩程序
 * @author Vboar
 * @date 2014/10/25
 */
package businesslogicservice.exceptionblservice;

import java.util.ArrayList;

import util.DocumentStatus;
import util.DocumentType;
import util.ResultMessage;
import vo.ExceptionLineItemVO;
import vo.ExceptionVO;

public class LossBLService_Stub implements ExceptionBLService {

	@Override
	public ResultMessage create(ExceptionVO vo) {
		return ResultMessage.SUCCESS;
	}

	@Override
	public ArrayList<ExceptionVO> show(String time1, String time2) {
		ArrayList<ExceptionLineItemVO> list = new ArrayList<ExceptionLineItemVO>();
		list.add(new ExceptionLineItemVO("00001-00001-00001", "飞利浦吊灯", "FLP01", 60, 50));
		ArrayList<ExceptionVO> list2 = new ArrayList<ExceptionVO>();
		list2.add(new ExceptionVO("ECP-20141023-00001", "22:21:37", list, DocumentStatus.NONCHECKED,
				DocumentType.LOSS, false));
		return list2;
	}

	@Override
	public ArrayList<ExceptionVO> findById(String id) {
		ArrayList<ExceptionLineItemVO> list = new ArrayList<ExceptionLineItemVO>();
		list.add(new ExceptionLineItemVO("00001-00001-00001", "飞利浦吊灯", "FLP01", 60, 50));
		ArrayList<ExceptionVO> list2 = new ArrayList<ExceptionVO>();
		list2.add(new ExceptionVO(id, "22:21:37", list, DocumentStatus.NONCHECKED,
				DocumentType.LOSS, false));
		return list2;
	}

	@Override
	public ArrayList<ExceptionVO> findByStatus(DocumentStatus status) {
		ArrayList<ExceptionLineItemVO> list = new ArrayList<ExceptionLineItemVO>();
		list.add(new ExceptionLineItemVO("00001-00001-00001", "飞利浦吊灯", "FLP01", 60, 50));
		ArrayList<ExceptionVO> list2 = new ArrayList<ExceptionVO>();
		list2.add(new ExceptionVO("ECP-20141023-00001", "22:21:37", list, status,
				DocumentType.LOSS, false));
		return list2;
	}

	@Override
	public ResultMessage update(ExceptionVO vo) {
		return ResultMessage.SUCCESS;
	}

}
