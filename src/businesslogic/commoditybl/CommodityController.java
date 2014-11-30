/**
 * commoditycontroller
 * @author Vboar
 * @date 2014/11/14
 */

package businesslogic.commoditybl;

import java.util.ArrayList;

import ui.commodityui.commodityui.CommodityTreeTableModel;
import util.ResultMessage;
import vo.CategoryCommodityVO;
import vo.CategoryVO;
import vo.CommodityVO;
import businesslogicservice.commodityblservice.CommodityBLService;

public class CommodityController implements CommodityBLService {

	@Override
	public ResultMessage add(CommodityVO vo) {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage delete(CommodityVO vo) {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage update(CommodityVO vo) {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCESS;
	}

	@Override
	public ArrayList<CommodityVO> show() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<CommodityVO> fuzzyFind(String keyWord) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<CategoryCommodityVO> bigShow() {
		ArrayList<CategoryCommodityVO> list = new ArrayList<CategoryCommodityVO>();
		list.add(new CategoryCommodityVO("0001",new CategoryVO("0001","a",10),null));
		list.add(new CategoryCommodityVO("0001-0001",null,
				new CommodityVO("0001-0001","aa","s01",10,5,10,5,10,0,false,new CategoryVO("0001","a",10))));
		list.add(new CategoryCommodityVO("0001-0002",null,
				new CommodityVO("0001-0002","bb","s01",10,5,10,5,10,0,false,new CategoryVO("0001","a",10))));
		list.add(new CategoryCommodityVO("0002",new CategoryVO("0002","b",10),null));
		list.add(new CategoryCommodityVO("0003",new CategoryVO("0003","c",10),null));
		list.add(new CategoryCommodityVO("0003-0001",null,
				new CommodityVO("0003-0001","cc","s01",10,5,10,5,10,0,false,new CategoryVO("0003","c",10))));
		return list;
	}

}
