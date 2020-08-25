package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebFilter(filterName = "StaticFilter")
public class StaticFilter implements Filter {
    private FilterConfig config;//为了获得ServleContext对象
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        /**
         * 第一次访问时，查找请求对应的html页面是否存在，如果存在重定向到html
         * 如果不存在，放行!把servlet访问数据库后，输出给客服端的数据保存到一个html页面中，在重定向到html
         * ---------------------
         * 获取category参数
         * category有四种可能
         * null-->null.html
         * 1-->1.html
         * 2-->2.html
         * 3-->3.html
         * html的保存路径，htmls目录下
         * -----------------------
         */
        /**
         * 判断对应的html页面是否存在，如果存在，直接重定向
         */
        String category = request.getParameter("category");//得到参数
        String htmlPage = category + ".html";//得到html文件
        String htmlPath = config.getServletContext().getRealPath("/htmls");//得到文件存放目录
        File destFile = new File(htmlPath , htmlPage);//

        if (destFile.exists()){ // 如果文件存在
            // 直接重定向到这个文件
            resp.sendRedirect(req.getContextPath() + "/htmls/" + htmlPage);
            return;
        }
        /**
         * 二、如果html文件不存在，我们要生成html
         * 1、放行，show.jsp会做出很多的输出，我们要让他别再输出到客服端，而是输出到我们制定的html文件中
         * 完成：
         * 调包response，让他的getWritter()与一个html文件绑定，那么show.jsp的输出就到了html文件中
         */
        StaticResponse sr = new StaticResponse(resp , destFile.getAbsolutePath());
        chain.doFilter(request , sr);//放行，即生成html文件
        // 这是页面已经存在，重定向到html文件
        resp.sendRedirect(req.getContextPath() + "/htmls/" + htmlPage);
    }

    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }

}
