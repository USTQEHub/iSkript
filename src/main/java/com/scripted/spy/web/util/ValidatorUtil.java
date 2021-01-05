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

import java.awt.Color;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import org.apache.commons.validator.routines.UrlValidator;

public class ValidatorUtil {

	private Border border = BorderFactory.createLineBorder(Color.red, 1);

	public boolean isURLAvailable(JTextField txtURL) {
		if (null != txtURL && txtURL.getText().trim().length() != 0) {
			return true;
		}
		txtURL.setBorder(border);
		return false;
	}
	
	public boolean verifyURL(JTextField txtURL, JLabel lblBrowserInfo) {
		boolean isValid = false;
		String  formattedUrl = null;
		if(null != txtURL && null != txtURL.getText()) {
			String  givenUrl = txtURL.getText();
			System.out.println(givenUrl);
			formattedUrl = getQualifiedURL(givenUrl);
			isValid = validateUrl(formattedUrl, lblBrowserInfo);
			if(isValid) txtURL.setText(formattedUrl);
		}
		System.out.println("Qualified URL is : " + formattedUrl);
		return isValid;
	}
	
	private String getQualifiedURL(String url) {
		if (null != url && !url.contains("http")) {
			if (!url.contains("www")) {
				url = "https://www." + url;
			} else {
				url = "https://" + url;
			}
			System.out.println(url);
			return url;
		}
	
		return url;
	}

	private boolean validateUrl(String url, JLabel lblBrowserInfo) {
		boolean isValid = false;
		String[] schemes = { "http", "https" };
		UrlValidator urlValidator = new UrlValidator(schemes,UrlValidator.ALLOW_LOCAL_URLS);
			if (urlValidator.isValid(url)) {
				System.out.println("url is valid");
				isValid = validateURLAgain(url);
		}
			else {
				displayURLError(lblBrowserInfo);
			}
		return isValid;
	}
	
	public boolean validateURLAgain(String url) {
		boolean isValid = false;
		
		try {
			System.out.println("Validating URL again");
			new URL(url).toURI();
			isValid = true;
			System.out.println("Hey I'm valid");
		} catch (MalformedURLException | URISyntaxException e) {
			e.printStackTrace();
			System.out.println("Hey I'm invalid");
			isValid = false;
		} 
		
		return isValid;
	}
	
	public boolean isPageNameValid(String pageName) {
		Pattern p = Pattern.compile("[^a-z ]", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(pageName);
		return m.find();
	}
	
	public boolean isProjectNameValid(String pageName) {
		Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(pageName);
		return m.find();
	}
	
	
	private void displayURLError(JLabel lblBrowserInfo) {
		String errorMessage = "Please enter a valid URL";
		System.out.println("Error Message: " + errorMessage);
		lblBrowserInfo.setVisible(true);
		lblBrowserInfo.setText(errorMessage);
		lblBrowserInfo.setForeground(Color.RED);
		MessageTimer timer = new MessageTimer(lblBrowserInfo);
		timer.start();
	}
	
	public boolean isFileExists(String projectLocation, String projectName) {
		String fileName = projectLocation + File.separator + RepositoryConstants.OBJECTREPOSITORY.getValue()
				+File.separator+ projectName + ".json";
		System.out.println("File Name: " + fileName);
		File importedFile = new File(fileName);
		if (importedFile.exists()) {
			return true;
		}
		return false;
	}

}
