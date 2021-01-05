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

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import com.paulhammant.ngwebdriver.ByAngular;
import com.paulhammant.ngwebdriver.NgWebDriver;
import com.scripted.spy.web.model.Attribute;
import com.scripted.spy.web.model.ObjectRepository;
import com.scripted.spy.web.model.Project;
import com.scripted.spy.web.model.WebOR;
import com.scripted.spy.web.model.WebORObject;
import com.scripted.spy.web.model.WebORPage;
import com.scripted.spy.web.util.ColorCode;
import com.scripted.spy.web.util.FileUtils;
import com.scripted.spy.web.util.LocatorType;
import com.scripted.spy.web.util.MessageTimer;
import com.scripted.spy.web.util.RepositoryConstants;

public class WebElementService {

	private XPathExpressionGenerator xpathExpressionGenerator = new XPathExpressionGenerator();
	private FileUtils fileUtils = new FileUtils();
	private LocatorService locatorService;
	public static boolean flagDelete = false;

	public WebElementService() {
		locatorService = new LocatorService();
	}

	public List<WebElement> findElementByLocators(LocatorType locatorType, String value, WebDriver webDriver,
			String attributeKey) {
		List<WebElement> webElement = null;
		System.out.println("Locator Type:" + locatorType);
		System.out.println("Driver:" + webDriver);
		try {
			if (LocatorType.ID == locatorType) {
				webElement = webDriver.findElements(By.id(value));
			} else if (LocatorType.XPATH == locatorType) {
				System.out.println("Driver: " + webDriver);
				webElement = webDriver.findElements(By.xpath(value));
			} else if (LocatorType.CSS == locatorType) {
				webElement = webDriver.findElements(By.cssSelector(value));
			} else if (LocatorType.CLASSNAME == locatorType) {
				webElement = webDriver.findElements(By.xpath("//*[@class='" + value + "']"));
			} else if (LocatorType.NAME == locatorType) {
				webElement = webDriver.findElements(By.xpath(value));
			} else if (LocatorType.NGMODEL == locatorType) {
				System.out.println("In Angular:");
				JavascriptExecutor js = (JavascriptExecutor) webDriver;
				NgWebDriver ngDriver = new NgWebDriver(js);
				webElement = webDriver.findElements(ByAngular.model(value));
			} else if (LocatorType.POSITION == locatorType) {
				webElement = webDriver.findElements(By.xpath(value));
			} else if (LocatorType.NGREPEATER == locatorType) {
				JavascriptExecutor js = (JavascriptExecutor) webDriver;
				NgWebDriver ngDriver = new NgWebDriver(js);
				webElement = webDriver.findElements(ByAngular.repeater(value));
			} else if (LocatorType.NGBUTTONTEXT == locatorType) {
				JavascriptExecutor js = (JavascriptExecutor) webDriver;
				NgWebDriver ngDriver = new NgWebDriver(js);
				webElement = webDriver.findElements(ByAngular.buttonText(value));
			} else if (LocatorType.NGBIND == locatorType) {
				JavascriptExecutor js = (JavascriptExecutor) webDriver;
				NgWebDriver ngDriver = new NgWebDriver(js);
				webElement = webDriver.findElements(ByAngular.exactBinding(value));
			}
		} catch (NoSuchWindowException w) {
			System.out.println("No Such Window Exception");
		} catch (InvalidSelectorException e) {
			System.out.println("Invalid Selector Exception");
		} catch (WebDriverException we) {

		}
		return webElement;
	}

