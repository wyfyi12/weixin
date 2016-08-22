package servlet;

import java.io.IOException;  

import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;

import util.EncoderHandler;  
  
public class CodeServlet extends HttpServlet {  
  
    private static final long serialVersionUID = 1L;  
      
    @Override  
    protected void service(HttpServletRequest requset, HttpServletResponse response)  
            throws ServletException, IOException {  
        String content ="http://weixin.njnantu.com:8080/Table";  
        EncoderHandler encoder = new EncoderHandler();  
        encoder.encoderQRCoder(content, response);  
    }  
  
}
