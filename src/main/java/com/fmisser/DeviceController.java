package com.fmisser;

import com.fmisser.model.DeviceRepository;
import com.fmisser.model.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2016/2/25.
 *
 */

@Controller
@RequestMapping(value = "/device")
public class DeviceController {

    private static final String PAGE = "device page";

    @Autowired
    private DeviceRepository deviceRepository;

    /**
     * GET /index
     */
    @RequestMapping("")
    @ResponseBody
    public String index() {
        return PAGE;
    }

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
    public String create(String name, long type, long status, String number, String intro) {
        Device device;
        if (StringUtils.isEmpty(intro)) {
            device = new Device(name, type, status, number);
        } else {
            device = new Device(name, type, status, number, intro);
        }
        deviceRepository.save(device);
        String id = java.lang.String.valueOf(device.getId());
        return "Terminal successfully created with id= " + id;
    }

    /**
     * GET /delete
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(long id) {
        deviceRepository.delete(id);
        return "Terminal successfully deleted";
    }

    /**
     * GET /get-by-name
     */
    @RequestMapping("/get-by-name")
    @ResponseBody
    public String getByName(String name) {
        List<Device> models = deviceRepository.findByName(name);
        String id = String.valueOf(models.get(0).getId());
        return "The Terminal id is: " + id;
    }

    /**
     * GET /update
     */
    @RequestMapping("/update")
    @ResponseBody
    public String updateTerminal(long id, String name) {
        Device model = deviceRepository.findOne(id);
        model.setName(name);
        deviceRepository.save(model);
        return "Terminal successfully updated";
    }
}
