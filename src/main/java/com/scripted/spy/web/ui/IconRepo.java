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

package com.scripted.spy.web.ui;

import javax.swing.ImageIcon;

public interface IconRepo {
	
	//Browser Icons
	ImageIcon CHROME = new ImageIcon(IconRepo.class.getResource("/icons/chrome-sm.png"));
	
	//Web Element Icons
	ImageIcon INPUT = new ImageIcon(IconRepo.class.getResource("/icons/Input.png"));
	ImageIcon DIV = new ImageIcon(IconRepo.class.getResource("/icons/Div.png"));
	ImageIcon ANCHOR = new ImageIcon(IconRepo.class.getResource("/icons/link.png"));
	ImageIcon IMAGE = new ImageIcon(IconRepo.class.getResource("/icons/picture.png"));
	ImageIcon BUTTON = new ImageIcon(IconRepo.class.getResource("/icons/btn-1.png"));
	ImageIcon HEADER = new ImageIcon(IconRepo.class.getResource("/icons/Div.png"));
	ImageIcon FOLDER = new ImageIcon(IconRepo.class.getResource("/icons/folder.png"));
	ImageIcon CHECKBOX = new ImageIcon(IconRepo.class.getResource("/icons/chk_box.png"));
	ImageIcon RADIO = new ImageIcon(IconRepo.class.getResource("/icons/radio.png"));
	ImageIcon TABLE1 = new ImageIcon(IconRepo.class.getResource("/icons/table_1.png"));
	
	//Button Icons
	ImageIcon BROWSE = new ImageIcon(IconRepo.class.getResource("/icons/browse.png"));
	ImageIcon CAPTURE = new ImageIcon(IconRepo.class.getResource("/icons/capture.png"));
	ImageIcon SAVE = new ImageIcon(IconRepo.class.getResource("/icons/save.png"));
	ImageIcon NEWPAGE = new ImageIcon(IconRepo.class.getResource("/icons/newpage.png"));
	ImageIcon DELETE = new ImageIcon(IconRepo.class.getResource("/icons/delete.png"));
	ImageIcon HIGHLIGHT = new ImageIcon(IconRepo.class.getResource("/icons/highlight.png"));
	ImageIcon ADDPAGE = new ImageIcon(IconRepo.class.getResource("/icons/addpage.png"));
	
	//Home Page Technology basedIcon
	ImageIcon EXWEB = new ImageIcon(IconRepo.class.getResource("/icons/web-ex.png"));
	ImageIcon WEB = new ImageIcon(IconRepo.class.getResource("/icons/web-sm.png"));
	ImageIcon INFO = new ImageIcon(IconRepo.class.getResource("/icons/info.png"));
	
	ImageIcon ABOUTLOGO = new ImageIcon(IconRepo.class.getResource("/icons/sk_spyweb_logo2x.png"));
	ImageIcon SPLASHSCREEN = new ImageIcon(IconRepo.class.getResource("/icons/splash.png"));
}