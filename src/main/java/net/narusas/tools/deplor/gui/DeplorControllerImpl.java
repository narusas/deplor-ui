package net.narusas.tools.deplor.gui;


import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;

import lombok.Getter;
import net.narusas.tools.deplor.domain.model.Branch;
import net.narusas.tools.deplor.domain.model.Repository;
import net.narusas.tools.deplor.domain.model.Revision;
import net.narusas.tools.deplor.domain.repository.BranchRepository;
import net.narusas.tools.deplor.domain.repository.RepoRepository;
import net.narusas.tools.deplor.domain.repository.RevisionRepository;

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


    @Override
    public void init() {

        this.repoListModel = new RepositoryListModel();
        ui.getRepositoryList().setModel(repoListModel);


        // initRepositoryList();
        // initBranchList();
        // initRevisionList();

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

        List<Repository> repositoryList = new ArrayList<>();


        private void updateRepository() {

            repositoryList = repoRepository.findAll();

            fireContentsChanged(this, 0, getSize());
        }


        @Override
        public int getSize() {

            return repositoryList.size();
        }


        @Override
        public Object getElementAt(int index) {

            return new RepositoryLabel(repositoryList.get(index));
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



    /**
     * process : initial branch list
     * 
     */
    @SuppressWarnings("unchecked")
    @Override
    public void initBranchList() {

        selectedRepository = (String) ui.getRepositoryList().getSelectedItem();

        final List<Branch> branchList = branchRepository.findByRepositoryName(selectedRepository);

        DefaultComboBoxModel<String> boxModel = new DefaultComboBoxModel<String>();
        boxModel.addElement("select");

        for (Branch branch : branchList) {
            boxModel.addElement(branch.getName());
        }
        ui.getBranchList().setModel(boxModel);


    }



    /**
     * process : initial revision list
     * 
     */
    @Override
    public void initRevisionList() {

        selectedBranch = (String) ui.getBranchList().getSelectedItem();
        System.out.println(" >> branch : " + selectedBranch);
        //
        // final List<Revision> revisionList = revisionRepository.findByBranch(selectedBranch);
        // DefaultListModel model = new DefaultListModel();
        // for (Revision revision : revisionList) {
        // model.addElement(revision);
        // }
        //


        // RevisionListModel aaa = new RevisionListModel(revisionRepository);
        // aaa.updateBranch(selectedBranch);
        //
        // ui.getRevisionList().setModel(aaa);
    }



    class RevisionListModel extends AbstractListModel {

        List<Revision> revision = new ArrayList<>();


        private void updateBranch(String branch) {

            revision = revisionRepository.findByBranch(branch);
            fireContentsChanged(this, 0, getSize());
        }


        @Override
        public int getSize() {

            return revision.size();
        }


        @Override
        public Object getElementAt(int index) {

            return revision.get(index);
        }

    }
    /**
     * process : search revision
     * 
     * 
     */



    /**
     * process : set revision information & change list
     */



    /**
     * process : add request list
     */



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
