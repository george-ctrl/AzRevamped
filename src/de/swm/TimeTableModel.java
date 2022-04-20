
package de.swm;

import javax.swing.table.AbstractTableModel;
import java.time.LocalTime;
import java.util.ArrayList;

public class TimeTableModel extends AbstractTableModel {
    private String[] columnNames =
            {
                    "Startzeit",
                    "EndZeit",
                    "MinutenGearbeitet",
            };

    private ArrayList<TimeModel> timeModels;

    public TimeTableModel()
    {
        timeModels = new ArrayList<TimeModel>();
    }

    public TimeTableModel(ArrayList<TimeModel> timeModels)
    {
        this.timeModels = timeModels;
    }

    @Override
    public int getColumnCount()
    {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column)
    {
        return columnNames[column];
    }

    @Override
    public int getRowCount()
    {
        return timeModels.size();
    }

    @Override
    public Class getColumnClass(int column)
    {
        return switch (column) {
            case 0 -> LocalTime.class;
            case 1 -> LocalTime.class;
            case 2 -> Long.class;
            default -> throw new IllegalStateException("Unexpected value: " + column);
        };
    }

    @Override
    public boolean isCellEditable(int row, int column)
    {
        return false;
    }


    @Override
    public Object getValueAt(int row, int column)
    {
        TimeModel timeModel = getTimeModel(row);

        return switch (column) {
            case 0 -> timeModel.getTimeStart();
            case 1 -> timeModel.getTimeEnd();
            case 2 -> timeModel.getTimeWorkedMinutes();
            default -> null;
        };
    }

    @Override
    public void setValueAt(Object value, int row, int column)
    {
    }

    public TimeModel getTimeModel(int row)
    {
        return timeModels.get(row);
    }









}

