package com.chengzhigao.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.chengzhigao.entity.Info;
import com.chengzhigao.entity.Result;
import com.chengzhigao.query.StudentQuery;
import com.chengzhigao.service.IStudentService;
import com.chengzhigao.util.PoiUtils;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private IStudentService service;
	
	SimpleDateFormat sdf=new SimpleDateFormat("/yyyy/MM/dd/");
	
	@RequestMapping("/export")
    @ResponseBody
    //ResponseEntity里面装了所有响应的数据
    public  ResponseEntity<byte[]> exportExcel() throws IOException {
    	return PoiUtils.exportJobLevelExcel(service.getAllJobLevels());
    
    }

    @RequestMapping("/import")
    @ResponseBody
    public Result importData(MultipartFile file, HttpServletRequest req) throws IOException {
    	List<Info> jObLevels=PoiUtils.parseFile2List(file);

        for (Info jObLevel : jObLevels) {
            System.out.println(jObLevel);
        }

        //根据上传的职称级别信息文件，批量添加数据
        if (service.addBatchJobLevel(jObLevels)==1){
            return new Result("200","批量导入成功！");
        }else {
            return new Result("400", "批量导入失败！");
        }
    }
    
    
	@RequestMapping("/selectAll")
	@ResponseBody
	public Map selectAllStudent(@RequestBody StudentQuery query){
		return service.selectAllStudent(query);
	}
	
	@RequestMapping("/editStudent")
	@ResponseBody
	public Map editStudent(Integer id){
		return service.editStudent(id);
	}
	
	@RequestMapping("/stuArr")
	@ResponseBody
	public List<Map> stuArr(){
		return service.stuArr();
	}
	
	@RequestMapping("/SaveEdit")
	@ResponseBody
	public Result SaveEdit(@RequestBody Info info){
		return service.SaveEdit(info);
	}
	
	@RequestMapping("/SaveAdd")
	@ResponseBody
	public Result SaveAdd(@RequestBody Info info){
		return service.SaveAdd(info);
	}

	@RequestMapping("/deleteStu")
	@ResponseBody()
	public Result deleteStu(@RequestBody int[] ids){
		return service.deleteStu(ids);
	}
}
