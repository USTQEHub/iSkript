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

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.scripted.spy.web.model.Attribute;

public class XPathExpressionGenerator {

	public String generateContainsXPathExpression(WebElement webElement, String attribute, Attribute attrObj,WebDriver driver1) {
		String containsExpression=""; int k=0;
		if(StringUtils.isNotEmpty(attrObj.getTextContent())) {
			containsExpression = "//" + attrObj.getTagName().toLowerCase() + "[contains(text(),'"
					+ attrObj.getTextContent().trim() + "')]";
		} else {
		containsExpression = "//" + attrObj.getTagName().toLowerCase() + "[contains(@"+attribute+",'"
				+ attrObj.getAttributes().get(attribute)+ "')]";
		}List<WebElement> webElements = driver1.findElements(By.xpath(containsExpression));
		if (null != webElements && !webElements.isEmpty()) {
			k = webElements.size();
			}
		if(k==1) {
		return containsExpression;
		}else {
			containsExpression=null;
		}
		return containsExpression;
	}

	public String generatePositionExpression(String xpath, WebElement webElement, String attributeKey,WebDriver driver1) {
		int k=0;
		String positionExpression = "//" + webElement.getTagName() + "[position() and @" + attributeKey + "=\'"
				+ webElement.getAttribute(attributeKey) + "\']";
		List<WebElement> webElements = driver1.findElements(By.xpath(positionExpression));
		if (null != webElements && !webElements.isEmpty()) {
			k = webElements.size();
			}
		if(k==1) {
		return positionExpression;
		}else {
			positionExpression=null;
		}
		return positionExpression;
	}

