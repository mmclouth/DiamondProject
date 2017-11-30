import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by kenziemclouth on 11/28/17.
 */
public class TriageFrame extends JFrame implements ActionListener {


    private Container container;
    private JButton enterButton;
    private JButton startButton;
    private JButton newButton;
    private JPanel startPanel;
    private JPanel inputPanel;
    private JPanel outputPanel;
    private GridLayout gridLayout1;
    private JTextField hbField;
    private JTextField bpField;
    private JTextField airwayField;
    private JTextField rrField;
    private JTextField glascowField;
    private JTextField bleedingField;

    private ArrayList<JLabel> taskLabels = new ArrayList<>();
    private ArrayList<JLabel> taskNameLabels = new ArrayList<>();

    public TriageFrame(){

        super("Triage");
        container = getContentPane();

        GridBagLayout gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);

        GridBagConstraints constr = new GridBagConstraints();


        //Start defining startPanel
        startPanel = new JPanel();

        //set gridLayout1 instance variabe to a new Gridlayout object
        gridLayout1 = new GridLayout(2,1,2,2);
        startPanel.setLayout(gridLayout1);

        JLabel clickToStart = new JLabel("Click to Start Program ");
        startPanel.add(clickToStart);

        startButton = new JButton("Start");
        startButton.addActionListener(this);
        startPanel.add(startButton);

        constr.fill = GridBagConstraints.HORIZONTAL;
        constr.gridx = 0;
        constr.gridy = 3;

        startPanel.setSize(400, 100);
        container.add(startPanel, constr);




        //Start defining inputPanel
        inputPanel = new JPanel();

        gridLayout1 = new GridLayout(7,2,2,2);
        inputPanel.setLayout(gridLayout1);

        JLabel hb = new JLabel("Heart Rate: ", SwingConstants.RIGHT);
        inputPanel.add(hb);

        hbField = new JTextField("");
        //hbField.setDocument(new JTextFieldLimit(1, true));
        hbField.addActionListener(this);
        inputPanel.add(hbField);

        JLabel bloodPressure = new JLabel("Blood Pressure: ", SwingConstants.RIGHT);
        inputPanel.add(bloodPressure);

        bpField = new JTextField("");
        bpField.addActionListener(this);
        inputPanel.add(bpField);

        JLabel airway = new JLabel("Is patient's airway blocked? (y/n) ", SwingConstants.RIGHT);
        inputPanel.add(airway);

        airwayField = new JTextField("");
        airwayField.addActionListener(this);
        inputPanel.add(airwayField);

        JLabel rr = new JLabel("Respiratory Rate: ", SwingConstants.RIGHT);
        inputPanel.add(rr);

        rrField = new JTextField("");
        rrField.addActionListener(this);
        inputPanel.add(rrField);

        JLabel bleeding = new JLabel("Is patient bleeding excessively? (y/n) ", SwingConstants.RIGHT);
        inputPanel.add(bleeding);

        bleedingField = new JTextField("");
        bleedingField.addActionListener(this);
        inputPanel.add(bleedingField);

        JLabel glascow = new JLabel("Glascow coma: ", SwingConstants.RIGHT);
        inputPanel.add(glascow);

        glascowField = new JTextField("");
        glascowField.addActionListener(this);
        inputPanel.add(glascowField);


        enterButton = new JButton("Enter");
        enterButton.addActionListener(this);
        inputPanel.add(enterButton);

        constr.fill = GridBagConstraints.HORIZONTAL;
        constr.gridx = 2;
        constr.gridy = 3;

        inputPanel.setSize(400, 100);
        inputPanel.setVisible(false);
        container.add(inputPanel, constr);




        //Start formatting for output panel
        outputPanel = new JPanel();

        gridLayout1 = new GridLayout(19,3,2,2);
        GridBagLayout gridbaglayouttest = new GridBagLayout();
        outputPanel.setLayout(gridbaglayouttest);

        for(int i=0 ; i<18 ; i++){
            taskNameLabels.add(new JLabel(""));
        }

