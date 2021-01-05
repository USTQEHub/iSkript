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

public class ScriptingService {
	
	String closePopup = "document.SkMate_Page_Recorder.closeForm();";
	String popupInfo = " document.getElementById('infoSpan').style.visibility = 'visible';" +
			"setTimeout(function(){ document.getElementById('infoSpan').style.visibility = 'hidden' }, 4000);"
			;
	String script="var target = '';\r\n" + 
			"var isAvailable = 'false';\r\n" + 
			"var isRefreshed = 'false';\r\n" + 
			"var infoSpan = document.createElement('div');\r\n" + 
			"infoSpan.id = 'infoSpan';\r\n" + 
			"infoSpan.innerHTML = 'Use this shortcut key, <strong>ALT+ C</strong> to capture the element.';\r\n" + 
			"var style = document.createElement('style');\r\n" + 
			"style.innerHTML = '#infoSpan {font-family: Arial;font-size: larger;top: 1px;position: relative;color: #ffffff;background-color: #000000;padding: 20px;width: 1400px;height: 50px;}';\r\n" +
			"document.head.appendChild(style);\r\n" + 
			"document.body.appendChild(infoSpan);\r\n" + 
			"var imported = document.createElement('script');\n" + 
			"imported.src = 'addelementpopup.js';\n" + 
			"document.head.appendChild(imported);" +
			"localStorage.setItem('isRefreshed', isRefreshed);\r\n" + 
			"function doRefresh() {\r\n" + 
			"	console.log('Page is refreshed');\r\n" + 
			"	isRefreshed = 'true';\r\n" + 
			"	localStorage.setItem('isRefreshed', isRefreshed);\r\n" + 
			"}\r\n" + 
			"window.onbeforeunload = doRefresh;\r\n" + 
			"\r\n" + 
			"document.onmouseover = function (event) {\r\n" + 
			"	if (event === undefined) event = window.event;\r\n" + 
			"	console.log('Script is executing' + event.clientX + 'Y: ' + event.clientY);\r\n" + 
			"	target = 'target' in event ? event.target : event.srcElement;\r\n" +
			"console.log('Script is executing:' +target);"+
			"	if (target.id === 'infoSpan' && event.clientY <= 50) {\r\n" + 
			"		console.log('Hidden');\r\n" + 
			"		target.style.visibility = 'hidden';\r\n" + 
			"	} else if (event.clientY > 50) {\r\n" + 
			"		console.log('Visible');\r\n" + 
			"		document.getElementById('infoSpan').style.visibility = 'visible';\r\n" + 
			"	}\r\n" + 
			"	target.style.border = '2px solid orange';\r\n" + 
			"	return target;\r\n" + 
			"}\r\n" + 
			"\r\n" + 
			"function getId(element) {\r\n" + 
			"	if (element.id !== '')\r\n" + 
			"		return 'id(\"'+ element.id +'\")';\r\n" + 
			"}\r\n" + 
			"document.onkeydown = function (event) {\r\n" + 
			"	if (event === undefined) event = window.event;\r\n" + 
			"	if (event.altKey && event.which == 67) {\r\n" + 
			"		console.log('Keyboard shortcut working!');\r\n" + 
			"		target.style.border = '2px solid green';\r\n" + 
			"		var xpath = getElementXPath(target);\r\n" + 
			"		var id = getElementId(target);\r\n" + 
			"		var css = generateCSS(target);\r\n" + 
			"		var classname = getElementClass(target);\r\n" +
			"var items = {};"+
			"		var data = {'id': id, 'xpath': xpath, 'classname': classname, css: css,'tagName':target.tagName,'textContent':target.textContent,'location':''};\n" + 
			"for (index = 0; index < target.attributes.length; ++index) \n" + 
			"		{ \n" + 
			"		items[target.attributes[index].name] = target.attributes[index].value \n" + 
			"		}; \n" + 
			"		data.attributeMap = items;" +
			"		localStorage.setItem('data', JSON.stringify(data));" +
			"		localStorage.setItem('isAvailable', 'true');\r\n" + 
			"		return false;\r\n" + 
			"	}\r\n" + 
			"}\r\n" + 
			"\r\n" + 
			"function getElementClass(element) {\r\n" + 
			"console.log('Generated Class: ' + element.class);" + 
			"	if (element.class !== '')\r\n" + 
			"console.log('Generated Class: ' + element.class);" + 
			"		return '.(\"'+ element.class +'\")';\r\n" + 
			"}\r\n" + 
			"\r\n" + 
			"function getElementName(element) {\r\n" + 
			"	if (element.name !== '')\r\n" + 
			"		return element.name;\r\n" + 
			"}\r\n" + 
			"\r\n" + 
			"function getElementTag(element) {\r\n" + 
			"	if (element.tagName !== '')\r\n" + 
			"		return element.tagName;\r\n" + 
			"}\r\n" + 
			"\r\n" + 
			"function getElementId(element) {\r\n" + 
			"	if (element.id !== '')\r\n" + 
			"		return element.id;\r\n" + 
			"	var siblings = element.parentNode.childNodes;\r\n" + 
			"	for (var i = 0; i < siblings.length; i++) {\r\n" + 
			"		var sibling = siblings[i];\r\n" + 
			"		if (sibling === element)\r\n" + 
			"			return getElementId(element.parentNode);\r\n" + 
			"	}\r\n" + 
			"}\r\n" + 
			"\r\n" + 
			"function previousElementSibling(element) {\r\n" + 
			"	if (element.previousElementSibling !== 'undefined') {\r\n" + 
			"		return element.previousElementSibling;\r\n" + 
			"	} else {\r\n" + 
			"		while (element = element.previousSibling) {\r\n" + 
			"			if (element.nodeType === 1) {\r\n" + 
			"				return element;\r\n" + 
			"			}\r\n" + 
			"		}\r\n" + 
			"	}\r\n" + 
			"}\r\n" + 
			"\r\n" + 
			"function generateCSS(element) {\r\n" + 
			"	if (!(element instanceof HTMLElement)) {\r\n" + 
			"		return false;\r\n" + 
			"	}\r\n" + 
			"	var path = [];\r\n" + 
			"	while (element.nodeType === Node.ELEMENT_NODE) {\r\n" + 
			"		var selector = element.nodeName;\r\n" + 
			"		if (element.id) {\r\n" + 
			"			selector += ('#' + element.id);\r\n" + 
			"		} else {\r\n" + 
			"			var sibling = element;\r\n" + 
			"			var siblingSelectors = [];\r\n" + 
			"			while (sibling !== null && sibling.nodeType === Node.ELEMENT_NODE) {\r\n" + 
			"				siblingSelectors.unshift(sibling.nodeName);\r\n" + 
			"				sibling = previousElementSibling(sibling);\r\n" + 
			"			}\r\n" + 
			"			if (siblingSelectors[0] !== 'HTML') {\r\n" + 
			"				siblingSelectors[0] = siblingSelectors[0] + ':first-child';\r\n" + 
			"			}\r\n" + 
			"			selector = siblingSelectors.join(' + ');\r\n" + 
			"		}\r\n" + 
			"		path.unshift(selector);\r\n" + 
			"		element = element.parentNode;\r\n" + 
			"	}\r\n" + 
			"	return path.join(' > ');\r\n" + 
			"}\r\n" + 
			"\r\n" + 
			"function getElementXPath(element) {\r\n" + 
			"	if (element.id !== '')\r\n" + 
			"		return '//' + element.tagName.toLowerCase() + '[@id=\"'+element.id+'\"]';\r\n" + 
			"if (element === document.body)\r\n" + 
			"	return element.tagName.toLowerCase();\r\n" + 
			"var ix = 0;\r\n" + 
			"var siblings = element.parentNode.childNodes;\r\n" + 
			"for (var i = 0; i < siblings.length; i++) {\r\n" + 
			"	var sibling = siblings[i];\r\n" + 
			"	if (sibling === element)\r\n" + 
			"		return getElementXPath(element.parentNode) + '/' + element.tagName.toLowerCase() + '[' + (ix + 1) + ']';\r\n" + 
			"	if (sibling.nodeType === 1 && sibling.tagName.toLowerCase() === element.tagName.toLowerCase())\r\n" + 
			"		ix++;\r\n" + 
			"}\r\n" + 
			"}\r\n" + 
			"\r\n" + 
			"function getPageXYZ(element) {\r\n" + 
			"	var x = element.getAttribute(\r\n" + 
			"		'id');\r\n" + 
			"	y = element.getAttribute(\r\n" + 
			"		'name');\r\n" + 
			"	z = element.getAttribute(\r\n" + 
			"		'class');\r\n" + 
			"	return [x, y, z];\r\n" + 
			"}\r\n" + 
			"document.onmouseout = function (event) {\r\n" + 
			"	if (event === undefined) event = window.event;\r\n" + 
			"	var target = 'target' in event ? event.target : event.srcElement;\r\n" + 
			"	target.style.removeProperty('border');\r\n" + 
			"}";
	public String getAddElementPopup() {
		return addElementPopup;
	}
	
	public void setAddElementPopup(String addElementPopup) {
		this.addElementPopup = addElementPopup;
	}

	String xpathExtractorScript = "var xpath=window.localStorage.getItem('xpath');\r\n" + "return xpath;";
	String idExtractorScript = "var id=window.localStorage.getItem('id');\r\n" + "return id;";
	String dataExtractorScript = "var data=window.localStorage.getItem('data');\r\n" + "return data;";
	String cssExtractorScript = "var css=window.localStorage.getItem('css');\r\n" + "return css;";
	String blinkScript = "window.hide = function(arguments[0])\r\n" + 
			"{\r\n" + 
			"	\r\n" + 
			"	arguments[0].style.border = '2px solid green';\r\n" + 
			"}\r\n" + 
			"window.hide = function(arguments[0])\r\n" + 
			"{\r\n" + 
			"	arguments[0].style.border = '0px';\r\n" + 
			"}\r\n" + 
			"console.log(arguments[0]);\r\n" + 
			"for(var i=900; i < 4500; i=i+900)\r\n" + 
			"{\r\n" + 
			"	setTimeout('hide(arguments[0])',i);\r\n" + 
			"	setTimeout('show(arguments[0])',i+450);\r\n" + 
			"}";
	
