package net.narusas.tools.deplor.domain.repository;

import java.util.List;

import net.narusas.tools.deplor.domain.model.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface RepoRepository extends JpaRepository<Repository, Long> {
}
