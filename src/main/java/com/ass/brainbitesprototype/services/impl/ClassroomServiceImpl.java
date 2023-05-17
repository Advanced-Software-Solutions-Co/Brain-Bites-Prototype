package com.ass.brainbitesprototype.services.impl;

import com.ass.brainbitesprototype.dtos.ClassroomDto;
import com.ass.brainbitesprototype.models.Classroom;
import com.ass.brainbitesprototype.repositories.ClassroomRepository;
import com.ass.brainbitesprototype.services.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassroomServiceImpl implements ClassroomService {
    private ClassroomRepository classroomRepository;
    @Autowired
    public ClassroomServiceImpl(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    @Override
    public List<ClassroomDto> findAllClassrooms() {
        List<Classroom> classrooms = classroomRepository.findAll();
        return classrooms.stream().map((classroom) -> mapToClassroomDto(classroom)).collect(Collectors.toList());
    }

    @Override
    public ClassroomDto findClassroomById(long classroomId) {
        Classroom classroom = classroomRepository.findById(classroomId).get();
        return mapToClassroomDto(classroom);
    }

    @Override
    public Classroom saveClassroom(ClassroomDto classroomDto) {
        Classroom classroom = mapToClassroom(classroomDto);
        return classroomRepository.save(classroom);
    }

    private Classroom mapToClassroom(ClassroomDto classroom) {
        Classroom classroomDto = Classroom.builder()
        .id(classroom.getId())
        .title(classroom.getTitle())
        .photoUrl(classroom.getPhotoUrl())
        .content(classroom.getContent())
        .build();
        return classroomDto;
    }

    //Mapper
    private ClassroomDto mapToClassroomDto(Classroom classroom) {
        ClassroomDto classroomDto = ClassroomDto.builder()
                .id(classroom.getId())
                .title(classroom.getTitle())
                .photoUrl(classroom.getPhotoUrl())
                .content(classroom.getContent())
                .build();
        return classroomDto;
    }
}
