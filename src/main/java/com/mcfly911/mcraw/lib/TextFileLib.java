package com.mcfly911.mcraw.lib;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TextFileLib {

	public static void writeStringListToFile(List<String> stringList, String fileName) {
		try {
			FileWriter wf = new FileWriter(fileName, false);
			BufferedWriter bf = new BufferedWriter(wf);
			for (String string : stringList) {
				bf.write(string);
				bf.newLine();
			}
			bf.close();
			wf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<String> readLineByLine(String fileName) {
		System.out.println("Reading file:" + fileName);
		List<String> data = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String currentLine;
			while ((currentLine = br.readLine()) != null) {
				data.add(currentLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public static void write(String fileName, String text) {
		try {
			FileWriter wf = new FileWriter(fileName, false);
			BufferedWriter bf = new BufferedWriter(wf);
				bf.write(text);
			bf.close();
			wf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public synchronized static void log(String text) {
		writeLine("LOG.txt", text + "     <br>");
	}
	
	public static void writeLine(String fileName, String text) {
		try {
			FileWriter wf = new FileWriter(fileName, true);
			BufferedWriter bf = new BufferedWriter(wf);
				bf.write(text);
			bf.close();
			wf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String read(String fileName) {
		StringBuilder sb = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String currentLine;
			while ((currentLine = br.readLine()) != null) {
				sb.append(currentLine);
				sb.append("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

}
