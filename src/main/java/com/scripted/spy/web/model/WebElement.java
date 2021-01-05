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

import java.io.Serializable;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WebElement implements Serializable {

	private static final long serialVersionUID = 1L;
	@JsonProperty("objectname")
	private String objectName;
	@JsonProperty("pagename")
	private String pageName;
	@JsonProperty("selectormethod")
	private String selectorMethod;
	private String value;
	private String type;
	private String description;
	private Map<String, Object> selectors;

	public WebElement() {
	}

	public WebElement(String objectName, String pageName, String selectorMethod, String value, String type) {
		super();
		this.objectName = objectName;
		this.pageName = pageName;
		this.selectorMethod = selectorMethod;
		this.value = value;
		this.type = type;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getSelectorMethod() {
		return selectorMethod;
	}

	public void setSelectorMethod(String selectorMethod) {
		this.selectorMethod = selectorMethod;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Map<String, Object> getSelectors() {
		return selectors;
	}

	public void setSelectors(Map<String, Object> selectors) {
		this.selectors = selectors;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
