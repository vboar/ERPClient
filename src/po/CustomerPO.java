/**
 * 客户PO类
 * @author oneoneO
 * @date  2014/10/25 
 */
package po;

import java.io.Serializable;

public class CustomerPO implements Serializable {

	/**
	 * 序列化UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 客户编号
	 */
	private String id;
	
	/**
	 * 分类
	 */
	private int category;
    
    /**
     * 级别  
     */
    private int level;
    
    /**
     * 姓名
     */
    private String name;
    
    /**
     * 电话
     */
    private String phoneNumber;
    
    /**
     * 地址
     */
    private String address;
    
    /**
     * 邮编
     */
    private String postalCode;
    
    /**
     * 电子邮箱
     */
    private String email;
    
    /**
     * 应收额度
     */
    private double creditLimit;
    
    /**
     * 应收
     */
    private double receivables;
    
    /**
     * 应付
     */
    private double paybles;
    
    /**
     * 默认操作员
     */
    private String defaultOperator;
    
    /**
     * 是否可删
     */
    private boolean isDeletable;
    
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
    public CustomerPO(String id,int category,int level,String name,
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

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(double creditLimit) {
		this.creditLimit = creditLimit;
	}

	public double getReceivables() {
		return receivables;
	}

	public void setReceivables(double receivables) {
		this.receivables = receivables;
	}

	public double getPaybles() {
		return paybles;
	}

	public void setPaybles(double paybles) {
		this.paybles = paybles;
	}

	public String getDefaultOperator() {
		return defaultOperator;
	}

	public void setDefaultOperator(String defaultOperator) {
		this.defaultOperator = defaultOperator;
	}

	public boolean isDeletable() {
		return isDeletable;
	}

	public void setDeletable(boolean isDeletable) {
		this.isDeletable = isDeletable;
	}

	public String getId() {
		return id;
	}
    
   
}
