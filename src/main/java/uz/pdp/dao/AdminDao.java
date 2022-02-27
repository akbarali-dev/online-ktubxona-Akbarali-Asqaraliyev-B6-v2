package uz.pdp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import uz.pdp.dto.CourseDto;
import uz.pdp.dto.MentorCourseDto;
import uz.pdp.dto.UserDto;

import java.util.List;
import java.util.UUID;

public class AdminDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<UserDto> getAllMessage() {

        String sqlQuery = "select u.id, u.\"firstName\", u.\"lastName\", c.id\n" +
                "from users u\n" +
                "         join admins_mentors_requests_courses c on u.id = c.user_id\n" +
                "group by u.id, c.id;\n";

//        String sqlQuery = "select c.id, c.name, c.status, c.is_active from courses c\n" +
//                " join modules m on c.id = m.course_id group by c.id ";

        List<UserDto> userDtoListFromDb = jdbcTemplate.query(sqlQuery, (rs, row) -> {
            UserDto userDto = new UserDto();
            userDto.setId(UUID.fromString(rs.getString(1)));
            userDto.setFirstName(rs.getString(2));
            userDto.setLastName(rs.getString(3));
            userDto.setMessageId(UUID.fromString(rs.getString(4)));



            return userDto;
        });
        return userDtoListFromDb;
    }

    public List<UserDto> getMessageById(UUID messageId) {
        String sqlQuery = "select user_id, description, course_id, u.\"lastName\", u.\"firstName\"\n" +
                "from admins_mentors_requests_courses a\n" +
                "join users u on a.user_id = u.id\n" +
                "where user_id = '"+messageId+"';";

//        String sqlQuery = "select c.id, c.name, c.status, c.is_active from courses c\n" +
//                " join modules m on c.id = m.course_id group by c.id ";

        List<UserDto> userDtoListFromDb = jdbcTemplate.query(sqlQuery, (rs, row) -> {
            UserDto userDto = new UserDto();
            userDto.setId(UUID.fromString(rs.getString(1)));
            userDto.setMessage(rs.getString(2));
            userDto.setCourseId(UUID.fromString(rs.getString(3)));
            userDto.setLastName(rs.getString(4));
            userDto.setFirstName(rs.getString(5));
            return userDto;
        });
        return userDtoListFromDb;
    }
}
