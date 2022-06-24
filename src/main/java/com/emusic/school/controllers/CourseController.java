package com.emusic.school.controllers;

import com.emusic.school.dtos.CourseDTO;
import com.emusic.school.dtos.NewCourseDTO;
import com.emusic.school.models.Course;
import com.emusic.school.models.Teacher;
import com.emusic.school.services.CourseService;
import com.emusic.school.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CourseController {
    @Autowired
    TeacherService teacherService;
    @Autowired
    CourseService courseService;
    @GetMapping("/courses")
    public List<CourseDTO> getCourses(){
        return courseService.getCoursesDTO();
    }
    @PostMapping("/courses")
    public ResponseEntity<?> createCourse(@RequestBody NewCourseDTO newCourseDTO){
        if(newCourseDTO.getDuration() > 10 || newCourseDTO.getLessons()<0 || newCourseDTO.getLevel().isEmpty() ||
                newCourseDTO.getName().isEmpty() || newCourseDTO.getIdTeacher() == null){
            return new ResponseEntity<>("MISSING DATA", HttpStatus.FORBIDDEN);
        }
        Teacher teacher = teacherService.getTeacherById(newCourseDTO.getIdTeacher());
        if(teacher == null){
            return new ResponseEntity<>("TEACHER DON'T EXISTS", HttpStatus.FORBIDDEN);
        }
        Course course = new Course(newCourseDTO.getLevel(), newCourseDTO.getName(), newCourseDTO.getLessons(),
                newCourseDTO.getPrice().doubleValue(), newCourseDTO.getDuration(), true, teacher);
        courseService.saveCourse(course);
        return new ResponseEntity<>("CREATED", HttpStatus.CREATED);
    }

    @PatchMapping("/courses")
    public ResponseEntity<?> deleteCourse(@RequestParam Long idCourse){
        if(idCourse == null){
            return new ResponseEntity<>("MISSIN DATA", HttpStatus.FORBIDDEN);
        }
        Course course = courseService.getCourseById(idCourse);
        if(course == null){
            return new ResponseEntity<>("THIS COURSE DOESN'T EXISTS", HttpStatus.FORBIDDEN);
        }
        courseService.deleteCourse(course);
        return new ResponseEntity<>("COURSE DELETED", HttpStatus.OK);
    }



}
