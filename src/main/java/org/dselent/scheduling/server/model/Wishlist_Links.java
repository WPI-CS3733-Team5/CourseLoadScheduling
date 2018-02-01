package org.dselent.scheduling.server.model;

import java.sql.JDBCType;
import java.util.*;

public class Wishlist_Links extends Model
{

    public static final String TABLE_NAME = "wishlist_links";

    public static enum Columns
    {

        ID,
        INSTRUCTOR_INFO_ID,
        SECTION_INFO_ID;

    }

    private static final List<Wishlist_Links.Columns> COLUMN_LIST = new ArrayList<>();

    // type mapping
    private static final Map<Wishlist_Links.Columns, JDBCType> COLUMN_TYPE_MAP = new HashMap<>();


    static
    {
        for(Wishlist_Links.Columns key: Wishlist_Links.Columns.values())
        {

            COLUMN_LIST.add(key);

        }

        COLUMN_TYPE_MAP.put(Wishlist_Links.Columns.ID, JDBCType.INTEGER);
        COLUMN_TYPE_MAP.put(Wishlist_Links.Columns.INSTRUCTOR_INFO_ID, JDBCType.INTEGER);
        COLUMN_TYPE_MAP.put(Wishlist_Links.Columns.SECTION_INFO_ID, JDBCType.INTEGER);

    }

    private Integer id;
    private Integer instructorInfoId;
    private Integer sectionInfoId;

    public static JDBCType getColumnType(Wishlist_Links.Columns column)
    {
        return COLUMN_TYPE_MAP.get(column);
    }

    public static String getColumnName(Wishlist_Links.Columns column)
    {
        return column.toString().toLowerCase();
    }

    public static List<String> getColumnNameList()

    {
        List<String> columnNameList = new ArrayList<>();

        for(Wishlist_Links.Columns column : COLUMN_LIST)
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
        Wishlist_Links that = (Wishlist_Links) o;
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


