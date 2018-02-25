package org.dselent.scheduling.server.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.dao.CalendarInfoDao;
import org.dselent.scheduling.server.dao.CourseInfoDao;
import org.dselent.scheduling.server.dao.LabInfoDao;
import org.dselent.scheduling.server.dao.SectionInfoDao;
import org.dselent.scheduling.server.dao.SectionPopulationDao;
import org.dselent.scheduling.server.dto.CreateCourseDto;
import org.dselent.scheduling.server.dto.CreateSectionDto;
import org.dselent.scheduling.server.dto.GetAllCoursesDto;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.CalendarInfo;
import org.dselent.scheduling.server.model.CourseInfo;
import org.dselent.scheduling.server.model.LabInfo;
import org.dselent.scheduling.server.model.SectionInfo;
import org.dselent.scheduling.server.model.SectionPopulation;
import org.dselent.scheduling.server.model.UserInfo;
import org.dselent.scheduling.server.service.CourseService;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * Created by dhmchorney on 2/9/2018.
 */

@Service
public class CourseServiceImpl implements CourseService{
    @Autowired
    private CourseInfoDao courseInfoDao;
    @Autowired
    private SectionInfoDao sectionInfoDao;
    @Autowired
    private SectionPopulationDao sectionPopulationDao;
    @Autowired
    private LabInfoDao labInfoDao;
    @Autowired
    private CalendarInfoDao calendarInfoDao;

    public CourseServiceImpl(){}

    @Transactional
    @Override
    public List<Integer> createCourse(CreateCourseDto createCourseDto, CreateSectionDto createSectionDto) throws SQLException{
        List<Integer> rowsAffectedList = new ArrayList<>();

        //Adding to the CourseInfo Table
        CourseInfo courseInfo = new CourseInfo();
        courseInfo.setCourseName(createCourseDto.getCourseName());
        courseInfo.setRequiredFrequencyPerTerm(createCourseDto.getRequiredFrequencyPerTerm());
        courseInfo.setRequiredFrequencyPerSemester(createCourseDto.getRequiredFrequencyPerSemester());
        courseInfo.setRequiredFrequencyPerYear(createCourseDto.getRequiredFrequencyPerYear());
        courseInfo.setCreditAmount(createCourseDto.getCreditAmount());
        courseInfo.setDeleted(createCourseDto.getDeleted());
        courseInfo.setDepartment(createCourseDto.getDepartment());
        courseInfo.setCourseNumber(createCourseDto.getCourseNumber());

        List<String> courseInsertColumnNameList = new ArrayList<>();
        List<String> courseKeyHolderColumnNameList = new ArrayList<>();

        courseInsertColumnNameList.add(CourseInfo.getColumnName(CourseInfo.Columns.COURSE_NAME));
        courseInsertColumnNameList.add(CourseInfo.getColumnName(CourseInfo.Columns.REQUIRED_FREQUENCY_PER_TERM));
        courseInsertColumnNameList.add(CourseInfo.getColumnName(CourseInfo.Columns.REQUIRED_FREQUENCY_PER_SEMESTER));
        courseInsertColumnNameList.add(CourseInfo.getColumnName(CourseInfo.Columns.REQUIRED_FREQUENCY_PER_YEAR));
        courseInsertColumnNameList.add(CourseInfo.getColumnName(CourseInfo.Columns.CREDIT_AMOUNT));
        courseInsertColumnNameList.add(CourseInfo.getColumnName(CourseInfo.Columns.DELETED));
        courseInsertColumnNameList.add(CourseInfo.getColumnName(CourseInfo.Columns.DEPARTMENT));
        courseInsertColumnNameList.add(CourseInfo.getColumnName(CourseInfo.Columns.COURSE_NUMBER));

        courseKeyHolderColumnNameList.add(CourseInfo.getColumnName(CourseInfo.Columns.ID));
        rowsAffectedList.add(courseInfoDao.insert(courseInfo, courseInsertColumnNameList, courseKeyHolderColumnNameList));

        //Add to CalendarInfo Table
        CalendarInfo calendarInfo = new CalendarInfo();
        calendarInfo.setYear(createSectionDto.getYear());
        calendarInfo.setSemester(createSectionDto.getSemester());
        calendarInfo.setDays(createSectionDto.getDays());
        calendarInfo.setStartTime(createSectionDto.getStartTime());
        calendarInfo.setEndTime(createSectionDto.getEndTime());
        calendarInfo.setTerm(createSectionDto.getTerm());
        
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

        //Add To SectionInfoTable
        SectionInfo sectionInfo = new SectionInfo();
        sectionInfo.setSectionNumber(createSectionDto.getSectionNumber());
        sectionInfo.setSectionType(createSectionDto.getSectionType());
        sectionInfo.setLocation(createSectionDto.getLocation());
        sectionInfo.setDeleted(createSectionDto.getDeleted());
        sectionInfo.setCourseInfoId(courseInfo.getId());
        sectionInfo.setCalendarInfoId(calendarInfo.getId());
        
        List<String> sectionInsertColumnNameList = new ArrayList<>();
        List<String> sectionKeyHolderColumnNameList = new ArrayList<>();
        sectionInsertColumnNameList.add(SectionInfo.getColumnName(SectionInfo.Columns.SECTION_NUMBER));
        sectionInsertColumnNameList.add(SectionInfo.getColumnName(SectionInfo.Columns.SECTION_TYPE));
        sectionInsertColumnNameList.add(SectionInfo.getColumnName(SectionInfo.Columns.LOCATION));
        sectionInsertColumnNameList.add(SectionInfo.getColumnName(SectionInfo.Columns.DELETED));
        sectionInsertColumnNameList.add(SectionInfo.getColumnName(SectionInfo.Columns.COURSE_INFO_ID));
        sectionInsertColumnNameList.add(SectionInfo.getColumnName(SectionInfo.Columns.CALENDAR_INFO_ID));
        
        sectionKeyHolderColumnNameList.add(SectionInfo.getColumnName(SectionInfo.Columns.ID));
        rowsAffectedList.add(sectionInfoDao.insert(sectionInfo, sectionInsertColumnNameList, sectionKeyHolderColumnNameList));
        
        return rowsAffectedList;
    }

