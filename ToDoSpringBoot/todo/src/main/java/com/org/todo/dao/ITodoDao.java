package com.org.todo.dao;

import java.util.List;

public interface ITodoDao {
	
	<T> T save(T t);
	<T> List<T> getTaskList(Class<T> clazz);
	<T> T deleteTask(Class<T> clazz, long id);
	<T> int updateTask(Class<T> clazz, long id);
 
}
