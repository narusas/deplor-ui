package net.narusas.tools.deplor.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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

import org.eclipse.persistence.annotations.BatchFetch;
import org.eclipse.persistence.annotations.BatchFetchType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "branches")
@Getter
@Setter
@EqualsAndHashCode(exclude = { "repository", "revisions" })
@ToString(exclude = { "repository", "revisions" })
public class Branch {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long			id;

	@Column
	String			name;

	@Column
	Long			lastRevision;

	@JoinColumn(name = "repository")
	Repository		repository;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "branch", cascade = { CascadeType.ALL })
	@OrderBy("timestamp")
	@BatchFetch(BatchFetchType.IN)
	List<Revision>	revisions;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "branch", cascade = { CascadeType.ALL })
	List<Stage>		stage;

	public void addRevision(Revision revision) {
		if (revisions == null) {
			revisions = new ArrayList<>();
		}
		revisions.add(revision);
		revision.setBranch(this);
	}

	public Stage getStage(String name) {
		for (Stage s : stage) {
			if (name.equals(s.getName())) {
				return s;
			}
		}
		Stage addstage = new Stage();
		addstage.setName(name);
		addstage.setBranch(this);
		stage.add(addstage);
		return addstage;
	}
}
