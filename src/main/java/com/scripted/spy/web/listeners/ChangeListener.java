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

package com.scripted.spy.web.listeners;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import com.scripted.spy.web.model.Attribute;
import com.scripted.spy.web.service.ElementObserver;
import com.scripted.spy.web.service.ElementSubject;
import com.scripted.spy.web.service.ScriptingService;
import com.scripted.spy.web.service.SpyWeb;

public class ChangeListener implements Runnable {
	private static final Logger logger = LogManager.getLogger(ChangeListener.class);
	private SpyWeb spyWeb;
	private JavascriptExecutor js;
	private WebDriver webDriver;
	private Map<String, Attribute> objAttrMap;
	private String homeUrl;
	private String mainWindow;
	private ScriptingService scriptingService = new ScriptingService();

	public ChangeListener(SpyWeb spyWeb) {
		this.spyWeb = spyWeb;
		js = this.spyWeb.getJs();
		webDriver = this.spyWeb.getDriver1();
		mainWindow = webDriver.getWindowHandle();
		homeUrl = this.spyWeb.getTxtURL().getText() + "/";
	}

	@Override
	public void run() {
		Set newWindows = webDriver.getWindowHandles();
		System.out.println("Thread Started...");
		String isAvailable = "";
		String tabIsAvailable = "";
		String unavailableScript = "var isAvailable=window.localStorage.setItem('isAvailable','false');";
		String tabIsUnavailableScript = "var newTabOpened=window.localStorage.setItem('newTabOpened','false');";
		ElementSubject eSubject = new ElementSubject();
		ElementObserver eObserver = new ElementObserver(this.spyWeb);
		Object isPageRefreshed = "";
		try {
			while (true && null != webDriver) {
				Object scriptObj = js.executeScript(scriptingService.getAvailCheckScript());
				Object newTabObj = js.executeScript(scriptingService.getnewTabOpened());
				isPageRefreshed = js.executeScript(scriptingService.getRefreshScript());
				if (null != scriptObj) {
					isAvailable = scriptObj.toString();
					if (null != newTabObj) {
						tabIsAvailable = newTabObj.toString();
						if (!tabIsAvailable.equalsIgnoreCase("false")) {
							if (tabIsAvailable.equalsIgnoreCase("true")) {
								 newWindows = webDriver.getWindowHandles();
								Iterator itr = newWindows.iterator();
								while (itr.hasNext()) {
									String popUpWindow = itr.next().toString();
									if (!popUpWindow.contains(mainWindow)) {
										webDriver.switchTo().window(popUpWindow);
									}
								}
								if (newWindows.size() == 1) {
									webDriver.switchTo().window(mainWindow);
									js.executeScript(tabIsUnavailableScript);
								}
							} else {
								webDriver.switchTo().window(mainWindow);
							}
						}
					}
					if (isAvailable.equalsIgnoreCase("true")) {
						System.out.println("Data is Available" + isAvailable);
						eSubject.addObserver(eObserver);
						eSubject.setData("This is new");
						js.executeScript(unavailableScript);
					}
				}
				addEvents(isPageRefreshed, newTabObj);
			}
		} catch (NoSuchWindowException w) {
			try {
				if (newWindows.size() > 1) {
					webDriver.switchTo().window(mainWindow);
					run();
					}
				else {
					SpyWeb.setFlagBrowser(false);
					this.spyWeb.displayErroMessage("Browser is closed. Please launch new browser instance to add object");
					SpyWeb.killDrivers();
				}
			} catch (NoSuchWindowException window) {
				SpyWeb.setFlagBrowser(false);
				this.spyWeb.displayErroMessage("Browser is closed. Please launch new browser instance to add object");
				SpyWeb.killDrivers();
			}

		} catch (WebDriverException w) {
			SpyWeb.setFlagBrowser(false);
			this.spyWeb.displayErroMessage("Browser is closed. Please launch new browser instance to add object");
			SpyWeb.killDrivers();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addEvents(Object pageRefresh, Object newTabObj) {
		String isPageRefresh = "";
		String clearLocalStorage = "window.localStorage.clear();";
		String windowStorageClear = "localStorage.clear();";
		if (null != pageRefresh)
			isPageRefresh = pageRefresh.toString();
		if (null != webDriver) {
			try {
				String newUrl = webDriver.getCurrentUrl();
				if (!homeUrl.trim().equalsIgnoreCase(newUrl.trim()) || isPageRefresh.equalsIgnoreCase("true")) {
					logger.debug("Navigated to different URL: " + newUrl);
					js = (JavascriptExecutor) webDriver;
					System.out.println("Script is executing...");
					this.spyWeb.updateDriverMapOnRefresh(newUrl);
					js.executeScript(scriptingService.getAddElementPopup());
					objAttrMap = new HashMap<>();
					homeUrl = webDriver.getCurrentUrl();
				}

			} catch (Exception e) {
				System.out.println("Session Exception:" + e.getMessage());
			}
		}
	}
	
	

}
