package net.narusas.tools.deplor.domain.repository;


import net.narusas.tools.deplor.domain.model.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByName(String name);
}
