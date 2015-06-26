package net.narusas.tools.deplor.domain.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class DeploySet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long					id;

	@Column
	Date					timestamp;

	//@formatter:off
	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "deployset_requests", 
			joinColumns = { @JoinColumn(name = "deploySetId") }, 
			inverseJoinColumns = { @JoinColumn(name = "requestId") }
	)
	//@formatter:on
	Set<DeploymentRequest>	requests;

	public void addRequest(DeploymentRequest deploymentRequest) {
		if (requests == null) {
			requests = new HashSet<>();
		}
		requests.add(deploymentRequest);
	}

	public Set<Change> getAllChanges() {
		Set<Change> changes = new HashSet<>();

		if (requests == null) {
			return changes;
		}

		for (DeploymentRequest request : requests) {
			changes.addAll(request.getChanges());
		}

		return changes;
	}
}
