package com.example.expense.entity;

import java.util.List;

public class CreateTableRequest  {
    private String tableName;
    private List<Column> columns;

    public static class Column {
        private String name;
        private String type;
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getType() { return type; }
        public void setType(String type) { this.type = type; }
    }

    public String getTableName() { return tableName; }
    public void setTableName(String tableName) { this.tableName = tableName; }
    public List<Column> getColumns() { return columns; }
    public void setColumns(List<Column> columns) { this.columns = columns; }
}
