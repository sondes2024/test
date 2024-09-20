package com.pmu.coursesmanager.endpoint;


import com.pmu.coursesmanager.dto.CourseDto;
import com.pmu.coursesmanager.service.ICourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses-manager-api/v1/course")
@RequiredArgsConstructor
public class CourseController {
    private final ICourseService courseService;

    @PostMapping("/create")
    public ResponseEntity<CourseDto> saveCourse(@RequestBody @Valid CourseDto courseDto) {

        CourseDto courseDtoCreated = this.courseService.createCourse(courseDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(courseDtoCreated);
    }
}
