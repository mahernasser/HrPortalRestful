
package com.llun.persistence.repository;

import com.llun.persistence.entity.User;
import com.llun.persistence.persistence_utils.TransactionUtil;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;

@ApplicationScoped
public class AuthRepo {

    public Optional<User> getUserByUsername(String username) {
        return TransactionUtil.doInTransaction(entityManager -> {
            List<User> users = entityManager
                    .createQuery("SELECT u FROM User u where u.username = :username", User.class)
                    .setParameter("username", username)
                    .getResultList();
            if (!users.isEmpty()) {
                return Optional.of(users.get(0));
            }
            return Optional.empty();
        });
    }

    public User createUser(User user) {
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);
        return TransactionUtil.doInTransaction(entityManager -> {
            entityManager.persist(user);
            return user;
        });
    }
}