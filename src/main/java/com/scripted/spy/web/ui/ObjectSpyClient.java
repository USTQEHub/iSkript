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

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.scripted.spy.web.model.ObjectRepository;
import com.scripted.spy.web.model.Project;
import com.scripted.spy.web.model.WebOR;
import com.scripted.spy.web.model.WebORPage;
import com.scripted.spy.web.service.JSONReader;
import com.scripted.spy.web.service.SpyWeb;
import com.scripted.spy.web.util.AppConstants;
import com.scripted.spy.web.util.ColorCode;
import com.scripted.spy.web.util.MessageTimer;
import com.scripted.spy.web.util.RepositoryConstants;
import com.scripted.spy.web.util.ValidatorUtil;

public class ObjectSpyClient extends JFrame {

	public static String projectPath = null;
	private static final Logger logger = LogManager.getLogger(ObjectSpyClient.class);
	private static final long serialVersionUID = 1L;
	private static final String PROJECTNAMEERRORMSG = "Please select a name without special characters";
	public JLabel lblpath;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private final ButtonGroup projectTypeGroup = new ButtonGroup();
	private final ButtonGroup technologyGroup = new ButtonGroup();
	private JFileChooser chooser;
	private Project project;
	private JLabel lblMan;
	private JLabel lblMan_1;
	private JRadioButton rdbtnNew;
	private JRadioButton rdbtnExisting;
	public static JLabel lblExistInfo;
	private Border border = BorderFactory.createLineBorder(Color.red, 1);
	private static final String BFONTNAME = "Segoe UI Bold";
	private static final String FONTNAME = "Segoe UI";
	private static final int FONTSIZE = 16;
	private static final int DEFAULTFONTSIZE = 16;
	private static final int TITLEFONTSIZE = 15;
	private String projectName;
	private String projectLocation;
	private JLabel lblInfo;
	private JTextField txtExistingLocation;
	private ValidatorUtil validatorUtil = new ValidatorUtil();
	private JSONReader jsonReader = new JSONReader();
	private boolean isWeb = true;
	private String importedFileParent;
	public static String projectLocalPath;

	public static String getProjectLocalPath() {
		return projectLocalPath;
	}

	public void setProjectLocalPath(String projectLocalPath) {
		this.projectLocalPath = projectLocalPath;
	}

