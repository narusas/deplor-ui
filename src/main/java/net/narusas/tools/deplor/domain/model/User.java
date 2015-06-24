package net.narusas.tools.deplor.domain.model;

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
@Table(name = "users")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long			id;

	@Column
	String			name;

	//@formatter:off
		@OneToMany(fetch = FetchType.LAZY)
		@JoinTable(
				name = "users_accounts", 
				joinColumns = { @JoinColumn(name = "userId") }, 
				inverseJoinColumns = { @JoinColumn(name = "accountId") }
		)
		//@formatter:on
	Set<Account>	accounts;

}
