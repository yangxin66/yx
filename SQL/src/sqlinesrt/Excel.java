//package sqlinesrt;
//
//import org.apache.poi.hssf.usermodel.*;
//
//import org.junit.Test;
//
//import java.io.FileOutputStream;
//
//public class Excel {
//    @Test
//    public void testHSSF() throws Exception {
//        //  创建一个工作簿
//        HSSFWorkbook wb = new HSSFWorkbook();
//        //  创建一个工作表
//        HSSFSheet sheet = wb.createSheet();
//
//        //  创建字体
//        HSSFFont font1 = wb.createFont();
//        HSSFFont font2 = wb.createFont();
//        font1.setFontHeightInPoints((short) 14);
//        //  创建单元格样式
//        HSSFCellStyle css1 = wb.createCellStyle();
//        HSSFCellStyle css2 = wb.createCellStyle();
//        HSSFDataFormat df = wb.createDataFormat();
//        //  设置单元格字体及格式
//        css1.setFont(font1);
//        css1.setDataFormat(df.getFormat("#,##0.0"));
//        css2.setFont(font2);
//        css2.setDataFormat(HSSFDataFormat.getBuiltinFormat("text"));
//
//        //  创建行
//        for (int i = 0; i < 20; i++) {
//            HSSFRow row = sheet.createRow(i);
//            for (int j = 0; j < 10; j = j + 2) {
//                HSSFCell cell = row.createCell(j);
//                cell.setCellValue("Spring");
//                cell.setCellStyle(css1);
//
//                HSSFCell cell2 = row.createCell(j+1);
//                cell2.setCellValue(new HSSFRichTextString("Hello! " + j));
//                cell2.setCellStyle(css2);
//            }
//        }
//
//        //  写文件
//        FileOutputStream fos = new FileOutputStream("C:\\Users\\Staryang\\Desktop\\YXproject\\yx\\yx\\SQL\\txt\\wb.xls");
//        wb.write(fos);
//        fos.close();
//    }
//
//}
