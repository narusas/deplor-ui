package net.narusas.tools.deplor.gui;


import static net.narusas.tools.deplor.deploy.UIUtil.col;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumnModel;

import lombok.Getter;
import net.narusas.tools.deplor.domain.model.Branch;
import net.narusas.tools.deplor.domain.model.Change;
import net.narusas.tools.deplor.domain.model.Repository;
import net.narusas.tools.deplor.domain.model.Revision;
import net.narusas.tools.deplor.domain.repository.BranchRepository;
import net.narusas.tools.deplor.domain.repository.RepoRepository;
import net.narusas.tools.deplor.domain.repository.RevisionRepository;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;


public class DeplorControllerImpl implements DeplorController {

    @Autowired RepoRepository repoRepository;
    @Autowired BranchRepository branchRepository;
    @Autowired RevisionRepository revisionRepository;

    private DeplorGUI ui;
    private String loginId;
    private String loginPass;
    private boolean isLogin = false;
    private String selectedRepository;
    private String selectedBranch;


    private RepositoryListModel repoListModel;
    private BranchListModel branchListModel;
    private RevisionListModel revisionListModel;
    private ChangeListModel changeListModel;
    private RequestTableModel requestTableModel;


    @Override
    public void init() {

        System.out.println(" << init GUI >>");

        // initialize model
        this.repoListModel = new RepositoryListModel();
        ui.getRepositoryList().setModel(repoListModel);

        this.branchListModel = new BranchListModel();
        ui.getBranchList().setModel(branchListModel);

        this.revisionListModel = new RevisionListModel();
        ui.getRevisionList().setModel(revisionListModel);

        this.changeListModel = new ChangeListModel();
        ui.getChangeListTable().setModel(changeListModel);

        this.requestTableModel = new RequestTableModel();
        ui.getRequestListTable().setModel(requestTableModel);



        // Event Listener : revisions filter
        ui.getRevFilterKeyword().getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {

                revisionListModel.setFilter(ui.getRevFilterKeyword().getText());
            }


            @Override
            public void removeUpdate(DocumentEvent e) {

                revisionListModel.setFilter(ui.getRevFilterKeyword().getText());
            }


            @Override
            public void changedUpdate(DocumentEvent e) {

                revisionListModel.setFilter(ui.getRevFilterKeyword().getText());
            }

        });


        ui.getChangeListTable().setColumnModel(getChangeTableColumnModel());
        ui.getRequestListTable().setColumnModel(getRequestTableColumnModel());

    }


    @Override
    public void setDeplorFrame(DeplorGUI ui) {

        this.ui = ui;
    }


    /**
     * process : login
     */

    @Override
    public boolean login() {

        loginId = ui.getLoginId().getText();
        loginPass = String.valueOf(ui.getLoginPass().getPassword());

        // TODO : 로그인 비교 로직 넣을것
        isLogin = true;

        // unvisible after login
        ui.getLoginPanel().setVisible(false);

        return isLogin;
    }


    /**
     * process : initial repository list
     * 
     */
    @Override
    @SuppressWarnings("unchecked")
    public void eventRepositoryList() {

        repoListModel.updateRepository();

    }

    /**
     * Repository List Model
     * 
     * @author ProBook
     *
     */
    class RepositoryListModel extends DefaultComboBoxModel {

        List<RepositoryLabel> repositoryList = new ArrayList<>();


        private void updateRepository() {

            repositoryList.clear();
            List<Repository> temp = repoRepository.findAll();
            for (Repository repository : temp) {
                repositoryList.add(new RepositoryLabel(repository));
            }
            fireContentsChanged(this, 0, getSize());
        }


        @Override
        public int getSize() {

            return repositoryList.size();
        }


        @Override
        public Object getElementAt(int index) {

            return repositoryList.get(index);
        }



    }

    /**
     * toString 를 위한 Wrapping class
     * 
     * @author ProBook
     *
     */
    class RepositoryLabel {

        @Getter private Repository repository;


        public RepositoryLabel(Repository repository) {

            this.repository = repository;
        }


        @Override
        public String toString() {

            return repository.getName();
        }

    }



    /*** START >> Branch ***************************************************************/
    @Override
    public void eventBranchList() {

        // Parameter : Selected Respository
        RepositoryLabel selectedRepository = (RepositoryLabel) ui.getRepositoryList().getSelectedItem();

        branchListModel.updateBranch(selectedRepository.getRepository());

    }


    /**
     * Repository List Model
     * 
     * @author ProBook
     *
     */
    class BranchListModel extends DefaultComboBoxModel {

        List<BranchLabel> branchList = new ArrayList<>();


        private void updateBranch(Repository selectedRepository) {

            branchList.clear();
            List<Branch> temp = selectedRepository.getBranches();
            for (Branch branch : temp) {
                branchList.add(new BranchLabel(branch));

            }
            fireContentsChanged(this, 0, getSize());
        }


        @Override
        public int getSize() {

            return branchList.size();
        }


        @Override
        public Object getElementAt(int index) {

            return branchList.get(index);
        }



    }


    /**
     * toString 를 위한 Wrapping class
     * 
     * @author ProBook
     *
     */
    class BranchLabel {

        @Getter private Branch branch;


        public BranchLabel(Branch branch) {

            this.branch = branch;
        }


        @Override
        public String toString() {

            return branch.getName();
        }

    }


    /*** END >> Branch ***************************************************************/



    /*** START >> Revision ************************************************************/

    /**
     * process : initial revision list
     * 
     */
    @Override
    public void eventRevisionList() {

        BranchLabel branch = (BranchLabel) ui.getBranchList().getSelectedItem();
        revisionListModel.updateRevision(branch.getBranch());

    }


    class RevisionListModel extends AbstractListModel {

        List<RevisionLabel> revisionList = new ArrayList<>();
        List<Revision> filterList = new ArrayList<>();


        private void updateRevision(Branch selectedBranch) {


            filterList = selectedBranch.getRevisions();

            revisionList.clear();
            List<Revision> temp = selectedBranch.getRevisions();
            for (Revision revision : temp) {

                revisionList.add(new RevisionLabel(revision));
            }
            fireContentsChanged(this, 0, getSize());
        }


        // Search Filter
        private void setFilter(String keyword) {

            revisionList.clear();
            List<Revision> temp = filterList;
            for (Revision revision : temp) {
                if (revision.getAuthor().getName().contains(keyword) == true) {
                    revisionList.add(new RevisionLabel(revision));
                }
            }
            fireContentsChanged(this, 0, getSize());
        }


        @Override
        public int getSize() {

            return revisionList.size();
        }


        @Override
        public Object getElementAt(int index) {

            return revisionList.get(index);
        }

    }


    /**
     * toString 를 위한 Wrapping class
     * 
     * @author ProBook
     *
     */
    class RevisionLabel {

        @Getter private Revision revision;


        public RevisionLabel(Revision revision) {

            this.revision = revision;
        }


        @Override
        public String toString() {

            String isMessage = "";
            if (!StringUtils.isEmpty(revision.getMessage())) {
                isMessage = "(MSG)";
            }
            return "- " + revision.getVersion() + " : " + revision.getAuthor().getName() + " : " + isMessage;
        }
    }



    /**
     * process : set revision information & change list
     */
    @Override
    public void eventRevisionInfoText() {

        RevisionLabel revision = (RevisionLabel) ui.getRevisionList().getSelectedValue();

        String revTxt = revision.getRevision().getTimestamp() + "  /  " + revision.getRevision().getAuthor().getName();

        if (StringUtils.isNotBlank(revision.getRevision().getMessage())) {
            revTxt += "\n========<< MESSAGE >>===========\n" + revision.getRevision().getMessage();
        }

        ui.getInfoRevisionTextArea().setText(revTxt);


        changeListModel.updateChange(revision.getRevision());
    }


    private TableColumnModel getChangeTableColumnModel() {

        return new ChangeTableColumnModel();
    }

    @SuppressWarnings("serial")
    class ChangeTableColumnModel extends DefaultTableColumnModel {

        public ChangeTableColumnModel() {

            addColumn(col(0, 140, "Revision"));
            addColumn(col(1, 70, "Type"));
            addColumn(col(2, 70, "Owner"));
            addColumn(col(3, 140, "Branch"));
            addColumn(col(4, "Path"));
        }
    }

    class ChangeListModel extends AbstractTableModel {

        List<Change> changeList = new ArrayList<>();


        public Change getChangeAtRow(int selectedRow) {

            return changeList.get(selectedRow);
        }


        private void updateChange(Revision selectedRevision) {


            changeList.clear();
            List<Change> changes = selectedRevision.getChanges();
            changeList = new ArrayList<>(changes);
            fireTableDataChanged();
        }


        @Override
        public int getRowCount() {

            return changeList.size();
        }


        @Override
        public int getColumnCount() {

            return 5;
        }


        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {

            switch (columnIndex) {
                case 0:
                    return changeList.get(rowIndex).getRevision();
                case 1:
                    return changeList.get(rowIndex).getType();
                case 2:
                    return changeList.get(rowIndex).getRevision().getAuthor().getName();
                case 3:
                    return changeList.get(rowIndex).getRevision().getBranch().getName();
                case 4:
                    return changeList.get(rowIndex).getPath();

            }
            return null;
        }
    }



    /*** END >> Revision ************************************************************/



    /**
     * process : add request list
     */
    @Override
    public void eventAddRequestList() {


        this.requestTableModel.addChange(this.changeListModel.getChangeAtRow(ui.getChangeListTable().getSelectedRow()));

        // this.requestTableModel.addChange(changeListModel.getValueAt(rowIndex, columnIndex));


        System.out.println(" >>>>>> " + this.changeListModel.changeList);

        // getRequestTableModel().addChange(getChangedTableModel().getChangeAtRow(this.changeList.getSelectedRow()));


    }


    private TableColumnModel getRequestTableColumnModel() {

        return new RequestTableColumnModel();
    }

    @SuppressWarnings("serial")
    class RequestTableColumnModel extends DefaultTableColumnModel {

        public RequestTableColumnModel() {

            addColumn(col(0, 140, "Revision"));
            addColumn(col(1, 70, "Type"));
            addColumn(col(2, 70, "Owner"));
            addColumn(col(3, 140, "Branch"));
            addColumn(col(4, "Path"));
        }
    }

    public static class RequestTableModel extends AbstractTableModel {

        private ArrayList<Change> changes = new ArrayList<>();;


        public void setChanges(Set<Change> newData) {

            this.changes = new ArrayList<Change>(newData);
            fireTableDataChanged();
        }


        public void addChange(Change changeAtRow) {



            changes.add(changeAtRow);
            fireTableDataChanged();
        }


        public List<Change> getChangesAt(int[] selectedRows) {

            // TODO Auto-generated method stub
            return null;
        }


        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {

            if (changes == null || changes.size() == 0) {
                return null;
            }

            switch (columnIndex) {
                case 0:
                    return changes.get(rowIndex).getRevision();
                case 1:
                    return changes.get(rowIndex).getType();
                case 2:
                    return "Owner";
                case 3:
                    return changes.get(rowIndex).getId(); // branch
                case 4:
                    return changes.get(rowIndex).getPath();

            }

            return null;

        }


        @Override
        public int getRowCount() {

            return changes.size();
        }


        @Override
        public int getColumnCount() {

            return 5;
        }


        @Override
        public String getColumnName(int column) {

            switch (column) {
                case 0:
                    return "Revision";
                case 1:
                    return "Type";
                case 2:
                    return "Owner";
                case 3:
                    return "Path";
                case 4:
                    return "Branch";
            }
            return null;
        }


    }

    /**
     * process : submit deploy
     */


    /**
     * process : history list
     */


    /**
     * process : select history
     */



}
