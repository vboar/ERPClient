package vo;

public class CategoryCommodityVO {
	
	public String id;
	
	public CategoryVO Categoryvo;
	
	public CommodityVO commodityvo;
	
	public CategoryCommodityVO(){}
	
	public CategoryCommodityVO(String id,CategoryVO Categoryvo,CommodityVO commodityvo){
		this.id=id;
		this.Categoryvo=Categoryvo;
		this.commodityvo=commodityvo;
	}

	

}
