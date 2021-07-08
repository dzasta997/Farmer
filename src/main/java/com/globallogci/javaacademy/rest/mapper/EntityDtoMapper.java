package com.globallogci.javaacademy.rest.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class EntityDtoMapper<ENTITY, DTO> {

    public abstract DTO convertToDto(ENTITY entity);

    public abstract ENTITY convertToEntity(DTO dto);

    public List<DTO> convertToDtos(Collection<ENTITY> entities) {
        return entities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<ENTITY> convertToEntities(Collection<DTO> dtos) {
        return dtos.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
    }

}
