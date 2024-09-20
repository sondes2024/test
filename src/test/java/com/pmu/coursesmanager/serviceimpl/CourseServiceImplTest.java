package com.pmu.coursesmanager.serviceimpl;

import com.pmu.coursesmanager.domain.Course;
import com.pmu.coursesmanager.domain.Partant;
import com.pmu.coursesmanager.dto.CourseDto;
import com.pmu.coursesmanager.dto.PartantDto;
import com.pmu.coursesmanager.repository.CourseJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CourseServiceImplTest {

    @InjectMocks
    private CourseServiceImpl courseService;

    @Mock
    private CourseJpaRepository courseJpaRepository;
    @Mock
    private KafkaProducerService kafkaProducerService;

    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        modelMapper = new ModelMapper();
        courseService = new CourseServiceImpl(courseJpaRepository, kafkaProducerService);
    }

    @Test
    void testCreateCourse() {
        // Given
        CourseDto courseDto = new CourseDto();
        courseDto.setNumero(1);
        List<PartantDto> partantDtos=new ArrayList<>();
        PartantDto partantDto1=new PartantDto();
        partantDto1.setNumero(1);
        partantDto1.setNom("1");
        PartantDto partantDto2=new PartantDto();
        partantDto2.setNumero(2);
        partantDto2.setNom("2");
        PartantDto partantDto3=new PartantDto();
        partantDto3.setNumero(3);
        partantDto3.setNom("3");
        partantDtos.add(partantDto1);
        partantDtos.add(partantDto2);
        partantDtos.add(partantDto3);
        courseDto.setPartants(partantDtos);


        Course savedCourse = new Course();
        savedCourse.setId(1L);
        savedCourse.setNumero(courseDto.getNumero());
        List<Partant> partants=new ArrayList<>();
        Partant partant1=new Partant();
        partant1.setNumero(1);
        partant1.setNom("1");
        Partant partant2=new Partant();
        partant2.setNumero(2);
        partant2.setNom("2");
        Partant partant3=new Partant();
        partant3.setNumero(3);
        partant3.setNom("3");
        partants.add(partant1);
        partants.add(partant2);
        partants.add(partant3);
        savedCourse.setPartants(partants);

        when(courseJpaRepository.save(any(Course.class))).thenReturn(savedCourse);
        when(courseJpaRepository.findById(any(Long.class))).thenReturn(Optional.of(savedCourse));

        // When
        CourseDto result = courseService.createCourse(courseDto);

        // Then
        assertEquals(courseDto.getNumero(), result.getNumero());
        verify(kafkaProducerService).sendMessage(result);
    }
}
