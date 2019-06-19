package com.chengzhigao.util;

import com.chengzhigao.entity.*;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PoiUtils {

    //这是把数据导出到本地保存为Excel文件的方法
    public static  ResponseEntity<byte[]> exportJobLevelExcel(List<Info> list) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();//创建一个Excel文件

        //创建Excel文档属性，必不可少。少了的话，getDocumentSummaryInformation()方法就会返回null
        workbook.createInformationProperties();
        DocumentSummaryInformation info = workbook.getDocumentSummaryInformation();
        info.setCompany("KYO Ltd.");//设置公司信息
        info.setManager("kyo");//设置管理者
        info.setCategory("职称表");//设置文件名

        //设置文件中的日期格式
        HSSFCellStyle datecellStyle = workbook.createCellStyle();
        datecellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));//这个文件的日期格式和平时的不一样

        //创建表单
        HSSFSheet sheet = workbook.createSheet("学生信息表");
        HSSFRow r0 = sheet.createRow(0);//创建第一行
        HSSFCell c0 = r0.createCell(0);// 创建列
        HSSFCell c1 = r0.createCell(1);// 创建列
        HSSFCell c2 = r0.createCell(2);// 创建列
        HSSFCell c3 = r0.createCell(3);// 创建列

        c0.setCellValue("姓名");
        c1.setCellValue("年龄");
        c2.setCellValue("班级");
        c3.setCellValue("创建时间");

        for (int i = 0; i < list.size(); i++) {
            Info jl=(Info) list.get(i);
            HSSFRow row = sheet.createRow(i + 1);
            HSSFCell cell0 = row.createCell(0);
            cell0.setCellValue(jl.getName());
            HSSFCell cell1 = row.createCell(1);
            cell1.setCellValue(jl.getAge());
            HSSFCell cell2 = row.createCell(2);
            cell2.setCellValue(jl.getGid());
            HSSFCell cell3 = row.createCell(3);
            cell3.setCellStyle(datecellStyle);//让日期格式数据正确显示
            cell3.setCellValue(jl.getTodate());
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment",
                new String("学生信息表.xls".getBytes("UTF-8"),"iso-8859-1"));
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        workbook.write(baos);

        ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.CREATED);
        return entity;
    }
        
        //这是解析上传的Excel文件为对象集合，从而批量添加数据的方法
        public static List<Info> parseFile2List(MultipartFile file) throws IOException {
            List<Info> jObLevels=new ArrayList<Info>();
            HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());
            HSSFSheet sheet = workbook.getSheetAt(0);
            int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();//获取表单所有的行
            for (int i = 1; i < physicalNumberOfRows; i++) {
                HSSFRow row = sheet.getRow(i);
                Info j1=new Info();

                HSSFCell c0 = row.getCell(0);
                j1.setName(c0.getStringCellValue());

                HSSFCell c1 = row.getCell(1);
                j1.setAge((int) c1.getNumericCellValue());
                
                HSSFCell c2 = row.getCell(2);
                j1.setGid((int) c2.getNumericCellValue());

                HSSFCell c3 = row.getCell(3);
                j1.setTodate(c3.getDateCellValue());

                jObLevels.add(j1);
            }

            return jObLevels;
    }
}
