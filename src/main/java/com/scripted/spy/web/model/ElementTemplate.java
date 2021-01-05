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

package com.scripted.spy.web.model;

import java.io.Serializable;

public class ElementTemplate implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	private String value;
	private String type;
	private String selectorMethod;
	private boolean angularElement;
	private String findByChooser;
	private String iframe;
	
	public void setIframe(String iframe) {
		this.iframe=iframe;	
	}
		
	public String getIframe() {
		return iframe;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSelectorMethod() {
		return selectorMethod;
	}

	public void setSelectorMethod(String selectorMethod) {
		this.selectorMethod = selectorMethod;
	}

	public boolean isAngularElement() {
		return angularElement;
	}

	public void setAngularElement(boolean angularElement) {
		this.angularElement = angularElement;
	}

	public String getFindByChooser() {
		return findByChooser;
	}

	public void setFindByChooser(String findByChooser) {
		this.findByChooser = findByChooser;
	}

	@Override
	public String toString() {
		return "ElementTemplate [name=" + name + ", value=" + value + ", type=" + type + ", selectorMethod="
				+ selectorMethod + ", angularElement=" + angularElement + ",iframe="+iframe+", findByChooser=" + findByChooser + "]";
	}

}
