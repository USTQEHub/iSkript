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

package com.scripted.spy.web.test;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class RadioButtonRenderer implements TableCellRenderer {

	public Component getTableCellRendererComponent(JTable tblAttributes, Object value, boolean isSelected,   boolean hasFocus, int row, int column) {
	      if (value==null) return null;
	      //JRadioButton b = new JRadioButton(String.valueOf(value));
	      return (Component)value;
	   }

}
