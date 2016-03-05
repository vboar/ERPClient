package businesslogic.commoditybl;

import java.util.Comparator;

import vo.CategoryCommodityVO;

@SuppressWarnings("rawtypes")
public class SortByIdForBig implements Comparator{

	 public int compare(Object obj1,Object obj2){
		 CategoryCommodityVO vo1=(CategoryCommodityVO)obj1;
		 CategoryCommodityVO vo2=(CategoryCommodityVO)obj2;
		  int result=vo1.id.compareTo(vo2.id);
		  return result;

}
}
