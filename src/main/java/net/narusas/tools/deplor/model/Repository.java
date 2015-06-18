package net.narusas.tools.deplor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "repository")
@Data
public class Repository {
	@Id
	Long	id;

	@Column
	String	url;

	@Column
	String	name;

	@Column
	Long	headRevision;
}
