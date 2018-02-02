package org.dselent.scheduling.server.model;

import java.sql.JDBCType;
import java.util.*;

public class wishlist_links
{

    public static final String TABLE_NAME = "wishlist_links";

    public static enum Columns
    {

        ID,
        INSTRUCTOR_INFO_ID,
        SECTION_INFO_ID;

    }

    private static final List<wishlist_links.Columns> COLUMN_LIST = new ArrayList<>();

    // type mapping
    private static final Map<wishlist_links.Columns, JDBCType> COLUMN_TYPE_MAP = new HashMap<>();


    static
    {
        for(wishlist_links.Columns key: wishlist_links.Columns.values())
        {

            COLUMN_LIST.add(key);

        }

        COLUMN_TYPE_MAP.put(wishlist_links.Columns.ID, JDBCType.INTEGER);
        COLUMN_TYPE_MAP.put(wishlist_links.Columns.INSTRUCTOR_INFO_ID, JDBCType.INTEGER);
        COLUMN_TYPE_MAP.put(wishlist_links.Columns.SECTION_INFO_ID, JDBCType.INTEGER);

    }

    private Integer id;
    private Integer instructor_Info_Id;
    private Integer section_Info_Id;

    public static JDBCType getColumnType(wishlist_links.Columns column)
    {
        return COLUMN_TYPE_MAP.get(column);
    }

    public static String getColumnName(wishlist_links.Columns column)
    {
        return column.toString().toLowerCase();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("wishlist_links{");
        sb.append("id=").append(id);
        sb.append(", instructor_Info_Id=").append(instructor_Info_Id);
        sb.append(", section_Info_Id=").append(section_Info_Id);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        wishlist_links that = (wishlist_links) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(instructor_Info_Id, that.instructor_Info_Id) &&
                Objects.equals(section_Info_Id, that.section_Info_Id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, instructor_Info_Id, section_Info_Id);
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

    public Integer getInstructor_Info_Id() {
        return instructor_Info_Id;
    }

    public void setInstructor_Info_Id(Integer instructor_Info_Id) {
        this.instructor_Info_Id = instructor_Info_Id;
    }

    public Integer getSection_Info_Id() {
        return section_Info_Id;
    }

    public void setSection_Info_Id(Integer section_Info_Id) {
        this.section_Info_Id = section_Info_Id;
    }
}