    @Transactional
    @Override
    public List<Object> getAllCourses() throws SQLException{
        List<String> selectColumnNameList = new ArrayList<>();
        List<QueryTerm> queryTermList = new ArrayList<>();
        List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
        selectColumnNameList.addAll(CourseInfo.getColumnNameList());
        //Pair<String, ColumnOrder> byId = new Pair<>(CourseInfo.getColumnName(CourseInfo.Columns.ID), ColumnOrder.ASC);
        //orderByList.add(byId);
        
        List<CourseInfo> allCourses = courseInfoDao.select(selectColumnNameList, queryTermList, orderByList);
        
        selectColumnNameList = new ArrayList<>();
        selectColumnNameList.addAll(SectionInfo.getColumnNameList());
        List<SectionInfo> allSections = sectionInfoDao.select(selectColumnNameList, queryTermList, orderByList);
       
        selectColumnNameList = new ArrayList<>();
        selectColumnNameList.addAll(SectionPopulation.getColumnNameList());
        List<SectionPopulation> allSecPops = sectionPopulationDao.select(selectColumnNameList, queryTermList, orderByList);
        
        selectColumnNameList = new ArrayList<>();
        selectColumnNameList.addAll(LabInfo.getColumnNameList());
        List<LabInfo> allLabs = labInfoDao.select(selectColumnNameList, queryTermList, orderByList);
        
        selectColumnNameList = new ArrayList<>();
        selectColumnNameList.addAll(CalendarInfo.getColumnNameList());
        List<CalendarInfo> allCalendars = calendarInfoDao.select(selectColumnNameList, queryTermList, orderByList);
        
        List<Object> returnList = new ArrayList<Object>();
        returnList.add(allCourses);
        returnList.add(allSections);
        returnList.add(allSecPops);
        returnList.add(allLabs);
        returnList.add(allCalendars);
        
        return returnList;
    }