	String addElementPopup = "(function () {\r\n" + 
			"    var SkMate_Page_Recorder,\r\n" + 
			"    addStyle,\r\n" + 
			"    addStyle1,\r\n" + 
			"    bye,\r\n" + 
			"    createCommand,\r\n" + 
			"    dbg,\r\n" + 
			"    getInputElementsByTypeAndValue,\r\n" + 
			"    getPageXY,\r\n" + 
			"    getCssSelectorOF,\r\n" + 
			"    getElementId,\r\n" + 
			"    getPathTo,\r\n" + 
			"    handler,\r\n" + 
			"    hello,\r\n" + 
			"    prev,\r\n" + 
			"    preventEvent,\r\n" + 
			"    pseudoGuid,\r\n" + 
			"    fullXpath,\r\n" + 
			"    tempcss,\r\n" + 
			"   tempxpath,\r\n" + 
			"    rightClickHandler,\r\n" + 
			"    say,\r\n" + 
			"    popUpForm,\r\n" + 
			"    findfollows,\r\n" + 
			"    getfollowing,\r\n" + 
			"    followsib,\r\n" + 
			"    parentpath,\r\n" + 
			"    iframeDocument,\r\n" + 
			"    data,\r\n" + 
			"    menuxpath,\r\n" + 
			"    menucsspath,\r\n" + 
			"    joint_css,\r\n" + 
			"    relxpath,\r\n" + 
			"    longxpath,\r\n" + 
			"    elementclass;\r\n" + 
			"    var maxIndex = 5;\r\n" + 
			"    var maxId = 3;\r\n" + 
			"    var ELEMENT_NODE = 1;\r\n" + 
			"    var collectionbased = null;\r\n" + 
			"    var followinglinkbasedXpath = null;\r\n" + 
			"    var followingidbasedXpath = null;\r\n" + 
			"    var followingnamebasedXpath = null;\r\n" + 
			"    var followingclassbasedXpath = null;\r\n" + 
			"    var followingattributebasedXpath = null;\r\n" + 
			"    var parentbasedPath = null;\r\n" + 
			"    var textbasedpath = null;\r\n" + 
			"    var precedingidbasedXpath = null;\r\n" + 
			"    var precedingnamebasedXpath = null;\r\n" + 
			"    var precedingclassbasedXpath = null;\r\n" + 
			"    var precedinglinkbasedXpath = null;\r\n" + 
			"    var precedingattributebasedXpath = null;\r\n" + 
			"    var newTabLink = null;\r\n" + 
			"    var iframeid;\r\n" + 
			"    var iframeEle,\r\n" + 
			"    iframeElement,\r\n" + 
			"    iframeLocatorElement;\r\n" + 
			"    var isOverIFrame = false;\r\n" + 
			"    var xmlpath;\r\n" + 
			"    var treeNodeName;\r\n" + 
			"    var clik = false;\r\n" + 
			"    var visibile;\r\n" + 
			"    var isNewWindow;\r\n" + 
			"    var thisIsParentWindow;\r\n" + 
			"    var tempNodeName = localStorage.getItem(\"nodeName\");\r\n" + 
			"   \r\n" + 
			"    window.name = thisIsParentWindow;\r\n" + 
			"    \r\n" + 
			"\r\n" + 
			"    var iframeDetails = {\r\n" + 
			"        iframeid: \"\",\r\n" + 
			"        iframexpath: \"\",\r\n" + 
			"        set id(id) {\r\n" + 
			"            this.iframeid = id;\r\n" + 
			"        },\r\n" + 
			"        set xpath(xpath) {\r\n" + 
			"            this.iframexpath = xpath;\r\n" + 
			"        }\r\n" + 
			"    };\r\n" + 
			"\r\n" + 
			"    document.body.addEventListener('click', function (e) {\r\n" + 
			"        console.log(e.target);\r\n" + 
			"        if (e.target.hasAttribute('href')) {\r\n" + 
			"            localStorage.setItem('LinkNodeClicked', 'true');\r\n" + 
			"        }\r\n" + 
			"    });\r\n" + 
			"\r\n" + 
			"    var isRefreshed = 'false';\r\n" + 
			"    localStorage.setItem('isRefreshed', isRefreshed);\r\n" + 
			"    function doRefresh() {\r\n" + 
			"        console.log('Page is refreshed');\r\n" + 
			"        isRefreshed = 'true';\r\n" + 
			"        localStorage.setItem('isRefreshed', isRefreshed);\r\n" + 
			"    }\r\n" + 
			"    window.onbeforeunload = doRefresh;\r\n" + 
			"    document.addEventListener(\"visibilitychange\", function () {\r\n" + 
			"        if (isNewWindow == true) {\r\n" + 
			"            if (document.hidden) {\r\n" + 
			"                localStorage.setItem('isAvailable', 'false');\r\n" + 
			"                localStorage.setItem('newTabOpened', 'true');\r\n" + 
			"                visible = document.visibilityState;\r\n" + 
			"                console.log(\"Hidden\");\r\n" + 
			"            } else {\r\n" + 
			"                localStorage.setItem('isAvailable', 'false');\r\n" + 
			"                localStorage.setItem('newTabOpened', null);\r\n" + 
			"                console.log(\"Visible\");\r\n" + 
			"            }\r\n" + 
			"        }\r\n" + 
			"    });\r\n" + 
			"\r\n" + 
			"   \r\n" + 
			"\r\n" + 
			"    say = function (something) {\r\n" + 
			"        if (typeof console !== \"undefined\" && console !== null) {\r\n" + 
			"            return console.log(something);\r\n" + 
			"        }\r\n" + 
			"    };\r\n" + 
			"\r\n" + 
			"    dbg = function (something) {\r\n" + 
			"        if (typeof console !== \"undefined\" && console !== null) {\r\n" + 
			"            return console.log(\"DBG:\" + something);\r\n" + 
			"        }\r\n" + 
			"    };\r\n" + 
			"\r\n" + 
			"    hello = function (something) {\r\n" + 
			"        return dbg(\"(begin): \" + something);\r\n" + 
			"    };\r\n" + 
			"\r\n" + 
			"    bye = function (something) {\r\n" + 
			"        return dbg(\"(end): \" + something);\r\n" + 
			"    };\r\n" + 
			"    document.addEventListener('visibilitychange', handleVisibilityChange, false);\r\n" + 
			"\r\n" + 
			"    function handleVisibilityChange() {\r\n" + 
			"        if (document.visibilityState == \"hidden\") {\r\n" + 
			"            console.log('HIDDEN');\r\n" + 
			"            localStorage.setItem('isAvailable', 'false');\r\n" + 
			"            localStorage.setItem('newTabOpened', 'true');\r\n" + 
			"        } else {\r\n" + 
			"            console.log(\"VISIBLE\");\r\n" + 
			"            localStorage.setItem('isAvailable', 'false');\r\n" + 
			"            localStorage.setItem('newTabOpened', null);\r\n" + 
			"        }\r\n" + 
			"    }\r\n" + 
			"\r\n" + 
			"   \r\n" + 
			"    var body = document.getElementsByTagName('body')[0];\r\n" + 
			"    body.addEventListener('click', function (e) {\r\n" + 
			"        if (e.target.hasAttribute('onclick')) {\r\n" + 
			"            var browserPrefixes = ['moz', 'ms', 'o', 'webkit'],\r\n" + 
			"            isVisible = true; // internal flag, defaults to true\r\n" + 
			"\r\n" + 
			"            // get the correct attribute name\r\n" + 
			"            function getHiddenPropertyName(prefix) {\r\n" + 
			"                return (prefix ? prefix + 'Hidden' : 'hidden');\r\n" + 
			"            }\r\n" + 
			"\r\n" + 
			"            // get the correct event name\r\n" + 
			"            function getVisibilityEvent(prefix) {\r\n" + 
			"                return (prefix ? prefix : '') + 'visibilitychange';\r\n" + 
			"            }\r\n" + 
			"\r\n" + 
			"            // get current browser vendor prefix\r\n" + 
			"            function getBrowserPrefix() {\r\n" + 
			"                for (var i = 0; i < browserPrefixes.length; i++) {\r\n" + 
			"                    if (getHiddenPropertyName(browserPrefixes[i])in document) {\r\n" + 
			"                        // return vendor prefix\r\n" + 
			"                        return browserPrefixes[i];\r\n" + 
			"                    }\r\n" + 
			"                }\r\n" + 
			"\r\n" + 
			"                // no vendor prefix needed\r\n" + 
			"                return null;\r\n" + 
			"            }\r\n" + 
			"\r\n" + 
			"            // bind and handle events\r\n" + 
			"            var browserPrefix = getBrowserPrefix(),\r\n" + 
			"            hiddenPropertyName = getHiddenPropertyName(browserPrefix),\r\n" + 
			"            visibilityEventName = getVisibilityEvent(browserPrefix);\r\n" + 
			"\r\n" + 
			"            function onVisible() {\r\n" + 
			"                // prevent double execution\r\n" + 
			"                if (isVisible) {\r\n" + 
			"                    return;\r\n" + 
			"                }\r\n" + 
			"\r\n" + 
			"                // change flag value\r\n" + 
			"                isVisible = true;\r\n" + 
			"                console.log('visible')\r\n" + 
			"                localStorage.setItem('isAvailable', 'false');\r\n" + 
			"                localStorage.setItem('newTabOpened', null);\r\n" + 
			"            }\r\n" + 
			"\r\n" + 
			"            function onHidden() {\r\n" + 
			"                // prevent double execution\r\n" + 
			"                if (!isVisible) {\r\n" + 
			"                    return;\r\n" + 
			"                }\r\n" + 
			"\r\n" + 
			"                // change flag value\r\n" + 
			"                isVisible = false;\r\n" + 
			"                console.log('hidden')\r\n" + 
			"                localStorage.setItem('isAvailable', 'false');\r\n" + 
			"                localStorage.setItem('newTabOpened', 'true');\r\n" + 
			"            }\r\n" + 
			"\r\n" + 
			"            function handleVisibilityChange(forcedFlag) {\r\n" + 
			"                // forcedFlag is a boolean when this event handler is triggered by a\r\n" + 
			"                // focus or blur eventotherwise it's an Event object\r\n" + 
			"                if (typeof forcedFlag === \"boolean\") {\r\n" + 
			"                    if (forcedFlag) {\r\n" + 
			"                        return onVisible();\r\n" + 
			"                    }\r\n" + 
			"\r\n" + 
			"                    return onHidden();\r\n" + 
			"                }\r\n" + 
			"\r\n" + 
			"                if (document[hiddenPropertyName]) {\r\n" + 
			"                    return onHidden();\r\n" + 
			"                }\r\n" + 
			"\r\n" + 
			"                return onVisible();\r\n" + 
			"            }\r\n" + 
			"\r\n" + 
			"            document.addEventListener(visibilityEventName, handleVisibilityChange, false);\r\n" + 
			"\r\n" + 
			"            // extra event listeners for better behaviour\r\n" + 
			"            document.addEventListener('focus', function () {\r\n" + 
			"                handleVisibilityChange(true);\r\n" + 
			"            }, false);\r\n" + 
			"\r\n" + 
			"            document.addEventListener('blur', function () {\r\n" + 
			"                handleVisibilityChange(false);\r\n" + 
			"            }, false);\r\n" + 
			"\r\n" + 
			"            window.addEventListener('focus', function () {\r\n" + 
			"                handleVisibilityChange(true);\r\n" + 
			"            }, false);\r\n" + 
			"\r\n" + 
			"            window.addEventListener('blur', function () {\r\n" + 
			"                handleVisibilityChange(false);\r\n" + 
			"            }, false);\r\n" + 
			"        }\r\n" + 
			"    }, false);\r\n" + 
			"\r\n" + 
			"   \r\n" + 
			"\r\n" + 
			"    // To iterate all attributes xpath\r\n" + 
			"    try {\r\n" + 
			"        xpathAttributes(attributeElement, tagName, element);\r\n" + 
			"    } catch (e) {}\r\n" + 
			"    function xpathAttributes(attributeElement, tagName, element) {\r\n" + 
			"        addAllXpathAttributesBbased(attributeElement, tagName, element);\r\n" + 
			"    }\r\n" + 
			"\r\n" + 
			"    // to find label - following xpath only if tag name name is 'input or textarea'\r\n" + 
			"    try {\r\n" + 
			"        if ((tagName === 'input' || tagName === 'textarea')) {\r\n" + 
			"            findLabel(element, tagName)\r\n" + 
			"        }\r\n" + 
			"    } catch (e) {}\r\n" + 
			"    function extractElefromNode(ele, array) {\r\n" + 
			"\r\n" + 
			"        return null;\r\n" + 
			"    }\r\n" + 
			"    function getXPathWithPosition(ele) {\r\n" + 
			"        let rowsPath = [];\r\n" + 
			"        var finalString;\r\n" + 
			"        while (ele.nodeType === 1) {\r\n" + 
			"            let tag = ele.tagName.toLowerCase();\r\n" + 
			"            let prevSib = ele,\r\n" + 
			"            position = 1;\r\n" + 
			"            var flag = false;\r\n" + 
			"            var result = [],\r\n" + 
			"            node = ele.parentNode.firstChild;\r\n" + 
			"            while (node) {\r\n" + 
			"                // var tags = node.tagName.toLowerCase();\r\n" + 
			"                if (node !== ele && node.nodeType === Node.ELEMENT_NODE)\r\n" + 
			"                    result.push(node.tagName.toLowerCase());\r\n" + 
			"                node = node.nextElementSibling || node.nextSibling;\r\n" + 
			"            }\r\n" + 
			"            if (prevSib.previousElementSibling != null) {\r\n" + 
			"                while (prevSib = prevSib.previousElementSibling) {\r\n" + 
			"\r\n" + 
			"                    if (prevSib.tagName.toLowerCase() == tag) {\r\n" + 
			"                        flag = true;\r\n" + 
			"                        position++;\r\n" + 
			"                    }\r\n" + 
			"                }\r\n" + 
			"            }\r\n" + 
			"\r\n" + 
			"            prevSib = ele;\r\n" + 
			"            if (flag == true || result.includes(prevSib.tagName.toLowerCase())) {\r\n" + 
			"                tag += `[${position}]`\r\n" + 
			"                console.log(\"tag: \" + tag);\r\n" + 
			"            }\r\n" + 
			"            \r\n" + 
			"\r\n" + 
			"            rowsPath.unshift(tag);\r\n" + 
			"\r\n" + 
			"            ele = ele.parentNode\r\n" + 
			"        }\r\n" + 
			"        finalString = rowsPath.join('/');\r\n" + 
			"        if (finalString.includes('/svg')) {\r\n" + 
			"            var len = finalString.length;\r\n" + 
			"            var start = finalString.lastIndexOf(\"/\") - 15;\r\n" + 
			"            var hasSvg = finalString.substring(start, len);\r\n" + 
			"            if (hasSvg.includes('/svg')) {\r\n" + 
			"                var xpath_delimiter = \"/svg\",\r\n" + 
			"                start = 1;\r\n" + 
			"                var xpath_tokens = finalString.split(xpath_delimiter).slice(0, start);\r\n" + 
			"                var leftwing = xpath_tokens.join(xpath_delimiter);\r\n" + 
			"                finalString = leftwing + \"/*[name()='svg']\";\r\n" + 
			"            }\r\n" + 
			"\r\n" + 
			"           \r\n" + 
			"        }\r\n" + 
			"        return finalString;\r\n" + 
			"    }\r\n" + 
			"\r\n" + 
			"    function addPrecedingSibling(folSib, tagName) {\r\n" + 
			"        try {\r\n" + 
			"            precedingBasedXpath = null;\r\n" + 
			"            precedingclassbasedXpath = null;\r\n" + 
			"            precedingTextbasedXpath = null;\r\n" + 
			"            precedingattributebasedXpath = null;\r\n" + 
			"            let classHasSpace = false;\r\n" + 
			"            let precedarray = [];\r\n" + 
			"            let temp;\r\n" + 
			"            let folSibAttributes = folSib.attributes;\r\n" + 
			"            let folSibTextContent = folSib.textContent;\r\n" + 
			"           \r\n" + 
			"            while (folSib) {\r\n" + 
			"                if (folSibAttributes.length != 0 && folSibTextContent != null || folSibTextContent != '') {\r\n" + 
			"                    break;\r\n" + 
			"                } else {\r\n" + 
			"                    folSib = folSib.nextElementSibling;\r\n" + 
			"                    folSibAttributes = folSib.attributes;\r\n" + 
			"                    folSibTextContent = folSib.textContent;\r\n" + 
			"                }\r\n" + 
			"            }\r\n" + 
			"           \r\n" + 
			"            let followingSiblingTagName = folSib.tagName.toLowerCase();\r\n" + 
			"            Array.prototype.slice.call(folSib.attributes)\r\n" + 
			"            .forEach(function (item) {\r\n" + 
			"                if (!(filterAttributesFromElement(item))) {\r\n" + 
			"                    let tempvalue = null;\r\n" + 
			"                    switch (item.name) {\r\n" + 
			"                    case 'id':\r\n" + 
			"                        if (folSib.hasAttribute('id')) {\r\n" + 
			"                            let id = folSib.id;\r\n" + 
			"                            let re = new RegExp('\\\\d{' + maxId + ',}', '\\g');\r\n" + 
			"                            let matches = re.test(id);\r\n" + 
			"                            if ((id != null) && (id.length > 0) && matches == false) {\r\n" + 
			"                                tempvalue = id;\r\n" + 
			"                            }\r\n" + 
			"                        }\r\n" + 
			"                        break;\r\n" + 
			"                    case 'class':\r\n" + 
			"                        if (folSib.hasAttribute('class')) {\r\n" + 
			"                            tempvalue = folSib.className;\r\n" + 
			"                            let splClass = tempvalue.trim()\r\n" + 
			"                                .split(\" \");\r\n" + 
			"                            if (splClass.length > 2) {\r\n" + 
			"                                tempvalue = `contains(@class,'${splClass[0]} ${splClass[1]}')`;\r\n" + 
			"                                classHasSpace = true;\r\n" + 
			"                            }\r\n" + 
			"                        }\r\n" + 
			"                        break;\r\n" + 
			"                    case 'name':\r\n" + 
			"                        if (folSib.hasAttribute('name')) {\r\n" + 
			"                            tempvalue = folSib.name;\r\n" + 
			"                        }\r\n" + 
			"                        break;\r\n" + 
			"                    default:\r\n" + 
			"                        tempvalue = item.value;\r\n" + 
			"                    }\r\n" + 
			"                    if (tempvalue == '') {\r\n" + 
			"                        tempvalue = null;\r\n" + 
			"                    }\r\n" + 
			"                    if (classHasSpace) {\r\n" + 
			"                        temp = `//${followingSiblingTagName}[${tempvalue}]/preceding-sibling::${tagName}[1]`\r\n" + 
			"                            if (temp.startsWith('//')) {\r\n" + 
			"                                if (getCountForEachXpath(temp) == 1 && evaluateXpath(temp)\r\n" + 
			"                                    .singleNodeValue.attributes.objectspyxpath != undefined) {\r\n" + 
			"                                    precedarray.push(temp);\r\n" + 
			"\r\n" + 
			"                                } else {\r\n" + 
			"                                    let t = addIndexToXpath(`//${followingSiblingTagName}[${tempvalue}]/preceding-sibling::${tagName}`)\r\n" + 
			"                                        if (t != undefined) {\r\n" + 
			"                                            precedarray.push(t)\r\n" + 
			"\r\n" + 
			"                                        } else\r\n" + 
			"                                            temp = null;\r\n" + 
			"                                }\r\n" + 
			"                            }\r\n" + 
			"\r\n" + 
			"                    } else if (tempvalue != null) {\r\n" + 
			"                        temp = `//${followingSiblingTagName}[@${item.name}='${tempvalue}']/preceding-sibling::${tagName}[1]`\r\n" + 
			"                            if (temp.startsWith('//')) {\r\n" + 
			"                                if (getCountForEachXpath(temp) == 1 && evaluateXpath(temp)\r\n" + 
			"                                    .singleNodeValue.attributes.objectspyxpath != undefined) {\r\n" + 
			"                                    precedarray.push(temp);\r\n" + 
			"                                } else {\r\n" + 
			"                                    let t = addIndexToXpath(`//${followingSiblingTagName}[@${item.name}='${tempvalue}']/preceding-sibling::${tagName}`)\r\n" + 
			"                                        if (t != undefined) {\r\n" + 
			"                                            precedarray.push(t)\r\n" + 
			"                                        } else\r\n" + 
			"                                            temp = null;\r\n" + 
			"                                }\r\n" + 
			"                            }\r\n" + 
			"                    }\r\n" + 
			"                }\r\n" + 
			"            });\r\n" + 
			"            if (temp == null || (folSib.innerText.length > 1)) {\r\n" + 
			"                let temp1;\r\n" + 
			"                let labelText;\r\n" + 
			"                let tag;\r\n" + 
			"                let counter = 0;\r\n" + 
			"                let bo = false;\r\n" + 
			"                let child = folSib.parentNode.children;\r\n" + 
			"                var j = 0;\r\n" + 
			"                while (child[j] != folSib) {\r\n" + 
			"                    j++;\r\n" + 
			"                    counter++;\r\n" + 
			"                }\r\n" + 
			"              \r\n" + 
			"                for (var xy = counter; xy <= child.length; xy++) {\r\n" + 
			"                    let text = child[xy].textContent;\r\n" + 
			"                    if (text != '') {\r\n" + 
			"                        labelText = text;\r\n" + 
			"                        tag = child[xy].tagName.toLowerCase()\r\n" + 
			"                            break;\r\n" + 
			"                    }\r\n" + 
			"                    if (xy == child.length && text != '') {\r\n" + 
			"                        break;\r\n" + 
			"                    }\r\n" + 
			"                }\r\n" + 
			"                \r\n" + 
			"                if (labelText.match(/[\\r\\n\\x0B\\x0C\\u0085\\u2028\\u2029]+/g)) {\r\n" + 
			"                    labelText = labelText.replace(/[\\r\\n\\x0B\\x0C\\u0085\\u2028\\u2029]+/g, \" \")\r\n" + 
			"                        bo = true;\r\n" + 
			"                }\r\n" + 
			"                if (bo && labelText.trim()\r\n" + 
			"                    .length > 1) {\r\n" + 
			"                    temp1 = `//${tag}[text()[normalize-space()='${labelText.trim()}']]/preceding-sibling::${tagName}[1]`;\r\n" + 
			"                } else {\r\n" + 
			"                    temp1 = `//${tag}[text()='${labelText}']/preceding-sibling::${tagName}[1]`;\r\n" + 
			"                }\r\n" + 
			"                let c = getCountForEachXpath(temp1)\r\n" + 
			"                    if (c != 1) {\r\n" + 
			"                        temp1 = `//${tag}[text()='${labelText}']/preceding-sibling::${tagName}`;\r\n" + 
			"                    }\r\n" + 
			"                    if (c == 0) {\r\n" + 
			"                        return null\r\n" + 
			"                    }\r\n" + 
			"                    c = getCountForEachXpath(temp1)\r\n" + 
			"                    if (c == 1 && evaluateXpath(temp1)\r\n" + 
			"                        .singleNodeValue.attributes.objectspyxpath != undefined) {\r\n" + 
			"                        precedarray.push(temp1)\r\n" + 
			"                    } else if ((c != undefined) || (c != null)) {\r\n" + 
			"                        xp = addIndexToXpath(temp1)\r\n" + 
			"                            if (xp != undefined) {\r\n" + 
			"                                precedarray.push(xp)\r\n" + 
			"                            }\r\n" + 
			"                    }\r\n" + 
			"            }\r\n" + 
			"\r\n" + 
			"            for (var i = 0; i < precedarray.length; i++) {\r\n" + 
			"                if (precedarray[i].includes('@id') || precedarray[i].includes('@class') || precedarray[i].includes('@name')) {\r\n" + 
			"                    if (precedarray[i].includes('@id')) {\r\n" + 
			"                        precedingclassbasedXpath = precedarray[i];\r\n" + 
			"                        break;\r\n" + 
			"                    } else {\r\n" + 
			"                        precedingclassbasedXpath = precedarray[i];\r\n" + 
			"                        break;\r\n" + 
			"                    }\r\n" + 
			"                } else if (precedarray[i].includes('contains') || precedarray[i].includes('text()')) {\r\n" + 
			"                    precedingTextbasedXpath = precedarray[i];\r\n" + 
			"\r\n" + 
			"                } else {\r\n" + 
			"\r\n" + 
			"                    var precedingattributebasedXpathcheck = precedarray[i];\r\n" + 
			"                    let c = getCountForEachXpath(precedingattributebasedXpathcheck);\r\n" + 
			"                    if (c == 1 && evaluateXpath(precedingattributebasedXpathcheck)) {\r\n" + 
			"                        precedingattributebasedXpath = precedingattributebasedXpathcheck;\r\n" + 
			"                    }\r\n" + 
			"\r\n" + 
			"                }\r\n" + 
			"\r\n" + 
			"            }\r\n" + 
			"\r\n" + 
			"            if (!precedingclassbasedXpath || !precedingattributebasedXpath || !precedingTextbasedXpath) {\r\n" + 
			"                if (precedingclassbasedXpath != null) {\r\n" + 
			"                    return precedingclassbasedXpath;\r\n" + 
			"                } else if (precedingTextbasedXpath != null) {\r\n" + 
			"                    return precedingTextbasedXpath;\r\n" + 
			"                } else {\r\n" + 
			"                    return precedingattributebasedXpath;\r\n" + 
			"                }\r\n" + 
			"\r\n" + 
			"            } else {\r\n" + 
			"                return null;\r\n" + 
			"            }\r\n" + 
			"\r\n" + 
			"        } catch (error) {}\r\n" + 
			"    }\r\n" + 
			"\r\n" + 
			"    function getParentId(element, tagName) {\r\n" + 
			"        let clicketItemId = element.id;\r\n" + 
			"        let re = new RegExp('\\\\d{' + maxId + ',}', '\\g');\r\n" + 
			"        let matches = re.test(clicketItemId);\r\n" + 
			"        if ((clicketItemId != null) && (clicketItemId.length > 0) && matches == false) {\r\n" + 
			"            let temp = `//${tagName}[@id='${clicketItemId}']`;\r\n" + 
			"            return temp;\r\n" + 
			"        } else\r\n" + 
			"            return null;\r\n" + 
			"\r\n" + 
			"    }\r\n" + 
			"\r\n" + 
			"    function getParentName(element, tagName) {\r\n" + 
			"        var clickedItemName = null;\r\n" + 
			"        if (element.name != null) {\r\n" + 
			"            let clickedItemName = element.name;\r\n" + 
			"        } else {\r\n" + 
			"            let clickedItemName = element.nodeValue;\r\n" + 
			"        }\r\n" + 
			"        if (!((element.name === \"\") || (element.name === undefined)) || !((element.nodeValue === \"\") || (element.nodeValue === undefined))) {\r\n" + 
			"            if (element.name != null) {\r\n" + 
			"                clickedItemName = element.name;\r\n" + 
			"            } else {\r\n" + 
			"                clickedItemName = element.nodeValue;\r\n" + 
			"            }\r\n" + 
			"            let tempName = `//${tagName}[@name='${clickedItemName}']`\r\n" + 
			"                return tempName;\r\n" + 
			"        } else\r\n" + 
			"            return null;\r\n" + 
			"    }\r\n" + 
			"\r\n" + 
			"    function getParentClassName(element, tagName) {\r\n" + 
			"        var lowerCaseTag = tagName.toLowerCase();\r\n" + 
			"        let clickedItemClass = element.className;\r\n" + 
			"        if (lowerCaseTag == \"svg\" && typeof clickedItemClass !== \"undefined\" && clickedItemClass !== null) {\r\n" + 
			"            clickedItemClass = element.className.baseVal;\r\n" + 
			"        }\r\n" + 
			"\r\n" + 
			"        let splitClass = clickedItemClass.trim()\r\n" + 
			"            .split(\" \");\r\n" + 
			"        if (splitClass.length > 2) {\r\n" + 
			"            let cl = `${splitClass[0]} ${splitClass[1]}`;\r\n" + 
			"            let temp = `//${tagName}[contains(@class,'${cl}')]`;\r\n" + 
			"            return temp;\r\n" + 
			"        } else if (!((clickedItemClass === \"\") || (clickedItemClass === undefined))) {\r\n" + 
			"            let tempClass = `//${tagName}[@class='${clickedItemClass}']`\r\n" + 
			"                return tempClass;\r\n" + 
			"        } else\r\n" + 
			"            return null;\r\n" + 
			"    }\r\n" + 
			"    // Get Text based XPath\r\n" + 
			"    function xpathText(element, tagName) {\r\n" + 
			"        textbasedpath = null;\r\n" + 
			"        let textBasedXpathEle = getTextBasedXpath(element, tagName);\r\n" + 
			"        if (!((textBasedXpathEle === null) || (textBasedXpathEle === undefined))) {\r\n" + 
			"            textbasedpath = textBasedXpathEle;\r\n" + 
			"            return textbasedpath;\r\n" + 
			"        }\r\n" + 
			"    }\r\n" + 
			"    //get ancestor based XPath\r\n" + 
			"    function getAncestor(element, tagName) {\r\n" + 
			"        parentbasedPath = null;\r\n" + 
			"        var ngbasedPath = null;\r\n" + 
			"        let ancestor = element.parentNode;\r\n" + 
			"        let parent = ancestor.parentNode;\r\n" + 
			"        var bodyTag = document.getElementsByTagName('body')[0];\r\n" + 
			"        var htmlTag = document.getElementsByTagName('html')[0];\r\n" + 
			"        let bo = false;\r\n" + 
			"        if (parent != bodyTag && parent != htmlTag) {\r\n" + 
			"            bo = checkIDNameClassHref(parent, bo);\r\n" + 
			"            if (bo == true) {\r\n" + 
			"                bo = false;\r\n" + 
			"            }\r\n" + 
			"            while (bo == false) {\r\n" + 
			"                //parent = parent.parentNode;\r\n" + 
			"                if (parent.parentNode != null) {\r\n" + 
			"                    parent = parent.parentNode;\r\n" + 
			"                } else {\r\n" + 
			"                    return null;\r\n" + 
			"                }\r\n" + 
			"                if (parent != bodyTag && parent != htmlTag && parent != document && parent != null & typeof parent != 'undefined') {\r\n" + 
			"                    bo = checkIDNameClassHref(parent, bo);\r\n" + 
			"                } else {\r\n" + 
			"                    return null;\r\n" + 
			"                }\r\n" + 
			"            }\r\n" + 
			"            let attributeElement = parent.attributes;\r\n" + 
			"            var tag = parent.tagName.toLowerCase();\r\n" + 
			"            var parentId = null;\r\n" + 
			"            var parentClass = null;\r\n" + 
			"            var parentName = null;\r\n" + 
			"            var others = null;\r\n" + 
			"            Array.prototype.slice.call(attributeElement)\r\n" + 
			"            .forEach(function (item) {\r\n" + 
			"                if (!(filterAttributesFromElement(item))) {\r\n" + 
			"                    switch (item.name) {\r\n" + 
			"                    case \"id\":\r\n" + 
			"                        parentId = getParentId(parent, tag)\r\n" + 
			"                            break;\r\n" + 
			"                    case \"class\":\r\n" + 
			"                        parentClass = getParentClassName(parent, tag)\r\n" + 
			"                            break;\r\n" + 
			"                    case \"name\":\r\n" + 
			"                        parentName = getParentName(parent, tag)\r\n" + 
			"                            break;\r\n" + 
			"                    default:\r\n" + 
			"                        let temp = item.value;\r\n" + 
			"                        if (temp != '') {\r\n" + 
			"\r\n" + 
			"                            others = `//${tag}[@${item.name}='${temp}']`\r\n" + 
			"                        }\r\n" + 
			"                        break;\r\n" + 
			"                    }\r\n" + 
			"                }\r\n" + 
			"            });\r\n" + 
			"            if (parentId != null && parentId != undefined) {\r\n" + 
			"                getParentXp(parentId, tagName, 'id', element);\r\n" + 
			"            }\r\n" + 
			"            if (parentClass != null && parentClass != undefined) {\r\n" + 
			"                getParentXp(parentClass, tagName, 'class', element);\r\n" + 
			"            }\r\n" + 
			"            if (parentName != null && parentName != undefined) {\r\n" + 
			"                getParentXp(parentName, tagName, 'name', element);\r\n" + 
			"            }\r\n" + 
			"            if (others != null && others != undefined) {\r\n" + 
			"                getParentXp(others, tagName, 'attribute', element);\r\n" + 
			"            }\r\n" + 
			"\r\n" + 
			"            function getParentXp(parent, tagName, locator, element) {\r\n" + 
			"                let tem = `${parent}//${tagName}[1]`;\r\n" + 
			"                parentbasedPath = null;\r\n" + 
			"                let checkTem = evaluateXpath(tem)\r\n" + 
			"                    let c = getCountForEachXpath(tem);\r\n" + 
			"                if (c == 0) {\r\n" + 
			"                    return null;\r\n" + 
			"                }\r\n" + 
			"                if (c == 1) {\r\n" + 
			"                    try {\r\n" + 
			"                        if (checkTem.singleNodeValue.hasAttribute('objectspyxpath')) {\r\n" + 
			"                            if (tem.includes('@ng-')) {\r\n" + 
			"                                ngbasedPath = tem;\r\n" + 
			"                            } else {\r\n" + 
			"                                parentbasedPath = tem;\r\n" + 
			"                                return parentbasedPath;\r\n" + 
			"                            }\r\n" + 
			"                           \r\n" + 
			"                        } else {\r\n" + 
			"                            tem = `${parent}//${tagName}`;\r\n" + 
			"                            c = getCountForEachXpath(tem);\r\n" + 
			"                            if (c == 0) {\r\n" + 
			"                                return null;\r\n" + 
			"                            }\r\n" + 
			"                            if (c >= 1) {\r\n" + 
			"                                try {\r\n" + 
			"                                    let te = addIndexToXpath(tem)\r\n" + 
			"                                        checkTem = evaluateXpath(te)\r\n" + 
			"                                        if (checkTem.singleNodeValue.attributes.objectspyxpath.value === \"objectspy\") {\r\n" + 
			"                                            if (te.includes('@ng-')) {\r\n" + 
			"                                                ngbasedPath = te;\r\n" + 
			"                                            } else {\r\n" + 
			"                                                parentbasedPath = te;\r\n" + 
			"                                                return parentbasedPath;\r\n" + 
			"                                               \r\n" + 
			"                                            }\r\n" + 
			"                                        }\r\n" + 
			"                                } catch (e) {}\r\n" + 
			"                            }\r\n" + 
			"                        }\r\n" + 
			"                    } catch (e) {}\r\n" + 
			"                } else if (c > 1) {\r\n" + 
			"                    tem = `${parent}//${tagName}`;\r\n" + 
			"                    let t = addIndexToXpath(tem);\r\n" + 
			"                    if (t != undefined && t != null) {\r\n" + 
			"                        parentbasedPath = t;\r\n" + 
			"                        return parentbasedPath;\r\n" + 
			"                    }\r\n" + 
			"                }\r\n" + 
			"            }\r\n" + 
			"        }\r\n" + 
			"        if (parentbasedPath != null || ngbasedPath != null) {\r\n" + 
			"            if (ngbasedPath != null) {\r\n" + 
			"                return ngbasedPath;\r\n" + 
			"            } else {\r\n" + 
			"                return parentbasedPath;\r\n" + 
			"            }\r\n" + 
			"        }\r\n" + 
			"    }\r\n" + 
			"\r\n" + 
			"    // svg\r\n" + 
			"    function svgPath(element, tagName) {\r\n" + 
			"        parentbasedPath = null;\r\n" + 
			"        var ngbasedPath = null;\r\n" + 
			"        let parent = element.parentNode;\r\n" + 
			"        var bodyTag = document.getElementsByTagName('body')[0];\r\n" + 
			"        var htmlTag = document.getElementsByTagName('html')[0];\r\n" + 
			"        let bo = false;\r\n" + 
			"        if (parent != bodyTag && parent != htmlTag) {\r\n" + 
			"            bo = checkIDNameClassHref(parent, bo);\r\n" + 
			"            while (bo == false) {\r\n" + 
			"                parent = parent.parentNode;\r\n" + 
			"                bo = checkIDNameClassHref(parent, bo);\r\n" + 
			"            }\r\n" + 
			"            let attributeElement = parent.attributes;\r\n" + 
			"            var tag = parent.tagName.toLowerCase();\r\n" + 
			"            var parentId = null;\r\n" + 
			"            var parentClass = null;\r\n" + 
			"            var parentName = null;\r\n" + 
			"            var others = null;\r\n" + 
			"            Array.prototype.slice.call(attributeElement)\r\n" + 
			"            .forEach(function (item) {\r\n" + 
			"                if (!(filterAttributesFromElement(item))) {\r\n" + 
			"                    switch (item.name) {\r\n" + 
			"                    case \"id\":\r\n" + 
			"                        parentId = getParentId(parent, tag)\r\n" + 
			"                            break;\r\n" + 
			"                    case \"class\":\r\n" + 
			"                        parentClass = getParentClassName(parent, tag)\r\n" + 
			"                            break;\r\n" + 
			"                    case \"name\":\r\n" + 
			"                        parentName = getParentName(parent, tag)\r\n" + 
			"                            break;\r\n" + 
			"                    default:\r\n" + 
			"                        let temp = item.value;\r\n" + 
			"                        if (temp != '') {\r\n" + 
			"\r\n" + 
			"                            others = `//${tag}[@${item.name}='${temp}']`\r\n" + 
			"                        }\r\n" + 
			"                        break;\r\n" + 
			"                    }\r\n" + 
			"                }\r\n" + 
			"            });\r\n" + 
			"            if (parentId != null && parentId != undefined) {\r\n" + 
			"                getParentXp(parentId, tagName, 'id', element);\r\n" + 
			"            }\r\n" + 
			"            if (parentClass != null && parentClass != undefined) {\r\n" + 
			"                getParentXp(parentClass, tagName, 'class', element);\r\n" + 
			"            }\r\n" + 
			"            if (parentName != null && parentName != undefined) {\r\n" + 
			"                getParentXp(parentName, tagName, 'name', element);\r\n" + 
			"            }\r\n" + 
			"            if (others != null && others != undefined) {\r\n" + 
			"                getParentXp(others, tagName, 'attribute', element);\r\n" + 
			"            }\r\n" + 
			"\r\n" + 
			"            function getParentXp(parent, tagName, locator, element) {\r\n" + 
			"                let tem = `${parent}//${tagName}[1]`;\r\n" + 
			"                parentbasedPath = null;\r\n" + 
			"                if (tagName == 'svg') {\r\n" + 
			"                    let tem = `${parent}//*[name()='${tagName}']`\r\n" + 
			"                        var toBeModifiedPath = tem;\r\n" + 
			"                    let checkTem = evaluateXpath(tem)\r\n" + 
			"                        let c = getCountForEachXpath(tem);\r\n" + 
			"                    if (c == 1) {\r\n" + 
			"                        parentbasedPath = tem;\r\n" + 
			"                        return parentbasedPath;\r\n" + 
			"                    } else if (c == 0 || c > 1) {\r\n" + 
			"                        var tempValue;\r\n" + 
			"                        let tem = `${parent}//*[name()='${tagName}'][@objectspyxpath]`\r\n" + 
			"                            let checkTem = evaluateXpath(tem)\r\n" + 
			"                            let c = getCountForEachXpath(tem);\r\n" + 
			"                        if (c == 1) {\r\n" + 
			"                            var elemnode = document.evaluate(tem, document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;\r\n" + 
			"                            let tagName = elemnode.tagName.toLowerCase();\r\n" + 
			"                            let getAttr = elemnode.attributes;\r\n" + 
			"                        \r\n" + 
			"                            Array.prototype.slice.call(elemnode)\r\n" + 
			"                            .forEach(function (item) {\r\n" + 
			"                                if (!(filterAttributesFromElement(item))) {\r\n" + 
			"                                    switch (item.name) {\r\n" + 
			"                                    case \"id\":\r\n" + 
			"                                        parentId = getParentId(parent, tag)\r\n" + 
			"                                            break;\r\n" + 
			"                                    case \"class\":\r\n" + 
			"                                        parentClass = getParentClassName(parent, tag)\r\n" + 
			"                                            break;\r\n" + 
			"                                    case \"name\":\r\n" + 
			"                                        parentName = getParentName(parent, tag)\r\n" + 
			"                                            break;\r\n" + 
			"                                    default:\r\n" + 
			"                                        let temp = item.value;\r\n" + 
			"                                        if (temp != '') {\r\n" + 
			"\r\n" + 
			"                                            others = `//${tag}[@${item.name}='${temp}']`\r\n" + 
			"                                        }\r\n" + 
			"                                        break;\r\n" + 
			"                                    }\r\n" + 
			"                                }\r\n" + 
			"                            });\r\n" + 
			"                            if (parentId != null && parentId != undefined) {\r\n" + 
			"                                getParentXp(parentId, tagName, 'id', element);\r\n" + 
			"                            }\r\n" + 
			"                            if (parentClass != null && parentClass != undefined) {\r\n" + 
			"                                let clickedItemClass = elemnode.className.baseVal; ;\r\n" + 
			"                                let finalClassPath = `${parent}//*[name()='${tagName}'][@class='${clickedItemClass}']`\r\n" + 
			"                                    let c = getCountForEachXpath(finalClassPath);\r\n" + 
			"                                if (c == 1) {\r\n" + 
			"                                    parentbasedPath = finalClassPath;\r\n" + 
			"                                    return parentbasedPath;\r\n" + 
			"                                } else if (c > 1) {\r\n" + 
			"                                    var temporary = getSamplePathTo(elemnode);\r\n" + 
			"                                    if (temporary.endsWith(\"/svg[1]\")) {\r\n" + 
			"                                        let xpathwithpos = getXPathWithPosition(elemnode);\r\n" + 
			"                                        let finalAbsXpath = xpathwithpos;\r\n" + 
			"                                        parentbasedPath = finalAbsXpath;\r\n" + 
			"                                        return parentbasedPath\r\n" + 
			"                                    } else {\r\n" + 
			"                                        return null\r\n" + 
			"                                    }\r\n" + 
			"                                    let tempParent = element.parentNode;\r\n" + 
			"                                    tempParent = tempParent.parentNode;\r\n" + 
			"                                    var tempsvPath = svgPath(tempParent, tagName);\r\n" + 
			"                                    let c = getCountForEachXpath(tempsvPath);\r\n" + 
			"                                    if (c == 0) {\r\n" + 
			"                                        return null;\r\n" + 
			"                                    }\r\n" + 
			"                                }\r\n" + 
			"                            }\r\n" + 
			"                            if (parentName != null && parentName != undefined) {\r\n" + 
			"                                let clickedItemClass = elemnode.getAttribute('name')\r\n" + 
			"                                    let finalNamePath = `${parent}//*[name()='${tagName}'][@name='${clickedItemClass}']`\r\n" + 
			"                                    let c = getCountForEachXpath(finalClassPath);\r\n" + 
			"                                if (c == 1) {\r\n" + 
			"                                    parentbasedPath = finalNamePath;\r\n" + 
			"                                    return parentbasedPath;\r\n" + 
			"                                }\r\n" + 
			"                            }\r\n" + 
			"                        }\r\n" + 
			"                        if (others != null && others != undefined) {\r\n" + 
			"                            let temp = item.value;\r\n" + 
			"                            if (temp != '') {\r\n" + 
			"                                let finalAttributePath = `${parent}//*[name()='${tagName}'][@${item.name}='${temp}']`\r\n" + 
			"                                    let c = getCountForEachXpath(finalClassPath);\r\n" + 
			"                                if (c == 1) {\r\n" + 
			"                                    parentbasedPath = finalAttributePath;\r\n" + 
			"                                    return parentbasedPath;\r\n" + 
			"                                }\r\n" + 
			"\r\n" + 
			"                            }\r\n" + 
			"                        }\r\n" + 
			"\r\n" + 
			"                    }\r\n" + 
			"                }\r\n" + 
			"\r\n" + 
			"            }\r\n" + 
			"\r\n" + 
			"        }\r\n" + 
			"        if (parentbasedPath != null || ngbasedPath != null) {\r\n" + 
			"            return parentbasedPath;\r\n" + 
			"\r\n" + 
			"        }\r\n" + 
			"    }\r\n" + 
			"\r\n" + 
			"    getSamplePathTo = function (element) {\r\n" + 
			"        var element_sibling,\r\n" + 
			"        siblingTagName,\r\n" + 
			"        siblings,\r\n" + 
			"        cnt,\r\n" + 
			"        sibling_count;\r\n" + 
			"\r\n" + 
			"        hello(\"getSamplePathTo\");\r\n" + 
			"        var elementTagName = element.tagName.toLowerCase();\r\n" + 
			"        if (element.id != '') {\r\n" + 
			"            return 'id(\"' + element.id + '\")';\r\n" + 
			"           \r\n" + 
			"        } else if (element.name && document.getElementsByName(element.name).length === 1) {\r\n" + 
			"            return '//' + elementTagName + '[@name=\"' + element.name + '\"]';\r\n" + 
			"        }\r\n" + 
			"        if (element === document.body) {\r\n" + 
			"            return '/html/' + elementTagName;\r\n" + 
			"        }\r\n" + 
			"        sibling_count = 0;\r\n" + 
			"        siblings = element.parentNode.childNodes;\r\n" + 
			"        siblings_length = siblings.length;\r\n" + 
			"        for (cnt = 0; cnt < siblings_length; cnt++) {\r\n" + 
			"            var element_sibling = siblings[cnt];\r\n" + 
			"            if (element_sibling.nodeType !== ELEMENT_NODE) { // not ELEMENT_NODE\r\n" + 
			"                continue;\r\n" + 
			"            }\r\n" + 
			"            if (element_sibling === element) {\r\n" + 
			"                return getSamplePathTo(element.parentNode) + '/' + elementTagName + '[' + (sibling_count + 1) + ']';\r\n" + 
			"            }\r\n" + 
			"            if (element_sibling.nodeType === 1 && element_sibling.tagName.toLowerCase() === elementTagName) {\r\n" + 
			"                sibling_count++;\r\n" + 
			"            }\r\n" + 
			"        }\r\n" + 
			"        return bye(\"getSamplePathTo\");\r\n" + 
			"    };\r\n" + 
			"\r\n" + 
			"    // get parent based XPath\r\n" + 
			"    function getParent(element, tagName) {\r\n" + 
			"        parentbasedPath = null;\r\n" + 
			"        var ngbasedPath = null;\r\n" + 
			"        let parent = element.parentNode;\r\n" + 
			"        var bodyTag = document.getElementsByTagName('body')[0];\r\n" + 
			"        var htmlTag = document.getElementsByTagName('html')[0];\r\n" + 
			"        let bo = false;\r\n" + 
			"        if (parent != bodyTag && parent != htmlTag) {\r\n" + 
			"            bo = checkIDNameClassHref(parent, bo);\r\n" + 
			"            while (bo == false) {\r\n" + 
			"                parent = parent.parentNode;\r\n" + 
			"                bo = checkIDNameClassHref(parent, bo);\r\n" + 
			"            }\r\n" + 
			"            let attributeElement = parent.attributes;\r\n" + 
			"            var tag = parent.tagName.toLowerCase();\r\n" + 
			"            var parentId = null;\r\n" + 
			"            var parentClass = null;\r\n" + 
			"            var parentName = null;\r\n" + 
			"            var others = null;\r\n" + 
			"            Array.prototype.slice.call(attributeElement)\r\n" + 
			"            .forEach(function (item) {\r\n" + 
			"                if (!(filterAttributesFromElement(item))) {\r\n" + 
			"                    switch (item.name) {\r\n" + 
			"                    case \"id\":\r\n" + 
			"                        parentId = getParentId(parent, tag)\r\n" + 
			"                            break;\r\n" + 
			"                    case \"class\":\r\n" + 
			"                        parentClass = getParentClassName(parent, tag)\r\n" + 
			"                            break;\r\n" + 
			"                    case \"name\":\r\n" + 
			"                        parentName = getParentName(parent, tag)\r\n" + 
			"                            break;\r\n" + 
			"                    default:\r\n" + 
			"                        let temp = item.value;\r\n" + 
			"                        if (temp != '') {\r\n" + 
			"\r\n" + 
			"                            others = `//${tag}[@${item.name}='${temp}']`\r\n" + 
			"                        }\r\n" + 
			"                        break;\r\n" + 
			"                    }\r\n" + 
			"                }\r\n" + 
			"            });\r\n" + 
			"            if (parentId != null && parentId != undefined) {\r\n" + 
			"                getParentXp(parentId, tagName, 'id', element);\r\n" + 
			"           }\r\n" + 
			"            if (parentClass != null && parentClass != undefined) {\r\n" + 
			"                getParentXp(parentClass, tagName, 'class', element);\r\n" + 
			"            }\r\n" + 
			"            if (parentName != null && parentName != undefined) {\r\n" + 
			"                getParentXp(parentName, tagName, 'name', element);\r\n" + 
			"            }\r\n" + 
			"            if (others != null && others != undefined) {\r\n" + 
			"                getParentXp(others, tagName, 'attribute', element);\r\n" + 
			"            }\r\n" + 
			"\r\n" + 
			"            function getParentXp(parent, tagName, locator, element) {\r\n" + 
			"                let tem = `${parent}//${tagName}[1]`;\r\n" + 
			"                parentbasedPath = null;\r\n" + 
			"                let checkTem = evaluateXpath(tem)\r\n" + 
			"                    let c = getCountForEachXpath(tem);\r\n" + 
			"                if (c == 0) {\r\n" + 
			"                    return null;\r\n" + 
			"                }\r\n" + 
			"                if (c == 1) {\r\n" + 
			"                    try {\r\n" + 
			"                        if (checkTem.singleNodeValue.hasAttribute('objectspyxpath')) {\r\n" + 
			"                            if (tem.includes('@ng-')) {\r\n" + 
			"                                ngbasedPath = tem;\r\n" + 
			"                            } else {\r\n" + 
			"                                parentbasedPath = tem;\r\n" + 
			"                                return parentbasedPath;\r\n" + 
			"                            }\r\n" + 
			"                            \r\n" + 
			"							\r\n" + 
			"                        } else {\r\n" + 
			"                            tem = `${parent}//${tagName}`;\r\n" + 
			"                            c = getCountForEachXpath(tem);\r\n" + 
			"                            if (c == 0) {\r\n" + 
			"                                return null;\r\n" + 
			"                            }\r\n" + 
			"                            if (c >= 1) {\r\n" + 
			"                                try {\r\n" + 
			"                                    let te = addIndexToXpath(tem)\r\n" + 
			"                                        checkTem = evaluateXpath(te)\r\n" + 
			"                                        if (checkTem.singleNodeValue.attributes.objectspyxpath.value === \"objectspy\") {\r\n" + 
			"                                           if (te.includes('@ng-')) {\r\n" + 
			"                                                ngbasedPath = te;\r\n" + 
			"                                            } else {\r\n" + 
			"                                                parentbasedPath = te;\r\n" + 
			"                                                return parentbasedPath;\r\n" + 
			"                                               \r\n" + 
			"                                            }\r\n" + 
			"                                        }\r\n" + 
			"                                } catch (e) {}\r\n" + 
			"                            }\r\n" + 
			"                        }\r\n" + 
			"                    } catch (e) {}\r\n" + 
			"                } else if (c > 1) {\r\n" + 
			"                    tem = `${parent}//${tagName}`;\r\n" + 
			"                    let t = addIndexToXpath(tem);\r\n" + 
			"                    if (t != undefined && t != null) {\r\n" + 
			"                        parentbasedPath = t;\r\n" + 
			"                        return parentbasedPath;\r\n" + 
			"                    }\r\n" + 
			"                }\r\n" + 
			"            }\r\n" + 
			"        }\r\n" + 
			"        if (parentbasedPath != null || ngbasedPath != null) {\r\n" + 
			"            if (ngbasedPath != null) {\r\n" + 
			"                return ngbasedPath;\r\n" + 
			"            } else {\r\n" + 
			"                return parentbasedPath;\r\n" + 
			"            }\r\n" + 
			"        }\r\n" + 
			"    }\r\n" + 
			"\r\n" + 
			"    function checkIDNameClassHref(parent, bo) {\r\n" + 
			"        var bodyTag = document.getElementsByTagName('body')[0];\r\n" + 
			"        var htmlTag = document.getElementsByTagName('html')[0];\r\n" + 
			"        if (parent != bodyTag && parent != htmlTag && parent != document && parent != null & typeof parent != 'undefined') {\r\n" + 
			"            Array.prototype.slice.call(parent.attributes)\r\n" + 
			"            .forEach(function (item) {\r\n" + 
			"                if (item.name === 'id' || item.name === 'class' || item.name === 'name')\r\n" + 
			"                    bo = true;\r\n" + 
			"            });\r\n" + 
			"        }\r\n" + 
			"        return bo;\r\n" + 
			"    }\r\n" + 
			"\r\n" + 
			"    function getParentText(element, tagName) {\r\n" + 
			"        let ep = element.parentNode.parentNode;\r\n" + 
			"        var child = ep.children;\r\n" + 
			"        var tagN = null;\r\n" + 
			"        var setBool = false;\r\n" + 
			"        for (var i = 0; i < child.length; i++) {\r\n" + 
			"            let innerChildLen = child[i].children.length;\r\n" + 
			"            for (let i = 0; i < innerChildLen; i++) {\r\n" + 
			"                if (child[i].children[i].textContent.length > 1) {\r\n" + 
			"                    ep = child[i].children[i];\r\n" + 
			"                    tagN = ep.tagName;\r\n" + 
			"                    setBool = true;\r\n" + 
			"                    break;\r\n" + 
			"                }\r\n" + 
			"            }\r\n" + 
			"            if (setBool)\r\n" + 
			"                break;\r\n" + 
			"        }\r\n" + 
			"        let text = getTextBasedXpath(ep, tagN.toLowerCase())\r\n" + 
			"            let temp = `${text}/following::${tagName}`;\r\n" + 
			"        let count = getCountForEachXpath(temp);\r\n" + 
			"        if (count == 1) {\r\n" + 
			"            let xp = `${text}/following::${tagName}[1]`;\r\n" + 
			"            return xp;\r\n" + 
			"        } else if (count > 1) {\r\n" + 
			"            let xp = `${text}/following::${tagName}`;\r\n" + 
			"            xp = addIndexToXpath(xp)\r\n" + 
			"                return xp;\r\n" + 
			"        } else\r\n" + 
			"            return null;\r\n" + 
			"    }\r\n" + 
			"\r\n" + 
			"    function findLabel(element, tagName) {\r\n" + 
			"        var label,\r\n" + 
			"        span = undefined;\r\n" + 
			"        let ele = `//*[@objectspyxpath='objectspy']`;\r\n" + 
			"        try {\r\n" + 
			"            label = getLabelTxet(ele, tagName)\r\n" + 
			"        } catch (error) {}\r\n" + 
			"        try {\r\n" + 
			"            span = getSpanText(ele, tagName)\r\n" + 
			"        } catch (error) {}\r\n" + 
			"        try {\r\n" + 
			"            if (label === undefined && span === undefined) {\r\n" + 
			"                let xp = getParentText(element, tagName)\r\n" + 
			"                    let temp = xp;\r\n" + 
			"                xp = evaluateXpath(xp);\r\n" + 
			"                if (xp != null && xp != undefined && ((xp.singleNodeValue.attributes.objectspyxpath) != undefined)) {\r\n" + 
			"                   \r\n" + 
			"                } else {\r\n" + 
			"                    temp = addIndexToXpath(temp)\r\n" + 
			"                        if (temp != null) {\r\n" + 
			"                            \r\n" + 
			"                        }\r\n" + 
			"                }\r\n" + 
			"            }\r\n" + 
			"        } catch (error) {}\r\n" + 
			"        if (label != undefined) {\r\n" + 
			"            return label;\r\n" + 
			"        } else if (span != undefined) {\r\n" + 
			"            return span;\r\n" + 
			"        }\r\n" + 
			"    }\r\n" + 
			"    function getSpanText(ele, tagName) {\r\n" + 
			"        var spanNode = `${ele}/preceding::span[1]`;\r\n" + 
			"        let checkSpanType = evaluateXpath(spanNode);\r\n" + 
			"        try {\r\n" + 
			"            if (typeof(checkSpanType.singleNodeValue.textContent) === 'string') {\r\n" + 
			"                return getLabel(spanNode, tagName);\r\n" + 
			"            } else {\r\n" + 
			"                throw 'no span text'\r\n" + 
			"            }\r\n" + 
			"        } catch (error) {}\r\n" + 
			"    }\r\n" + 
			"    function getLabelTxet(ele, tagName) {\r\n" + 
			"        var labelNode = `${ele}/preceding::label[1]`;\r\n" + 
			"        let checkLabelType = evaluateXpath(labelNode);\r\n" + 
			"        try {\r\n" + 
			"            if (typeof(checkLabelType.singleNodeValue.textContent) === 'string') {\r\n" + 
			"                return getLabel(labelNode, tagName);\r\n" + 
			"            } else {\r\n" + 
			"                throw 'no label preceding';\r\n" + 
			"            }\r\n" + 
			"        } catch (error) {}\r\n" + 
			"    }\r\n" + 
			"\r\n" + 
			"    function getLabel(node, tagName) {\r\n" + 
			"        let c = getCountForEachXpath(node);\r\n" + 
			"        if (c > 0) {\r\n" + 
			"            var label = evaluateXpath(node);\r\n" + 
			"            var newEle = label.singleNodeValue;\r\n" + 
			"            var labelTag = newEle.tagName.toLowerCase();\r\n" + 
			"            var labelText = getTextBasedXpath(newEle, labelTag);\r\n" + 
			"            var newLabelXpath = labelText + '/' + 'following::' + tagName;\r\n" + 
			"            if (getCountForEachXpath(newLabelXpath) == 1) {\r\n" + 
			"                let newLabel = evaluateXpath(newLabelXpath);\r\n" + 
			"                if (newLabel != null && newLabel != undefined && ((newLabel.singleNodeValue.attributes.objectspyxpath) != undefined)) {\r\n" + 
			"                   \r\n" + 
			"                    return newLabelXpath;\r\n" + 
			"                }\r\n" + 
			"            } else {\r\n" + 
			"                var labelTextWithIndex = addIndexToXpath(newLabelXpath)\r\n" + 
			"                    let newLabel = evaluateXpath(labelTextWithIndex);\r\n" + 
			"                return labelTextWithIndex;\r\n" + 
			"            }\r\n" + 
			"        }\r\n" + 
			"    }\r\n" + 
			"\r\n" + 
			"    function getTextBasedXpath(element, tagName) {\r\n" + 
			"        let textBasedXpath = null;\r\n" + 
			"        var checkReturn;\r\n" + 
			"        let link;\r\n" + 
			"        let hasSpace = false;\r\n" + 
			"        let gotPartial = false;\r\n" + 
			"        if (element.textContent.length > 0) {\r\n" + 
			"            // link text\r\n" + 
			"            if (tagName === 'a') {\r\n" + 
			"                link = element.textContent;\r\n" + 
			"                if (element.childElementCount > 0) {\r\n" + 
			"                    link = element.children[0].innerText;\r\n" + 
			"                    if (link != undefined) {\r\n" + 
			"                        let partialLink = `//a[contains(text(),'${link.trim()}')]`;\r\n" + 
			"                        if (getCountForEachXpath(partialLink) == 1) {  \r\n" + 
			"                          \r\n" + 
			"                            gotPartial = true;\r\n" + 
			"                        }\r\n" + 
			"                    } else {\r\n" + 
			"                        link = element.textContent;\r\n" + 
			"                    }\r\n" + 
			"                }\r\n" + 
			"                let temp = `//a[contains(text(),'${link.trim()}')]`;\r\n" + 
			"                checkReturn = link.match(/[\\r\\n\\x0B\\x0C\\u0085\\u2028\\u2029]+/g);\r\n" + 
			"                if (checkReturn && gotPartial == false) {\r\n" + 
			"                    link = link.replace(/[\\r\\n\\x0B\\x0C\\u0085\\u2028\\u2029]+/g, \" \")\r\n" + 
			"                        hasSpace = link.match(/\\s/g);\r\n" + 
			"                    if (hasSpace) {\r\n" + 
			"                        link = link.replace(/\\s+/g, \" \");\r\n" + 
			"                    }\r\n" + 
			"                } else if (gotPartial == false && getCountForEachXpath(temp) == 1) {\r\n" + 
			"                    textBasedXpath = temp;\r\n" + 
			"                    return textBasedXpath;\r\n" + 
			"                } else if (gotPartial == false && getCountForEachXpath(`//a[text()='${link.trim()}']`) == 1) {\r\n" + 
			"                    textBasedXpath = temp;\r\n" + 
			"                    return textBasedXpath;\r\n" + 
			"                } else if (gotPartial == false && getCountForEachXpath(`//a[text()='${link.trim()}']`) > 1) {\r\n" + 
			"                    textBasedXpath = `//${tagName}[text()='${link.trim()}']`;\r\n" + 
			"                    let simpleText = getTextCount(textBasedXpath);\r\n" + 
			"                    textBasedXpath = simpleText;\r\n" + 
			"                    return textBasedXpath;\r\n" + 
			"                }\r\n" + 
			"            }\r\n" + 
			"            if (hasSpace) {\r\n" + 
			"                let normalizeSpace = `//${tagName}[text()[normalize-space()='${link.trim()}']]`;\r\n" + 
			"                let validNSXP = getCountForEachXpath(normalizeSpace)\r\n" + 
			"                    if (validNSXP == 1) {\r\n" + 
			"                        textBasedXpath = normalizeSpace;\r\n" + 
			"                        return textBasedXpath;\r\n" + 
			"                    } else if (validNSXP > 1) {\r\n" + 
			"                        let xp = addIndexToXpath(normalizeSpace)\r\n" + 
			"                            if (xp != null && xp != undefined)\r\n" + 
			"                                textBasedXpath = xp;\r\n" + 
			"                            return textBasedXpath;\r\n" + 
			"                    }\r\n" + 
			"            }\r\n" + 
			"\r\n" + 
			"            // if tagName is select then text should not appears\r\n" + 
			"            if (tagName != \"select\" && tagName != 'a') {\r\n" + 
			"                var innerText = element.textContent;\r\n" + 
			"                let hasBr = false;\r\n" + 
			"                if (innerText.match(/[\\r\\n\\x0B\\x0C\\u0085\\u2028\\u2029]+/g)) {\r\n" + 
			"                    hasSpace = innerText.match(/\\s/g);\r\n" + 
			"                    if (hasSpace) {\r\n" + 
			"                        innerText = innerText.replace(/\\s+/g, \" \");\r\n" + 
			"                        if (innerText != \" \") {\r\n" + 
			"                            textBasedXpath = `//${tagName}[text()[normalize-space()='${innerText.trim()}']]`;\r\n" + 
			"                        }\r\n" + 
			"                        let validText = getTextCount(textBasedXpath);\r\n" + 
			"                        while (validText) {\r\n" + 
			"                            return textBasedXpath;\r\n" + 
			"                        }\r\n" + 
			"                    }\r\n" + 
			"                } else {\r\n" + 
			"                    textBasedXpath = `//${tagName}[text()='${innerText}']`;\r\n" + 
			"                    let simpleText = getTextCount(textBasedXpath);\r\n" + 
			"                    while (simpleText) {\r\n" + 
			"                        return simpleText;\r\n" + 
			"                    }\r\n" + 
			"                }\r\n" + 
			"                let findBr = element.childNodes;\r\n" + 
			"                let otherChild = element.childNodes;\r\n" + 
			"                for (let br in findBr) {\r\n" + 
			"                    if (findBr[br].nodeName === 'BR') {\r\n" + 
			"                        hasBr = true;\r\n" + 
			"                        break;\r\n" + 
			"                    }\r\n" + 
			"                }\r\n" + 
			"                if (hasBr) {\r\n" + 
			"                    let containsdotText = '[contains(.,\\'' + innerText.trim() + '\\')]';\r\n" + 
			"                    textBasedXpath = '//' + tagName + containsdotText;\r\n" + 
			"                    let containsDotText = getTextCount(textBasedXpath);\r\n" + 
			"                    while (containsDotText) {\r\n" + 
			"                        return containsDotText;\r\n" + 
			"                    }\r\n" + 
			"                } else if (otherChild.length > 1) {\r\n" + 
			"                    var temp = null;\r\n" + 
			"                    for (var i = 0; i < otherChild.length; i++) {\r\n" + 
			"                        if ((otherChild[i].textContent.length > 1) && (otherChild[i].textContent.match(/\\w/g))) {\r\n" + 
			"                            temp = otherChild[i].textContent;\r\n" + 
			"                            textBasedXpath = '//' + tagName + '[text()=\\'' + temp.trim() + '\\']';\r\n" + 
			"                            let otherChilText = getTextCount(textBasedXpath);\r\n" + 
			"                            while (otherChilText) {\r\n" + 
			"                                return otherChilText;\r\n" + 
			"                            }\r\n" + 
			"                        }\r\n" + 
			"                    }\r\n" + 
			"                }\r\n" + 
			"                if (innerText.length > 0) {\r\n" + 
			"                    if (innerText.match(/[\\r\\n\\x0B\\x0C\\u0085\\u2028\\u2029]+/g)) {\r\n" + 
			"                        hasSpace = innerText.match(/\\s/g);\r\n" + 
			"                        if (hasSpace) {\r\n" + 
			"                            innerText = innerText.replace(/\\s+/g, \" \");\r\n" + 
			"                            textBasedXpath = `//${tagName}[text()[normalize-space()='${innerText.trim()}']]`;\r\n" + 
			"                        }\r\n" + 
			"                    } else if (innerText.match(\"\\\\s\")) {\r\n" + 
			"                        let containsText = '[contains(text(),\\'' + innerText.trim() + '\\')]';\r\n" + 
			"                        textBasedXpath = '//' + tagName + containsText;\r\n" + 
			"                        if (getCountForEachXpath(textBasedXpath) == 0) {\r\n" + 
			"                            let t = innerText.split(/\\u00a0/g)[1];\r\n" + 
			"                            textBasedXpath = `//${tagName}[text()='${t}']`;\r\n" + 
			"                        } else if (getCountForEachXpath(textBasedXpath) === 0) {\r\n" + 
			"                            let startsWith = '[starts-with(text(),\\'' + innerText.split(/\\u00a0/g)[0].trim() + '\\')]';\r\n" + 
			"                            textBasedXpath = '//' + tagName + startsWith;\r\n" + 
			"                        }\r\n" + 
			"                    }\r\n" + 
			"                }\r\n" + 
			"            }\r\n" + 
			"            let count = getCountForEachXpath(textBasedXpath);\r\n" + 
			"            if (count == 0 || count == undefined) {\r\n" + 
			"                textBasedXpath = null;\r\n" + 
			"            } else if (count > 1) {\r\n" + 
			"                textBasedXpath = addIndexToXpath(textBasedXpath);\r\n" + 
			"            }\r\n" + 
			"            /**\r\n" + 
			"             * To handle wild character like single quotes in a text\r\n" + 
			"             */\r\n" + 
			"            if (textBasedXpath != null) {\r\n" + 
			"                if (textBasedXpath.startsWith('//') || textBasedXpath.startsWith('(')) {\r\n" + 
			"                    let len = textBasedXpath.split('\\'')\r\n" + 
			"                        .length;\r\n" + 
			"                    if (len > 2) {\r\n" + 
			"                        let firstIndex = textBasedXpath.indexOf('\\'');\r\n" + 
			"                        let temp = textBasedXpath.replace(textBasedXpath.charAt(firstIndex), `\"`);\r\n" + 
			"                        let lastIndex = temp.lastIndexOf('\\'');\r\n" + 
			"                        textBasedXpath = setCharAt(temp, lastIndex, '\"');\r\n" + 
			"                    }\r\n" + 
			"                }\r\n" + 
			"            }\r\n" + 
			"            return textBasedXpath;\r\n" + 
			"        }\r\n" + 
			"    }\r\n" + 
			"\r\n" + 
			"    function getTextCount(text) {\r\n" + 
			"        let c = getCountForEachXpath(text)\r\n" + 
			"            if (c == 0 || c == undefined) {\r\n" + 
			"                return null;\r\n" + 
			"            } else if (c == 1) {\r\n" + 
			"                return text;\r\n" + 
			"            } else {\r\n" + 
			"                return text = addIndexToXpath(text)\r\n" + 
			"            }\r\n" + 
			"    }\r\n" + 
			"\r\n" + 
			"    function filterAttributesFromElement(item) {\r\n" + 
			"        return (item.name === 'objectspyxpath') || (item.name === 'jsname') || (item.name === 'jsmodel') || (item.name === 'jsdata') || (item.name === 'jscontroller') || (item.name === 'face') || (item.name.includes('pattern')) || (item.name.includes('length')) || (item.name === 'border') || (item.name === 'formnovalidate') || (item.name === 'required-field') || (item.name === 'ng-click') || (item.name === 'tabindex') || (item.name === 'required') || (item.name === 'strtindx') || ((item.name === 'title') && (item.value === '')) || (item.name === 'autofocus') || (item.name === 'tabindex') || ((item.name === 'type') && (item.value === 'text')) || (item.name === 'ac_columns') || // (item.name.startsWith('d')) ||\r\n" + 
			"        (item.name === 'ac_order_by') || (item.name.startsWith('aria-')) || (item.name === 'href1' && !(item.value.length <= 50)) || (item.name === 'aria-autocomplete') || (item.name === 'autocapitalize') || (item.name === 'jsaction') || (item.name === 'autocorrect') || (item.name === 'aria-haspopup') || (item.name === 'style') || (item.name === 'size') || (item.name === 'height') || (item.name === 'width') || (item.name.startsWith('on')) || (item.name === 'autocomplete') || (item.name === 'value' && item.value.length <= 2) || (item.name === 'ng-model-options') || (item.name === 'ng-model-update-on-enter') || (item.name === 'magellan-navigation-filter') || (item.name === 'ng-blur') || (item.name === 'ng-focus') || (item.name === 'ng-trim') || (item.name === 'spellcheck') || (item.name === 'target') || (item.name === 'rel') || (item.name === 'maxlength');\r\n" + 
			"    }\r\n" + 
			"\r\n" + 
			"    function isAngular(item) {\r\n" + 
			"        return (item.name.startsWith('ng-'))(item.name == 'ng-click') || (item.name = 'ng-class') || (item.name == 'ng-controller') || (item.name === 'ng-model-options') || (item.name === 'ng-model-update-on-enter') || (item.name === 'magellan-navigation-filter') || (item.name === 'ng-blur') || (item.name === 'ng-focus');\r\n" + 
			"    }\r\n" + 
			"\r\n" + 
			"    function addAllXpathAttributesBbased(attribute, tagName, element) {\r\n" + 
			"        Array.prototype.slice.call(attribute)\r\n" + 
			"        .forEach(function (item) {\r\n" + 
			"            // Filter attribute not to shown in xpath\r\n" + 
			"            //  atrributesArray.push(item.name);\r\n" + 
			"            if (!(filterAttributesFromElement(item))) {\r\n" + 
			"                // Pushing xpath to arrays\r\n" + 
			"                switch (item.name) {\r\n" + 
			"                case 'id':\r\n" + 
			"                    let id = getIdBasedXpath(element, tagName)\r\n" + 
			"                       if (id != null) {\r\n" + 
			"                            \r\n" + 
			"                            var ids = id;\r\n" + 
			"                        };\r\n" + 
			"                    break;\r\n" + 
			"                case 'class':\r\n" + 
			"                    let className = getClassBasedXpath(element, tagName)\r\n" + 
			"                        if (className != null) {\r\n" + 
			"                            var clasn = className;\r\n" + 
			"                        }\r\n" + 
			"                        break;\r\n" + 
			"                case 'name':\r\n" + 
			"                    let name = getNameBasedXpath(element, tagName)\r\n" + 
			"                        if (name != null) {\r\n" + 
			"                            var names = name;\r\n" + 
			"                        }\r\n" + 
			"                        break;\r\n" + 
			"                default:\r\n" + 
			"                    let temp = item.value;\r\n" + 
			"                    var allXpathAttr = null;\r\n" + 
			"                    if (temp != '') {\r\n" + 
			"                        allXpathAttr = `//${tagName}[@${item.name}='${temp}']`\r\n" + 
			"                    }\r\n" + 
			"                    if (getCountForEachXpath(allXpathAttr) == 1) {\r\n" + 
			"                        var xarr = collectionbased;\r\n" + 
			"                        break;\r\n" + 
			"                    } else {\r\n" + 
			"                        let temp = addIndexToXpath(allXpathAttr);\r\n" + 
			"                        if (temp != undefined) {}\r\n" + 
			"                    }\r\n" + 
			"                    break;\r\n" + 
			"                }\r\n" + 
			"            }\r\n" + 
			"        });\r\n" + 
			"\r\n" + 
			"    }\r\n" + 
			"    function getCountForEachXpath(element) {\r\n" + 
			"        try {\r\n" + 
			"            return document.evaluate('count(' + element + ')', document, null, XPathResult.ANY_TYPE, null)\r\n" + 
			"            .numberValue;\r\n" + 
			"        } catch (error) {}\r\n" + 
			"    }\r\n" + 
			"    function addIndexToXpath(allXpathAttr) {\r\n" + 
			"        try {\r\n" + 
			"            var index = 0;\r\n" + 
			"            let doc = document.evaluate(allXpathAttr, document, null, XPathResult.ANY_TYPE, null);\r\n" + 
			"            var next = doc.iterateNext();\r\n" + 
			"            try {\r\n" + 
			"                while (next && index <= maxIndex) {\r\n" + 
			"                    index++;\r\n" + 
			"                    if ((next.attributes.objectspyxpath) != undefined) {\r\n" + 
			"                        throw 'break';\r\n" + 
			"                    }\r\n" + 
			"                    next = doc.iterateNext();\r\n" + 
			"                }\r\n" + 
			"            } catch (error) {}\r\n" + 
			"            let indexedXpath = `(${allXpathAttr})[${index}]`;\r\n" + 
			"            if (index <= maxIndex) {\r\n" + 
			"                let c = getCountForEachXpath(indexedXpath)\r\n" + 
			"                    if (c > 0) {\r\n" + 
			"                        return indexedXpath;\r\n" + 
			"                    }\r\n" + 
			"            } else\r\n" + 
			"                return null;\r\n" + 
			"        } catch (error) {}\r\n" + 
			"\r\n" + 
			"    }\r\n" + 
			"    function getIdBasedXpath(element, tagName) {\r\n" + 
			"        let idBasedXpath = null;\r\n" + 
			"        let clicketItemId = element.id;\r\n" + 
			"        let re = new RegExp('\\\\d{' + maxId + ',}', '\\g');\r\n" + 
			"        let matches = re.test(clicketItemId);\r\n" + 
			"        if ((clicketItemId != null) && (clicketItemId.length > 0) && matches == false) {\r\n" + 
			"            let tempId = \"[@id=\\'\" + clicketItemId + \"\\']\";\r\n" + 
			"            idBasedXpath = '//' + '*' + tempId;\r\n" + 
			"            let count = getCountForEachXpath(idBasedXpath)\r\n" + 
			"                if (count == 0) {\r\n" + 
			"                    return null;\r\n" + 
			"                } else if (count == 1) {\r\n" + 
			"                    return clicketItemId;\r\n" + 
			"                } else {\r\n" + 
			"                    idBasedXpath = '//' + tagName + tempId;\r\n" + 
			"                    if (count > 1) {\r\n" + 
			"                        idBasedXpath = addIndexToXpath(idBasedXpath)\r\n" + 
			"                            if (idBasedXpath != null) {\r\n" + 
			"                                var idxpath = idBasedXpath;\r\n" + 
			"                            }\r\n" + 
			"                            return null;\r\n" + 
			"                    }\r\n" + 
			"                }\r\n" + 
			"        }\r\n" + 
			"        return idBasedXpath;\r\n" + 
			"    }\r\n" + 
			"    // To get class based xpath\r\n" + 
			"    function getClassBasedXpath(element, tagName) {\r\n" + 
			"        let classBasedXpath = null;\r\n" + 
			"        let clickedItemClass = element.className;\r\n" + 
			"        var lowerCaseTag = tagName.toLowerCase();\r\n" + 
			"        if (lowerCaseTag == \"svg\" && typeof clickedItemClass !== \"undefined\" && clickedItemClass !== null) {\r\n" + 
			"            clickedItemClass = element.className.baseVal;\r\n" + 
			"        }\r\n" + 
			"        let splitClass = clickedItemClass.trim()\r\n" + 
			"            .split(\" \");\r\n" + 
			"        if (splitClass.length > 2) {\r\n" + 
			"            let cl = `${splitClass[0]} ${splitClass[1]}`;\r\n" + 
			"            let temp = `//${tagName}[contains(@class,'${cl}')]`;\r\n" + 
			"            let count = getCountForEachXpath(temp)\r\n" + 
			"                if (count == 0) {\r\n" + 
			"                    return null;\r\n" + 
			"                } else if (count > 1) {\r\n" + 
			"                    temp = addIndexToXpath(temp)\r\n" + 
			"                }\r\n" + 
			"                return temp;\r\n" + 
			"        }\r\n" + 
			"        if (!((clickedItemClass === \"\") || (clickedItemClass === undefined))) {\r\n" + 
			"            let tempClass = `//*[@class='${clickedItemClass}']`;\r\n" + 
			"            let count = getCountForEachXpath(tempClass);\r\n" + 
			"            let spl = clickedItemClass.trim()\r\n" + 
			"                .split(\" \")\r\n" + 
			"                if (count == 1 && spl.length == 1) {\r\n" + 
			"                    var spls = clickedItemClass;\r\n" + 
			"                    return null;\r\n" + 
			"                } else {\r\n" + 
			"                    classBasedXpath = `//${tagName}[@class='${clickedItemClass}']`;\r\n" + 
			"                    let count = getCountForEachXpath(classBasedXpath)\r\n" + 
			"                        if (count == 0) {\r\n" + 
			"                            return null;\r\n" + 
			"                        } else if (count == 1) {\r\n" + 
			"                            return classBasedXpath;\r\n" + 
			"                        } else {\r\n" + 
			"                            classBasedXpath = addIndexToXpath(classBasedXpath)\r\n" + 
			"                        }\r\n" + 
			"                }\r\n" + 
			"        }\r\n" + 
			"        return classBasedXpath;\r\n" + 
			"    }\r\n" + 
			"    // To get Name based xpath\r\n" + 
			"    function getNameBasedXpath(element, tagName) {\r\n" + 
			"        let nameBasedXpath = null;\r\n" + 
			"        let clickedItemName = element.attributes.name.value;\r\n" + 
			"        let matches = clickedItemName.match(/\\d{3,}/g);\r\n" + 
			"        if (!((clickedItemName === \"\") || (clickedItemName === undefined) || matches != null)) {\r\n" + 
			"            let tempName = \"[@name=\\'\" + clickedItemName + \"\\']\";\r\n" + 
			"            let tem = `//*${tempName}`;\r\n" + 
			"            let count = getCountForEachXpath(tem)\r\n" + 
			"                if (count == 1) {\r\n" + 
			"                   \r\n" + 
			"                    var clicitem = clickedItemName\r\n" + 
			"                } else if (count > 1) {\r\n" + 
			"                    tem = `//${tagName}${tempName}`;\r\n" + 
			"                    nameBasedXpath = addIndexToXpath(tem)\r\n" + 
			"                }\r\n" + 
			"        }\r\n" + 
			"        return nameBasedXpath;\r\n" + 
			"    }\r\n" + 
			"\r\n" + 
			"    // Following-sibling push to array\r\n" + 
			"    try {\r\n" + 
			"        xpathFollowingSibling(preiousSiblingElement, tagName);\r\n" + 
			"    } catch (e) {}\r\n" + 
			"\r\n" + 
			"    followsib = function (preiousSiblingElement, tagName) {\r\n" + 
			"        if (preiousSiblingElement != null || preiousSiblingElement != undefined) {\r\n" + 
			"            var temp = addFollowingSibling(preiousSiblingElement, tagName);\r\n" + 
			"            return temp;\r\n" + 
			"        }\r\n" + 
			"    };\r\n" + 
			"    function evaluateXpath(element) {\r\n" + 
			"        try {\r\n" + 
			"            return document.evaluate(element, document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null);\r\n" + 
			"        } catch (error) {}\r\n" + 
			"    }\r\n" + 
			"\r\n" + 
			"    function addFollowingSibling(preSib, tagName) {\r\n" + 
			"        try {\r\n" + 
			"            followingclassbasedXpath = null;\r\n" + 
			"            followingidbasedXpath = null;\r\n" + 
			"            followingTextbasedXpath = null;\r\n" + 
			"            followingnamebasedXpath = null;\r\n" + 
			"            followingattributebasedXpath = null;\r\n" + 
			"            let classHasSpace = false;\r\n" + 
			"            let temp;\r\n" + 
			"            let follArray = [];\r\n" + 
			"            let preSibAttributes = preSib.attributes;\r\n" + 
			"            let preSibTextContent = preSib.textContent;\r\n" + 
			"            while (preSib) {\r\n" + 
			"                if (preSibAttributes.length != 0 && preSibTextContent != null || preSibTextContent != '') {\r\n" + 
			"                    break;\r\n" + 
			"                } else {\r\n" + 
			"                    preSib = preSib.previousElementSibling;\r\n" + 
			"                    preSibAttributes = preSib.attributes;\r\n" + 
			"                    preSibTextContent = preSib.textContent;\r\n" + 
			"                }\r\n" + 
			"            }\r\n" + 
			"            let previousSiblingTagName = preSib.tagName.toLowerCase();\r\n" + 
			"            Array.prototype.slice.call(preSib.attributes)\r\n" + 
			"            .forEach(function (item) {\r\n" + 
			"                if (!(filterAttributesFromElement(item))) {\r\n" + 
			"                    let tempvalue = null;\r\n" + 
			"                    switch (item.name) {\r\n" + 
			"                    case 'id':\r\n" + 
			"                        if (preSib.hasAttribute('id')) {\r\n" + 
			"                            let id = preSib.id;\r\n" + 
			"                            let re = new RegExp('\\\\d{' + maxId + ',}', '\\g');\r\n" + 
			"                            let matches = re.test(id);\r\n" + 
			"                            if ((id != null) && (id.length > 0) && matches == false) {\r\n" + 
			"                                tempvalue = id;\r\n" + 
			"                            }\r\n" + 
			"                        }\r\n" + 
			"                        break;\r\n" + 
			"                    case 'class':\r\n" + 
			"                        if (preSib.hasAttribute('class')) {\r\n" + 
			"                            tempvalue = preSib.className;\r\n" + 
			"                            let splClass = tempvalue.trim()\r\n" + 
			"                                .split(\" \");\r\n" + 
			"                            if (splClass.length > 2) {\r\n" + 
			"                                tempvalue = `contains(@class,'${splClass[0]} ${splClass[1]}')`;\r\n" + 
			"                                classHasSpace = true;\r\n" + 
			"                            }\r\n" + 
			"                        }\r\n" + 
			"                        break;\r\n" + 
			"                    case 'name':\r\n" + 
			"                        if (preSib.hasAttribute('name')) {\r\n" + 
			"                            tempvalue = preSib.name;\r\n" + 
			"                        }\r\n" + 
			"                        break;\r\n" + 
			"                    default:\r\n" + 
			"                        tempvalue = item.value;\r\n" + 
			"                    }\r\n" + 
			"                    if (tempvalue == '') {\r\n" + 
			"                        tempvalue = null;\r\n" + 
			"                    }\r\n" + 
			"                    if (classHasSpace) {\r\n" + 
			"                        temp = `//${previousSiblingTagName}[${tempvalue}]/following-sibling::${tagName}[1]`\r\n" + 
			"                            if (temp.startsWith('//')) {\r\n" + 
			"                                if (getCountForEachXpath(temp) == 1 && evaluateXpath(temp)\r\n" + 
			"                                    .singleNodeValue.attributes.objectspyxpath != undefined) {\r\n" + 
			"                                    follArray.push(temp);\r\n" + 
			"                                } else {\r\n" + 
			"                                    let t = addIndexToXpath(`//${previousSiblingTagName}[${tempvalue}]/following-sibling::${tagName}`)\r\n" + 
			"                                        if (t != undefined) {\r\n" + 
			"                                            follArray.push(t);\r\n" + 
			"                                        } else\r\n" + 
			"                                            temp = null;\r\n" + 
			"                                }\r\n" + 
			"                            }\r\n" + 
			"\r\n" + 
			"                    } else if (tempvalue != null) {\r\n" + 
			"                        temp = `//${previousSiblingTagName}[@${item.name}='${tempvalue}']/following-sibling::${tagName}[1]`\r\n" + 
			"                            if (temp.startsWith('//')) {\r\n" + 
			"                                if (getCountForEachXpath(temp) == 1 && evaluateXpath(temp)\r\n" + 
			"                                    .singleNodeValue.attributes.objectspyxpath != undefined) {\r\n" + 
			"                                    follArray.push(temp);\r\n" + 
			"                                } else {\r\n" + 
			"                                    let t = addIndexToXpath(`//${previousSiblingTagName}[@${item.name}='${tempvalue}']/following-sibling::${tagName}`)\r\n" + 
			"                                        if (t != undefined) {\r\n" + 
			"                                            follArray.push(t);\r\n" + 
			"                                        } else\r\n" + 
			"                                            temp = null;\r\n" + 
			"                                }\r\n" + 
			"                            }\r\n" + 
			"                    }\r\n" + 
			"                }\r\n" + 
			"            });\r\n" + 
			"            if (temp == null || (preSib.innerText.length > 1)) {\r\n" + 
			"                let temp1;\r\n" + 
			"                let labelText;\r\n" + 
			"                let tag;\r\n" + 
			"                let counter = 0;\r\n" + 
			"                let bo = false;\r\n" + 
			"                let child = preSib.parentNode.children;\r\n" + 
			"                var j = 0;\r\n" + 
			"                while (child[j] != preSib) {\r\n" + 
			"                    j++;\r\n" + 
			"                    counter++;\r\n" + 
			"                }\r\n" + 
			"                for (var xy = counter; xy >= 0; xy--) {\r\n" + 
			"                    let text = child[xy].textContent;\r\n" + 
			"                    if (text != '') {\r\n" + 
			"                        labelText = text;\r\n" + 
			"                        tag = child[xy].tagName.toLowerCase()\r\n" + 
			"                            break;\r\n" + 
			"                    }\r\n" + 
			"                   if (xy == 0 && text != '') {\r\n" + 
			"                        break;\r\n" + 
			"                    }\r\n" + 
			"                }\r\n" + 
			"                if (labelText.match(/[\\r\\n\\x0B\\x0C\\u0085\\u2028\\u2029]+/g)) {\r\n" + 
			"                    labelText = labelText.replace(/[\\r\\n\\x0B\\x0C\\u0085\\u2028\\u2029]+/g, \" \")\r\n" + 
			"                        bo = true;\r\n" + 
			"                }\r\n" + 
			"                if (bo && labelText.trim()\r\n" + 
			"                    .length > 1) {\r\n" + 
			"                    temp1 = `//${tag}[text()[normalize-space()='${labelText.trim()}']]/following-sibling::${tagName}[1]`;\r\n" + 
			"                } else {\r\n" + 
			"                    temp1 = `//${tag}[text()='${labelText}']/following-sibling::${tagName}[1]`;\r\n" + 
			"                }\r\n" + 
			"                let c = getCountForEachXpath(temp1)\r\n" + 
			"                    temp1 = `//${tag}[text()='${labelText}']/following-sibling::${tagName}`;\r\n" + 
			"                if (c == 0) {\r\n" + 
			"                    return null\r\n" + 
			"                }\r\n" + 
			"                c = getCountForEachXpath(temp1)\r\n" + 
			"                    if (c == 1 && evaluateXpath(temp1)\r\n" + 
			"                        .singleNodeValue.attributes.objectspyxpath != undefined) {\r\n" + 
			"                        follArray.push(temp1);\r\n" + 
			"                    } else if ((c != undefined) || (c != null)) {\r\n" + 
			"                        xp = addIndexToXpath(temp1)\r\n" + 
			"                            if (xp != undefined) {\r\n" + 
			"                                follArray.push(xp);\r\n" + 
			"                            }\r\n" + 
			"                    }\r\n" + 
			"            }\r\n" + 
			"\r\n" + 
			"            for (var i = 0; i < follArray.length; i++) {\r\n" + 
			"                if (follArray[i].includes('@id') || follArray[i].includes('@class') || follArray[i].includes('@name')) {\r\n" + 
			"                    if (follArray[i].includes('@id')) {\r\n" + 
			"                        followingclassbasedXpath = follArray[i];\r\n" + 
			"                        break;\r\n" + 
			"                    } else {\r\n" + 
			"                        followingclassbasedXpath = follArray[i];\r\n" + 
			"                        break;\r\n" + 
			"                    }\r\n" + 
			"                } else if (follArray[i].includes('contains') || follArray[i].includes('text()')) {\r\n" + 
			"                    followingTextbasedXpath = follArray[i];\r\n" + 
			"\r\n" + 
			"                } else {\r\n" + 
			"                    var followingattributebasedXpathcheck = follArray[i];\r\n" + 
			"                    let c = getCountForEachXpath(followingattributebasedXpathcheck);\r\n" + 
			"                    if (c == 1 && evaluateXpath(followingattributebasedXpathcheck)) {\r\n" + 
			"                        followingattributebasedXpath = followingattributebasedXpathcheck;\r\n" + 
			"                    }\r\n" + 
			"\r\n" + 
			"                }\r\n" + 
			"\r\n" + 
			"            }\r\n" + 
			"            if (!followingclassbasedXpath || !followingattributebasedXpath || !followingTextbasedXpath) {\r\n" + 
			"                if (followingclassbasedXpath != null) {\r\n" + 
			"                    return followingclassbasedXpath;\r\n" + 
			"                } else if (followingattributebasedXpath != null) {\r\n" + 
			"                    return followingattributebasedXpath;\r\n" + 
			"                } else if (followingTextbasedXpath != null) {\r\n" + 
			"                    return followingTextbasedXpath;\r\n" + 
			"                }\r\n" + 
			"\r\n" + 
			"            } else {\r\n" + 
			"                return null;\r\n" + 
			"            }\r\n" + 
			"\r\n" + 
			"        } catch (error) {}\r\n" + 
			"    }\r\n" + 
			"\r\n" + 
			"    pseudoGuid = function () {\r\n" + 
			"        var result;\r\n" + 
			"        hello(\"pseudoGuid\");\r\n" + 
			"        result = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx';\r\n" + 
			"        result = result.replace(/[xy]/g, function (re_match) {\r\n" + 
			"            var random_value,\r\n" + 
			"            replacement;\r\n" + 
			"            random_value = Math.random() * 16 | 0;\r\n" + 
			"            replacement = re_match === 'x' ? random_value : random_value & 0x3 | 0x8;\r\n" + 
			"            return replacement.toString(16);\r\n" + 
			"        });\r\n" + 
			"        bye(\"pseudoGuid\");\r\n" + 
			"        return result;\r\n" + 
			"    };\r\n" + 
			"\r\n" + 
			"    getInputElementsByTypeAndValue = function (inputType, inputValue) {\r\n" + 
			"        var allDocumentInputElements,\r\n" + 
			"        inputElement,\r\n" + 
			"        result,\r\n" + 
			"        _i,\r\n" + 
			"        _len;\r\n" + 
			"        hello(\"getInputElementsByTypeAndValue\");\r\n" + 
			"        allDocumentInputElements = document.getElementsByTagName('input');\r\n" + 
			"        result = new Array();\r\n" + 
			"        for (_i = 0, _len = allDocumentInputElements.length; _i < _len; _i++) {\r\n" + 
			"            inputElement = allDocumentInputElements[_i];\r\n" + 
			"            if (inputElement.type === inputType && inputElement.value === inputValue) {\r\n" + 
			"                result.push(inputElement);\r\n" + 
			"           }\r\n" + 
			"        }\r\n" + 
			"        bye(\"getInputElementsByTypeAndValue\");\r\n" + 
			"        return result;\r\n" + 
			"    };\r\n" + 
			"\r\n" + 
			"    getElementId = function (element) {\r\n" + 
			"        var selector = '';\r\n" + 
			"        hello('getElementId');\r\n" + 
			"\r\n" + 
			"        if (element instanceof Element && element.nodeType === ELEMENT_NODE && element.id) {\r\n" + 
			"            selector = element.id;\r\n" + 
			"            console.log(\"Element id------------------------\" + selector);\r\n" + 
			"        }\r\n" + 
			"        if (element.id) {\r\n" + 
			"            var ele = getElementsById(element.id);\r\n" + 
			"            if (ele.length = 1) {\r\n" + 
			"                selector = element.id;\r\n" + 
			"                console.log(\"Element id------------------------\" + selector);\r\n" + 
			"            }\r\n" + 
			"        }\r\n" + 
			"        bye('getElementId');\r\n" + 
			"        return selector;\r\n" + 
			"    };\r\n" + 
			"\r\n" + 
			"    getiframeElementId = function (element) {\r\n" + 
			"        var selector = '';\r\n" + 
			"        hello('getElementId');\r\n" + 
			"        if (element instanceof Element && element.nodeType === ELEMENT_NODE && element.id) {\r\n" + 
			"            selector = element.id;\r\n" + 
			"            console.log(\"Iframe Element id------------------------\" + selector);\r\n" + 
			"        }\r\n" + 
			"        bye('getElementId');\r\n" + 
			"        return selector;\r\n" + 
			"    };\r\n" + 
			"\r\n" + 
			"    getCompleteXPath = function (element) {\r\n" + 
			"        if (element.tagName.toLowerCase() == 'html')\r\n" + 
			"            return '/html[1]';\r\n" + 
			"        if (element === document.body)\r\n" + 
			"            return '/html[1]/body[1]';\r\n" + 
			"        var ix = 0;\r\n" + 
			"        var siblings = element.parentNode.childNodes;\r\n" + 
			"        for (var i = 0; i < siblings.length; i++) {\r\n" + 
			"            var sibling = siblings[i];\r\n" + 
			"            if (sibling === element)\r\n" + 
			"                return getCompleteXPath(element.parentNode) + '/' + element.tagName.toLowerCase() + '[' + (ix + 1) + ']';\r\n" + 
			"            if (sibling.nodeType === 1 && sibling.tagName.toLowerCase() === element.tagName.toLowerCase())\r\n" + 
			"                ix++;\r\n" + 
			"        }\r\n" + 
			"    };\r\n" + 
			"\r\n" + 
			"    splitXpath = function (path) {\r\n" + 
			"        var start;\r\n" + 
			"        if (path.length >= 40 && path.length <= 79) {\r\n" + 
			"            start = 3;\r\n" + 
			"        } else if (path.length > 79 && path.length <= 100) {\r\n" + 
			"            start = 6;\r\n" + 
			"        } else if (path.length > 100 && path.length <= 150) {\r\n" + 
			"            start = 10;\r\n" + 
			"        } else if (path.length > 150) {\r\n" + 
			"            start = 15;\r\n" + 
			"        }\r\n" + 
			"        var xpath_delimiter = '/',\r\n" + 
			"        xpath_tokens = path.split(xpath_delimiter).slice(start),\r\n" + 
			"        rightwing = xpath_tokens.join(xpath_delimiter);\r\n" + 
			"        var tokens2 = path.split(xpath_delimiter).slice(0, start),\r\n" + 
			"        leftwing = tokens2.join(xpath_delimiter);\r\n" + 
			"        if (rightwing != null && rightwing != \"\") {\r\n" + 
			"            var joinxpath = leftwing + \"/\" + \"\\n\" + rightwing;\r\n" + 
			"            menuxpath = joinxpath;\r\n" + 
			"            console.log(\"righttttwing not nulll\" + joinxpath);\r\n" + 
			"        } else {\r\n" + 
			"            menuxpath = path;\r\n" + 
			"            console.log(\"rightwingISSSnulllll\" + joinxpath);\r\n" + 
			"        }\r\n" + 
			"        return menuxpath;\r\n" + 
			"    }\r\n" + 
			"    splitCss = function (menucsspath) {\r\n" + 
			"        var start;\r\n" + 
			"        if (menucsspath.length >= 40 && menucsspath.length <= 79) {\r\n" + 
			"            start = 3;\r\n" + 
			"        } else if (menucsspath.length > 79 && menucsspath.length <= 100) {\r\n" + 
			"            start = 6;\r\n" + 
			"        } else if (menucsspath.length > 100 && menucsspath.length <= 150) {\r\n" + 
			"            start = 10;\r\n" + 
			"        } else if (menucsspath.length > 150) {\r\n" + 
			"            start = 12;\r\n" + 
			"        }\r\n" + 
			"        var css_delimiter = '>',\r\n" + 
			"        css_tokens = menucsspath.split(css_delimiter).slice(start),\r\n" + 
			"        rightwing = css_tokens.join(css_delimiter);\r\n" + 
			"        var tokens2 = menucsspath.split(css_delimiter).slice(0, start),\r\n" + 
			"        leftwing = tokens2.join(css_delimiter);\r\n" + 
			"\r\n" + 
			"        if (rightwing != \"\") {\r\n" + 
			"            var joints1 = leftwing + \"\\n\" + '>' + rightwing;\r\n" + 
			"            menucsspath = joints1;\r\n" + 
			"        } else {\r\n" + 
			"            menucsspath = css_selector;\r\n" + 
			"        }\r\n" + 
			"        return menucsspath;\r\n" + 
			"    }\r\n" + 
			"\r\n" + 
			"    getCssSelectorOF = function (element) {\r\n" + 
			"        hello('getCssSelectorOF');\r\n" + 
			"        var path = [];\r\n" + 
			"        var flag = false;\r\n" + 
			"        if (!(element instanceof Element)) {\r\n" + 
			"            var selector = element.nodeName.toLowerCase();\r\n" + 
			"            if (element.id) {\r\n" + 
			"                console.log(\"inside css if condition Id\");\r\n" + 
			"                if (element.id.indexOf('-') > -1) {\r\n" + 
			"                    selector += '[id = \"' + element.id + '\"]';\r\n" + 
			"                } else {\r\n" + 
			"                    selector += '#' + element.id;\r\n" + 
			"                }\r\n" + 
			"                return selector;\r\n" + 
			"            } else {\r\n" + 
			"                var element_sibling = element;\r\n" + 
			"                var sibling_cnt = 1;\r\n" + 
			"                while (element_sibling = element_sibling.previousElementSibling) {\r\n" + 
			"                    if (element_sibling.nodeName.toLowerCase() == selector)\r\n" + 
			"                        sibling_cnt++;\r\n" + 
			"                }\r\n" + 
			"                if (sibling_cnt != 1)\r\n" + 
			"                    selector += ':nth-of-type(' + sibling_cnt + ')';\r\n" + 
			"                path.unshift(selector);\r\n" + 
			"                element = element.parentNode;\r\n" + 
			"            }\r\n" + 
			"\r\n" + 
			"            return path.toString();\r\n" + 
			"        }\r\n" + 
			"        while (element.nodeType === ELEMENT_NODE) {\r\n" + 
			"            var selector = element.nodeName.toLowerCase();\r\n" + 
			"            if (element.id) {\r\n" + 
			"                if (element.id.indexOf('-') > -1) {\r\n" + 
			"                    selector += '[id = \"' + element.id + '\"]';\r\n" + 
			"                } else {\r\n" + 
			"                    selector += '#' + element.id;\r\n" + 
			"                }\r\n" + 
			"                path.unshift(selector);\r\n" + 
			"                break;\r\n" + 
			"            } else {\r\n" + 
			"                var element_sibling = element;\r\n" + 
			"                var sibling_cnt = 1;\r\n" + 
			"                while (element_sibling = element_sibling.previousElementSibling) {\r\n" + 
			"                    if (element_sibling.nodeName.toLowerCase() == selector)\r\n" + 
			"                        sibling_cnt++;\r\n" + 
			"                }\r\n" + 
			"                if (sibling_cnt != 1)\r\n" + 
			"                    selector += ':nth-of-type(' + sibling_cnt + ')';\r\n" + 
			"            }\r\n" + 
			"            path.unshift(selector);\r\n" + 
			"            element = element.parentNode;\r\n" + 
			"        }\r\n" + 
			"\r\n" + 
			"        bye('getCssSelectorOF');\r\n" + 
			"        tempcss = path.join(' > ')\r\n" + 
			"            return tempcss;\r\n" + 
			"    };\r\n" + 
			"    function getElementsById(elementID) {\r\n" + 
			"        var elementCollection = new Array();\r\n" + 
			"        var allElements = document.getElementsByTagName(\"*\");\r\n" + 
			"        for (i = 0; i < allElements.length; i++) {\r\n" + 
			"            if (allElements[i].id == elementID)\r\n" + 
			"                elementCollection.push(allElements[i]);\r\n" + 
			"        }\r\n" + 
			"        return elementCollection;\r\n" + 
			"    }\r\n" + 
			"\r\n" + 
			"    function getElementsByClass(elementCLASS) {\r\n" + 
			"        var elementCollection = new Array();\r\n" + 
			"        var allElements = document.getElementsByTagName(\"*\");\r\n" + 
			"        for (i = 0; i < allElements.length; i++) {\r\n" + 
			"            if (allElements[i].className == elementCLASS)\r\n" + 
			"                elementCollection.push(allElements[i]);\r\n" + 
			"        }\r\n" + 
			"        return elementCollection;\r\n" + 
			"    }\r\n" + 
			"    function getX(element) {\r\n" + 
			"        var element_sibling,\r\n" + 
			"        siblingTagName,\r\n" + 
			"        siblings,\r\n" + 
			"        cnt,\r\n" + 
			"        sibling_count;\r\n" + 
			"\r\n" + 
			"        hello(\"getPathTo\");\r\n" + 
			"        if (typeof iframeElement == 'undefined') {\r\n" + 
			"            iframeDocument = \"\";\r\n" + 
			"\r\n" + 
			"        } else {\r\n" + 
			"            iframeDocument = iframeElement.contentDocument || iframeElement.contentWindow.document;\r\n" + 
			"        }\r\n" + 
			"        if (element === iframeDocument) {\r\n" + 
			"            if (typeof element.tagName == 'undefined') {\r\n" + 
			"                var nul = \"\";\r\n" + 
			"                return nul;\r\n" + 
			"            }\r\n" + 
			"            return element.tagName;\r\n" + 
			"        } else {\r\n" + 
			"            var elementTagName = element.tagName;\r\n" + 
			"            if (element.id != '') {\r\n" + 
			"                var elementsId = getElementsById(element.id);\r\n" + 
			"                if (elementsId.length <= 1) {\r\n" + 
			"                    return '//' + elementTagName.toLowerCase() + '[@id=\\'' + element.id + '\\']';\r\n" + 
			"                } else if (element.name && document.getElementsByName(element.name).length === 1) {\r\n" + 
			"                    return '//' + elementTagName.toLowerCase() + '[@name=\"' + element.name + '\"]';\r\n" + 
			"                }\r\n" + 
			"            } else if (element === document.body) {\r\n" + 
			"                return '/html/' + elementTagName.toLowerCase();\r\n" + 
			"            }\r\n" + 
			"        }\r\n" + 
			"        sibling_count = 0;\r\n" + 
			"        siblings = element.parentNode.childNodes;\r\n" + 
			"        siblings_length = siblings.length;\r\n" + 
			"        for (cnt = 0; cnt < siblings_length; cnt++) {\r\n" + 
			"            var element_sibling = siblings[cnt];\r\n" + 
			"            if (element_sibling.nodeType !== ELEMENT_NODE) { // not ELEMENT_NODE\r\n" + 
			"                continue;\r\n" + 
			"            }\r\n" + 
			"            if (element_sibling === element) {\r\n" + 
			"                return getX(element.parentNode) + '/' + element.tagName.toLowerCase() + '[' + (sibling_count + 1) + ']';\r\n" + 
			"            }\r\n" + 
			"            if (element_sibling.nodeType === 1 && element_sibling.tagName === element.tagName) {\r\n" + 
			"                sibling_count++;\r\n" + 
			"            }\r\n" + 
			"        }\r\n" + 
			"        return bye(\"getPathTo\");\r\n" + 
			"    }\r\n" + 
			"\r\n" + 
			"    getPathTo = function (element) {\r\n" + 
			"        var element_sibling,\r\n" + 
			"        siblingTagName,\r\n" + 
			"        siblings,\r\n" + 
			"        cnt,\r\n" + 
			"        sibling_count;\r\n" + 
			"\r\n" + 
			"        hello(\"getPathTo\");\r\n" + 
			"        if (typeof iframeElement == 'undefined') {\r\n" + 
			"            iframeDocument = \"\";\r\n" + 
			"\r\n" + 
			"        } else {\r\n" + 
			"            iframeDocument = iframeElement.contentDocument || iframeElement.contentWindow.document;\r\n" + 
			"        }\r\n" + 
			"        if (element === iframeDocument) {\r\n" + 
			"            if (typeof element.tagName == 'undefined') {\r\n" + 
			"                var nul = \"\";\r\n" + 
			"                return nul;\r\n" + 
			"            }\r\n" + 
			"            return element.tagName;\r\n" + 
			"        } else if (element === document.body) {\r\n" + 
			"            return '/html/' + elementTagName.toLowerCase();\r\n" + 
			"        } else {\r\n" + 
			"            var elementTagName = element.tagName;\r\n" + 
			"            sibling_count = 0;\r\n" + 
			"            siblings = element.parentNode.childNodes;\r\n" + 
			"            siblings_length = siblings.length;\r\n" + 
			"            for (cnt = 0; cnt < siblings_length; cnt++) {\r\n" + 
			"                var element_sibling = siblings[cnt];\r\n" + 
			"                if (element_sibling.nodeType !== ELEMENT_NODE) { // not ELEMENT_NODE\r\n" + 
			"                    continue;\r\n" + 
			"                }\r\n" + 
			"                if (element_sibling === element) {\r\n" + 
			"                    return getX(element.parentNode) + '/' + element.tagName.toLowerCase() + '[' + (sibling_count + 1) + ']';\r\n" + 
			"                }\r\n" + 
			"                if (element_sibling.nodeType === 1 && element_sibling.tagName === element.tagName) {\r\n" + 
			"                    sibling_count++;\r\n" + 
			"                }\r\n" + 
			"            }\r\n" + 
			"\r\n" + 
			"        }\r\n" + 
			"\r\n" + 
			"        return bye(\"getPathTo\");\r\n" + 
			"    };\r\n" + 
			"    getPageXY = function (element) {\r\n" + 
			"        var x,\r\n" + 
			"        y;\r\n" + 
			"        hello(\"getPageXY\");\r\n" + 
			"        x = 0;\r\n" + 
			"        y = 0;\r\n" + 
			"        while (element) {\r\n" + 
			"            x += element.offsetLeft;\r\n" + 
			"            y += element.offsetTop;\r\n" + 
			"            element = element.offsetParent;\r\n" + 
			"        }\r\n" + 
			"        bye(\"getPageXY\");\r\n" + 
			"        if (isNaN(x) && isNaN(y)) {\r\n" + 
			"            x = 0;\r\n" + 
			"            y = 0;\r\n" + 
			"        }\r\n" + 
			"        return [x, y];\r\n" + 
			"    };\r\n" + 
			"\r\n" + 
			"    createCommand = function (jsonData) {\r\n" + 
			"        var myJSONText;\r\n" + 
			"        hello(\"createCommand\");\r\n" + 
			"        myJSONText = JSON.stringify(jsonData, null, 2);\r\n" + 
			"        document.skmatepr_command = myJSONText;\r\n" + 
			"        return bye(\"createCommand\");\r\n" + 
			"    };\r\n" + 
			"\r\n" + 
			"    addStyle = function (css) {\r\n" + 
			"        var head,\r\n" + 
			"        style;\r\n" + 
			"        hello(\"addStyle\");\r\n" + 
			"        head = document.getElementsByTagName('head')[0];\r\n" + 
			"        style = document.createElement('style');\r\n" + 
			"        style.type = 'text/css';\r\n" + 
			"        if (style.styleSheet) {\r\n" + 
			"            style.styleSheet.cssText = css;\r\n" + 
			"        } else {\r\n" + 
			"            style.appendChild(document.createTextNode(css));\r\n" + 
			"        }\r\n" + 
			"        head.appendChild(style);\r\n" + 
			"        return bye(\"addStyle\");\r\n" + 
			"    };\r\n" + 
			"    addStyle1 = function (css) {\r\n" + 
			"        var head,\r\n" + 
			"        style1;\r\n" + 
			"        hello(\"addStyle1\");\r\n" + 
			"        head = document.getElementsByTagName('head')[0];\r\n" + 
			"        style1 = document.createElement('style');\r\n" + 
			"        style1.type = 'text/css';\r\n" + 
			"        if (style1.styleSheet) {\r\n" + 
			"            style1.styleSheet.cssText = css;\r\n" + 
			"        } else {\r\n" + 
			"            style1.appendChild(document.createTextNode(css));\r\n" + 
			"        }\r\n" + 
			"        head.appendChild(style1);\r\n" + 
			"        return bye(\"addStyle1\");\r\n" + 
			"    };\r\n" + 
			"    preventEvent = function (event) {\r\n" + 
			"        hello(\"preventEvent\");\r\n" + 
			"        if (event.preventDefault) {\r\n" + 
			"            event.preventDefault();\r\n" + 
			"        }\r\n" + 
			"        event.returnValue = false;\r\n" + 
			"        if (event.stopPropagation) {\r\n" + 
			"            event.stopPropagation();\r\n" + 
			"        } else {\r\n" + 
			"            event.cancelBubble = true;\r\n" + 
			"        }\r\n" + 
			"        bye(\"preventEvent\");\r\n" + 
			"        return false;\r\n" + 
			"    };\r\n" + 
			"    prev = void 0;\r\n" + 
			"    document.Swd_prevActiveElement = void 0;\r\n" + 
			"    function getElementByXpath(path) {\r\n" + 
			"        if (path != \"\") {\r\n" + 
			"            return document.evaluate(path, document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;\r\n" + 
			"        }\r\n" + 
			"    }\r\n" + 
			"    handler = function (event) {\r\n" + 
			"        hello(\"handler\");\r\n" + 
			"        var targetPath;\r\n" + 
			"        var high;\r\n" + 
			"        if (event.target.tagName === \"IFRAME\") {\r\n" + 
			"            console.log(\"tagname of activEle\" + event.target.tagName);\r\n" + 
			"            console.log(\"iframe\");\r\n" + 
			"            iframeFlag = true;\r\n" + 
			"            iframeEle = document.activeElement;\r\n" + 
			"\r\n" + 
			"            if (iframeEle.tagName == \"IFRAME\") {\r\n" + 
			"                iframeEle = document.activeElement;\r\n" + 
			"            } else {\r\n" + 
			"                var bodIframe = document.getElementsByTagName(\"iframe\");\r\n" + 
			"                for (var j = 0; j <= bodIframe.length; j++) {\r\n" + 
			"                    iframeEle = bodIframe[j];\r\n" + 
			"                    if (iframeEle.id) {\r\n" + 
			"                        iframeDetails.id = getiframeElementId(iframeEle);\r\n" + 
			"                        break;\r\n" + 
			"                    } else {\r\n" + 
			"                        var iframeXpathOrg = getPathTo(iframeEle);\r\n" + 
			"                        break;\r\n" + 
			"                    }\r\n" + 
			"                }\r\n" + 
			"            }\r\n" + 
			"\r\n" + 
			"            clik = false;\r\n" + 
			"            var element = document.getElementsByTagName(\"iframe\");\r\n" + 
			"            for (var i = 0; i < element.length; i++) {\r\n" + 
			"                element[i].onmouseout = processMouseOut;\r\n" + 
			"                element[i].onmouseover = processMouseOver;\r\n" + 
			"            }\r\n" + 
			"\r\n" + 
			"            document.getElementById(iframeEle.id).contentDocument.addEventListener('mousemove', function (event) {\r\n" + 
			"                console.log(event.target.id);\r\n" + 
			"                isOverIFrame = true;\r\n" + 
			"            }\r\n" + 
			"                .bind(this));\r\n" + 
			"\r\n" + 
			"            function processMouseOver() {\r\n" + 
			"                console.log(\"IFrame mouse >> OVER << detected.\");\r\n" + 
			"\r\n" + 
			"                clik = true;\r\n" + 
			"\r\n" + 
			"            }\r\n" + 
			"\r\n" + 
			"            function processMouseOut() {\r\n" + 
			"                console.log(\"IFrame mouse >> OUT << detected.\");\r\n" + 
			"                isOverIFrame = false;\r\n" + 
			"\r\n" + 
			"            \r\n" + 
			"                top.focus();\r\n" + 
			"            }\r\n" + 
			"\r\n" + 
			"            if (iframeEle.id) {\r\n" + 
			"                iframeDetails.id = getiframeElementId(iframeEle);\r\n" + 
			"            } else {\r\n" + 
			"                var iframeXpathOrg = getPathTo(iframeEle);\r\n" + 
			"            }\r\n" + 
			"            if (iframeXpathOrg == \"\") {\r\n" + 
			"                if (iframeXpathOrg.includes(\"iframe\") == true) {\r\n" + 
			"                    iframeDetails.xpath = iframeXpathOrg;\r\n" + 
			"                }\r\n" + 
			"            }\r\n" + 
			"            console.log(\"iframe id : \" + iframeDetails.iframeid);\r\n" + 
			"            console.log(\"iframe xpath : \" + iframeDetails.iframexpath);\r\n" + 
			"\r\n" + 
			"            if (iframeDetails.iframeid == \"\") {\r\n" + 
			"                iframeElement = getElementByXpath(iframeDetails.iframexpath);\r\n" + 
			"                console.log(\"----------------------------------------------------\" + iframeElement);\r\n" + 
			"            } else if (iframeDetails.iframeid != \"\") {\r\n" + 
			"                iframeElement = document.getElementById(iframeDetails.iframeid);\r\n" + 
			"                console.log(\"----------------------------------------------------\" + iframeElement);\r\n" + 
			"            } else {\r\n" + 
			"                iframeElement = \"Please identify iframe locator manually\";\r\n" + 
			"            }\r\n" + 
			"           \r\n" + 
			"\r\n" + 
			"            var ids = iframeElement;\r\n" + 
			"            console.log(\"iframe id------\" + iframeDetails.iframeid);\r\n" + 
			"\r\n" + 
			"            if (ids != null) {\r\n" + 
			"                if (ids.contentWindow.document.body.addEventListener) {\r\n" + 
			"\r\n" + 
			"                    ids.contentWindow.document.body.addEventListener('mouseover', handler, false);\r\n" + 
			"                    ids.contentWindow.document.addEventListener('contextmenu', rightClickHandler, false);\r\n" + 
			"\r\n" + 
			"                } else if (ids.document.body.attachEvent) {\r\n" + 
			"                    ids.document.body.attachEvent('mouseover', function (e) {\r\n" + 
			"                        return handler(e || window.event);\r\n" + 
			"                    });\r\n" + 
			"                    ids.document.body.attachEvent('oncontextmenu', function (e) {\r\n" + 
			"                        return rightClickHandler(e || window.event);\r\n" + 
			"                    });\r\n" + 
			"                } else {\r\n" + 
			"                    ids.document.body.onmouseover = handler;\r\n" + 
			"                    ids.document.body.onmouseover = rightClickHandler;\r\n" + 
			"                }\r\n" + 
			"            }\r\n" + 
			"\r\n" + 
			"        } else {\r\n" + 
			"            console.log(\"tagname of activEle\" + event.target.tagName);\r\n" + 
			"            iframeFlag = false;\r\n" + 
			"\r\n" + 
			"        }\r\n" + 
			"\r\n" + 
			"        var body = null;\r\n" + 
			"        var tempLinkNode;\r\n" + 
			"       \r\n" + 
			"\r\n" + 
			"        window.addEventListener('beforeunload', function (e) {\r\n" + 
			"            if (visibile == \"hidden\") {\r\n" + 
			"                localStorage.setItem('isAvailable', 'false');\r\n" + 
			"                localStorage.setItem('newTabOpened', 'false');\r\n" + 
			"            }\r\n" + 
			"        });\r\n" + 
			"\r\n" + 
			"        console.log(\"iframe flag ------------\" + iframeFlag);\r\n" + 
			"        if (document.SkMate_Page_Recorder == null) {\r\n" + 
			"            return;\r\n" + 
			"        }\r\n" + 
			"        if (event.target === document.body || prev === event.target) {\r\n" + 
			"            return;\r\n" + 
			"        }\r\n" + 
			"        if (prev) {\r\n" + 
			"            prev.className = prev.className.replace(/\\s?\\bhighlight\\b/, '');\r\n" + 
			"            prev = void 0;\r\n" + 
			"            high = true;\r\n" + 
			"        }\r\n" + 
			"        if (event.target && event.ctrlKey && high == true) {\r\n" + 
			"            prev = event.target;\r\n" + 
			"            prev.className += \" highlight\";\r\n" + 
			"        }\r\n" + 
			"        return bye(\"handler\");\r\n" + 
			"    };\r\n" + 
			"\r\n" + 
			"    function isHidden(el) {\r\n" + 
			"        var style = window.getComputedStyle(el);\r\n" + 
			"        return (style.display === 'none')\r\n" + 
			"    }\r\n" + 
			"\r\n" + 
			"    rightClickHandler = function (event) {\r\n" + 
			"        var JsonData,\r\n" + 
			"        samples,\r\n" + 
			"        body,\r\n" + 
			"        eventPreventingResult,\r\n" + 
			"        mxy,\r\n" + 
			"        path,\r\n" + 
			"        root,\r\n" + 
			"        target,\r\n" + 
			"        txy,\r\n" + 
			"        id,\r\n" + 
			"        isSkySpy,\r\n" + 
			"        skSpyAttributes,\r\n" + 
			"        css_menu,\r\n" + 
			"        elemnodes,\r\n" + 
			"        labelXpath,\r\n" + 
			"        textXpath,\r\n" + 
			"        positionxpath,\r\n" + 
			"        precedingsibXpath,\r\n" + 
			"        parentbasedXpath,\r\n" + 
			"        ancestorXpath,\r\n" + 
			"        svgXPath,\r\n" + 
			"        sampleattributexpath,\r\n" + 
			"        followingsibXpath;\r\n" + 
			"\r\n" + 
			"        hello(\"rightClickHandler\");\r\n" + 
			"\r\n" + 
			"        if (event.ctrlKey) {\r\n" + 
			"                                           target = 'target' in event ? event.target : event.srcElement;\r\n" + 
			"                                           if(target.ownerDocument !== document) {\r\n" + 
			"               // node must be inside iframe\r\n" + 
			"                                              if (target.ownerDocument.getElementById('SkMate_PopUp') != null) {\r\n" + 
			"                                               target.ownerDocument.getElementById('SkMate_PopUp').remove();\r\n" + 
			"                                              }          \r\n" + 
			"            }\r\n" + 
			"            isSkySpy = 'target' in event ? event.target : event.srcElement;\r\n" + 
			"            skSpyAttributes = isSkySpy.attributes;\r\n" + 
			"            if (document.getElementById('SkMate_PopUp') != null) {\r\n" + 
			"                document.getElementById('SkMate_PopUp').remove();\r\n" + 
			"\r\n" + 
			"              \r\n" + 
			"            }\r\n" + 
			"            document.SkMate_Page_Recorder = new SkMate_Page_Recorder();\r\n" + 
			"            if (skSpyAttributes.hasOwnProperty('objspy')) {\r\n" + 
			"                return;\r\n" + 
			"            } else {\r\n" + 
			"                document.SkMate_Page_Recorder.createElementForm();\r\n" + 
			"            }\r\n" + 
			"\r\n" + 
			"            if (document.SkMate_Page_Recorder == null) {\r\n" + 
			"                return;\r\n" + 
			"            }\r\n" + 
			"\r\n" + 
			"            if (event == null) {\r\n" + 
			"                event = window.event;\r\n" + 
			"            }\r\n" + 
			"            target = 'target' in event ? event.target : event.srcElement;\r\n" + 
			"            root = document.compatMode === 'CSS1Compat' ? document.documentElement : document.body;\r\n" + 
			"            mxy = [event.clientX + root.scrollLeft, event.clientY + root.scrollTop];\r\n" + 
			"            var objectspy = \"[@objectspyxpath='objectspy']\";\r\n" + 
			"            let removeobjectspy = `//*${objectspy}`;\r\n" + 
			"            let re = evaluateXpath(removeobjectspy)\r\n" + 
			"                if (re.singleNodeValue != null) {\r\n" + 
			"                    re.singleNodeValue.removeAttribute('objectspyxpath');\r\n" + 
			"                }\r\n" + 
			"                target.setAttribute('objectspyxpath', 'objectspy');\r\n" + 
			"            let attributeElement = target.attributes;\r\n" + 
			"            let tagName = target.tagName.toLowerCase();\r\n" + 
			"            let childNextSibling = target.nextSibling;\r\n" + 
			"            let preiousSiblingElement = target.previousElementSibling;\r\n" + 
			"            let followingSiblingElement = target.nextElementSibling;\r\n" + 
			"            if ((tagName === 'input' || tagName === 'textarea')) {\r\n" + 
			"                labelXpath = findLabel(target, tagName);\r\n" + 
			"\r\n" + 
			"            }\r\n" + 
			"            sampleattributexpath = xpathAttributes(attributeElement, tagName, target);\r\n" + 
			"            precedingsibXpath = addPrecedingSibling(followingSiblingElement, tagName)\r\n" + 
			"                followingsibXpath = followsib(preiousSiblingElement, tagName);\r\n" + 
			"            parentbasedXpath = getParent(target, tagName);\r\n" + 
			"            ancestorXpath = getAncestor(target, tagName);\r\n" + 
			"            textXpath = xpathText(target, tagName);\r\n" + 
			"            svgXPath = svgPath(target, tagName);\r\n" + 
			"            path = getPathTo(target);\r\n" + 
			"            txy = getPageXY(target);\r\n" + 
			"            css_selector = getCssSelectorOF(target);\r\n" + 
			"           \r\n" + 
			"            id = getIdBasedXpath(target, tagName);\r\n" + 
			"            \r\n" + 
			"            fullXpath = getXPathWithPosition(target);\r\n" + 
			"            body = document.getElementsByTagName('body')[0];\r\n" + 
			"            if (path.includes(\"/svg[1]\") && !path.includes('html')) {\r\n" + 
			"                var xpath_delimiter = \"/svg[1]\",\r\n" + 
			"                start = 1;\r\n" + 
			"                var xpath_tokens = path.split(xpath_delimiter).slice(0, start);\r\n" + 
			"                var leftwing = xpath_tokens.join(xpath_delimiter);\r\n" + 
			"                finalString = leftwing + \"/*[name()='svg']\";\r\n" + 
			"                path = finalString;\r\n" + 
			"            } else if (path.includes(\"/svg[1]\") && path.includes('html')) {\r\n" + 
			"                path = fullXpath;\r\n" + 
			"            }\r\n" + 
			"            if (typeof svgXPath !== \"undefined\" && svgXPath !== null) {\r\n" + 
			"                path = svgXPath;\r\n" + 
			"            }\r\n" + 
			"            xpath = path;\r\n" + 
			"            menucsspath = getCssSelectorOF(target);\r\n" + 
			"            var text = document.evaluate(fullXpath, document, null, XPathResult.STRING_TYPE, null);\r\n" + 
			"            var contentText = text.stringValue;\r\n" + 
			"            if (path.includes('//undefined[@id=\"undefined\"]')) {\r\n" + 
			"\r\n" + 
			"                xpath = fullXpath;\r\n" + 
			"                menuxpath = fullXpath;\r\n" + 
			"            }\r\n" + 
			"            if (path.length >= 40) {\r\n" + 
			"                menuxpath = splitXpath(path);\r\n" + 
			"            } else {\r\n" + 
			"                menuxpath = xpath;\r\n" + 
			"            }\r\n" + 
			"            if (menucsspath.length >= 40) {\r\n" + 
			"                menucsspath = splitCss(menucsspath);\r\n" + 
			"            } else {\r\n" + 
			"                menucsspath = css_selector;\r\n" + 
			"            }\r\n" + 
			"            /*if (isOverIFrame == true) {\r\n" + 
			"                if (iframeDetails.iframeid == \"\") {\r\n" + 
			"                    iframeLocatorElement = iframeDetails.iframexpath\r\n" + 
			"                        document.getElementById(\"SkMate_PopUp_iframeElement\").innerHTML = iframeLocatorElement;\r\n" + 
			"                } else {\r\n" + 
			"                    iframeLocatorElement = iframeDetails.iframeid\r\n" + 
			"                        document.getElementById(\"SkMate_PopUp_iframeElement\").innerHTML = iframeLocatorElement;\r\n" + 
			"                }\r\n" + 
			"            } else {\r\n" + 
			"                document.getElementById(\"SkMate_PopUp_iframeElement\").innerHTML = \"\";\r\n" + 
			"            }*/\r\n" + 
			"            JsonData = {\r\n" + 
			"                \"Command\": \"GetXPathFromElement\",\r\n" + 
			"                \"Caller\": \"EventListener : mousedown\",\r\n" + 
			"                \"CommandId\": pseudoGuid(),\r\n" + 
			"                \"CssSelector\": css_selector,\r\n" + 
			"                \"ElementId\": id,\r\n" + 
			"                \"XPathValue\": xpath,\r\n" + 
			"                \"iframeElement\": iframeLocatorElement\r\n" + 
			"            };\r\n" + 
			"            createCommand(JsonData);\r\n" + 
			"            var items = {};\r\n" + 
			"\r\n" + 
			"            console.log(\"target-----------------------------\" + target.attributes);\r\n" + 
			"\r\n" + 
			"            if (isOverIFrame == true) {\r\n" + 
			"                data = {\r\n" + 
			"                    id: id,\r\n" + 
			"                    xpath: xpath,\r\n" + 
			"                    isAvailable: 'true',\r\n" + 
			"                    css: css_selector,\r\n" + 
			"                    tagName: target.tagName,\r\n" + 
			"                    xPosition: txy[0],\r\n" + 
			"                    yPosition: txy[1],\r\n" + 
			"                    completeXpath: fullXpath,\r\n" + 
			"                    iframeElement: iframeLocatorElement,\r\n" + 
			"                    preced: precedingsibXpath,\r\n" + 
			"                    follow: followingsibXpath,\r\n" + 
			"                    parent: parentbasedXpath,\r\n" + 
			"                    textContent: textXpath\r\n" + 
			"                };\r\n" + 
			"            } else {\r\n" + 
			"                data = {\r\n" + 
			"                    id: id,\r\n" + 
			"                    xpath: xpath,\r\n" + 
			"                    isAvailable: 'true',\r\n" + 
			"                    css: css_selector,\r\n" + 
			"                    tagName: target.tagName,\r\n" + 
			"                    xPosition: txy[0],\r\n" + 
			"                    yPosition: txy[1],\r\n" + 
			"                    completeXpath: fullXpath,\r\n" + 
			"                    iframeElement: null,\r\n" + 
			"                    preced: precedingsibXpath,\r\n" + 
			"                    follow: followingsibXpath,\r\n" + 
			"                    parent: parentbasedXpath,\r\n" + 
			"                    textContent: textXpath\r\n" + 
			"                };\r\n" + 
			"            }\r\n" + 
			"            var index;\r\n" + 
			"			/* if (target.attributes.length >=1) {\r\n" + 
			"                    target.removeAttribute('objectspyxpath');\r\n" + 
			"                }*/\r\n" + 
			"            for (index = 0; index < target.attributes.length; ++index) {\r\n" + 
			"				if(target.attributes[index].name !=\"objectspyxpath\"){	\r\n" + 
			"                items[target.attributes[index].name] = target.attributes[index].value;\r\n" + 
			"				}\r\n" + 
			"            }\r\n" + 
			"            items['xPosition'] = txy[0];\r\n" + 
			"            items['yPosition'] = txy[1];\r\n" + 
			"            data.attributes = items;\r\n" + 
			"\r\n" + 
			"            console.log(\"rightClickHandler id ---------------------------------\" + JsonData);\r\n" + 
			"\r\n" + 
			"            console.log(\"rightClickHandler id ---------------------------------\" + id);\r\n" + 
			"\r\n" + 
			"            document.SkMate_Page_Recorder.showPos(event, xpath, css_selector, id, iframeLocatorElement);\r\n" + 
			"                                          \r\n" + 
			"            eventPreventingResult = preventEvent(event);\r\n" + 
			"            bye(\"rightClickHandler\");\r\n" + 
			"            return eventPreventingResult;\r\n" + 
			"        }\r\n" + 
			"    };\r\n" + 
			"\r\n" + 
			"    SkMate_Page_Recorder = (function () {\r\n" + 
			"        function SkMate_Page_Recorder() {}\r\n" + 
			"\r\n" + 
			"        SkMate_Page_Recorder.prototype.getMainWinElement = function () {\r\n" + 
			"            return document.getElementById('SkMate_PopUp');\r\n" + 
			"        };\r\n" + 
			"\r\n" + 
			"        SkMate_Page_Recorder.prototype.displayObjForm = function (x, y) {\r\n" + 
			"            var el;\r\n" + 
			"            hello(\"displayObjForm\");\r\n" + 
			"            el = this.getMainWinElement();\r\n" + 
			"            el.style.background = \"white\";\r\n" + 
			"            el.style.position = \"absolute\";\r\n" + 
			"            el.style.left = x + \"px\";\r\n" + 
			"            el.style.top = y + \"px\";\r\n" + 
			"            el.style.display = \"block\";\r\n" + 
			"            el.style.border = \"3px solid black\";\r\n" + 
			"            el.style.padding = \"5px 5px 5px 5px\";\r\n" + 
			"            el.style.zIndex = 2147483647;\r\n" + 
			"            return bye(\"displayObjForm\");\r\n" + 
			"        };\r\n" + 
			"        SkMate_Page_Recorder.prototype.displayObjForm_1 = function (x, y) {\r\n" + 
			"            var el;\r\n" + 
			"            hello(\"displayObjForm\");\r\n" + 
			"            el = this.getMainWinElement();\r\n" + 
			"\r\n" + 
			"            el.style.background = \"white\";\r\n" + 
			"            el.style.position = \"absolute\";\r\n" + 
			"            el.style.left = x + \"px\";\r\n" + 
			"            el.style.top = y + \"px\";\r\n" + 
			"            el.style.display = \"block\";\r\n" + 
			"            el.style.border = \"3px solid black\";\r\n" + 
			"            el.style.padding = \"5px 5px 5px 5px\";\r\n" + 
			"            el.style.zIndex = 2147483647;\r\n" + 
			"\r\n" + 
			"            addStyle(\".highlight { background-color:silver !important}\");\r\n" + 
			"\r\n" + 
			"            addStyle(\"table#ObjTable {             background-color:white;             border-collapse:collapse;           }                       table#ObjTable,table#ObjTable th, table#ObjTable td  {font-family: Verdana, Arial;font-size: 10pt;padding-left:10pt;      padding-right:10pt;border-bottom: 1px solid black;padding:7px}\");\r\n" + 
			"\r\n" + 
			"            addStyle(\"table#ObjbuttonTable {background-color:white;border-collapse:collapse;}table#ObjbuttonTable,table#ObjbuttonTable th, table#ObjbuttonTable td  {font-family: Verdana, Arial;font-size: 10pt;padding-left:10pt;padding-right:10pt;padding:1px}\");\r\n" + 
			"\r\n" + 
			"            addStyle(\"input#SkMate_PopUp_CodeIDText {             display:table-cell;             width:95%;          }\");\r\n" + 
			"\r\n" + 
			"            addStyle(\"span#SkMate_PopUp_CloseButton {              display:table-cell;-moz-border-radius:4px;-webkit-border-radius: 4px;-o-border-radius:4px;border-radius:4px;border:1px solid #ccc;color: white;background-color:#980000;cursor:pointer;font-size:9pt;padding:3px 1px;font-weight: bold;position:absolute;right:0px;top:13px;}\");\r\n" + 
			"\r\n" + 
			"            addStyle(\"div#SkMate_PopUp {             display:block;           }           div#SkMate_PopUp_Element_Name {             display:table;             width: 100%;           }\");\r\n" + 
			"\r\n" + 
			"            return bye(\"displayObjForm\");\r\n" + 
			"        };\r\n" + 
			"\r\n" + 
			"        SkMate_Page_Recorder.prototype.showPos = function (event, xpath, css_selector, id, iframeLocatorElement) {\r\n" + 
			"            var P = document.getElementById(\"subBtn\");\r\n" + 
			"            var closeAction = document.getElementById(\"SkMate_PopUp_CloseButton\");\r\n" + 
			"            closeAction.onclick = function () {\r\n" + 
			"                return document.getElementById('SkMate_PopUp').remove();\r\n" + 
			"            };\r\n" + 
			"            P.onclick = function () {\r\n" + 
			"                newContent();\r\n" + 
			"            };\r\n" + 
			"            function newContent() {\r\n" + 
			"                var JsonData,\r\n" + 
			"                XPathLocatorElement,\r\n" + 
			"                codeIDTextElement,\r\n" + 
			"                htmlIdElement;\r\n" + 
			"                hello(\"addElement\");\r\n" + 
			"                codeIDTextElement = document.getElementById(\"SkMate_PopUp_CodeIDText\");\r\n" + 
			"                htmlIdElement = document.getElementById(\"SkMate_PopUp_ElementId\");\r\n" + 
			"                CssSelectorElement = document.getElementById(\"SkMate_PopUp_CssSelector\");\r\n" + 
			"                XPathLocatorElement = document.getElementById(\"SkMate_PopUp_XPathLocator\");\r\n" + 
			"                IframeLocatorElement = document.getElementById(\"SkMate_PopUp_iframeElement\");\r\n" + 
			"                data['nodename'] = codeIDTextElement.value;\r\n" + 
			"                var tabName = document.title;\r\n" + 
			"                localStorage.setItem('tabName', tabName);\r\n" + 
			"                localStorage.setItem('data', JSON.stringify(data));\r\n" + 
			"                localStorage.setItem('isAvailable', 'true');\r\n" + 
			"                localStorage.setItem('isAvailable', 'true');\r\n" + 
			"                if (isOverIFrame == true) {\r\n" + 
			"                    JsonData = {\r\n" + 
			"                        \"Command\": \"AddElement\",\r\n" + 
			"                        \"Caller\": \"addElement\",\r\n" + 
			"                        \"ElementId\": (htmlIdElement.hasChildNodes()) ? htmlIdElement.firstChild.nodeValue : \"\",\r\n" + 
			"                        \"ElementCssSelector\": CssSelectorElement.firstChild.nodeValue,\r\n" + 
			"                        \"ElementXPath\": XPathLocatorElement.firstChild.nodeValue,\r\n" + 
			"                        \"iframeElement\": IframeLocatorElement.firstChild.nodeValue\r\n" + 
			"                    };\r\n" + 
			"                    createCommand(JsonData);\r\n" + 
			"                } else {\r\n" + 
			"\r\n" + 
			"                    JsonData = {\r\n" + 
			"                        \"Command\": \"AddElement\",\r\n" + 
			"                        \"Caller\": \"addElement\",\r\n" + 
			"                        \"ElementId\": (htmlIdElement.hasChildNodes()) ? htmlIdElement.firstChild.nodeValue : \"\",\r\n" + 
			"                        \"ElementCssSelector\": CssSelectorElement.firstChild.nodeValue,\r\n" + 
			"                        \"ElementXPath\": XPathLocatorElement.firstChild.nodeValue,\r\n" + 
			"\r\n" + 
			"                    };\r\n" + 
			"                    createCommand(JsonData);\r\n" + 
			"\r\n" + 
			"                }\r\n" + 
			"                return bye(\"addElement >\");\r\n" + 
			"            }\r\n" + 
			"            var x,\r\n" + 
			"            y;\r\n" + 
			"            hello(\"showPos\");\r\n" + 
			"\r\n" + 
			"            console.log(\"showPOS id ---------------------------------\" + id);\r\n" + 
			"            var width = document.body.offsetWidth ? document.body.offsetWidth : html.offsetWidth;\r\n" + 
			"            if (window.event) {\r\n" + 
			"                x = window.event.clientX + document.documentElement.scrollLeft + document.body.scrollLeft;\r\n" + 
			"                y = window.event.clientY + document.documentElement.scrollTop + document.body.scrollTop;\r\n" + 
			"            } else {\r\n" + 
			"                x = event.clientX + window.scrollX;\r\n" + 
			"                y = event.clientY + window.scrollY;\r\n" + 
			"            }\r\n" + 
			"            x -= 2;\r\n" + 
			"            y -= 2;\r\n" + 
			"            var z = x;\r\n" + 
			"            y = y + 15;\r\n" + 
			"            var a =x+20;\r\n" + 
			"            if(a>=width){\r\n" + 
			"              x=x-18;\r\n" + 
			"            }\r\n" + 
			"            if (x <= width) {\r\n" + 
			"                if (x <= 200) {\r\n" + 
			"                    x = x ;\r\n" + 
			"                   \r\n" + 
			"                } else {\r\n" + 
			"                    x =x - 300;\r\n" + 
			"                } \r\n" + 
			"                if (x <= 0) {\r\n" + 
			"                    x = x + 100;\r\n" + 
			"                }\r\n" + 
			"            }\r\n" + 
			"            var height = document.body.offsetHeight ? document.body.offsetHeight : html.offsetHeight;\r\n" + 
			"            if (y <= height && y >= height - 300) {\r\n" + 
			"                y = y - 120;\r\n" + 
			"                if (y <= 0) {\r\n" + 
			"                    y = y + 100;\r\n" + 
			"                }\r\n" + 
			"            }\r\n" + 
			"            /*if (xpath.length <= 24 && css_selector.length <= 24) {\r\n" + 
			"                this.displayObjForm_1(x, y);\r\n" + 
			"            } else {\r\n" + 
			"                this.displayObjForm(x, y);\r\n" + 
			"            }*/\r\n" + 
			"          this.displayObjForm(x, y);\r\n" + 
			"            //document.getElementById(\"SkMate_PopUp_ElementId\").innerHTML = id;\r\n" + 
			"            say(x + \";\" + y);\r\n" + 
			"            return bye(\"showPos\");\r\n" + 
			"        };\r\n" + 
			"        SkMate_Page_Recorder.prototype.closeForm = function () {\r\n" + 
			"            return document.getElementById('SkMate_PopUp').style.display = 'none';\r\n" + 
			"        };\r\n" + 
			"\r\n" + 
			"       \r\n" + 
			"\r\n" + 
			"        SkMate_Page_Recorder.prototype.createElementForm = function () {\r\n" + 
			"\r\n" + 
			"            var closeClickHandler,\r\n" + 
			"            element;\r\n" + 
			"            hello(\"createElementForm\");\r\n" + 
			"            element = document.createElement(\"div\");\r\n" + 
			"            element.id = 'SkMate_PopUp';\r\n" + 
			"            if (document.body != null) {\r\n" + 
			"                document.body.appendChild(element);\r\n" + 
			"            } else {\r\n" + 
			"                say(\"createElementForm Failed to inject element SkMate_PopUp. The document has no body\");\r\n" + 
			"            }\r\n" + 
			"            closeClickHandler = \"\";\r\n" + 
			"            element.innerHTML = '\\\r\n" + 
			"                <table id=\"ObjTable\" objspy=\"sk_mate\">\\\r\n" + 
			"                <tr>\\\r\n" + 
			"                <td>Object Name</td>\\\r\n" + 
			"                <td>\\\r\n" + 
			"                <div id=\"SkMate_PopUp_Element_Name\" objspy=\"sk_mate\">\\\r\n" + 
			"                <span id=\"SkMate_PopUp_CodeID\" objspy=\"sk_mate\">\\\r\n" + 
			"                <input type=\"text\" id=\"SkMate_PopUp_CodeIDText\" objspy=\"sk_mate\">\\\r\n" + 
			"                </span>\\\r\n" + 
			"                <span id=\"SkMate_PopUp_CodeClose\" objspy=\"sk_mate\"></span>\\\r\n" + 
			"                <span id=\"SkMate_PopUp_CloseButton\" objspy=\"sk_mate\" \">X</span>\\\r\n" + 
			"                </div>\\\r\n" + 
			"                </td>\\\r\n" + 
			"                </tr>\\\r\n" + 
			"                </table>\\\r\n" + 
			"                <table id=\"ObjbuttonTable\" objspy=\"sk_mate\">\\\r\n" + 
			"                <tr>\\\r\n" + 
			"                <td> <input type=\"button\" id=\"subBtn\"s value=\"Add element\" objspy=\"sk_mate\">\\\r\n" + 
			"                <td><label\\\r\n" + 
			"                id=\"SKSpy_PopUp_MsgBox\" objspy=\"sk_mate\"></label></td></tr></table>\\\r\n" + 
			"                ';\r\n" + 
			"\r\n" + 
			"            return bye(\"createElementForm\");\r\n" + 
			"        };\r\n" + 
			"\r\n" + 
			"        return SkMate_Page_Recorder;\r\n" + 
			"\r\n" + 
			"    })();\r\n" + 
			"   \r\n" + 
			"    addStyle(\".highlight { background-color:silver !important}\");\r\n" + 
			"\r\n" + 
			"    addStyle(\"table#ObjTable {             background-color:white;             border-collapse:collapse;           }                       table#ObjTable,table#ObjTable th, table#ObjTable td  {font-family: Verdana, Arial;font-size: 10pt;padding-left:10pt;      padding-right:10pt;border-bottom: 1px solid black;padding:2px}\");\r\n" + 
			"\r\n" + 
			"    addStyle(\"table#ObjbuttonTable {background-color:white;border-collapse:collapse;}table#ObjbuttonTable,table#ObjbuttonTable th, table#ObjbuttonTable td  {font-family: Verdana, Arial;font-size: 10pt;padding-left:10pt;padding-right:10pt;padding:2px}\");\r\n" + 
			"\r\n" + 
			"    addStyle(\"input#SkMate_PopUp_CodeIDText {             display:table-cell;             width:95%;          }\");\r\n" + 
			"\r\n" + 
			"    addStyle(\"span#SkMate_PopUp_CloseButton {              display:table-cell;-moz-border-radius:4px;-webkit-border-radius: 4px;-o-border-radius:4px;border-radius:4px;border:1px solid #ccc;color: white;background-color:#980000;cursor:pointer;font-size:9pt;padding:0px 1px;font-weight: bold;position:absolute;right:3px;top:8px;}\");\r\n" + 
			"\r\n" + 
			"    addStyle(\"div#SkMate_PopUp {             display:block;           }           div#SkMate_PopUp_Element_Name {             display:table;             width: 100%;           }\");\r\n" + 
			"\r\n" + 
			"    \r\n" + 
			"\r\n" + 
			"    if (document.body != null) {\r\n" + 
			"        if (document.body.addEventListener) {\r\n" + 
			"            if (document.body.tagName === \"IFRAME\") {\r\n" + 
			"                var ids = iframeElement;\r\n" + 
			"                ids.contentWindow.document.body.addEventListener('mouseover', handler, false);\r\n" + 
			"                ids.contentWindow.document.addEventListener('contextmenu', rightClickHandler, false);\r\n" + 
			"            }\r\n" + 
			"            document.body.addEventListener('mouseover', handler, false);\r\n" + 
			"            document.addEventListener('contextmenu', rightClickHandler, false);\r\n" + 
			"        } else if (document.body.attachEvent) {\r\n" + 
			"            document.body.attachEvent('mouseover', function (e) {\r\n" + 
			"                return handler(e || window.event);\r\n" + 
			"            });\r\n" + 
			"            document.body.attachEvent('oncontextmenu', function (e) {\r\n" + 
			"                return rightClickHandler(e || window.event);\r\n" + 
			"            });\r\n" + 
			"        } else {\r\n" + 
			"            document.body.onmouseover = handler;\r\n" + 
			"            document.body.onmouseover = rightClickHandler;\r\n" + 
			"        }\r\n" + 
			"    } else {\r\n" + 
			"        say(\"Document has no body tag... Injecting empty\");\r\n" + 
			"        document.SkMate_Page_Recorder = \"STUB. Document has no body tag :(\";\r\n" + 
			"    }\r\n" + 
			"\r\n" + 
			"}).call(this);\r\n" + 
			"\r\n" + 
			"\r\n" + 
			" \r\n" + 
			"\r\n" + 
			"\r\n" + 
			"";

	
	String availCheckScript = "try { var isAvailable=window.localStorage.getItem('isAvailable');\r\n"
			+ "return isAvailable; } catch(exception) {}";
	String healisAvailableScript = "try { var healisAvailableScript=window.localStorage.getItem('healisAvailableScript');\r\n"
			+ "return healisAvailableScript; } catch(exception) {}";
	String newTabOpened = "try { var newTabOpened=window.localStorage.getItem('newTabOpened');\r\n"
			+ "return newTabOpened; } catch(exception) {}";
	String tabName = "try { var tabName=window.localStorage.getItem('tabName');\r\n"
			+ "return tabName; } catch(exception) {}";
	String LinkNodeClicked = "try { var LinkNodeClicked=window.localStorage.getItem('LinkNodeClicked');\r\n"
			+ "return LinkNodeClicked; } catch(exception) {}";
	String refreshScript = "try { var isRefreshed=window.localStorage.getItem('isRefreshed');\r\n"
			+ "console.log('isRefreshed value:' + isRefreshed);return isRefreshed; } catch(exception) {console.log('Exception occurs while refreshing..');}";
	public String getScript() {
		return script;
	}

