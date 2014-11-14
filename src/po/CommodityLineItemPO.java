/**
 * 进出货清单PO类
 * @author oneoneO
 * @date  2014/10/25 
 */
package po;

import java.io.Serializable;

public class CommodityLineItemPO implements Serializable{

	/**
	 * 序列化UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 商品编号
	 */
    private String id;
    
    /**
     * 名称
     */
    private String name;
    
    /**
     * 型号
     */
    private String model;
    
    /**
     * 数量
     */
    private int number;
    
    /**
     * 单价
     */
    private double price;
    
    /**
     * 总价
     */
    private double total;
    
    /**
     * 备注
     */
    private String remark;
  
    
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
    public CommodityLineItemPO(String id,String name,String model,int number,
    		               double price,double total,String remark){
    	this.id=id;
    	this.name=name;
    	this.model=model;
    	this.number=number;
    	this.price=price;
    	this.total=total;
    	this.remark=remark;
    }
    
    public String getId(){
    	return id;
    }
    
    public String getName(){
    	return name;
    }
    
    public String getModel(){
    	return model;
    }
    
    public int number(){
    	return number;
    }
    
    public double price(){
    	return price;
    }
    
    public double total(){
    	return total;
    }
    
    public String remark(){
    	return remark;
    }
}
