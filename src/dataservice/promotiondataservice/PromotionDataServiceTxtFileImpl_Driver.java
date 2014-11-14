/**
 * 促销策略数据驱动
 * @date 2014/10/26
 * @author chengcheng
 */

package dataservice.promotiondataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CustomerGiftPO;
import po.SpecialOfferPO;
import po.TotalGiftPO;

public class PromotionDataServiceTxtFileImpl_Driver {
	public void drive(PromotionDataService promotionDataService) throws RemoteException{
		promotionDataService.insert(
				new CustomerGiftPO("00001",5,null,100.0,10.0,"2014/10/23","2014/10/25",false));
		
		promotionDataService.insert(new SpecialOfferPO(
				"00002", null, 100.0, "2014/10/23","2014/10/25",false));
		
		promotionDataService.insert(new TotalGiftPO("00002",10000.0,null,10.0,10.0,"2014/10/23","2014/10/25",false));
		
		
		promotionDataService.update(
				new CustomerGiftPO("00001",5,null,100.0,10.0,"2014/10/23","2014/10/25",false));
		
		promotionDataService.update(new SpecialOfferPO(
				"00002", null, 100.0, "2014/10/23","2014/10/25",false));
		
		promotionDataService.update(new TotalGiftPO("00002",10000.0,null,10.0,10.0,"2014/10/23","2014/10/25",false));
		
		
		ArrayList<CustomerGiftPO> voList=new ArrayList<CustomerGiftPO>();
		voList=promotionDataService.findByVip();
		System.out.println("折让金额为：");
		System.out.println(voList.get(0).getDiscount());
		
		ArrayList<SpecialOfferPO> voList2=new ArrayList<SpecialOfferPO>();
		voList2=promotionDataService.findByCommodity();
		System.out.println("特价包金额为：");
		System.out.println(voList2.get(0).getTotal());
		
		ArrayList<TotalGiftPO> voList3=new ArrayList<TotalGiftPO>();
		voList3=promotionDataService.findByPrice();
		System.out.println("折让金额为：");
		System.out.println(voList3.get(0).getDiscount());
		
		
	}

}
