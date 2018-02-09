package org.dselent.scheduling.server.service;

import java.sql.SQLException;
import java.util.List;

import org.dselent.scheduling.server.dto.CreateSectionDto;
import org.dselent.scheduling.server.model.SectionInfo;
import org.springframework.stereotype.Service;

@Service
public interface SectionService{

    public List<Integer> createSection(CreateSectionDto createSectionDto) throws SQLException;
    public SectionInfo getOneSection() throws SQLException;
    public List<SectionService> getAllSections() throws SQLException;
}