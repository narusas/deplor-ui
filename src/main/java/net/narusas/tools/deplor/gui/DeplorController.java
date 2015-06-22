package net.narusas.tools.deplor.gui;


import javax.swing.JComboBox;
import javax.swing.JList;

/**
 * 
 * @author jyan
 *
 */

public interface DeplorController {

    void setDeporFrame(DeporGUIFrame frame);


    void added();


    void removed();


    void imports();


    /**
     * Repository list
     * 
     * @param rComboBox
     */
    void initRepositoryList(JComboBox rComboBox);


    /**
     * Branch list
     * 
     * @param rComboBox
     */
    void initBranchList(JComboBox rComboBox);


    /**
     * Revision list
     * 
     * @param rList
     */
    void initRevisionList(JList rList);


    void revisionSelected();

}
