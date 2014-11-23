/**
 * 期初建账业务逻辑桩程序
 * @author Vboar
 * @date 2014/10/26
 */
package businesslogicservice.initialblservice;

import java.util.ArrayList;

import util.ResultMessage;
import vo.AccountVO;
import vo.CommodityVO;
import vo.CustomerVO;
import vo.InitialVO;

public class InitialBLService_Stub implements InitialBLService {

	@Override
	public ArrayList<InitialVO> findById(String id) {
		ArrayList<InitialVO> list = new ArrayList<InitialVO>();
		list.add(new InitialVO(id, "2014期初"));
		return list;
	}

	@Override
	public ArrayList<InitialVO> show() {
		ArrayList<InitialVO> list = new ArrayList<InitialVO>();
		list.add(new InitialVO("INI_2014", "2014期初"));
		return list;
	}
	
	@Override
	public ResultMessage initialAccount(InitialVO vo) {
		return ResultMessage.SUCCESS;
	}

	@Override
	public ArrayList<CommodityVO> showCommodity(InitialVO vo) {
		ArrayList<CommodityVO> list = new ArrayList<CommodityVO>();
		list.add(new CommodityVO("00001-00001-00001", "飞利浦吊灯", "FLP01", 233, 20 ,40 ,20 ,40, 50, true,null));
		return list;
	}

	@Override
	public ArrayList<CustomerVO> showCustomer(InitialVO vo) {
		ArrayList<CustomerVO> list = new ArrayList<CustomerVO>();
		list.add(new CustomerVO("00001", 0, 1, "金刚狼", "12345678901", 
				"美国加利福尼州", "210000", "Ironman@gmail.com", 10000, 20000, 0, "美队", false));
		return list;
	}

	@Override
	public ArrayList<AccountVO> showAccount(InitialVO vo) {
		ArrayList<AccountVO> list = new ArrayList<AccountVO>();
		list.add(new AccountVO("工商银行账户1","6111013200067574123",10000));
		list.add(new AccountVO("中国银行账户1","6111013200067574123",10000));
		return list;
	}

}
