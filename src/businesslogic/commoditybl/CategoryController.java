/**
 * categorycontroller
 * @author Vboar
 * @date 2014/11/14
 */

package businesslogic.commoditybl;

import java.util.ArrayList;

import util.ResultMessage;
import vo.CategoryVO;
import businesslogicservice.commodityblservice.CategoryBLService;

public class CategoryController implements CategoryBLService {

	Category category=new Category();
	@Override
	public ResultMessage add(CategoryVO vo) {
		return category.add(vo);
		
	}
	
	@Override
	public String createId(String fatherId){
		return category.createId(fatherId);
	}
	
	@Override
	public ResultMessage delete(CategoryVO vo) {
		
		return category.delete(vo);
	}

	@Override
	public ResultMessage update(CategoryVO vo) {
		return category.update(vo);
	}

	@Override
	public ArrayList<CategoryVO> show() {
		return category.show();
	}

	public ArrayList<CategoryVO> fuzzyFind(String keyword) {
		return category.fuzzyFind(keyword);
				
	}

}
