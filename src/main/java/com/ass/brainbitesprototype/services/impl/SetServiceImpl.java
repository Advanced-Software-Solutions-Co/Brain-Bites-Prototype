package com.ass.brainbitesprototype.services.impl;

import com.ass.brainbitesprototype.dtos.SetDto;
import com.ass.brainbitesprototype.models.Set;
import com.ass.brainbitesprototype.repositories.SetRepository;
import com.ass.brainbitesprototype.services.SetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class SetServiceImpl implements SetService {

    //May have to add AutoWired but gives an error, supposdly new spring-boot does automatically - chris
    public SetServiceImpl(SetRepository setRepository) {
        this.setRepository = setRepository;
    }

    private SetRepository setRepository;

    @Override
    public List<SetDto> findAllSets() {
        List<Set> sets = setRepository.findAll();
        //Might have to change the "(Set)" to lowercase "(set)" if there is an error- chris
        return sets.stream().map((Set) -> mapToSetDto(Set)).collect(Collectors.toList());
    }

    private SetDto mapToSetDto(Set set) {
        SetDto setDto = SetDto.builder()
                .id(set.getId())
                .title(set.getTitle())
                .photoUrl(set.getPhotoUrl())
                .content(set.getContent())
                .createdOn(set.getCreatedOn())
                .updatedOn(set.getUpdatedOn())
                .build();
        return setDto;
    }
}
