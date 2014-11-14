/**
 * SaleController
 * @author oneoneO
 * @date 2014/11/14
 */
package businesslogic.salebl;

import java.util.ArrayList;

import util.ResultMessage;
import vo.CommodityLineItemVO;
import vo.SaleVO;
import businesslogicservice.saleblservice.SaleBLService;

public class SaleController implements SaleBLService {

	@Override
	public ResultMessage add(SaleVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<SaleVO> findByTime(String time1, String time2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<SaleVO> findByCommodityName(String commodityName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<SaleVO> findByCustomer(String customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<SaleVO> findBySalesman(String salesman) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<SaleVO> findByStorage(String Storage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<SaleVO> show() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage updateCommodityBySale(
			ArrayList<CommodityLineItemVO> list) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ResultMessage updateAccountBySale(String name,double total) {
		// TODO 自动生成的方法存根
		return null;
	}

}
