package hamigua.MCModVisable.model;

import java.io.File;
import java.util.Map;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ModInfo {
	private final StringProperty modid;
	private final StringProperty modName;
	private final StringProperty version;
	private final StringProperty forgeVersion;
	private final StringProperty description;
	private final StringProperty url;
	private final StringProperty updateUrl;
	private final StringProperty mcversion;
	private final StringProperty filename;
	private final ObjectProperty<File> file;

	public StringProperty getModid() {
		return modid;
	}

	public ModInfo(Map.Entry<String, String> entry) {
		this(ModInfoReader.readModID(entry.getValue()), ModInfoReader.readModName(entry.getValue()),
				ModInfoReader.readModVersion(entry.getValue()), ModInfoReader.readModForgeVersion(entry.getValue()),
				ModInfoReader.readModDescription(entry.getValue()), ModInfoReader.readModURL(entry.getValue()),
				ModInfoReader.readModUpdateUrl(entry.getValue()), ModInfoReader.readModMCVersion(entry.getValue()),
				new File(entry.getKey()));
	}

	public ModInfo(String modid, String modName, String version, String forgeVersion, String description, String url,
			String updateUrl, String mcversion, File file) {
		this.modid = new SimpleStringProperty(modid);
		this.modName = new SimpleStringProperty(modName);
		this.version = new SimpleStringProperty(version);
		this.forgeVersion = new SimpleStringProperty(forgeVersion);
		this.description = new SimpleStringProperty(description);
		this.url = new SimpleStringProperty(url);
		this.updateUrl = new SimpleStringProperty(updateUrl);
		this.mcversion = new SimpleStringProperty(mcversion);
		this.file = new SimpleObjectProperty<File>(file);
		this.filename = new SimpleStringProperty(file.getName());
	}

	public StringProperty getMcversion() {
		return mcversion;
	}

	public StringProperty getFilename() {
		return filename;
	}

	public StringProperty getModName() {
		return modName;
	}

	public StringProperty getVersion() {
		return version;
	}

	public StringProperty getForgeVersion() {
		return forgeVersion;
	}

	public StringProperty getDescription() {
		return description;
	}

	public StringProperty getUrl() {
		return url;
	}

	public StringProperty getUpdateUrl() {
		return updateUrl;
	}

	public ObjectProperty<File> getFile() {
		return file;
	}
}
