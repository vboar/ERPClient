/**
 * 商品分类管理业务逻辑桩程序
 * @author Vboar
 * @date 2014/10/25
 */
package businesslogicservice.commodityblservice;

import java.util.ArrayList;

import util.ResultMessage;
import vo.CategoryVO;

public class CategoryBLService_Stub implements CategoryBLService {

	@Override
	public ResultMessage add(CategoryVO vo) {
		if (vo.name.equals("吊灯")) return ResultMessage.FAILED;
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage delete(CategoryVO vo) {
		if (vo.number > 0) return ResultMessage.FAILED;
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage update(CategoryVO vo) {
		return ResultMessage.SUCCESS;
	}

	@Override
	public ArrayList<CategoryVO> findById(String id) {
		ArrayList<CategoryVO> list = new ArrayList<CategoryVO>();
		list.add(new CategoryVO("吊灯", 2));
		list.get(0).id = "00001-00001";
		return list;
	}

	@Override
	public ArrayList<CategoryVO> findByName(String name) {
		ArrayList<CategoryVO> list = new ArrayList<CategoryVO>();
		list.add(new CategoryVO(name, 2));
		list.get(0).id = "00001-00001";
		return list;
	}

	@Override
	public ArrayList<CategoryVO> show() {
		ArrayList<CategoryVO> list = new ArrayList<CategoryVO>();
		list.add(new CategoryVO("吊灯", 2));
		list.get(0).id = "00001-00001";
		return list;
	}

}
