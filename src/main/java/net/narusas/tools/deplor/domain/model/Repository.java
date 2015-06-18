package net.narusas.tools.deplor.domain.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "repository")
@Getter
@Setter
@EqualsAndHashCode
@ToString(exclude = { "branches" })
public class Repository {
	@Id
	Long			id;

	@Column
	String			url;

	@Column
	String			name;

	@Column
	Long			headRevision;

	@Column
	String			workerId;

	@Column
	String			workerPassword;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "repository")
	List<Branch>	branches;
}
