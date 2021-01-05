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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JTable;

public class RadioButtonEditor extends DefaultCellEditor implements ItemListener {
	
	
	private static final long serialVersionUID = 1L;
	private JRadioButton button;
	   public RadioButtonEditor(JCheckBox checkBox) {
	      super(checkBox);
	   }
	   public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		   System.out.println("Value: " + value);
	      if (value==null) return null;
	      button = (JRadioButton)value;
	      button.addItemListener((ItemListener) this);
	      return (Component)value;
	   }
	   public Object getCellEditorValue() {
	      button.removeItemListener((ItemListener) this);
	      return button;
	   }
	  public void itemStateChanged(ItemEvent e) 
	  { 
		  super.fireEditingStopped(); 
	  }
	

}
