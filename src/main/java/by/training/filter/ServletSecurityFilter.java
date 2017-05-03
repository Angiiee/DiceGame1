package by.training.filter;

import by.training.entity.profile.RoleType;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by angelina on 27.03.2017.
 */
@WebFilter(urlPatterns = { "/*"}, servletNames = { "DiceGame" })
public class ServletSecurityFilter implements Filter{
    public void destroy() {
    }
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        RoleType type = (RoleType) session.getAttribute("visitor");
        if (type == null) {
            type = RoleType.GUEST;
            session.setAttribute("visitor", type);
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/index.jsp");
            dispatcher.forward(req, resp);
            return;
        }
        chain.doFilter(request, response);
    }
    public void init(FilterConfig fConfig) throws ServletException {
    }
}
