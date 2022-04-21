package de.swm;

import javax.swing.*;
import java.awt.*;

public class View {

    private JTable tblTimes;
    private JButton btnPushAndCalc;
    private JTextField txtBegin;
    private JTextField txtEnd;
    private JFrame frame;
    private JButton btnGetTime;
    private JTextField txtUserId;

    public View(){
        frame = new JFrame("TimeCalc");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel lblBegin = new JLabel("Startzeit: hh:mm");
        JLabel lblEnd = new JLabel("Endzeit: hh:mm");
        JLabel lblUserid = new JLabel("Mitarbeiter ID:");

        JPanel pnlTimeCalc = new JPanel();
        JPanel pnlTimeCalcLeft = new JPanel(new BorderLayout(1,1));
        JPanel pnlTimeCalcRight = new JPanel(new GridLayout(20,1));
        JSplitPane pnlTimeCalcSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        tblTimes = new JTable();

        btnPushAndCalc = new JButton("Pushen");
        btnGetTime = new JButton("Aktualisieren");
        txtBegin = new JTextField();
        txtEnd = new JTextField();
        txtUserId = new JTextField();

        pnlTimeCalcLeft.add(new JScrollPane(tblTimes));
        pnlTimeCalcSplit.setLeftComponent(pnlTimeCalcLeft);
        pnlTimeCalcSplit.setRightComponent(pnlTimeCalcRight);
        pnlTimeCalc.add(pnlTimeCalcSplit);

        pnlTimeCalcRight.add(lblUserid);
        pnlTimeCalcRight.add(txtUserId);
        pnlTimeCalcRight.add(lblBegin);
        pnlTimeCalcRight.add(txtBegin);
        pnlTimeCalcRight.add(lblEnd);
        pnlTimeCalcRight.add(txtEnd);
        pnlTimeCalcRight.add(btnPushAndCalc);
        pnlTimeCalcRight.add(btnGetTime);

        frame.setSize(650, 600);
        frame.add(pnlTimeCalc);
        frame.setVisible(true);

    }


//region getter/setter
    public JTextField getTxtUserId(){
        return txtUserId;
    }

    public JButton getBtnGetTime(){
        return btnGetTime;
    }

    public JTable getTblTimes() {
        return tblTimes;
    }

    public void setTblTimes(JTable tblTimes) {
        this.tblTimes = tblTimes;
    }

    public JButton getBtnPushAndCalc() {
        return btnPushAndCalc;
    }

    public void setBtnPushAndCalc(JButton btnPushAndCalc) {
        this.btnPushAndCalc = btnPushAndCalc;
    }

    public JTextField getTxtBegin() {
        return txtBegin;
    }

    public void setTxtBegin(JTextField txtBegin) {
        this.txtBegin = txtBegin;
    }

    public JTextField getTxtEnd() {
        return txtEnd;
    }

    public void setTxtEnd(JTextField txtEnd) {
        this.txtEnd = txtEnd;
    }

    public JFrame getFrame() {
        return frame;
    }
    public void setFrame(JFrame frame) {
        this.frame = frame;
    }
//endregion

}
