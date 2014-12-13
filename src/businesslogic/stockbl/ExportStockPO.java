package businesslogic.stockbl;

import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import vo.StockVO;


public class ExportStockPO  {  
    /** 
     * @功能：手工构建一个简单格式的Excel 
     */  
//    private static List<StockVO> getStockVO() throws Exception{  
//        List<StockVO> list = new ArrayList<StockVO>();  
//  
//        StockVO vo1=new StockVO("00000-00001","飞利浦吊灯","吊灯", 20,300,"2014-10-15","00001");
//        StockVO vo2=new StockVO("00000-00002","飞利浦吊灯2","吊灯", 20,301,"2014-10-15","00002");
//        StockVO vo3=new StockVO("00000-00003","飞利浦吊灯3","吊灯", 20,302,"2014-10-15","00003");
//        StockVO vo4=new StockVO("00000-00004","飞利浦吊灯4","吊灯", 20,303,"2014-10-15","00004");
//        list.add(vo1);
//        list.add(vo2);
//        list.add(vo3);
//        list.add(vo4);
//        return list;  
//    }  
//  

	public static void export(List<StockVO> voList,String path)throws Exception  {  
        // 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("库存查看表");  
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow((int) 0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
  
        HSSFCell cell = row.createCell(0);  
        
        cell.setCellValue("批次");  
        cell.setCellStyle(style);  
        cell = row.createCell(1);  
        cell.setCellValue("批号");  
        cell.setCellStyle(style);  
        cell = row.createCell(2);  
        cell.setCellValue("商品编号");  
        cell.setCellStyle(style);  
        cell = row.createCell(3);  
        cell.setCellValue("商品名称");  
        cell.setCellStyle(style);
        cell = row.createCell(4);  
        cell.setCellValue("商品型号");  
        cell.setCellStyle(style);
        cell = row.createCell(5);  
        cell.setCellValue("库存数量");  
        cell.setCellStyle(style);
        cell = row.createCell(6);  
        cell.setCellValue("库存均价");  
        cell.setCellStyle(style);

  
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，  
        List<StockVO> list = voList;
  
        for (int i = 0; i < list.size(); i++)  
        {  
            row = sheet.createRow((int) i + 1);  
            StockVO vo = (StockVO) list.get(i);  
            // 第四步，创建单元格，并设置值  
            row.createCell(0).setCellValue(vo.batch);  
            row.createCell(1).setCellValue(vo.batchNumber);  
            row.createCell(2).setCellValue(vo.commodityId);  
            row.createCell(3).setCellValue(vo.commodityName);
            row.createCell(4).setCellValue(vo.commodityModel);
            row.createCell(5).setCellValue(vo.number);
            row.createCell(6).setCellValue(vo.avgPrice);
            
            
         }  
        // 第六步，将文件存到指定位置  
        try  
        {  
            FileOutputStream fout = new FileOutputStream(path);  
            wb.write(fout);  
            fout.close();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
    }  
//	public static void main(String[] args) {
//		try {
//			export();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}       