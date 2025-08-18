package com.example.expense.controller;


import com.example.expense.entity.CreateTableRequest;
import com.example.expense.entity.TableSchemaResponse;
import com.example.expense.entity.TableSchemaResponse.ColumnInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:3000", "https://monthly-expense-tracker-frontend.vercel.app"})
public class TableController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/create-table")
    public TableSchemaResponse createTable(@RequestBody CreateTableRequest request) {
        validateNames(request.getTableName(), request.getColumns());

        StringBuilder sql = new StringBuilder("CREATE TABLE ");
        sql.append(backtick(request.getTableName()))
           .append(" (id INT AUTO_INCREMENT PRIMARY KEY, ");

        for (int i = 0; i < request.getColumns().size(); i++) {
            var col = request.getColumns().get(i);
            sql.append(backtick(col.getName())).append(" ").append(col.getType());
            if (i < request.getColumns().size() - 1) sql.append(", ");
        }
        sql.append(")");
        jdbcTemplate.execute(sql.toString());

        // return schema immediately
        return describeTable(request.getTableName());
    }

    // @GetMapping("/table/{tableName}/schema")
    // public TableSchemaResponse getSchema(@PathVariable String tableName) {
    //     return describeTable(tableName);
    // }

    private TableSchemaResponse describeTable(String tableName) {
        String sql = "DESCRIBE " + backtick(tableName);
        List<ColumnInfo> cols = jdbcTemplate.query(sql, (rs, i) ->
            new ColumnInfo(
                rs.getString("Field"),
                rs.getString("Type"),
                "YES".equalsIgnoreCase(rs.getString("Null")),
                rs.getString("Key"),
                rs.getString("Default"),
                rs.getString("Extra")
            )
        );
        TableSchemaResponse resp = new TableSchemaResponse();
        resp.setTableName(tableName);
        resp.setColumns(cols);
        return resp;
    }

    private void validateNames(String tableName, List<CreateTableRequest.Column> cols) {
        if (tableName == null || tableName.isBlank()) throw new IllegalArgumentException("Table name required");
        if (cols == null || cols.isEmpty()) throw new IllegalArgumentException("At least one column required");
        if (!tableName.matches("[A-Za-z_][A-Za-z0-9_]*")) throw new IllegalArgumentException("Invalid table name");
        for (var c : cols) {
            if (c.getName() == null || c.getName().isBlank())
                throw new IllegalArgumentException("Column name required");
            if (!c.getName().matches("[A-Za-z_][A-Za-z0-9_]*"))
                throw new IllegalArgumentException("Invalid column name: " + c.getName());
        }
    }

    private String backtick(String identifier) {
        // Quote identifiers to avoid keyword/space issues and prevent injection
        return "`" + identifier.replace("`", "``") + "`";
    }
}

