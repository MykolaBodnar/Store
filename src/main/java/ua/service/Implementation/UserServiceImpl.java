package ua.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.dao.UserDao;
import ua.dto.ResetPasswordForm;
import ua.entity.Role;
import ua.entity.User;
import ua.service.MailSender;
import ua.service.UserService;

import java.util.List;
import java.util.UUID;

@Service("userDetailsService")
@Transactional
public class UserServiceImpl implements UserService ,UserDetailsService{

    @Autowired
    UserDao userDao;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    MailSender mailSender;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findOne(int id) {
        return userDao.findOne(id);
    }

    @Override
    public void delete(int id) {
        userDao.delete(id);
    }

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        String uuid = UUID.randomUUID().toString();
        user.setUUID(uuid);
        userDao.save(user);
        mailSender.sendMail("MyStore registration",user.getEmail(),"http://localhost:8080/confirm/" + uuid);
    }

    @Override
    public void confirm(String uuid) {
        User user = userDao.findByUUID(uuid);
        user.setEnabled(true);
        userDao.save(user);
    }

    @Override
    public void forgotPassword(String email) {
        User user = userDao.findByEmail(email);
        if(user!=null){
            mailSender.sendMail("MyStore reset password",email,"http://localhost:8080/reset-password/" + user.getUUID());
        }
    }

    @Override
    public void resetPassword(ResetPasswordForm resetForm) {
        User user = userDao.findByUUID(resetForm.getUuid());
        user.setPassword(bCryptPasswordEncoder.encode(resetForm.getPassword()));
        userDao.save(user);
    }

    @Override
    public User findOne(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDao.findByEmail(email);
        return user;
    }
}
