package ua.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    User findByEmail(String email);

    @Query("select u from User u where u.UUID =:uuid")
    User findByUUID(@Param("uuid") String uuid);
}
