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
import java.awt.Font;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.scripted.spy.web.model.Attribute;
import com.scripted.spy.web.ui.IconRepo;
import com.scripted.spy.web.util.AppConstants;
import com.scripted.spy.web.util.ColorCode;
import com.scripted.spy.web.util.MessageTimer;

public class RenameObject {
	private static String NewObjectName;
	private static JButton btnOk;
	private static JButton btnCancel;
	private static JLabel errorBox;
	private static JPanel contentPane;
	private Border border = BorderFactory.createLineBorder(Color.GRAY, 1);
	private Border redBorder = BorderFactory.createLineBorder(Color.RED, 1);
	private static final int BUTTONFONTSIZE = 12;
	private static final String FONTNAME = "Calibri";
	private static final int DEFAULTFONTSIZE = 14;
	public SpyWeb spyWeb;
	public JTextField txtFileName = new JTextField();
	public static boolean flagRenameObject = false;

	public RenameObject(SpyWeb spyWeb, String oldObjectName) throws IOException {
		String keptVal=oldObjectName.substring( 0, oldObjectName.indexOf("_"));
		String remainingVal=oldObjectName.substring(oldObjectName.lastIndexOf("_") + 1);
		System.out.println("Exixting Object Name : "+remainingVal);
		this.spyWeb = spyWeb;
		UIManager UI = new UIManager();
		UI.put("OptionPane.background", Color.decode("#F2FBFF"));
		UI.put("Panel.background", Color.decode("#F2FBFF"));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.decode("#F2FBFF"));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(null);
		mainPanel.setBounds(5, 2, 370, 130);
		mainPanel.setBackground(Color.decode(ColorCode.PANELBG.getCode()));
		contentPane.add(mainPanel);
		mainPanel.setLayout(null);

		errorBox = new JLabel("");
		errorBox.setFont(new Font(FONTNAME, Font.PLAIN, DEFAULTFONTSIZE));
		errorBox.setBounds(15, 35, 500, 23);
		errorBox.setBackground(Color.decode(ColorCode.TEXT.getCode()));
		mainPanel.add(errorBox);

		JLabel lblFileName = new JLabel("Object Name");
		lblFileName.setFont(new Font(FONTNAME, Font.BOLD, DEFAULTFONTSIZE));
		lblFileName.setBounds(15, 12, 170, 20);
		lblFileName.setBackground(Color.decode(ColorCode.TEXT.getCode()));
		mainPanel.add(lblFileName);

		txtFileName.setFont(new Font(FONTNAME, Font.PLAIN, DEFAULTFONTSIZE));
		txtFileName.setBounds(100, 12, 210, 20);
		txtFileName.setBackground(Color.decode(ColorCode.INPUTBACKGROUND.getCode()));
		txtFileName.setBorder(border);
		txtFileName.setFont(new Font(FONTNAME, Font.PLAIN, 14));
		txtFileName.setEditable(true);
		txtFileName.setText(remainingVal);
		mainPanel.add(txtFileName);

