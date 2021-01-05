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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeSettings {
	public static Logger LOGGER = Logger.getLogger(ChromeSettings.class);
	private ChromeOptions chromeOptionsObj = new ChromeOptions();
	Map<String, Object> chromePrefs = new HashMap<String, Object>();
	List<String> chromeOptions = new ArrayList<>();

	public ChromeOptions setOptions() {
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("profile.content_settings.pattern_pairs.*.multiple-automatic-downloads", 1);
		chromePrefs.put("download.prompt_for_download", false);
		chromePrefs.put("credentials_enable_service", false);
		chromePrefs.put("password_manager_enabled", false);
		chromeOptions.add("disable-plugins");
		chromeOptions.add("disable-extensions");
		chromeOptions.add("allow-running-insecure-content");
		chromeOptions.add("ignore-certificate-errors");
		chromeOptions.add("--always-authorize-plugins");
		chromeOptions.add("--disable-notifications");
		chromeOptions.add("disable-infobars");
		chromeOptions.add("--test-type");
		chromeOptionsObj.addArguments(chromeOptions);
		chromeOptionsObj.setExperimentalOption("prefs", chromePrefs);
		return this.chromeOptionsObj;
	}
	
}
