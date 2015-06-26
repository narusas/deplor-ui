package net.narusas.tools.deplor.domain.repository;


import net.narusas.tools.deplor.domain.model.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface RepoRepository extends JpaRepository<Repository, Long> {

    Repository findByName(String name);
}
