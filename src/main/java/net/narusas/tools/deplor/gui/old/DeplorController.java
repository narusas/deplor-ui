package net.narusas.tools.deplor.gui.old;


import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTable;

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


    void initChangeList(JTable revListTable);


    /**
     * Request Table list
     */
    void initRequestList(JTable reqtListTable);


    /**
     * Selected Revision
     */
    void revisionSelected();


    /**
     * Add selected items
     */
    void addSelectedItem();


    void removeRequestItems();

}
