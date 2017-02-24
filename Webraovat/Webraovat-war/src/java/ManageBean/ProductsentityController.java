/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBean;

import Entity.Productsentity;
import Stub.StubBookmark;
import Stub.StubProduct;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.nio.file.Files;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/**
 *
 * @author Asus
 */
@Named(value = "productsentityController")
@SessionScoped
public class ProductsentityController implements Serializable {

    private Productsentity entity = null;
    private Stub.StubProduct Stub = null;
    private static final String SAVE_DIR = "uploadFiles";
    private String message = null;

    /**
     * Creates a new instance of Productsentity
     */
    public ProductsentityController() {
        entity = new Productsentity();
    }

    public Productsentity FindProductentityByPostID(int id) {
        Stub = new StubProduct();

       

        try {

            entity = Stub.FindProductentityByID(id);
            if (entity == null) {
                System.out.println("entity null");
            }
            if (entity != null) {

                return entity;
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    public String UploadFile(Part part) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String appPath = request.getServletContext().getRealPath("");
        // constructs path of the directory to save uploaded file
        String savePath = "C:\\Users\\Asus\\Documents\\NetBeansProjects\\du phong1\\Webraovat\\Webraovat-war\\web\\resources\\images";
        
        System.out.println(appPath);
        // creates the save directory if it does not exists
        File fileSaveDir = new File(savePath);

        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        String fileName = extractFileName(part);

        try (InputStream input = part.getInputStream()) {

            Files.copy(input, new File(savePath, fileName).toPath());
        } catch (IOException e) {
            e.getMessage();
            System.out.println("loi ko chay");
        }

        return fileName;

    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }

    public Productsentity getEntity() {
        return entity;
    }

    public void setEntity(Productsentity entity) {
        this.entity = entity;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
