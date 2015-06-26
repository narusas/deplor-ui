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
    void initBranchList();


    /**
     * process : initial revision list
     * 
     */
    void initRevisionList();



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