	public static void main(String[] args) {

		try {
			String path = ObjectSpyClient.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()
					.substring(1).replace("/target/classes/", "");
			int index = path.lastIndexOf('/');
			path = path.substring(0, index);
			System.out.println(path);
			path = path + "/browserdrivers";
			Path projectPath = Paths.get(path);
			if (!Files.exists(projectPath)) {
				new File(path).mkdir();
				System.out.println("Browerdriver folder is created");
			}
			String chrome = path + "/chrome";
			Path chromePath = Paths.get(chrome);
			if (!Files.exists(chromePath)) {
				new File(chrome).mkdir();
				System.out.println("Chrome folder is created");
			}
			showSplashScreen();
			generateFrame();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	public static void generateFrame() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ObjectSpyClient frame = new ObjectSpyClient();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ObjectSpyClient() {
		setTitle("iSpy");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 606, 340);
		setBackground(Color.WHITE);
		setResizable(false);
		setLocationRelativeTo(null);
		Image icon = Toolkit.getDefaultToolkit().getImage(ObjectSpyClient.class.getResource(AppConstants.LOGOPATH));
		setIconImage(icon);
		// setMaximizedBounds(new Rectangle(100, 100, 606, 340));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setBackground(Color.decode("#F2FBFF"));
		contentPane.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(null);
		panel_1.setBounds(48, 83, 500, 157);
		panel_1.setBackground(Color.decode(ColorCode.PANELBG.getCode()));
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		rdbtnNew = new JRadioButton("New Project");
		rdbtnNew.setBounds(150, 40, 150, 23);
		rdbtnNew.setBackground(Color.decode(ColorCode.PANELBG.getCode()));
		rdbtnNew.setFont(new Font(FONTNAME, Font.BOLD, TITLEFONTSIZE));
		projectTypeGroup.add(rdbtnNew);
		contentPane.add(rdbtnNew);
		rdbtnNew.setSelected(true);
		rdbtnNew.addActionListener(e -> {
			if (rdbtnNew.isSelected())
				renderNewWindow(e, panel_1);
		});

		generateNewPanelComponents(panel_1);

		rdbtnExisting = new JRadioButton("Existing Project");
		rdbtnExisting.setBounds(330, 40, 140, 23);
		rdbtnExisting.setFont(new Font(FONTNAME, Font.BOLD, TITLEFONTSIZE));
		rdbtnExisting.setBackground(Color.decode(ColorCode.PANELBG.getCode()));
		rdbtnExisting.addActionListener(e -> {
			if (rdbtnExisting.isSelected())
				renderExistingWindow(e, panel_1);
		});
		projectTypeGroup.add(rdbtnExisting);
		contentPane.add(rdbtnExisting);

		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(Color.decode(ColorCode.PANELBG.getCode()));
		panel.setBounds(48, 30, 500, 40);

		contentPane.add(panel);

		JButton btnOk = new JButton("Ok");
		btnOk.setBounds(205, 250, 89, 28);
		btnOk.setFont(new Font(BFONTNAME, Font.PLAIN, FONTSIZE));
		btnOk.setBackground(Color.decode(ColorCode.HAPPYBUTTON.getCode()));
		btnOk.setBorder(null);
		btnOk.setForeground(Color.decode(ColorCode.PANELBG.getCode()));
		btnOk.setEnabled(true);
		btnOk.addActionListener(e -> openSpyWindow());
		contentPane.add(btnOk);

		btnOk.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnOk.setBackground(Color.decode(ColorCode.BUTTONHOVER.getCode()));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnOk.setBackground(Color.decode(ColorCode.HAPPYBUTTON.getCode()));
			}
		});

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textField_1.setText("");
				if (null != txtExistingLocation)
					txtExistingLocation.setText("");
			}
		});
		btnCancel.setBounds(303, 250, 89, 28);
		btnCancel.setBorder(null);
		btnCancel.setFont(new Font(BFONTNAME, Font.PLAIN, FONTSIZE));
		btnCancel.setBackground(Color.decode(ColorCode.TEXT.getCode()));
		btnCancel.setForeground(Color.decode(ColorCode.PANELBG.getCode()));
		btnCancel.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnCancel.setBackground(Color.decode("#4b5b6e"));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnCancel.setBackground(Color.decode(ColorCode.TEXT.getCode()));
			}
		});
		contentPane.add(btnCancel);
	}

	private void invokeSpyWeb(File dir, String projectLocation, boolean isNewWindow, String importFilename)
			throws Exception {
		if (!isNewWindow) {
			String pageUrl = project.getPageUrl();
			project.setPageUrl(pageUrl);
		}
		projectLocalPath = projectLocation;
		projectPath = textField_1.getText();
		int index = projectPath.lastIndexOf('\\');
		projectPath = projectPath.substring(0, index);
		System.out.println("Project Location: " + textField_1.getText() + File.separator + textField.getText());
		if (isNewWindow) {
			project = new Project();
			boolean isDirCreated = dir.mkdirs();
			System.out.println("Directory created: " + isDirCreated + "Location: " + projectLocation);
			if (isDirCreated) {

				File objRepo = new File(
						projectLocation + File.separator + RepositoryConstants.OBJECTREPOSITORY.getValue());
				File pageObjRepo = new File(
						projectLocation + File.separator + RepositoryConstants.PAGEOBJECTREPOSITORY.getValue());
				objRepo.mkdir();
				pageObjRepo.mkdir();
				projectPath = projectLocation + File.separator + RepositoryConstants.OBJECTREPOSITORY.getValue();

			}
		}
		dispose();
		project.setProjectName(textField.getText());
		System.out.println("Project Name :" + textField.getText());
		project.setLocation(projectLocation);
		project.setImportFileLocation(importFilename);
		if (isWeb) {
			SpyWeb spyWeb = new SpyWeb(project, isNewWindow);
			spyWeb.invokeSpyWeb(project, importFilename);
		}
	}

	public static String getProjectPath() {
		return projectPath;
	}

	public static void setProjectPath(String projectPath) {
		ObjectSpyClient.projectPath = projectPath;
	}

	private void renderNewWindow(ActionEvent e, JPanel panel_1) {
		panel_1.removeAll();
		panel_1.revalidate();
		panel_1.repaint();
		generateNewPanelComponents(panel_1);
	}

	private void generateNewPanelComponents(JPanel panel_1) {
		JLabel lblEnterProjectInformation = new JLabel("Enter Project Information");
		lblEnterProjectInformation.setFont(new Font(FONTNAME, Font.BOLD, 16));
		lblEnterProjectInformation.setBounds(20, 13, 200, 23);
		lblEnterProjectInformation.setBackground(Color.decode(ColorCode.TEXT.getCode()));
		panel_1.add(lblEnterProjectInformation);

		JLabel lblName = new JLabel("Project Name");
		lblName.setFont(new Font(FONTNAME, Font.PLAIN, DEFAULTFONTSIZE));
		lblName.setBounds(20, 48, 95, 20);
		lblName.setBackground(Color.decode(ColorCode.TEXT.getCode()));
		panel_1.add(lblName);

		textField = new JTextField();
		textField.setBounds(142, 48, 225, 28);
		textField.setFont(new Font(FONTNAME, Font.PLAIN, DEFAULTFONTSIZE));
		textField.setBackground(Color.decode(ColorCode.INPUTBACKGROUND.getCode()));
		textField.setBorder(null);
		if (null != projectName) {
			textField.setText(projectName);
		}
		textField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE || e.getKeyChar() == KeyEvent.VK_DELETE) {
					return;
				}
				if (validatorUtil.isProjectNameValid(String.valueOf(e.getKeyChar()))) {
					displayErrorMessage(PROJECTNAMEERRORMSG);
					return;
				} else {
					textField.setBorder(null);
				}
			}
		});

		panel_1.add(textField);
		textField.setColumns(10);

		JLabel projectTooltip = new JLabel();
		projectTooltip.setBounds(370, 55, 14, 14);
		projectTooltip.setToolTipText("Special Characters are not allowed.");
		projectTooltip.setIcon(IconRepo.INFO);
		panel_1.add(projectTooltip);

		lblMan = new JLabel("*");
		lblMan.setFont(new Font(FONTNAME, Font.PLAIN, DEFAULTFONTSIZE));
		lblMan.setBounds(115, 50, 10, 10);
		lblMan.setForeground(Color.red);
		panel_1.add(lblMan);

		JLabel lblTechnology = new JLabel("Technology");
		lblTechnology.setFont(new Font(FONTNAME, Font.PLAIN, DEFAULTFONTSIZE));
		lblTechnology.setBounds(20, 77, 90, 22);
		lblTechnology.setBackground(Color.decode(ColorCode.TEXT.getCode()));
		panel_1.add(lblTechnology);

		ButtonGroup techGroup = new ButtonGroup();

		JRadioButton rdbtnWeb = new JRadioButton("Web");
		rdbtnWeb.setBounds(142, 80, 60, 20);
		rdbtnWeb.setFont(new Font(FONTNAME, Font.PLAIN, DEFAULTFONTSIZE));
		rdbtnWeb.setBackground(Color.decode(ColorCode.PANELBG.getCode()));
		rdbtnWeb.setEnabled(true);
		rdbtnWeb.setSelected(true);
		techGroup.add(rdbtnWeb);
		panel_1.add(rdbtnWeb);

		JLabel thumb = new JLabel("");
		thumb.setBounds(400, 25, 70, 70);
		thumb.setForeground(Color.red);
		panel_1.add(thumb);

		if (rdbtnWeb.isSelected()) {
			thumb.setIcon(IconRepo.WEB);
		}

		rdbtnWeb.addActionListener(e -> setWebImage(thumb));

		JLabel lblLocation = new JLabel("Location");
		lblLocation.setFont(new Font(FONTNAME, Font.PLAIN, DEFAULTFONTSIZE));
		lblLocation.setBounds(20, 107, 75, 20);
		lblLocation.setBackground(Color.decode(ColorCode.TEXT.getCode()));
		panel_1.add(lblLocation);

		lblInfo = new JLabel("Info");
		lblInfo.setFont(new Font(FONTNAME, Font.PLAIN, 15));
		lblInfo.setBounds(20, 133, 450, 20);
		lblInfo.setBackground(Color.decode(ColorCode.TEXT.getCode()));
		lblInfo.setVisible(false);
		panel_1.add(lblInfo);

		textField_1 = new JTextField();
		textField_1.setFont(new Font(FONTNAME, Font.PLAIN, DEFAULTFONTSIZE));
		textField_1.setBounds(142, 105, 225, 28);
		textField_1.setBorder(null);
		textField_1.setEnabled(false);
		textField_1.setBackground(Color.decode(ColorCode.INPUTBACKGROUND.getCode()));

		if (null != projectLocation) {
			textField_1.setText(projectLocation);
		}
		panel_1.add(textField_1);
		textField_1.setColumns(10);

		lblMan_1 = new JLabel("*");
		lblMan_1.setFont(new Font(FONTNAME, Font.PLAIN, DEFAULTFONTSIZE));
		lblMan_1.setBounds(80, 110, 10, 10);
		lblMan_1.setForeground(Color.red);
		panel_1.add(lblMan_1);

		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setFont(new Font(BFONTNAME, Font.PLAIN, DEFAULTFONTSIZE));
		btnBrowse.setBounds(387, 105, 95, 28);
		btnBrowse.setBackground(Color.decode(ColorCode.HAPPYBUTTON.getCode()));
		btnBrowse.setForeground(Color.decode(ColorCode.PANELBG.getCode()));
		btnBrowse.setIcon(IconRepo.BROWSE);
		btnBrowse.setBorder(null);
		panel_1.add(btnBrowse);
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnBrowse) {
					chooser = new JFileChooser(new File("d:\\"));
					chooser.setDialogTitle("Select Location");
					chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					chooser.setAcceptAllFileFilterUsed(false);
					if (chooser.showSaveDialog(btnBrowse) == JFileChooser.APPROVE_OPTION) {
						textField_1.setBorder(null);
						String fileID = chooser.getSelectedFile().getPath();
						System.out.println("File Location: " + fileID);
						textField_1.setText(fileID);
						textField_1.setToolTipText(fileID);

					}
				}
			}
		});

		btnBrowse.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnBrowse.setBackground(Color.decode(ColorCode.BUTTONHOVER.getCode()));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnBrowse.setBackground(Color.decode(ColorCode.HAPPYBUTTON.getCode()));
			}
		});
	}

	private void setWebImage(JLabel thumb) {
		isWeb = true;
		thumb.setIcon(IconRepo.WEB);
	}

	private Object renderExistingWindow(ActionEvent e, JPanel panel_1) {
		projectName = textField.getText();
		projectLocation = textField_1.getText();
		panel_1.removeAll();
		panel_1.revalidate();
		panel_1.repaint();

		ButtonGroup technologyGroup = new ButtonGroup();

		JLabel lblEnterProjectInformation = new JLabel("Select the File Location");
		lblEnterProjectInformation.setFont(new Font(FONTNAME, Font.BOLD, 16));
		lblEnterProjectInformation.setBounds(20, 15, 200, 23);
		lblEnterProjectInformation.setBackground(Color.decode(ColorCode.TEXT.getCode()));
		panel_1.add(lblEnterProjectInformation);

		JLabel lblTechnology = new JLabel("File Location");
		lblTechnology.setFont(new Font(FONTNAME, Font.PLAIN, DEFAULTFONTSIZE));
		lblTechnology.setBounds(20, 57, 100, 20);
		lblTechnology.setBackground(Color.decode(ColorCode.TEXT.getCode()));
		panel_1.add(lblTechnology);

		lblMan = new JLabel("*");
		lblMan.setFont(new Font(FONTNAME, Font.PLAIN, DEFAULTFONTSIZE));
		lblMan.setBounds(108, 57, 10, 10);
		lblMan.setForeground(Color.red);
		panel_1.add(lblMan);
		
		lblExistInfo = new JLabel("Info");
		lblExistInfo.setFont(new Font(FONTNAME, Font.PLAIN, DEFAULTFONTSIZE));
		lblExistInfo.setBounds(20, 128, 450, 20);
		lblExistInfo.setBackground(Color.decode(ColorCode.TEXT.getCode()));
		lblExistInfo.setVisible(false);
		panel_1.add(lblExistInfo);

		txtExistingLocation = new JTextField();
		txtExistingLocation.setFont(new Font(FONTNAME, Font.PLAIN, DEFAULTFONTSIZE));
		txtExistingLocation.setBounds(125, 55, 235, 28);
		txtExistingLocation.setBorder(null);
		txtExistingLocation.setEnabled(false);
		txtExistingLocation.setBackground(Color.decode(ColorCode.INPUTBACKGROUND.getCode()));
		/*
		 * if (null != projectLocation) { txtExistingLocation.setText(projectLocation);
		 * }
		 */
		panel_1.add(txtExistingLocation);
		txtExistingLocation.setColumns(10);

		getBrowseButton(panel_1);

		JLabel lblName = new JLabel("Project Name");
		lblName.setFont(new Font(FONTNAME, Font.PLAIN, DEFAULTFONTSIZE));
		lblName.setBounds(20, 102, 150, 20);
		lblName.setBackground(Color.decode(ColorCode.TEXT.getCode()));
		panel_1.add(lblName);

		textField = new JTextField();
		textField.setFont(new Font(FONTNAME, Font.PLAIN, DEFAULTFONTSIZE));
		textField.setBounds(125, 100, 235, 28);
		textField.setBackground(Color.decode(ColorCode.INPUTBACKGROUND.getCode()));
		textField.setBorder(null);
		textField.setEditable(false);
		panel_1.add(textField);
		textField.setColumns(10);

		JRadioButton rdbtnWeb_1 = new JRadioButton("Web");
		rdbtnWeb_1.setFont(new Font(FONTNAME, Font.PLAIN, DEFAULTFONTSIZE));
		rdbtnWeb_1.setBounds(112, 105, 60, 20);
		rdbtnWeb_1.setBackground(Color.decode(ColorCode.PANELBG.getCode()));
		technologyGroup.add(rdbtnWeb_1);
		rdbtnWeb_1.setSelected(true);
		JLabel thumb = new JLabel("");
		thumb.setBounds(370, 78, 81, 80);
		thumb.setForeground(Color.red);
		if (rdbtnWeb_1.isSelected()) {
			thumb.setIcon(IconRepo.WEB);
		}
		lblInfo = new JLabel("Info");
		lblInfo.setFont(new Font(FONTNAME, Font.PLAIN, DEFAULTFONTSIZE));
		lblInfo.setBounds(37, 125, 450, 20);
		lblInfo.setBackground(Color.decode(ColorCode.TEXT.getCode()));
		lblInfo.setVisible(false);
		panel_1.add(lblInfo);
		
		
		return null;
	}

	private JButton getBrowseButton(JPanel panel_1) {
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setFont(new Font(FONTNAME, Font.BOLD, DEFAULTFONTSIZE));
		btnBrowse.setBounds(382, 55, 95, 28);
		btnBrowse.setBackground(Color.decode(ColorCode.HAPPYBUTTON.getCode()));
		btnBrowse.setForeground(Color.decode(ColorCode.PANELBG.getCode()));
		btnBrowse.setIcon(IconRepo.BROWSE);
		btnBrowse.setBorder(null);
		btnBrowse.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnBrowse.setBackground(Color.decode(ColorCode.BUTTONHOVER.getCode()));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnBrowse.setBackground(Color.decode(ColorCode.HAPPYBUTTON.getCode()));
			}
		});

		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnBrowse) {
					chooser = new JFileChooser(new File("d:\\"));
					chooser.setDialogTitle("Select Sky File");
					chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
					FileFilter filt = new FileNameExtensionFilter("Sky File(*.sky)", "sky");
					chooser.setFileFilter(filt);
					if (chooser.showOpenDialog(btnBrowse) == JFileChooser.APPROVE_OPTION) {
						textField.setBorder(null);
						txtExistingLocation.setBorder(null);
						System.out.println("Parent Path:" + chooser.getSelectedFile().getParent());
						importedFileParent = chooser.getSelectedFile().getParent();
						String fileID = chooser.getSelectedFile().getName().replaceAll(".sky", "");
						System.out.println("File Location: " + fileID);
						textField.setText(fileID);
						String path = chooser.getSelectedFile().getAbsolutePath();
						textField_1.setText(path);
						txtExistingLocation.setText(path);
						txtExistingLocation.setToolTipText(path);
						System.out.println(path);
						btnBrowse.addActionListener(e1 -> openSpyWindow());
						try {
							jsonReader.readJson(path);
							project = jsonReader.readJson(path);
							ObjectRepository repo = null;
							WebOR webOR = null;
							WebORPage page = null;
							List<WebORPage> pages = null;
							repo = project.getObjectRepository();
							webOR = repo.getWebOR();
							pages = webOR.getPages();
							System.out.println("******" + pages);
							if (pages != null) {
								JLabel thumb = new JLabel("");
								thumb.setBounds(405, 82, 100, 70);
								thumb.setForeground(Color.red);
								panel_1.add(thumb);
								thumb.setIcon(IconRepo.EXWEB);
							}
						} catch (IOException e2) {
							e2.printStackTrace();
						}

					}
				}
			}
		});

		panel_1.add(btnBrowse);
		panel_1.repaint();
		panel_1.revalidate();
		return btnBrowse;
	}

	public void openSpyWindow() {
		String projectLocation = textField_1.getText() + File.separator + textField.getText();
		File dir = new File(projectLocation);

		String PName = textField.getText();
		if (rdbtnNew.isSelected()) {
			if (textField.getText().trim().length() != 0 && textField_1.getText().trim().length() != 0) {
				try {
					if (dir.exists()) {
						lblInfo.setVisible(true);
						lblInfo.setForeground(Color.RED);
						lblInfo.setText(textField.getText() + " project already exist.");
						MessageTimer msgTimer = new MessageTimer(lblInfo);
						msgTimer.start();
					} else {
						System.out.println("Given Text: " + textField.getText());
						if (!validatorUtil.isProjectNameValid(textField.getText())) {
							invokeSpyWeb(dir, projectLocation, true, null);
						} else {
							displayErrorMessage(PROJECTNAMEERRORMSG);
						}

					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Unable to invoke Spyweb");
				}
			} else {
				if (textField_1.getText().trim().length() != 0) {
					textField.setBorder(border);
					displayErrorMessage("Please enter the project name");
				} else if (textField.getText().trim().length() != 0)
				{
					textField_1.setBorder(border);
					displayErrorMessage("Please select a location");
				}
				else {
					textField.setBorder(border);
					textField_1.setBorder(border);
					displayErrorMessage("Please enter the project name and location");
				}
			}
		}

		else if (rdbtnExisting.isSelected()) {
			if (textField.getText().trim().length() != 0) {
				try {
					System.out.println(
							"Txt: " + textField.getText() + " Existing Text: " + txtExistingLocation.getText());
					projectLocation = importedFileParent.replace(RepositoryConstants.OBJECTREPOSITORY.getValue(), "");
					System.out.println("Project Location: " + projectLocation);
					if (!importedFileParent.contains(RepositoryConstants.OBJECTREPOSITORY.getValue())) {
						displayErrorMessage("Please select a valid *.sky file");
						return;
					}
					String importedFileLocation = txtExistingLocation.getText();
					try {
						jsonReader.readJson(importedFileLocation);
						System.out.println("import file loc" + importedFileLocation);
						System.out.println("page url while opening spy:" + project.getPageUrl());
					} catch (Exception e) {
						displayErrorMessage("Please select *.sky file");
						e.printStackTrace();
						return;
					}

					invokeSpyWeb(dir, projectLocation, false, importedFileLocation);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Unable to invoke SpyWeb");
				}
			} else {
				txtExistingLocation.setBorder(border);
				lblExistInfo.setVisible(true);
				lblExistInfo.setForeground(Color.RED);
				lblExistInfo.setText("Please select a location");
				MessageTimer msgTimer = new MessageTimer(lblExistInfo);
				msgTimer.start();
			}
		}
	}

	public void displayErrorMessage(String message) {
		lblInfo.setVisible(true);
		lblInfo.setForeground(Color.RED);
		lblInfo.setText(message);
		MessageTimer msgTimer = new MessageTimer(lblInfo);
		msgTimer.start();
	}

	private String getProjectLocation(String importedFilePath, String projectName) {
		if (null != importedFilePath) {
			String b[] = importedFilePath.split("\\\\");
			return b[0] + "\\\\" + projectName;
		}
		return null;
	}

	private String getProjectLocation() {
		StringBuilder projectPath = new StringBuilder();
		String s = "D:\\docs\\selfhealing\\selfhealing.json";
		String split[] = s.split("\\\\");
		String splitter = "\\";
		for (int i = 0; i < split.length - 1; i++) {
			if (i == split.length - 2) {
				splitter = "";
			}
			projectPath.append(split[i] + splitter);
		}
		return projectPath.toString();
	}

	public static void showSplashScreen() {
		JWindow splashScreen = new JWindow();
		Icon image = IconRepo.SPLASHSCREEN;
		JLabel label = new JLabel(image);
		label.setSize(596, 320);
		Container splashContent = splashScreen.getContentPane();
		splashContent.add(label);
		splashScreen.setBounds(750, 350, 500, 284);
		splashScreen.setVisible(true);
		splashScreen.setLocationRelativeTo(null);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		splashScreen.setVisible(false);
	}

}
