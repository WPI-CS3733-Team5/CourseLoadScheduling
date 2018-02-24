package org.dselent.scheduling.server.service.impl;

import org.dselent.scheduling.server.dao.WishlistLinksDao;
import org.dselent.scheduling.server.dto.WishlistLinksDto;
import org.dselent.scheduling.server.model.WishlistLinks;
import org.dselent.scheduling.server.service.WishlistLinksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    	
    	List<String> selectedWishlistIds = new ArrayList<String>();
    	selectedWishlistIds.addAll(WishlistLinks.getColumnNameList());
    	
    	QueryTerm

        WishlistLinks wishlistLinks = wishlistLinksDao.findById(dto.getInstructorInfoId());

        return  wishlistLinks;

    }
}