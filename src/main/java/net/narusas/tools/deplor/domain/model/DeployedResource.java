package net.narusas.tools.deplor.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "deployedResource")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class DeployedResource {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long		id;

	@JoinColumn(name = "branch")
	Branch		branch;

	@JoinColumn(name = "resource")
	Resource	resource;

	@JoinColumn(name = "change_id")
	Change		change;

	public DeployedResource(Branch branch, Resource resource, Change change) {
		this.branch = branch;
		this.resource = resource;
		this.change = change;
	}

}
