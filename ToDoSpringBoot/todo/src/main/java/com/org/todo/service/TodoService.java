package com.org.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.todo.dao.ITodoDao;

@Service
public class TodoService implements ITodoService{
	
	@Autowired
	private ITodoDao todoDao;
	
	@Override
	public <T> T save(T t) {
		return todoDao.save(t);
		
		
		
	}

	@Override
	public <T> List<T> getTaskList(Class<T> clazz) {
		return todoDao.getTaskList(clazz);
	}

	@Override
	public <T> T deleteTask(Class<T> clazz, long id) {
		return todoDao.deleteTask(clazz, id);
	}

	@Override
	public <T> int updateTask(Class<T> clazz, long id) {
		return todoDao.updateTask(clazz, id);
	}
	
	

}
