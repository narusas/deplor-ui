package net.narusas.tools.deplor.domain.repository;


import java.util.List;

import net.narusas.tools.deplor.domain.model.Branch;
import net.narusas.tools.deplor.domain.model.Revision;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RevisionRepository extends JpaRepository<Revision, Long> {

    Revision findOneByBranchAndVersion(Branch branch, long version);


    List<Revision> findByBranch(String selectedBranch);

}
