package org.dselent.scheduling.server.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.dao.CourseInfoDao;
import org.dselent.scheduling.server.dao.SectionInfoDao;
import org.dselent.scheduling.server.dao.SectionPopulationDao;
import org.dselent.scheduling.server.dao.LabInfoDao;
import org.dselent.scheduling.server.dao.CalendarInfoDao;
import org.dselent.scheduling.server.dto.CreateSectionDto;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.CourseInfo;
import org.dselent.scheduling.server.model.SectionInfo;
import org.dselent.scheduling.server.model.SectionPopulation;
import org.dselent.scheduling.server.model.LabInfo;
import org.dselent.scheduling.server.model.CalendarInfo;

import org.dselent.scheduling.server.service.SectionService;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class SectionServiceImpl implements SectionService {

    @Autowired
    private SectionInfoDao sectionInfoDao;

    @Autowired
    private CourseInfoDao courseInfoDao;
    private SectionPopulationDao sectionPopulationDao;
    private LabInfoDao labInfoDao;
    private CalendarInfoDao calendarInfoDao;


    public SectionServiceImpl() {
        //
    }

    @Transactional
    @Override
    public List<Integer> createSection(CreateSectionDto createSectionDto) throws SQLException{
    	List<Integer> rowsAffectedList = new ArrayList<>();
    	
    	SectionInfo sectionInfo = new SectionInfo();
    	//sectionInfo.setCourseInfoId(createSectionDto.);
    	
        return null;
    }

    @Override
    public SectionInfo getOneSection() throws SQLException {
        return null;
    }

    @Override
    public List<SectionService> getAllSections() throws SQLException {
        return null;
    }
}