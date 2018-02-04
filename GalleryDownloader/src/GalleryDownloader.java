import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class GalleryDownloader extends JFrame implements ActionListener {

	private JPanel headerPane;
	private JPanel fieldsPane;

	// Launch the application.
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

	// Initialize the components
	public GalleryDownloader() {
		init();
	}

	private void init() {
		// Create the frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		headerPane = new JPanel();
		headerPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(headerPane);
		headerPane.setLayout(new MigLayout("", "[140.00px][][grow]", "[20px][grow][]"));
		
		// Create a label for the ComboBox
		JLabel domainLabel = new JLabel("Domain:");
		headerPane.add(domainLabel, "flowx,cell 1 0,alignx left,aligny center");
		
		// Create the ComboBox
		String[] domainStrings = { "", "Imgur", "Pixiv" };
		JComboBox domainList = new JComboBox(domainStrings);
		domainList.addActionListener(this);
		headerPane.add(domainList, "cell 1 0,alignx left,aligny top");
		
		// Create and add new JPanel under headerPane
		fieldsPane = new JPanel();
		headerPane.add(fieldsPane, "cell 0 1 3 1,grow");
		
		// Create submit button
		JButton submitBtn = new JButton("Get Gallery");
		headerPane.add(submitBtn, "cell 1 2,alignx center");
	}

	// Call respective method for creating the chosen domain's fields
	public void actionPerformed(ActionEvent arg0) {
		@SuppressWarnings("unchecked")
		JComboBox<String> cb = (JComboBox<String>)arg0.getSource();
		String domainName = (String)cb.getSelectedItem();
		
		switch(domainName){
			case "Imgur":
				createImgurFields(domainName);
				break;
			case "Pixiv":
				createPixivFields(domainName);
				break;
		}
	}

	// Remove all fields in fieldsPane and then create the Imgur fields
	private void createImgurFields(String domainName) {
		fieldsPane.removeAll();
		
		JLabel urlLabel = new JLabel("URL:");
		fieldsPane.add(urlLabel);
		
		JTextField imgurURL = new JTextField(10);
		fieldsPane.add(imgurURL);
		
		revalidate();
		repaint();
	}

	// Remove all fields in fieldsPane and then create the Pixiv fields
	private void createPixivFields(String domainName) {
		fieldsPane.removeAll();
		
		JLabel memberIDLabel = new JLabel("Member ID:");
		fieldsPane.add(memberIDLabel);
		
		JTextField pixivID = new JTextField(10);
		fieldsPane.add(pixivID);
		
		revalidate();
		repaint();
	}

}
