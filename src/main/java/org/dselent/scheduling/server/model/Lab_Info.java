package org.dselent.scheduling.server.model;

import java.sql.JDBCType;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

public class Lab_Info extends Model {

    // table name
    public static final String TABLE_NAME = "lab_info";

    // column names
    public static enum Columns {
        ID,
        SECTION_INFO_ID,
        INSTRUCTOR_INFO_ID,
        LOCATION,
        CALENDAR_INFO_ID
    }
    // enum list

    private static final List<Lab_Info.Columns> COLUMN_LIST = new ArrayList<>();

    // type mapping
    private static final Map<Lab_Info.Columns, JDBCType> COLUMN_TYPE_MAP = new HashMap<>();

    static {
        for (Lab_Info.Columns key : Lab_Info.Columns.values()) {
            COLUMN_LIST.add(key);
        }

        COLUMN_TYPE_MAP.put(Lab_Info.Columns.ID, JDBCType.INTEGER);
        COLUMN_TYPE_MAP.put(Lab_Info.Columns.SECTION_INFO_ID, JDBCType.INTEGER);
        COLUMN_TYPE_MAP.put(Lab_Info.Columns.INSTRUCTOR_INFO_ID, JDBCType.INTEGER);
        COLUMN_TYPE_MAP.put(Lab_Info.Columns.LOCATION, JDBCType.VARCHAR);
        COLUMN_TYPE_MAP.put(Lab_Info.Columns.CALENDAR_INFO_ID, JDBCType.INTEGER);

    }
    ;

    // attributes

    private Integer id;
    private Integer sectionInfoId;
    private Integer instructorInfoId;
    private String location;
    private Integer calendarInfoId;

    // methods

    public static JDBCType getColumnType(Lab_Info.Columns column)
    {
        return COLUMN_TYPE_MAP.get(column);
    }

    public static String getColumnName(Lab_Info.Columns column)
    {
        return column.toString().toLowerCase();
    }

    public static List<String> getColumnNameList()
    {
        List<String> columnNameList = new ArrayList<>();

        for(Lab_Info.Columns column : COLUMN_LIST)
        {
            columnNameList.add(getColumnName(column));
        }

        return columnNameList;
    }


    //Getters and setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSectionInfoId() {
        return sectionInfoId;
    }

    public void setSectionInfoId(Integer sectionInfoId) {
        this.sectionInfoId = sectionInfoId;
    }

    public Integer getInstructorInfoId() {
        return instructorInfoId;
    }

    public void setInstructorInfoId(Integer instructorInfoId) {
        this.instructorInfoId = instructorInfoId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getCalendarInfoId() {
        return calendarInfoId;
    }

    public void setCalendarInfoId(Integer calendarInfoId) {
        this.calendarInfoId = calendarInfoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lab_Info lab_info = (Lab_Info) o;
        return Objects.equals(id, lab_info.id) &&
                Objects.equals(sectionInfoId, lab_info.sectionInfoId) &&
                Objects.equals(instructorInfoId, lab_info.instructorInfoId) &&
                Objects.equals(location, lab_info.location) &&
                Objects.equals(calendarInfoId, lab_info.calendarInfoId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, sectionInfoId, instructorInfoId, location, calendarInfoId);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("LabInfo [id=");
        builder.append(id);
        builder.append(", sectionInfoId=");
        builder.append(sectionInfoId);
        builder.append(", instructorInfoId=");
        builder.append(instructorInfoId);
        builder.append(", location=");
        builder.append(location);
        builder.append(", calendarInfoId=");
        builder.append(calendarInfoId);
        builder.append("]");
        return builder.toString();
    }
}
