package com.example.filebrowser;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
class FileBrowserSpringBootRestApplicationTests {

	@Test
	void contextLoads() {
		File file = new File("/Users/olegp");
		org.junit.jupiter.api.Assertions.assertTrue(file.isDirectory());
	}


}
