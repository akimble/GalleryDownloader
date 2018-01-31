import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import net.miginfocom.swing.MigLayout;

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
		init();
	}

	private void init() {
		// Create the frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[39px][53px][][][][][]", "[20px]"));
		
		// Create the ComboBox
		String[] domainStrings = { "", "Pixiv", "Imgur" };
		
		// Create a label
		JLabel domainLabel = new JLabel("Domain:");
		contentPane.add(domainLabel, "cell 4 0,alignx left,aligny center");
		JComboBox domainList = new JComboBox(domainStrings);
		domainList.addActionListener(this);
		contentPane.add(domainList, "cell 5 0,alignx left,aligny top");
	}

	public void actionPerformed(ActionEvent arg0) {
		JComboBox cb = (JComboBox)arg0.getSource();
		String domainName = (String)cb.getSelectedItem();
		
		switch(domainName){
			case "Imgur":
				revealImgurFields(domainName);
				break;
			case "Pixiv":
				revealPixivFields(domainName);
				break;
		}
	}

	// Create the Imgur fields
	private void revealImgurFields(String domainName) {
		JLabel urlLabel = new JLabel("URL:");
		contentPane.add(urlLabel, "cell 4 1,alignx left,aligny center");
		
		JTextField imgurURL = new JTextField(10);
		contentPane.add(imgurURL, "cell 5 1,alignx left,aligny top");
		
		revalidate();
	}

	// Create the Pixiv fields
	private void revealPixivFields(String domainName) {System.out.println(domainName);
		JLabel memberIDLabel = new JLabel("Member ID:");
		contentPane.add(memberIDLabel, "cell 4 1,alignx left,aligny center");
		
		JTextField pixivID = new JTextField(10);
		contentPane.add(pixivID, "cell 5 1,alignx left,aligny top");
		
		revalidate();
	}

}
