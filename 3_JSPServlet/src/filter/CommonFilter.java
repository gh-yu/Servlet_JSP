package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class Commonfilter2
 */
// @WebFilter("/*")로만 해도 url매핑됨. fileName까지 같이 지정해주면 urlPatterns값으로 매핑될 url 넣어야함 -> 주석처리, web.xml에서 매핑함
// @WebFilter(filterName = "encoding", urlPatterns = { "/*" })
public class CommonFilter implements Filter {

    /**
     * Default constructor. 
     */
    public CommonFilter() {
        // TODO Auto-generated constructor stub
    	System.out.println("CommonFilter 생성자");
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
		request.setCharacterEncoding("UTF-8"); // 모든 서블릿 파일에 인코딩 설정
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("CommonFilter init()");
	}

}
