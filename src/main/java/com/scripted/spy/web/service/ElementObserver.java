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

import java.util.Observable;
import java.util.Observer;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.scripted.spy.web.model.Attribute;

public class ElementObserver implements Observer {
	private SpyWeb spyWeb;
	private JavascriptExecutor js;
	private WebDriver webDriver;

	public ElementObserver(SpyWeb spyWeb) {
		this.spyWeb = spyWeb;
		this.js = this.spyWeb.getJs();
		this.webDriver = this.spyWeb.getDriver1();
	}

	@Override
	public void update(Observable o, Object arg) {
		Attribute attribute = spyWeb.addElement(this.js, this.webDriver);
		if(null != attribute) {
		spyWeb.updateTreeWithSingleNode(spyWeb.getTrPageExplorer(), attribute);
		}
	}

}
