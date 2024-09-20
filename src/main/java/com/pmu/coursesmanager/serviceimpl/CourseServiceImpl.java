package com.pmu.coursesmanager.serviceimpl;

import com.pmu.coursesmanager.domain.Course;
import com.pmu.coursesmanager.domain.Partant;
import com.pmu.coursesmanager.dto.CourseDto;
import com.pmu.coursesmanager.dto.PartantDto;
import com.pmu.coursesmanager.exception.CourseException;
import com.pmu.coursesmanager.repository.CourseJpaRepository;
import com.pmu.coursesmanager.service.ICourseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements ICourseService {
    private final CourseJpaRepository courseJpaRepository;
    private final KafkaProducerService kafkaProducerService;
    private final ModelMapper modelMapper = new ModelMapper();


    /**
     * This service allows you to create a course and their
     * To store the information in a database and to send it to Kafka
     *
     * @param courseDto un DTO contients a course with their horses
     * @return courseDto
     */
    @Transactional
    @Override
    public CourseDto createCourse(CourseDto courseDto) {
        Course courseEntity = this.modelMapper.map(courseDto, Course.class);
            courseEntity.setPartants(courseDto.getPartants().stream()
                    .map(partantDto -> this.modelMapper.map(partantDto, Partant.class))
                    .toList());

        Course coursePersistant = this.courseJpaRepository.save(courseEntity);
        Optional<Course> optionalCourse = this.courseJpaRepository.findById(coursePersistant.getId());
        if (optionalCourse.isPresent()) {
            CourseDto courseDto1 = this.modelMapper.map(optionalCourse.get(), CourseDto.class);
            courseDto1.setPartants(optionalCourse.get().getPartants().stream().map(partantDto -> this.modelMapper.map(partantDto, PartantDto.class)
            ).toList());
            kafkaProducerService.sendMessage(courseDto1);
            return courseDto1;
        } else {
            throw new CourseException("la course n'est pas trouvé dans la base de données ,vérifier que la course et leurs partants dsont bien crées dans la base de données");
        }


    }
}
