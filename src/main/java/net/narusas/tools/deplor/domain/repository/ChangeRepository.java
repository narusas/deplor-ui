package net.narusas.tools.deplor.domain.repository;

import net.narusas.tools.deplor.domain.model.Change;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChangeRepository extends JpaRepository<Change, Long> {
}
