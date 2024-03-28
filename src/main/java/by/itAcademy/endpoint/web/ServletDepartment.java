package by.itAcademy.endpoint.web;
import by.itAcademy.core.DTO.department.DTODepartmentCreate;
import by.itAcademy.core.DTO.department.DepartPut;
import by.itAcademy.core.DTO.department.DepartmentDelete;
import by.itAcademy.core.DTO.department.out.ChildrenParent;
import by.itAcademy.core.DTO.department.out.DTODepartmentOut;
import by.itAcademy.core.DTO.department.out.DTODepartmentOutAll;
import by.itAcademy.dao.entity.Department;
import by.itAcademy.service.api.IServiceDepartment;
import by.itAcademy.service.api.IServiceLocation;
import by.itAcademy.service.fabric.ServiceDepartmentSingleton;
import by.itAcademy.service.fabric.ServiceLocationSingleton;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/department")
public class ServletDepartment extends HttpServlet {
    private final IServiceDepartment serviceDepartment = ServiceDepartmentSingleton.getInstance();
    private final IServiceLocation serviceLocation = ServiceLocationSingleton.getInstance();
    private final ObjectMapper mapper = new ObjectMapper().findAndRegisterModules().registerModule(new JavaTimeModule());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");
        String id = req.getParameter("id");
        if (id != null) {
            Long idLong = Long.parseLong(id);
            Department department = serviceDepartment.getById(idLong);
            DTODepartmentOut departmentOut = new DTODepartmentOut();
            departmentOut.setId(department.getId());
            departmentOut.setName(department.getName());
            departmentOut.setPhone(departmentOut.getPhone());
            departmentOut.setLocation(department.getLocation().getName());

            ChildrenParent parent = new ChildrenParent();
            if(department.getParent()!=null){
                parent.setId(department.getParent().getId());
                parent.setName(department.getParent().getName());
            }
            departmentOut.setParent(parent);
            List<Department> child = serviceDepartment.getAllChildren(department.getId());
            if(child!=null&&child.size()!=0){
                for(Department ch : child){
                    departmentOut.addChildren(
                            new ChildrenParent(ch.getId(),ch.getName()));
                }
            } else {
                departmentOut.addChildren(new ChildrenParent(null,null));
            }
            departmentOut.setTimeCrate(department.getDateCreate());
            departmentOut.setTimeUpdate(department.getDateUpdate());
            String json = mapper.writeValueAsString(departmentOut);
            Writer writer = resp.getWriter();
            writer.write(json);
        } else {
            List<Department> departmentAll = serviceDepartment.getAll();
            List<DTODepartmentOutAll> departmentOutAll = new ArrayList<>();
            for(Department department : departmentAll){
                departmentOutAll.add(new DTODepartmentOutAll(
                   department.getId(),
                   department.getName(),
                   department.getPhone(),
                   department.getLocation().getName()
                ));
            }
            Writer writer = resp.getWriter();
            String jsonOut = mapper.writeValueAsString(departmentOutAll);
            writer.write(jsonOut);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        DTODepartmentCreate departmentCreate = this.mapper.readValue(
                req.getInputStream(), DTODepartmentCreate.class);
        serviceDepartment.add(departmentCreate);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        DepartPut departmentPut = this.mapper.readValue(
                req.getInputStream(), DepartPut.class);
        serviceDepartment.update(departmentPut);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        DepartmentDelete departmentDelete = this.mapper.readValue(
                req.getInputStream(), DepartmentDelete.class);
        serviceDepartment.delete(departmentDelete);
    }
}
