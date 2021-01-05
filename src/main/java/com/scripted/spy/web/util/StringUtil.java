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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;

public class StringUtil {
	private static List<String> elementAttributesFilter = new ArrayList<>();
	static {
		elementAttributesFilter.add("style");
		elementAttributesFilter.add("class");
		elementAttributesFilter.add("type");
		elementAttributesFilter.add("ng-click");
		elementAttributesFilter.add("ng-dblclick");
		elementAttributesFilter.add("ng-class");
		elementAttributesFilter.add("ng-disabled");
	}
	public Map<String, String> insertAttributes(String elemetAttributes) {
		System.out.println("string to map: Start " + new Date());
		if(StringUtils.isNotBlank(elemetAttributes)) {
		Gson jsonParser = new Gson();
		Map<String, String> attMap = jsonParser.fromJson(elemetAttributes,Map.class);
		System.out.println("string to map: End " + new Date());
		return attMap;
		}
		return null;
	}

	public String getformattedPage(String title, String currentURL) {
		String[] formattedPageName = null;
		String pageTitle = null;
		try {
			if (title.contains(":")) {
				formattedPageName = title.split(":");
				pageTitle = formattedPageName[0];
			} else {
				formattedPageName = currentURL.split("/");
				if(formattedPageName.length == 3 ) {
					return formattedPageName[0]+formattedPageName[1]+formattedPageName[2];
				} 
				System.out.println("Length: " + formattedPageName.length + "Page Title: " + pageTitle);
				pageTitle = formattedPageName[0] + formattedPageName[1] + formattedPageName[2]+"_"+formattedPageName[3]+"_"+formattedPageName[4];
			}
			return pageTitle;
		} catch (Exception e) {
			e.printStackTrace();
			return title;
		}
	}

	public String getElementTag(String value) {
		System.out.println("Tree Value:" + value);
		String[] elementArray;
		String tagName = null;
		if(null != value) {
			elementArray = value.split("_"); 
			if(elementArray.length != 0) {
				tagName = elementArray[0];
			}
		}
		return tagName;
	}
	
	
}
