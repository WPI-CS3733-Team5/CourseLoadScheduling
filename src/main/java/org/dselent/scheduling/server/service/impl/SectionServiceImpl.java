package org.dselent.scheduling.server.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.dao.CalendarInfoDao;
import org.dselent.scheduling.server.dao.SectionInfoDao;
import org.dselent.scheduling.server.dto.CreateSectionDto;
import org.dselent.scheduling.server.dto.GetOneSectionDto;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.CalendarInfo;
import org.dselent.scheduling.server.model.SectionInfo;
import org.dselent.scheduling.server.service.SectionService;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class SectionServiceImpl implements SectionService {

    @Autowired
    private SectionInfoDao sectionInfoDao;
    @Autowired
    private CalendarInfoDao calendarInfoDao;

    public SectionServiceImpl() {
        //
    }

    @Transactional
    @Override
    public List<Integer> createSection(CreateSectionDto dto) throws SQLException{
    	List<Integer> rowsAffectedList = new ArrayList<>();
    	
    	//Fill Calendar Table
    	CalendarInfo calendarInfo = new CalendarInfo();
    	calendarInfo.setDays(dto.getDays());
    	calendarInfo.setSemester(dto.getSemester());
    	calendarInfo.setTerm(dto.getTerm());
    	calendarInfo.setStartTime(dto.getStartTime());
    	calendarInfo.setEndTime(dto.getEndTime());
    	calendarInfo.setYear(dto.getYear());
    	
    	List<String> calendarInsertColumnNameList = new ArrayList<>();
    	List<String> calendarKeyHolderColumnNameList = new ArrayList<>();
        calendarInsertColumnNameList.add(CalendarInfo.getColumnName(CalendarInfo.Columns.YEAR));
        calendarInsertColumnNameList.add(CalendarInfo.getColumnName(CalendarInfo.Columns.SEMESTER));
        calendarInsertColumnNameList.add(CalendarInfo.getColumnName(CalendarInfo.Columns.DAYS));
        calendarInsertColumnNameList.add(CalendarInfo.getColumnName(CalendarInfo.Columns.START_TIME));
        calendarInsertColumnNameList.add(CalendarInfo.getColumnName(CalendarInfo.Columns.END_TIME));
        calendarInsertColumnNameList.add(CalendarInfo.getColumnName(CalendarInfo.Columns.TERM));
        
        calendarKeyHolderColumnNameList.add(CalendarInfo.getColumnName(CalendarInfo.Columns.ID));

        rowsAffectedList.add(calendarInfoDao.insert(calendarInfo, calendarInsertColumnNameList, calendarKeyHolderColumnNameList));
    	
        //Fill Section Table
    	SectionInfo sectionInfo = new SectionInfo();
    	sectionInfo.setCourseInfoId(dto.getCourseInfoId());
    	sectionInfo.setCalendarInfoId(calendarInfo.getId());
    	sectionInfo.setDeleted(dto.getDeleted());
    	sectionInfo.setInstructorInfoId(dto.getInstructorInfoId());
    	sectionInfo.setLocation(dto.getLocation());
    	sectionInfo.setSectionNumber(dto.getSectionNumber());
    	sectionInfo.setSectionType(dto.getSectionType());
    	
    	List<String> sectionInsertColumnNameList = new ArrayList<>();
    	List<String> sectionKeyHolderColumnNameList = new ArrayList<>();
    	
    	sectionInsertColumnNameList.add(SectionInfo.getColumnName(SectionInfo.Columns.COURSE_INFO_ID));
    	sectionInsertColumnNameList.add(SectionInfo.getColumnName(SectionInfo.Columns.CALENDAR_INFO_ID));
    	sectionInsertColumnNameList.add(SectionInfo.getColumnName(SectionInfo.Columns.DELETED));
    	sectionInsertColumnNameList.add(SectionInfo.getColumnName(SectionInfo.Columns.INSTRUCTOR_INFO_ID));
    	sectionInsertColumnNameList.add(SectionInfo.getColumnName(SectionInfo.Columns.LOCATION));
    	sectionInsertColumnNameList.add(SectionInfo.getColumnName(SectionInfo.Columns.SECTION_NUMBER));
    	sectionInsertColumnNameList.add(SectionInfo.getColumnName(SectionInfo.Columns.SECTION_TYPE));
    	
    	sectionKeyHolderColumnNameList.add(SectionInfo.getColumnName(SectionInfo.Columns.ID));
    	rowsAffectedList.add(sectionInfoDao.insert(sectionInfo, sectionInsertColumnNameList, sectionKeyHolderColumnNameList));
    	
        return rowsAffectedList;
    }

    @Override
    public SectionInfo getOneSection(GetOneSectionDto dto) throws SQLException{
    	SectionInfo SectionInfo = sectionInfoDao.findById(dto.getId());

		return  SectionInfo;

    }

    @Override
    public List<SectionInfo> getAllSections() throws SQLException {
    	List<String> selectColumnNameList = new ArrayList<>();
		List<QueryTerm> queryTermList = new ArrayList<>();
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		selectColumnNameList.addAll(SectionInfo.getColumnNameList());
		Pair<String, ColumnOrder> byId = new Pair<>(SectionInfo.getColumnName(SectionInfo.Columns.ID), ColumnOrder.ASC);

		return sectionInfoDao.select(selectColumnNameList, queryTermList, orderByList);
    }
}