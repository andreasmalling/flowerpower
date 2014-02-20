import java.io.*;

import javax.faces.bean.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebFilter implements Filter {
	protected FilterConfig config;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// FilterConfig is used by container to provide the parameters and the context object to the filter
		this.config = filterConfig;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// The filter checks whether the owner ID and password is entered or not. If it is and it is valid, the owner will get access to the admin page, if not, the request will be denied and the owner will return back to the login page.
	
		YoloBean yoloBean = (YoloBean)((HttpServletRequest)request).getSession().getAttributeNames();
		if(yoloBean == null){
		String ourPath = ((HttpServletRequest)request).getServletPath();
		((HttpServletResponse)response).sendRedirect(ourPath + "loginPage.xhtml");
		}
		chain.doFilter(request, response);
	}
    @Override
     public void destroy() {
     // Method used to close any resources opened by filter.		 
    }
}
