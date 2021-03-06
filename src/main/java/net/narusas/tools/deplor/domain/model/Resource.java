package net.narusas.tools.deplor.domain.model;

import javax.persistence.Column;
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
@Table(name = "resources")
@Getter
@Setter
@EqualsAndHashCode(exclude = { "repository" })
@ToString(exclude = { "repository" })
@NoArgsConstructor
public class Resource {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long		id;

	@JoinColumn(name = "repository")
	Repository	repository;

	@JoinColumn(name = "branch")
	Branch		branch;

	@JoinColumn(name = "latestChange")
	Change		latestChange;

	@Column
	String		type;

	@Column
	String		path;

	public Resource(Repository repo, Branch branch, String shortPath) {
		this.repository = repo;
		this.branch = branch;
		this.path = shortPath;
	}

}
