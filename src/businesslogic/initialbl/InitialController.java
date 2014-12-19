package businesslogic.initialbl;

import java.util.ArrayList;

import businesslogic.accountbl.Account;
import dataservice.datafactoryservice.DataFactoryImpl;
import util.ResultMessage;
import vo.AccountVO;
import vo.CommodityVO;
import vo.CustomerVO;
import vo.InitialVO;
import businesslogicservice.initialblservice.InitialBLService;

public class InitialController implements InitialBLService {

	@Override
	public ArrayList<InitialVO> findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<InitialVO> show() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage initialAccount(InitialVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<CommodityVO> showCommodity(InitialVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<CustomerVO> showCustomer(InitialVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<AccountVO> showAccount(InitialVO vo) {
		return new Account().showByInitial(vo);
	}

}
