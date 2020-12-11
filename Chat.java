import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.ArrayList;
class MyClass{
	public String name;
	public String msg;
	MyClass(String n,String m){
		name=n;
		msg=m;
	}
	public String getName(){
		return name;
	}
	public String getMsg(){
		return msg;
	}
}
public class  Chat extends HttpServlet{
	static ArrayList<MyClass> contents=new ArrayList<MyClass>();
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException {
		doGet(req,res);
	}
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException {
		
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		String name=req.getParameter("name");
		String msg=req.getParameter("msg");
		
		out.println("<html><head><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"></head><body>");
		// out.println("<h1>Test</h1><br>");
		// out.println("<h2>"+name+"</h2>");
		
		if(msg!=null){
			contents.add(new MyClass(name,msg));
			if(contents.size()>50){
				contents.remove(0);
			}
			
		}
		out.println("<h1>Welcome to Public Group Chat</h1>");
		
		if(Chat.contents.size()>0){
			for(MyClass i : Chat.contents)
				out.println("<h2>"+i.getName()+" : "+i.getMsg()+"</h2>");
		}		
		
		
		out.println("<br><br>");
		out.println("<form action=\"chat\" method=\"POST\">");
		out.println("<input type=\"hidden\" value=\""+name+"\" name=\"name\">");
		// out.println("<input type=\"hidden\" value=\"manual\" name=\"usr\">");
		
		out.println("<input type=\"text\" name=\"msg\"><br>");
		out.println("<input type=\"submit\" value=\"Send\">");
		out.println("</form>");
		// Refresh Button
		out.println("<form action=\"chat\" method=\"POST\">");
		out.println("<input type=\"hidden\" value=\""+name+"\" name=\"name\">");
		out.println("<input type=\"submit\" value=\"Refresh\">");
		out.println("</form>");
		out.println("</body></html>");
		out.close();
	}
}