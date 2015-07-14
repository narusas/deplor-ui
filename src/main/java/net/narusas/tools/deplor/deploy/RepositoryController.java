package net.narusas.tools.deplor.deploy;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingUtilities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.narusas.tools.deplor.domain.model.Branch;
import net.narusas.tools.deplor.domain.model.Repository;
import net.narusas.tools.deplor.domain.repository.RepoRepository;
import net.narusas.tools.deplor.domain.service.LsbService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class RepositoryController {
	@Autowired
	RepoRepository					repoRepository;

	@Autowired
	LsbService						lsbService;

	private UI						ui;
	private RepositoryComboBoxModel	repositoryModel;

	private BrancheComboBoxModel	branchModel;
	private MainController			parent;

	private Branch	selectedBranch;

	public void setUI(UI ui) {
		this.ui = ui;
	}

	public void init(MainController parent) {
		this.parent = parent;
		ui.getRepositoryComboBox().setModel(getRepositoryModel());
		getRepositoryModel().refresh();
		ui.getBranchComboBox().setModel(getBranchModel());

	}

	private BrancheComboBoxModel getBranchModel() {
		if (branchModel != null) {
			return branchModel;
		}
		branchModel = new BrancheComboBoxModel();
		return branchModel;
	}

	private RepositoryComboBoxModel getRepositoryModel() {
		if (repositoryModel == null) {
			repositoryModel = new RepositoryComboBoxModel();
		}
		return repositoryModel;
	}

	@SuppressWarnings("serial")
	class BrancheComboBoxModel extends DefaultComboBoxModel {

		private Repository		repository;
		private List<Branch>	branches;

		public void update(RepositoryLabel repositoryLabel) {
			this.repository = repositoryLabel.getRepository();
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					removeAllElements();
					branches = repository.getBranches();
					for (Branch branch : branches) {
						addElement(new BranchLabel(branch));
					}
				}
			});
		}

	}

	@RequiredArgsConstructor
	class BranchLabel {
		@Getter
		final Branch	branch;

		@Override
		public String toString() {
			return branch.getName();
		}
	}

	@SuppressWarnings("serial")
	class RepositoryComboBoxModel extends DefaultComboBoxModel {
		private List<Repository>	repositories;

		public void refresh() {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					removeAllElements();
					repositories = repoRepository.findAll();
					for (Repository repository : repositories) {
						addElement(new RepositoryLabel(repository));
					}
				}
			});
		}

		public Repository getRepositoryAt(int index) {
			return repositories.get(index);
		}
	}

	@RequiredArgsConstructor
	class RepositoryLabel {
		@Getter
		final Repository	repository;

		@Override
		public String toString() {
			return repository.getName();
		}
	}

	public void 저장소_선택됨() {
		getBranchModel().update((RepositoryLabel) ui.getRepositoryComboBox().getSelectedItem());
	}

	public void 브랜치_선택됨() {
		BranchLabel branchLabel = (BranchLabel) ui.getBranchComboBox().getSelectedItem();
		if (branchLabel == null) {
			parent.선택된_브랜치로_변경(null);
			return;
		}
		this.selectedBranch = branchLabel.getBranch();
		parent.선택된_브랜치로_변경(branchLabel.getBranch());
	}

	public void LSB_신규생성() {
		log.info("LSB_신규생성");
		lsbService.LSB_신규생성(selectedBranch);
		
	}

}
