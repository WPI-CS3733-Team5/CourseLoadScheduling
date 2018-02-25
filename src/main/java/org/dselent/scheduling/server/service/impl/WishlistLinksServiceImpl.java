package org.dselent.scheduling.server.service.impl;

import org.dselent.scheduling.server.dao.WishlistLinksDao;
import org.dselent.scheduling.server.dto.WishlistLinksDto;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.ScheduleLinks;
import org.dselent.scheduling.server.model.WishlistLinks;
import org.dselent.scheduling.server.service.WishlistLinksService;
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
    public List<WishlistLinks> wishlistLinks(Integer instructorInfoId) throws SQLException {
    	
    	List<String> selectedWishlistIds = new ArrayList<String>();
    	selectedWishlistIds.addAll(WishlistLinks.getColumnNameList());
    	
    	QueryTerm termOne = new QueryTerm(WishlistLinks.getColumnName(WishlistLinks.Columns.INSTRUCTOR_INFO_ID), ComparisonOperator.EQUAL, selectedWishlistIds, null);
    	
    	List<QueryTerm> queryTermList = new ArrayList<>();
    	
    	List<Pair<String, ColumnOrder>> thing = new ArrayList<>();
    	
    	List<WishlistLinks> returnList = new ArrayList<WishlistLinks>();
    	
    	returnList.addAll(wishlistLinksDao.select(selectedWishlistIds, queryTermList, thing));

        return  returnList;

    }
}