		btnOk = new JButton("Ok");
		btnOk.setBounds(75, 55, 88, 23);
		btnOk.setFont(new Font(FONTNAME, Font.PLAIN, BUTTONFONTSIZE));
		btnOk.setBackground(Color.decode(ColorCode.HAPPYBUTTON.getCode()));
		btnOk.setForeground(Color.decode(ColorCode.BUTTONTEXT.getCode()));
		btnOk.setBorder(null);
		btnOk.setEnabled(true);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtFileName.getText().trim().isEmpty()) {
					txtFileName.setBorder(redBorder);
					errorBox.setBounds(60, 35, 500, 23);
					displayErroMessage("The Object name field cannot be empty");
				} else {
					txtFileName.setBorder(border);
					NewObjectName = txtFileName.getText();
					if (NewObjectName.length() >= AppConstants.OBJECTNAMELIMIT) {
						errorBox.setBounds(30, 35, 500, 23);
						displayErroMessage("You can enter only a maximum of 15 characters");
					} else if (isObjectNameValid(NewObjectName)) {
						txtFileName.setBorder(redBorder);
						errorBox.setBounds(50, 35, 500, 23);
						displayErroMessage("Please remove special characters/numbers");

					}else if (NewObjectName.equalsIgnoreCase(remainingVal.trim())) {
						JComponent comp = (JComponent) e.getSource();
						Window win = SwingUtilities.getWindowAncestor(comp);
						win.dispose();
						flagRenameObject = false;
					} else {
						boolean flagDuplicate = false;
						Map<String, Attribute> ObjMap = SpyWeb.getObjAttrMap();
						System.out.println("PageMap :"+ObjMap);
						if(ObjMap!=null) {
						for (Entry<String, Attribute> obj : ObjMap.entrySet()) {
							String key = obj.getKey();
							Attribute value = obj.getValue();
							System.out.println("key :" + key);
							System.out.println("Value :" + value);
							if (key.equalsIgnoreCase(oldObjectName)) {
								errorBox.setBounds(15, 35, 500, 23);
								displayErroMessage("Object name already exists. Please enter a new name");
								flagDuplicate = true;
								flagRenameObject = false;
								return;
							}
						}
						}
						if (flagDuplicate == false) {
							JComponent comp = (JComponent) e.getSource();
							Window win = SwingUtilities.getWindowAncestor(comp);
							win.dispose();
							flagRenameObject = true;
						}
						NewObjectName=NewObjectName.replaceAll(" ", "");
						NewObjectName=keptVal+"_"+NewObjectName;
					}
				}

			}
		});
		mainPanel.add(btnOk);

		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(175, 55, 88, 23);
		btnCancel.setBorder(null);
		btnCancel.setFont(new Font(FONTNAME, Font.BOLD, BUTTONFONTSIZE));
		btnCancel.setBackground(Color.decode(ColorCode.TEXT.getCode()));
		btnCancel.setForeground(Color.decode(ColorCode.PANELBG.getCode()));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object source = e.getSource();
				if (source == btnCancel) {
					System.out.println("cancel");
					JComponent comp = (JComponent) e.getSource();
					Window win = SwingUtilities.getWindowAncestor(comp);
					win.dispose();
					flagRenameObject = false;
				}
			}
		});
		mainPanel.add(btnCancel);

		JOptionPane jop = new JOptionPane(contentPane, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, null,
				new Object[] {}, null);
		jop.setPreferredSize(new Dimension(350, 130));

		JDialog dialog = jop.createDialog("Rename Object");
		Image image = ImageIO.read(IconRepo.class.getResource("/icons/sk_spyweb_logo2x.png"));
		dialog.setIconImage(image);
		dialog.setVisible(true);
		dialog.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				System.out.println("jdialog window closed event received");
			}

			public void windowClosing(WindowEvent e) {
				System.out.println("jdialog window closing event received");
			}
		});
		dialog.setDefaultCloseOperation(dialog.DO_NOTHING_ON_CLOSE);
	}

	public static boolean isFlagNewObject() {
		return flagRenameObject;
	}

	public static void setFlagNewObject(boolean flagRenameObject) {
		RenameObject.flagRenameObject = flagRenameObject;
	}

	public static String getNewObjectName() {
		return NewObjectName;
	}

	public static void setObjectName(String objectName) {
		RenameObject.NewObjectName = objectName;
	}

	private void setContentPane(JPanel contentPane2) {
		// TODO Auto-generated method stub

	}

	public void displayErroMessage(String message) {
		errorBox.setVisible(true);
		errorBox.setText(message);
		errorBox.setForeground(Color.RED);
		MessageTimer msgTimer = new MessageTimer(errorBox);
		msgTimer.start();
	}

	public boolean isObjectNameValid(String objectName) {
		Pattern p = Pattern.compile("[^a-zA-Z ]", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(objectName);
		boolean b = m.find();
		System.out.println("Flag :" + b);
		if (b)
			System.out.println("There is a special character in my string");
		return b;
	}
}
