/*
Copyright 2011 Software Freedom Conservatory.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package org.usapi;

import com.thoughtworks.selenium.Selenium;

import org.openqa.selenium.WebDriver;
import org.springframework.context.ApplicationContext;

/**
 * 
 * <h4>Entry point to Selenium abstraction layer</h4>
 * <p>
 * 
 *  Spring configuration file contains mapping of UI display string (e.g. 'Login') to
 *  Selenium locator for a given UI element/DOM node, grouped by type.
 *  
 *  For list of supported element types, see Spring configuration file.
 *  
 *  A test case accesses UI elements using the UI display string through an instance
 *  of this class.  Example: app.button("Login");
 *  
 *  The resulting IDOMNode instance then supports methods applicable to this particular
 *  type of element: app.button("Login").click();
 *  
 *  Note that all selenium-native API calls should use the abstraction provided by 
 *  this class as well.  This will allow to have a given locator defined in exactly
 *  one location.  Example: isElementPresent(app.button("Login").getLocator());
 *  
 *  Default is application-elements.xml in root of this src.
 * 
 */
public class BaseApplication implements IApplication {
	
    protected DOMNodeFactory domNodeFactory = null;

    public static WebDriver webDriver = null;
    
    // do not allow instantiation without handle to webDriver/selenium instances
	private BaseApplication() {}
	
	public BaseApplication(WebDriver wd, ApplicationContext appCtx)
	{
		webDriver = wd;
		
		domNodeFactory = DOMNodeFactory.getInstance(); 
        domNodeFactory.initialize(appCtx, webDriver );
	}

	// *****************************  IApplication Impl ****************************** //
	
	/**
	 * Return the instance of web driver used by this instance of BaseApplication.
	 */
	public WebDriver getWebDriver()
	{
		return webDriver;
	}
	
	/**
	 * Wrap org.openqa.selenium.Alert for consistency, and to not require 
	 * explicit import of selenium .jar in downstream projects
	 */
	public Alert alert()
	{
		return new Alert( webDriver );
	}
	
	
	/**
	 * Get DOM node of type anchor
	 * @param nodeName The UI display string on the anchor &lt;a&gt; 
	 */
	public IDOMNode link(String nodeName) throws DOMNodeNotFoundException
	{ 
		return domNodeFactory.getDOMNode(NodeType.LINK, nodeName);	
	}

	/**
	 * Get DOM node of type button
	 * @param nodeName The UI display string on the button &lt;input type="submit"&gt; 
	 */
	public IDOMNode button(String nodeName) throws DOMNodeNotFoundException
	{
		return domNodeFactory.getDOMNode(NodeType.BUTTON, nodeName);
	}
	
	/**
	 * Get DOM node of type category
	 * @param nodeName the UI display string in the category
	 */
	public IDOMNode category(String nodeName) throws DOMNodeNotFoundException
	{
		return domNodeFactory.getDOMNode(NodeType.CATEGORY, nodeName);
	}

	/**
	 * Get DOM node of type checkbox
	 * @param nodeName The UI display string on the checkbox &lt;input type="checkbox"&gt; 
	 */
	public IDOMNode checkbox(String nodeName) throws DOMNodeNotFoundException{
		return domNodeFactory.getDOMNode(NodeType.CHECKBOX, nodeName);
	}
	
	/**
	 * Get (input) DOM nodes of type hidden 
	 * @param nodeName The name of the node as defined in application-elements.xml
	 */
	public IDOMNode hiddenfield( String nodeName ) throws DOMNodeNotFoundException{
		return domNodeFactory.getDOMNode(NodeType.HIDDENFIELD, nodeName);
	}

	/**
	 * Get DOM node of type image
	 * @param nodeName The name by which the image is referred to. 
	 */
	public IDOMNode image(String nodeName) throws DOMNodeNotFoundException{
		return domNodeFactory.getDOMNode(NodeType.IMAGE, nodeName);
	}

	/**
	 * Get DOM node of type menu
	 * @param nodeName The UI display string on the menu 
	 */
	public IDOMNode menu(String nodeName) throws DOMNodeNotFoundException{
		return domNodeFactory.getDOMNode(NodeType.MENU, nodeName);
	}

