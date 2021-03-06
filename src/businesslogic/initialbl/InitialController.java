package businesslogic.initialbl;

import java.util.ArrayList;

import businesslogic.accountbl.Account;
import businesslogic.commoditybl.Category;
import businesslogic.commoditybl.Commodity;
import businesslogic.customerbl.Customer;
import dataservice.datafactoryservice.DataFactoryImpl;
import util.ResultMessage;
import vo.*;
import businesslogicservice.initialblservice.InitialBLService;

public class InitialController implements InitialBLService {

	private Initial initial = new Initial();

	@Override
	public ArrayList<InitialVO> findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<InitialVO> show() {
		return initial.show();
	}

	@Override
	public ResultMessage initialAccount() {
		return initial.initialAccount();
	}

	@Override
	public ArrayList<CategoryCommodityVO> showCommodity(String id) {
		return new Commodity().showByInitial(id);
	}

	@Override
	public ArrayList<CustomerVO> showCustomer(String id) {
		return new Customer().showByInitial(id);
	}

	@Override
	public ArrayList<AccountVO> showAccount(String id) {
		return new Account().showByInitial(id);
	}

}
