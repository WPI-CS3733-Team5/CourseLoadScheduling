package org.dselent.scheduling.server.model;

import java.sql.JDBCType;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InstructorInfo extends Model {

    // table name
    public static final String TABLE_NAME = "instructor_info";

    // column names
    public static enum Columns
    {
        ID,
        RANK,
        COURSE_LOAD,
        //PHONE_NUMBER,
        OFFICE,
        USER_INFO_ID,
        DEPARTMENT
    }

    // enum list
    private static final List<InstructorInfo.Columns> COLUMN_LIST = new ArrayList<>();

    // type mapping
    private static final Map<InstructorInfo.Columns, JDBCType> COLUMN_TYPE_MAP = new HashMap<>();

    static
    {
        for(InstructorInfo.Columns key : InstructorInfo.Columns.values())
        {
            COLUMN_LIST.add(key);
        }

        COLUMN_TYPE_MAP.put(InstructorInfo.Columns.ID, JDBCType.INTEGER);
        COLUMN_TYPE_MAP.put(InstructorInfo.Columns.RANK, JDBCType.INTEGER);
        COLUMN_TYPE_MAP.put(InstructorInfo.Columns.COURSE_LOAD, JDBCType.INTEGER);
        //COLUMN_TYPE_MAP.put(InstructorInfo.Columns.PHONE_NUMBER, JDBCType.VARCHAR);
        COLUMN_TYPE_MAP.put(InstructorInfo.Columns.OFFICE, JDBCType.VARCHAR);
        COLUMN_TYPE_MAP.put(InstructorInfo.Columns.USER_INFO_ID, JDBCType.INTEGER);
        COLUMN_TYPE_MAP.put(InstructorInfo.Columns.DEPARTMENT, JDBCType.VARCHAR);
    };

    // attributes
    
    @JsonProperty("instructorInfo_id")
    private Integer id;
    
    @JsonProperty("instructorInfo_rank")
    private Integer rank;
    
    @JsonProperty("instructorInfo_courseLoad")
    private Integer courseLoad;
    
    //@JsonProperty("instructorInfo_phoneNumber")
    //private String phoneNumber;
    
    @JsonProperty("instructorInfo_office")
    private String office;
    
    @JsonProperty("instructorInfo_userInfoId")
    private Integer userInfoId;
    
    @JsonProperty("instructorInfo_department")
    private String department;

    // methods

    public static JDBCType getColumnType(InstructorInfo.Columns column)
    {
        return COLUMN_TYPE_MAP.get(column);
    }

    public static String getColumnName(InstructorInfo.Columns column)
    {
        return column.toString().toLowerCase();
    }

    public static List<String> getColumnNameList()
    {
        List<String> columnNameList = new ArrayList<>();

        for(InstructorInfo.Columns column : COLUMN_LIST)
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

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getCourseLoad() {
        return courseLoad;
    }

    public void setCourseLoad(Integer courseLoad) {
        this.courseLoad = courseLoad;
    }
/*
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
*/
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
        InstructorInfo that = (InstructorInfo) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(rank, that.rank) &&
                Objects.equals(courseLoad, that.courseLoad) &&
                //Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(office, that.office) &&
                Objects.equals(userInfoId, that.userInfoId) &&
                Objects.equals(department, that.department);
    }

    @Override
    public int hashCode() {
    	return Objects.hash(id, rank, courseLoad, office, userInfoId, department);
        //return Objects.hash(id, rank, courseLoad, phoneNumber, office, userInfoId, department);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("InstructorInfo{");
        sb.append("id=").append(id);
        sb.append(", rank='").append(rank).append('\'');
        sb.append(", courseLoad=").append(courseLoad);
        //sb.append(", phoneNumber='").append(phoneNumber).append('\'');
        sb.append(", office='").append(office).append('\'');
        sb.append(", userInfoId=").append(userInfoId);
        sb.append(", department='").append(department).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
