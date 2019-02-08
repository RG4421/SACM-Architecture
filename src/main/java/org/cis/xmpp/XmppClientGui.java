package org.cis.xmpp;

import javax.swing.*;

public class XmppClientGui extends JFrame {
	private JPanel rootPanel;
	private JLabel toLabel;
	private JTextField toTextField;
	private JTextArea messageTextArea;
	private JButton sendMessageBtn;

	public XmppClientGui() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		add(rootPanel);

		setTitle("XMPP Chat");
		setSize(636, 589);
	}
}
