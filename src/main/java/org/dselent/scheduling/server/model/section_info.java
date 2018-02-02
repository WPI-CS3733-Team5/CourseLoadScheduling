package org.dselent.scheduling.server.model;

import java.sql.JDBCType;
import java.util.*;

public class section_info extends Model
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

        COLUMN_TYPE_MAP.put(section_info.Columns.ID, JDBCType.INTEGER);
        COLUMN_TYPE_MAP.put(section_info.Columns.SECTION_NUMBER, JDBCType.INTEGER);
        COLUMN_TYPE_MAP.put(section_info.Columns.SECTION_TYPE, JDBCType.VARCHAR);
        COLUMN_TYPE_MAP.put(section_info.Columns.INSTRUCTOR_INFO_ID, JDBCType.INTEGER);
        COLUMN_TYPE_MAP.put(section_info.Columns.LOCATION, JDBCType.VARCHAR);
        COLUMN_TYPE_MAP.put(section_info.Columns.DELETED, JDBCType.BOOLEAN);
        COLUMN_TYPE_MAP.put(section_info.Columns.COURSE_INFO_ID, JDBCType.INTEGER);
        COLUMN_TYPE_MAP.put(section_info.Columns.CALENDAR_INFO_ID, JDBCType.INTEGER);

    }

    //attributes

    private Integer id;
    private Integer section_Id;
    private String section_Type;
    private Integer instructor_Info_Id;
    private String location;
    private Boolean deleted;
    private Integer course_Info_Id;
    private Integer calendar_Info_Id;

    //methods

    public static JDBCType getColumnType(section_info.Columns column)
    {
        return COLUMN_TYPE_MAP.get(column);
    }

    public static String getColumnName(section_info.Columns column)
    {
        return column.toString().toLowerCase();
    }

    public static List<String> getColumnNameList()
    {
        List<String> columnNameList = new ArrayList<>();

        for(section_info.Columns column : COLUMN_LIST)
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

    public Integer getSection_Id() {
        return section_Id;
    }

    public void setSection_Id(Integer section_Id) {
        this.section_Id = section_Id;
    }

    public String getSection_Type() {
        return section_Type;
    }

    public void setSection_Type(String section_Type) {
        this.section_Type = section_Type;
    }

    public Integer getInstructor_Info_Id() {
        return instructor_Info_Id;
    }

    public void setInstructor_Info_Id(Integer instructor_Info_Id) {
        this.instructor_Info_Id = instructor_Info_Id;
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

    public Integer getCourse_Info_Id() {
        return course_Info_Id;
    }

    public void setCourse_Info_Id(Integer course_Info_Id) {
        this.course_Info_Id = course_Info_Id;
    }

    public Integer getCalendar_Info_Id() {
        return calendar_Info_Id;
    }

    public void setCalendar_Info_Id(Integer calendar_Info_Id)
    {
    	
    }
    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        section_info that = (section_info) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(section_Id, that.section_Id) &&
                Objects.equals(section_Type, that.section_Type) &&
                Objects.equals(instructor_Info_Id, that.instructor_Info_Id) &&
                Objects.equals(location, that.location) &&
                Objects.equals(deleted, that.deleted) &&
                Objects.equals(course_Info_Id, that.course_Info_Id) &&
                Objects.equals(calendar_Info_Id, that.calendar_Info_Id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, section_Id, section_Type, instructor_Info_Id, location, deleted, course_Info_Id, calendar_Info_Id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("section_info{");
        sb.append("id=").append(id);
        sb.append(", section_Id=").append(section_Id);
        sb.append(", section_Type='").append(section_Type).append('\'');
        sb.append(", instructor_Info_Id=").append(instructor_Info_Id);
        sb.append(", location='").append(location).append('\'');
        sb.append(", deleted=").append(deleted);
        sb.append(", course_Info_Id=").append(course_Info_Id);
        sb.append(", calendar_Info_Id=").append(calendar_Info_Id);
        sb.append('}');
        return sb.toString();
    }
}
