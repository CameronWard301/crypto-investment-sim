package com.crypto.investment.sim.repos;

import com.crypto.investment.sim.model.Coin;
import org.springframework.data.repository.CrudRepository;

public interface CoinRepository extends CrudRepository<Coin, String> {
}
