package filter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class StaticResponse extends HttpServletResponseWrapper {
    private HttpServletResponse response;
    private PrintWriter pw ;

    /**
     * String path :html文件路径
     * @param response
     * @param path
     */
    public StaticResponse(HttpServletResponse response , String path) {
        super(response);
        this.response = response;
        try {
            // 创建一个与html文件路径在一起的流对象
            pw = new PrintWriter(path , "utf-8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public PrintWriter getWriter(){
        //返回一个与html绑定在一起的printWriter对象
        //jsp就会使用它进行输出，这样数据就都输出到html文件了
        return pw;
    }
}
