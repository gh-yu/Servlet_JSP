package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import filter.wrapper.EncryptWrapper;

/**
 * Servlet Filter implementation class EncryptFilter
 */
@WebFilter(
		servletNames = { 
				"UpdatePwdServlet", 
				"LoginServlet", 
				"InsertMemberServlet"
		})
public class EncryptFilter implements Filter {

    /**
     * Default constructor. 
     */
    public EncryptFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest hsr = (HttpServletRequest)request; // wrapper생성시 생성자에 넣기 위한 HttpServletRequest객체 생성
		
		EncryptWrapper ew = new EncryptWrapper(hsr);
		
		// pass the request along the filter chain
		chain.doFilter(ew, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
