package hamigua.MCModVisable.view;

import java.io.File;

import hamigua.MCModVisable.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.DirectoryChooser;

public class MainLayoutControler {
	static final String defaultPath = "E:\\MC";

	private Main main;

	public void setMain(Main main) {
		this.main = main;
	}

	@FXML
	private void handleOpen() {
		DirectoryChooser chooser = new DirectoryChooser();
		chooser.setInitialDirectory(new File(defaultPath));
		chooser.setTitle("ѡ��mod�ļ���");
		File path = chooser.showDialog(main.getPrimaryStage());
		main.changePath(path);
	}

	@FXML
	private void handleAbout() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("About");
		alert.setHeaderText("��������");
		alert.setContentText("���� : hamiguazzz");
		alert.show();
		System.out.println("xx");
	}
}
