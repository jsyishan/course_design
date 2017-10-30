package main.java.UI.panels;

import main.java.Logic.DatabaseManager;
import main.java.Logic.Operator;
import main.java.Logic.OperatorManager;
import main.java.Logic.bean.Device;
import main.java.Tools.RegExpForTextField;
import main.java.UI.AppMain;
import main.java.UI.IconButton;
import main.java.UI.UIConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class OperatorPanel extends JPanel {

    private IconButton btnAddDevice;
    private IconButton btnEditDevice;

    private JTextField inputDeviceName;
    private JTextField inputDeviceNumber;

    private JTextField inputShaftPower;
    private JTextField inputMotorPower;
    private JTextField inputDevicesPower;
    private JTextField inputMotorEfficiency;
    private JTextField inputUtilizationFactor;
    private JTextField inputMotorRevSpeed;

    JTextField[] outputTextField;

    private JPanel[] panelItems;

    private IconButton btnCalculate;

    private final String[] comboBoxLabels = {
            "I",
            "II",
            "III"};

    private final DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(comboBoxLabels);

    public OperatorPanel() {
        init();
        addComponent();
        addListener();
    }

    private void init() {
        this.setBackground(UIConstants.MAIN_COLOR);
        this.setLayout(new BorderLayout());
    }

    private void addComponent() {
//        this.add(upperPanel(), BorderLayout.NORTH);
        this.add(midPanel(), BorderLayout.CENTER);
        this.add(lowerPanel(), BorderLayout.SOUTH);
    }

    private JPanel upperPanel() {
        JPanel panel = new JPanel();

        panel.setBackground(UIConstants.SUB_COLOR);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 13));

        JLabel title = new JLabel("Operating Panel");
        title.setFont(new Font("font", Font.BOLD, 20));
        panel.add(title);

        return panel;
    }

    private JPanel midPanel() {
        JPanel panelMain = new JPanel();

        JPanel panelNorth = new JPanel();

        JPanel panelCenter = new JPanel();

        panelItems = new JPanel[4];

        panelMain.setBackground(UIConstants.MAIN_COLOR);
        panelMain.setLayout(new BorderLayout());

        panelNorth.setBackground(new Color(201, 201, 201));
        panelNorth.setLayout(new GridLayout(1, 2, 20, 15));

        btnAddDevice = new IconButton(UIConstants.ICON_OPERATOR_ADD,
                "Add Device",
                "Add Device into the database");
        btnEditDevice = new IconButton(UIConstants.ICON_OPERATOR_EDIT,
                "Edit Device",
                "Edit Device from the database");

        panelNorth.add(btnAddDevice);
        panelNorth.add(btnEditDevice);

        panelCenter.setBackground(UIConstants.SSUB_COLOR);
        panelCenter.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

        for (int i = 0; i < 4; i++) {
            panelItems[i] = new JPanel();
            panelItems[i].setBackground(UIConstants.SSUB_COLOR);
        }
//        panelItems[3] = new JPanel();
//        panelItems[3].setBackground(UIConstants.MAIN_COLOR);

        panelItems[0].setLayout(new FlowLayout(FlowLayout.CENTER, 22, 13));
        panelItems[1].setLayout(new FlowLayout(FlowLayout.LEFT, 15, 12));
        panelItems[2].setLayout(new FlowLayout(FlowLayout.LEFT, 15, 12));
        panelItems[3].setLayout(new GridLayout(2, 3, 12, 12));

        JLabel labelDeviceName = new JLabel("      Device Name:    ");
        JLabel labelDeviceNumber = new JLabel("     Device Number:   ");

        inputDeviceName = new JTextField();
        inputDeviceNumber = new JTextField();

        inputDeviceName.setPreferredSize(new Dimension(125, 30));
        inputDeviceNumber.setPreferredSize(new Dimension(125, 30));

        inputDeviceNumber.setDocument(new RegExpForTextField("^(([1-9])|([1-9][0-9]))$"));
        inputDeviceNumber.setHorizontalAlignment(JTextField.CENTER);

        panelItems[0].add(labelDeviceName);
        panelItems[0].add(inputDeviceName);
        panelItems[0].add(labelDeviceNumber);
        panelItems[0].add(inputDeviceNumber);

        JLabel labelShaftPower = new JLabel("Shaft Power:");
        JLabel labelMotorPower = new JLabel("Motor Power:");
        JLabel labelDevicesPower = new JLabel("Devices Power:");
        JLabel labelMotorEfficiency = new JLabel("Motor Efficiency:");
        JLabel labelUtilizationFactor = new JLabel("Utilization Factor K1:");
        JLabel labelMotorRevSpeed = new JLabel("Motor Rev Speed:");

        JLabel labelSPUnit = new JLabel("kW");
        JLabel labelMPUnit = new JLabel("kW");
        JLabel labelDPUnit = new JLabel("kW");
        JLabel labelGEUnit = new JLabel("%");
        JLabel labelGRSUnit = new JLabel("rpm");

        labelSPUnit.setFont(new Font("font", Font.ITALIC, 15));
        labelMPUnit.setFont(new Font("font", Font.ITALIC, 15));
        labelDPUnit.setFont(new Font("font", Font.ITALIC, 15));
        labelGEUnit.setFont(new Font("font", Font.ITALIC, 15));
        labelGRSUnit.setFont(new Font("font", Font.ITALIC, 15));

        inputShaftPower = new JTextField();
        inputMotorPower = new JTextField();
        inputDevicesPower = new JTextField();
        inputMotorEfficiency = new JTextField();
        inputUtilizationFactor = new JTextField();
        inputMotorRevSpeed = new JTextField();

        inputShaftPower.setPreferredSize(new Dimension(90, 30));
        inputMotorPower.setPreferredSize(new Dimension(90, 30));
        inputDevicesPower.setPreferredSize(new Dimension(90, 30));
        inputMotorEfficiency.setPreferredSize(new Dimension(60, 30));
        inputUtilizationFactor.setPreferredSize(new Dimension(70, 30));
        inputMotorRevSpeed.setPreferredSize(new Dimension(60, 30));

        inputShaftPower.setDocument(new RegExpForTextField(
                "^(([0-9])|([0-9]\\.)|([0-9]\\.[0-9])|([0-9]\\.[0-9][1-9])|" +
                        "([0-9]\\.[0-9][0-9])|([1-9][0-9])|([1-9][0-9]\\.)|" +
                        "([1-9][0-9]\\.[0-9])|([1-9][0-9]\\.[0-9][0-9]))$"));
        inputMotorPower.setDocument(new RegExpForTextField(
                "^(([0-9])|([0-9]\\.)|([0-9]\\.[0-9])|([0-9]\\.[0-9][1-9])|" +
                        "([0-9]\\.[0-9][0-9])|([1-9][0-9])|([1-9][0-9]\\.)|" +
                        "([1-9][0-9]\\.[0-9])|([1-9][0-9]\\.[0-9][0-9]))$"));
        inputDevicesPower.setDocument(new RegExpForTextField(
                "^(([0-9])|([0-9]\\.)|([0-9]\\.[0-9])|([0-9]\\.[0-9][1-9])|" +
                        "([0-9]\\.[0-9][0-9])|([1-9][0-9])|([1-9][0-9]\\.)|" +
                        "([1-9][0-9]\\.[0-9])|([1-9][0-9]\\.[0-9][0-9]))$"));
        inputMotorEfficiency.setDocument(new RegExpForTextField(
                "^(([0-9])|([0-9]\\.)|([0-9]\\.[0-9])|([0-9]\\.[0-9][1-9])|" +
                        "([0-9]\\.[0-9][0-9])|([1-9][0-9])|([1-9][0-9]\\.)|" +
                        "([1-9][0-9]\\.[0-9])|([1-9][0-9]\\.[0-9][0-9]))$"));
        inputUtilizationFactor.setDocument(new RegExpForTextField(
                "^((1)|(1\\.)|(1\\.0)|(0)|(0\\.)|(0\\.[0-9])|(0\\.[0-9][1-9])|(0\\.[1-9][0-9]))$"));
        inputMotorRevSpeed.setDocument(new RegExpForTextField(
                "^[0-9/]+$"));

        inputShaftPower.setHorizontalAlignment(JTextField.CENTER);
        inputMotorPower.setHorizontalAlignment(JTextField.CENTER);
        inputDevicesPower.setHorizontalAlignment(JTextField.CENTER);
        inputMotorEfficiency.setHorizontalAlignment(JTextField.CENTER);
        inputUtilizationFactor.setHorizontalAlignment(JTextField.CENTER);
        inputMotorRevSpeed.setHorizontalAlignment(JTextField.CENTER);

        inputUtilizationFactor.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if(ifInputValueInvalid(inputUtilizationFactor.getText())) {
                    inputUtilizationFactor.setText("");
                }
            }
        });

        panelItems[1].add(labelShaftPower);
        panelItems[1].add(inputShaftPower);
        panelItems[1].add(labelSPUnit);

        panelItems[1].add(labelMotorPower);
        panelItems[1].add(inputMotorPower);
        panelItems[1].add(labelMPUnit);

        panelItems[1].add(labelDevicesPower);
        panelItems[1].add(inputDevicesPower);
        panelItems[1].add(labelDPUnit);

        panelItems[2].add(labelMotorEfficiency);
        panelItems[2].add(inputMotorEfficiency);
        panelItems[2].add(labelGEUnit);

        panelItems[2].add(labelMotorRevSpeed);
        panelItems[2].add(inputMotorRevSpeed);
        panelItems[2].add(labelGRSUnit);

        panelItems[2].add(labelUtilizationFactor);
        panelItems[2].add(inputUtilizationFactor);

        panelItems[3].add(statusPanel("Navigating"));
        panelItems[3].add(statusPanel("Weighing"));
        panelItems[3].add(statusPanel("Berthing"));
        panelItems[3].add(statusPanel("Stevedoring"));
        panelItems[3].add(statusPanel("Emergency"));

        panelItems[3].add(resultPanel());

        for (int i = 0; i < 4; i++) {
            panelCenter.add(panelItems[i]);
        }

        panelMain.add(panelNorth, BorderLayout.NORTH);
        panelMain.add(panelCenter, BorderLayout.CENTER);

        return panelMain;
    }

    private JPanel statusPanel(String statusName) {
        JPanel panel = new JPanel();

        JPanel[] panelItems = new JPanel[4];

        panel.setBackground(UIConstants.MAIN_COLOR);
        panel.setLayout(new GridLayout(4, 1));

        for (int i = 0; i < 4; i++) {
            panelItems[i] = new JPanel();
            panelItems[i].setBackground(Color.LIGHT_GRAY);
            panelItems[i].setLayout(new FlowLayout(FlowLayout.LEFT, 15, 8));
        }

        JLabel labelNavigating = new JLabel(statusName);
        labelNavigating.setFont(new Font("font", Font.BOLD, 20));
        JLabel labelCoincidenceFactorK0 = new JLabel("Coincidence Factor K0:       ");
        JLabel labelMachineLoadFactorK2 = new JLabel("Machine Load Factor K2:    ");
        JLabel labelLoadType = new JLabel("Load Type:                           ");

        JTextField inputCoincidenceFactorK0 = new JTextField();
        JTextField inputMachineLoadFactorK2 = new JTextField();

        inputCoincidenceFactorK0.setPreferredSize(new Dimension(60, 30));
        inputMachineLoadFactorK2.setPreferredSize(new Dimension(60, 30));

        inputCoincidenceFactorK0.setDocument(new RegExpForTextField(
                "^((1)|(1\\.)|(1\\.0)|(0)|(0\\.)|(0\\.[0-9])|(0\\.[0-9][1-9])|(0\\.[1-9][0-9]))$"));
        inputMachineLoadFactorK2.setDocument(new RegExpForTextField(
                "^((1)|(1\\.)|(1\\.0)|(0)|(0\\.)|(0\\.[0-9])|(0\\.[0-9][1-9])|(0\\.[1-9][0-9]))$"));

        inputCoincidenceFactorK0.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if(ifInputValueInvalid(inputCoincidenceFactorK0.getText())) {
                    inputCoincidenceFactorK0.setText("");
                }
            }
        });

        inputMachineLoadFactorK2.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if(ifInputValueInvalid(inputMachineLoadFactorK2.getText())) {
                    inputMachineLoadFactorK2.setText("");
                }
            }
        });

        inputCoincidenceFactorK0.setHorizontalAlignment(JTextField.CENTER);
        inputMachineLoadFactorK2.setHorizontalAlignment(JTextField.CENTER);

        JComboBox<String> comboBoxLoadType = new JComboBox<>(comboBoxModel);

        panelItems[0].add(labelNavigating);
        panelItems[1].add(labelCoincidenceFactorK0);
        panelItems[1].add(inputCoincidenceFactorK0);
        panelItems[2].add(labelMachineLoadFactorK2);
        panelItems[2].add(inputMachineLoadFactorK2);
        panelItems[3].add(labelLoadType);
        panelItems[3].add(comboBoxLoadType);

        for (int i = 0; i < 4; i++) {
            panel.add(panelItems[i]);
        }

        return panel;
    }

    private JPanel resultPanel() {
        JPanel panel = new JPanel();

        JPanel[] panelItems = new JPanel[6];
        for (int i = 0; i < 6; i++) {
            panelItems[i] = new JPanel();

            panelItems[i].setBackground(Color.LIGHT_GRAY);
            panelItems[i].setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
        }

        panel.setBackground(UIConstants.MAIN_COLOR);
        panel.setLayout(new GridLayout(6, 1));


        JLabel labelTitle = new JLabel("Result");

        JLabel[] labelEachStatusPower = new JLabel[5];
        labelEachStatusPower[0] = new JLabel("航行所需总功率:    ");
        labelEachStatusPower[1] = new JLabel("起锚所需总功率:    ");
        labelEachStatusPower[2] = new JLabel("停泊所需总功率:    ");
        labelEachStatusPower[3] = new JLabel("装卸货所需总功率: ");
        labelEachStatusPower[4] = new JLabel("应急所需总功率:    ");

        JLabel[] labelUnit = new JLabel[5];

        for (int i = 0; i < 5; i++) {
            labelUnit[i] = new JLabel("kW");
            labelUnit[i].setFont(new Font("font", Font.ITALIC, 15));
        }

        labelTitle.setFont(new Font("font", Font.BOLD, 20));

        outputTextField = new JTextField[5];

        panelItems[0].add(labelTitle);

        for (int i = 0; i < 5; i++) {
            outputTextField[i] = new JTextField();
            outputTextField[i].setEditable(false);
            outputTextField[i].setPreferredSize(new Dimension(70, 30));
            outputTextField[i].setHorizontalAlignment(JTextField.CENTER);
            panelItems[i + 1].add(labelEachStatusPower[i]);
            panelItems[i + 1].add(outputTextField[i]);
            panelItems[i + 1].add(labelUnit[i]);
        }

        for (int i = 0; i < 6; i++) {
            panel.add(panelItems[i]);
        }

        return panel;
    }

    private JPanel lowerPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(UIConstants.SUB_COLOR);
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, -1));

        btnCalculate = new IconButton(UIConstants.ICON_RESULT,
                "计算",
                "calculate...");

        panel.add(btnCalculate);
        return panel;
    }

    private boolean ifInputValueInvalid(String text) {
        if (text.equals("0.") || text.equals("0.0")) {
            System.out.println("Invalid value");
            JOptionPane.showMessageDialog(
                    null,
                    "invalid value",
                    "Error Message",
                    JOptionPane.ERROR_MESSAGE);
            return true;
        }
        return false;
    }

    public void updateDataDisplay(int row) {

        Device device = DatabaseManager.getInstance().devices.get(row);

        inputDeviceName.setText((String)OperatorManager.getter(device, "DeviceName"));
        inputDeviceNumber.setText(String.valueOf((int)OperatorManager.getter(device, "Number")));

        inputShaftPower.setText(getObjectNotNull(OperatorManager.getter(device, "ShaftPower")));
        inputMotorPower.setText(getObjectNotNull(OperatorManager.getter(device, "MotorPower")));
        inputDevicesPower.setText(getObjectNotNull(OperatorManager.getter(device, "DevicesPower")));
        inputMotorEfficiency.setText(getObjectNotNull(OperatorManager.getter(device, "MotorEfficiency")));
        inputUtilizationFactor.setText(getObjectNotNull(OperatorManager.getter(device, "FactorK1")));
        inputMotorRevSpeed.setText(getObjectNotNull(OperatorManager.getter(device, "MotorRevSpeed")));


        for (int i = 0; i < 5; i++) {
            ((JTextField)((JPanel)((JPanel)panelItems[3].getComponent(i)).getComponent(2)).getComponent(1))
                    .setText(getObjectNotNull(OperatorManager.getter(device, "FactorK2", i, int.class)));

            ((JTextField)((JPanel)((JPanel)panelItems[3].getComponent(i)).getComponent(1)).getComponent(1))
                    .setText(getObjectNotNull(OperatorManager.getter(device, "FactorK0", i, int.class)));

            int index = 0;
            switch ((String)OperatorManager.getter(device, "LoadType")) {
                case "I":
                    index = 0;
                    break;
                case "II":
                    index = 1;
                    break;
                case "III":
                    index = 2;
                    break;
            }

            ((JComboBox<String>)((JPanel)((JPanel)panelItems[3].getComponent(i)).getComponent(3)).getComponent(1))
                    .setSelectedIndex(index);
        }
    }

    private String getObjectNotNull(Object obj) {
        if (obj.getClass() == String.class) {
            if (obj == "") {
                return "";
            }
            return obj.toString();
        }
        if (obj.getClass() == float.class) {
            if ((float)obj == 1.000001f || (float)obj == 100.000001f || (float)obj == 0.000001f) {
                return "";
            }
            return obj.toString();
        }
        return obj.toString();
    }

    private boolean setDataErrorHandler(JTextField obj, String errMsg) {
        if (obj.getText().equals("")) {
            JOptionPane.showMessageDialog(
                    null,
                    errMsg,
                    "Error Message",
                    JOptionPane.ERROR_MESSAGE);
            return true;
        }
        return false;
    }

    private void addListener() {

        btnAddDevice.addActionListener((e) -> {
            Device device = new Device();

            if (setDataErrorHandler(inputDeviceName, "请输入设备名称")) { return; }
            device.setDeviceName(inputDeviceName.getText());

            if (setDataErrorHandler(inputDeviceNumber, "请输入设备数量")) { return; }
            device.setNumber(Integer.parseInt(inputDeviceNumber.getText()));

            if (inputShaftPower.getText().equals("")) {
                device.setShaftPower(1.000001f);
            } else {
                device.setShaftPower(Float.parseFloat(inputShaftPower.getText()));
            }

            if (inputUtilizationFactor.getText().equals("") && inputMotorPower.getText().equals("") &&
                    inputMotorEfficiency.getText().equals("")) {
                if (!inputDevicesPower.getText().equals("")) {
                    device.setFactorK1(1.000001f);
                    device.setMotorPower(1.000001f);
                    device.setMotorEfficiency(100.000001f);
                    device.setDevicesPower(Float.parseFloat(inputDevicesPower.getText()));
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "请输入＂设备总功率＂一项＂或电机功率＂,＂电机效率＂,＂利用系数＂三项",
                            "Error Message",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else if (inputDevicesPower.getText().equals("")) {
                if (!inputUtilizationFactor.getText().equals("") && !inputMotorPower.getText().equals("") &&
                        !inputMotorEfficiency.getText().equals("")) {
                    device.setFactorK1(Float.parseFloat(inputUtilizationFactor.getText()));
                    device.setMotorPower(Float.parseFloat(inputMotorPower.getText()));
                    device.setMotorEfficiency(Float.parseFloat(inputMotorEfficiency.getText()));
                    device.setDevicesPower(1.000001f);
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "请输入＂设备总功率＂一项＂或电机功率＂,＂电机效率＂,＂利用系数＂三项",
                            "Error Message",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(
                        null,
                        "请输入＂设备总功率＂一项＂或电机功率＂,＂电机效率＂,＂利用系数＂三项",
                        "Error Message",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (inputMotorRevSpeed.getText().equals("")) {
                device.setMotorRevSpeed("");
            } else {
                device.setMotorRevSpeed(inputMotorRevSpeed.getText());
            }

            for (int i = 0; i < 5; i++) {

                String k2 = ((JTextField)((JPanel)((JPanel)panelItems[3].
                        getComponent(i)).getComponent(2)).getComponent(1)).
                        getText();

                String k0 = ((JTextField)((JPanel)((JPanel)panelItems[3].
                        getComponent(i)).getComponent(1)).getComponent(1)).
                        getText();

                if (k2.equals("")) {
                    device.setFactorK2(i, 0.0f);
                } else {
                    device.setFactorK2(i, Float.parseFloat(k2));
                }
                if (k0.equals("")) {
                    device.setFactorK0(i, 0.0f);
                } else {
                    device.setFactorK0(i, Float.parseFloat(k0));
                }

                device.setLoadType(((JComboBox<String>)((JPanel)((JPanel)panelItems[3].
                        getComponent(i)).getComponent(3)).getComponent(1)).getSelectedItem().toString());
            }

            AppMain.databaseManager.insertDevice(device);
//            System.out.println(device);

        });

        btnEditDevice.addActionListener((e) -> {

        });

        btnCalculate.addActionListener((e) -> {
            Operator operator = new Operator();

            for (int i = 0; i < 5; i++) {
                outputTextField[i].setText(String.valueOf(operator.calculate(i)));
            }
        });
    }
}
