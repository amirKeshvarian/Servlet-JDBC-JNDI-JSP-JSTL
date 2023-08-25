package org.company.project.controller.person;

import org.company.project.common.wrapper.ErrorHandler;
import org.company.project.model.domain.Person;
import org.company.project.model.service.PersonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/person/findAll.do")
public class FindAll extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Person> personList = PersonService.getInstance().findAll();
            req.setAttribute("list", personList);
            req.getRequestDispatcher("/person/index.jsp").forward(req, resp);
        } catch (Exception e) {
            ErrorHandler.getError(e, req, resp);
        }
    }
}
