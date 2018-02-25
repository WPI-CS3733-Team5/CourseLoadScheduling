package org.dselent.scheduling.server.model;

import java.sql.JDBCType;
import java.util.*;

//import org.dselent.scheduling.server.model.User_Info.Columns;

public class ScheduleLinks extends Model
{

    public static final String TABLE_NAME = "schedule_links";

    public static enum Columns
    {

        ID,
        INSTRUCTOR_INFO_ID,
        SECTION_INFO_ID;

    }

    private static final List<ScheduleLinks.Columns> COLUMN_LIST = new ArrayList<>();

    // type mapping
    private static final Map<ScheduleLinks.Columns, JDBCType> COLUMN_TYPE_MAP = new HashMap<>();


    static
    {
        for(ScheduleLinks.Columns key: ScheduleLinks.Columns.values())
        {

            COLUMN_LIST.add(key);

        }

        COLUMN_TYPE_MAP.put(ScheduleLinks.Columns.ID, JDBCType.INTEGER);
        COLUMN_TYPE_MAP.put(ScheduleLinks.Columns.INSTRUCTOR_INFO_ID, JDBCType.INTEGER);
        COLUMN_TYPE_MAP.put(ScheduleLinks.Columns.SECTION_INFO_ID, JDBCType.INTEGER);

    }

    private Integer id;
    private Integer instructorInfoId;
    private Integer sectionInfoId;

    public static JDBCType getColumnType(ScheduleLinks.Columns column)
    {
        return COLUMN_TYPE_MAP.get(column);
    }

    public static String getColumnName(ScheduleLinks.Columns column)
    {
        return column.toString().toLowerCase();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("schedule_links{");
        sb.append("id=").append(id);
        sb.append(", instructorInfoId=").append(instructorInfoId);
        sb.append(", sectionInfoId=").append(sectionInfoId);
        sb.append('}');
        return sb.toString();
    }

    public static List<String> getColumnNameList()
    {
        List<String> columnNameList = new ArrayList<>();

        for(Columns column : COLUMN_LIST)
        {
            columnNameList.add(getColumnName(column));
        }

        return columnNameList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduleLinks that = (ScheduleLinks) o;
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

    public void setInstructorInfoId(Integer instructorInfoId) {
        this.instructorInfoId = instructorInfoId;
    }

    public Integer getSectionInfoId() {
        return sectionInfoId;
    }

    public void setSectionInfoId(Integer sectionInfoId) {
        this.sectionInfoId = sectionInfoId;
    }
}


