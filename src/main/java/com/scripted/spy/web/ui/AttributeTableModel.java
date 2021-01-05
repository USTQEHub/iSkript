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

 import javax.swing.table.DefaultTableModel;

public class AttributeTableModel extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	public AttributeTableModel() {
		super(new String[] {"Attribute", "Value"}, 0);
	}
	
}
