package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.Enroll;

@Mapper
public interface EnrollDao {
	
	List<Enroll> selectBySid(int sid);
	
	List<Enroll> selectByCid(int cid);
	
    int deleteByPrimaryKey(Integer id);

    int insert(Enroll record);

    int insertSelective(Enroll record);

    Enroll selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Enroll record);

    int updateByPrimaryKey(Enroll record);
}