        taskLabels.add(new JLabel("1st task: ", SwingConstants.RIGHT));
        taskLabels.add(new JLabel("2nd task: ", SwingConstants.RIGHT));
        taskLabels.add(new JLabel("3rd task: ", SwingConstants.RIGHT));
        taskLabels.add(new JLabel("4th task: ", SwingConstants.RIGHT));
        taskLabels.add(new JLabel("5th task: ", SwingConstants.RIGHT));
        taskLabels.add(new JLabel("6th task: ", SwingConstants.RIGHT));
        taskLabels.add(new JLabel("7th task: ", SwingConstants.RIGHT));
        taskLabels.add(new JLabel("8th task: ", SwingConstants.RIGHT));
        taskLabels.add(new JLabel("9th task: ", SwingConstants.RIGHT));
        taskLabels.add(new JLabel("10th task: ", SwingConstants.RIGHT));
        taskLabels.add(new JLabel("11th task: ", SwingConstants.RIGHT));
        taskLabels.add(new JLabel("12th task: ", SwingConstants.RIGHT));
        taskLabels.add(new JLabel("13th task: ", SwingConstants.RIGHT));
        taskLabels.add(new JLabel("14th task: ", SwingConstants.RIGHT));
        taskLabels.add(new JLabel("15th task: ", SwingConstants.RIGHT));
        taskLabels.add(new JLabel("16th task: ", SwingConstants.RIGHT));
        taskLabels.add(new JLabel("17th task: ", SwingConstants.RIGHT));
        taskLabels.add(new JLabel("18th task: ", SwingConstants.RIGHT));


        for(int i=0 ; i<18 ; i++){

            constr.fill = GridBagConstraints.NORTHWEST;
            constr.gridx = 0;
            constr.gridy = i;

            outputPanel.add(taskLabels.get(i), constr);

            constr.gridx = 1;

            outputPanel.add(taskNameLabels.get(i), constr);
        }


        newButton = new JButton("New Patient");
        newButton.addActionListener(this);
        outputPanel.add(newButton);


        constr.fill = GridBagConstraints.VERTICAL;
        constr.gridx = 0;
        constr.gridy = 0;
        constr.anchor = GridBagConstraints.NORTHWEST;

        outputPanel.setPreferredSize(outputPanel.getPreferredSize());
        outputPanel.setSize(outputPanel.preferredSize());
        outputPanel.setVisible(false);


        container.add(outputPanel, constr);

    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource().equals(startButton)) {


            startPanel.setVisible(false);

            inputPanel.setVisible(true);
        } else if(e.getSource().equals(enterButton)){

            int heartRate = Integer.parseInt(hbField.getText());
            int bloodPressure = Integer.parseInt(bpField.getText());
            String airway = airwayField.getText();
            int respRate = Integer.parseInt(rrField.getText());
            int glascow = Integer.parseInt(glascowField.getText());
            String bleeding = bleedingField.getText();

            Patient patient = new Patient();
            ArrayList<String> orderedTasks = patient.triage(heartRate, bloodPressure, respRate, glascow, airway, bleeding);

            for(int i=0 ; i<18 ; i++){

                if(i<orderedTasks.size()){

                    taskLabels.get(i).setVisible(true);
                    taskNameLabels.get(i).setText(orderedTasks.get(i));
                    taskNameLabels.get(i).setVisible(true);

                } else {
                    taskLabels.get(i).setVisible(false);
                    taskNameLabels.get(i).setVisible(false);
                }

            }

            container.setPreferredSize(container.getPreferredSize());
            container.setSize(container.getPreferredSize());
            inputPanel.setVisible(false);
            outputPanel.setVisible(true);

        } else if(e.getSource().equals(newButton)){

            hbField.setText("");
            bpField.setText("");
            airwayField.setText("");
            rrField.setText("");
            glascowField.setText("");
            bleedingField.setText("");

            startPanel.setVisible(false);
            outputPanel.setVisible(false);
            inputPanel.setVisible(true);

        }
    }

}
