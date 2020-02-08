package com.browserstack.demo.service.impl;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.input.ReversedLinesFileReader;
import org.springframework.stereotype.Service;

import com.browserstack.demo.service.BrowserstackService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BrowserstackServiceImpl implements BrowserstackService {
	
	@Override
	public String readLastNLines() {
		String filePath = "/Users/b0206959/desktop/gitprojects/test.log";
		File file = new File(filePath);
		int lineCount = 10;
		int counter = 0;
		ReversedLinesFileReader object = null;
		try {
			object = new ReversedLinesFileReader(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		StringBuilder sb = new StringBuilder();
		if (object == null) {
			log.error("Cannot read file");
			return null;
		}
		while (counter < lineCount) {
			try {
				sb.insert(0, object.readLine()).insert(0, "\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
			counter++;
		}
		try {
			object.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

}
