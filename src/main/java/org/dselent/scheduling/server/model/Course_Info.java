package org.dselent.scheduling.server.model;

import java.sql.JDBCType;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;


public class Course_Info extends Model {
    // table name
    public static final String TABLE_NAME = "course_info";

    // column names
    public static enum Columns {
        ID,
        COURSE_NAME,
        REQUIRED_FREQUENCY_PER_TERM,
        REQUIRED_FREQUENCY_PER_SEMESTER,
        REQUIRED_FREQUENCY_PER_YEAR,
        CREDIT_AMOUNT,
        DELETED,
        DEPARTMENT,
        COURSE_NUMBER
    }

    // enum list
    private static final List<Columns> COLUMN_LIST = new ArrayList<>();

    // type mapping
    private static final Map<Columns, JDBCType> COLUMN_TYPE_MAP = new HashMap<>();

    static {
        for (Columns key : Columns.values()) {
            COLUMN_LIST.add(key);
        }

        COLUMN_TYPE_MAP.put(Columns.REQUIRED_FREQUENCY_PER_TERM, JDBCType.INTEGER);
        COLUMN_TYPE_MAP.put(Columns.REQUIRED_FREQUENCY_PER_SEMESTER, JDBCType.INTEGER);
        COLUMN_TYPE_MAP.put(Columns.REQUIRED_FREQUENCY_PER_YEAR, JDBCType.INTEGER);
        COLUMN_TYPE_MAP.put(Columns.CREDIT_AMOUNT, JDBCType.INTEGER);
        COLUMN_TYPE_MAP.put(Columns.DELETED, JDBCType.BOOLEAN);
        COLUMN_TYPE_MAP.put(Columns.DEPARTMENT, JDBCType.VARCHAR);
        COLUMN_TYPE_MAP.put(Columns.COURSE_NUMBER, JDBCType.INTEGER);
    }

    ;

    // attributes

    private Integer id;
    private String courseName;
    private Integer requireFrequencyPerTerm;
    private Integer requiredFrequencyPerSemester;
    private Integer requiredFrequencyPerYear;
    private Integer creditAmount;
    private Boolean deleted;
    private Integer department;
    private Integer courseNumber;

    // methods

    public static JDBCType getColumnType(Course_Info.Columns column)
    {
        return COLUMN_TYPE_MAP.get(column);
    }

    public static String getColumnName(Course_Info.Columns column)
    {
        return column.toString().toLowerCase();
    }

    public static List<String> getColumnNameList()
    {
        List<String> columnNameList = new ArrayList<>();

        for(Course_Info.Columns column : COLUMN_LIST)
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

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getRequireFrequencyPerTerm() {
        return requireFrequencyPerTerm;
    }

    public void setRequireFrequencyPerTerm(Integer requireFrequencyPerTerm) {
        this.requireFrequencyPerTerm = requireFrequencyPerTerm;
    }

    public Integer getRequiredFrequencyPerSemester() {
        return requiredFrequencyPerSemester;
    }

    public void setRequiredFrequencyPerSemester(Integer requiredFrequencyPerSemester) {
        this.requiredFrequencyPerSemester = requiredFrequencyPerSemester;
    }

    public Integer getRequiredFrequencyPerYear() {
        return requiredFrequencyPerYear;
    }

    public void setRequiredFrequencyPerYear(Integer requiredFrequencyPerYear) {
        this.requiredFrequencyPerYear = requiredFrequencyPerYear;
    }

    public Integer getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(Integer creditAmount) {
        this.creditAmount = creditAmount;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Integer getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    public Integer getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(Integer courseNumber) {
        this.courseNumber = courseNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course_Info that = (Course_Info) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(courseName, that.courseName) &&
                Objects.equals(requireFrequencyPerTerm, that.requireFrequencyPerTerm) &&
                Objects.equals(requiredFrequencyPerSemester, that.requiredFrequencyPerSemester) &&
                Objects.equals(requiredFrequencyPerYear, that.requiredFrequencyPerYear) &&
                Objects.equals(creditAmount, that.creditAmount) &&
                Objects.equals(deleted, that.deleted) &&
                Objects.equals(department, that.department) &&
                Objects.equals(courseNumber, that.courseNumber);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, courseName, requireFrequencyPerTerm, requiredFrequencyPerSemester, requiredFrequencyPerYear, creditAmount, deleted, department, courseNumber);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Course_Info{");
        sb.append("id=").append(id);
        sb.append(", courseName='").append(courseName).append('\'');
        sb.append(", requireFrequencyPerTerm=").append(requireFrequencyPerTerm);
        sb.append(", requiredFrequencyPerSemester=").append(requiredFrequencyPerSemester);
        sb.append(", requiredFrequencyPerYear=").append(requiredFrequencyPerYear);
        sb.append(", creditAmount=").append(creditAmount);
        sb.append(", deleted=").append(deleted);
        sb.append(", department=").append(department);
        sb.append(", courseNumber=").append(courseNumber);
        sb.append('}');
        return sb.toString();
    }
}