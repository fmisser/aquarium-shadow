package com.fmisser;

import com.fmisser.model.TerminalDao;
import com.fmisser.model.TerminalModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2016/2/25.
 *
 */

@Controller
@RequestMapping(value = "/terminal")
public class TerminalController {

    private static final String PAGE = "terminal page";

    @Autowired
    private TerminalDao terminalDao;

    /**
     * GET /info
     */
    @RequestMapping("/info")
    @ResponseBody
    public String info() {
        return PAGE;
    }

    /**
     * GET /create
     */
    @RequestMapping("/create")
    @ResponseBody
    public String create(String name) {
        TerminalModel model = new TerminalModel(name);
        terminalDao.save(model);
        String id = java.lang.String.valueOf(model.getId());
        return "Terminal successfully created with id= " + id;
    }

    /**
     * GET /delete
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(long id) {
        TerminalModel model = new TerminalModel(id);
        terminalDao.delete(model);
        return "Terminal successfully deleted";
    }

    /**
     * GET /get-by-name
     */
    @RequestMapping("/get-by-name")
    @ResponseBody
    public String getByName(String name) {
        List<TerminalModel> models = terminalDao.findByName(name);
        String id = String.valueOf(models.get(0).getId());
        return "The Terminal id is: " + id;
    }

    /**
     * GET /update
     */
    @RequestMapping("/update")
    @ResponseBody
    public String updateTerminal(long id, String name) {
        TerminalModel model = terminalDao.findOne(id);
        model.setName(name);
        terminalDao.save(model);
        return "Terminal successfully updated";
    }
}
