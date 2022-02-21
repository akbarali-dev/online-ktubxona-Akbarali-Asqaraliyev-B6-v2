package uz.pdp.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import uz.pdp.dto.*;

import java.lang.reflect.Type;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FileDownloadDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public List<FileDto> getAllLessons(UUID uuid) {
        String sqlQuery = "select *\n" +
                "from lesson_all_data('"+uuid+"');";
        List<FileDto> lessonDtoListFromDb = jdbcTemplate.query(sqlQuery, (rs, row) -> {
            FileDto fileDto = new FileDto();
            fileDto.setId(UUID.fromString(rs.getString(1)));
            fileDto.setFileName(rs.getString(2));
            fileDto.setFileType(rs.getString(3));
            return fileDto;
        });
        return lessonDtoListFromDb;
    }


    public LessonDto getLesson(UUID id) {
        String sqlQuery = "Select * from lessons where id='" + id + "'";
        return jdbcTemplate.queryForObject(sqlQuery, (rs, rowNum) -> {
            LessonDto lesson = new LessonDto();
            lesson.setId(UUID.fromString(rs.getString(1)));
            lesson.setTitle(rs.getString(2));
            return lesson;
        });
    }

}
