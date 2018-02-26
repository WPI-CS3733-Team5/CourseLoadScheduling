package org.dselent.scheduling.server.service;

import org.dselent.scheduling.server.dto.GetOneScheduleDto;
import org.dselent.scheduling.server.model.ScheduleLinks;

import java.util.List;
import java.sql.SQLException;
import org.springframework.stereotype.Service;


@Service
public interface ScheduleService
{
    /**
     * Gets the schedule for the requesting user
     *
     * @param getOneScheduleDto DTO container information for the insertions
     * @return A list of rows affected for each insert operation
     * @throws SQLException
     */

    public List<ScheduleLinks> getOneSchedule(Integer instructorInfoId) throws SQLException;
    public List<Integer> createSchedule(Integer instructorInfoId, List<Integer> sectionInfoIdList) throws SQLException;

}
