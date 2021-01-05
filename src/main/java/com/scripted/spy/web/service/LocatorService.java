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

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.scripted.spy.web.model.Attribute;

public class LocatorService {

	private WebElement webElement;

	public void detectLocatorId(Attribute attribute, WebDriver webDriver) {
		try {
			if (null != attribute && StringUtils.isNotBlank(attribute.getId())) {
				webElement = webDriver.findElement(By.id(attribute.getId()));
				if (null != webElement) {
					attribute.setSelectorMethod("id");
					attribute.setSelectorValue(attribute.getId());
				}
				System.out.println("ID is the primary locator");
			}else {
				detectLocatorByName(attribute, webDriver);
			}
		} catch (Exception e) {
			e.printStackTrace();
			detectLocatorByName(attribute, webDriver);
		}
	}

	public void detectLocatorByName(Attribute attribute, WebDriver webDriver) {
		try {
			if (null != attribute && null != attribute.getName()) {
				webElement = webDriver.findElement(By.name(attribute.getName()));
				if (null != webElement) {
					attribute.setSelectorMethod("name");
					attribute.setSelectorValue(attribute.getName());
				}
				System.out.println("Name is the primary locator");
			}else {
				detectLocatorByXPath(null,attribute, webDriver);
			}
		} catch (Exception e) {
			e.printStackTrace();
			detectLocatorByXPath(null, attribute, webDriver);
		}
	}

	public void detectLocatorByXPath(String attributeKey, Attribute attribute, WebDriver webDriver) {
		try {
			if (null != attribute && null != attribute.getXpath()) {
				if(null != attributeKey && attributeKey.contains("XPath:")) {
					Map<String, String> attrMap = attribute.getAttributes();
					webElement = webDriver.findElement(By.xpath(attrMap.get(attributeKey)));
					if (null != webElement) {
						attribute.setSelectorValue(attrMap.get(attributeKey));
					}
				}else {
				webElement = webDriver.findElement(By.xpath(attribute.getXpath()));
				if (null != webElement) {
					attribute.setSelectorValue(attribute.getXpath());
				}
				}
				attribute.setSelectorMethod("xpath");
			}
			else {
				detectLocatorByCSS(attribute, webDriver);
			}
			System.out.println("XPath is the primary locator");
		} catch (Exception e) {
			e.printStackTrace();
			detectLocatorByCSS(attribute, webDriver);
		}
	}

	public void detectLocatorByCSS(Attribute attribute, WebDriver webDriver) {
		try {
			if (null != attribute && null != attribute.getCss()) {
				webElement = webDriver.findElement(By.cssSelector(attribute.getCss()));
				if (null != webElement) {
					attribute.setSelectorMethod("css");
					attribute.setSelectorValue(attribute.getCss());
				}
			}
			System.out.println("CSS is the primary locator");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setLocatorPreference(String attributeKey, Attribute attribute,
			WebDriver webDriver) {
		System.out.println("Selected Key: " + attributeKey);
		if(null == attributeKey) {
			attributeKey  = "Id";
		}
		String modifiedKey = getLocator(attributeKey.trim());
		switch (modifiedKey) {
		case "Name":
			detectLocatorByName(attribute, webDriver);
			break;
		case "Id":
			detectLocatorId(attribute, webDriver);
			break;
		case "CSS":
			detectLocatorByCSS(attribute, webDriver);
			break;
		case "XPath":
			detectLocatorByXPath(attributeKey, attribute, webDriver);
			break;
		default:
			detectLocatorId(attribute, webDriver);
		}
	}
	

	private String getLocator(String locator) {
		return locator.split(":")[0];
	}
}
