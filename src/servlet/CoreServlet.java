package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.AesException;
import com.WXBizMsgCrypt;

import util.getdata;

public class CoreServlet extends HttpServlet{
	private static final long serialVersionUID = 4440739483644821986L;
	
    String sToken = "";//这个Token是随机生成，但是必须跟企业号上的相同
    String sCorpID = "";//这里是你企业号的CorpID
    String sEncodingAESKey = "";//这个EncodingAESKey是随机生成，但是必须跟企业号上的相同
   
    @Override
    public void init() throws ServletException {
    	// TODO Auto-generated method stub
    	super.init();
    	getdata gd=new getdata();
    	try {
			getdata.getconn();
			sCorpID=gd.getwxid();
			sToken=gd.getsToken();
			sEncodingAESKey=gd.getsEncodingAESKey();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    /**
     * 确认请求来自微信服务器
     * @throws IOException 
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        // 微信加密签名 
        String sVerifyMsgSig = request.getParameter("msg_signature");
        // 时间戳
        String sVerifyTimeStamp = request.getParameter("timestamp");
        // 随机数
        String sVerifyNonce = request.getParameter("nonce");
        // 随机字符串
        String sVerifyEchoStr = request.getParameter("echostr");
        String sEchoStr; //需要返回的明文
        PrintWriter out = response.getWriter();  
        WXBizMsgCrypt wxcpt;
        try {
            wxcpt = new WXBizMsgCrypt(sToken, sEncodingAESKey, sCorpID);
            sEchoStr = wxcpt.VerifyURL(sVerifyMsgSig, sVerifyTimeStamp,sVerifyNonce, sVerifyEchoStr);
            // 验证URL成功，将sEchoStr返回
            out.print(sEchoStr);  
        } catch (AesException e1) {
            e1.printStackTrace();
        }
    }
 
    /**
     * 处理微信服务器发来的消息
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO 消息的接收、处理、响应
    }
}
