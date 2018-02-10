package org.dselent.scheduling.server.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.dao.CourseInfoDao;
import org.dselent.scheduling.server.dto.CreateCourseDto;
import org.dselent.scheduling.server.dto.GetAllCoursesDto;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.CourseInfo;
import org.dselent.scheduling.server.service.CourseService;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
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

    public CourseServiceImpl(){}

    @Transactional
    @Override
    public List<Integer> createCourse(CreateCourseDto createCourseDto) throws SQLException{
        List<Integer> rowsAffectedList = new ArrayList<>();

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

        return rowsAffectedList;
    }

    @Transactional
    @Override
    public List<CourseInfo> getAllCourses(GetAllCoursesDto getAllCoursesDto) throws SQLException{
        List<String> selectColumnNameList = new ArrayList<>();
        List<QueryTerm> queryTermList = new ArrayList<>();
        List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
        selectColumnNameList.addAll(CourseInfo.getColumnNameList());
        Pair<String, ColumnOrder> byId = new Pair<>(CourseInfo.getColumnName(CourseInfo.Columns.ID), ColumnOrder.ASC);

        return courseInfoDao.select(selectColumnNameList, queryTermList, orderByList);
    }
}
