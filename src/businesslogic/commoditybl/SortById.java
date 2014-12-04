package businesslogic.commoditybl;

import java.util.Comparator;

import vo.CategoryVO;

@SuppressWarnings("rawtypes")
public class SortById implements Comparator{
	 public int compare(Object obj1,Object obj2){
	  CategoryVO po1=(CategoryVO)obj1;
	  CategoryVO po2=(CategoryVO)obj2;
	  int result=po1.id.compareTo(po2.id);
	  return result;
	 }
	}
