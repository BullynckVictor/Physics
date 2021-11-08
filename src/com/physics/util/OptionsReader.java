package com.physics.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;
import java.util.stream.Stream;

public class OptionsReader {

	public OptionsReader()
	{
		valueMap = new TreeMap<>();
		defaultMap = new TreeMap<>();
	}

	public OptionsReader(String filename) throws IOException {
		valueMap = new TreeMap<>();
		defaultMap = new TreeMap<>();
		BufferedReader file = new BufferedReader(new FileReader(filename));
		Stream<String> lines = file.lines();
		for (String line : lines.toList())
		{
			int index = line.indexOf(':');
			String key = (String) line.subSequence(0, index);
			String value = ((String) line.subSequence(index + 1, line.length())).strip();
			valueMap.put(key, value);
		}
	}

	public void addDefault(String key, String value)
	{
		defaultMap.put(key, value);
	}

	public String getValue(String key)
	{
		return valueMap.getOrDefault(key, defaultMap.getOrDefault(key, null));
	}

	private final TreeMap<String, String> valueMap;
	private final TreeMap<String, String> defaultMap;
}
