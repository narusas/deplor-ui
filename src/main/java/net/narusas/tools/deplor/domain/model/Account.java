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
@Table(name = "accounts")
@Getter
@Setter
@EqualsAndHashCode(exclude = { "repository" })
@ToString(exclude = { "repository" })
@NoArgsConstructor
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long		id;

	@JoinColumn(name = "repository")
	Repository	repository;

	@Column
	String		name;

	public Account(Repository repository, String authorName) {
		this.repository = repository;
		name = authorName;
	}
}
