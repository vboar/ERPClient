/**
 * 商品管理业务逻辑桩程序
 * @author Vboar
 * @date 2014/10/25
 */
package businesslogicservice.commodityblservice;

import java.util.ArrayList;

import util.ResultMessage;
import vo.CommodityVO;

public class CommodityBLService_Stub implements CommodityBLService {

	@Override
	public ResultMessage add(CommodityVO vo) {
		if (vo.name.equals("飞利浦吊灯") && vo.model.equals("FLP01")) 
			return ResultMessage.FAILED;
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage delete(CommodityVO vo) {
		if (vo.isTrade) return ResultMessage.FAILED;
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage update(CommodityVO vo) {
		return ResultMessage.SUCCESS;
	}

	@Override
	public ArrayList<CommodityVO> findById(String id) {
		ArrayList<CommodityVO> list = new ArrayList<CommodityVO>();
		list.add(new CommodityVO(id, "飞利浦吊灯", "FLP01", 233, 20 ,40 ,20 ,40, 50, true,null));
		return list;
	}

	@Override
	public ArrayList<CommodityVO> findByName(String name) {
		ArrayList<CommodityVO> list = new ArrayList<CommodityVO>();
		list.add(new CommodityVO("00001-00001-00001", name, "FLP01", 233, 20 ,40 ,20 ,40, 50, true,null));
		return list;
	}

	@Override
	public ArrayList<CommodityVO> findByModel(String model) {
		ArrayList<CommodityVO> list = new ArrayList<CommodityVO>();
		list.add(new CommodityVO("00001-00001-00001", "飞利浦吊灯", model, 233, 20 ,40 ,20 ,40, 50, true,null));
		return list;
	}

	@Override
	public ArrayList<CommodityVO> show() {
		ArrayList<CommodityVO> list = new ArrayList<CommodityVO>();
		list.add(new CommodityVO("00001-00001-00001", "飞利浦吊灯", "FLP01", 233, 20 ,40 ,20 ,40, 50, true,null));
		return list;
	}

}
