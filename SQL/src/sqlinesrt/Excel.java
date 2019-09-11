package sqlinesrt;

import org.junit.Test;

public class Excel {
    @Test
    public void testHSSF() throws Exception {
        //  ����һ��������
        HSSFWorkbook wb = new HSSFWorkbook();
        //  ����һ��������
        HSSFSheet sheet = wb.createSheet();

        //  ��������
        HSSFFont font1 = wb.createFont();
        HSSFFont font2 = wb.createFont();
        font1.setFontHeightInPoints((short) 14);
        font1.setColor(HSSFColor.HSSFColorPredefined.RED.getIndex());
        font2.setFontHeightInPoints((short) 12);
        font2.setColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
        //  ������Ԫ����ʽ
        HSSFCellStyle css1 = wb.createCellStyle();
        HSSFCellStyle css2 = wb.createCellStyle();
        HSSFDataFormat df = wb.createDataFormat();
        //  ���õ�Ԫ�����弰��ʽ
        css1.setFont(font1);
        css1.setDataFormat(df.getFormat("#,##0.0"));
        css2.setFont(font2);
        css2.setDataFormat(HSSFDataFormat.getBuiltinFormat("text"));

        //  ������
        for (int i = 0; i < 20; i++) {
            HSSFRow row = sheet.createRow(i);
            for (int j = 0; j < 10; j = j + 2) {
                HSSFCell cell = row.createCell(j);
                cell.setCellValue("Spring");
                cell.setCellStyle(css1);

                HSSFCell cell2 = row.createCell(j+1);
                cell2.setCellValue(new HSSFRichTextString("Hello! " + j));
                cell2.setCellStyle(css2);
            }
        }

        //  д�ļ�
        FileOutputStream fos = new FileOutputStream("G:/wb.xls");
        wb.write(fos);
        fos.close();
    }

    @Test
    public void testSS() throws IOException {
        Workbook[] wbs = {new HSSFWorkbook(), new XSSFWorkbook()};
        for (int i = 0; i < wbs.length; i++) {
            Workbook wb = wbs[i];
            CreationHelper creationHelper = wb.getCreationHelper();
            Sheet sheet = wb.createSheet();
            for (int j = 0; j < 10; j++) {
                Row row = sheet.createRow(j);
                Cell cell = row.createCell(0);
                cell.setCellValue(creationHelper.createRichTextString("ABC"));
            }

            String filename = "G:/workbook.xls";
            if (wb instanceof XSSFWorkbook) {
                filename = filename + "x";
            }
            wb.write(new FileOutputStream(filename));
            wb.close();
        }
    }
}
