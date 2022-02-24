//package uz.pdp.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//import uz.pdp.dao.LoginDao;
//import uz.pdp.dto.UserDto;
//import uz.pdp.model.Role;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.util.List;
//import java.util.UUID;
//
//@Service
//@Component
//public class LoginService {
//    @Autowired
//    LoginDao loginDao;
//
//    public UserDto roles(String emailOrPhoneNumber, String password) {
//        return loginDao.getAllUserDtos(emailOrPhoneNumber, password);
//    }
//
//    public UUID sessionGetEmail(HttpServletRequest request, String user) {
//        HttpSession session1 = request.getSession();
//        if (session1 != null) {
//            String username = String.valueOf(session1.getAttribute("username"));
//            return UUID.fromString(loginDao.getUserId(username));
//        }
//        return null;
//    }
//}
