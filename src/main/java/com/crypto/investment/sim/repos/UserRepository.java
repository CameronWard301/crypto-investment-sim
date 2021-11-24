package com.crypto.investment.sim.repos;

import com.crypto.investment.sim.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
