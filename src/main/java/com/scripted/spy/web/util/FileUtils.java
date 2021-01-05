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

package com.scripted.spy.web.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.scripted.spy.web.model.Project;

public class FileUtils {

	public static void main(String[] args) throws IOException {
		new FileUtils().getScriptData();
	}

	public String getScriptData() {
		BufferedReader br = null;
		StringBuilder jsScript = new StringBuilder();
		try {
			File file = new File("objectspy.js");
			br = new BufferedReader(new FileReader(file));
			String st;
			while ((st = br.readLine()) != null)
				jsScript.append(st);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return jsScript.toString();
	}
	
	public boolean checkPageObjectFolderExists(Project project) {
		if (null != project.getLocation()) {
			File dir = new File(project.getLocation());
			if (dir.exists()) {
				File pageObjRepo = new File(
						project.getLocation() + File.separator + RepositoryConstants.PAGEOBJECTREPOSITORY.getValue());
				if (pageObjRepo.exists()) {
					return true;
				} else {
					pageObjRepo.mkdir();
					return true;
				}
			} else {
				dir.mkdirs();
				File objRepo = new File(
						project.getLocation() + File.separator + RepositoryConstants.OBJECTREPOSITORY.getValue());
				File pageObjRepo = new File(
						project.getLocation() + File.separator + RepositoryConstants.PAGEOBJECTREPOSITORY.getValue());
				objRepo.mkdir();
				pageObjRepo.mkdir();
				return true;
			}
		}
		return false;
	}

	
	
	public boolean checkObjectFolderExists(Project project) {
		if (null != project.getLocation()) {
			File dir = new File(project.getLocation());
			if (dir.exists()) {
				File pageObjRepo = new File(
						project.getLocation() + File.separator + RepositoryConstants.OBJECTREPOSITORY.getValue());
				if (pageObjRepo.exists()) {
					return true;
				} else {
					pageObjRepo.mkdir();
					return true;
				}
			} else {
				dir.mkdirs();
				File objRepo = new File(
						project.getLocation() + File.separator + RepositoryConstants.OBJECTREPOSITORY.getValue());
				File pageObjRepo = new File(
						project.getLocation() + File.separator + RepositoryConstants.PAGEOBJECTREPOSITORY.getValue());
				objRepo.mkdir();
				pageObjRepo.mkdir();
				return true;
			}
		}
		return false;
	}
}
