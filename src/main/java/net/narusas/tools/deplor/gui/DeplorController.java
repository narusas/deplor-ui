package net.narusas.tools.deplor.gui;


import org.springframework.stereotype.Controller;

/**
 * 
 * @author jyan
 *
 */
@Controller
public class DeplorController {

    // @Autowired

    /**
     * Get repository list
     * 
     * @return
     */
    public String getRepositoryList() {

        return "";
    }


    /**
     * Get branch list
     * 
     * @param repoId
     *        : repository ID
     * @return
     */
    public String getBranchList(String repoId) {

        return "";
    }


    /**
     * Get revision list
     * 
     * @param branchId
     * @return
     */
    public String getRevisionList(String branchId) {

        return "";
    }


    /**
     * Get change list
     * 
     * @param revId
     * @return
     */
    public String getChangeList(String revId) {

        return "";
    }


    /**
     * Get history
     * 
     * @param revId
     * @return
     */
    public String getRequestHistoryList(String revId) {

        return "";
    }


    /**
     * Save requested deploy
     */
    public void saveRequestDeploy() {

    }


}
