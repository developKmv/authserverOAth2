package ru.devmvx.authserver.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.devmvx.authserver.model.User;

@Slf4j
@Repository
@Data
@NoArgsConstructor
public class UserRepoImpl implements UserRepo{

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public User getUserByUsername(String username) {
        User user = null;
        try {
            user = entityManager.createQuery("from USER_KEBAB_CLOUD where username = :paramName", User.class)
                    .setParameter("paramName", username).getResultList().get(0);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        return user;
    }
}
