package com.example.miyaeboard.data.common;

public interface EntityMapper <DTO, Entity>{
    Entity toEntity(DTO dto);
}
