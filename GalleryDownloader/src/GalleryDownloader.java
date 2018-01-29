import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;

public class GalleryDownloader extends JFrame implements ActionListener {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GalleryDownloader frame = new GalleryDownloader();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Initialize the components
	 */
	public GalleryDownloader() {
		// Create the frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		// Create a label
		JLabel domainLabel = new JLabel("Domain:");
		contentPane.add(domainLabel);
		
		// Create the ComboBox
		String[] domainStrings = { "Pixiv", "DeviantArt" };
		JComboBox domainList = new JComboBox(domainStrings);
		domainList.setSelectedIndex(0);
		domainList.addActionListener(this);
		contentPane.add(domainList);
	}

	public void actionPerformed(ActionEvent arg0) {
		JComboBox cb = (JComboBox)arg0.getSource();
		String domainName = (String)cb.getSelectedItem();
		
		switch(domainName){
			case "Pixiv":
				updatePixivField(domainName);
		}
	}

	// Create a Label and TextField for the gallery domain
	private void updatePixivField(String domainName) {
		JLabel memberIDLabel = new JLabel("Member ID:");
		contentPane.add(memberIDLabel);
		
		JTextField pixivID = new JTextField(20);
		contentPane.add(pixivID);
	}

}
