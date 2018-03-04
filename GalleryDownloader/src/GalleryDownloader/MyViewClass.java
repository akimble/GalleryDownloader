package GalleryDownloader;

import java.awt.EventQueue;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

@SuppressWarnings("serial")
public class MyViewClass extends JFrame {

	private String folderPath = new File("").getAbsolutePath();
	private JPanel headerPane;
	private JPanel fieldsPane;
	private JTextField imgurURL;
	private JTextField pixivID;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem mntmSetFolder;

	// Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyViewClass frame = new MyViewClass();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Initialize the components
	private MyViewClass() {
		init();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void init() {
		// Create the frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		// Create the JMenuBar -> JMenu -> JMenuItem
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		mntmSetFolder = new JMenuItem("Set folder...");
		mnFile.add(mntmSetFolder);
		chooseDwnldFolderActionListener listener1 = new chooseDwnldFolderActionListener(this);
		mntmSetFolder.addActionListener(listener1);
		
		// Create the first JPanel
		headerPane = new JPanel();
		headerPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(headerPane);
		headerPane.setLayout(new MigLayout("", "[140.00px][][grow]", "[20px][grow][]"));
		
		// Create a label for the ComboBox
		JLabel domainLabel = new JLabel("Domain:");
		
		// Create the ComboBox and its listener
		String[] domainStrings = { "", "Imgur", "Pixiv" };
		JComboBox domainList = new JComboBox(domainStrings);
		chooseDomainActionListener listener2 = new chooseDomainActionListener(this);
		domainList.addActionListener(listener2);
		
		// Create a new JPanel to put under headerPane for most of the dynamic fields
		fieldsPane = new JPanel();
		
		// Add components to headerPane
		headerPane.add(domainLabel, "flowx,cell 1 0,alignx left,aligny center");
		headerPane.add(domainList, "cell 1 0,alignx left,aligny top");
		headerPane.add(fieldsPane, "cell 0 1 3 1,grow");
	}
	
	// Download the image from the source URL
	void downloadImage(String imageSrc) {
		String strImageName = imageSrc.substring(imageSrc.lastIndexOf("/") + 1);
		
		System.out.println("Saving '" + strImageName + "' from " + imageSrc);
		
		try {
			URL imageURL = new URL(imageSrc);
			InputStream iss = imageURL.openStream();
			
			byte[] buffer = new byte[4096];
			int n = -1;
			
			OutputStream oss = new FileOutputStream(folderPath + "/" + strImageName);
			
			while ((n = iss.read(buffer)) != -1) {
				oss.write(buffer, 0, n);
			}
			
			oss.close();
			
			System.out.println(strImageName + " saved.");
		} catch (IOException e) {
			System.err.println("An error occured: " + e.getMessage());
		}
	}

	// Remove all fields in fieldsPane and then create the Imgur fields
	void createImgurFields(String domainName) {
		fieldsPane.removeAll();
		
		JLabel urlLabel = new JLabel("URL:");
		imgurURL = new JTextField(10);
		JButton submitBtn = new JButton("Get Gallery");
		imgurSubmitBtnActionListener listener3 = new imgurSubmitBtnActionListener(this);
		submitBtn.addActionListener(listener3);
		
		fieldsPane.add(urlLabel);
		fieldsPane.add(imgurURL);
		fieldsPane.add(submitBtn, "cell 1 2,alignx center");
		
		revalidate();
		repaint();
	}

	// Remove all fields in fieldsPane and then create the Pixiv fields
	void createPixivFields(String domainName) {
		fieldsPane.removeAll();
		
		JLabel memberIDLabel = new JLabel("Member ID:");
		pixivID = new JTextField(10);
		JButton submitBtn = new JButton("Get Gallery");
		//submitBtn.addActionListener(imgurSubmitBtnActionListener());
		
		fieldsPane.add(memberIDLabel);
		fieldsPane.add(pixivID);
		fieldsPane.add(submitBtn, "cell 1 2,alignx center");
		
		revalidate();
		repaint();
	}

	void setFolderPath(String newPath) {
		folderPath = newPath;
	}

    JTextField getImgurURL() {
        return imgurURL;
    }

}