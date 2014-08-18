package com.xware.barter.action;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.*;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.*;

import org.apache.commons.io.FileUtils;
import org.springframework.context.ApplicationContext;

public class UploadServiceTest {
UploadService uploadService;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		uploadService = new UploadService();
	}
	

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLoadProps1() {
		uploadService.loadProps1();
		String loc=   uploadService.getConfigProp().getProperty("filelocation");
		 
		 System.out.println("file loc"+ loc);
		 assertTrue(!(loc.equals("")));
     //   assertTrue(FileUtils.readFileToString(new File(loc), "UTF-8")
       //         .contains("My Expected String"));
	}

//	@Test
//	public void testUploadService() {
	

	//	public class TestMyServlet{

		    @Test
		    public void testServlet() throws Exception {
		        HttpServletRequest request = mock(HttpServletRequest.class);       
		        HttpServletResponse response = mock(HttpServletResponse.class);    
		    	String loc=   uploadService.getConfigProp().getProperty("filelocation");
				 
				 System.out.println("file loc"+ loc);
				//	Resource resource = 
					//           appContext.getResource("classpath:com/mkyong/common/testing.txt");
			//	 FileReader f= new FileReader( getClass().getResource("/test.txt").getFile());
			//	 FileReader f= new FileReader( getClass().getClassLoader().getResource("test.txt").getFile());
				 File f = new File( this.getClass().getResource( "/test.txt" ).toURI() );
				 BufferedReader br=new BufferedReader(new FileReader(f));
			//	 request.
					//BufferedReader br=new BufferedReader(new FileReader(loc+"/test.txt"));
		      // when(request.getParameter("uploadfile")).thenReturn(f);
		   //     
            //  when(request.getParameter("myfile")).thenReturn();
              try{
            	  final Part filePart = request.getPart("file");
            	uploadService.processRequest(request, response);  
            	  
		      //  PrintWriter writer = new PrintWriter("somefile.txt");
		      
		      
		   
	//	        request.getPart("file").write(loc);
		    //   br  =new BufferedReader(new FileReader(loc));
		       
		       assertTrue(br.readLine().length() > 5);
		//        when(response.getWriter()).thenReturn(writer);

		        //new MyServlet().doPost(request, response);

		    //    verify(request, atLeast(1)).getParameter("username"); // only if you want to verify username was called...
		      //  writer.flush(); // it may not have been flushed yet...
		      //  assertTrue(FileUtils.readFileToString(new File("somefile.txt"), "UTF-8")
		        //           .contains("My Expected String"));
              }
              finally{
            	  if (br != null)
            	    br.close();  
              }
	}

	@Test
	public void testProcessRequest() {
		fail("Not yet implemented");
	}

}
