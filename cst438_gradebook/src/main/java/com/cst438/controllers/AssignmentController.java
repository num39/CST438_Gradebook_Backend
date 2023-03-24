package com.cst438.controllers;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cst438.domain.Assignment;
import com.cst438.domain.AssignmentListDTO;
import com.cst438.domain.AssignmentListDTO.AssignmentDTO;
import com.cst438.domain.AssignmentGrade;
import com.cst438.domain.AssignmentGradeRepository;
import com.cst438.domain.AssignmentRepository;
import com.cst438.domain.Course;
import com.cst438.domain.CourseDTOG;
import com.cst438.domain.CourseRepository;
import com.cst438.domain.Enrollment;
import com.cst438.domain.GradebookDTO;
import com.cst438.services.RegistrationService;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@RestController
@CrossOrigin(origins = {"http://localhost:3000","http://localhost:3001"})
public class AssignmentController {
	
	@Autowired
	AssignmentRepository assignmentRepository;
	
	@Autowired
	CourseRepository courseRepository;
	
	
	@PostMapping("/assignment")
	@Transactional
	public AssignmentDTO newAssignment(@RequestBody AssignmentDTO newAssignment){
		
		String email = "dwisneski@csumb.edu"; 
		Course c = courseRepository.findById(newAssignment.courseId).orElse(null); 
		System.out.println(newAssignment.name);
		if( c != null && c.getInstructor().equals(email))
		{
	    Assignment assign = new Assignment();
	    System.out.println(newAssignment.toString());
	    assign.setCourse(c);
	    assign.setName(newAssignment.name);
	    assign.setDueDate(Date.valueOf(newAssignment.dueDate));
	    assign.setNeedsGrading(1);
	    assign = assignmentRepository.save(assign);
	    newAssignment.assignmentId= assign.getId();
	    return newAssignment; 
		}
		else {
			// invalid course
			throw new ResponseStatusException( 
                           HttpStatus.BAD_REQUEST, 
                          "Invalid course id.");
		}
	}
}
