package dev.remitano.core.filter;

import dev.remitano.infrastructure.configuration.AccessInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);

    private AccessInfo accessInfo;

    public AuthenticationFilter(AccessInfo accessInfo) {
        this.accessInfo = accessInfo;
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        String requestURI = req.getRequestURI();
        String servletPath = "/".equals(accessInfo.getServletPath()) ? "" : accessInfo.getServletPath();
        String apiKey = req.getHeader("apiKey");
        //ignore OPTIONS
        if (req.getMethod().equals("OPTIONS")) {
            chain.doFilter(req, response);
        } else {
            if (req.getMethod().equals("GET") && (requestURI.contains("/api/images"))) {
                chain.doFilter(req, response);
            }
            if (requestURI.equals(servletPath + "/swagger-ui.html") || requestURI
                    .startsWith(servletPath + "/webjars/")
                    || requestURI.equals(servletPath + "/configuration/ui") || requestURI
                    .equals(servletPath + "/swagger-resources")
                    || requestURI.equals(servletPath + "/v2/api-docs") || requestURI
                    .equals(servletPath + "/configuration/security")) {
                if (accessInfo.getEnv().equals("development") || accessInfo.getEnv().equals("local")){
                    chain.doFilter(req, response);
                } else {
                    HttpServletResponse resp = (HttpServletResponse) response;
                    resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }
            } else {
                chain.doFilter(req, response);
            }
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

}
