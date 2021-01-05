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

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.scripted.spy.web.util.ElementUtil;
import static org.openqa.selenium.support.locators.RelativeLocator.withTagName;
public class RelativeElementLocators {
	
	private ElementUtil elementUtil = new ElementUtil();
	public WebElement getLeftElement(WebDriver webDriver, WebElement selectedElement) {
		WebElement leftElement = null;
		try {
			if(null != selectedElement) {
			leftElement = webDriver
					.findElement(withTagName(selectedElement.getTagName()).toLeftOf(selectedElement));
			System.out.println("leftElement: " + leftElement.getTagName() + "leftElement.getText()" + leftElement.getText());
			System.out.println("leftElement: Attributes : " + elementUtil.getAllAttributes(leftElement, webDriver));
			}
		} catch (Exception e) {
			System.out.println("Left Element not Found for: " + selectedElement.toString());
			e.printStackTrace();
		}
		return leftElement;
	}

	public WebElement getRightElement(WebDriver webDriver, WebElement selectedElement) {
		WebElement rightElement = null;
		try {
			if(null != selectedElement) {
			rightElement = webDriver
					.findElement(withTagName(selectedElement.getTagName()).toRightOf(selectedElement));
			}
		} catch (Exception e) {
			System.out.println("Right Element not Found for: " + selectedElement.toString());
			e.printStackTrace();
		}
		return rightElement;
	}

	public WebElement getAboveElement(WebDriver webDriver, WebElement selectedElement) {
		WebElement aboveElement = null;
		try {
			if(null != selectedElement) {
			aboveElement = webDriver
					.findElement(withTagName(selectedElement.getTagName()).above(selectedElement));
			}
		} catch (Exception e) {
			System.out.println("Above Element not Found for: " + selectedElement.toString());
			e.printStackTrace();
		}
		return aboveElement;
	}

	public WebElement getBelowElement(WebDriver webDriver, WebElement selectedElement) {
		WebElement belowElement = null;
		try {
			if(null != selectedElement) {
			belowElement = webDriver
					.findElement(withTagName(selectedElement.getTagName()).below(selectedElement));
			}
		} catch (Exception e) {
			System.out.println("Below Element not Found for: " + selectedElement.toString());
			e.printStackTrace();
		}
		return belowElement;
	}
	
	public WebElement getNearElement(WebDriver webDriver, WebElement selectedElement) {
		WebElement nearElement = null;
		try {
			if(null != selectedElement) {
			nearElement = webDriver
					.findElement(withTagName(selectedElement.getTagName()).near(selectedElement));
			}
		} catch (Exception e) {
			System.out.println("Near Element not Found for: " + selectedElement.toString());
			e.printStackTrace();
		}
		return nearElement;
	}
}
