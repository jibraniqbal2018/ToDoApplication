package com.org.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.org.todo.model.Task;
import com.org.todo.service.ITodoService;


@CrossOrigin(origins  = "*")
@RestController
@RequestMapping("/todo")
public class TodoController {
	
	@Autowired
	private ITodoService service;
	
	@RequestMapping(value = "/saveTask", method= RequestMethod.POST)
	public Task saveTask(@RequestBody Task task) {
		Task task1 = service.save(task);
		System.out.println("TAsk Id  "+task1.getId());
		return task1;
	}
	
	@RequestMapping(value = "/taskList", method= RequestMethod.GET)
	public List<Task> getTaskList(){
		return service.getTaskList(Task.class);
		
	}
	
	@RequestMapping(value = "/deleteTask/{id}", method  = RequestMethod.DELETE)
	public Task deleteTask(@PathVariable long id) {
		return service.deleteTask(Task.class, id);
		
	}
	
	@RequestMapping(value = "/updateTask/{id}" ,  method  = RequestMethod.PUT)
	public int updateTask(@PathVariable long id) {
		return  service.updateTask(Task.class, id);
	}
	
	

}
