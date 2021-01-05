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

public enum LocatorType {

	ID("ID", "id","Id"), XPATH("XPath", "xpath","XPath"), CSS("CSS","css", "Css"), CLASSNAME("ClassName","classname","ClassName"), POSITION("Position","",""), NGMODEL("Ng-model","model",""), NAME("Name","name","Name"), NGREPEATER("ng-repeater","ng-repeater",""), NGBUTTONTEXT("buttonText","buttonText",""), NGBIND("ng-bind", "exactBinding","");
	private String value;
	private String pageObjectKey;
	private String jSelectorMethod;
	private String csSelectorMethod;
	LocatorType(String value, String jSelectorMethod, String csSelectorMethod) {
		this.value = value;
		this.jSelectorMethod = jSelectorMethod;
		this.csSelectorMethod = csSelectorMethod;
	}

	public static LocatorType fromString(String locator) {
		locator = getFormattedLocator(locator);
		System.out.println("Locator: " + locator);
		for (LocatorType b : LocatorType.values()) {
			if (b.value.equalsIgnoreCase(locator)) {
				System.out.println("Selected Locator: " + b.value);
				return b;
			}
		}
		return LocatorType.XPATH;
	}
	
	private static String getFormattedLocator(String value) {
		if (null != value && value.contains(":")) {
			return value.split(":")[0];
		}
		return value;
	}

	public String getValue() {
		return value;
	}

	public String getPageObjectKey(String language) {
		if(language.equalsIgnoreCase("java")) {
			return jSelectorMethod;
		} else {
			return csSelectorMethod;
		}
	}
	
	
	
}
