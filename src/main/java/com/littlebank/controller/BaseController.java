package com.littlebank.controller;

import com.littlebank.logic.BankAccount;
import com.littlebank.util.Factory;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

/**
 * Created by velichko-aa on 16.03.17.
 */
@Controller
@Slf4j
public class BaseController {

    private static final String VIEW_INDEX = "index";
    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(BaseController.class);
    private static int counter = 0;

    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public String welcome(ModelMap model) {
        model.addAttribute("message", "Welcome");
        model.addAttribute("counter", ++counter);
        logger.info("[welcome] counter : {}", counter);
        log.debug("[welcome] counter : {}", counter);

        // Spring uses InternalResourceViewResolver and return back index.jsp
        return VIEW_INDEX;

    }

//    @RequestMapping(value = "/{save}", method = RequestMethod.GET)
//    public String welcomeName(@PathVariable String save,
//                              @RequestParam String timeStart,
//                              @RequestParam String timeEnd,
//                              ModelMap model) {
//
//        model.addAttribute("message", "Welcome " + save);
//        model.addAttribute("counter", ++counter);
//        logger.debug("[welcomeName] counter : {}", counter);
//        saveData(timeStart, timeEnd);
//        return VIEW_INDEX;
//
//    }
//
//    private void saveData(String timeStart, String timeEnd) {
//        BankAccount bankAccount = new BankAccount();
//        bankAccount.setTimeStart(timeStart);
//        bankAccount.setTimeEnd(timeEnd);
//        try {
//            Factory.getInstance().getSessionsDAO().addAccount(bankAccount);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
