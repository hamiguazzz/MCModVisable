package hamigua.MCModVisable.model;

import hamigua.MCModVisable.core.MCModControl;

public final class ModInfoReader {
	private ModInfoReader() {
	}

	private final static String notFound = "NULL";

	public static String readModID(String content) {
		String string = MCModControl.readInfoString("modid", content);
		if (string == null) {
			return notFound;
		}
		return string;
	}

	public static String readModName(String content) {
		String string = MCModControl.readInfoString("name", content);
		if (string == null) {
			return notFound;
		}
		return string;
	}

	public static String readModVersion(String content) {
		String string = MCModControl.readInfoString("version", content);
		if (string == null) {
			return notFound;
		}
		return string;
	}

	public static String readModForgeVersion(String content) {
		String string = MCModControl.readInfoString("Forge", content);
		if (string == null) {
			return notFound;
		}
		return string;
	}

	public static String readModDescription(String content) {
		String string = MCModControl.readInfoString("description", content);
		if (string == null) {
			return notFound;
		}
		return string;
	}

	public static String readModUpdateUrl(String content) {
		String string = MCModControl.readInfoString("udateurl", content);
		if (string == null) {
			return notFound;
		}
		return string;
	}

	public static String readModURL(String content) {
		String string = MCModControl.readInfoString("url", content);
		if (string == null) {
			return notFound;
		}
		return string;
	}

	public static String readModMCVersion(String content) {
		String string = MCModControl.readInfoString("mcversion", content);
		if (string == null) {
			return notFound;
		}
		return string;
	}

}
