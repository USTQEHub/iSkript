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

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import com.scripted.spy.web.service.SpyWeb;
import com.scripted.spy.web.ui.IconRepo;
import com.scripted.spy.web.util.AppConstants;

public class About extends JFrame {

	private JPanel contentPane;
	private static final String FONTNAME = "Segoe UI Semibold";
	private static final int FONTSIZE = 13;
	private static final int TITLEFONTSIZE = 25;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					About frame = new About();
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
	public About() {
		setBounds(100, 100, 572, 191);
		setTitle("About");
		setIconImage(Toolkit.getDefaultToolkit().getImage(SpyWeb.class.getResource(AppConstants.LOGOPATH)));
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.decode("#F2FBFF"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(10, 32, 72, 60);
		lblLogo.setIcon(IconRepo.ABOUTLOGO);
		contentPane.add(lblLogo);
		
		JLabel lblAbout = new JLabel("UST iSkript - Web");
		lblAbout.setBounds(96, 23, 199, 28);
		lblAbout.setFont(new Font(FONTNAME, Font.PLAIN, FONTSIZE));
		lblAbout.setVisible(true);
		contentPane.add(lblAbout);
		
		JTextPane lblVersion = new JTextPane();
		lblVersion.setText("Version: 1.0.0 (Base Version)");
		lblVersion.setFont(new Font(FONTNAME, Font.PLAIN, FONTSIZE));
		lblVersion.setBounds(96, 45, 234, 14);
		lblVersion.setEditable(false); 
		lblVersion.setBackground(null); 
		lblVersion.setBorder(null);
		contentPane.add(lblVersion);
		
		JTextPane lblCpoyright = new JTextPane();
		lblCpoyright.setText("Please reach out to us at assurance@ust.com for the full version.");
		lblCpoyright.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		lblCpoyright.setFont(new Font(FONTNAME, Font.PLAIN, FONTSIZE));
		lblCpoyright.setBounds(96, 85, 460, 20);
		lblCpoyright.setEditable(false); 
		lblCpoyright.setBackground(null); 
		lblCpoyright.setBorder(null);
		contentPane.add(lblCpoyright);
		
		JTextPane lblContact = new JTextPane();
		lblContact.setText("(c) Copyright UST. All rights reserved. https://www.ust.com");
		lblContact.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		lblContact.setFont(new Font(FONTNAME, Font.PLAIN, FONTSIZE));
		lblContact.setBounds(96, 105, 460, 43);
		lblContact.setEditable(false); 
		lblContact.setBackground(null); 
		lblContact.setBorder(null);
		contentPane.add(lblContact);
		
	}
}
