//package by.training.filter;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * Created by angelina on 29.03.2017.
// */
//@WebFilter(urlPatterns = {"/controller?command=locale&locale=en-EN&submit="}, servletNames = {"DiceGame"})
//public class TestFilter implements Filter {
//    public void destroy() {
//    }
//
//    public void doFilter(ServletRequest request, ServletResponse response,
//                         FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse resp = (HttpServletResponse) response;
//        String command = req.getParameter("command");
//        if ("locale".equals(command)) {
//
//            req
//            RequestDispatcher dispatcher = request.getServletContext()
//                    .getRequestDispatcher(page);
//            dispatcher.forward(req, resp);
//            request.getRequestDispatcher(page).forward(req, resp);
//            return;
//        }
//
//        String ololo = ((HttpServletRequest) request).getRequestURI();
//        if(((HttpServletRequest) request).getRequestURI().startsWith("controller?command=locale")){
//            String page = req.getRequestURI();
//            request.setAttribute("page", page);
//        }
//        chain.doFilter(request, response);
//
//    }
//
//    public void init(FilterConfig fConfig) throws ServletException {
//    }
//}
