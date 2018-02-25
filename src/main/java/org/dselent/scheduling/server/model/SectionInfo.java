package org.dselent.scheduling.server.model;

import java.sql.JDBCType;
import java.util.*;

public class SectionInfo extends Model
{

    //table name
    public static final String TABLE_NAME = "section_info";

    public static enum Columns
    {

        ID,
        SECTION_NUMBER,
        SECTION_TYPE,
        INSTRUCTOR_INFO_ID,
        LOCATION,
        DELETED,
        COURSE_INFO_ID,
        CALENDAR_INFO_ID

    }

    private static final List<Columns> COLUMN_LIST = new ArrayList<>();

    private static final Map<Columns, JDBCType> COLUMN_TYPE_MAP = new HashMap<>();

    static
    {

        for(Columns key: Columns.values())
        {

            COLUMN_LIST.add(key);

        }

        COLUMN_TYPE_MAP.put(SectionInfo.Columns.ID, JDBCType.INTEGER);
        COLUMN_TYPE_MAP.put(SectionInfo.Columns.SECTION_NUMBER, JDBCType.INTEGER);
        COLUMN_TYPE_MAP.put(SectionInfo.Columns.SECTION_TYPE, JDBCType.VARCHAR);
        COLUMN_TYPE_MAP.put(SectionInfo.Columns.INSTRUCTOR_INFO_ID, JDBCType.INTEGER);
        COLUMN_TYPE_MAP.put(SectionInfo.Columns.LOCATION, JDBCType.VARCHAR);
        COLUMN_TYPE_MAP.put(SectionInfo.Columns.DELETED, JDBCType.BOOLEAN);
        COLUMN_TYPE_MAP.put(SectionInfo.Columns.COURSE_INFO_ID, JDBCType.INTEGER);
        COLUMN_TYPE_MAP.put(SectionInfo.Columns.CALENDAR_INFO_ID, JDBCType.INTEGER);

    }

    //attributes

    private Integer id;
    private Integer sectionNumber;
    private String sectionType;
    private Integer instructorInfoId;
    private String location;
    private Boolean deleted;
    private Integer courseInfoId;
    private Integer calendarInfoId;

    //methods

    public static JDBCType getColumnType(SectionInfo.Columns column)
    {
        return COLUMN_TYPE_MAP.get(column);
    }

    public static String getColumnName(SectionInfo.Columns column)
    {
        return column.toString().toLowerCase();
    }

    public static List<String> getColumnNameList()
    {
        List<String> columnNameList = new ArrayList<>();

        for(SectionInfo.Columns column : COLUMN_LIST)
        {
            columnNameList.add(getColumnName(column));
        }

        return columnNameList;
    }

    //

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

    public Integer getSectionNumber() {
        return sectionNumber;
    }

    public void setSectionNumber(Integer sectionNumber) {
        this.sectionNumber = sectionNumber;
    }

    public String getSectionType() {
        return sectionType;
    }

    public void setSectionType(String sectionType) {
        this.sectionType = sectionType;
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

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Integer getCourseInfoId() {
        return courseInfoId;
    }

    public void setCourseInfoId(Integer courseInfoId) {
        this.courseInfoId = courseInfoId;
    }

    public Integer getCalendarInfoId() {
        return calendarInfoId;
    }

    public void setCalendarInfoId(Integer calendarInfoId)
    {
    	this.calendarInfoId = calendarInfoId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SectionInfo that = (SectionInfo) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(sectionNumber, that.sectionNumber) &&
                Objects.equals(sectionType, that.sectionType) &&
                Objects.equals(instructorInfoId, that.instructorInfoId) &&
                Objects.equals(location, that.location) &&
                Objects.equals(deleted, that.deleted) &&
                Objects.equals(courseInfoId, that.courseInfoId) &&
                Objects.equals(calendarInfoId, that.calendarInfoId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, sectionNumber, sectionType, instructorInfoId, location, deleted, courseInfoId, calendarInfoId);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("section_info{");
        sb.append("id=").append(id);
        sb.append(", sectionId=").append(sectionNumber);
        sb.append(", sectionType='").append(sectionType).append('\'');
        sb.append(", instructorInfoId=").append(instructorInfoId);
        sb.append(", location='").append(location).append('\'');
        sb.append(", deleted=").append(deleted);
        sb.append(", courseInfoId=").append(courseInfoId);
        sb.append(", calendarInfoId=").append(calendarInfoId);
        sb.append('}');
        return sb.toString();
    }
}
