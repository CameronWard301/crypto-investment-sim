package com.crypto.investment.sim.repos;

import com.crypto.investment.sim.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository <User,Integer>  {
    public List<User> findByUsername(String username);


}
