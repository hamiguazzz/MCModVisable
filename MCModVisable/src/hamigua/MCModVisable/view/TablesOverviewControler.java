package hamigua.MCModVisable.view;

import hamigua.MCModVisable.Main;
import hamigua.MCModVisable.model.ModInfo;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TablesOverviewControler {
	@FXML
	private TableView<ModInfo> modTable;
	@FXML
	private TableColumn<ModInfo, String> idColumn;
	@FXML
	private TableColumn<ModInfo, String> NameColumn;
	@FXML
	private TableColumn<ModInfo, String> versionColumn;
	@FXML
	private TableColumn<ModInfo, String> fileNameColumn;
	@FXML
	private TableColumn<ModInfo, String> descriptionColumn;
	@FXML
	private TableColumn<ModInfo, String> forgeColumn;
	@FXML
	private TableColumn<ModInfo, String> urlColumn;
	@FXML
	private TableColumn<ModInfo, String> mcversionColumn;
	@FXML
	private TableColumn<ModInfo, String> updateColumn;

	// Reference to the main application.
	private Main mainApp;

	public TablesOverviewControler() {
	}

	@FXML
	public void initialize() {
		idColumn.setCellValueFactory(c -> c.getValue().getModid());
		NameColumn.setCellValueFactory(c -> c.getValue().getModName());
		versionColumn.setCellValueFactory(c -> c.getValue().getVersion());
		fileNameColumn.setCellValueFactory(c -> c.getValue().getFilename());
		forgeColumn.setCellValueFactory(c -> c.getValue().getForgeVersion());
		urlColumn.setCellValueFactory(c -> c.getValue().getUrl());
		updateColumn.setCellValueFactory(c -> c.getValue().getUpdateUrl());
		descriptionColumn.setCellValueFactory(c -> c.getValue().getDescription());
		mcversionColumn.setCellValueFactory(c -> c.getValue().getMcversion());
	}

	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
		this.modTable.setItems(mainApp.getModInfosData());
	}

}
