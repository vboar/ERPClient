package businesslogic.commoditybl;

import java.util.Comparator;

import po.CategoryPO;

@SuppressWarnings("rawtypes")
public class SortById implements Comparator{
	 public int compare(Object obj1,Object obj2){
	  CategoryPO po1=(CategoryPO)obj1;
	  CategoryPO po2=(CategoryPO)obj2;
	  int result=po1.getId().compareTo(po2.getId());
	  return result;
	 }
	}
