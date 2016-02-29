package com.fmisser;

import com.fmisser.model.Device;
import com.fmisser.model.DeviceRepository;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServlet;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.servlet.annotation.WebServlet;
import java.util.Collection;

/**
 * Created by Administrator on 2016/2/29.
 *
 */

@SpringUI(path = "/ui/devices")
@Theme("valo")
public class DeviceUI extends UI {

    DeviceRepository deviceRepository;

    TextField tfFilter;
    Button btnNewDevice;
    Grid gridContentList;
    DeviceForm deviceForm;

    @Autowired
    public DeviceUI(DeviceRepository repository) {
        this.deviceRepository = repository;
        configComponents();
        buildLayout();
    }

    private void configComponents() {
        this.tfFilter = new TextField();
        this.btnNewDevice = new Button("添加", clickEvent -> deviceForm.edit(new Device()));
        this.gridContentList = new Grid();
        this.deviceForm = new DeviceForm();

        tfFilter.setInputPrompt("搜索设备名称...");
        tfFilter.addTextChangeListener(textChangeEvent -> refreshDevices(textChangeEvent.getText()));

        gridContentList.setContainerDataSource(new BeanItemContainer<>(Device.class));
        gridContentList.setColumnOrder("name", "type", "status", "number", "intro");
        gridContentList.removeColumn("id");
        gridContentList.setSelectionMode(Grid.SelectionMode.SINGLE);
        gridContentList.addSelectionListener(selectionEvent -> deviceForm.edit((Device) gridContentList.getSelectedRow()));
        refreshDevices();
    }

    private void buildLayout() {
        HorizontalLayout actions = new HorizontalLayout(tfFilter, btnNewDevice);
        actions.setWidth("100%");
        tfFilter.setWidth("100%");
        actions.setExpandRatio(tfFilter, 1);

        VerticalLayout left = new VerticalLayout(actions, gridContentList);
        left.setSizeFull();
        gridContentList.setSizeFull();
        left.setExpandRatio(gridContentList, 1);

        HorizontalLayout mainLayout = new HorizontalLayout(left, deviceForm);
        mainLayout.setSizeFull();
        mainLayout.setExpandRatio(left, 1);

        setContent(mainLayout);
    }

    void refreshDevices() {
        refreshDevices(tfFilter.getValue());
    }

    private void refreshDevices(String filterString) {
        if (StringUtils.isEmpty(filterString)) {
            gridContentList.setContainerDataSource(new BeanItemContainer(Device.class, (Collection) deviceRepository.findAll()));
        } else {
            gridContentList.setContainerDataSource(new BeanItemContainer(Device.class, deviceRepository.findByName(filterString)));
        }
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        configComponents();
        buildLayout();
    }

    @WebServlet(urlPatterns = "/*")
    @VaadinServletConfiguration(ui = DeviceUI.class, productionMode = false)
    public static class DeviceUIServlet extends VaadinServlet {

    }
}
