package com.org.todo.service;

import java.util.List;

public interface ITodoService {
	<T> T save(T t );
	<T> List<T> getTaskList(Class<T> clazz);
	<T> T deleteTask(Class<T> clazz, long id);
	<T> int updateTask(Class<T> clazz, long id);

}
