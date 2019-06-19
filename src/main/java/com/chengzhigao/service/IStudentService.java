package com.chengzhigao.service;

import java.util.List;
import java.util.Map;

import com.chengzhigao.entity.Info;
import com.chengzhigao.entity.Result;
import com.chengzhigao.query.StudentQuery;

public interface IStudentService {

	Map selectAllStudent(StudentQuery query);

	Map editStudent(Integer id);

	List<Map> stuArr();

	Result SaveEdit(Info info);

	Result SaveAdd(Info info);

	Result deleteStu(int[] ids);

	List<Info> getAllJobLevels();

	int addBatchJobLevel(List<Info> jObLevels);

}
