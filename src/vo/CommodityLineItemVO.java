package vo;

public class CommodityLineItemVO {
	/**
	 * 商品编号
	 */
    public String id;
    
    /**
     * 名称
     */
    public String name;
    
    /**
     * 型号
     */
    public String model;
    
    /**
     * 数量
     */
    public int number;
    
    /**
     * 单价
     */
    public double price;
    
    /**
     * 总价
     */
    public double total;
    
    /**
     * 备注
     */
    public String remark;
  
    
    /**
     * 构造函数
     * 
     * @param id
     * @param name
     * @param model
     * @param number
     * @param price
     * @param total
     * @param remark
     */
    public CommodityLineItemVO(String id,String name,String model,int number,
    		               double price,double total,String remark){
    	this.id=id;
    	this.name=name;
    	this.model=model;
    	this.number=number;
    	this.price=price;
    	this.total=total;
    	this.remark=" ";
    }
    
    public CommodityLineItemVO(String id, String name ,String model, int number){
    	this.id = id;
    	this.name = name;
    	this.model = model;
    	this.number = number;
    }
    
    
    public CommodityLineItemVO(String id, String name ,String model, int number,double price){
    	this.id = id;
    	this.name = name;
    	this.model = model;
    	this.number = number;
    	this.price = price;
    }
    
    @Override
    public String toString(){
    	return name + "-" + model + "-￥" + price + "-"+number;
    }
    
}
