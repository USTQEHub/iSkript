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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JRadioButton;
import javax.swing.JTree;
import javax.swing.tree.TreePath;

import com.scripted.spy.web.model.Attribute;
import com.scripted.spy.web.service.SpyWeb;

public class LocatorSelectionListener implements ActionListener {
	private SpyWeb spyWeb = null;
	private JTree pageExplorer;
	private String attributeKey;
	private String attributeValue;
	private Map<String, Map<String, Attribute>> pagesMap;
	public LocatorSelectionListener(JTree pageExplorer, Map<String, Map<String, Attribute>> pagesMap, String attributeKey, String attributeValue) {
		this.pageExplorer = pageExplorer;
		this.pagesMap = pagesMap;
		this.attributeKey = attributeKey;
		this.attributeValue = attributeValue;
	}
	 public void actionPerformed(ActionEvent e){
	        JRadioButton button = (JRadioButton) e.getSource();
	        System.out.println("Button Text: " + button.getText());
	        TreePath selectedRow = pageExplorer.getSelectionPath();
			String objectName = selectedRow.getLastPathComponent().toString();
			String pageName = selectedRow.getParentPath().toString().replace("Page_", "");
			updateSelectors(pageExplorer, objectName, pageName, button.getText());
	    }
	 public void updateSelectors(JTree pageExplorer, String objectName, String pageName, String selectedKey) {
			String selectedPageName = getSelectedPage(pageExplorer.getSelectionPath().getParentPath().toString());
			System.out.println("Selected Object:  " + objectName + "  pageName: " + selectedPageName);
			Map<String, Attribute> attributeMap = pagesMap.get(selectedPageName.trim());
			Attribute a = attributeMap.get(objectName);
			a.setSelectorMethod(selectedKey.toLowerCase());
			a.setSelectorValue(getSelectorValue(a, selectedKey.toLowerCase()));
			System.out.println("Pages Map: " + pagesMap.toString());
			}
	 
	 private String getSelectedPage(String parentNode) {
			System.out.println("Given Parent Node : " + parentNode);
			String[] pagesArray = parentNode.split(",");
			if (pagesArray.length == 1)
				return null;
			String selectedPage = pagesArray[1];
			selectedPage = selectedPage.replace("Page_", "");
			selectedPage = selectedPage.replace("]", "");
			return selectedPage;
		}

	 private String getSelectorValue(Attribute a, String key) {
		 String value = null;
		 if(key.equalsIgnoreCase("ID")) {
			 value = a.getId();
		 } else if(key.equalsIgnoreCase("Name")) {
			 value = a.getAttributes().get("name");
		 } else if(key.equalsIgnoreCase("Classname")) {
			 value = a.getClassname();
		 } else if(key.equalsIgnoreCase("XPath")) {
			 value = a.getXpath();
		 }else if(key.equalsIgnoreCase("CSS")) {
			 value = a.getCss();
		 } else {
			 value = a.getXpaths().get(key);
			 if(value == null ) {
				 value = a.getAttributes().get(key.toLowerCase());
			 }
		 }
		 System.out.println("Given Key: " + key + "Processed Value: " + value);
		 return value;
	 }
}
