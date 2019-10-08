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
//        //  ����һ��������
//        HSSFWorkbook wb = new HSSFWorkbook();
//        //  ����һ��������
//        HSSFSheet sheet = wb.createSheet();
//
//        //  ��������
//        HSSFFont font1 = wb.createFont();
//        HSSFFont font2 = wb.createFont();
//        font1.setFontHeightInPoints((short) 14);
//        //  ������Ԫ����ʽ
//        HSSFCellStyle css1 = wb.createCellStyle();
//        HSSFCellStyle css2 = wb.createCellStyle();
//        HSSFDataFormat df = wb.createDataFormat();
//        //  ���õ�Ԫ�����弰��ʽ
//        css1.setFont(font1);
//        css1.setDataFormat(df.getFormat("#,##0.0"));
//        css2.setFont(font2);
//        css2.setDataFormat(HSSFDataFormat.getBuiltinFormat("text"));
//
//        //  ������
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
//        //  д�ļ�
//        FileOutputStream fos = new FileOutputStream("C:\\Users\\Staryang\\Desktop\\YXproject\\yx\\yx\\SQL\\txt\\wb.xls");
//        wb.write(fos);
//        fos.close();
//    }
//
//}
