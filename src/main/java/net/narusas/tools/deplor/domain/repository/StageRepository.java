package net.narusas.tools.deplor.domain.repository;

import net.narusas.tools.deplor.domain.model.Stage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StageRepository extends JpaRepository<Stage, Long> {

}
