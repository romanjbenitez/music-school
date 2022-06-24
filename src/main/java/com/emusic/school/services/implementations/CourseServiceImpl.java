package com.emusic.school.services.implementations;

import com.emusic.school.repositories.CourseRepository;
import com.emusic.school.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseRepository courseRepository;
}
