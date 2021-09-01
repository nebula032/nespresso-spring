package com.exe.springweb;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@ServerEndpoint(value="/echo.do")
public class WebSocketChat {
    
    private static final List<Session> sessionList=new ArrayList<Session>();;
    private static final Logger logger = LoggerFactory.getLogger(WebSocketChat.class);
    public WebSocketChat() {
        // TODO Auto-generated constructor stub
        System.out.println("������(����) ��ü����");
    }
    
    @OnOpen
    public void onOpen(Session session) {
    	System.out.println("������(����) ����");
        logger.info("Open session id:"+session.getId());
        try {
            final Basic basic=session.getBasicRemote();
            basic.sendText("�ȳ��ϼ���. �׽������� Ŭ���Դϴ�. ������ ���͵帱���?,nespresso");
        }catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
        sessionList.add(session);
    }
    
    /*
     * ��� ����ڿ��� �޽����� �����Ѵ�.
     * @param self
     * @param sender
     * @param message
     */
    private void sendAllSessionToMessage(Session self, String message) {
    	System.out.println("�޽��� ������");
        try {
           for(Session session : WebSocketChat.sessionList) {
        	   if(!self.getId().equals(session.getId())) {
                    session.getBasicRemote().sendText(message);
               }
           }
        }catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
    }
    
    /*
     * ���� �Է��ϴ� �޼���
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message,Session session) {
    //	String message = (String) messages;
    	System.out.println("�� �޼���" + message);
    	//String sender = message.split(",")[1];
    	//message = message.split(",")[0];
    	
        logger.info("Message From : "+message);
        try {
            final Basic basic=session.getBasicRemote();
            basic.sendText(message);
        }catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
        sendAllSessionToMessage(session, message);
    }
    
    @OnError
    public void onError(Throwable e,Session session) {
        System.out.println("����");
    }
    
    @OnClose
    public void onClose(Session session) {
    	System.out.println("������(����) ���� ����");
        logger.info("Session "+session.getId()+" has ended");
        sessionList.remove(session);
    }
}
