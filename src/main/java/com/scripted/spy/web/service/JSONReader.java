/*Copyright [2020] [UST-Global]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/

package com.scripted.spy.web.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.scripted.spy.web.model.Project;
import com.scripted.spy.web.util.RepositoryConstants;

public class JSONReader {
	GenericUtils gUtils=new GenericUtils();
	
	public void writeJson(Project project) throws JsonParseException, JsonMappingException, IOException {
		String encrypted=new String();
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		File filePath=new File(project.getLocation() + File.separator + RepositoryConstants.OBJECTREPOSITORY.getValue()
		+ File.separator + project.getProjectName() + ".sky");
		
		String jsonFilePath=project.getLocation() + File.separator + RepositoryConstants.OBJECTREPOSITORY.getValue()
		+ File.separator + project.getProjectName() + ".sky";
		
		mapper.writerWithDefaultPrettyPrinter().writeValue(filePath, project);
		try {
			encrypted=gUtils.encryptContent(readFile(jsonFilePath));
            BufferedWriter writer = new BufferedWriter(new FileWriter(jsonFilePath));
            writer.write(encrypted);
            writer.close();

			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}
		System.out.println("Encrypted String>"+encrypted);
		 
	}
	
	public String readFile(String jsonPath) {
		String fileContent = null;
		try {
			File file = new File(jsonPath);
			FileReader reader = new FileReader(file);
			char[] chars = new char[(int) file.length()];
			reader.read(chars);
			fileContent = new String(chars);
		} catch (Exception e) {
			System.out.println("Exception while reading file content :" + e);
		}

		return fileContent;
	}

	public Project readJson(String filename) throws JsonParseException, JsonMappingException, IOException {
		String decrypted=new String();
		ObjectMapper mapper = new ObjectMapper();
		try {
			decrypted=gUtils.decryptContent(readFile(filename));
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Encrypted String>"+filename);
		Project project = mapper.readValue(decrypted, Project.class);
		System.out.println("Project detail: " + project.toString());
		return project;
	}
	 
}
