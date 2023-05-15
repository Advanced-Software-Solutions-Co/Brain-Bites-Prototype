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

    private SetRepository setRepository;

    //May have to add AutoWired but gives an error, supposdly new spring-boot does automatically - chris
    @Autowired
    public SetServiceImpl(SetRepository setRepository) {
        this.setRepository = setRepository;
    }

    @Override
    public List<SetDto> findAllSets() {
        List<Set> sets = setRepository.findAll();
        //Might have to change the "(Set)" to lowercase "(set)" if there is an error- chris
        return sets.stream().map((set) -> mapToSetDto(set)).collect(Collectors.toList());
    }

    @Override
    public Set saveSet(SetDto setDto) {
        Set set = mapToSet(setDto);
        return setRepository.save(set);
    }

    @Override
    public SetDto findSetById(Long setId) {
        Set set = setRepository.findById(setId).get();
        return mapToSetDto(set);
    }

    @Override
    public void updateSet(SetDto setDto) {
        Set set = mapToSet(setDto);
        setRepository.save(set);
    }

    @Override
    public void delete(Long setId) {
        setRepository.deleteById(setId);
    }

    /*
    @Override
    public List<SetDto> searchSets(String query) {
        List<Set> sets = setRepository.searchSets(query);
        return sets.stream().map(set -> mapToSetDto(set)).collect(Collectors.toList());

    }
     */

    private Set mapToSet(SetDto set) {
        Set setDto = Set.builder()
                .id(set.getId())
                .title(set.getTitle())
                .photoUrl(set.getPhotoUrl())
                .content(set.getContent())
                .createdOn(set.getCreatedOn())
                .updatedOn(set.getUpdatedOn())
                .build();
        return setDto;
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
