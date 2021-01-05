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

package com.scripted.spy.web.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

public class MessageTimer implements ActionListener {

	private Timer timer;
	private JLabel label;

	public MessageTimer(JLabel label) {
		timer = new Timer(AppConstants.MESSAGETIMER, this);
		this.label = label;
	}

	public void start() {
		timer.start();
	}

	public void actionPerformed(ActionEvent e) {
		label.setVisible(false);
		((Timer) e.getSource()).stop();
	}

}
