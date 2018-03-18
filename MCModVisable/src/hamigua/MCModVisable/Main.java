package hamigua.MCModVisable;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import hamigua.MCModVisable.core.MCModControl;
import hamigua.MCModVisable.model.ModInfo;
import hamigua.MCModVisable.view.TablesOverviewControler;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
	private BorderPane mainLayout;
	private Stage primaryStage;
	private AnchorPane tablesOverview;
	private ObservableList<ModInfo> modInfos = FXCollections.observableArrayList();;
	private StringProperty path = new SimpleStringProperty("E:\\MC\\1.7.10MOD");

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;

		this.primaryStage.setTitle("Mods");

		initRootLayout();

		readMods();

		showTablesOverView();
	}

	public ObservableList<ModInfo> getModInfosData() {
		return modInfos;
	}

	private void readMods() {
		if (path == null) {
			return;
		} else {
			MCModControl controller = new MCModControl(path.get());
			HashMap<String, String> infoMap = controller.readAllInfo();
			for (Map.Entry<String, String> info : infoMap.entrySet()) {
				modInfos.add(new ModInfo(info));
			}
		}
	}

	private void showTablesOverView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/TablesOverView.fxml"));
			tablesOverview = (AnchorPane) loader.load();
			mainLayout.setCenter(tablesOverview);

			TablesOverviewControler tablesOverviewControler = loader.getController();
			tablesOverviewControler.setMainApp(this);
		} catch (IOException e) {
		}
	}

	public void initRootLayout() {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/MainLayout.fxml"));
			mainLayout = (BorderPane) loader.load();

			Scene scene = new Scene(mainLayout);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
