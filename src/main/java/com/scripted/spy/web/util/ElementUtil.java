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

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementUtil {

	public String getAllAttributes(WebElement webElement, WebDriver webDriver) {
		JavascriptExecutor executor = (JavascriptExecutor) webDriver;
		Object aa= "";
		if(null != webElement) {
		aa = executor.executeScript(
				"var items = {}; "
				+ "for (index = 0; index < arguments[0].attributes.length; ++index) { "
				+ "items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value }; "
				+ "return JSON.stringify(items);",
				webElement);
		System.out.println("Selected Attributes: " + aa.toString());
		}
		return aa.toString();
	}

}
