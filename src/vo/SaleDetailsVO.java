package vo;

/**
 * 销售明细表VO
 * @author JanelDQ
 * @date 2014/12/7
 */
public class SaleDetailsVO {

	public String time;
	
	public String name;
	
	public String model;
	
	public int number;
	
	public double price;
	
	public double total;
	
	public SaleDetailsVO(String time, String name, String model,
			int number, double price, double total){
		this.time = time;
		this.name = name;
		this.model = model;
		this.number = number;
		this.price = price;
		this.total = total;
	}
	
}
