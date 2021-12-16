package com.crypto.investment.sim.repos;

import com.crypto.investment.sim.model.PortfolioBalance;
import org.springframework.data.repository.CrudRepository;


public interface PortfolioHistoryRepository extends CrudRepository<PortfolioBalance, Integer> {
}
