package net.narusas.tools.deplor.domain.repository;

import net.narusas.tools.deplor.domain.model.Branch;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {
}