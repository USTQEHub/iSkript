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

public enum ColorCode {

	TEXT("#2A3F54"), HAPPYBUTTON("#1ABB9C"), NOTHAPPYBUTTON("#2A3F54"), BUTTONTEXT("#FFFFFF"), INPUTBACKGROUND(
			"#F3F3F3"), PANELBG("#FFFFFF"), BUTTONSELECTED("#2A3F54"), MENUBG(
					"#F2FBFF"), SCROLLPANEBG("#F9F9F9"), BUTTONHOVER("#209980"), BUTTONCANCELHOVER("#4b5b6e"), ATTRIBUTEHIGHLIGHT("#c9f7ef"), GRIDLINECOLOR("#E0E0E0");

	private String code;

	ColorCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}
}
