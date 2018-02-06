import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class GalleryDownloader extends JFrame {

	private static final String folderPath = "C:/Users/Andrew/Downloads/";
	private JPanel headerPane;
	private JPanel fieldsPane;
	private JTextField imgurURL;
	private JTextField pixivID;

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

	@SuppressWarnings({ "unchecked", "rawtypes" })
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
		
		// Create the ComboBox and its listener
		String[] domainStrings = { "", "Imgur", "Pixiv" };
		JComboBox domainList = new JComboBox(domainStrings);
		domainList.addActionListener(comboBoxActionListener());
		
		// Create and add new JPanel under headerPane
		fieldsPane = new JPanel();
		
		// Add components to headerPane
		headerPane.add(domainLabel, "flowx,cell 1 0,alignx left,aligny center");
		headerPane.add(domainList, "cell 1 0,alignx left,aligny top");
		headerPane.add(fieldsPane, "cell 0 1 3 1,grow");
	}
	
	// Call respective method for creating the chosen domain's fields
	private ActionListener comboBoxActionListener(){
		ActionListener res = new ActionListener(){
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent arg0) {
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
		};
		
		return res;
	}

	// Call respective method for creating the chosen domain's fields
	private ActionListener imgurSubmitBtnActionListener(){
		ActionListener res = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			String url = imgurURL.getText();
			
			try {
				// Get the Document from the url
				Document document = Jsoup
						.connect(url)
						.userAgent("Mozilla/5.0")
						.referrer("https://www.google.com")
						.timeout(10 * 1000)
						.get();
				
				Elements images = document.select("img");
				
				for (Element image : images) {
					System.out.println(image);
					String imageSrc = image.absUrl("src");
					
					downloadImage(imageSrc);
				}
			} catch (IOException e1) {
				System.err.println("An error occured: " + e1.getMessage());
			}
		}};
		
		return res;
	}
	
	protected void downloadImage(String imageSrc) {
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
	private void createImgurFields(String domainName) {
		fieldsPane.removeAll();
		
		JLabel urlLabel = new JLabel("URL:");
		
		imgurURL = new JTextField(10);
		
		JButton submitBtn = new JButton("Get Gallery");
		submitBtn.addActionListener(imgurSubmitBtnActionListener());
		
		fieldsPane.add(urlLabel);
		fieldsPane.add(imgurURL);
		fieldsPane.add(submitBtn, "cell 1 2,alignx center");
		
		revalidate();
		repaint();
	}

	// Remove all fields in fieldsPane and then create the Pixiv fields
	private void createPixivFields(String domainName) {
		fieldsPane.removeAll();
		
		JLabel memberIDLabel = new JLabel("Member ID:");
		
		pixivID = new JTextField(10);
		
		JButton submitBtn = new JButton("Get Gallery");
		submitBtn.addActionListener(imgurSubmitBtnActionListener());
		
		fieldsPane.add(memberIDLabel);
		fieldsPane.add(pixivID);
		fieldsPane.add(submitBtn, "cell 1 2,alignx center");
		
		revalidate();
		repaint();
	}

}
