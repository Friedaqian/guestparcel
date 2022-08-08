package com.example.demo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping(value = "/")
public class uploadController {
	
	@PostMapping(value="upload")
	public void uplaodImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
	    String fileName = file.getOriginalFilename();
	    InputStream fileInputStream = file.getInputStream();
		S3uoloadFile.uploadToS3(fileName,fileInputStream);
		//return ResponseEntity.status(200);
	}
	

	@GetMapping("detail")
	public ResponseEntity<String> getDataByUser(@RequestParam("user") String user) {
		String userID = "friedaqian_hotmail_com";
		
		ResponseEntity<String> res = null;
		if(user.equals(userID)) {
			res = ResponseEntity.ok().body("{\"id\":1,\r\n"
					+ "\"title\":\"testnewnn\"}");
		}
		else {res =ResponseEntity.status(203).body("failed");}
		return res;
		
	}
	
	@PostMapping("detail")
	public ResponseEntity<String> uploadDataByUser(@RequestParam("user") String user, @RequestBody String a ) {
		String userID = "friedaqian_hotmail_com";
		
		ResponseEntity<String> res = null;
		if(user.equals(userID)) {
			res = ResponseEntity.ok().body(a);
		}
		else {res =ResponseEntity.status(203).body("failed");}
		return res;
		
	}
	

}
