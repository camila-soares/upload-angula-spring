package com.muchmore.digital.resources;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/archive")
public class ArchiveResources {

	@PostMapping
	public void upload(@RequestParam MultipartFile archive){
		this.saveArchive(archive);
	}

	private void saveArchive(MultipartFile archive) {
		Path directory = Paths.get("/tmp/archive");

		try{
			Files.createDirectories(directory);
		}catch(IOException e){
			throw new RuntimeException("Problemas ao criar repositório raiz", e);
		}

		Path archivePath = directory.resolve(archive.getOriginalFilename());

		try {
			archive.transferTo(archivePath.toFile());
		} catch (Exception e){
			throw new RuntimeException("Problemas ao salvar o arquivo", e);
		}
	}

}
