package org.dselent.scheduling.server.model;

import java.sql.JDBCType;
import java.util.*;

public class InstructorInfoHistory extends Model{

    // table name
    public static final String TABLE_NAME = "instructor_info_history";

    // column names
    public static enum Columns
    {
        ID,
        INSTRUCTOR_INFO_ID,
        RANK,
        COURSE_LOAD,
        PHONE_NUMBER,
        OFFICE,
        USER_INFO_ID,
        DEPARTMENT
    }

    // enum list
    private static final List<InstructorInfoHistory.Columns> COLUMN_LIST = new ArrayList<>();

    // type mapping
    private static final Map<InstructorInfoHistory.Columns, JDBCType> COLUMN_TYPE_MAP = new HashMap<>();

    static
    {
        for(InstructorInfoHistory.Columns key : InstructorInfoHistory.Columns.values())
        {
            COLUMN_LIST.add(key);
        }

        COLUMN_TYPE_MAP.put(InstructorInfoHistory.Columns.ID, JDBCType.INTEGER);
        COLUMN_TYPE_MAP.put(InstructorInfoHistory.Columns.INSTRUCTOR_INFO_ID, JDBCType.INTEGER);
        COLUMN_TYPE_MAP.put(InstructorInfoHistory.Columns.RANK, JDBCType.VARCHAR);
        COLUMN_TYPE_MAP.put(InstructorInfoHistory.Columns.COURSE_LOAD, JDBCType.INTEGER);
        COLUMN_TYPE_MAP.put(InstructorInfoHistory.Columns.PHONE_NUMBER, JDBCType.VARCHAR);
        COLUMN_TYPE_MAP.put(InstructorInfoHistory.Columns.OFFICE, JDBCType.VARCHAR);
        COLUMN_TYPE_MAP.put(InstructorInfoHistory.Columns.USER_INFO_ID, JDBCType.INTEGER);
        COLUMN_TYPE_MAP.put(InstructorInfoHistory.Columns.DEPARTMENT, JDBCType.VARCHAR);
    };

    // attributes
    private Integer id;
    private Integer instructorInfoId;
    private String rank;
    private Integer courseLoad;
    private String phoneNumber;
    private String office;
    private Integer userInfoId;
    private String department;

    public static JDBCType getColumnType(InstructorInfoHistory.Columns column)
    {
        return COLUMN_TYPE_MAP.get(column);
    }

    public static String getColumnName(InstructorInfoHistory.Columns column)
    {
        return column.toString().toLowerCase();
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

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Integer getCourseLoad() {
        return courseLoad;
    }

    public void setCourseLoad(Integer courseLoad) {
        this.courseLoad = courseLoad;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public Integer getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(Integer userInfoId) {
        this.userInfoId = userInfoId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InstructorInfoHistory that = (InstructorInfoHistory) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(instructorInfoId, that.instructorInfoId) &&
                Objects.equals(rank, that.rank) &&
                Objects.equals(courseLoad, that.courseLoad) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(office, that.office) &&
                Objects.equals(userInfoId, that.userInfoId) &&
                Objects.equals(department, that.department);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, instructorInfoId, rank, courseLoad, phoneNumber, office, userInfoId, department);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("InstructorInfoHistory{");
        sb.append("id=").append(id);
        sb.append(", instructorInfoId=").append(instructorInfoId);
        sb.append(", rank='").append(rank).append('\'');
        sb.append(", courseLoad=").append(courseLoad);
        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
        sb.append(", office='").append(office).append('\'');
        sb.append(", userInfoId=").append(userInfoId);
        sb.append(", department='").append(department).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
