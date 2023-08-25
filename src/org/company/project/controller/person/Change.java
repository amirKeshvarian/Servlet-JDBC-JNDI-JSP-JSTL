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

@WebServlet(urlPatterns = "/person/change.do")
public class Change extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Person person = new Person()
                    .setId(Long.parseLong(req.getParameter("id")))
                    .setName(req.getParameter("name"))
                    .setFamily(req.getParameter("family"))
                    .setSalary(Long.parseLong(req.getParameter("salary")))
                    .setRecordVersion(Long.parseLong(req.getParameter("recordversion")));
            PersonService.getInstance().change(person);
            resp.sendRedirect("/person/findAll.do");
        } catch (Exception e) {
            ErrorHandler.getError(e, req, resp);
        }

    }
}
