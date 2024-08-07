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

        tableModel = new DefaultTableModel(new Object[]{"Assignment Name", "Due Date"}, 0);
        assignmentTable = new JTable(tableModel);

        assignmentTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        assignmentTable.setFont(new Font("Arial", Font.PLAIN, 12));
        assignmentTable.setRowHeight(25);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        assignmentTable.setDefaultRenderer(Object.class, centerRenderer);

        JScrollPane scrollPane = new JScrollPane(assignmentTable);
        add(scrollPane, BorderLayout.CENTER);

        nextButton = new JButton("Proceed to Download");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.showDownloadPanel();
            }
        });
        add(nextButton, BorderLayout.SOUTH);
    }

    public void updateAssignments(List<Assignment> assignments) {
        tableModel.setRowCount(0);
        for (Assignment assignment : assignments) {
            tableModel.addRow(new Object[]{assignment.getName(), assignment.getDueDate()});
        }
    }
}