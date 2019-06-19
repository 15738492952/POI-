package com.chengzhigao.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chengzhigao.entity.Info;
import com.chengzhigao.entity.Result;
import com.chengzhigao.mapper.IStudentMapper;
import com.chengzhigao.query.StudentQuery;
import com.chengzhigao.service.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService{
	
	@Autowired
	private IStudentMapper mapper;
	
	public Map selectAllStudent(StudentQuery query) {
		Integer totalCount = mapper.selectStudentCount(query);
		List<Map> studentData = mapper.selectAllStudent(query);
		
		Map<Object,Object> map = new HashMap<Object, Object>();
		map.put("studentData", studentData);
		map.put("totalCount", totalCount);
		return map;
	}

	public Map editStudent(Integer id) {
		// TODO Auto-generated method stub
		return mapper.editStudent(id);
	}

	public List<Map> stuArr() {
		// TODO Auto-generated method stub
		return mapper.stuArr();
	}

	public Result SaveEdit(Info info) {
		// TODO Auto-generated method stub
		mapper.SaveEdit(info);
		return new Result("200", "修改成功");
	}

	public Result SaveAdd(Info info) {
		// TODO Auto-generated method stub
		mapper.SaveAdd(info);
		return new Result("200", "添加成功");
	}

	public Result deleteStu(int[] ids) {
		// TODO Auto-generated method stub
		mapper.deleteStu(ids);
		return new Result("200", "删除成功");
	}
	
	public List<Info> getAllJobLevels() {
		// TODO Auto-generated method stub
		return mapper.getAllJobLevels();
	}
	
	@Transactional
    public int addBatchJobLevel(List<Info> jObLevels) {
       /* for (Info jObLevel : jObLevels) {
            jObLevel.setTodate(new Date());
        }*/
        return mapper.addBatchJobLevel(jObLevels);
    }
	

}
