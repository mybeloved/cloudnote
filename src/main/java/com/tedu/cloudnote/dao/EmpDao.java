package com.tedu.cloudnote.dao;

import com.tedu.cloudnote.annotation.MyBatisRepository;
import com.tedu.cloudnote.entity.Emp;

@MyBatisRepository
public interface EmpDao {
	public void save(Emp emp);
}