	public WebElement findPrecedingWebElement(String xpath, WebDriver webDriver) {
		WebElement webElement = null;
		try {
			String precedingExpression = xpath + "/preceding::div[position()=1]";
			System.out.println("Preceding Sibling Xpath expression" + precedingExpression);
			webElement  = webDriver.findElement(By.xpath(precedingExpression));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return webElement;
	}

	public WebElement findFollowingWebElement(String xpath, WebDriver webDriver) {
		WebElement webElement = null;
		try {
			String precedingExpression = xpath + "/following::div[position()=1]";
			System.out.println("Following Sibling Xpath expression" + precedingExpression);
			webElement = webDriver.findElement(By.xpath(precedingExpression));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return webElement;
	}

	public String generatePrcedingExpression(WebElement followingWebelement, String followingAttribute, boolean isAngular, WebElement selectedElement, String attribute, Attribute attrObj,WebDriver driver1) {
		int k=0;String value=null;
		String containsOrAttributeExpression = getContainsTextExpression(followingWebelement, followingAttribute,value, isAngular, attrObj);
		String selectedExpression = getContainsTextExpression(selectedElement, attribute,value, isAngular, attrObj);
		String precedingExpression = "//*"+containsOrAttributeExpression+"/preceding::*"+selectedExpression;
		System.out.println("Preceding Expression: " + precedingExpression);
		List<WebElement> webElements = driver1.findElements(By.xpath(precedingExpression));
		if (null != webElements && !webElements.isEmpty()) {
			k = webElements.size();
			}
		if(k==1) {
		return precedingExpression;
		}else {
			precedingExpression=null;
		}
		return precedingExpression;
	}

	public String generateFollowingExpression(WebElement precedingWebelement, String precedingAttribute, String precedingValue,boolean isAngular, WebElement selectedElement, String attribute, Attribute attrObj, WebDriver driver1) {
		int k=0;String value=null;
		String containsOrAttributeExpression = getContainsTextExpression(precedingWebelement, precedingAttribute,precedingValue,isAngular, attrObj);
		if(containsOrAttributeExpression.contains("null")) {
			String a = attrObj.getXpath();
			String[] splitString=a.split("\"");
			for (int i=0;i<splitString.length-1;i++) {
			System.out.println("First splitted"+splitString[0]);
			String firstSplit=splitString[0];
			System.out.println("Second splitted"+splitString[1]);
			containsOrAttributeExpression=containsOrAttributeExpression.replace(containsOrAttributeExpression, splitString[1]);
			System.out.println("replaced xpath:"+containsOrAttributeExpression);
			String selectedExpression = getContainsTextExpression(selectedElement, attribute,value, isAngular, attrObj);
			String followingExpression = "("+firstSplit+"'"+containsOrAttributeExpression+"']"+"//following::*"+selectedExpression+")[1]";
			System.out.println("following: "+followingExpression);
			List<WebElement> webElements = driver1.findElements(By.xpath(followingExpression));
			if (null != webElements && !webElements.isEmpty()) {
				k = webElements.size();
				}
			if(k==1) {
			return followingExpression;
			}else {
				followingExpression=null;
			}
			return followingExpression;
				}
				
			}else {
			String selectedExpression = getContainsTextExpression(selectedElement, attribute,value, isAngular, attrObj);
			String followingExpression = "//*"+containsOrAttributeExpression+"/following::*"+selectedExpression;
			System.out.println("Following Expression: " + followingExpression);
			List<WebElement> webElements = driver1.findElements(By.xpath(followingExpression));
			if (null != webElements && !webElements.isEmpty()) {
				k = webElements.size();
				}
			if(k==1) {
			return followingExpression;
			}else {
				followingExpression=null;
			}
			
				
			}
		return null;
	}
	

	public String generateParentExpression(WebElement selectedElement, String xpath, String attribute, boolean isAngular, Attribute attributeObj,WebDriver driver1) {
		int k=0;String value=null;
		String containsOrAttributeExpression = getContainsTextExpression(selectedElement, attribute,value, isAngular, attributeObj);
		String parentExpression = xpath+"/parent::*/"+selectedElement.getTagName()+containsOrAttributeExpression;
		System.out.println("Parent Expression: " + parentExpression.toString());
		List<WebElement> webElements = driver1.findElements(By.xpath(parentExpression));
		if (null != webElements && !webElements.isEmpty()) {
			k = webElements.size();
			}
		if(k==1) {
		return parentExpression;
		}else {
			parentExpression=null;
		}
		return parentExpression;
	}

	public String generateAncestorExpression(WebElement selectedElement, String xpath, String attribute, boolean isAngular, Attribute attrObj,WebDriver driver1) {
		int k=0;String value=null;
		String containsOrAttributeExpression = getContainsTextExpression(selectedElement, attribute, value,isAngular, attrObj);
		String ancesctorExpression = xpath +"/ancestor::*/" + selectedElement.getTagName() + containsOrAttributeExpression;
		System.out.println("Parent Expression: " + ancesctorExpression);
		List<WebElement> webElements = driver1.findElements(By.xpath(ancesctorExpression));
		if (null != webElements && !webElements.isEmpty()) {
			k = webElements.size();
			}
		if(k==1) {
		return ancesctorExpression;
		}else {
			ancesctorExpression=null;
		}
		return ancesctorExpression;
	}

	public String generateAttributeExpression(WebElement selectedWebElement, String attribute, Attribute attrObj,WebDriver driver1) {
		String attributeExpression = ""; int k=0;String value=null;
		if(null!= selectedWebElement) {
			if(null == selectedWebElement.getAttribute(attribute)) {
				attributeExpression = "//" +selectedWebElement.getTagName()+getContainsTextExpression(selectedWebElement, attribute,value, false, attrObj);
			}else {
			attributeExpression = "//"+selectedWebElement.getTagName()+"[@" + attribute + "=\'" + selectedWebElement.getAttribute(attribute) + "\']";
		}
			System.out.println("attributeExpression: " + attributeExpression);
		}List<WebElement> webElements = driver1.findElements(By.xpath(attributeExpression));
		if (null != webElements && !webElements.isEmpty()) {
			k = webElements.size();
			}
		if(k==1) {
		return attributeExpression;
		}else {
			attributeExpression=null;
		}
		return attributeExpression;
	}

	public String generateChildExpression(WebElement selectedElement, String xpath, String attribute, boolean isAngular, Attribute attrObj,WebDriver driver1) {
		int k=0;String value=null;
		String containsOrAttributeExpression = getContainsTextExpression(selectedElement, attribute,value, isAngular, attrObj);
		String childExpression = xpath + "/parent::*/child::" + selectedElement.getTagName()
				+ containsOrAttributeExpression;
		System.out.println("Child Expression: " + childExpression);
		List<WebElement> webElements = driver1.findElements(By.xpath(childExpression));
		if (null != webElements && !webElements.isEmpty()) {
			k = webElements.size();
			}
		if(k==1) {
		return childExpression;
		}else {
			childExpression=null;
		}
		return childExpression;
	}

	public String generateDescendentExpression(WebElement selectedElement, String xpath, String attribute, boolean isAngular, Attribute attrObj,WebDriver driver1) {
		int k=0; String value=null;
		String containsOrAttributeExpression = getContainsTextExpression(selectedElement, attribute, value, isAngular, attrObj);
		String descendantExpression = xpath + "/parent::*/descendant::" + selectedElement.getTagName() +containsOrAttributeExpression;
		System.out.println("descendantExpression : " + descendantExpression);
		List<WebElement> webElements = driver1.findElements(By.xpath(descendantExpression));
		if (null != webElements && !webElements.isEmpty()) {
			k = webElements.size();
			}
		if(k==1) {
		return descendantExpression;
		}else {
			descendantExpression=null;
		}
		return descendantExpression;
	}
	
	private String getElementText(WebElement webElement) {
		String elementText = "";
		if (null!= webElement && webElement.getTagName().equalsIgnoreCase("input")) {
			elementText = webElement.getAttribute("name");
		} else {
			elementText = webElement.getText();
		}
		System.out.println("Tag: " + webElement.getTagName() + "Element Text : " + elementText);
		return elementText;
	}
	
	public String generateAngularAttributeExpression(String attribute, String value) {
		String attributeExpression = "//*[@" + attribute.toLowerCase() + "='" + value + "']";
		System.out.println("Angular attributeExpression: " + attributeExpression);
		return attributeExpression;
	}
	
	private String getContainsTextExpression(WebElement webElement, String attribute,String value, boolean isAngular, Attribute attributeObj) {
		String containsExpression = null;
		if (null != attributeObj && isAngular &&StringUtils.isNotBlank(attributeObj.getTextContent())) {
			containsExpression = "[contains(text(),'" + attributeObj.getTextContent() + "')]";
		} else if (null != attributeObj && StringUtils.isNotBlank(attributeObj.getTextContent())) {
			if(webElement.getText().isEmpty()) {
				containsExpression = "[@" + attribute + "= \"" + value + "\"]";
			}else {
			System.out.println("webelement text="+webElement.getText()+"|");
			containsExpression = "[contains(text(),'" + webElement.getText() + "')]";
			}
		} else if (null != attributeObj) {
			containsExpression = "[@" + attribute + "= \'" + attributeObj.getAttributes().get(attribute) + "\']";
		}
		
		return containsExpression;
	}
	
}
