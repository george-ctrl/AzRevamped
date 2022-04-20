package de.swm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Controller {
    public Controller(){
        initAction();
    }

    private View view = new View();
    private SQLHandler sqlHandler = new SQLHandler("localhost:3306", "root", "");


    public void initAction(){
        view.getBtnPushAndCalc().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TimeModel timeModel = new TimeModel();
                try {
                    timeModel.calcTimeWorked(view.getTxtBegin().getText(),view.getTxtEnd().getText(),Integer.parseInt(view.getTxtUserId().getText()));
                    sqlHandler.sqlPushTime(timeModel);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Bitte gueltiges Stundenformat benutzen");
                }


            }
        });

        view.getBtnGetTime().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {


                TimeTableModel model = new TimeTableModel(sqlHandler.sqlGetTime(Integer.parseInt(view.getTxtUserId().getText())));
                view.getTblTimes().setModel(model);
            }
                catch (NumberFormatException nbrEx){
                    JOptionPane.showMessageDialog(null, "Bitte eine gueltige Mitarbeiter ID angeben");
                }
            }
        });

    }
}
