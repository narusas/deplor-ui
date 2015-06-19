package net.narusas.tools.deplor.domain.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.apache.commons.lang3.StringUtils;

@Entity
@Table
@Getter
@Setter
@EqualsAndHashCode(exclude = { "branches", "accounts" })
@ToString(exclude = { "branches", "accounts" })
public class Repository {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long					id;

	@Column
	String					url;

	@Column
	String					name;

	@Column
	Long					headRevision;

	@Column
	String					workerId;

	@Column
	String					workerPassword;

	@OneToMany(mappedBy = "repository")
	@MapKeyColumn(name = "name", updatable = false, insertable = false)
	Map<String, Account>	accounts;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "repository")
	List<Branch>			branches;

	public Branch getBranche(String name) {
		if (branches == null || StringUtils.isEmpty(name)) {
			return null;
		}
		for (Branch branch : branches) {
			if (name.equals(branch.getName())) {
				return branch;
			}
		}
		return null;
	}

	public Account getAccount(String authorName) {
		if (accounts == null) {
			accounts = new HashMap<>();
		}

		return accounts.get(authorName);
	}

	public void addAccount(Account account) {
		accounts.put(account.getName(), account);
	}
}
