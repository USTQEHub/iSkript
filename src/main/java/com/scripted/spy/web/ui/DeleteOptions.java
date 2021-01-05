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
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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

import com.scripted.spy.web.service.SpyWeb;
import com.scripted.spy.web.util.ColorCode;

public class DeleteOptions {

	public SpyWeb spyWeb;

	private static JButton btnYes;
	private static JButton btnNo;
	private static JLabel lblInfo;
	private static JPanel contentPane;
	private static final int BUTTONFONTSIZE = 13;
	private static final String FONTNAME = "Calibri";
	private static final int TITLEFONTSIZE = 15;
	private static final int DEFAULTFONTSIZE = 14;
	public boolean deleteFlag = false;

	public DeleteOptions(SpyWeb spyWeb, String objectType, String selectedObject) throws IOException {
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
		mainPanel.setBounds(5, 2, 320, 130);
		mainPanel.setBackground(Color.decode(ColorCode.PANELBG.getCode()));
		contentPane.add(mainPanel);
		mainPanel.setLayout(null);
		
		if (objectType.equalsIgnoreCase("page")) {
			lblInfo = new JLabel("Are you sure you want to delete the page?");
		} else {
			lblInfo = new JLabel("Are you sure you want to delete the object?");
		}
		lblInfo.setFont(new Font(FONTNAME, Font.BOLD, DEFAULTFONTSIZE));
		lblInfo.setBounds(30, 20, 500, 23);
		lblInfo.setBackground(Color.decode(ColorCode.TEXT.getCode()));
		mainPanel.add(lblInfo);

		btnYes = new JButton("Yes");
		btnYes.setBounds(75, 60, 88, 23);
		btnYes.setFont(new Font(FONTNAME, Font.PLAIN, BUTTONFONTSIZE));
		btnYes.setBackground(Color.decode(ColorCode.HAPPYBUTTON.getCode()));
		btnYes.setForeground(Color.decode(ColorCode.BUTTONTEXT.getCode()));
		btnYes.setBorder(null);
		btnYes.setEnabled(true);
		btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
				deleteFlag = true;
			}
		});
		mainPanel.add(btnYes);

		btnNo = new JButton("No");
		btnNo.setBounds(175, 60, 88, 23);
		btnNo.setBorder(null);
		btnNo.setFont(new Font(FONTNAME, Font.BOLD, BUTTONFONTSIZE));
		btnNo.setBackground(Color.decode(ColorCode.TEXT.getCode()));
		btnNo.setForeground(Color.decode(ColorCode.PANELBG.getCode()));
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object source = e.getSource();
				if (source == btnNo) {
					System.out.println("cancel");
					deleteFlag = false;
					JComponent comp = (JComponent) e.getSource();
					Window win = SwingUtilities.getWindowAncestor(comp);
					win.dispose();
				}
			}
		});
		mainPanel.add(btnNo);

		JOptionPane jop = new JOptionPane(contentPane, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, null,
				new Object[] {}, null);
		jop.setPreferredSize(new Dimension(350, 130));
		
		
		JDialog dialog = jop.createDialog("Delete");
		Image image = ImageIO.read(IconRepo.class.getResource("/icons/sk_spyweb_logo2x.png"));
		dialog.setIconImage(image);
		dialog.setVisible(true);
		dialog.setAlwaysOnTop(true);
		dialog.setAutoRequestFocus(false);
		/*dialog.setModal(true);
		dialog.setModalityType(ModalityType.APPLICATION_MODAL);*/
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

	public boolean isDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	private void setContentPane(JPanel contentPane2) {
		// TODO Auto-generated method stub

	}

}
