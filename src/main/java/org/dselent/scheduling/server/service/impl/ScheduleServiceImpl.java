package org.dselent.scheduling.server.service.impl;


import org.dselent.scheduling.server.dao.ScheduleLinksDao;
import org.dselent.scheduling.server.dao.SectionInfoDao;
import org.dselent.scheduling.server.dto.GetOneScheduleDto;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.CourseInfo;
import org.dselent.scheduling.server.model.ScheduleLinks;
import org.dselent.scheduling.server.model.SectionInfo;
import org.dselent.scheduling.server.service.ScheduleService;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService
{
    @Autowired
    private ScheduleLinksDao scheduleLinksDao;

    @Autowired
    private SectionInfoDao sectionInfoDao;
    
    public ScheduleServiceImpl()
    {
        //
    }

    /*
     * (non-Javadoc)
     * @see org.dselent.scheduling.server.service.UserService#registerUser(org.dselent.scheduling.server.dto.CreateUserDto)
     */

    @Transactional
    @Override
    public List<ScheduleLinks> getOneSchedule(Integer instructorInfoId) throws SQLException {
    	
    	List<String> selectedInstructorIds = new ArrayList<String>();
    	selectedInstructorIds.addAll(ScheduleLinks.getColumnNameList());
    	
    	QueryTerm onlyTerm = new QueryTerm(ScheduleLinks.getColumnName(ScheduleLinks.Columns.INSTRUCTOR_INFO_ID), ComparisonOperator.EQUAL, instructorInfoId, null);
    	
    	List<QueryTerm> queryTermList = new ArrayList<QueryTerm>();
    	
    	queryTermList.add(onlyTerm);
    	
    	List<Pair<String, ColumnOrder>> thing = new ArrayList<>();
    	
    	List<ScheduleLinks> returnList = new ArrayList<ScheduleLinks>();
    	
    	returnList.addAll(scheduleLinksDao.select(selectedInstructorIds, queryTermList, thing));
    	
        return  returnList;

    }

	@Override
	public List<Integer> createSchedule(Integer instructorInfoId, List<Integer> sectionInfoIdList) throws SQLException {
		List<Integer> rowsAffectedList = new ArrayList<>();
		
        List<String> scheduleInsertColumnNameList = new ArrayList<>();
        List<String> scheduleKeyHolderColumnNameList = new ArrayList<>();
        
        scheduleInsertColumnNameList.add(ScheduleLinks.getColumnName(ScheduleLinks.Columns.INSTRUCTOR_INFO_ID));
        scheduleInsertColumnNameList.add(ScheduleLinks.getColumnName(ScheduleLinks.Columns.SECTION_INFO_ID));
        
        scheduleKeyHolderColumnNameList.add(ScheduleLinks.getColumnName(ScheduleLinks.Columns.ID));
        

        
		for(Integer sectionInfoId : sectionInfoIdList) {
			ScheduleLinks scheduleLinks = new ScheduleLinks();
			scheduleLinks.setInstructorInfoId(instructorInfoId);
			scheduleLinks.setSectionInfoId(sectionInfoId);
			
	        List<QueryTerm> queryTermList = new ArrayList<QueryTerm>();
			QueryTerm updateSection = new QueryTerm(SectionInfo.getColumnName(SectionInfo.Columns.ID), ComparisonOperator.EQUAL, sectionInfoId, null);
			queryTermList.add(updateSection);
			
			sectionInfoDao.update(SectionInfo.getColumnName(SectionInfo.Columns.INSTRUCTOR_INFO_ID), instructorInfoId, queryTermList);
			
			scheduleLinksDao.insert(scheduleLinks, scheduleInsertColumnNameList, scheduleKeyHolderColumnNameList);
			
			
		}
		return rowsAffectedList;
	}
}
