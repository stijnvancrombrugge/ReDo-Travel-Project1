package com.realdolmen.project1.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.realdolmen.project1.XML.TripXMLParser;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import java.io.*;


/**
 * Created by JVDAX31 on 13/10/2015.
 */
@ManagedBean(name="fileUploadController")
public class FileUploadController {
    private UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void upload(FileUploadEvent event) {
        if(file != null) {
            String fileName = "newTrips.xml"; //--- this is an example, in my real code it is dynamic
            System.out.println("in upload");
            try {
                copyFile(fileName, event.getFile().getInputstream());
            } catch (IOException e) {
                e.printStackTrace();
            }

//--- getters and setters



        }
    }

    public void copyFile(String fileName, InputStream in) {
        try {

            String location = "C:\\test\\";
            // write the inputStream to a FileOutputStream
            OutputStream out = new FileOutputStream(new File(fileName));

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            in.close();
            out.flush();
            out.close();

            System.out.println("New file created!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
