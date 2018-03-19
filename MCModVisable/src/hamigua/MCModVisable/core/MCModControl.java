package hamigua.MCModVisable.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class MCModControl {

	static final String infoPath = "mcmod.info";

	public static final String defaultPath = "E:\\MC\\1.7.10MOD";

	private final String modPath;

	public MCModControl(String modPath) {
		this.modPath = modPath;
	}

	public MCModControl() {
		this.modPath = "E:\\MC\\1.7.10MOD";
	}

	public void saveInfo(String saveFileName) {
		HashMap<String, String> map = readAllInfo();
		if (saveFileName == null || saveFileName == "") {
			System.out.println("保存失败");
		}
		File file = new File(this.modPath + "\\out\\" + saveFileName);
		try {
			file.createNewFile();
			FileWriter writer = new FileWriter(file);
			for (Map.Entry<String, String> entry : map.entrySet()) {
				writer.write(entry.getKey() + "\n" + entry.getValue() + "\n\n");
			}
			writer.close();
		} catch (Exception e) {
			System.out.println("保存失败");
		}
	}

	public HashMap<String, String> readAllInfo() {
		HashMap<String, String> strings = new HashMap<String, String>();

		ArrayList<File> files = readAllModFile();

		for (File file : files) {
			InputStream inputStream = getJarFileInputStream(file.getPath());
			String string;
			try {
				string = inputStreamToString(inputStream);
			} catch (IOException e) {
				string = "";
				System.out.println("Jarfile Read error");
				e.printStackTrace();
			}
			if (string != null) {
				strings.put(file.getPath(), string);
			}
		}

		return strings;
	}

	public ArrayList<File> readAllModFile() {
		return getAllModFile(modPath);
	}

	public static ArrayList<File> getAllModFile(String modpath) {
		File path = new File(modpath);
		File[] files = path.listFiles();
		if (files == null) {
			if (path.isFile() && path.exists()) {
				ArrayList<File> reList = new ArrayList<>();
				reList.add(path);
				return reList;
			} else
				return null;
		}
		ArrayList<File> start = new ArrayList<>(Arrays.asList(files));
		ArrayList<File> reList = new ArrayList<>();
		for (File file : start) {
			if (file.isFile() && (file.getName().endsWith(".jar") || file.getName().endsWith(".jar.disable"))) {
				reList.add(file);
			} else if (file.isDirectory()) {
				reList.addAll(getAllModFile(file.getPath()));
			}
		}
		System.out.println("In" + modpath + "," + "SumModFileCount = " + reList.size());
		files = new File[] {};
		return reList;
	}

	private InputStream getJarFileInputStream(String jarname) {
		String resPath = infoPath;
		String jarUrl = jarname;
		if (!jarUrl.endsWith(".jar")) {
			System.out.println("不是jar包");
			return null;
		}
		URL url = null;

		try {
			url = new URL("jar:file:/" + jarUrl + "!/");
		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.out.println("url构造失败");
			return null;
		}

		try {
			JarURLConnection jarURLConnection;
			jarURLConnection = (JarURLConnection) url.openConnection();
			JarFile jarFile = jarURLConnection.getJarFile();
			JarEntry jarEntry = jarFile.getJarEntry(resPath);
			if (jarEntry == null) {
				return null;
			}
			return jarFile.getInputStream(jarEntry);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("获取文件失败");
			return null;
		}

	}

	private static String inputStreamToString(InputStream is) throws IOException {
		if (is == null) {
			return null;
		}
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		StringBuffer buffer = new StringBuffer();
		String line = "";
		while ((line = in.readLine()) != null) {
			buffer.append(line);
		}
		return buffer.toString();
	}

	public static void renameAllFile(String renameFloder) {
		Pattern pattern1 = Pattern.compile("^\\[(.*?)]");
		Pattern pattern2 = Pattern.compile("_");
		ArrayList<File> files = getAllModFile(renameFloder);
		int i = 0;
		for (File file : files) {
			if (pattern1.matcher(file.getName()).find()) {
				File tFile = new File(file.getParent(), pattern1.matcher(file.getName()).replaceFirst(""));
				file.renameTo(tFile);
				i++;
			}
			if (pattern2.matcher(file.getName()).find()) {
				File tFile = new File(file.getParent(), pattern2.matcher(file.getName()).replaceAll("-"));
				file.renameTo(tFile);
				i++;
			}
		}
		System.out.println("RenamedFileCount=" + i);
	}

	public static String readInfoString(String att, String oriString) {
		String format = "\"" + att + "\"( *):( *)\"(.*?)(\")";
		Pattern pattern = Pattern.compile(format);
		Matcher matcher = pattern.matcher(oriString);
		if (matcher.find()) {
			return matcher.group(3);
		} else {
			return null;
		}

	}

	public static HashMap<String, String> readInfoString(String att, HashMap<String, String> map) {
		HashMap<String, String> reMap = new HashMap<>();
		for (Entry<String, String> estring : map.entrySet()) {
			String stringFinded = readInfoString("name", estring.getValue());
			if (stringFinded == null) {
				System.out.println(estring.getKey() + "匹配失败");
			} else {
				reMap.put(estring.getKey(), stringFinded);
			}
		}
		return reMap;
	}

}
