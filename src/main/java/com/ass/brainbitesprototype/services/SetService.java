package com.ass.brainbitesprototype.services;

import com.ass.brainbitesprototype.dtos.SetDto;
import com.ass.brainbitesprototype.models.Set;

import java.util.List;

public interface SetService {
    List<SetDto> findAllSets();
    Set saveSet(SetDto setDto);

    SetDto findSetById(Long setId);

    void updateSet(SetDto set);
}
