package org.dselent.scheduling.server.service.impl;

import org.dselent.scheduling.server.dao.ScheduleLinksDao;
import org.dselent.scheduling.server.dto.GetOneScheduleDto;
import org.dselent.scheduling.server.model.ScheduleLinks;
import org.dselent.scheduling.server.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
public class ScheduleServiceImpl implements ScheduleService
{
    @Autowired
    private ScheduleLinksDao scheduleLinksDao;

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
    public ScheduleLinks getOneSchedule(GetOneScheduleDto dto) throws SQLException {

        ScheduleLinks scheduleLinks = scheduleLinksDao.findById(dto.getId());

        return  scheduleLinks;

    }
}
