package net.narusas.tools.deplor.gui;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import net.narusas.tools.deplor.domain.model.Branch;
import net.narusas.tools.deplor.domain.model.Change;
import net.narusas.tools.deplor.domain.model.Repository;
import net.narusas.tools.deplor.domain.model.Revision;
import net.narusas.tools.deplor.domain.repository.BranchRepository;
import net.narusas.tools.deplor.domain.repository.RepoRepository;
import net.narusas.tools.deplor.domain.repository.RevisionRepository;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;


public class DeplorControllerImpl implements DeplorController, InitializingBean {

    @Autowired RepoRepository repoRepository;
    @Autowired BranchRepository branchRepository;
    @Autowired RevisionRepository revisionRepository;

    private DeporGUIFrame ui;
    private JList revisionList;
    private JTable changeList;
    private JTable requestList;
    private RequestTableModel requestTableModel;
    private ChangesTableModel changesTableModel;



    @Override
    public void setDeporFrame(DeporGUIFrame ui) {

        this.ui = ui;
    }


    @Override
    public void added() {

        // TODO Auto-generated method stub

    }


    @Override
    public void removed() {

        // TODO Auto-generated method stub

    }


    @Override
    public void imports() {

        // TODO Auto-generated method stub

    }



    @Override
    public void afterPropertiesSet() throws Exception {

        System.out.println("Initialized");
    }



    @Override
    public void initRepositoryList(JComboBox rComboBox) {

        final List<Repository> repos = repoRepository.findAll();

        DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();
        for (Repository re : repos) {
            comboBoxModel.addElement(re.getName());
        }

        rComboBox.setModel(comboBoxModel);

    }


    @Override
    public void initBranchList(JComboBox rComboBox) {

        final List<Branch> repos = branchRepository.findAll();

        DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();
        for (Branch re : repos) {

            comboBoxModel.addElement(re.getName());
        }
        rComboBox.setModel(comboBoxModel);
    }



    @Override
    public void initRevisionList(JList rList) {

        this.revisionList = rList;


        final List<Revision> repos = revisionRepository.findAll();


        rList.setModel(new AbstractListModel() {

            @Override
            public int getSize() {

                return repos.size();
            }


            @Override
            public Object getElementAt(int index) {

                return repos.get(index);// .getVersion() + " ( " + repos.get(index).getAuthor().getName() + " ) ";
            }
        });


    }


    @Override
    public void revisionSelected() {

        Object obj = revisionList.getSelectedValue();
        if (obj == null) {
            return;
        }
        Revision r = (Revision) obj;

        // SET time , owner , comment
        ui.getRevDetailOwner().setText(r.getAuthor().getName());
        ui.getRevDetailTime().setText(r.getTimestamp().toString());
        ui.getRevInfoText().setText(r.getMessage());

        // SET Revision's Change List
        // getChangedTableModel().updateChanges(r.getChanges());

        ui.getChangesListTable().getColumnModel().getColumn(0).setMaxWidth(80);

    }

    public static class ChangesTableModel extends AbstractTableModel {

        List<Change> list = new ArrayList<>();


        public void updateChanges(Set<Change> changes) {

            list = new ArrayList<>(changes);
            fireTableDataChanged();
        }


        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {

            switch (columnIndex) {
                case 0:
                    String rvl = "";
                    switch (list.get(rowIndex).getType()) {
                        case "A":
                            rvl = "Add";
                            break;
                        case "M":
                            rvl = "Modify";
                            break;
                        case "D":
                            rvl = "Delete";
                            break;

                    }

                    return rvl;
                case 1:
                    return list.get(rowIndex).getPath();

            }

            return null;

        }


        @Override
        public int getRowCount() {

            return list.size();
        }


        @Override
        public int getColumnCount() {

            return 2;
        }


        @Override
        public String getColumnName(int column) {

            switch (column) {
                case 0:
                    return "Type";
                case 1:
                    return "Path";

            }
            return null;
        }


        public Change getChangeAtRow(int selectedRow) {

            return list.get(selectedRow);
        }
    }



    public ChangesTableModel getChangedTableModel() {

        if (changesTableModel != null) {
            return changesTableModel;
        }
        changesTableModel = new ChangesTableModel();

        return changesTableModel;

    }


    @Override
    public void initRequestList(JTable reqtListTable) {

        this.requestList = reqtListTable;
        this.requestList.setModel(getRequestTableModel());

    }


    @Override
    public void initChangeList(JTable revListTable) {

        this.changeList = revListTable;
        this.changeList.setModel(getChangedTableModel());
    }


    RequestTableModel getRequestTableModel() {

        if (requestTableModel != null) {
            return requestTableModel;
        }
        requestTableModel = new RequestTableModel();
        return requestTableModel;
    }



    @Override
    public void addSelectedItem() {

        System.out.println(" >>>>>> " + this.changeList.getSelectedRow());
        System.out.println(getChangedTableModel().getChangeAtRow(this.changeList.getSelectedRow()));
        getRequestTableModel().addChange(getChangedTableModel().getChangeAtRow(this.changeList.getSelectedRow()));
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
                    return changes.get(rowIndex).getPath();
                case 4:
                    return changes.get(rowIndex).getId(); // branch

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


    @Override
    public void removeRequestItems() {

        System.out.println(" >> Remove Click");
    }

}
