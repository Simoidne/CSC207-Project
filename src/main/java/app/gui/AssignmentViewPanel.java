package app.gui;

import entity.Assignment;
import app.CalendarController;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AssignmentViewPanel extends JPanel {
    private CalendarController controller;
    private JTable assignmentTable;
    private DefaultTableModel tableModel;
    private JButton nextButton;

    public AssignmentViewPanel(CalendarController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        //create table model with column name
        tableModel = new DefaultTableModel(new Object[]{"Assignment Name", "Due Date"}, 0);

        //create the JTable
        assignmentTable = new JTable(tableModel);
        assignmentTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        assignmentTable.setFont(new Font("Arial", Font.PLAIN, 12));
        assignmentTable.setRowHeight(25);

        //centers the text
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        assignmentTable.setDefaultRenderer(Object.class, centerRenderer);

        //add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(assignmentTable);
        add(scrollPane, BorderLayout.CENTER);

        //generates a next button
        nextButton = new JButton("Proceed to Download");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.showDownloadPanel();
            }
        });
        add(nextButton, BorderLayout.SOUTH);
    }

    //method to update the table with assignment
    public void updateAssignments(List<Assignment> assignments) {
        tableModel.setRowCount(0);
        for (Assignment assignment : assignments) {
            tableModel.addRow(new Object[]{assignment.getName(), assignment.getDueDate()});
        }
    }
}