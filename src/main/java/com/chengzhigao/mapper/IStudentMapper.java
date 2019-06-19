package com.chengzhigao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.chengzhigao.entity.Info;
import com.chengzhigao.query.StudentQuery;
public interface IStudentMapper {

	List<Map> selectAllStudent(StudentQuery query);

	Integer selectStudentCount(StudentQuery query);

	Map editStudent(Integer id);

	List<Map> stuArr();

	void SaveEdit(Info info);

	void SaveAdd(Info info);

	void deleteStu(int[] ids);

	List<Info> getAllJobLevels();

	int addBatchJobLevel(@Param("jls") List<Info> jObLevels);

}
