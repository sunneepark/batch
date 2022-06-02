package com.sunny.batch.accessingdatamysql;

import org.springframework.data.repository.CrudRepository;
import com.sunny.batch.accessingdatamysql.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Integer> {

}