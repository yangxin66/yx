package com.deloitte.dhr.hr.provider.gateway.rest.v1;



import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.deloitte.dhr.common.export.ExcelExportImpl;
import com.deloitte.dhr.common.export.model.ExportTitle;
import com.deloitte.dhr.hr.api.ExcelOutInterface;
import com.deloitte.dhr.hr.api.model.*;
import com.deloitte.dhr.hr.provider.service.HrStaffService;
import com.deloitte.dhr.hr.provider.service.StaffInfoService;
import com.deloitte.infrastructure.communication.Request;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/api/v1/export")
public class ExcelController implements ExcelOutInterface {


    @Autowired
    HrStaffService hrStaffService;

    @Autowired
    ObjectMapper objectMapper;


    @ApiOperation(value = "输入员工编号",notes = "导出员工全部信息到Excel中")
    @ApiImplicitParams({

    })
    @RequestMapping(value = "/excel",method = RequestMethod.POST)
    @Override
    public void getExcel(HttpServletResponse  response, String data) throws IOException, ParseException {

//        String data2 = request.getData();
//        Boolean searchType;//是否审核
        List<Boolean> sendFlags = new ArrayList<>();//是否发送邮件
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(Request.class, ExcelOutExprotDto.class);
        Request<ExcelOutExprotDto>  excelOutExprotDtoRequest = objectMapper.readValue(data, javaType);
//        SerializerUtils.deserialize(data, Request<ExcelOutExprotDto>.class);
//        Request<ExcelOutExprotDto>  excelOutExprotDtoRequest= JSONObject.parseObject(data,new TypeReference<Request<ExcelOutExprotDto>>(){});
        ExcelOutExprotDto excelOutExprotDto = excelOutExprotDtoRequest.getData();
//        if(excelOutExprotDto.getSearchDto().getType().equals(SearchTypeEnum.COMPLETE_TASK.toString())){
//             searchType=true;
//        }else   searchType=false;
        List<ExportTitle> title = excelOutExprotDto.getTitle();
        String outType = excelOutExprotDto.getOutType().name();
        SearchDto searchDto = excelOutExprotDto.getSearchDto();
        List<String> staffNoList = excelOutExprotDto.getStaffListDto().getStaffNoList();
        ExcelExportImpl excelExport = new ExcelExportImpl();
        JSONArray data2 = new JSONArray();

        //批量导出
        if(outType.equals(OutExportType.BATCH_EXPORT.toString())){
            List<StaffInfoStatus> staffInfoStatusList= hrStaffService.getEmployJsonObjectListFromStaffIds(staffNoList);
            for (int k=staffInfoStatusList.size();0 <= k;k--) {
//                JSONObject staffInfo = staffInfoStatus.getStaffInfo();
                Boolean sendFlag = staffInfoStatusList.get(k).isSendFlag();
                sendFlags.add(sendFlag);
                data2.add(staffInfoStatusList.get(k).getData());
            }

        }
        //全部导出
        //OutExportType.ALL_EXPORTS.toString();
        if(outType.equals(OutExportType.ALL_EXPORTS.toString())){

            Request<SearchDto> searchDtoRequest=new Request<>(searchDto);
            //查询数据
            List<StaffInfoStatus> staffInfoStatusList = hrStaffService.exportJsonObjects(searchDtoRequest);
            if(staffInfoStatusList==null || staffInfoStatusList.size()==0) return;
            for (int k = staffInfoStatusList.size()-1; k>=0; k--) {
//                JSONObject staffInfo = staffInfoStatus.getStaffInfo();
                Boolean sendFlag = staffInfoStatusList.get(k).isSendFlag();
                sendFlags.add(sendFlag);
                data2.add(staffInfoStatusList.get(k).getData());
            }
//          List<Document> documents = staffInfoService.queryStaffInfoByStaffAll();
//            for (Document document:documents) {
//                String s = document.toJson();
//                data.add(JSONObject.parseObject(s));
//            }
        }
        String filename="信息导出";
        excelExport.generateDoucment(response,title, data2, filename,sendFlags);
//        File file =new File(s);
//        String filename1=new String(s.getBytes("UTF-8"),"ios-8859-1");
//    String fileName = new String("publish.zip".getBytes("UTF-8"), "iso-8859-1");
//    headers.setContentDispositionFormData("attachment", fileName);
//    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//    return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);

    }
}
