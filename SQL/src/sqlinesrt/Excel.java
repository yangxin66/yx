package sqlinesrt;





import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.*;
import org.junit.Test;

import java.io.FileOutputStream;
import java.util.Date;


public class Excel {
    @Test
    public void testHSSF() throws Exception {
        //  ����һ��������
        SXSSFWorkbook wb = new SXSSFWorkbook();

        //  ����һ��������
        SXSSFSheet sheet = wb.createSheet();
        long l = System.currentTimeMillis();
        FileOutputStream fos = new FileOutputStream("C:\\Users\\Staryang\\Desktop\\YXproject\\yx\\yx\\SQL\\txt\\wb.xlsx");
        System.out.println(l);
        try {


        //  ��������
        Font font1 = wb.createFont();
        Font font2 = wb.createFont();
        font1.setFontHeightInPoints((short) 14);
        //  ������Ԫ����ʽ
        CellStyle css1 = wb.createCellStyle();
        CellStyle css2 = wb.createCellStyle();
        DataFormat df = wb.createDataFormat();
        //  ���õ�Ԫ�����弰��ʽ
        css1.setFont(font1);
        css1.setDataFormat(df.getFormat("#,##0.0"));
        css2.setFont(font2);
            System.out.println(System.currentTimeMillis());
        //  ������
       for (int i = 0; i < 1000000; i++) {
            SXSSFRow row = sheet.createRow(i);
            for (int j = 0; j < 10; j = j + 2) {
                SXSSFCell cell = row.createCell(j);
                cell.setCellValue("Spring");
                cell.setCellStyle(css1);
                SXSSFCell cell2 = row.createCell(j+1);
                cell2.setCellValue(new XSSFRichTextString("Hello!1111111111111 " + j));
                cell2.setCellStyle(css2);
            }
        }

        //  д�ļ�
        wb.write(fos);
        }finally {
            if (null != wb) {
                try {
                    wb.dispose();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (null != fos) {
                try {
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println(System.currentTimeMillis()-l);
            fos.close();
        }

    }

}
