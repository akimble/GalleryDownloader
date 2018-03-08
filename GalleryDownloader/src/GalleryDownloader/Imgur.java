package GalleryDownloader;

import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;


// Imgur object for DomainFactory
public class Imgur implements Domain {

    private final MyViewClass myView;
    private JTextField urlTxtFld;

    Imgur(MyViewClass myView) {
        this.myView = myView;
    }

    // Remove all fields in fieldsPane and then create the Imgur fields
    public void createFields() {
        JPanel fieldsPane = myView.getFieldsPane();

        fieldsPane.removeAll();

        JLabel urlLabel = new JLabel("URL:");
        urlTxtFld = new JTextField(10);
        JButton submitBtn = new JButton("Get Gallery");
        imgurSubmitBtnActionListener listener3 = new imgurSubmitBtnActionListener(this);
        submitBtn.addActionListener(listener3);

        fieldsPane.add(urlLabel);
        fieldsPane.add(urlTxtFld);
        fieldsPane.add(submitBtn, "cell 1 2,alignx center");

        fieldsPane.revalidate();
        fieldsPane.repaint();
    }

    // Download the image from the source URL
    public void downloadImage(String imageSrc) {
        String strImageName = imageSrc.substring(imageSrc.lastIndexOf("/") + 1);

        System.out.println("Saving '" + strImageName + "' from " + imageSrc);

        try {
            URL imageURL = new URL(imageSrc);
            InputStream iss = imageURL.openStream();

            byte[] buffer = new byte[4096];

            OutputStream oss = new FileOutputStream(myView.getFolderPath() + "/" + strImageName);

            int n;
            while ((n = iss.read(buffer)) != -1) {
                oss.write(buffer, 0, n);
            }

            oss.close();

            System.out.println(strImageName + " saved.");
        } catch (IOException e) {
            System.err.println("An error occured: " + e.getMessage());
        }
    }

    String getUrlTxtFld() {
        return urlTxtFld.getText();
    }
}
