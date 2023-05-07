package controller;

import model.entity.Category;
import model.service.category.ICategoryService;
import model.service.category.ICategoryServiceIPLM;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/CategoryServlet")
public class CategoryServlet extends HttpServlet {
    private ICategoryService categoryService=new ICategoryServiceIPLM();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action==null){
            action="";
        }
        switch (action){
            case "GetAllCategory":
                showAllCategory(request,response);
                break;
            default:
                break;
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    private void showAllCategory(HttpServletRequest request, HttpServletResponse response) {
        List<Category> categoryList= categoryService.findAll();
        System.out.println("categoryList===>>"+categoryList);
        request.setAttribute("categoryList",categoryList);
        RequestDispatcher dispatcher=request.getRequestDispatcher("view/list.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
