package GalleryDownloader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class chooseDwnldFolderActionListener implements ActionListener {
    private final MyViewClass myView;

    chooseDwnldFolderActionListener(MyViewClass myView) {
        this.myView = myView;
    }

    // Set the download folder
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(null); // Passing in null = OS's default directory
        chooser.setDialogTitle("Set download folder...");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            myView.setFolderPath(chooser.getSelectedFile().toString());
    }
}