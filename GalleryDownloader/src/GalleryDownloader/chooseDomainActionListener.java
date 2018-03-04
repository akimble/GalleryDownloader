package GalleryDownloader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class chooseDomainActionListener implements ActionListener {
    private final MyViewClass myView;

    chooseDomainActionListener(MyViewClass myView) {
        this.myView = myView;
    }

    // Call respective method for creating the chosen domain's fields
    public void actionPerformed(ActionEvent e) {
        JComboBox<String> cb = (JComboBox<String>)e.getSource();
        String domainName = (String)cb.getSelectedItem();

        switch(domainName) {
            case "Imgur":
                myView.createImgurFields(domainName);
                break;
            case "Pixiv":
                myView.createPixivFields(domainName);
                break;
        }
    }
}