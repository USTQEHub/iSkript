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

package com.scripted.spy.web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Project {
	private String projectName;
	private String pageUrl;
	private ObjectRepository objectRepository;
	@JsonIgnore
	private String location;
	@JsonIgnore
	private String importFileLocation;

	public String getProjectName() {
		return projectName;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public ObjectRepository getObjectRepository() {
		return objectRepository;
	}

	public void setObjectRepository(ObjectRepository objectRepository) {
		this.objectRepository = objectRepository;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getImportFileLocation() {
		return importFileLocation;
	}

	public void setImportFileLocation(String importFileLocation) {
		this.importFileLocation = importFileLocation;
	}

	@Override
	public String toString() {
		return "Project [projectName=" + projectName + ", pageUrl=" + pageUrl+", objectRepository=" + objectRepository + ", location="
				+ location + ", importFileLocation=" + importFileLocation + "]";
	}
		
}
