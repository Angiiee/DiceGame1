//package by.training.filter;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * Created by angelina on 11.04.2017.
// */
//@WebFilter(urlPatterns = { "/*"}, servletNames = { "DiceGame" })
//public class NoCacheFilter implements Filter {
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
//        HttpServletResponse response = (HttpServletResponse) res;
//
//        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
//        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
//        response.setDateHeader("Expires", 0); // Proxies.
//
//        chain.doFilter(req, res);
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}