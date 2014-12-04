/**
 * commoditycontroller
 * @author Vboar
 * @date 2014/11/14
 */

package businesslogic.commoditybl;

import java.util.ArrayList;

import util.ResultMessage;
import vo.CategoryCommodityVO;
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
		return commodity.fuzzyFind(keyWord);
				
	}

	@Override
	public ArrayList<CategoryCommodityVO> bigShow() {
		return commodity.bigShow();
	
	}

}
