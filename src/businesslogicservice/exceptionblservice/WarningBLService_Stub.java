package businesslogicservice.exceptionblservice;

import java.util.ArrayList;

import util.DocumentType;
import util.ResultMessage;
import vo.WarningLineItemVO;
/**
 * 报警单业务逻辑桩程序
 * @author Vboar
 * @date 2014/10/25
 */
import vo.WarningVO;

public class WarningBLService_Stub implements WarningBLService {

	@Override
	public ResultMessage create(WarningVO vo) {
		return ResultMessage.SUCCESS;
	}

	@Override
	public ArrayList<WarningVO> show(String time1, String time2) {
		ArrayList<WarningLineItemVO> list = new ArrayList<WarningLineItemVO>();
		list.add(new WarningLineItemVO("00001-00001-00001", "飞利浦吊灯", "FLP01", 30, 50));
		ArrayList<WarningVO> list2 = new ArrayList<WarningVO>();
		list2.add(new WarningVO("WAR-20141023-00001", "22:21:37", list, DocumentType.WARNING));
		return list2;
	}

	@Override
	public ArrayList<WarningVO> findById(String id) {
		ArrayList<WarningLineItemVO> list = new ArrayList<WarningLineItemVO>();
		list.add(new WarningLineItemVO("00001-00001-00001", "飞利浦吊灯", "FLP01", 30, 50));
		ArrayList<WarningVO> list2 = new ArrayList<WarningVO>();
		list2.add(new WarningVO(id, "22:21:37", list, DocumentType.WARNING));
		return list2;
	}

}