	public void serializeObjects(Project pro, Map<String, Map<String, Attribute>> pagesMap,
			Map<String, Attribute> objAttrMap, JLabel lblInfo, String attributeKey, String attributeValue,
			WebDriver webDriver, Map<String, Boolean> savedPages, String selectedLanguage, boolean exportJsonflag) {

		JSONReader reader = new JSONReader();
		Project project = new Project();
		project.setProjectName(pro.getProjectName());
		project.setLocation(pro.getLocation());
		project.setPageUrl(pro.getPageUrl());
		List<Attribute> attributes;
		WebORPage page = null;
		List<WebORPage> pages = new ArrayList<>();
		List<WebORObject> webORObjects = null;
		ObjectRepository repo = new ObjectRepository();
		WebOR webOR = new WebOR();
		webOR.setObjectRepoName("WebOR");
		for (Map.Entry<String, Map<String, Attribute>> pagesEntry : pagesMap.entrySet()) {
			savedPages.put(pagesEntry.getKey(), true);
			objAttrMap = pagesEntry.getValue();
			if (null != objAttrMap && !objAttrMap.isEmpty()) {
				page = new WebORPage();
				System.out.println("Page: " + pagesEntry.getKey());
				String pageSuffix = "Page";
				String stringContainsPage = pagesEntry.getKey().toString().toLowerCase();
				if (stringContainsPage.contains("Page")) {
					pageSuffix = "";
				} else if (pagesEntry.getKey().contains("Page")) {
					pageSuffix = "";
				}
				page.setPageName(pagesEntry.getKey() + pageSuffix);
				webORObjects = new ArrayList<>();
				for (Map.Entry<String, Attribute> attributeEntry : objAttrMap.entrySet()) {

					attributes = new ArrayList<>();
					WebORObject obj = new WebORObject();
					obj.setObjectName(attributeEntry.getKey());
					Attribute a = attributeEntry.getValue();
					a.setNodename("");
					System.out.println("Selected Attribute: " + a.toString());
					Map<String, String> selectedLocatorMap = new HashMap<String, String>();
					if (null != a.getSelectorValue()) {
						String attributeMethod = null;
						if (null != a.getSelectorMethod()) {
							attributeMethod = LocatorType.fromString(a.getSelectorMethod())
									.getPageObjectKey(selectedLanguage);
						}
						selectedLocatorMap.put("attribute", attributeMethod);
						selectedLocatorMap.put("value", a.getSelectorValue());
						System.out.println("attribute: " + a.getSelectorMethod() + " Value: " + a.getSelectorValue());
						a.setSelectedLocator(selectedLocatorMap);
					} else {
					}
					System.out.println("Selectors: " + a.getSelectedLocator());
					Map<String, String> xpaths = a.getXpaths();
					Map<String, String> properties = a.getAttributes();
					properties.values().removeIf(value -> value.isEmpty());
					properties.remove("id");
					properties.remove("class");
					properties.remove("objectspyxpath");
					a.setName(properties.get("name"));
					a.setXpaths(xpaths);
					a.setProperties(properties);
					attributes.add(a);
					obj.setAttributes(a);
					webORObjects.add(obj);
				}
				page.setWebORObjects(webORObjects);
				pages.add(page);
			} else {
				page = new WebORPage();
				System.out.println("Page: " + pagesEntry.getKey());
				String pageSuffix = "Page";
				String stringContainsPage = pagesEntry.getKey().toString().toLowerCase();
				if (stringContainsPage.contains("Page")) {
					pageSuffix = "";
				} else if (pagesEntry.getKey().contains("Page")) {
					pageSuffix = "";
				}
				page.setPageName(pagesEntry.getKey() + pageSuffix);
				pages.add(page);
			}
		}
		webOR.setPages(pages);
		project.setObjectRepository(repo);
		webOR.setPages(pages);
		repo.setWebOR(webOR);

		try {
			if (fileUtils.checkObjectFolderExists(project)) {
				reader.writeJson(project);
				lblInfo.setVisible(true);
				lblInfo.setForeground(Color.decode(ColorCode.HAPPYBUTTON.getCode()));
				if (pro.getLocation().endsWith("\\")) {
					lblInfo.setText("The file was saved and is available at Location: " + pro.getLocation()
							+ RepositoryConstants.OBJECTREPOSITORY.getValue());
				} else {
					lblInfo.setText("The file was saved and is available at Location: " + pro.getLocation()
							+ File.separator + RepositoryConstants.OBJECTREPOSITORY.getValue());
				}
				MessageTimer timer = new MessageTimer(lblInfo);
				timer.start();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	public void serializeObject(Project pro, Map<String, Map<String, Attribute>> pagesMap,
			Map<String, Attribute> objAttrMap, JLabel lblInfo, String attributeKey, String attributeValue,
			WebDriver webDriver, JTree pageExplorer) {
		JSONReader reader = new JSONReader();
		String filePath = pro.getLocation() + File.separator + RepositoryConstants.OBJECTREPOSITORY.getValue()
				+ File.separator + pro.getProjectName() + ".json";

		File file = new File(filePath);
		WebORPage page = null;
		List<WebORPage> pages = null;
		List<WebORObject> webORObjects = null;
		WebOR webOR = null;
		List<Attribute> attributes = null;
		ObjectRepository repo = null;
		Project project = null;
		boolean isFileExist = file.exists();

		if (null == pageExplorer.getSelectionPath()) {
			lblInfo.setVisible(true);
			lblInfo.setForeground(Color.RED);
			lblInfo.setText("Please select the pagename.");
			MessageTimer timer = new MessageTimer(lblInfo);
			timer.start();
			return;
		}
		DefaultMutableTreeNode selectedElement = (DefaultMutableTreeNode) pageExplorer.getSelectionPath()
				.getLastPathComponent();
		String selectedPage = null;
		int selectionCount = pageExplorer.getSelectionPath().getParentPath().toString().split(",").length;
		System.out.println("Parent Element: " + selectedElement.getParent().toString());
		if (selectedElement.isLeaf() && selectionCount == 1) {
			lblInfo.setVisible(true);
			lblInfo.setForeground(Color.RED);
			lblInfo.setText("No objects available in pages.");
			MessageTimer timer = new MessageTimer(lblInfo);
			timer.start();
			return;
		} else if (selectionCount > 1) {
			lblInfo.setVisible(true);
			lblInfo.setForeground(Color.RED);
			lblInfo.setText("Please select the pagename.");
			MessageTimer timer = new MessageTimer(lblInfo);
			timer.start();
			return;
		}

		TreePath[] selectedRows = pageExplorer.getSelectionPaths();
		if (isFileExist) {
			try {
				project = reader.readJson(filePath);
				project.setProjectName(pro.getProjectName());
				project.setLocation(pro.getLocation());
				repo = project.getObjectRepository();
				webOR = repo.getWebOR();
				pages = webOR.getPages();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			project = new Project();
			project.setProjectName(pro.getProjectName());
			project.setLocation(pro.getLocation());
			repo = new ObjectRepository();
			webOR = new WebOR();
			webOR.setObjectRepoName("WebOR");
			pages = new ArrayList<>();
			page = new WebORPage();
			page.setPageName(selectedPage);
		}
		try {
			for (TreePath tree : selectedRows) {
				selectedPage = tree.getLastPathComponent().toString();
				System.out.println("Selected Page: " + selectedPage);
				objAttrMap = pagesMap.get(selectedPage);
				System.out.println("Object Attribute Map: " + objAttrMap.toString());
				boolean isSamePage = false;
				for (WebORPage orPage : pages) {
					if (null != orPage && orPage.getPageName().equalsIgnoreCase(selectedPage)) {
						page = orPage;
						isSamePage = true;
					}
				}
				if (!isSamePage) {
					page = new WebORPage();
					page.setPageName(selectedPage);
				}

				webORObjects = new ArrayList<>();
				for (Map.Entry<String, Attribute> attributeEntry : objAttrMap.entrySet()) {
					attributes = new ArrayList<>();
					WebORObject obj = new WebORObject();
					obj.setObjectName(attributeEntry.getKey());
					Attribute a = attributeEntry.getValue();
					Map<String, String> selectedLocatorMap = new HashMap<String, String>();
					selectedLocatorMap.put("attribute", a.getSelectorMethod());
					selectedLocatorMap.put("value", a.getSelectorValue());
					a.setSelectedLocator(selectedLocatorMap);
					Map<String, String> xpaths = a.getXpaths();
					Map<String, String> properties = a.getAttributes();
					a.setXpaths(xpaths);
					a.setProperties(properties);
					attributes.add(a);
					obj.setAttributes(a);
					webORObjects.add(obj);
				}
				page.setWebORObjects(webORObjects);
				if (!isSamePage) {
					pages.add(page);
				}
				if (!isFileExist) {
					project.setObjectRepository(repo);
					webOR.setPages(pages);
					repo.setWebOR(webOR);
				}
			}
			if (fileUtils.checkObjectFolderExists(project)) {
				reader.writeJson(project);
				lblInfo.setVisible(true);
				lblInfo.setForeground(Color.decode(ColorCode.HAPPYBUTTON.getCode()));
				lblInfo.setText("Saved Successfully. Location:  " + pro.getLocation() + File.separator
						+ RepositoryConstants.OBJECTREPOSITORY.getValue());
				MessageTimer timer = new MessageTimer(lblInfo);
				timer.start();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void removeData(String elementName, String pageName, Map<String, Map<String, Attribute>> pagesMap,
			boolean isPage, Map<String, List<Object[]>> tableAttributes, JComboBox<String> cmbPageName,
			JTable tblAttributes) {
		System.out.println("Elements to be deleted:" + elementName + "IsPage: " + isPage + "Page Name: " + pageName);
		if (isPage) {
			flagDelete = true;
			System.out.println("PagesMap: " + pagesMap.toString());
			pagesMap.remove(pageName.trim());
			System.out.println("Index: " + ((DefaultComboBoxModel) cmbPageName.getModel()).getIndexOf(pageName));
			if (((DefaultComboBoxModel) cmbPageName.getModel()).getIndexOf(pageName) >= 0) {
				cmbPageName.removeItem(pageName);
			}
			System.out.println("Pages Removed");
		} else {
			flagDelete = true;
			Map<String, Attribute> attributeMap = pagesMap.get(pageName.trim());
			System.out.println("PagesMap: " + pagesMap.toString());
			attributeMap.remove(elementName);
			tableAttributes.remove(elementName);
		}
		System.out.println("Before Model: " + tblAttributes.getModel().getColumnCount());
		tblAttributes.setModel(new DefaultTableModel());
		System.out.println("After Model: " + tblAttributes.getModel().getColumnCount());
		System.out.println("Pages after deletion: " + pagesMap.toString());
	}

	public static boolean isFlagDelete() {
		return flagDelete;
	}

	public static void setFlagDelete(boolean flagDelete) {
		WebElementService.flagDelete = flagDelete;
	}

	public void removeElementFromExplorer(JTree pageExplorer, Object selectedNode, boolean isPage, JTextField txtPage,
			JTextField txtObject) {
		DefaultMutableTreeNode selectednode = (DefaultMutableTreeNode) selectedNode;
		System.out.println("Parent Node: " + pageExplorer.getSelectionPath().getPathComponent(1).toString());
		boolean isParentDeleted = false;
		System.out.println("Child Count: " + selectednode.getParent().getChildCount());
		if (selectednode.getParent().getChildCount() == 1) {
			isParentDeleted = true;
		}
		System.out.println("Selected node for delete: " + selectednode.toString());
		if (isPage) {
			txtPage.setText("");
		} else {
			txtObject.setText("");
		}
		DefaultTreeModel model = (DefaultTreeModel) pageExplorer.getModel();
		model.removeNodeFromParent(selectednode);
	}

	public boolean isAngularElement(Attribute attr) {
		if (null != attr && null != attr.getAttributes() && !attr.getAttributes().isEmpty()) {
			if (attr.getAttributes().keySet().stream().anyMatch(x -> x.contains("ng"))) {
				return true;
			}
		}
		return false;
	}

	public void updatePagesMap(String currentPageName, String newPageName,
			Map<String, Map<String, Attribute>> pagesMap) {
		currentPageName = currentPageName.replace("Page_", "");
		newPageName = newPageName.replace("Page_", "");
		if (pagesMap.get(currentPageName) != null) {
			Map<String, Attribute> attributeMap = pagesMap.get(currentPageName);
			pagesMap.remove(currentPageName);
			pagesMap.put(newPageName, attributeMap);
		} else {
			Map<String, Attribute> attributeMap = new HashMap<String, Attribute>();
			pagesMap.remove(currentPageName);
			pagesMap.put(newPageName, attributeMap);
		}

		System.out.println("PagesMap after change: " + pagesMap.toString());
	}

	public void updatePageAlias(String currentPageName, String newPageName, Map<String, String> pageAliases,
			String title) {
		newPageName = newPageName.replace("Page_", "");
		pageAliases.put(title.trim(), newPageName);
		System.out.println("Page alias in key press event: " + pageAliases.toString());
	}

	public void updateObjectMap(String currentObjectName, String newObjectName, Map<String, Attribute> objAttrMap) {
		System.out.println("Current Obj Name:" + currentObjectName + "New Object Name: " + newObjectName);
		System.out.println("Object Attr Map: " + objAttrMap.toString());
		Attribute attr = objAttrMap.get(currentObjectName);
		objAttrMap.put(newObjectName, attr);
		objAttrMap.remove(currentObjectName);
	}

	public String checkElementId(String xpath, String id) {
		System.out.println("Given: XPath: " + xpath + "  ID: " + id);
		if (xpath.contains("]/")) {
			return "";
		}
		System.out.println("After validation: " + id);
		return id;
	}
}
