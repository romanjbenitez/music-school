package com.emusic.school.controllers;

import com.emusic.school.dtos.TeacherDTO;
import com.emusic.school.models.Teacher;
import com.emusic.school.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TeacherController{
    @Autowired
    TeacherService teacherService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/teachers")
    public List<TeacherDTO> getTeachers(){
        return teacherService.getTeachersDTO();
    }
    @GetMapping("/teacher/{id}")
    public TeacherDTO getTeacher(@PathVariable Long id){
        return teacherService.getTeacherDTOById(id);
    }
    @PostMapping("teachers")
    public ResponseEntity<?> createAccountTeacher(@RequestParam String firstName,
                                                  @RequestParam String lastName, @RequestParam String email,
                                                  @RequestParam String password){
        if(firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()){
            return new ResponseEntity<>("MISSING DATA", HttpStatus.FORBIDDEN);
        }
        if(teacherService.getTeacherByEmail(email) != null){
            return new ResponseEntity<>("EMAIL ALREADY REGISTERED", HttpStatus.FORBIDDEN);
        }
        Teacher teacher = new Teacher(firstName, lastName, email, passwordEncoder.encode(password));
        teacherService.saveTeacher(teacher);
        return new ResponseEntity<>("REGISTERED TEACHER", HttpStatus.CREATED);
    }
}