	/**
	 * Get DOM node of type menuitem
	 * @param nodeName The UI display string on the menu item 
	 */
	public IDOMNode menuitem(String nodeName) throws DOMNodeNotFoundException{
		return domNodeFactory.getDOMNode(NodeType.MENUITEM, nodeName);
	}

	/**
	 * Get DOM node of type menuitem
	 * @param nodeName The UI display string on the menu item 
	 */
	public IDOMNode modaldialog(String nodeName) throws DOMNodeNotFoundException{
		return domNodeFactory.getDOMNode(NodeType.MODALDIALOG, nodeName);
	}
	
	/**
	 * Get DOM node of type radiobutton
	 * @param nodeName The UI display string on the radio button &lt;input type="radiobutton&gt; 
	 */
	public IDOMNode radiobutton(String nodeName) throws DOMNodeNotFoundException
	{
		return domNodeFactory.getDOMNode(NodeType.RADIOBUTTON, nodeName);
	}

	/**
	 * Get DOM node of type select
	 * @param nodeName The UI display string on the select box &lt;input type="select"&gt; 
	 */
	public IDOMNode selectbox(String nodeName) throws DOMNodeNotFoundException
	{
		return domNodeFactory.getDOMNode(NodeType.SELECT, nodeName);
	}

	/**
	 * Get DOM node of type tablecell
	 * @param nodeName A UI display string uniquely identifying the table cell visually 
	 */
	public IDOMNode tablecell(String nodeName) throws DOMNodeNotFoundException
	{
		return domNodeFactory.getDOMNode(NodeType.TABLECELL, nodeName);
	}

	/**
	 * Get DOM node of type tablerow
	 * @param nodeName The UI display string uniquely identifying the table row visually
	 */
	public IDOMNode tablerow(String nodeName) throws DOMNodeNotFoundException
	{
		return domNodeFactory.getDOMNode(NodeType.TABLEROW, nodeName);
	}
	
	/**
	 * Get DOM node of type tab
	 * @param nodeName The UI display string on the tab &lt;a&gt; 
	 */
	public IDOMNode tab(String nodeName) throws DOMNodeNotFoundException
	{
		return domNodeFactory.getDOMNode(NodeType.TAB, nodeName);
	}
	
	/**
	 * Get DOM node of type text
	 * @param txt The text that is displayed (chdata or other text
	 * not belonging to/associated with a control node) 
	 */
	public IDOMNode text(String txt) throws DOMNodeNotFoundException
	{
		return domNodeFactory.getDOMNode(NodeType.TEXT, txt);
	}

	/**
	 * Get DOM node of type textfield
	 * @param nodeName The UI display string on the textfield &lt;input type="textfield"&gt; 
	 */
	public IDOMNode textfield(String nodeName) throws DOMNodeNotFoundException
	{
		return domNodeFactory.getDOMNode(NodeType.TEXTFIELD, nodeName);
	}
	

	/**
	 * Get DOM node of type textfield
	 * @param nodeName The UI display string on the textfield &lt;input type="textfield"&gt; 
	 */
	public IDOMNode treenode(String nodeName) throws DOMNodeNotFoundException
	{
		return domNodeFactory.getDOMNode(NodeType.TREENODE, nodeName);
	}
	
	/**
	 * Get window.  While technically not a DOM node, representing it as such
	 * to maintain syntactic integrity.
	 * @param windowName Caption of the window 
	 */
	public IDOMNode window(String windowName) throws DOMNodeNotFoundException
	{
		return domNodeFactory.getDOMNode(NodeType.WINDOW, windowName);
	}
	
	/**
	 * Get an instance of the specified type, for the specified locator.  The returned
	 * IDOMNode is identical to what would be returned if the locator was defined in 
	 * application elements XML.  Intended use is to provide a type-safe mechanism for
	 * nodes identified by locators constructed dynamically in test code, e.g. window
	 * titles, randomly generated file names, etc. 
	 * @param nodeType Type of node to create.  See {@link org.usapi.NodeType}.  Also,
	 * this class (BaseApplication) provides all node types as public members (TYPE_BUTTON, etc).
	 * @param locator Locator to identify the node in the DOM. 
     * @return node of specified type, with specified locator
	 */
	public IDOMNode element( NodeType nodeType, String locator )
	{
	    return domNodeFactory.getElementNode( nodeType, locator );
	}

}