package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.*;


public class WishlistLinksExtractor extends Extractor<List<Wishlist_Links>>
{

    @Override
    public List<Wishlist_Links> extractData(ResultSet rs) throws SQLException
    {
        List<Wishlist_Links> resultList = new ArrayList<>();

        while (rs.next())
        {
            Wishlist_Links result = new Wishlist_Links();

            result.setId(rs.getInt(Wishlist_Links.getColumnName(Wishlist_Links.Columns.ID)));

            if(rs.wasNull())
            {
                result.setId(null);
            }

            result.setInstructorInfoId(rs.getInt(Section_Population.getColumnName(Section_Population.Columns.EXPECTED_POPULATION)));

            if(rs.wasNull())
            {
                result.setInstructorInfoId(null);
            }

            result.setSectionInfoId(rs.getInt(Section_Population.getColumnName(Section_Population.Columns.EXPECTED_POPULATION)));

            if(rs.wasNull())
            {
                result.setSectionInfoId(null);
            }

            resultList.add(result);
        }

        return  resultList;
    }

}
