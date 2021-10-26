package com.cts.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.auth.model.UserRetail;
/**
 * @author POD4
 * @version 1.8
 * @apiNote This is an interface which is used to communicate with the database.
 *          Whatever the data we want to fetch from database related to
 *          {@link MyUser} we can use this interface for this purpose. This will
 *          extend {@link JpaRepository} which is a JPA specific extension of
 *          Repository. It contains the full API of CrudRepository.So it
 *          contains API for basic CRUD operations and also API for pagination
 *          and sorting.
 *
 * @see JpaRepository
 */
@Repository
public interface UserDao extends JpaRepository<UserRetail, String>{

}
