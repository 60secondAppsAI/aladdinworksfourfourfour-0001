package com.aladdinworksfourfourfour.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;


import com.aladdinworksfourfourfour.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.aladdinworksfourfourfour.domain.Task;
import com.aladdinworksfourfourfour.dto.TaskDTO;
import com.aladdinworksfourfourfour.dto.TaskSearchDTO;
import com.aladdinworksfourfourfour.dto.TaskPageDTO;
import com.aladdinworksfourfourfour.service.TaskService;
import com.aladdinworksfourfourfour.dto.common.RequestDTO;
import com.aladdinworksfourfourfour.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/task")
@RestController
public class TaskController {

	private final static Logger logger = LoggerFactory.getLogger(TaskController.class);

	@Autowired
	TaskService taskService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Task> getAll() {

		List<Task> tasks = taskService.findAll();
		
		return tasks;	
	}

	@GetMapping(value = "/{taskId}")
	@ResponseBody
	public TaskDTO getTask(@PathVariable Integer taskId) {
		
		return (taskService.getTaskDTOById(taskId));
	}

 	@RequestMapping(value = "/addTask", method = RequestMethod.POST)
	public ResponseEntity<?> addTask(@RequestBody TaskDTO taskDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = taskService.addTask(taskDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/tasks")
	public ResponseEntity<TaskPageDTO> getTasks(TaskSearchDTO taskSearchDTO) {
 
		return taskService.getTasks(taskSearchDTO);
	}	

	@RequestMapping(value = "/updateTask", method = RequestMethod.POST)
	public ResponseEntity<?> updateTask(@RequestBody TaskDTO taskDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = taskService.updateTask(taskDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
