package businesslogicservice.purchaseblservice;

import java.util.ArrayList;

import util.DocumentStatus;
import util.DocumentType;
import util.ResultMessage;
import vo.CommodityLineItemVO;
import vo.PurchaseVO;

public class PurchaseReturnBLService_Driver{
	public void drive(PurchaseBLService sbs){
		ResultMessage result;
		
		ArrayList<CommodityLineItemVO> commodity=new ArrayList<CommodityLineItemVO>();
		commodity.add(new CommodityLineItemVO("00001-00001-00001-00001","飞利浦吊灯","FLP01",5,40,200,"自提"));
	    result=sbs.add(new PurchaseVO("XSD-20141023-00001","00001","钢铁侠","XS001-浩克","1",commodity,-1750
	            ,"自提",DocumentStatus.NONCHECKED,false,DocumentType.SALE));
		
	    if(result==ResultMessage.SUCCESS)
	    	System.out.println("添加进货退货单成功！\n");
	    else
	    	System.out.println("添加进货退货单失败！\n");

	    ArrayList<PurchaseVO> list=sbs.show();
	    System.out.println("进货退货单查看结果：");
	    System.out.println("客户编号:"+list.get(0).customerId+"；客户姓名："+list.get(0).name+
	                       "；操作员："+list.get(0).operator+"；仓库："+
	    		           list.get(0).storage+"\n"+"出货商品清单：\n"+
	                       "商品编号："+list.get(0).saleList.get(0).id+"；商品名称："+
	    		           list.get(0).saleList.get(0).name+"；型号："+
	                       list.get(0).saleList.get(0).model+
	                       "；数量："+list.get(0).saleList.get(0).number+
	                       "；单价："+list.get(0).saleList.get(0).price+
	                       "；金额："+list.get(0).saleList.get(0).total+
	                       "；商品备注:"+list.get(0).saleList.get(0).remark+"\n"+
	                       "金额："+list.get(0).total+"；备注："+list.get(0).remark+
	                       "；审批状态："+list.get(0).approvalState+
	                       "；是否为红冲单据："+list.get(0).isWriteOff+"；单据类型："+list.get(0).receiptType);
	
	    list=sbs.findByTime("2014/10/20", "2014/10/26");
	    System.out.println("按时间查找进货退货单的结果：");
	    System.out.println("客户编号:"+list.get(0).customerId+"；客户姓名："+list.get(0).name+
                "；操作员："+list.get(0).operator+"；仓库："+
		           list.get(0).storage+"\n"+"出货商品清单：\n"+
                "商品编号："+list.get(0).saleList.get(0).id+"；商品名称："+
		           list.get(0).saleList.get(0).name+"；型号："+
                list.get(0).saleList.get(0).model+
                "；数量："+list.get(0).saleList.get(0).number+
                "；单价："+list.get(0).saleList.get(0).price+
                "；金额："+list.get(0).saleList.get(0).total+
                "；商品备注:"+list.get(0).saleList.get(0).remark+"\n"+
                "金额："+list.get(0).total+"；备注："+list.get(0).remark+
                "；审批状态："+list.get(0).approvalState+
                "；是否为红冲单据："+list.get(0).isWriteOff+"；单据类型："+list.get(0).receiptType);
	  
	    list=sbs.findByCommodityName("飞利浦吊灯");
	    System.out.println("按商品名称查找进货退货单的结果：");
	    System.out.println("客户编号:"+list.get(0).customerId+"；客户姓名："+list.get(0).name+
                "；操作员："+list.get(0).operator+"；仓库："+
		           list.get(0).storage+"\n"+"出货商品清单：\n"+
                "商品编号："+list.get(0).saleList.get(0).id+"；商品名称："+
		           list.get(0).saleList.get(0).name+"；型号："+
                list.get(0).saleList.get(0).model+
                "；数量："+list.get(0).saleList.get(0).number+
                "；单价："+list.get(0).saleList.get(0).price+
                "；金额："+list.get(0).saleList.get(0).total+
                "；商品备注:"+list.get(0).saleList.get(0).remark+"\n"+
                "金额："+list.get(0).total+"；备注："+list.get(0).remark+
                "；审批状态："+list.get(0).approvalState+
                "；是否为红冲单据："+list.get(0).isWriteOff+"；单据类型："+list.get(0).receiptType);
	  
	    list=sbs.findByCustomer("钢铁侠");
	    System.out.println("按客户查找进货退货单的结果：");
	    System.out.println("客户编号:"+list.get(0).customerId+"；客户姓名："+list.get(0).name+
                "；操作员："+list.get(0).operator+"；仓库："+
		           list.get(0).storage+"\n"+"出货商品清单：\n"+
                "商品编号："+list.get(0).saleList.get(0).id+"；商品名称："+
		           list.get(0).saleList.get(0).name+"；型号："+
                list.get(0).saleList.get(0).model+
                "；数量："+list.get(0).saleList.get(0).number+
                "；单价："+list.get(0).saleList.get(0).price+
                "；金额："+list.get(0).saleList.get(0).total+
                "；商品备注:"+list.get(0).saleList.get(0).remark+"\n"+
                "金额："+list.get(0).total+"；备注："+list.get(0).remark+
                "；审批状态："+list.get(0).approvalState+
                "；是否为红冲单据："+list.get(0).isWriteOff+"；单据类型："+list.get(0).receiptType);
	  
	    list=sbs.findBySalesman("美队");
	    System.out.println("按业务员查找进货退货单的结果：");
	    System.out.println("客户编号:"+list.get(0).customerId+"；客户姓名："+list.get(0).name+
                "；操作员："+list.get(0).operator+"；仓库："+
		           list.get(0).storage+"\n"+"出货商品清单：\n"+
                "商品编号："+list.get(0).saleList.get(0).id+"；商品名称："+
		           list.get(0).saleList.get(0).name+"；型号："+
                list.get(0).saleList.get(0).model+
                "；数量："+list.get(0).saleList.get(0).number+
                "；单价："+list.get(0).saleList.get(0).price+
                "；金额："+list.get(0).saleList.get(0).total+
                "；商品备注:"+list.get(0).saleList.get(0).remark+"\n"+
                "金额："+list.get(0).total+"；备注："+list.get(0).remark+
                "；审批状态："+list.get(0).approvalState+
                "；是否为红冲单据："+list.get(0).isWriteOff+"；单据类型："+list.get(0).receiptType);
	    
	    list=sbs.findByStorage("1");
	    System.out.println("按仓库查找进货退货单的结果：");
	    System.out.println("客户编号:"+list.get(0).customerId+"；客户姓名："+list.get(0).name+
                "；操作员："+list.get(0).operator+"；仓库："+
		           list.get(0).storage+"\n"+"出货商品清单：\n"+
                "商品编号："+list.get(0).saleList.get(0).id+"；商品名称："+
		           list.get(0).saleList.get(0).name+"；型号："+
                list.get(0).saleList.get(0).model+
                "；数量："+list.get(0).saleList.get(0).number+
                "；单价："+list.get(0).saleList.get(0).price+
                "；金额："+list.get(0).saleList.get(0).total+
                "；商品备注:"+list.get(0).saleList.get(0).remark+"\n"+
                "金额："+list.get(0).total+"；备注："+list.get(0).remark+
                "；审批状态："+list.get(0).approvalState+
                "；是否为红冲单据："+list.get(0).isWriteOff+"；单据类型："+list.get(0).receiptType);
	}
}
