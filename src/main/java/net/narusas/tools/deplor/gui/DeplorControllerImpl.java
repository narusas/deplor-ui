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
    private BranchListModel branchListModel;
    private RevisionListModel revisionListModel;


    @Override
    public void init() {

        this.repoListModel = new RepositoryListModel();
        ui.getRepositoryList().setModel(repoListModel);

        this.branchListModel = new BranchListModel();
        ui.getBranchList().setModel(branchListModel);

        this.revisionListModel = new RevisionListModel();
        ui.getRevisionList().setModel(revisionListModel);

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
        String selectedRepository = String.valueOf(ui.getRepositoryList().getSelectedItem());
        branchListModel.updateBranch(selectedRepository);

    }


    /**
     * Repository List Model
     * 
     * @author ProBook
     *
     */
    class BranchListModel extends DefaultComboBoxModel {

        List<BranchLabel> branchList = new ArrayList<>();


        private void updateBranch(String selectedRepository) {

            branchList.clear();
            List<Branch> temp = branchRepository.findByRepositoryName(selectedRepository);
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

        Branch selectedBranch = (Branch) ui.getBranchList().getSelectedItem();

        revisionListModel.updateRevision(selectedBranch);



        System.out.println(" >> branch : " + selectedBranch);
        //
        // final List<Revision> revisionList = revisionRepository.findByBranch(selectedBranch);
        // DefaultListModel model = new DefaultListModel();
        // for (Revision revision : revisionList) {
        // model.addElement(revision);
        // }
        //


    }



    class RevisionListModel extends AbstractListModel {

        List<RevisionLabel> revisionList = new ArrayList<>();


        private void updateRevision(Branch selectedBranch) {

            revisionList.clear();
            List<Revision> temp = selectedBranch.getRevisions();
            for (Revision revision : temp) {

                revisionList.add(new RevisionLabel(revision));
            }
            fireContentsChanged(this, 0, getSize());
        }


        // Search Filter
        private void setFilter() {

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

            return "- " + revision.getVersion() + " : " + revision.getAuthor();
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

    /*** END >> Revision ************************************************************/

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
