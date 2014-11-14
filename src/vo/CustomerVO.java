/**
 * 客户vo类
 * @date 2014/10/25
 * @author chengcheng
 */
package vo;

public class CustomerVO {

	/**
	 * 客户编号
	 */
	public String id;
	
	/**
	 * 分类
	 */
    public int category;
    
    /**
     * 级别  
     */
    public int level;
    
    /**
     * 姓名
     */
    public String name;
    
    /**
     * 电话
     */
    public String phoneNumber;
    
    /**
     * 地址
     */
    public String address;
    
    /**
     * 邮编
     */
    public String postalCode;
    
    /**
     * 电子邮箱
     */
    public String email;
    
    /**
     * 应收额度
     */
    public double creditLimit;
    
    /**
     * 应收
     */
    public double receivables;
    
    /**
     * 应付
     */
    public double paybles;
    
    /**
     * 默认操作员
     */
    public String defaultOperator;
    
    /**
     * 是否可删
     */
    public boolean isDeletable;
    
    /**
     * 构造方法
     * 
     * @param id
     * @param category
     * @param level
     * @param name
     * @param phoneNumber
     * @param address
     * @param postalCode
     * @param email
     * @param creditLimit
     * @param receivables
     * @param paybles
     * @param defaultOperator
     * @param isDeletable
     */
    public CustomerVO(String id,int category,int level,String name,
  		            String phoneNumber,String address,String postalCode,String email,
  		            double creditLimit,double receivables,double paybles,
  		            String defaultOperator,boolean isDeletable){
  	  this.id=id;
  	  this.category=category;
  	  this.level=level;
  	  this.name=name;
  	  this.phoneNumber=phoneNumber;
  	  this.address=address;
  	  this.postalCode=postalCode;
  	  this.email=email;
  	  this.creditLimit=creditLimit;
  	  this.receivables=receivables;
  	  this.paybles=paybles;
  	  this.defaultOperator=defaultOperator;
      this.isDeletable=isDeletable;    	 
    }
}
