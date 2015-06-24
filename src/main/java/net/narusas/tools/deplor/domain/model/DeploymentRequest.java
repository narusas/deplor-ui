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
@Table(name = "deploymentRequest")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class DeploymentRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long		id;

	@JoinColumn(name = "author")
	Account		author;

	//@formatter:off
	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "deploymentRequest_changes", 
			joinColumns = { @JoinColumn(name = "deployId") }, 
			inverseJoinColumns = { @JoinColumn(name = "changeId") }
	)
	//@formatter:on
	Set<Change>	changes;

	@Column
	Date		timestamp;

	@Column
	String		message;

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
