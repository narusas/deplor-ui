package net.narusas.tools.deplor.domain.model;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.tmatesoft.svn.core.SVNLogEntry;

@Entity
@Table(name = "revisions")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"changes"})
// @ToString(exclude = { "changes" })
public class Revision {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

    @JoinColumn(name = "branch") Branch branch;

    @Column Long version;

    @Column Date timestamp;

    @JoinColumn(name = "author") Account author;

    @Column String message;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "revision") Set<Change> changes;


    public Revision(long version, Date timestamp, Account author, String message) {

        this.version = version;
        this.timestamp = timestamp;
        this.author = author;
        this.message = message;
    }


    public void addChange(Change change) {

        if (changes == null) {
            changes = new HashSet<>();
        }
        changes.add(change);
        change.setRevision(this);
    }


    public static Revision from(SVNLogEntry svnLogEntry, Account account) {

        return new Revision(svnLogEntry.getRevision(), svnLogEntry.getDate(), account, svnLogEntry.getMessage());
    }


    @Override
    public String toString() {

        return getVersion() + " ( " + getAuthor().getName() + " ) ";
    }

}
