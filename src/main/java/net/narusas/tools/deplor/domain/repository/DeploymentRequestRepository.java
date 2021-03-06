package net.narusas.tools.deplor.domain.repository;

import java.util.List;

import net.narusas.tools.deplor.domain.model.Account;
import net.narusas.tools.deplor.domain.model.Branch;
import net.narusas.tools.deplor.domain.model.DeploymentRequest;
import net.narusas.tools.deplor.domain.model.DeploymentStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeploymentRequestRepository extends JpaRepository<DeploymentRequest, Long> {

	List<DeploymentRequest> findByAuthorOrderByTimestamp(Account account);

	List<DeploymentRequest> findByStatus(DeploymentStatus targetStatus);

	List<DeploymentRequest> findByBranch(Branch branch);
}
