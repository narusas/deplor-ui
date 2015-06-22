package net.narusas.tools.deplor.gui;


import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JList;

import net.narusas.tools.deplor.domain.model.Branch;
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


        final List<Revision> repos = revisionRepository.findAll();


        rList.setModel(new AbstractListModel() {

            @Override
            public int getSize() {

                return repos.size();
            }


            @Override
            public Object getElementAt(int index) {

                return repos.get(index).getVersion() + " ( " + repos.get(index).getAuthor().getName() + " ) ";
            }
        });


    }

}
