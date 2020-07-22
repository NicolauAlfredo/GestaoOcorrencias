/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlo;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author user
 */
public class FiltroPaginas implements Filter {

    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public FiltroPaginas() {
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        //HttpSession sessao = request.getSession();
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String url = httpServletRequest.getRequestURI();
        HttpSession sessao = httpServletRequest.getSession();

        if (sessao.getAttribute("administrador") != null || url.lastIndexOf("/paginas/administrador/login.jsp") > -1 || url.lastIndexOf("administradorServlet") > -1) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).sendRedirect("/paginas/administrador/login.jsp");
        }

    }

    public void init(FilterConfig filterConfig) {
    }

}
