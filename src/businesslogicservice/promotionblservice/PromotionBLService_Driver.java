/**
 * 促销策略制定逻辑驱动模块
 * date 2014/10/25
 * @author chengcheng
 */
package businesslogicservice.promotionblservice;

import java.util.ArrayList;

import util.ResultMessage;
import vo.CustomerGiftVO;
import vo.SpecialOfferVO;
import vo.TotalGiftVO;

public class PromotionBLService_Driver {
	public void drive(PromotionBLService promotionBLService){
		ResultMessage result;
		
		result=promotionBLService.createByVip(
				new CustomerGiftVO("00001",5,null,100.0,10.0,"2014/10/23","2014/10/25",false));
		if(result==ResultMessage.SUCCESS){
			System.out.println("创建成功");
		}
			
		result=promotionBLService.createByCommodity(new SpecialOfferVO(
					"00002", null, 100.0, "2014/10/23","2014/10/25",false));
			if(result==ResultMessage.SUCCESS){
				System.out.println("创建成功");	
		}
			
		result=promotionBLService.createByPrice(
				new TotalGiftVO("00002",10000.0,null,10.0,"2014/10/23","2014/10/25",false));
			if(result==ResultMessage.SUCCESS){
				System.out.println("创建成功");					
		}
		result=promotionBLService.update(
					new CustomerGiftVO("00001",5,null,100.0,10.0,"2014/10/23","2014/10/25",false));
			if(result==ResultMessage.SUCCESS){
				System.out.println("创建成功");
			}
		result=promotionBLService.update(new SpecialOfferVO(
					"00002", null, 100.0, "2014/10/23","2014/10/25",false));
			if(result==ResultMessage.SUCCESS){
				System.out.println("创建成功");	
		}
		result=promotionBLService.update(
					new TotalGiftVO("00002",10000.0,null,10.0,"2014/10/23","2014/10/25",false));
				if(result==ResultMessage.SUCCESS){
					System.out.println("创建成功");					
			}	
				
				ArrayList<CustomerGiftVO> voList=new ArrayList<CustomerGiftVO>();
				voList=promotionBLService.findByVip();
				System.out.println("折让金额为：");
				System.out.println(voList.get(0).discount);
				
				ArrayList<SpecialOfferVO> voList2=new ArrayList<SpecialOfferVO>();
				voList2=promotionBLService.findByCommodity();
				System.out.println("特价包金额为：");
				System.out.println(voList2.get(0).total);
				
								
				
				
	}

}
