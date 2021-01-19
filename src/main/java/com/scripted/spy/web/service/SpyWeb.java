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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumnModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.gson.Gson;
import com.scripted.spy.web.listeners.ChangeListener;
import com.scripted.spy.web.model.Attribute;
import com.scripted.spy.web.model.Project;
import com.scripted.spy.web.test.About;
import com.scripted.spy.web.test.RadioButtonEditor;
import com.scripted.spy.web.test.RadioButtonRenderer;
import com.scripted.spy.web.ui.AttributeTableModel;
import com.scripted.spy.web.ui.BrowserImageRenderer;
import com.scripted.spy.web.ui.DeleteOptions;
import com.scripted.spy.web.ui.IconRepo;
import com.scripted.spy.web.ui.ObjectSpyClient;
import com.scripted.spy.web.ui.TreeNodeRenderer;
import com.scripted.spy.web.util.AppConstants;
import com.scripted.spy.web.util.Browsers;
import com.scripted.spy.web.util.ColorCode;
import com.scripted.spy.web.util.ElementUtil;
import com.scripted.spy.web.util.LocatorSelectionListener;
import com.scripted.spy.web.util.LocatorType;
import com.scripted.spy.web.util.MessageTimer;
import com.scripted.spy.web.util.StringUtil;
import com.scripted.spy.web.util.ValidatorUtil;

public class SpyWeb extends JFrame implements IconRepo {
	private static String OS = System.getProperty("os.name").toLowerCase();
	private static final Logger logger = LogManager.getLogger(SpyWeb.class);
	private static final long serialVersionUID = 1L;
	private static final String HOMEDIR = System.getProperty("user.home");
	private static Map<String, ImageIcon> nodeIcons;
	private static List<String> primeLocators;
	private static final String SUCCESS = "green";
	private static final String ERROR = "red";
	public static boolean flagBrowser = false;
	private static JLabel errorBox;
	private JPanel contentPane;
	private JTextField txtURL;
	private JTextField txtPageName;
	private JTextField txtObjectName;
	public static String tagname;
	private JTable tblAttributes;
	private WebDriver driver1;
	private JavascriptExecutor js;
	private static Map<String, Attribute> objAttrMap = new HashMap<>();
	private Project project;
	private JLabel lblInfo;
	private JLabel lblGenerateInfo;
	private JTree trPageExplorer;
	public static Map<String, Map<String, Attribute>> pagesMap;
	Map<String, List<Object[]>> attributeMap;
	JComboBox<String> cmbPageName = new JComboBox<>();
	private String attributeKey;
	private String attributeValue;
	private JLabel lblFoundNElement;
	private JSONReader jsonReader = new JSONReader();
	private ElementUtil elementUtil = new ElementUtil();
	private StringUtil stringUtil = new StringUtil();
	private WebElementService webElementService = new WebElementService();
	private ScriptingService scriptingService = new ScriptingService();
	private ValidatorUtil validatorUtil = new ValidatorUtil();
	private static final String FONTNAME = "Calibri";
	private static final int FONTSIZE = 16;
	private static final int TITLEFONTSIZE = 15;
	private static final int OBJECTLOCATORSFONT = 14;
	private static final int BUTTONFONTSIZE = 13;
	private static final String BUTTONFONTNAME = "Segoe UI Semibold";
	private XPathExpressionGenerator xpathExpressionGenerator = new XPathExpressionGenerator();
	private RelativeElementLocators relativeElementLocator = new RelativeElementLocators();
	private Map<String, String> pageAliases = new HashMap<>();
	private String title;
	List<TableCellEditor> editors = new ArrayList<TableCellEditor>(3);
	private final ButtonGroup locatorGroup = new ButtonGroup();
	private JRadioButton rdBtnLocators;
	private JRadioButton rdBtnXPath;
	private JRadioButton rdBtnAttr;
	JScrollPane sPaneAttributes = new JScrollPane();
	private JLabel lblBrowserInfo;
	private Map<String, WebDriver> driverMap = new HashMap<>();
	private Map<String, Set<String>> sitePagesMap = new HashMap<>();
	private Set<String> pagesSet;
	private JComboBox<String> cmbBrowsers;
	JRadioButton selectedKey = null;
	private boolean isNewWindow = true;
	private boolean flagExit = false;
	private boolean flagNewPage = false;
	private ImportService importService;
	private AttributeTableModel model;
	private Map<String, Boolean> savedPages = new HashMap<>();
	private int count;
	public String objectName;
	public String fXpath;
	public String iFrameXpath;
	private JButton btnHighlight;
	public boolean flagBrowserVersion=false;

	public static boolean isFlagBrowser() {
		return flagBrowser;
	}

	public static void setFlagBrowser(boolean flagBrowser) {
		SpyWeb.flagBrowser = flagBrowser;
	}

	public WebDriver getDriver1() {
		return driver1;
	}

	public void setDriver1(WebDriver driver1) {
		this.driver1 = driver1;
	}

	public JavascriptExecutor getJs() {
		return js;
	}

	public void setJs(JavascriptExecutor js) {
		this.js = js;
	}

	public static Map<String, Attribute> getObjAttrMap() {
		return objAttrMap;
	}

	public void setObjAttrMap(Map<String, Attribute> objAttrMap) {
		this.objAttrMap = objAttrMap;
	}

	public JTree getTrPageExplorer() {
		return trPageExplorer;
	}

	public void setTrPageExplorer(JTree trPageExplorer) {
		this.trPageExplorer = trPageExplorer;
	}

	public JTextField getTxtURL() {
		return txtURL;
	}

	public void setTxtURL(JTextField txtURL) {
		this.txtURL = txtURL;
	}

	public static Map<String, Map<String, Attribute>> getPagesMap() {
		return pagesMap;
	}

	public void setPagesMap(Map<String, Map<String, Attribute>> pagesMap) {
		this.pagesMap = pagesMap;
	}

	public void invokeSpyWeb(Project project, String importFilename) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SpyWeb frame = new SpyWeb(project, isNewWindow);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void invokeSpyWeb(boolean isNewWindow) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SpyWeb frame = new SpyWeb(project, isNewWindow);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SpyWeb() {
		BasicConfigurator.configure();
		setBackground(Color.WHITE);
		drawElements();
		icon();
	}

