/**
 * 商品分类商品组合类
 * @author JanelDQ
 * @date 2014/11/20
 */
package vo;

public class CategoryCommodityVO {

	/**
	 * 商品Id
	 */
	public String id;

	/**
	 * 分类vo
	 */
	public CategoryVO Categoryvo;

	/**
	 * 商品vo
	 */
	public CommodityVO commodityvo;

	/**
	 * 无参构造函数
	 */
	public CategoryCommodityVO(){}

	/**
	 * 构造函数
	 * @param id
	 * @param Categoryvo
	 * @param commodityvo
	 */
	public CategoryCommodityVO(String id,CategoryVO Categoryvo,CommodityVO commodityvo){
		this.id=id;
		this.Categoryvo=Categoryvo;
		this.commodityvo=commodityvo;
	}

	

}
