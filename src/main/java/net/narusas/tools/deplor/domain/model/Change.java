package net.narusas.tools.deplor.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "changes")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class Change {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long		id;

	@JoinColumn(name = "REVISION")
	Revision	revision;

	@Column
	String		type;

	@Column
	String		path;

	@JoinColumn(name = "resource")
	Resource	resource;

	public Change(Resource resource, String path, String type) {
		this.resource = resource;
		this.path = path;
		this.type = type;
	}

}
