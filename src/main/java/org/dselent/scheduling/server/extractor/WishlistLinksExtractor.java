package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.*;


public class WishlistLinksExtractor extends Extractor<List<WishlistLinks>>
{

    @Override
    public List<WishlistLinks> extractData(ResultSet rs) throws SQLException
    {
        List<WishlistLinks> resultList = new ArrayList<>();

        while (rs.next())
        {
            WishlistLinks result = new WishlistLinks();

            result.setId(rs.getInt(WishlistLinks.getColumnName(WishlistLinks.Columns.ID)));

            if(rs.wasNull())
            {
                result.setId(null);
            }

            result.setInstructorInfoId(rs.getInt(WishlistLinks.getColumnName(WishlistLinks.Columns.INSTRUCTOR_INFO_ID)));

            if(rs.wasNull())
            {
                result.setInstructorInfoId(null);
            }

            result.setSectionInfoId(rs.getInt(WishlistLinks.getColumnName(WishlistLinks.Columns.SECTION_INFO_ID)));

            if(rs.wasNull())
            {
                result.setSectionInfoId(null);
            }

            resultList.add(result);
        }

        return  resultList;
    }

}
