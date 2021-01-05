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

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.Map.Entry;

@JsonPropertyOrder({ "id", "name", "classname", "iframeElement", "css", "completeXpath", "xpath", "xpaths",
		"attributes", "selectedLocator" })
public class Attribute {

	private String id;
	private String name;
	private String classname;
	private String iframeElement;
	@JsonIgnore
	private String xpath;
	private String css;
	@JsonIgnore
	private Integer xPosition;
	@JsonIgnore
	private Integer yPosition;
	@JsonProperty("xpath")
	private Map<String, String> xpaths;
	@JsonIgnore
	private Map<String, String> properties;
	@JsonIgnore
	private String selectorMethod;
	@JsonIgnore
	private String selectorValue;
	@JsonProperty("attributes")
	private Map<String, String> attributes;
	@JsonIgnore
	private Map<String, String> xpathaxes;
	@JsonIgnore
	private Map<String, String> attMap;
	@JsonIgnore
	private String tagName;
	@JsonIgnore
	private String textContent;
	@JsonIgnore
	private String location;
	@JsonIgnore
	private String follow;
	@JsonIgnore
	private String parent;
	@JsonIgnore
	private String preced;
	@JsonIgnore
	private String nodename;

	
	public String getfollow() {
		return follow == null ? "" : follow;
	}

	public void setfollow(String follow) {
		this.follow = follow;
	}
	public String getparent() {
		return parent == null ? "" : parent;
	}

	public void setparent(String parent) {
		this.parent = parent;
	}
	public Map<String, String> getXpathaxes() {
		return xpathaxes;
	}

	public void setXpathaxes(Map<String, String> xpathaxes) {
		this.xpathaxes = xpathaxes;
	}


	public String getpreced() {
		return preced == null ? "" : preced;
	}

	public void setpreced(String preced) {
		this.preced = preced;
	}

	public String getIframeElement() {
		return iframeElement == null ? "" : iframeElement;
	}

	public void setIframeElement(String iframeElement) {
		this.iframeElement = iframeElement;
	}

	@JsonIgnore
	private String completeXpath;
	private Map<String, String> selectedLocator;

	public String getId() {
		return id == null ? "" : id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {

		return name == null ? "" : name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNodename() {
		return nodename;
	}

	public void setNodename(String nodename) {

		this.nodename = nodename;

	}

	public String getClassname() {
		return classname == null ? "" : classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getXpath() {
		return xpath == null ? "" : xpath;
	}

	public void setXpath(String xpath) {
		this.xpath = xpath;
	}

	public String getCss() {
		return css == null ? "" : css;
	}

	public void setCss(String css) {
		this.css = css;
	}

	public Integer getxPosition() {
		return xPosition;
	}

	public void setxPosition(Integer xPosition) {
		this.xPosition = xPosition;
	}

	public Integer getyPosition() {
		return yPosition;
	}

	public void setyPosition(Integer yPosition) {
		this.yPosition = yPosition;
	}

	public Map<String, String> getXpaths() {
		for (Entry<String, String> entry : xpaths.entrySet()) {
			if (entry.getValue() == null) {
				xpaths.put(entry.getKey(), "");
			}
		}
		return xpaths;

	}

	public void setXpaths(Map<String, String> xpaths) {
		this.xpaths = xpaths;
	}

	public String getSelectorMethod() {
		return selectorMethod == null ? "" : selectorMethod;
	}

	public void setSelectorMethod(String selectorMethod) {
		this.selectorMethod = selectorMethod;
	}

	public String getSelectorValue() {
		return selectorValue == null ? "" : selectorValue;
	}

	public void setSelectorValue(String selectorValue) {
		this.selectorValue = selectorValue;
	}

	public Map<String, String> getAttMap() {
		return attMap;
	}

	public void setAttMap(Map<String, String> attMap) {
		this.attMap = attMap;
	}

	public String getTagName() {
		return tagName == null ? "" : tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getTextContent() {
		return textContent == null ? "" : textContent;
	}

	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCompleteXpath() {
		return completeXpath == null ? "" : completeXpath;
	}

	public void setCompleteXpath(String completeXpath) {
		this.completeXpath = completeXpath;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

	public Map<String, String> getSelectedLocator() {
		return selectedLocator;
	}

	public void setSelectedLocator(Map<String, String> selectedLocator) {
		this.selectedLocator = selectedLocator;
	}

	@Override
	public String toString() {
		return "Attribute [id=" + id + ", names=" + name + ", classname=" + classname + ", xpath=" + xpath + ", css="
				+ css + ",iframeElement=" + iframeElement + ", xPosition=" + xPosition + ", yPosition=" + yPosition
				+ ", xpaths=" + xpaths + ", properties=" + properties + ", selectorMethod=" + selectorMethod
				+ ", selectorValue=" + selectorValue + ", attributes=" + attributes + ", attMap=" + attMap
				+ ", tagName=" + tagName + ", textContent=" + textContent + ", location=" + location
				+ ", completeXpath=" + completeXpath + ",preced=" + preced + ",parent=" + parent + ",follow=" + follow
				+  ", selectedLocator=" + selectedLocator + "]";
	}

}
