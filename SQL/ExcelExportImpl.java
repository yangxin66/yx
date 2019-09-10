package com.deloitte.dhr.common.export;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.deloitte.dhr.common.export.model.ExportTitle;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <br/>26/08/2019 18:20
 *
 * @author lshao
 */
public class ExcelExportImpl implements Exports {

    @Override
    public void generateDoucment(HttpServletResponse response, List<ExportTitle> title, JSONArray data, String fileName,List<Boolean> sendFlags) throws IOException, ParseException {

//        fileName=fileName+".xls";

        response.reset();// 清空输出流
		//对生成的excel进行命名
        String filename=response.encodeURL( new String("导出excel.xls".getBytes("UTF-8"), "ISO-8859-1"));
		//跨域
        response.setHeader("Access-Control-Allow-Origin","*");
		
        response.setHeader("Access-Control-Allow-Methods", "*");
		//命名
        response.setHeader("Content-Disposition", "attachment;filename=" + filename);
		//设置格式
        response.setContentType("application/octet_stream;charset=utf-8");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,XFILENAME,XFILECATEGORY,XFILESIZE");
      //  response.setContentType("application/vnd.ms-excel;charset=UTF-8");// 定义输出类型

        response.setCharacterEncoding("UTF-8");
        //fileName = URLEncoder.encode(fileName, "UTF-8");

        //  创建一个工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        //  创建一个工作表
        HSSFSheet sheet = wb.createSheet("sheet1");
		//设置单元格高度
        sheet.setDefaultRowHeightInPoints(20);
		//打开流
        OutputStream os=response.getOutputStream();
        //设置日期格式
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);//水平居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
       // HSSFDataFormat format= wb.createDataFormat();
	   //设置日期格式
        cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
        //创建表格的title
        HSSFRow titleRow=sheet.createRow(0);

		//生成表头
        for (int i = 0; i < title.size(); i++) {
            //ExportTitle exportTitle=title[i];
            HSSFCell cell = titleRow.createCell(i);
            cell.setCellValue(title.get(i).getName());
			//设置大小
            sheet.setColumnWidth(cell.getColumnIndex(), 256 * 30);
            //把格式写入单元格
            cell.setCellStyle(cellStyle);

        }

        //插入表格中的数据
        //  创建行
        for (int i = 0; i < data.size(); i++) {
            HSSFRow row = sheet.createRow(i+1);
            //JSONObject object = data.getJSONObject(i);
            for (int j = 0; j < title.size(); j++) {

                JSONObject object = data.getJSONObject(i);
                ExportTitle exportTitle1 = title.get(j);
                String[] titles=exportTitle1.getField().split("\\.");

                //如果第一层没有
                if(titles.length>1){
                    for(int k=0;k<titles.length-1;k++){
                        object=object.getJSONObject(titles[k]);
                        if(k==titles.length-2){
                            HSSFCell cell = row.createCell(j);
//                            //ExportTitle exportTitle=title.get(j);
//                           if(object.getString(titles[k+1]).equals("sendFlag")){
//                               if (sendFlag)
//                                   cell.setCellValue("待审核");
//                               else
//                                   cell.setCellValue("未发送邮件");
//
//                           }else if(object.getString(titles[k+1]).equals("revirwFlag"))
//                               cell.setCellValue("已审核");
//                           else
                            cell.setCellValue(object.getString(titles[k+1]));
                            //把格式写入单元格
                            cell.setCellStyle(cellStyle);
                            sheet.setColumnWidth(cell.getColumnIndex(), 256 * 50);
                        }
                    }
                }else{
                    HSSFCell cell = row.createCell(j);
                     // ExportTitle exportTitle=title.get(j);
					 //进行状态判断
                    if(titles[0].equals("sendFlag")){
                        if (sendFlags.get(i)){
                            cell.setCellValue("待审核");
                        }else{
                            cell.setCellValue("未发送邮件");
                        }
                    }else if(titles[0].equals("reviewFlag  ")) {
                        cell.setCellValue("已审核");
                    }else  if(titles[0].equals("_OPTIONTIME")){
                        String string = object.getString(titles[0]);
                        SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd");
                        Date date=sDateFormat.parse(string);
                        cell.setCellValue(date);
                    }else
                        cell.setCellValue(object.getString(titles[0]));
                    cell.setCellStyle(cellStyle);
                    sheet.setColumnWidth(cell.getColumnIndex(), 256 * 50);
                }
            }
        }
        wb.write(os); // 写入文件
        os.flush();
        os.close();
    }

}
