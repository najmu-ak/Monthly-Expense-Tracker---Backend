package com.example.expense.entity;

import java.util.List;

public class TableSchemaResponse  {
    private String tableName;
    private List<ColumnInfo> columns;

    public static class ColumnInfo {
        private String name;
        private String type;
        private boolean nullable;
        private String key;
        private String defaultValue;
        private String extra;

        public ColumnInfo() {}
        public ColumnInfo(String name, String type, boolean nullable, String key, String defaultValue, String extra) {
            this.name = name; this.type = type; this.nullable = nullable; this.key = key;
            this.defaultValue = defaultValue; this.extra = extra;
        }
        public String getName() { return name; }
        public String getType() { return type; }
        public boolean isNullable() { return nullable; }
        public String getKey() { return key; }
        public String getDefaultValue() { return defaultValue; }
        public String getExtra() { return extra; }
        public void setName(String name) { this.name = name; }
        public void setType(String type) { this.type = type; }
        public void setNullable(boolean nullable) { this.nullable = nullable; }
        public void setKey(String key) { this.key = key; }
        public void setDefaultValue(String defaultValue) { this.defaultValue = defaultValue; }
        public void setExtra(String extra) { this.extra = extra; }
    }

    public String getTableName() { return tableName; }
    public void setTableName(String tableName) { this.tableName = tableName; }
    public List<ColumnInfo> getColumns() { return columns; }
    public void setColumns(List<ColumnInfo> columns) { this.columns = columns; }
}
