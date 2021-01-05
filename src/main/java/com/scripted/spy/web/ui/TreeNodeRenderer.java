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

import java.awt.Component;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import com.scripted.spy.web.util.StringUtil;

public class TreeNodeRenderer extends DefaultTreeCellRenderer {
	
	private static final long serialVersionUID = 1L;
	private Map<String, ImageIcon> dataMap;
	private StringUtil stringUtil  = new StringUtil();
	public TreeNodeRenderer(Map<String, ImageIcon> dataMap) {
		this.dataMap = dataMap;
	}

	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf,
			int row, boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
		if (leaf) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
			String text = node.getUserObject().toString();
			if(node != null && null != node.getParent() &&node.getParent().toString().equalsIgnoreCase("jtree")) {
				setIcon(this.dataMap.get("Page"));
				return this;
			}
			String tagName = stringUtil.getElementTag(text);
			if(null == this.dataMap.get(tagName)) {
				setIcon(this.dataMap.get("default"));
			}else {
				setIcon(this.dataMap.get(tagName));
			}
		} else {
			setIcon(this.dataMap.get("parentnode"));
		}
		return this;
	}
}
