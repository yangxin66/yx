//package sqlinesrt;
//
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.math.BigInteger;
//import java.util.List;
//
//import java.util.regex.Pattern;
//
//import org.apache.poi.xwpf.usermodel.XWPFDocument;
//import org.apache.poi.xwpf.usermodel.XWPFParagraph;
//import org.apache.poi.xwpf.usermodel.XWPFRun;
//import org.apache.poi.xwpf.usermodel.XWPFTable;
//import org.apache.poi.xwpf.usermodel.XWPFTableCell;
//import org.apache.poi.xwpf.usermodel.XWPFTableRow;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.STVerticalJc;
//
//class App {
//    private static String regex = "[a-zA-Z]+";
//    private static Pattern pattern = Pattern.compile(regex);
//
//    public static void main(String[] args) throws IOException {
//        XWPFDocument doc = new XWPFDocument(); //����һ������
//        XWPFParagraph para = doc.createParagraph(); //һ��XWPFRun���������ͬ���Ե�һ������һ���ı�
//        XWPFRun run = para.createRun();
//        run.setBold(true); //�Ӵ�
//        run.setText("�Ӵֵ�����");
//        run = para.createRun();
//        run.setColor("FF0000");
//        run.setText("��ɫ���֡�");
//        XWPFTable table = doc.createTable(5, 5); //�������ӵ���ԭ����ʼ����������5����ͨ��getTableCells()������ȡʱ��ȡ��������ͨ��row�����ľͿ��ԡ� //table.addNewCol(); //���������һ�У����6��
//        table.createRow(); //���������һ�У����6��
//        List rows = table.getRows(); //�������
//        CTTblPr tablePr = table.getCTTbl().addNewTblPr(); //�����
//        CTTblWidth width = tablePr.addNewTblW();
//        width.setW(BigInteger.valueOf(8000));
//        XWPFTableRow row;
//        List cells;
//        XWPFTableCell cell;
//        int rowSize = rows.size();
//        int cellSize;
//        for (int i = 0; i < 5; i++) {
//            row = (XWPFTableRow) rows.get(i);//������Ԫ��
//            row.addNewTableCell(); //�����еĸ߶�
//            row.setHeight(500); //������ //CTTrPr rowPr = row.getCtRow().addNewTrPr(); //���ַ�ʽ�ǿ��Ի�ȡ��������cell�ġ� //List list = row.getCtRow().getTcList();
//            cells = row.getTableCells();
//            cellSize = cells.size();
//            for (int j = 0; j < 5; j++) {
//                cell = (XWPFTableCell) cells.get(j);
//                if ((i + j) % 2 == 0) { //���õ�Ԫ�����ɫ
//                    cell.setColor("ff0000"); //��ɫ
//                } else {
//                    cell.setColor("0000ff"); //��ɫ
//                } //��Ԫ������
//                CTTcPr cellPr = cell.getCTTc().addNewTcPr();
//                cellPr.addNewVAlign().setVal(STVerticalJc.CENTER);
//                if (j == 3) { //���ÿ��
//                    cellPr.addNewTcW().setW(BigInteger.valueOf(3000));
//                }
//                cell.setText(i + ", " + j);
//            }
//        } //�ļ�������ʱ���Զ�����
//        OutputStream os = new FileOutputStream("C:\\Users\\Staryang\\Desktop\\YXproject\\yx\\yx\\SQL\\txt\\simpleWrite.docx"); //��doc����������
//        doc.write(os);
//        os.close();
//
//    }
//}