	public String getXpathExtractorScript() {
		return xpathExtractorScript;
	}

	public String getIdExtractorScript() {
		return idExtractorScript;
	}

	public String getCssExtractorScript() {
		return cssExtractorScript;
	}

	public String getBlinkScript() {
		return blinkScript;
	}

	public String getAvailCheckScript() {
		return availCheckScript;
	}
	
	public String getnewTabOpened() {
		return newTabOpened;
	}
	
	public String getTabName() {
		return tabName;
	}
	public String getLinkNodeClicked() {
		return LinkNodeClicked;
	}
	
	
	public String getHealIsAvailable() {
		return healisAvailableScript;
	}

	public String getRefreshScript() {
		return refreshScript;
	}

	public void setRefreshScript(String refreshScript) {
		this.refreshScript = refreshScript;
	}

	public String getDataExtractorScript() {
		return dataExtractorScript;
	}

	public void setDataExtractorScript(String dataExtractorScript) {
		this.dataExtractorScript = dataExtractorScript;
	}

	public String getPopupInfo() {
		return popupInfo;
	}

	public void setPopupInfo(String popupInfo) {
		this.popupInfo = popupInfo;
	}
	
	public static String sentHealElement(String healElem) {
		String elem="localStorage.setItem('sampleXpath', '"+healElem+"');";
		return elem;
	}
	public static String sentNodeName(String nodeName) {
		String elem="localStorage.setItem('nodeName', '"+nodeName+"');";
		return elem;
	}
	public String tabThreeAlert() {
		String alertMsg="var msg=\"Current tab would be closed if you wish to leave.\";\n" + 
				"var newLine = \"\\r\\n\";\n" + 
				"msg+=newLine\n" + 
				"msg += \"Do you want to continue?\";\n" + 
				"if (confirm(msg)) {\n" + 
				"    window.close();\n" + 
				"  }";
		return alertMsg;
	}

	public String getPopupMessageScript(String message, String color) {
		String msg ="var msgElement = document.getElementById(\"SKSpy_PopUp_MsgBox\");" +  
				"msgElement.innerHTML='"+message+"'\r\n" +
				 "msgElement.style.color='"+color+"';" +
				 "setTimeout(function(){ if(msgElement.innerHTML.indexOf( 'The object addition was successful') >= 0) {msgElement.innerHTML=''; document.SkMate_Page_Recorder.closeForm();} else {msgElement.innerHTML='';}}, 2000);"
				 
				 ;
		return msg;
	}

	public String getClosePopup() {
		return closePopup;
	}

	public void setClosePopup(String closePopup) {
		this.closePopup = closePopup;
	}

	

	

	
	
}
