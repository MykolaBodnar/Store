package ua.service;

import ua.dto.ResetPasswordForm;
import ua.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findOne(int id);

    void delete(int id);

    void save(User user);

    void confirm(String uuid);

    void forgotPassword(String email);

    void resetPassword(ResetPasswordForm resetForm);

    User findOne(String email);
}
