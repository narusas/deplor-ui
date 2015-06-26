package net.narusas.tools.deplor.domain.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import net.narusas.tools.deplor.domain.model.DeploymentStatus.DeploymentStatusConverter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "deploymentRequest")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString
public class DeploymentRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long				id;

	@JoinColumn(name = "branch")
	Branch				branch;

	@Column
	@Convert(converter = DeploymentStatusConverter.class)
	DeploymentStatus	status	= DeploymentStatus.미제출;

	@JoinColumn(name = "author")
	Account				author;

	@Column
	Date				timestamp;

	//@formatter:off
	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "deploymentRequest_changes", 
			joinColumns = { @JoinColumn(name = "deployId") }, 
			inverseJoinColumns = { @JoinColumn(name = "changeId") }
	)
	//@formatter:on
	Set<Change>			changes;

	@Column
	String				message;

	public void addChange(Change change) {
		if (changes == null) {
			changes = new HashSet<>();
		}
		changes.add(change);
	}

	public static DeploymentRequest from(DeploymentRequest old) {
		DeploymentRequest newRequest = new DeploymentRequest();
		newRequest.setAuthor(old.getAuthor());
		newRequest.setChanges(old.getChanges());

		return newRequest;
	}

}
