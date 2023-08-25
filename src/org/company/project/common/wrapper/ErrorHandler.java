package org.company.project.common.wrapper;

import org.apache.log4j.Logger;
import org.company.project.common.exception.NotMatchRecordVersionException;
import org.company.project.common.exception.UserNotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ErrorHandler {
    private ErrorHandler () {}
    private static final Logger LOGGER = Logger.getLogger(ErrorHandler.class);
    public static void getError (Exception e, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        e.printStackTrace();
        if(e instanceof ArithmeticException){
            LOGGER.error(e.getMessage());
            LOGGER.error(e.getClass());
            req.setAttribute("msg", e.getMessage());
            resp.sendError(701);
        } else if (e instanceof SQLException) {
            LOGGER.error(e.getMessage());
            LOGGER.error(e.getClass());
            req.setAttribute("msg", e.getMessage());
            resp.sendError(701);
        }else if (e instanceof NotMatchRecordVersionException){
            LOGGER.error(e.getMessage());
            LOGGER.error(e.getClass());
            req.setAttribute("msg", "NotMatchRecordVersionException");
            resp.sendError(701);;
        } else if (e instanceof UserNotFoundException) {
            LOGGER.error(e.getMessage());
            LOGGER.error(e.getClass());
            req.setAttribute("msg", "UserNotFoundException");
            resp.sendError(701);
        }else {
            LOGGER.error(e.getMessage());
            LOGGER.error(e.getClass());
            req.setAttribute("msg", e.getMessage());
            resp.sendError(701);
        }
    }
}
