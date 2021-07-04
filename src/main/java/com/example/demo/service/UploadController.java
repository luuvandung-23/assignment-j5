package com.example.demo.service;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadController {
	@Autowired
	ServletContext app;
	public File  save(MultipartFile attach) throws IllegalStateException, IOException{
		if(!attach.isEmpty()) {
			System.out.println(attach);
			String filename = attach.getOriginalFilename();
			File file = new File(app.getRealPath("/img"),filename);
			attach.transferTo(file);
			return file;
		}
		return null;
	}

}
