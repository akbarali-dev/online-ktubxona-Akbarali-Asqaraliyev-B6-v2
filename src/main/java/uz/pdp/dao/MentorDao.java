package uz.pdp.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import uz.pdp.dto.CourseDto;
import uz.pdp.dto.ModuleDto;

import java.lang.reflect.Type;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class MentorDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public List<CourseDto> getOwnCourses(UUID uuid){
        String sql="select * from get_own_course('"+uuid+"')";
        List<CourseDto> courseDtoList=jdbcTemplate.query(sql,(rs, rowNum) -> {
            CourseDto courseDto=new CourseDto();
            courseDto.setId(UUID.fromString(rs.getString(1)));
            courseDto.setName(rs.getString(2));
            courseDto.setPrice(rs.getDouble(3));
            courseDto.setActive(rs.getBoolean(4));
            courseDto.setDescription(rs.getString(5));
            Array modules=rs.getArray(6);
            Type type=new TypeToken<ArrayList<ModuleDto>>(){}.getType();
            List<ModuleDto> moduleDtoList=new Gson().fromJson(modules.toString(),type);
            courseDto.setModule(moduleDtoList);
            return courseDto;
        });
        return courseDtoList;
    }
}
