package org.dselent.scheduling.server.model;

import java.sql.JDBCType;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

public class Calendar_Info extends Model {
    // table name
    public static final String TABLE_NAME = "calendar_info";

    // column names
    public static enum Columns {
        ID,
        YEAR,
        SEMESTER,
        TERM_ID,
        CREDIT_AMOUNT,
        DAYS,
        START_TIME,
        END_TIME
    }
    // enum list
    private static final List<Calendar_Info.Columns> COLUMN_LIST = new ArrayList<>();

    // type mapping
    private static final Map<Calendar_Info.Columns, JDBCType> COLUMN_TYPE_MAP = new HashMap<>();

    static {
        for (Calendar_Info.Columns key : Calendar_Info.Columns.values()) {
            COLUMN_LIST.add(key);
        }

        COLUMN_TYPE_MAP.put(Calendar_Info.Columns.ID, JDBCType.INTEGER);
        COLUMN_TYPE_MAP.put(Calendar_Info.Columns.YEAR, JDBCType.INTEGER);
        COLUMN_TYPE_MAP.put(Calendar_Info.Columns.SEMESTER, JDBCType.INTEGER);
        COLUMN_TYPE_MAP.put(Calendar_Info.Columns.CREDIT_AMOUNT, JDBCType.INTEGER);
        COLUMN_TYPE_MAP.put(Calendar_Info.Columns.DAYS, JDBCType.VARCHAR);
        COLUMN_TYPE_MAP.put(Calendar_Info.Columns.START_TIME, JDBCType.INTEGER);
        COLUMN_TYPE_MAP.put(Calendar_Info.Columns.END_TIME, JDBCType.INTEGER);
    }

    ;

    // attributes

    private Integer id;
    private Integer year;
    private Integer semester;
    private Integer creditAmount;
    private String days;
    private Integer startTime;
    private Integer endTime;


    // methods

    public static JDBCType getColumnType(Calendar_Info.Columns column)
    {
        return COLUMN_TYPE_MAP.get(column);
    }

    public static String getColumnName(Calendar_Info.Columns column)
    {
        return column.toString().toLowerCase();
    }

    public static List<String> getColumnNameList()
    {
        List<String> columnNameList = new ArrayList<>();

        for(Calendar_Info.Columns column : COLUMN_LIST)
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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public Integer getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(Integer creditAmount) {
        this.creditAmount = creditAmount;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Calendar_Info that = (Calendar_Info) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(year, that.year) &&
                Objects.equals(semester, that.semester) &&
                Objects.equals(creditAmount, that.creditAmount) &&
                Objects.equals(days, that.days) &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(endTime, that.endTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, year, semester, creditAmount, days, startTime, endTime);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Calendar_Info{");
        sb.append("id=").append(id);
        sb.append(", year=").append(year);
        sb.append(", semester=").append(semester);
        sb.append(", creditAmount=").append(creditAmount);
        sb.append(", days='").append(days).append('\'');
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append('}');
        return sb.toString();
    }
}
