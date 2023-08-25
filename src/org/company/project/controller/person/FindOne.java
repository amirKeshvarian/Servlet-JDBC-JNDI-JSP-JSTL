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
import java.util.Arrays;
import java.util.List;

@WebServlet(urlPatterns = "/person/findOne.do")
public class FindOne extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Person findPerson = PersonService.getInstance().findOne(new Person().setId(Long.parseLong(req.getParameter("id"))));
            List<Person> person = Arrays.asList(findPerson);
            req.setAttribute("list", person);
            req.getRequestDispatcher("/person/index.jsp").forward(req, resp);
        } catch (Exception e) {
            ErrorHandler.getError(e, req, resp);
        }
    }
}
