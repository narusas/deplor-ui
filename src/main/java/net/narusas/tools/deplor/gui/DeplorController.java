package net.narusas.tools.deplor.gui;



public interface DeplorController {


    void init();


    /**
     * set ui frame
     * 
     * @param frame
     */
    void setDeplorFrame(DeplorGUI frame);


    /**
     * process : login
     */
    boolean login();


    /**
     * process : initial repository list
     * 
     */
    void eventRepositoryList();


    /**
     * process : initial branch list
     * 
     */
    // void initBranchList();


    void eventBranchList();


    /**
     * process : initial revision list
     * 
     */
    void eventRevisionList();


    /**
     * process : set revision information & change list
     */
    void eventRevisionInfoText();



    /**
     * process : add request list
     */

    void eventAddRequestList();

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
