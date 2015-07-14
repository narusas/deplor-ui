package net.narusas.tools.deplor.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.eclipse.persistence.annotations.BatchFetch;
import org.eclipse.persistence.annotations.BatchFetchType;

@Entity
@Table(name = "stage")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = "branch")
@ToString(exclude = "branch")
public class Stage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long					id;

	@JoinColumn(name = "branch")
	Branch					branch;

	@Column
	String					name;

	@JoinColumn(name = "previous")
	Stage					previous;

	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinTable(name = "stage_deployResource", joinColumns = { @JoinColumn(name = "stage") }, inverseJoinColumns = { @JoinColumn(name = "deployResource") })
	List<DeployedResource>	resources	= new ArrayList<>();

	public void clearResources() {
		resources.clear();
	}

	public void add(DeployedResource deployedResource) {
		resources.add(deployedResource);
	}

}
