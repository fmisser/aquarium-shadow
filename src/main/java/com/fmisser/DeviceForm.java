package com.fmisser;

import com.fmisser.model.Device;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Created by Administrator on 2016/2/29.
 *
 */

public class DeviceForm extends FormLayout {

    Button btnSave;
    Button btnCancel;
    @PropertyId("name") TextField tfName;
    @PropertyId("type") TextField tfType;
    @PropertyId("status") TextField tfStatus;
    @PropertyId("number") TextField tfNumber;
    @PropertyId("intro") TextField tfIntro;

    Device device;
    BeanFieldGroup<Device> deviceBeanFieldGroup;

    public DeviceForm() {
        configComponents();
        buildLayout();
    }

    private void configComponents() {
        btnSave = new Button("保存", this::btnSaveClick);
        btnCancel = new Button("取消", this::btnCancelClick);
        tfName = new TextField("设备名称");
        tfType = new TextField("设备类型");
        tfStatus = new TextField("设备状态");
        tfNumber = new TextField("设备号");
        tfIntro = new TextField("设备简介");
        btnSave.setStyleName(ValoTheme.BUTTON_PRIMARY);
        btnSave.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        setVisible(false);
    }

    private void buildLayout() {
        setSizeUndefined();
        setMargin(true);
        HorizontalLayout actions = new HorizontalLayout(btnSave, btnCancel);
        actions.setSpacing(true);
        addComponents(actions, tfName, tfType, tfStatus, tfNumber, tfIntro);
    }

    public void btnSaveClick(Button.ClickEvent event) {
        try {
            deviceBeanFieldGroup.commit();
            getUI().deviceRepository.save(device);
            getUI().refreshDevices();
        } catch (FieldGroup.CommitException e) {
            e.printStackTrace();
        }
    }

    public void btnCancelClick(Button.ClickEvent event) {
        getUI().gridContentList.select(null);
    }

    public void edit(Device device) {
        this.device = device;
        if (device != null) {
            deviceBeanFieldGroup = BeanFieldGroup.bindFieldsBuffered(device, this);
            tfName.focus();
        }
        setVisible(device != null);
    }

    @Override
    public DeviceUI getUI() {
        return (DeviceUI) super.getUI();
    }

}
