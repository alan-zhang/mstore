package org.azt.mstore.utility;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class IOUtil {

	public static String read(String filePath) throws IOException {
		BufferedReader input = new BufferedReader(new FileReader(filePath));
		String line = null;
		StringBuffer sb = new StringBuffer();
		while ((line = input.readLine()) != null) {
			sb.append(line);
			sb.append(System.getProperty("line.separator"));
		}

		input.close();
		return sb.toString();
	}

	public static String readFromInuptStream(InputStream inputStream) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
		StringBuffer buffer = new StringBuffer();
		String line = "";
		while ((line = in.readLine()) != null) {
			buffer.append(line);
		}
		return buffer.toString();
	}

	public static String readEncoding(String path, String encode) throws IOException {
		InputStream r = new FileInputStream(path);
		ByteArrayOutputStream byteout = new ByteArrayOutputStream();
		byte tmp[] = new byte[256];
		byte context[];
		int i = 0;
		while ((i = r.read(tmp)) > 0) {
			byteout.write(tmp, 0, i);
		}
		context = byteout.toByteArray();
		String str = new String(context, encode);
		r.close();
		byteout.close();
		return str;
	}

	public static void write(String content, String filePath) throws IOException {
		File file = new File(filePath);
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter writer = new FileWriter(file);
		writer.write(content);
		writer.flush();
		writer.close();
	}

}
