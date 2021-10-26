package com.sabitov.repositories;

import com.sabitov.models.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class FileRepImpl implements FileRep {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //language=SQL
    public static final String SQL_FIND_BY_NAME = "select * from files where originalName=:originalName";
    //language=SQL
    public static final String SQL_SAVE = "insert into files (size, originalname, name, mimetype) values (:size, :originalName, :name, :mimeType)";


    @Autowired
    public FileRepImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    private final RowMapper<File> fileMapper = (row, rowNumber) -> {
        return File.builder()
                .id(row.getInt("id"))
                .size(row.getInt("size"))
                .fileName(row.getString("name"))
                .originalFileName(row.getString("originalName"))
                .mimeType(row.getString("mimeType"))
                .build();
    };

    @Override
    public void save(File file) {
        KeyHolder holder = new GeneratedKeyHolder();
        Map<String, Object> map = new HashMap<>();
        map.put("name", file.getFileName());
        map.put("originalName", file.getOriginalFileName());
        map.put("size", file.getSize());
        map.put("mimeType", file.getMimeType());
        SqlParameterSource parameterSource = new MapSqlParameterSource(map);
        namedParameterJdbcTemplate.update(SQL_SAVE, parameterSource, holder, new String[]{"id"});
        file.setId(holder.getKeyAs(Integer.class));
    }

    @Override
    public Optional<File> findByName(String name) {
        return Optional.of(namedParameterJdbcTemplate.queryForObject(SQL_FIND_BY_NAME, Collections.singletonMap("originalName", name), fileMapper));
    }
}
