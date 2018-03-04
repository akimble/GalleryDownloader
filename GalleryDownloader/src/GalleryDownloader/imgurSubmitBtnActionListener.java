package GalleryDownloader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class imgurSubmitBtnActionListener implements ActionListener {
    private final MyViewClass myView;

    imgurSubmitBtnActionListener(MyViewClass myView) {
        this.myView = myView;
    }

    // Download the images from the URL into the download folder
    public void actionPerformed(ActionEvent e) {
        String url = myView.getImgurURL().getText();

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

                myView.downloadImage(imageSrc);
            }
        } catch (IOException e1) {
            System.err.println("An error occured: " + e1.getMessage());
        }
    }
}