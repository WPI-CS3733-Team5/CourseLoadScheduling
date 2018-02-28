package org.dselent.scheduling.server.model;

import java.sql.JDBCType;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WishlistLinks extends Model
{

    public static final String TABLE_NAME = "wishlist_links";

    public static enum Columns
    {

        ID,
        INSTRUCTOR_INFO_ID,
        SECTION_INFO_ID;

    }

    private static final List<WishlistLinks.Columns> COLUMN_LIST = new ArrayList<>();

    // type mapping
    private static final Map<WishlistLinks.Columns, JDBCType> COLUMN_TYPE_MAP = new HashMap<>();


    static
    {
        for(WishlistLinks.Columns key: WishlistLinks.Columns.values())
        {

            COLUMN_LIST.add(key);

        }

        COLUMN_TYPE_MAP.put(WishlistLinks.Columns.ID, JDBCType.INTEGER);
        COLUMN_TYPE_MAP.put(Columns.INSTRUCTOR_INFO_ID, JDBCType.INTEGER);
        COLUMN_TYPE_MAP.put(WishlistLinks.Columns.SECTION_INFO_ID, JDBCType.INTEGER);

    }

    @JsonProperty("wishlistLinks_id")
    private Integer id;
    
    @JsonProperty("wishlistLinks_instructorInfoId")
    private Integer instructorInfoId;
    
    @JsonProperty("wishlistLinks_sectionInfoId")
    private Integer sectionInfoId;

    public static JDBCType getColumnType(WishlistLinks.Columns column)
    {
        return COLUMN_TYPE_MAP.get(column);
    }

    public static String getColumnName(WishlistLinks.Columns column)
    {
        return column.toString().toLowerCase();
    }

    public static List<String> getColumnNameList()

    {
        List<String> columnNameList = new ArrayList<>();

        for(WishlistLinks.Columns column : COLUMN_LIST)
        {
            columnNameList.add(getColumnName(column));
        }

        return columnNameList;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("wishlist_links{");
        sb.append("id=").append(id);
        sb.append(", instructor_Info_Id=").append(instructorInfoId);
        sb.append(", section_Info_Id=").append(sectionInfoId);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WishlistLinks that = (WishlistLinks) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(instructorInfoId, that.instructorInfoId) &&
                Objects.equals(sectionInfoId, that.sectionInfoId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, instructorInfoId, sectionInfoId);
    }

    public static String getTableName() {

        return TABLE_NAME;
    }

    public static List<Columns> getColumnList() {
        return COLUMN_LIST;
    }

    public static Map<Columns, JDBCType> getColumnTypeMap() {
        return COLUMN_TYPE_MAP;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInstructorInfoId() {
        return instructorInfoId;
    }

    public void setInstructorInfoId(Integer instructor_Info_Id) {
        this.instructorInfoId = instructor_Info_Id;
    }

    public Integer getSectionInfoId() {
        return sectionInfoId;
    }

    public void setSectionInfoId(Integer sectionInfoId) {
        this.sectionInfoId = sectionInfoId;
    }
}


