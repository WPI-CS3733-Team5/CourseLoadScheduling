package org.dselent.scheduling.server.service.impl;

import org.dselent.scheduling.server.dao.ScheduleLinksDao;
import org.dselent.scheduling.server.dao.WishlistLinksDao;
import org.dselent.scheduling.server.dto.GetOneScheduleDto;
import org.dselent.scheduling.server.dto.WishlistLinksDto;
import org.dselent.scheduling.server.model.ScheduleLinks;
import org.dselent.scheduling.server.model.WishlistLinks;
import org.dselent.scheduling.server.service.ScheduleService;
import org.dselent.scheduling.server.service.WishlistLinksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
public class WishlistLinksServiceImpl implements WishlistLinksService
{
    @Autowired
    private WishlistLinksDao wishlistLinksDao;

    public WishlistLinksServiceImpl()
    {
        //
    }

    /*
     * (non-Javadoc)
     * @see org.dselent.scheduling.server.service.UserService#registerUser(org.dselent.scheduling.server.dto.CreateUserDto)
     */

    @Transactional
    @Override
    public WishlistLinks wishlistLinks(WishlistLinksDto dto) throws SQLException {

        WishlistLinks wishlistLinks = wishlistLinksDao.findById(dto.getId());

        return  wishlistLinks;

    }
}