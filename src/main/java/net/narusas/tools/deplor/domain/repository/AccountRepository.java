package net.narusas.tools.deplor.domain.repository;

import net.narusas.tools.deplor.domain.model.Account;

import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