	@Override
	public List<Integer> editCourse(CreateCourseDto createCourseDto, CreateSectionDto createSectionDto, Integer sectionInfoId) throws SQLException {
		List<Integer> rowsAffectedList = new ArrayList<>();
		
		SectionInfo theSection = sectionInfoDao.findById(sectionInfoId);
		
		List<QueryTerm> queryTermList = new ArrayList<QueryTerm>();
		QueryTerm updateCourse = new QueryTerm(CourseInfo.getColumnName(CourseInfo.Columns.ID), ComparisonOperator.EQUAL, createSectionDto.getCourseInfoId(), null);
		queryTermList.add(updateCourse);
		
		//Edit Course Info
		String courseName = createCourseDto.getCourseName();
		Integer requiredFrequencyPerTerm = createCourseDto.getRequiredFrequencyPerTerm();
		Integer requiredFrequencyPerSemester = createCourseDto.getRequiredFrequencyPerSemester();
		Integer requiredFrequencyPerYear = createCourseDto.getRequiredFrequencyPerYear();
		Integer creditAmount = createCourseDto.getCreditAmount();
		Boolean deleted = createCourseDto.getDeleted();
		String department = createCourseDto.getDepartment();
		Integer courseNumber = createCourseDto.getCourseNumber();
		
		courseInfoDao.update(CourseInfo.getColumnName(CourseInfo.Columns.COURSE_NAME), courseName, queryTermList);
		courseInfoDao.update(CourseInfo.getColumnName(CourseInfo.Columns.REQUIRED_FREQUENCY_PER_TERM), requiredFrequencyPerTerm, queryTermList);
		courseInfoDao.update(CourseInfo.getColumnName(CourseInfo.Columns.REQUIRED_FREQUENCY_PER_SEMESTER), requiredFrequencyPerSemester, queryTermList);
		courseInfoDao.update(CourseInfo.getColumnName(CourseInfo.Columns.REQUIRED_FREQUENCY_PER_YEAR), requiredFrequencyPerYear, queryTermList);
		courseInfoDao.update(CourseInfo.getColumnName(CourseInfo.Columns.CREDIT_AMOUNT), creditAmount, queryTermList);
		courseInfoDao.update(CourseInfo.getColumnName(CourseInfo.Columns.DELETED), deleted, queryTermList);
		courseInfoDao.update(CourseInfo.getColumnName(CourseInfo.Columns.DEPARTMENT), department, queryTermList);
		courseInfoDao.update(CourseInfo.getColumnName(CourseInfo.Columns.COURSE_NUMBER), courseNumber, queryTermList);
		
		//Edit Calendar Info
		QueryTerm updateCalendar = new QueryTerm(CalendarInfo.getColumnName(CalendarInfo.Columns.ID), ComparisonOperator.EQUAL, theSection.getCalendarInfoId(), null);
		queryTermList.remove(0);
		queryTermList.add(updateCalendar);
		
		
		String semester = createSectionDto.getSemester();
		Integer year = createSectionDto.getYear();
		String days = createSectionDto.getDays();
		Integer startTime = createSectionDto.getStartTime();
		Integer endTime = createSectionDto.getEndTime();
		Integer term = createSectionDto.getTerm();
		
		calendarInfoDao.update(CalendarInfo.getColumnName(CalendarInfo.Columns.SEMESTER), semester, queryTermList);
		calendarInfoDao.update(CalendarInfo.getColumnName(CalendarInfo.Columns.YEAR), year, queryTermList);
		calendarInfoDao.update(CalendarInfo.getColumnName(CalendarInfo.Columns.DAYS), days, queryTermList);
		calendarInfoDao.update(CalendarInfo.getColumnName(CalendarInfo.Columns.START_TIME), startTime, queryTermList);
		calendarInfoDao.update(CalendarInfo.getColumnName(CalendarInfo.Columns.END_TIME), endTime, queryTermList);
		calendarInfoDao.update(CalendarInfo.getColumnName(CalendarInfo.Columns.TERM), term, queryTermList);
		
		//Edit Section Info
		QueryTerm updateSection = new QueryTerm(SectionInfo.getColumnName(SectionInfo.Columns.ID), ComparisonOperator.EQUAL, sectionInfoId, null);
		queryTermList.remove(0);
		queryTermList.add(updateSection);
		
		Integer sectionNumber = createSectionDto.getSectionNumber();
		String sectionType = createSectionDto.getSectionType();
		String location = createSectionDto.getLocation();
		//Boolean deleted = createSectionDto.getDeleted();
		Integer instructorInfoId = createSectionDto.getInstructorInfoId();
		
		sectionInfoDao.update(SectionInfo.getColumnName(SectionInfo.Columns.SECTION_NUMBER), sectionNumber, queryTermList);
		sectionInfoDao.update(SectionInfo.getColumnName(SectionInfo.Columns.SECTION_TYPE), sectionType, queryTermList);
		sectionInfoDao.update(SectionInfo.getColumnName(SectionInfo.Columns.LOCATION), location, queryTermList);
		sectionInfoDao.update(SectionInfo.getColumnName(SectionInfo.Columns.DELETED), deleted, queryTermList);
		sectionInfoDao.update(SectionInfo.getColumnName(SectionInfo.Columns.INSTRUCTOR_INFO_ID), instructorInfoId, queryTermList);
		
		return rowsAffectedList;
	}
}
