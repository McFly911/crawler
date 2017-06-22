package com.mcfly911.mcraw.lib;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class FileLib {

	private static final Logger LOG = LogManager.getLogger(FileLib.class);

	public static void createFileIfNotExists(String fileName) {
		if (!Files.exists(Paths.get(fileName))) {
			try {
				Files.createFile(Paths.get(fileName));
			} catch (IOException e) {
				LOG.error("Cannot create file", e);
			}
		}
	}
}
