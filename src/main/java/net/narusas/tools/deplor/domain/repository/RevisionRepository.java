package net.narusas.tools.deplor.domain.repository;

import net.narusas.tools.deplor.domain.model.Revision;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RevisionRepository extends JpaRepository<Revision, Long> {

}
