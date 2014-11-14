package businesslogic.presentbl;

import util.ResultMessage;
import businesslogic.commoditybl.Commodity;

public class MockCommodity extends Commodity {
	
	private String id;
	
	private int num;
	
	public MockCommodity(String id, int num) {
		this.id = id;
		this.num = num;
	}

	public String getId() {
		return id;
	}

	public int getNum() {
		return num;
	}
	
	public ResultMessage updateNum(int number) {
		num -= number;
		return ResultMessage.SUCCESS;
	}

}
