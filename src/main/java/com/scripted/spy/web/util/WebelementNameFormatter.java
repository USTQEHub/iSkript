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

import java.util.List;
import com.scripted.spy.web.model.ElementTemplate;

public class WebelementNameFormatter {

	private String getFormattedObjectName(String objectName) {
		String formattedName = "default";
		try {
			String[] tags = objectName.split("_");
			if(tags.length > 1) {
			formattedName = tags[1];
			formattedName.replaceAll("\\W", "");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "default";
		}
		return formattedName;
	}

	public void formatElements(List<ElementTemplate> elements) {
		for (ElementTemplate e : elements) {
			e.setName(e.getName());
		}
		System.out.println("Formatting is done: " + elements.toString());
	}
}
