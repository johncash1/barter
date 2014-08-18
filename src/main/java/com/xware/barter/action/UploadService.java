package com.xware.barter.action;


import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.io.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Servlet implementation class UploadService
 */
// @WebServlet(name = "FileUploadServlet", urlPatterns = {"/upload"})
@MultipartConfig
public class  UploadService  {

    private final static Logger LOGGER = 
            Logger.getLogger(UploadService.class.getCanonicalName());
	private static final long serialVersionUID = 1L;
 
	private Properties configProp = new Properties();

	public Properties getConfigProp() {
		return configProp;
	}

	public void setConfigProp(Properties configProp) {
		this.configProp = configProp;
	}

	public static Logger getLogger() {
		return LOGGER;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void loadProps1() {
	        InputStream in = this.getClass().getResourceAsStream("/barter.properties");
	        try {
	            configProp.load(in);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}  
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadService() {
        super();
        loadProps1();
        // TODO Auto-generated constructor stub
    }
    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
    	
    /*	String resourceName = "myconf.properties"; // could also be a constant
    	ClassLoader loader = Thread.currentThread().getContextClassLoader();
    	Properties props = new Properties();
    	int fileSize= Integer.parseInt(props.getProperty("filesize"));
    	String filelocation =props.getProperty("filelocation");
    	BufferedReader reader = new BufferedReader(new FileReader(resourceName));
    	try {
    	 fileSize= Integer.parseInt(props.getProperty("filesize"));
        	 filelocation =props.getProperty("filelocation");
    	} finally {
    	    reader.close();
    	}
    //	try(InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
    //	    props.load(resourceStream);
   // 	}
    	*/
    	
        response.setContentType("text/html;charset=UTF-8");

        // Create path components to save the file
        final String path = configProp.getProperty("filelocation"); // request.getParameter("destination");
        int fileSize=Integer.parseInt(configProp.getProperty("filesize") );
        final Part filePart = request.getPart("file");
        final String fileName = getFileName(filePart);

        OutputStream out = null;
        InputStream filecontent = null;
        final PrintWriter writer = response.getWriter();

        try {
        	File f = new File(path + File.separator
                    + fileName);
        	f.isFile();
        	double bytesize = f.length();
        	if (bytesize > fileSize){
                LOGGER.log(Level.SEVERE, "Problems during file upload. Error: filesize too big ");
                response.getWriter().append("Error: filesize too big ");
        	}
            out = new FileOutputStream(f);
            filecontent = filePart.getInputStream();
            

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            writer.println("New file " + fileName + " created at " + path);
            LOGGER.log(Level.INFO, "File{0}being uploaded to {1}", 
                    new Object[]{fileName, path});
        } catch (FileNotFoundException fne) {
            writer.println("You either did not specify a file to upload or are "
                    + "trying to upload a file to a protected or nonexistent "
                    + "location.");
            writer.println("<br/> ERROR: " + fne.getMessage());

            LOGGER.log(Level.SEVERE, "Problems during file upload. Error: {0}", 
                    new Object[]{fne.getMessage()});
        } finally {
            if (out != null) {
                out.close();
            }
            if (filecontent != null) {
                filecontent.close();
            }
            if (writer != null) {
                writer.close();
            }
        }
    }

    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * 
	 
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	*/

}
