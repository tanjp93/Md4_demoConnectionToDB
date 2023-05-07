package model.service.category;

import model.config.ConnectionToDB;
import model.entity.Category;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class ICategoryServiceIPLM implements ICategoryService {
    List<Category> categoryList = new ArrayList<>();

    @Override
    public List<Category> findAll() {
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnection();
            CallableStatement callSt = conn.prepareCall("call PROC_GetAllCategory()");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getString("id"));
                category.setName(rs.getString("name"));
                categoryList.add(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionToDB.closeConnection(conn);
        }
        return categoryList;
    }

    @Override
    public boolean save(Category category) {
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnection();
            CallableStatement callSt = conn.prepareCall("call PROC_InsertNewCategory(?,?)");
            callSt.setString(1, category.getId());
            callSt.setString(2, category.getName());
            callSt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionToDB.closeConnection(conn);
        }
        return false;
    }

    @Override
    public boolean update(Category category) {
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnection();
            CallableStatement callableStatement = conn.prepareCall("call PROC_UpdateCategory(?,?)");
            callableStatement.setString(1, category.getId());
            callableStatement.setString(2, category.getName());
            callableStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionToDB.closeConnection(conn);
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnection();
            CallableStatement callSt = conn.prepareCall("call PROC_DeleteCategory(?)");
            callSt.setString(1, id);
            callSt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionToDB.closeConnection(conn);
        }
        return true;
    }

    @Override
    public Category findById(String id) {
        Connection conn=null;
        Category category=new Category();
        try{
            conn=ConnectionToDB.getConnection();
            CallableStatement callableStatement=conn.prepareCall("call PROC_FindCategoryById(?)");
            callableStatement.setString(1,id);
            ResultSet rs=callableStatement.executeQuery();
            while (rs.next()){
                category.setId(rs.getString(1));
                category.setName(rs.getString(2));
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return category;
    }
}
