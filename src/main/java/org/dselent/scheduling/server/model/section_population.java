package org.dselent.scheduling.server.model;

import java.sql.JDBCType;
import java.util.*;

public class section_population
{

    //table name
    public static final String TABLE_NAME = "section_population";

    public static enum Columns
    {

        ID,
        EXPECTED_POPULATION,
        POPULATION_CAP,
        SECTION_INFO_ID;

    }

    // enum list
    private static final List<Columns> COLUMN_LIST = new ArrayList<>();

    // type mapping
    private static final Map<Columns, JDBCType> COLUMN_TYPE_MAP = new HashMap<>();

    static
    {
        for(section_population.Columns key: section_population.Columns.values())
        {

            COLUMN_LIST.add(key);

        }

        COLUMN_TYPE_MAP.put(section_population.Columns.ID, JDBCType.INTEGER);
        COLUMN_TYPE_MAP.put(section_population.Columns.EXPECTED_POPULATION, JDBCType.INTEGER);
        COLUMN_TYPE_MAP.put(section_population.Columns.POPULATION_CAP, JDBCType.INTEGER);
        COLUMN_TYPE_MAP.put(section_population.Columns.SECTION_INFO_ID, JDBCType.INTEGER);

    }

    private Integer id;
    private Integer expected_Population;
    private Integer population_Cap;
    private Integer section_Info_Id;

    //

    public static JDBCType getColumnType(section_population.Columns column)
    {
        return COLUMN_TYPE_MAP.get(column);
    }

    public static String getColumnName(section_population.Columns column)
    {
        return column.toString().toLowerCase();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("section_population{");
        sb.append("id=").append(id);
        sb.append(", expected_Population=").append(expected_Population);
        sb.append(", population_Cap=").append(population_Cap);
        sb.append(", section_Info_Id=").append(section_Info_Id);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        section_population that = (section_population) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(expected_Population, that.expected_Population) &&
                Objects.equals(population_Cap, that.population_Cap) &&
                Objects.equals(section_Info_Id, that.section_Info_Id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, expected_Population, population_Cap, section_Info_Id);
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

    public Integer getExpected_Population() {
        return expected_Population;
    }

    public void setExpected_Population(Integer expected_Population) {
        this.expected_Population = expected_Population;
    }

    public Integer getPopulation_Cap() {
        return population_Cap;
    }

    public void setPopulation_Cap(Integer population_Cap) {
        this.population_Cap = population_Cap;
    }

    public Integer getSection_Info_Id() {
        return section_Info_Id;
    }

    public void setSection_Info_Id(Integer section_Info_Id) {
        this.section_Info_Id = section_Info_Id;
    }

    public static List<String> getColumnNameList()

    {
        List<String> columnNameList = new ArrayList<>();

        for(section_population.Columns column : COLUMN_LIST)
        {
            columnNameList.add(getColumnName(column));
        }

        return columnNameList;
    }

}
