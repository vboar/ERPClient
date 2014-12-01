/**
 * commoditycontroller
 * @author Vboar
 * @date 2014/11/14
 */

package businesslogic.commoditybl;

import java.util.ArrayList;

import util.ResultMessage;
import vo.CategoryCommodityVO;
import vo.CategoryVO;
import vo.CommodityVO;
import businesslogicservice.commodityblservice.CommodityBLService;

public class CommodityController implements CommodityBLService {

	Commodity commodity=new Commodity();
	@Override
	public ResultMessage add(CommodityVO vo) {
		return 	commodity.add(vo);
	}

	@Override
	public ResultMessage delete(CommodityVO vo) {
		return commodity.delete(vo);
		
	}

	@Override
	public ResultMessage update(CommodityVO vo) {
		return commodity.update(vo);
	}

	@Override
	public ArrayList<CommodityVO> show() {
		return commodity.show();
	}

	@Override
	public ArrayList<CommodityVO> fuzzyFind(String keyWord) {
		ArrayList<CommodityVO> list = new ArrayList<CommodityVO>();

		if(keyWord.equals("00")){
			list.add(new CommodityVO("001-0001","aa","s01",10,5,10,5,10,0,false,new CategoryVO("0001","a",10)));
			list.add(new CommodityVO("002-0001","aa","s01",10,5,10,5,10,0,false,new CategoryVO("0001","a",10)));
			return list;
		}else if(keyWord.equals("0")){
			list.add(new CommodityVO("001-0001","aa","s01",10,5,10,5,10,0,false,new CategoryVO("0001","a",10)));
			list.add(new CommodityVO("002-0001","aa","s01",10,5,10,5,10,0,false,new CategoryVO("0001","a",10)));
			list.add(new CommodityVO("013-0001","aa","s01",10,5,10,5,10,0,false,new CategoryVO("0001","a",10)));
			return list;
		}else if(keyWord.equals("001")){
			list.add(new CommodityVO("001-0001","aa","s01",10,5,10,5,10,0,false,new CategoryVO("0001","a",10)));
			return list;
		}
		return null;
	}

	@Override
	public ArrayList<CategoryCommodityVO> bigShow() {
		ArrayList<CategoryCommodityVO> list = new ArrayList<CategoryCommodityVO>();
		list.add(new CategoryCommodityVO("0001",new CategoryVO("0001","a",10),null));
		list.add(new CategoryCommodityVO("0001-0001",new CategoryVO("0001-0001","a",10),null));
		list.add(new CategoryCommodityVO("0001-0001-0001",null,
				new CommodityVO("0001-0001-0001","aa","s01",10,5,10,5,10,0,false,new CategoryVO("0001-0001","a",10))));
		list.add(new CategoryCommodityVO("0002",new CategoryVO("0002","b",10),null));
		list.add(new CategoryCommodityVO("0002-0001",null,
				new CommodityVO("0002-0001","bb","s01",10,5,10,5,10,0,false,new CategoryVO("0002","a",10))));
		list.add(new CategoryCommodityVO("0003",new CategoryVO("0003","c",10),null));
		list.add(new CategoryCommodityVO("0003-0001",null,
				new CommodityVO("0003-0001","cc","s01",10,5,10,5,10,0,false,new CategoryVO("0003","c",10))));
		return list;
	}

}
