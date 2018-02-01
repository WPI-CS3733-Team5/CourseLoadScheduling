package org.dselent.scheduling.server.model;

import java.sql.JDBCType;
import java.util.*;

public class SectionPopulation extends  Model
{

    //table name
    public static final String TABLE_NAME = "section_population";

    public static enum Columns
    {

        ID,
        EXPECTEDPOPULATION,
        POPULATIONCAP,
        SECTIONINFOID

    }

    // enum list
    private static final List<Columns> COLUMN_LIST = new ArrayList<>();

    // type mapping
    private static final Map<Columns, JDBCType> COLUMN_TYPE_MAP = new HashMap<>();

    static
    {
        for(SectionPopulation.Columns key: SectionPopulation.Columns.values())
        {

            COLUMN_LIST.add(key);

        }

        COLUMN_TYPE_MAP.put(SectionPopulation.Columns.ID, JDBCType.INTEGER);
        COLUMN_TYPE_MAP.put(SectionPopulation.Columns.EXPECTEDPOPULATION, JDBCType.INTEGER);
        COLUMN_TYPE_MAP.put(SectionPopulation.Columns.POPULATIONCAP, JDBCType.INTEGER);
        COLUMN_TYPE_MAP.put(SectionPopulation.Columns.SECTIONINFOID, JDBCType.INTEGER);

    }

    private Integer id;
    private Integer expectedPopulation;
    private Integer populationCap;
    private Integer sectionInfoId;

    //methods

    public static JDBCType getColumnType(SectionPopulation.Columns column)
    {
        return COLUMN_TYPE_MAP.get(column);
    }

    public static String getColumnName(SectionPopulation.Columns column)
    {
        return column.toString().toLowerCase();
    }

    public static List<String> getColumnNameList()

    {
        List<String> columnNameList = new ArrayList<>();

        for(SectionPopulation.Columns column : COLUMN_LIST)
        {
            columnNameList.add(getColumnName(column));
        }

        return columnNameList;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("section_population{");
        sb.append("id=").append(id);
        sb.append(", expected_Population=").append(expectedPopulation);
        sb.append(", population_Cap=").append(populationCap);
        sb.append(", section_Info_Id=").append(sectionInfoId);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SectionPopulation that = (SectionPopulation) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(expectedPopulation, that.expectedPopulation) &&
                Objects.equals(populationCap, that.populationCap) &&
                Objects.equals(sectionInfoId, that.sectionInfoId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, expectedPopulation, populationCap, sectionInfoId);
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

    public Integer getExpectedPopulation() {
        return expectedPopulation;
    }

    public void setExpectedPopulation(Integer expected_Population) {
        this.expectedPopulation = expected_Population;
    }

    public Integer getPopulationCap() {
        return populationCap;
    }

    public void setPopulationCap(Integer population_Cap) {
        this.populationCap = population_Cap;
    }

    public Integer getSectionInfoId() {
        return sectionInfoId;
    }

    public void setSectionInfoId(Integer section_Info_Id) {
        this.sectionInfoId = section_Info_Id;
    }


}
