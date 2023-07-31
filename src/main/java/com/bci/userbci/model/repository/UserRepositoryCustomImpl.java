package com.bci.userbci.model.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import com.bci.userbci.model.entity.User;

import java.util.List;
import java.util.UUID;

public class UserRepositoryCustomImpl implements UserRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

        Root<User> users = criteriaQuery.from(User.class);
        criteriaQuery.select(users).where(criteriaBuilder.equal(users.get("isActive"), true));;

        try {
            return entityManager.createQuery(criteriaQuery).getResultList();
        } catch (NoResultException e) {
            return null; // Handle no result found case
        }
    }
    @Transactional
    @Override
    public User findByEmail(String email) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

        // Root of query (User)
        Root<User> userRoot = criteriaQuery.from(User.class);

        // Fetch the phones eagerly
        userRoot.fetch("phones", JoinType.INNER);

        // Conditions
        criteriaQuery.where(
                criteriaBuilder.and(
                        criteriaBuilder.equal(userRoot.get("isActive"), true),
                        criteriaBuilder.equal(userRoot.get("email"), email)
                )
        );

        // Select all users
        criteriaQuery.select(userRoot);

        // Execute the query
        User users = entityManager.createQuery(criteriaQuery).getSingleResult();

        try {
            return entityManager.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException e) {
            return null; // Handle no result found case
        }
    }

    @Override
    public User getUserById(UUID userId) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> users = criteriaQuery.from(User.class);

        // Conditions
        criteriaQuery.where(
                criteriaBuilder.and(
                        criteriaBuilder.equal(users.get("isActive"), true),
                        criteriaBuilder.equal(users.get("userId"), userId)
                )
        );

        try {
            return entityManager.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException e) {
            return null; // Handle no result found case
        }
    }
}
