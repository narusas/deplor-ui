package net.narusas.tools.deplor.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.eclipse.persistence.annotations.BatchFetch;
import org.eclipse.persistence.annotations.BatchFetchType;
import org.tmatesoft.svn.core.SVNLogEntry;

@Entity
@Table(name = "revisions")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = { "changes" })
// @ToString(exclude = { "changes" })
public class Revision {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long			id;

	@JoinColumn(name = "branch")
	Branch			branch;

	@Column
	Long			version;

	@Column
	Date			timestamp;

	@JoinColumn(name = "author")
	Account			author;

	@Column
	String			message;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "revision")
	@OrderBy("path")
	@BatchFetch(BatchFetchType.IN)
	List<Change>	changes;

	public Revision(Branch branch, long version, Date timestamp, Account author, String message) {

		this.branch = branch;
		this.version = version;
		this.timestamp = timestamp;
		this.author = author;
		this.message = message;
	}

	public void addChange(Change change) {

		if (changes == null) {
			changes = new ArrayList<>();
		}
		changes.add(change);
		change.setRevision(this);
	}

	public static Revision from(Branch branch, SVNLogEntry svnLogEntry, Account account) {

		return new Revision(branch, svnLogEntry.getRevision(), svnLogEntry.getDate(), account, svnLogEntry.getMessage());
	}

	@Override
	public String toString() {

		return getVersion() + " ( " + getAuthor().getName() + " ) ";
	}

}
