package org.dselent.scheduling.server.model;

import java.sql.JDBCType;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by dhmchorney on 2/1/2018.
 */
public class Notification extends Model {

    //table name
    public static final String TABLE_NAME = "notifications";

    //column names
    public static enum Columns{
        ID,
        MESSAGE,
        FROM_USER_INFO_ID,
        TO_USER_INFO_ID
    }

    // enum list
    private static final List<Notification.Columns> COLUMN_LIST = new ArrayList<>();

    // type mapping
    private static final Map<Notification.Columns, JDBCType> COLUMN_TYPE_MAP = new HashMap<>();

    static{
        for(Notification.Columns key : Notification.Columns.values()){
            COLUMN_LIST.add(key);
        }

        COLUMN_TYPE_MAP.put(Notification.Columns.ID, JDBCType.INTEGER);
        COLUMN_TYPE_MAP.put(Notification.Columns.MESSAGE, JDBCType.VARCHAR);
        COLUMN_TYPE_MAP.put(Notification.Columns.FROM_USER_INFO_ID, JDBCType.INTEGER);
        COLUMN_TYPE_MAP.put(Notification.Columns.TO_USER_INFO_ID, JDBCType.INTEGER);
    }

    // attributes
    
    @JsonProperty("notification_id")
    private Integer id;
    
    @JsonProperty("notification_message")
    private String message;
    
    @JsonProperty("notification_fromUserInfoId")
    private Integer fromUserInfoId;
    
    @JsonProperty("notification_toUserInfoId")
    private Integer toUserInfoId;

    // methods

    public static String getTableName(){
        return TABLE_NAME;
    }

    public static String getColumnName(Columns column){
        return column.toString().toLowerCase();
    }

    public static JDBCType getColumnType(Columns column){
        return COLUMN_TYPE_MAP.get(column);
    }

    public static List<String> getColumnNameList(){

        List<String> columnNameList = new ArrayList<>();

        for(Columns column : COLUMN_LIST){
            columnNameList.add(getColumnName(column));
        }

        return columnNameList;
    }

    public static Map<Columns, JDBCType> getColumnTypeMap(){
        return COLUMN_TYPE_MAP;
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public Integer getFromUserInfoId(){
        return fromUserInfoId;
    }

    public void setFromUserInfoId(Integer fromUserInfoId){
        this.fromUserInfoId = fromUserInfoId;
    }

    public Integer getToUserInfoId(){
        return toUserInfoId;
    }

    public void setToUserInfoId(Integer toUserInfoId){
        this.toUserInfoId = toUserInfoId;
    }

    @Override
    public String toString(){
        final StringBuilder builder = new StringBuilder("Notification: [ id=");
        builder.append(id);
        builder.append(", message= ");
        builder.append(message);
        builder.append(", fromUser= ");
        builder.append(fromUserInfoId);
        builder.append(", toUser= ");
        builder.append(toUserInfoId);

        return builder.toString();
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }

        Notification other = (Notification) obj;

        return(Objects.equals(id, other.id) &&
               Objects.equals(message, other.message) &&
               Objects.equals(fromUserInfoId, other.fromUserInfoId) &&
               Objects.equals(toUserInfoId, other.toUserInfoId));
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, message, fromUserInfoId, toUserInfoId);
    }
}
