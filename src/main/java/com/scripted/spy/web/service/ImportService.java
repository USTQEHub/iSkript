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

import java.awt.Color;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;

import com.scripted.spy.web.model.Attribute;
import com.scripted.spy.web.model.Project;
import com.scripted.spy.web.model.WebOR;
import com.scripted.spy.web.model.WebORObject;
import com.scripted.spy.web.model.WebORPage;
import com.scripted.spy.web.util.MessageTimer;
import com.scripted.spy.web.util.RepositoryConstants;

public class ImportService {
	private JSONReader jsonReader = null;

	public ImportService() {
		jsonReader = new JSONReader();
	}

	public Map<String, Map<String, Attribute>> parseToPagesData(String projectLocation, String projectName,
			JLabel lblInfo, String importFilename) throws Exception {
		System.out.println(
				"Parsing started from the file: " + projectLocation + "Imported File Location: " + importFilename);
		if (!new File(importFilename).getParent().contains(RepositoryConstants.OBJECTREPOSITORY.getValue())) {
			lblInfo.setVisible(true);
			lblInfo.setText("Project does not exist!");
			lblInfo.setForeground(Color.RED);
			MessageTimer timer = new MessageTimer(lblInfo);
			timer.start();
		}
		Map<String, Map<String, Attribute>> pages = null;
		try {
			Project project = jsonReader.readJson(importFilename);
			Map<String, Attribute> attributes = null;
			Map<String, Attribute> xpathaxes = null;
			WebOR webObject = project.getObjectRepository().getWebOR();
			if (null != webObject && null != webObject.getPages() && !webObject.getPages().isEmpty()) {
				pages = new HashMap<>();
				for (WebORPage page : webObject.getPages()) {
					attributes = new HashMap<String, Attribute>();
					if (page.getWebORObjects()!=null) {
						for (WebORObject object : page.getWebORObjects()) {
							String objectName = object.getObjectName();
							Attribute attribute = object.getAttributes();
							attribute.setXpath(attribute.getXpaths().get("default"));
							attributes.put(objectName, attribute);
						}
					}
					pages.put(page.getPageName().replace("Page", ""), attributes);
					System.out.println("Pages Data after parsing from JSON" + pages.toString());
				}
			} else {
				lblInfo.setVisible(true);
				lblInfo.setText("No pages found!");
				lblInfo.setForeground(Color.RED);
				MessageTimer timer = new MessageTimer(lblInfo);
				timer.start();
			}
		} catch (Exception e) {
			lblInfo.setVisible(true);
			lblInfo.setText("Please select *.sky file to import");
			lblInfo.setForeground(Color.RED);
			MessageTimer timer = new MessageTimer(lblInfo);
			timer.start();
		}
		return pages;
	}

}