	public SpyWeb(Project project, boolean isNewWindow) throws Exception {
		this.project = project;
		this.isNewWindow = isNewWindow;
		System.out.println("Is New: " + isNewWindow);
		flagNewPage=true;
		if (pagesMap != null)
			pagesMap.clear();
		importService = new ImportService();
		drawElements();
		createNewPage(trPageExplorer);
		if (!this.isNewWindow) {
			trPageExplorer.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("JTree")));
			this.updateTree(this.trPageExplorer, importService.parseToPagesData(project.getLocation(),
					project.getProjectName(), lblInfo, project.getImportFileLocation()));
		}
	}

	private void drawElements() {
		if(!isNewWindow) {
			setTitle("UST iSkript - Web - " + this.project.getLocation().substring(0,this.project.getLocation().length()-1));
		}else {
		setTitle("UST iSkript - Web - " + this.project.getLocation());
		}
		setIconImage(Toolkit.getDefaultToolkit().getImage(SpyWeb.class.getResource(AppConstants.LOGOPATH)));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 872, 540);
		setResizable(false);
		// setMaximizedBounds(new Rectangle(100, 100, 872, 540));
		setLocationRelativeTo(null);
		
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				int event = windowEvent.getID();
				int dialogButton = JOptionPane.showConfirmDialog(null, "Do you want to exit the UST iSkript?", "Confirm Exit",
						JOptionPane.YES_NO_OPTION);
				if (dialogButton == JOptionPane.YES_OPTION) {
					int confirmOption = unsavedChangesValidation();
					if (null != driver1) {
						driver1.quit();
						killDrivers();
					}
					if(flagBrowserVersion==true)
					{
						killDrivers();
					}
					File file=new File(project.getLocation()+"\\objectRepo\\"+project.getProjectName()+".sky");
					System.out.println("File Path :"+file.toPath().toString());
					if(!Files.exists(file.toPath()))
					{
						File projectPath=new File(project.getLocation());
						System.out.println("Project Path :"+projectPath.toPath().toString());
						String[]entries = projectPath.list();
						for(String s: entries){
						    File currentFile = new File(projectPath.getPath(),s);
						    currentFile.delete();
						}
						projectPath.delete();
					}
					dispose();
					System.exit(0);
				}
				System.out.println("Window is closing");
			}

		});

		addMenus();
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.decode("#F2FBFF"));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JButton btnCaptureObject = generateInputPanel();
		generateObjectViewPanel(btnCaptureObject);

	}

	private void generateObjectViewPanel(JButton btnCaptureObject) {
		JPanel objectViewPanel = new JPanel();
		objectViewPanel.setBounds(45, 90, 774, 358);
		objectViewPanel.setBackground(Color.decode("#FFFFFF"));
		objectViewPanel.setLayout(null);
		contentPane.add(objectViewPanel);

		JLabel lblCapturedObjects = new JLabel("Captured Pages & Objects");
		lblCapturedObjects.setVerticalAlignment(SwingConstants.TOP);
		lblCapturedObjects.setBounds(21, 22, 250, 20);
		lblCapturedObjects.setFont(new Font(FONTNAME, Font.BOLD, FONTSIZE));
		lblCapturedObjects.setForeground(Color.decode(ColorCode.TEXT.getCode()));
		objectViewPanel.add(lblCapturedObjects);

		JLabel lblPageName = new JLabel("Page Name");
		lblPageName.setBounds(23, 56, 96, 20);
		lblPageName.setVerticalAlignment(SwingConstants.TOP);
		lblPageName.setFont(new Font(FONTNAME, Font.PLAIN, FONTSIZE));
		lblPageName.setForeground(Color.decode(ColorCode.TEXT.getCode()));
		objectViewPanel.add(lblPageName);

		txtPageName = new JTextField(0);
		txtPageName.setColumns(10);
		txtPageName.setBounds(100, 55, 250, 20);
		txtPageName.setBackground(Color.decode(ColorCode.INPUTBACKGROUND.getCode()));
		txtPageName.setBorder(null);
		txtPageName.setFont(new Font(FONTNAME, Font.PLAIN, FONTSIZE));
		txtPageName.setForeground(Color.decode(ColorCode.TEXT.getCode()));
		txtPageName.setMaximumSize(new Dimension(10, 20));
		txtPageName.setEditable(false);
		objectViewPanel.add(txtPageName);

		JLabel iconAddpage = new JLabel("");
		iconAddpage.setBounds(355, 57, 17, 17);
		iconAddpage.setIcon(IconRepo.ADDPAGE);
		iconAddpage.setToolTipText("Add Page");
		objectViewPanel.add(iconAddpage);
		iconAddpage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				generateNewPage(trPageExplorer);
			}

		});

		JLabel lblObjectName = new JLabel("Object Name");
		lblObjectName.setBounds(386, 55, 90, 20);
		lblObjectName.setFont(new Font(FONTNAME, Font.PLAIN, FONTSIZE));
		lblObjectName.setForeground(Color.decode(ColorCode.TEXT.getCode()));
		objectViewPanel.add(lblObjectName);

		txtObjectName = new JTextField();
		txtObjectName.setBounds(475, 55, 268, 20);
		txtObjectName.setBackground(Color.decode(ColorCode.INPUTBACKGROUND.getCode()));
		txtObjectName.setColumns(10);
		txtObjectName.setFont(new Font(FONTNAME, Font.PLAIN, FONTSIZE));
		txtObjectName.setFont(new Font(FONTNAME, Font.PLAIN, 12));
		txtObjectName.setBorder(null);
		txtObjectName.setEditable(false);
		objectViewPanel.add(txtObjectName);

		JScrollPane globalTabPaneAttributes = new JScrollPane();
		globalTabPaneAttributes.setBounds(386, 94, 361, 195);
		globalTabPaneAttributes.setBackground(Color.decode(ColorCode.SCROLLPANEBG.getCode()));
		globalTabPaneAttributes.setOpaque(false);

		// Radio Button(Start)
		JPanel selectorMethodsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		selectorMethodsPanel.setBounds(386, 84, 360, 32);
		selectorMethodsPanel.setBackground(Color.decode("#F1F1F1"));
		selectorMethodsPanel.setBorder(new LineBorder(Color.GRAY));

		JLabel lblSelection = new JLabel("Object Details");
		lblSelection.setFont(new Font(FONTNAME, Font.PLAIN, OBJECTLOCATORSFONT));
		lblSelection.setForeground(Color.decode(ColorCode.TEXT.getCode()));
		selectorMethodsPanel.add(lblSelection);

		rdBtnLocators = new JRadioButton("Locators");
		rdBtnLocators.setFont(new Font(FONTNAME, Font.PLAIN, OBJECTLOCATORSFONT));
		rdBtnLocators.setBounds(50, 0, 20, 5);
		rdBtnLocators.setSelected(true);
		rdBtnLocators.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (trPageExplorer.getSelectionCount() == AppConstants.NOSELECTION)
					return;
				addAttributesData(null, trPageExplorer, sPaneAttributes, driver1);

			}
		});
		locatorGroup.add(rdBtnLocators);

		rdBtnXPath = new JRadioButton("XPaths");
		rdBtnXPath.setFont(new Font(FONTNAME, Font.PLAIN, OBJECTLOCATORSFONT));
		rdBtnXPath.setBounds(350, 0, 20, 5);
		rdBtnXPath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (trPageExplorer.getSelectionCount() == AppConstants.NOSELECTION)
					return;
				addAttributesData(null, trPageExplorer, sPaneAttributes, driver1);
			}
		});
		locatorGroup.add(rdBtnXPath);

		rdBtnAttr = new JRadioButton("Attributes");
		rdBtnAttr.setFont(new Font(FONTNAME, Font.PLAIN, OBJECTLOCATORSFONT));
		rdBtnAttr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (trPageExplorer.getSelectionCount() == AppConstants.NOSELECTION)
					return;
				addAttributesData(null, trPageExplorer, sPaneAttributes, driver1);
			}
		});
		locatorGroup.add(rdBtnAttr);

		selectorMethodsPanel.add(rdBtnLocators);
		selectorMethodsPanel.add(rdBtnXPath);
		selectorMethodsPanel.add(rdBtnAttr);
		objectViewPanel.add(selectorMethodsPanel);
		sPaneAttributes.setBounds(386, 114, 361, 175);
		sPaneAttributes.setBackground(Color.decode(ColorCode.SCROLLPANEBG.getCode()));
		sPaneAttributes.setOpaque(false);
		objectViewPanel.add(sPaneAttributes);
		trPageExplorer = new JTree();
		trPageExplorer.setBackground(Color.decode(ColorCode.SCROLLPANEBG.getCode()));
		trPageExplorer.setRootVisible(false);
		trPageExplorer.setShowsRootHandles(true);
		trPageExplorer.setModel(null);
		trPageExplorer.setBounds(10, 74, 349, 193);
		trPageExplorer.setFont(new Font(FONTNAME, Font.PLAIN, 15));

		trPageExplorer.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_F2) {
					TreePath tp = trPageExplorer.getSelectionPath();
					int selectionCount = trPageExplorer.getSelectionPath().getParentPath().toString().split(",").length;
					if (selectionCount > 1) {
						String existingObjectName = txtObjectName.getText().toString().trim();
						System.out.println("Selected object name for renaming :" + existingObjectName);
						System.out.println("Selected Object :" + existingObjectName);
						objectRename(existingObjectName);
						return;
					} else {
						String existingPageName = getSelectedPage(tp.toString());
						System.out.println("Selected page name for renaming :" + existingPageName);
						pageRename(existingPageName);
					}
				}

			}
		});
		trPageExplorer.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				addAttributesData(me, trPageExplorer, sPaneAttributes, driver1);
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 84, 351, 204);
		scrollPane.setViewportView(trPageExplorer);
		objectViewPanel.add(scrollPane);
		model = new AttributeTableModel();
		tblAttributes = new JTable(model) {
			public void tableChanged(TableModelEvent tme) {
				super.tableChanged(tme);
				repaint();
			}
		};
		tblAttributes.setGridColor(Color.decode(ColorCode.GRIDLINECOLOR.getCode()));
		tblAttributes.setFont(new Font(FONTNAME, Font.PLAIN, 12));
		tblAttributes.setRowHeight(22);
		tblAttributes.setCellSelectionEnabled(false);
		model.addRow(new Object[] { "ID", "" });
		tblAttributes.setModel(model);
		contentPane.add(new JScrollPane(tblAttributes));

		JButton btnSave = new JButton("Save");
		btnSave.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnSave.setBackground(Color.decode(ColorCode.BUTTONHOVER.getCode()));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnSave.setBackground(Color.decode(ColorCode.HAPPYBUTTON.getCode()));
			}
		});
		btnSave.setBackground(Color.decode(ColorCode.HAPPYBUTTON.getCode()));
		btnSave.setForeground(Color.decode(ColorCode.BUTTONTEXT.getCode()));
		btnSave.addActionListener(e -> saveObjects());
		btnSave.setBounds(21, 306, 74, 23);
		btnSave.setBorder(null);
		btnSave.setFont(new Font(BUTTONFONTNAME, Font.PLAIN, BUTTONFONTSIZE));
		btnSave.setIcon(IconRepo.SAVE);
		objectViewPanel.add(btnSave);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(e -> delete(trPageExplorer, tblAttributes));
		btnDelete.setBounds(100, 306, 74, 23);
		btnDelete.setBorder(null);
		btnDelete.setBackground(Color.decode(ColorCode.HAPPYBUTTON.getCode()));
		btnDelete.setForeground(Color.decode(ColorCode.BUTTONTEXT.getCode()));
		btnDelete.setFont(new Font(BUTTONFONTNAME, Font.BOLD, BUTTONFONTSIZE));
		btnDelete.setIcon(IconRepo.DELETE);
		objectViewPanel.add(btnDelete);

		btnDelete.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnDelete.setBackground(Color.decode(ColorCode.BUTTONHOVER.getCode()));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnDelete.setBackground(Color.decode(ColorCode.HAPPYBUTTON.getCode()));
			}
		});

		btnHighlight = new JButton("Highlight");
		btnHighlight.addActionListener(e -> highlightElement(attributeKey, attributeValue, trPageExplorer));
		btnHighlight.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				if (rdBtnAttr.isSelected()) {
					btnHighlight.setEnabled(false);
					btnHighlight.setBackground(Color.decode("#bbbfbf"));
				}
				else {
				btnHighlight.setBackground(Color.decode(ColorCode.BUTTONHOVER.getCode()));
				}
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				if (rdBtnAttr.isSelected()) {
					btnHighlight.setEnabled(false);
					btnHighlight.setBackground(Color.decode("#bbbfbf"));
				}
				else {
				btnHighlight.setBackground(Color.decode(ColorCode.HAPPYBUTTON.getCode()));
				}
			}
		});

		btnHighlight.setBackground(Color.decode(ColorCode.HAPPYBUTTON.getCode()));
		btnHighlight.setForeground(Color.decode(ColorCode.BUTTONTEXT.getCode()));
		btnHighlight.setBounds(386, 306, 84, 23);
		btnHighlight.setBorder(null);
		btnHighlight.setFont(new Font(BUTTONFONTNAME, Font.PLAIN, BUTTONFONTSIZE));
		btnHighlight.setIcon(IconRepo.HIGHLIGHT);
		objectViewPanel.add(btnHighlight);

		lblInfo = new JLabel("New label");
		lblInfo.setFont(new Font(FONTNAME, Font.PLAIN, FONTSIZE));
		lblInfo.setBounds(21, 338, 700, 14);
		lblInfo.setVisible(false);
		lblInfo.setForeground(Color.decode(ColorCode.BUTTONHOVER.getCode()));
		objectViewPanel.add(lblInfo);

		lblFoundNElement = new JLabel("Found N element with selected attribute");
		lblFoundNElement.setBounds(386, 338, 600, 14);
		lblFoundNElement.setFont(new Font(FONTNAME, Font.PLAIN, 16));
		lblFoundNElement.setForeground(Color.decode(ColorCode.TEXT.getCode()));
		lblFoundNElement.setVisible(false);
		objectViewPanel.add(lblFoundNElement);
	}

	public DefaultMutableTreeNode searchNode(String nodeStr) {
		DefaultMutableTreeNode node = null;
		DefaultTreeModel model = (DefaultTreeModel) trPageExplorer.getModel();
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
		Enumeration e = root.breadthFirstEnumeration();
		while (e.hasMoreElements()) {
			node = (DefaultMutableTreeNode) e.nextElement();
			if (nodeStr.equals(node.getUserObject().toString())) {
				return node;
			}
		}
		return null;
	}

	public void objectRename(String existingObjectName) {
		try {
			RenameObject obj = new RenameObject(this, existingObjectName.trim());
			if (obj.flagRenameObject == true) {
				String newObjectName = RenameObject.getNewObjectName().trim();
				if (null != pagesMap) {
					changename(null, trPageExplorer, newObjectName);
					webElementService.updateObjectMap(txtObjectName.getText(), newObjectName, objAttrMap);
					txtObjectName.setText(newObjectName);

				}
				return;
			} else {
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void pageRename(String previousName) {
		try {
			int selectionCount = trPageExplorer.getSelectionPath().getParentPath().toString().split(",").length;
			if (selectionCount > 1) {
				displayErroMessage("Please select the page to rename");
				return;
			}
			title = trPageExplorer.getSelectionPath().getLastPathComponent().toString();
			RenamePage obj = new RenamePage(this, previousName);
			if (obj.flagRenamePage == true) {
				String newName = obj.getNewPageName().trim();
				Map<String, Attribute> attributeMap = pagesMap.get(previousName.trim());
				String previousValue = changename(null, trPageExplorer, newName);
				pagesMap.put(newName, attributeMap);
				webElementService.updatePagesMap(previousValue, newName, pagesMap);
				webElementService.updatePageAlias(previousValue, newName, pageAliases, title);
				System.out.println("Final PageMap :" + pagesMap.toString());
				updatePOMPages(previousValue, newName);
				txtPageName.setText(newName);
				return;
			} else {
				return;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private JButton generateInputPanel() {
		JPanel inputPanel = new JPanel();
		inputPanel.setBorder(null);
		inputPanel.setBounds(45, 18, 774, 64);
		inputPanel.setBackground(Color.decode("#FFFFFF"));
		contentPane.add(inputPanel);
		inputPanel.setLayout(null);

		JLabel lblURL = new JLabel("URL");
		lblURL.setBounds(21, 16, 43, 22);
		lblURL.setFont(new Font(FONTNAME, Font.BOLD, FONTSIZE));
		lblURL.setForeground(Color.decode(ColorCode.TEXT.getCode()));
		inputPanel.add(lblURL);

		lblBrowserInfo = new JLabel();
		lblBrowserInfo.setFont(new Font(FONTNAME, Font.PLAIN, FONTSIZE));
		lblBrowserInfo.setBounds(21, 44, 700, 15);
		lblBrowserInfo.setForeground(Color.red);
		lblBrowserInfo.setVisible(false);
		inputPanel.add(lblBrowserInfo);

		JLabel lblMandatory = new JLabel("*");
		lblMandatory.setFont(new Font(FONTNAME, Font.PLAIN, FONTSIZE));
		lblMandatory.setBounds(45, 20, 10, 10);
		lblMandatory.setForeground(Color.red);
		inputPanel.add(lblMandatory);

		txtURL = new JTextField();
		txtURL.setBounds(66, 16, 410, 22);
		txtURL.setBackground(Color.decode(ColorCode.INPUTBACKGROUND.getCode()));
		txtURL.setBorder(null);
		txtURL.setFont(new Font(FONTNAME, Font.PLAIN, 14));
		txtURL.setForeground(Color.decode(ColorCode.TEXT.getCode()));
		if (!isNewWindow) {
			txtURL.setText(this.project.getPageUrl());
			System.out.println("txUrl textfield" + this.project.getPageUrl());
		}
		txtURL.addKeyListener(new KeyAdapter() {
			public void keyTyped(final KeyEvent e) {
				txtURL.setBorder(null);
			}
		});
		inputPanel.add(txtURL);

		JLabel projectTooltip = new JLabel();
		projectTooltip.setBounds(485, 20, 14, 14);
		projectTooltip.setToolTipText("Please provide complete URL for HTTP websites");
		projectTooltip.setIcon(IconRepo.INFO);
		inputPanel.add(projectTooltip);

		cmbBrowsers = new JComboBox<>();
		cmbBrowsers.setBounds(510, 16, 100, 22);
		Map<Object, Icon> icons = new HashMap<Object, Icon>();
		icons.put(Browsers.CHROME.getValue(), CHROME);
		cmbBrowsers.setRenderer(new BrowserImageRenderer(icons));
		cmbBrowsers.setBackground(Color.decode("#F3F3F3"));
		cmbBrowsers.addItem(Browsers.CHROME.getValue());

		cmbBrowsers.setBorder(new EmptyBorder(0, 0, 0, 0));
		cmbBrowsers.setFont(new Font(FONTNAME, Font.PLAIN, FONTSIZE));
		inputPanel.add(cmbBrowsers);

		JButton btnCaptureObject = new JButton("Capture Objects");
		btnCaptureObject.setBounds(617, 16, 133, 22);
		btnCaptureObject
				.addActionListener(e -> openBrowser(cmbBrowsers.getSelectedItem().toString(), txtURL.getText()));
		btnCaptureObject.setBackground(Color.decode(ColorCode.HAPPYBUTTON.getCode()));
		btnCaptureObject.setForeground(Color.decode(ColorCode.BUTTONTEXT.getCode()));
		btnCaptureObject.setFont(new Font(BUTTONFONTNAME, Font.PLAIN, BUTTONFONTSIZE));
		cmbBrowsers.setForeground(Color.decode(ColorCode.TEXT.getCode()));
		btnCaptureObject.setIcon(IconRepo.CAPTURE);
		btnCaptureObject.setBorder(null);

		btnCaptureObject.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnCaptureObject.setBackground(Color.decode(ColorCode.BUTTONHOVER.getCode()));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnCaptureObject.setBackground(Color.decode(ColorCode.HAPPYBUTTON.getCode()));
			}
		});
		inputPanel.add(btnCaptureObject);
		return btnCaptureObject;
	}

	private void addMenus() {
		JMenuBar mnuSpyWeb = new JMenuBar();
		setJMenuBar(mnuSpyWeb);
		mnuSpyWeb.setBackground(Color.decode(ColorCode.MENUBG.getCode()));

		JMenu mnFile = new JMenu("File");
		JMenu mnHelp = new JMenu("Help");
		JMenu mnEdit = new JMenu("Edit");

		mnuSpyWeb.add(mnFile);
		mnuSpyWeb.add(mnEdit);
		mnuSpyWeb.add(mnHelp);

		JMenuItem mntmNewProject = new JMenuItem("New Project");
		mntmNewProject.addActionListener(e -> {
			int confirmOption = unsavedChangesValidation();
			if (confirmOption == JOptionPane.NO_OPTION || confirmOption == JOptionPane.YES_OPTION) {
				invokeNewProject();
				if (null != driver1) {
					driver1.quit();
					killDrivers();
				}
				File file=new File(project.getLocation()+"\\objectRepo\\"+project.getProjectName()+".sky");
				System.out.println("File Path :"+file.toPath().toString());
				if(!Files.exists(file.toPath()))
				{
					File projectPath=new File(project.getLocation());
					System.out.println("Project Path :"+projectPath.toPath().toString());
					String[]entries = projectPath.list();
					for(String s: entries){
					    File currentFile = new File(projectPath.getPath(),s);
					    currentFile.delete();
					}
					projectPath.delete();
				}
			}
		});
		mnFile.add(mntmNewProject);

		JMenuItem mnItmImport = new JMenuItem("Open Project");
		mnItmImport.addActionListener(e -> {
			int confirmOption = unsavedChangesValidation();
			if (confirmOption == JOptionPane.NO_OPTION || confirmOption == JOptionPane.YES_OPTION) {
				importProject();
				if (null != driver1) {
					driver1.quit();
					killDrivers();
				}
				File file=new File(project.getLocation()+"\\objectRepo\\"+project.getProjectName()+".sky");
				System.out.println("Import Location :"+this.project.getLocation());
				
				System.out.println("File Path :"+file.toPath().toString());
				if(!Files.exists(file.toPath()))
				{
					File projectPath=new File(project.getLocation());
					System.out.println("Project Path :"+projectPath.toPath().toString());
					String[]entries = projectPath.list();
					for(String s: entries){
					    File currentFile = new File(projectPath.getPath(),s);
					    currentFile.delete();
					}
					projectPath.delete();
				}
				
			}
		});
		mnFile.add(mnItmImport);

		JMenuItem mnItmRename = new JMenuItem("Rename");
		mnItmRename.setAccelerator((KeyStroke) KeyStroke.getAWTKeyStroke("F2"));
		mnItmRename.addActionListener(e -> {
			TreePath tp = trPageExplorer.getSelectionPath();
			if (tp != null) {
				int selectionCount = trPageExplorer.getSelectionPath().getParentPath().toString().split(",").length;
				if (selectionCount > 1) {
					String existingObjectName = txtObjectName.getText().toString().trim();
					System.out.println("Selected object name for renaming :" + existingObjectName);
					System.out.println("Selected Object :" + existingObjectName);
					objectRename(existingObjectName);
					return;
				} else {
					String existingPageName = getSelectedPage(tp.toString());
					System.out.println("Selected page name for renaming :" + existingPageName);
					pageRename(existingPageName);
				}
			} else {
				displayErroMessage("Please select the page or object to rename");
			}
		});
		mnEdit.add(mnItmRename);

		JMenuItem mnItmDelete = new JMenuItem("Delete");
		mnItmDelete.setAccelerator((KeyStroke) KeyStroke.getAWTKeyStroke("DELETE"));

		mnItmDelete.addActionListener(e -> {
			delete(trPageExplorer, tblAttributes);
		});
		mnEdit.add(mnItmDelete);

		JMenuItem mnItmDocs = new JMenuItem("Help Contents");
		mnItmDocs.addActionListener(e -> {
			openDocs();
		});
		mnHelp.add(mnItmDocs);

		JMenuItem mnItmAbout = new JMenuItem("About");
		mnItmAbout.addActionListener(e -> {
			About frmAbout = new About();
			frmAbout.setVisible(true);
		});
		mnHelp.add(mnItmAbout);
	}

	private int unsavedChangesValidation() {
		for (Map.Entry<String, Boolean> page : savedPages.entrySet()) {
			System.out.println("Saved Pages Map: " + savedPages.toString());
			if (null != pagesMap && null != pagesMap.get(page.getKey()) && !page.getValue()) {
				int dialogButton = JOptionPane.showConfirmDialog(null, "Do you want to save the changes?", "UST iSkript",
						JOptionPane.YES_NO_OPTION);
				if (dialogButton == JOptionPane.YES_OPTION) {
					saveObjects();
					return JOptionPane.YES_OPTION;
				} else if (dialogButton == JOptionPane.NO_OPTION) {
					flagExit = true;
					return JOptionPane.NO_OPTION;
				}else if (dialogButton == JOptionPane.CLOSED_OPTION) {
					return JOptionPane.CLOSED_OPTION;
				}
				break;
			}
		}
		if (WebElementService.isFlagDelete() == true) {

			int dialogButton = JOptionPane.showConfirmDialog(null, "Do you want to save the changes?", "UST iSkript",
					JOptionPane.YES_NO_OPTION);
			if (dialogButton == JOptionPane.YES_OPTION) {
				saveObjects();
				return JOptionPane.YES_OPTION;
			} else if (dialogButton == JOptionPane.NO_OPTION) {
				flagExit = true;
				return JOptionPane.NO_OPTION;
			}  else if (dialogButton == JOptionPane.CLOSED_OPTION) {
				return JOptionPane.CLOSED_OPTION;
			}
		}
		return JOptionPane.YES_OPTION;
	}

	private void invokeNewProject() {
		dispose();
		ObjectSpyClient.generateFrame();
	}

	public String specialIcon(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {
		String spec = null;
		if (value.toString().contains("_")) {
			if (value.toString().startsWith("a_")) {
				spec = "anchor";
			} else if (value.toString().startsWith("button_")) {
				spec = "button";
			} else if (value.toString().startsWith("img_")) {
				spec = "img";
			} else if (value.toString().startsWith("h_")) {
				spec = "header";
			} else if (value.toString().startsWith("input_")) {
				spec = "input";
			} else {
				spec = "div";
			}
		} else {
			spec = "objpagename";
		}
		return spec;
	}

	private void generateObjectBasedXpath() {
		System.out.println("Entering!");
	}

	void addAttributesData(MouseEvent me, JTree tree, JScrollPane scrollPane, WebDriver driver1) {
		AttributeTableModel model = new AttributeTableModel();
		attributeMap = new HashMap<>();
		System.out.println("Attribute Map: " + attributeMap.toString());
		TreePath tp = tree.getSelectionPath();
		tblAttributes.setCellSelectionEnabled(false);
		String pageData = "";
		String objectData = "";
		if (tp != null) {
			if (tp.getParentPath().toString().contains("JTree") && tp.getParentPath().toString().contains(",")) {
				String selectedPageName = getSelectedPage(tp.getParentPath().toString());
				fillAttributesData(attributeMap, selectedPageName.trim());
				List<Object[]> attributesData = attributeMap.get(tp.getLastPathComponent().toString());
				if (null != attributesData) {
					for (Object[] o : attributesData) {
						model.addRow(o);
						tblAttributes.setModel(model);
						TableColumnModel columnModel = tblAttributes.getColumnModel();
						columnModel.getColumn(0).setPreferredWidth(50);
						columnModel.getColumn(1).setWidth(500);
						scrollPane.setViewportView(tblAttributes);
					}
					if (!rdBtnAttr.isSelected()) {
						tblAttributes.getColumn("Attribute").setCellRenderer(new RadioButtonRenderer());
						tblAttributes.getColumn("Attribute").setCellEditor(new RadioButtonEditor(new JCheckBox()));
					}

				} else {
					tblAttributes.setModel(new AttributeTableModel());
					tblAttributes.repaint();
				}
				pageData = tp.getParentPath().toString();
				pageData = pageData.replace("JTree", "");
				pageData = pageData.replace(",", "");
				pageData = pageData.replace("[", "");
				pageData = pageData.replace("]", "");
				objectData = tp.getLastPathComponent().toString();
				txtObjectName.setEditable(false);
			} else if (tp.getParentPath().toString().contains("JTree")) {
				pageData = tp.getLastPathComponent().toString();
				txtObjectName.setEditable(false);
				tblAttributes.setModel(new DefaultTableModel());
			}
			txtPageName.setText(pageData);
			System.out.println("pagedata is " + pageData);
			System.out.println("jtree url is " + project.getPageUrl());
		}
		txtObjectName.setText(objectData);
	}

	private String getSelectedPage(String parentNode) {
		System.out.println("Given Parent Node : " + parentNode);
		String[] pagesArray = parentNode.split(",");
		if (pagesArray.length == 1)
			return null;
		String selectedPage = pagesArray[1];
		selectedPage = selectedPage.replace("Page_", "");
		selectedPage = selectedPage.replace("]", "");
		return selectedPage;
	}

	private void fillAttributesData(Map<String, List<Object[]>> attributeMap, String selectedPageName) {
		System.out.println("Selected page name: " + selectedPageName);
		objAttrMap = pagesMap.get(selectedPageName.trim());
		System.out.println("Pages Map: " + pagesMap.toString());
		System.out.println("Object Map: " + objAttrMap.toString());
		String ngLocatorKey = null;
		LocatorSelectionListener selectionListener = new LocatorSelectionListener(trPageExplorer, pagesMap,
				attributeKey, attributeValue);
		for (Map.Entry<String, Attribute> entry : objAttrMap.entrySet()) {
			Attribute attr = objAttrMap.get(entry.getKey());
			JRadioButton rdBtnId = new JRadioButton("ID");
			JRadioButton rdBtnName = new JRadioButton("Name");
			JRadioButton rdBtnClassname = new JRadioButton("Classname");
			JRadioButton rdBtnCss = new JRadioButton("CSS");
			JRadioButton rdBtnXpath = new JRadioButton("XPath");
			JRadioButton rdBtnPosition = new JRadioButton("Position");
			JRadioButton rdBtnIframe = new JRadioButton("IFrame");
			rdBtnIframe.setEnabled(false);
			JRadioButton ngLocator = null;
			rdBtnId.addActionListener(selectionListener);
			rdBtnName.addActionListener(selectionListener);
			rdBtnClassname.addActionListener(selectionListener);
			rdBtnCss.addActionListener(selectionListener);
			rdBtnXpath.addActionListener(selectionListener);
			rdBtnPosition.addActionListener(selectionListener);
			rdBtnIframe.addActionListener(selectionListener);

			if (null != attr.getAttributes() && null != attr.getAttributes().get("ng-model")) {
				ngLocator = new JRadioButton("Ng-model");
				ngLocatorKey = "ng-model";
				ngLocator.addActionListener(selectionListener);
			} else if (null != attr.getAttributes() && null != attr.getAttributes().get("buttontext")) {
				ngLocator = new JRadioButton("Buttontext");
				ngLocatorKey = "buttontext";
				ngLocator.addActionListener(selectionListener);
			} else if (null != attr.getAttributes() && null != attr.getAttributes().get("ng-bind")) {
				ngLocator = new JRadioButton("Ng-bind");
				ngLocatorKey = "ng-bind";
				ngLocator.addActionListener(selectionListener);
			}
			if (null != attr.getSelectorValue() && !attr.getSelectorValue().isEmpty()) {
				System.out.println("Already selected Value: " + attr.getSelectorMethod());
				System.out.println(
						"Selector Method:" + attr.getSelectorMethod() + "Selector value:" + attr.getSelectorValue());
				if (attr.getSelectorMethod().equalsIgnoreCase("Xpath")) {
					rdBtnXpath.setSelected(true);
				}
				if (attr.getSelectorMethod().equalsIgnoreCase("ID")) {
					rdBtnId.setSelected(true);
				} else if (attr.getSelectorMethod().equalsIgnoreCase("Name")) {
					rdBtnName.setSelected(true);
				} else if (attr.getSelectorMethod().equalsIgnoreCase("Classname")) {
					rdBtnClassname.setSelected(true);
				} else if (attr.getSelectorMethod().equalsIgnoreCase("CSS")) {
					rdBtnCss.setSelected(true);
				} else if (attr.getSelectorMethod().equalsIgnoreCase("IFrame")) {
					rdBtnIframe.setSelected(false);
				} else if (attr.getSelectorMethod().equalsIgnoreCase("ng-model")
						|| attr.getSelectorMethod().equalsIgnoreCase("ng-bind")
						|| attr.getSelectorMethod().equalsIgnoreCase("buttontext")) {
					ngLocator.setSelected(true);
				} else {
					JRadioButton xpathButton = new JRadioButton(StringUtils.capitalize(attr.getSelectorMethod()));
					xpathButton.setSelected(true);
				}
			} else {
				if (null != attr && StringUtils.isNotBlank(attr.getId())) {
					System.out.println("ID: " + attr.getId());
					rdBtnId.setSelected(true);
					attr.setSelectorMethod("ID");
					attr.setSelectorValue(attr.getId());
				} else if (StringUtils.isNotBlank(attr.getAttributes().get(ngLocatorKey))) {
					ngLocator.setSelected(true);
					attr.setSelectorMethod(ngLocatorKey);
					attr.setSelectorValue(attr.getAttributes().get(ngLocatorKey));
				} else if (StringUtils.isNotBlank(attr.getXpath())) {
					rdBtnXpath.setSelected(true);
					attr.setSelectorMethod("XPath");
					attr.setSelectorValue(attr.getXpath());
				} else if (StringUtils.isNotBlank(attr.getIframeElement())) {
					rdBtnXpath.setSelected(false);
					attr.setSelectorMethod("IFrame");
					attr.setSelectorValue(attr.getIframeElement());
				} else {
					rdBtnCss.setSelected(true);
					attr.setSelectorMethod("CSS");
					attr.setSelectorValue(attr.getCss());
				}
			}

			List<Object[]> attributes = new ArrayList<>();
			ButtonGroup primeLocatorGroup = new ButtonGroup();
			primeLocatorGroup.add(rdBtnId);
			primeLocatorGroup.add(rdBtnName);
			primeLocatorGroup.add(rdBtnClassname);
			primeLocatorGroup.add(rdBtnCss);
			primeLocatorGroup.add(rdBtnXpath);
			primeLocatorGroup.add(rdBtnPosition);
			primeLocatorGroup.add(ngLocator);
			primeLocatorGroup.add(rdBtnIframe);

			if (rdBtnLocators.isSelected()) {
				btnHighlight.setEnabled(true);
				btnHighlight.setBackground(Color.decode(ColorCode.HAPPYBUTTON.getCode()));
				if (attr.getId().isEmpty()) {
					attributes.add(new Object[] { rdBtnId, attr.getId() });
					rdBtnId.setEnabled(false);
				} else {
					attributes.add(new Object[] { rdBtnId, attr.getId() });
				}
				if (attr.getAttributes().get("name") == null) {
					attributes.add(new Object[] { rdBtnName, attr.getAttributes().get("name") });
					rdBtnName.setEnabled(false);
				} else {
					attributes.add(new Object[] { rdBtnName, attr.getAttributes().get("name") });
				}
				if (attr.getClassname().isEmpty()) {
					attributes.add(new Object[] { rdBtnClassname, attr.getClassname() });
					rdBtnClassname.setEnabled(false);
				} else {
					attributes.add(new Object[] { rdBtnClassname, attr.getClassname() });
				}
				if (attr.getCss().isEmpty()) {
					attributes.add(new Object[] { rdBtnCss, attr.getCss() });
					rdBtnCss.setEnabled(false);
				} else {
					attributes.add(new Object[] { rdBtnCss, attr.getCss() });
				}
				if (attr.getXpath().isEmpty()) {
					attributes.add(new Object[] { rdBtnXpath, attr.getXpath() });
					rdBtnXpath.setEnabled(false);
				} else {
					attributes.add(new Object[] { rdBtnXpath, attr.getXpath() });
				}
				attributes.add(new Object[] { rdBtnIframe, attr.getIframeElement() });
				if (null != ngLocator)
					attributes.add(new Object[] { ngLocator, attr.getAttributes().get(ngLocatorKey) });
			}

			if (rdBtnXPath.isSelected()) {
				btnHighlight.setEnabled(true);
				btnHighlight.setBackground(Color.decode(ColorCode.HAPPYBUTTON.getCode()));
				for (Map.Entry<String, String> a : attr.getXpaths().entrySet()) {
					System.out.println("Key: " + StringUtils.capitalize(a.getKey()));
					JRadioButton xpathButton = new JRadioButton(
							StringUtils.capitalize(a.getKey().replace("XPath:", "")));
					xpathButton.addActionListener(selectionListener);
					System.out.println("Selected Key: " + selectedKey);
					if (null != attr.getSelectorMethod() && attr.getSelectorMethod().equalsIgnoreCase(a.getKey())) {
						xpathButton.setSelected(true);
					}
					primeLocatorGroup.add(xpathButton);
					if (a.getValue() == null || a.getValue() == "") {

					} else {

						attributes.add(new Object[] { xpathButton, a.getValue() });
					}
				}
			}

			if (rdBtnAttr.isSelected()) {
				btnHighlight.setEnabled(false);
				btnHighlight.setBackground(Color.decode("#bbbfbf"));
				Set<String> filteredAttributes = new HashSet<>();
				filteredAttributes.add("id");
				filteredAttributes.add("name");
				filteredAttributes.add("class");
				filteredAttributes.add("ng-model");
				filteredAttributes.add("buttontext");
				filteredAttributes.add("ng-bind");
				Map<String, String> filteredPropertyMap = attr.getAttributes().entrySet().stream()
						.filter(ent -> !filteredAttributes.contains(ent.getKey()))
						.collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));
				for (Map.Entry<String, String> a : filteredPropertyMap.entrySet()) {
					JRadioButton xpathButton = new JRadioButton(StringUtils.capitalize(a.getKey()));
					xpathButton.addActionListener(selectionListener);
					System.out.println("Key: " + StringUtils.capitalize(a.getKey()));
					primeLocatorGroup.add(xpathButton);
					if (StringUtils.isNotBlank(a.getValue()))
						attributes.add(new Object[] { xpathButton.getText(), a.getValue() });
				}
			}
			attributeMap.put(entry.getKey(), attributes);
			System.out.println("Table Data: " + attributeMap.toString());
		}

	}

	private void addPageExplorerData(ActionEvent me, JTree trPageExplorer) {
		addTreeObjects(trPageExplorer);
	}

	private Attribute addTreeObjects(JTree trPageExplorer) {
		trPageExplorer.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("JTree")));

		updateTree(trPageExplorer, null);
		return null;
	}

	public void updateTree(JTree tree, Map<String, Map<String, Attribute>> pages) {
		if (null != pages) {
			System.out.println("Import Calling");
			pagesMap = pages;
		}
		try {
			DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
			tree.setCellRenderer(new TreeNodeRenderer(nodeIcons));
			DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
			String pageName = "";
			if (null != pagesMap && !pagesMap.isEmpty()) {
				for (Map.Entry<String, Map<String, Attribute>> entry : pagesMap.entrySet()) {
					pageName = entry.getKey().trim();
					Map<String, Attribute> attr = entry.getValue();
					objAttrMap.putAll(attr);
					System.out.println("pageName: " + pageName);
					if (((DefaultComboBoxModel) cmbPageName.getModel()).getIndexOf(pageName) == -1) {
						cmbPageName.removeItem(entry.getKey().trim());
						cmbPageName.addItem(pageName);
					}
					DefaultMutableTreeNode page1 = new DefaultMutableTreeNode(pageName.trim());
					System.out.println("Pagneme is " + pageName);
					System.out.println("User Object: " + page1.getUserObject().toString());
					System.out.println("page Validation: " + root.isNodeChild(page1));
					if (pagesMap.keySet().contains("Noname") == true) {
						count = 1;
					}

					if (!root.isNodeChild(page1)) {
						model.insertNodeInto(page1, root, root.getChildCount());
						model.reload();
					}
					for (String objectName : entry.getValue().keySet()) {
						DefaultMutableTreeNode child = new DefaultMutableTreeNode(objectName);
						System.out.println("Object Child: " + page1.isNodeChild(child));
						if (!page1.isNodeChild(child)) {
							model.insertNodeInto(child, page1, page1.getChildCount());
						}
						tree.scrollPathToVisible(new TreePath(child.getPath()));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateTreeWithSingleNode(JTree tree, Attribute attribute) {
		try {
			DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
			tree.setCellRenderer(new TreeNodeRenderer(nodeIcons));
			if (model != null) {
				if (null == tree.getSelectionPath()) {
					displayErroMessage("Please select any page to add the element.");
					return;
				}
				DefaultMutableTreeNode selectedPage = (DefaultMutableTreeNode) tree.getSelectionPath()
						.getLastPathComponent();
				System.out.println("Selected Page Name in the Explorer:  " + selectedPage);
				DefaultMutableTreeNode child = new DefaultMutableTreeNode(
						attribute.getTagName().toLowerCase() + "_" + attribute.getNodename());
				System.out.println("Element is Available in the selected page: " + selectedPage.isNodeChild(child));
				if (!selectedPage.isNodeChild(child)) {
					model.insertNodeInto(child, selectedPage, selectedPage.getChildCount());
					model.reload(child);
					TreePath path = new TreePath(child.getPath());
					tree.setSelectionPath(path);
					addAttributesData(null, trPageExplorer, sPaneAttributes, driver1);
					txtObjectName.setText("");
					tblAttributes.setModel(new DefaultTableModel());
				}
				tree.setSelectionPath(new TreePath(selectedPage.getPath()));
				model.reload(selectedPage);
				tree.setFocusable(true);
			} else {
				displayErroMessage("Please Create/Select a Page");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void openBrowser(String selectedBrowser, String url) {
		if (isFlagBrowser() == false) {
			System.out.println("Opening a browser");
			if (validatorUtil.isURLAvailable(txtURL) && validatorUtil.verifyURL(txtURL, lblBrowserInfo)
					&& selectedBrowser.equalsIgnoreCase(Browsers.CHROME.getValue())) {
				System.setProperty("webdriver.chrome.driver", Browsers.CHROME.getWebdriverLocation());
				try {
					JOptionPane.showMessageDialog(null, AppConstants.SHORTCUTKEYMSG);
					driver1 = new ChromeDriver();
					url = txtURL.getText();
					initiateBrowser(url, driver1, txtURL);
					project.setPageUrl(url);
					System.out.println("urls are" + project.getPageUrl());
					System.out.println("URL location 1: " + driver1.getCurrentUrl());
					generateDriverMap(driver1.getCurrentUrl(), driver1);
					pagesSet = new HashSet<>();
					flagBrowser = true;
				} catch (WebDriverException e) {
					displayBrowserError(e.getMessage(), Browsers.CHROME.getValue());
				} catch (Exception e) {
					displayBrowserError(e.getMessage(), Browsers.CHROME.getValue());
					System.out.println("Common Error Message: " + e.getMessage());
				}
			} else {
				if (validatorUtil.isURLAvailable(txtURL) == false) {
					lblBrowserInfo.setVisible(true);
					lblBrowserInfo.setText("Please enter application URL");
					MessageTimer timer = new MessageTimer(lblBrowserInfo);
					timer.start();
					return;
				}
			}
		} else {
			lblBrowserInfo.setVisible(true);
			lblBrowserInfo.setText("Browser instance is already up and running");
			MessageTimer timer = new MessageTimer(lblBrowserInfo);
			timer.start();
		}

	}

	private void initiateBrowser(String url, WebDriver webDriver, JTextField txtURL) {
		webDriver.manage().window().maximize();
		webDriver.get(url);
		if (webDriver.getCurrentUrl().contains(url))
			txtURL.setText(webDriver.getCurrentUrl());
		System.out.println("Mouse hover script started...");
		js = (JavascriptExecutor) webDriver;
		System.out.println("Mouse hover script Ended...");
		Thread t1 = new Thread(new ChangeListener(this), "changelistener");
		t1.start();
	}

	public Attribute extractElementData(JavascriptExecutor js, WebDriver driver1) {

		WebElement we = null;
		Attribute attribute = new Attribute();
		System.out.println("Script running time: Start: " + new Date());
		String data = js.executeScript(scriptingService.getDataExtractorScript()).toString();
		System.out.println("Script running time: End" + new Date());

		System.out.println("Extracted Data from JS: " + data);

		System.out.println("Parsing Started " + new Date());
		Attribute capturedData = parseObject(data);
		System.out.println("Extracted Data from JS: " + capturedData);
		attribute.setId(capturedData.getId());
		String formattedXPath = capturedData.getXpath().replace("bo" + "dy", "/");
		formattedXPath = formattedXPath.replace("t/", "tbody");
		attribute.setXpath(formattedXPath);
		attribute.setCss(capturedData.getCss());
		Map<String, String> attMap = capturedData.getAttributes();
		attribute.setAttributes(attMap);
		attribute.setClassname(attMap.get("class"));
		attribute.setTextContent(capturedData.getTextContent());
		attribute.setTagName(capturedData.getTagName());
		attribute.setName(capturedData.getName());
		System.out.println("parsed Data: " + attribute.toString());
		System.out.println("Parsing Ended " + new Date());
		if (null != trPageExplorer.getSelectionPath()
				&& null != trPageExplorer.getSelectionPath().getLastPathComponent()) {
			title = trPageExplorer.getSelectionPath().getLastPathComponent().toString();
		} else {
			title = stringUtil.getformattedPage(driver1.getTitle(), driver1.getCurrentUrl());
		}

		String pageAlias = null;
		if (null == pagesMap || pagesMap.isEmpty()) {
			pagesMap = new HashMap<>();
			objAttrMap = new HashMap<>();
		} else if (null != pagesMap) {
			if (!pageAliases.isEmpty()) {
				pageAlias = pageAliases.get(title);
				System.out.println("other name: " + pageAlias);
			} else {
				pageAlias = title;
				System.out.println("other name in Else: " + pageAlias);
			}
			objAttrMap = pagesMap.get(pageAlias);
			if (null == objAttrMap || objAttrMap.isEmpty()) {
				objAttrMap = new HashMap<>();
			}
		}

		attMap.put("name", capturedData.getName());
		System.out.println("Name end: " + new Date());
		we = driver1.findElement(By.xpath(attribute.getXpath()));
		attribute.setName(capturedData.getName());
		attribute.setId(webElementService.checkElementId(attribute.getXpath(), attribute.getId()));
		System.out.println("Element Attributes: " + attMap.toString());
		generateXPathExpression(driver1, we, attribute, attMap);
		if (null == pageAlias && null != title) {
			pageAliases.put(title.trim(), title);
		}
		System.out.println("Page Name: " + title + "Page Alias Map: " + pageAliases.toString());
		if (!pageAliases.isEmpty())
			pagesMap.put(pageAliases.get(title).trim(), objAttrMap);
		pagesSet.add(driver1.getCurrentUrl());
		sitePagesMap.put(txtURL.getText(), pagesSet);

		System.out.println("Size of the Objects:  " + objAttrMap.size() + "Size of the pages: " + pagesMap.size());
		System.out.println("SitePages Map: " + sitePagesMap.toString());
		return attribute;
	}

	public Attribute addElement(JavascriptExecutor js, WebDriver driver1) {
		WebElement we = null;
		if (null == trPageExplorer.getModel()) {
			js.executeScript(scriptingService
					.getPopupMessageScript("Please create and select the page name to add the element", ERROR));
			return null;
		} else if (null != trPageExplorer.getModel()) {
			DefaultMutableTreeNode root = (DefaultMutableTreeNode) trPageExplorer.getModel().getRoot();
			System.out.println("Root Node: " + root.getUserObject().toString());
			if (root.getChildCount() == 0) {
				js.executeScript(scriptingService
						.getPopupMessageScript("Please create and select the page name to add the element", ERROR));
				return null;
			}
		}

		if (null != trPageExplorer.getSelectionPath()
				&& null != trPageExplorer.getSelectionPath().getLastPathComponent()) {
			title = trPageExplorer.getSelectionPath().getLastPathComponent().toString();
		} else {
			title = stringUtil.getformattedPage(driver1.getTitle(), driver1.getCurrentUrl());
		}
		if (StringUtils.isEmpty(title)) {
			js.executeScript(scriptingService.getPopupMessageScript("Please enter the page name", ERROR));
			return null;
		}
		String data = js.executeScript(scriptingService.getDataExtractorScript()).toString();
		Attribute attribute = parseObject(data);
		System.out.println("Extracted Data from JS: " + data);
		System.out.println("Extracted Data from JS***: " + attribute);
		if (StringUtils.isEmpty(attribute.getNodename())) {
			js.executeScript(scriptingService.getPopupMessageScript("Please enter the object name", ERROR));
			return null;
		}
		tagname = attribute.getTagName();
		if(attribute.getTagName().contains("-"))
		{
			 tagname=attribute.getTagName().replaceAll("-", "_");
		}
		String name = tagname.toLowerCase() + "_" + attribute.getNodename().toLowerCase();
		System.out.println("Extracted Data ****: " + name);
		Map<String, String> xpaths = new HashMap<>();
		attribute.setClassname(attribute.getAttributes().get("class"));
		attribute.setName(attribute.getAttributes().get("name"));
		attribute.setIframeElement(attribute.getIframeElement());
		System.out.println("Property Map modified: " + xpaths.toString());
		objAttrMap = pagesMap.get(title);
		System.out.println("xpathss" + xpaths);
		System.out.println("title **" + title);
		System.out.println("pagemap" + pagesMap);
		System.out.println("objmap" + objAttrMap);
		if (trPageExplorer.getSelectionPath() == null) {
			js.executeScript(scriptingService.getPopupMessageScript("Please select the page", ERROR));
			System.out.println("Please select the page");
			return null;
		} else {
			int selectionCount = trPageExplorer.getSelectionPath().getParentPath().toString().split(",").length;
			System.out.println("count***" + selectionCount);
			if (null == objAttrMap && selectionCount > 1) {
				js.executeScript(scriptingService.getPopupMessageScript("Please select the page.", ERROR));
				System.out.println("Please select the page");
				return null;
			}
		}
		if (null != objAttrMap && null != objAttrMap.get(name.toLowerCase()) && objAttrMap.containsKey(name)) {
			js.executeScript(scriptingService
					.getPopupMessageScript("The object name already exists. Please enter a new name", ERROR));
			System.out.println(name + " Object already exist.");
			return null;
		}
		boolean flagIframe = false;
		if (attribute.getIframeElement() != null) {
			if (attribute.getIframeElement().contains("//")) {
				driver1.switchTo().frame(driver1.findElement(By.xpath(attribute.getIframeElement())));
				flagIframe = true;
			} else {
				if (!attribute.getIframeElement().equals("")) {
					driver1.switchTo().frame(driver1.findElement(By.id(attribute.getIframeElement())));
					flagIframe = true;
				}
			}
		}
		we = driver1.findElement(By.xpath(attribute.getXpath()));
		if (webElementService.isAngularElement(attribute)) {
			if ("button".equalsIgnoreCase(attribute.getTagName())) {
				attribute.getAttributes().put("buttontext", we.getText());
			}
		}
		generateXPathExpression(driver1, we, attribute, xpaths);
		xpaths.put("default", attribute.getXpath());
		xpaths.put("fullxpath", "/"+attribute.getCompleteXpath());
		xpaths.put("precedance", attribute.getpreced());
		xpaths.put("parent", attribute.getparent());
		if (attribute.getfollow() == null) {
			xpaths.put("follows", "");
		} else {
			xpaths.put("follows", attribute.getfollow());
		}
		xpaths.put("contains", attribute.getTextContent());
		objAttrMap.put(tagname.toLowerCase() + "_" + attribute.getNodename().toLowerCase(), attribute);
		if (null == pagesMap || pagesMap.isEmpty()) {
			displayErroMessage("Please create a page...");
			return null;
		}
		attribute.setXpaths(xpaths);
		pagesMap.put(title.trim(), objAttrMap);
		savedPages.put(title.trim(), false);
		pagesSet.add(driver1.getCurrentUrl());
		sitePagesMap.put(txtURL.getText(), pagesSet);
		if (flagIframe == true) {
			driver1.switchTo().defaultContent();
		}
		js.executeScript(scriptingService.getPopupMessageScript("The object addition was successful", SUCCESS));
		System.out.println("Attribute Object: " + attribute.toString());
		System.out.println("Object added: " + objAttrMap.toString());
		System.out.println("Size of the Objects:  " + objAttrMap.size() + "Size of the pages: " + pagesMap.size());
		return attribute;
	}

	private void generateXPathExpression(WebDriver driver1, WebElement we, Attribute attribute,
			Map<String, String> attMap) {
		System.out.println("Initial AttMap: " + attMap.toString());
		String attributeKey = attribute.getAttributes().keySet().iterator().next().toString();
		System.out.println("Attribute Key: " + attributeKey);
		boolean isAngular = false;
		System.out.println("XPath Expression Start TIme: " + new Date());
		generateXPathAxes(driver1, we, attribute, attMap, attributeKey, isAngular);
	}

	private void generateRelativeLocators(WebDriver driver1, WebElement we, Attribute attribute,
			Map<String, String> attMap) {
		WebElement leftElement = relativeElementLocator.getLeftElement(driver1, we);
		if (null != leftElement) {
			Map<String, String> leftElementMap = stringUtil
					.insertAttributes(elementUtil.getAllAttributes(leftElement, driver1));
			String leftElementAttribute = leftElementMap.keySet().iterator().next().toString();
			attMap.put("XPath:Left", xpathExpressionGenerator.generateAttributeExpression(leftElement,
					leftElementAttribute, attribute, driver1));
		} else {
			attMap.put("XPath:Left", "");
		}

		WebElement rightElement = relativeElementLocator.getRightElement(driver1, we);
		if (null != rightElement) {
			Map<String, String> rightElementMap = stringUtil
					.insertAttributes(elementUtil.getAllAttributes(rightElement, driver1));
			String rightElementAttribute = rightElementMap.keySet().iterator().next().toString();
			attMap.put("XPath:right", xpathExpressionGenerator.generateAttributeExpression(rightElement,
					rightElementAttribute, attribute, driver1));
		} else {
			attMap.put("XPath:right", "");
		}

		WebElement aboveElement = relativeElementLocator.getAboveElement(driver1, we);
		if (null != aboveElement) {
			Map<String, String> aboveElementMap = stringUtil
					.insertAttributes(elementUtil.getAllAttributes(aboveElement, driver1));
			String aboveElementAttribute = aboveElementMap.keySet().iterator().next().toString();
			attMap.put("XPath:above", xpathExpressionGenerator.generateAttributeExpression(aboveElement,
					aboveElementAttribute, attribute, driver1));
		} else {
			attMap.put("XPath:above", "");
		}

		WebElement belowElement = relativeElementLocator.getBelowElement(driver1, we);
		if (null != belowElement) {
			Map<String, String> belowElementMap = stringUtil
					.insertAttributes(elementUtil.getAllAttributes(belowElement, driver1));
			String belowElementAttribute = belowElementMap.keySet().iterator().next().toString();
			attMap.put("XPath:below", xpathExpressionGenerator.generateAttributeExpression(belowElement,
					belowElementAttribute, attribute, driver1));
		} else {
			attMap.put("XPath:below", "");
		}
		WebElement nearElement = relativeElementLocator.getNearElement(driver1, we);
		Map<String, String> nearElementMap = stringUtil
				.insertAttributes(elementUtil.getAllAttributes(nearElement, driver1));
		if (null != nearElementMap) {
			String nearElementAttribute = nearElementMap.keySet().iterator().next().toString();
			attMap.put("XPath:near", xpathExpressionGenerator.generateAttributeExpression(nearElement,
					nearElementAttribute, attribute, driver1));
		} else {
			attMap.put("XPath:near", "");
		}
	}

	private void generateXPathAxes(WebDriver driver1, WebElement we, Attribute attribute, Map<String, String> attMap,
			String attributeKey, boolean isAngular) {
		attMap.put("ancestor", xpathExpressionGenerator.generateAncestorExpression(we, attribute.getXpath(),
				attributeKey, isAngular, attribute, driver1));

		// To generate the attribute expression
		attMap.put("attribute",
				xpathExpressionGenerator.generateAttributeExpression(we, attributeKey, attribute, driver1));

		// To generate the Child Expression
		attMap.put("child", xpathExpressionGenerator.generateChildExpression(we, attribute.getXpath(), attributeKey,
				isAngular, attribute, driver1));

		// To generate the Descendant Expression
		attMap.put("descendant", xpathExpressionGenerator.generateDescendentExpression(we, attribute.getXpath(),
				attributeKey, isAngular, attribute, driver1));

		// To generate the Position Expression
		attMap.put("position",
				xpathExpressionGenerator.generatePositionExpression(attribute.getXpath(), we, attributeKey, driver1));

	}

	private Attribute parseObject(String data) {
		Gson jsonParser = new Gson();
		return jsonParser.fromJson(data, Attribute.class);
	}

	public void icon() {
		Icon icon = new ImageIcon("folder_icon.jpg");
		UIManager.put("Tree.closedIcon", icon);
		UIManager.put("Tree.openIcon", icon);
		UIManager.put("Tree.leafIcon", icon);
	}

	String changename(MouseEvent me, JTree tree1, String value) {
		DefaultMutableTreeNode selectednode = (DefaultMutableTreeNode) tree1.getLastSelectedPathComponent();
		String previousValue = "";
		if (selectednode == null) {
			System.out.println("Selected node is null");
			return "";
		}
		previousValue = selectednode.getUserObject().toString();
		System.out.println("Selected Node: " + previousValue);
		selectednode.setUserObject(value);
		System.out.println("Selected Node after setting: " + selectednode.getUserObject().toString());
		DefaultTreeModel model = (DefaultTreeModel) tree1.getModel();
		model.reload(selectednode);
		return previousValue;
	}

	private String getQualifiedURL(String url) {
		if (null != url && !url.contains("https")) {
			if (!url.contains("www")) {
				url = "https://www." + url;
			} else {
				url = "https://" + url;
			}
			return url;
		}
		return url;
	}

	private void highlightElement(String attributeKey, String value, JTree tree) {
		String iframeelement = null;
		boolean iframeflag = false;
		System.out.println("Object Map: " + objAttrMap.toString());
		System.out.println("Driver Info: " + driver1);

		try {
			if (null == driver1 && objAttrMap != null) {
				highlightInfo(lblFoundNElement, "Please launch the browser to highlight", Color.RED);
				return;
			}
			if (null == driver1) {
				highlightInfo(lblFoundNElement, "No objects selected to highlight", Color.RED);
				return;
			}

			if (objAttrMap.isEmpty() || pagesMap.isEmpty()) {
				highlightInfo(lblFoundNElement, "Please select the object to highlight", Color.RED);
				return;
			}
			if (null == objAttrMap.get(tree.getSelectionPath().getLastPathComponent().toString())) {
				highlightInfo(lblFoundNElement, "Please select the object to highlight", Color.RED);
				return;
			}
			/*
			Set<String> stringsSet = driver1.getWindowHandles();
			List<String> stringsList = new ArrayList<String>(stringsSet);
			String MainWindow = stringsList.get(0);
			driver1.switchTo().window(MainWindow); */
			
			
			
			Attribute a = objAttrMap.get(tree.getSelectionPath().getLastPathComponent().toString());
			attributeKey = a.getSelectorMethod();

			if (a.getIframeElement() != null && !a.getIframeElement().isEmpty()) {

				iframeelement = a.getIframeElement();
				iframeflag = true;

			}

			if (a.getSelectorMethod().equalsIgnoreCase("default")) {
				a.setSelectorValue(a.getXpath());
			} else if (a.getSelectorMethod().equalsIgnoreCase("fullxpath")) {
				a.setSelectorValue(a.getXpaths().get("fullxpath"));
			} else if (a.getSelectorMethod().equalsIgnoreCase("ancestor")) {
				a.setSelectorValue(a.getXpaths().get("ancestor"));
			} else if (a.getSelectorMethod().equalsIgnoreCase("descendant")) {
				a.setSelectorValue(a.getXpaths().get("descendant"));
			} else if (a.getSelectorMethod().equalsIgnoreCase("child")) {
				a.setSelectorValue(a.getXpaths().get("child"));
			} else if (a.getSelectorMethod().equalsIgnoreCase("default")) {
				a.setSelectorValue(a.getXpaths().get("default"));
			} else if (a.getSelectorMethod().equalsIgnoreCase("precedance")) {
				a.setSelectorValue(a.getXpaths().get("precedance"));
			} else if (a.getSelectorMethod().equalsIgnoreCase("follows")) {
				a.setSelectorValue(a.getXpaths().get("follows"));
			} else if (a.getSelectorMethod().equalsIgnoreCase("contains")) {
				a.setSelectorValue(a.getXpaths().get("contains"));
			} else if (a.getSelectorMethod().equalsIgnoreCase("parent")) {
				a.setSelectorValue(a.getXpaths().get("parent"));
			}

			value = a.getSelectorValue();
			System.out.println("Selected Key: " + attributeKey + "Selected Value: " + value);
			if (null != attributeKey && null != value && !value.isEmpty()) {
				if (iframeflag == true) {
					if (iframeelement.contains("\"") || iframeelement.contains("\'")
							|| iframeelement.trim().charAt(0) == '/') {
						System.out.println("Selected Xpath is inside iframe with the xpath:" + iframeelement);
						driver1.switchTo().frame(driver1.findElement(By.xpath(iframeelement)));
					} else {
						driver1.switchTo().frame(iframeelement);
					}
					js = (JavascriptExecutor) driver1;
					driver1 = getDriver(trPageExplorer);

				} else {
					js = (JavascriptExecutor) driver1;
					driver1 = getDriver(trPageExplorer);
				}
				System.out.println("Key: " + attributeKey.trim() + " Value: " + value);
				List<WebElement> webElements = webElementService.findElementByLocators(
						LocatorType.fromString(attributeKey.trim()), value, driver1, attributeKey);
				System.out.println("Web Element: " + webElements);
				if (null != webElements && !webElements.isEmpty()) {
					int n = webElements.size();
					for (int j = 0; j < 4; j++) {
						for (int i = 0; i < n; i++) {
							js.executeScript("arguments[0].scrollIntoView(true);", webElements.get(i));
							js.executeScript("arguments[0].setAttribute('style', arguments[1]);", webElements.get(i),
									"border: 2px solid green;");
						}
						try {
							Thread.sleep(250);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						for (int i = 0; i < n; i++) {
							js.executeScript("arguments[0].setAttribute('style', arguments[1]);", webElements.get(i),
									"border: 2px solid red");
						}
						try {
							Thread.sleep(250);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						for (int i = 0; i < n; i++) {
							js.executeScript("arguments[0].setAttribute('style', arguments[1]);", webElements.get(i));
						}
					}
					clearSelection();
					highlightInfo(lblFoundNElement, webElements.size() + " element found",
							Color.decode(ColorCode.HAPPYBUTTON.getCode()));
				} else {
					boolean isBrowserClosed = false;
					try {
						System.out.println("driver:" + driver1.getCurrentUrl());
					} catch (WebDriverException e) {
						isBrowserClosed = true;
						highlightInfo(lblFoundNElement, "Please open a new browser instance", Color.RED);
					}
					if (!isBrowserClosed)
						highlightInfo(lblFoundNElement, "Unable to highlight the object. Please try learning again", Color.RED);
				}
				if (iframeflag == true) {
					driver1.switchTo().defaultContent();
					iframeflag = false;
				}
			} else {
				displayErroMessage("No Objects found to Highlight!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void clearSelection() {
		attributeKey = "";
		attributeValue = "";
	}

	static {
		nodeIcons = new HashMap<>();
		nodeIcons.put("input", INPUT);
		nodeIcons.put("a", ANCHOR);
		nodeIcons.put("button", BUTTON);
		nodeIcons.put("img", IMAGE);
		nodeIcons.put("h", HEADER);
		nodeIcons.put("parentnode", FOLDER);
		nodeIcons.put("Page", FOLDER);
		nodeIcons.put("default", DIV);
		nodeIcons.put("label", DIV);
		nodeIcons.put("h2", DIV);
		nodeIcons.put("legend", DIV);
		nodeIcons.put("span", DIV);
		nodeIcons.put("select", DIV);
		nodeIcons.put("td", TABLE1);
		nodeIcons.put("th", TABLE1);
		nodeIcons.put("div", DIV);
		nodeIcons.put("table", TABLE1);
		nodeIcons.put("radio", RADIO);
		primeLocators = new ArrayList<>();
		primeLocators.add("ng");
	}

	private void delete(JTree pageExplorer, JTable tblAttributes) {
		try {

			String deleteNode = "";
			boolean isPage = false;
			String parentNode = "";
			Object selectedNode = null;
			TreePath[] selectedRows = pageExplorer.getSelectionPaths();
			if (selectedRows == null) {
				displayErroMessage("Please select the page or object to delete");
				return;
			}
			String selectedPageName = "";
			String selectedElementName = "";
			for (TreePath tree : selectedRows) {
				selectedNode = tree.getLastPathComponent();
				parentNode = tree.getParentPath().toString();
				System.out.println("Selected Node: " + selectedNode + " Parent Node : " + parentNode);
				if (null == selectedNode) {
					displayErroMessage("No Objects found to Delete!");
					return;
				}
				selectedElementName = selectedNode.toString();
				selectedPageName = getSelectedPage(pageExplorer.getSelectionPath().getParentPath().toString());
				if (selectedPageName == null) {
					isPage = true;
				}
			}
			String selectedObject = "";
			String objectType = "";
			if (isPage == true) {
				selectedObject = selectedElementName;
				objectType = "page";
			} else {
				selectedObject = selectedElementName;
				objectType = "object";
			}

			DeleteOptions obj = new DeleteOptions(this, objectType, selectedObject);

			if (obj.isDeleteFlag() == true) {

				for (TreePath tree : selectedRows) {
					selectedNode = tree.getLastPathComponent();
					parentNode = tree.getParentPath().toString();
					System.out.println("Selected Node: " + selectedNode + " Parent Node : " + parentNode);
					if (null == selectedNode) {
						displayErroMessage("No Objects found to Delete!");
						return;
					}
					selectedElementName = selectedNode.toString();
					selectedPageName = getSelectedPage(pageExplorer.getSelectionPath().getParentPath().toString());
					System.out.println("Tbl Attributes Model Size: " + tblAttributes.getModel().getColumnCount());
					System.out.println("Element to be deleted: " + selectedElementName);
					if (null == selectedPageName) {
						isPage = true;
						selectedPageName = selectedElementName.replace("Page_", "");
					}
					webElementService.removeData(selectedElementName, selectedPageName, pagesMap, isPage, attributeMap,
							cmbPageName, tblAttributes);
					webElementService.removeElementFromExplorer(pageExplorer, selectedNode, isPage, txtPageName,
							txtObjectName);
					deleteNode = selectedElementName;
				}
				if (isPage == true) {
					successInfo(lblInfo, "The deletion of Page '" + deleteNode + "' was successful",
							Color.decode(ColorCode.HAPPYBUTTON.getCode()));
				} else {
					successInfo(lblInfo, "The deletion of Object '" + deleteNode + "' was successful",
							Color.decode(ColorCode.HAPPYBUTTON.getCode()));
				}
				System.out.println("Object Attribute Map :" + objAttrMap);
			} else {
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void displayErroMessage(String message) {
		lblInfo.setVisible(true);
		lblInfo.setText(message);
		lblInfo.setForeground(Color.RED);
		MessageTimer msgTimer = new MessageTimer(lblInfo);
		msgTimer.start();
	}

	private void displayError(String message) {
		lblGenerateInfo.setVisible(true);
		lblGenerateInfo.setText(message);
		lblGenerateInfo.setForeground(Color.RED);
		MessageTimer msgTimer = new MessageTimer(lblGenerateInfo);
		msgTimer.start();
	}

	private void displayBrowserError(String message, String browserType) {
		String errorMessage = "";
		String msg = "";
		System.out.println("Message: " + message);
		String driverMissingMessage ="Please copy the latest version of "+ browserType + " driver to the browserdrivers folder "
				+ browserType.toLowerCase();
		if (null != message && (message.contains("The driver executable does not exist")
				|| message.contains("Cannot find firefox binary in PATH"))) {
			errorMessage = driverMissingMessage;
		}
		if (errorMessage.contains("Build info")) {
			errorMessage = "Please install the latest version of the browser";
		} else if (message.contains("This version of ChromeDriver only supports")) {
			errorMessage = "Please update the chrome driver";
			flagBrowserVersion=true;
		}
		System.out.println("Error Message: " + errorMessage);
		lblBrowserInfo.setVisible(true);
		lblBrowserInfo.setText(errorMessage);
		lblBrowserInfo.setForeground(Color.RED);
		MessageTimer timer = new MessageTimer(lblBrowserInfo);
		timer.start();
	}

	public static void main(String[] args) {
		new SpyWeb().invokeSpyWeb(true);
	}

	private void generateNewPage(JTree tree) {
		createNewPage(tree);
	}

	private void generateDriverMap(String url, WebDriver driver) {
		driverMap.put(url, driver);
		System.out.println("Driver Map: " + driverMap.toString());
	}

	private String getWebPageName(String qualifiedURL) {
		return "https://" + qualifiedURL.split("/")[2];
	}

	public WebDriver getWebDriver(String qualifiedURL) {
		String webPageName = getWebPageName(qualifiedURL);
		return driverMap.get(webPageName);
	}

	private String getWebPageBySelectedNode(JTree tree) {
		TreePath tp = tree.getSelectionPath();
		String domain = "";
		if (null != tp && null != tp.getParentPath() && tp.getParentPath().toString().contains("JTree")
				&& tp.getParentPath().toString().contains(",")) {
			String selectedPageName = getSelectedPage(tp.getParentPath().toString());
			System.out.println("Selected Page Name: " + selectedPageName);
			System.out.println("SitePage: " + sitePagesMap.toString());
			selectedPageName = selectedPageName.replace("https:", "");
			for (Map.Entry<String, Set<String>> sitePage : sitePagesMap.entrySet()) {
				for (String d : sitePage.getValue()) {
					if (d.contains(selectedPageName)) {
						domain = sitePage.getKey();
						System.out.println("Domain Value: " + domain);
					}
				}
			}
		}
		return domain;
	}

	private WebDriver getDriver(JTree tree) {
		String domain = getWebPageBySelectedNode(tree);
		System.out.println("Domain: " + domain);
		System.out.println("driverMap: " + driverMap.toString());
		for (Map.Entry<String, WebDriver> driverEntry : driverMap.entrySet()) {
			if (driverEntry.getKey().contains(domain)) {
				System.out.println("driver: " + driverEntry.getValue());
				return driverEntry.getValue();
			}
		}
		return null;
	}

	private void updatePOMPages(String oldValue, String newValue) {
		String changedName = "";
		changedName = txtPageName.getText();
		if (changedName.isEmpty()) {

			cmbPageName.removeItem(oldValue);
		} else {
			if (null != oldValue) {
				System.out.println("Removing: " + oldValue);
				cmbPageName.removeItem(oldValue);
			}

			cmbPageName.addItem(newValue);
		}

	}

	public void updateDriverMapOnRefresh(String newUrl) {
		generateDriverMap(newUrl, driver1);
	}

	public void highlightInfo(JLabel label, String text, Color fgColor) {
		label.setVisible(true);
		label.setText(text);
		label.setForeground(fgColor);
		MessageTimer timer = new MessageTimer(label);
		timer.start();
	}

	public void successInfo(JLabel label, String text, Color fgColor) {
		label.setVisible(true);
		label.setText(text);
		label.setForeground(fgColor);
		MessageTimer timer = new MessageTimer(label);
		timer.start();
	}

	public void createNewPage(JTree tree) {
		if(flagNewPage==true)
		{
			DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
			String pageName="NewPage";
			if (null == model) {
				tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("JTree")));
				tree.setCellRenderer(new TreeNodeRenderer(nodeIcons));
			}
				
				DefaultTreeModel updatedModel = (DefaultTreeModel) tree.getModel();
				System.out.println("Model: " + tree.getModel().getRoot());
				DefaultMutableTreeNode root = (DefaultMutableTreeNode) updatedModel.getRoot();
				System.out.println("Root Node: " + root.getUserObject().toString());
				DefaultMutableTreeNode page1 = new DefaultMutableTreeNode(pageName);
				System.out.println("Node Child: " + root.getChildCount());
				updatedModel.insertNodeInto(page1, root, root.getChildCount());
				((DefaultTreeModel) (tree.getModel())).reload(root);
				if (null == pagesMap || pagesMap.isEmpty()) {
					pagesMap = new HashMap<>();
					objAttrMap = new HashMap<>();
				}
				pagesMap.put(page1.getUserObject().toString(), new HashMap<>());
				savedPages.put(page1.getUserObject().toString(), false);
				if (((DefaultComboBoxModel) cmbPageName.getModel()).getIndexOf(pageName) == -1) {
					cmbPageName.addItem(pageName);
				}
				System.out.println("New Node: " + page1.getUserObject().toString());
				count++;
			
			flagNewPage=false;
		}
		else {
		try {
			DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
			String pageName;
			if (null == model) {
				tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("JTree")));
				tree.setCellRenderer(new TreeNodeRenderer(nodeIcons));
			}
			
			AddNewPage obj = new AddNewPage(this);
			if (obj.isFlagNewPage() == true) {
				pageName = obj.getPageName();

				DefaultTreeModel updatedModel = (DefaultTreeModel) tree.getModel();
				System.out.println("Model: " + tree.getModel().getRoot());
				DefaultMutableTreeNode root = (DefaultMutableTreeNode) updatedModel.getRoot();
				System.out.println("Root Node: " + root.getUserObject().toString());
				DefaultMutableTreeNode page1 = new DefaultMutableTreeNode(pageName);
				System.out.println("Node Child: " + root.getChildCount());
				updatedModel.insertNodeInto(page1, root, root.getChildCount());
				((DefaultTreeModel) (tree.getModel())).reload(root);
				if (null == pagesMap || pagesMap.isEmpty()) {
					pagesMap = new HashMap<>();
					objAttrMap = new HashMap<>();
				}
				pagesMap.put(page1.getUserObject().toString(), new HashMap<>());
				savedPages.put(page1.getUserObject().toString(), false);
				if (((DefaultComboBoxModel) cmbPageName.getModel()).getIndexOf(pageName) == -1) {
					cmbPageName.addItem(pageName);
				}
				System.out.println("New Node: " + page1.getUserObject().toString());
				count++;
				obj.setFlagNewPage(false);
			} else {
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
	}

	private void importProject() {
		JFrame frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(SpyWeb.class.getResource(AppConstants.LOGOPATH)));
		JFileChooser chooser = new JFileChooser(new File("d:\\"));
		chooser.setDialogTitle("Select Sky file");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileFilter filt = new FileNameExtensionFilter("Sky File (*.sky)", "sky");
		chooser.setFileFilter(filt);
		if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
			String filename = chooser.getSelectedFile().getName().replaceAll(".sky", "");
			System.out.println("File ID: " + filename);
			String path = chooser.getSelectedFile().getAbsolutePath();
			System.out.println("Selected file to import: " + path);
			try {
				trPageExplorer.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("JTree")));
				this.updateTree(this.trPageExplorer,
						importService.parseToPagesData(project.getLocation(), project.getProjectName(), lblInfo, path));
				this.setTitle("UST iSkript - Web - " + path.replace(File.separator+"objectrepo"+File.separator+chooser.getSelectedFile().getName(), ""));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void openDocs() {
		URI uri = null;
		try {
			uri = new URI("https://ustqe.github.io/iSkript");
		} catch (URISyntaxException e) {
			e.printStackTrace();
			System.out.println("openDocs" + e.getMessage());
		}
		try {
			java.awt.Desktop.getDesktop().browse(uri);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("openDocs" + e.getMessage());
		}
		System.out.println("Web page opened in browser");
	}

	public void displayExportErroMessage(String message) {
		errorBox.setVisible(true);
		errorBox.setText(message);
		errorBox.setForeground(Color.RED);
		MessageTimer msgTimer = new MessageTimer(errorBox);
		msgTimer.start();
	}

	public void saveObjects() {
		if (null == pagesMap || pagesMap.isEmpty()) {
			
			displayErroMessage("Please save after adding pages & objects");
			return;
		}
		driver1 = getDriver(trPageExplorer);
		webElementService.serializeObjects(this.project, pagesMap, objAttrMap, lblInfo, attributeKey, attributeValue,
				driver1, savedPages, "Java", false);
		WebElementService.setFlagDelete(false);
	}

	public static void killDrivers() {
		if (isWindows()) {
			Process process;
			try {
				String line;
				String pidInfo = "";
				process = Runtime.getRuntime().exec(System.getenv("windir") + "/system32/" + "tasklist.exe");
				BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
				while ((line = input.readLine()) != null) {
					pidInfo += line;
				}
				input.close();
				if (pidInfo.contains("ChromeDriver.exe")) {
					Runtime.getRuntime().exec("taskkill /f /im ChromeDriver.exe");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static boolean isWindows() {

		return (OS.indexOf("win") >= 0);

	}

}
