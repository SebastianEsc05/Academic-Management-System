/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.panels;
import components.Student;
import gui.MainFrame;
import gui.styles.Panel;
import gui.styles.Button;
import gui.styles.Style;
import interfaces.IPersistenceFacade;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import persistences.PersistenceStudents;
import structures.ArrayList;

/**
 *
 * @author david
 */
public final class ReportPanel extends Panel {
    private Button btnListStudents;
    private Button btnListAverageStudents;
    private JTextArea txtAreaReportStudents;
    private JScrollPane jsp;
    private IPersistenceFacade persistenceFacade;

    public ReportPanel(MainFrame frame, NorthPanel northPanel, IPersistenceFacade persistenceFacade) {
        super(frame, northPanel, persistenceFacade);
        this.persistenceFacade = persistenceFacade;
    }

    @Override
    public void startComponents() {
        JPanel panelButtoms = new JPanel(new FlowLayout());
        btnListStudents = new Button("Listar Estudiantes");
        btnListAverageStudents = new Button("Listar Estudiantes por Promedio");
        panelButtoms.add(btnListStudents);
        panelButtoms.add(btnListAverageStudents);
        txtAreaReportStudents = new JTextArea(19, 92);
        txtAreaReportStudents.setMargin(new Insets(100, 0, 0, 0));
        txtAreaReportStudents.setLineWrap(true);
        txtAreaReportStudents.setWrapStyleWord(true);
        txtAreaReportStudents.setEditable(false);
        txtAreaReportStudents.setFont(Style.INPUT_FONT);
        txtAreaReportStudents.setMargin(new Insets(20, 30, 20, 15));
        JScrollPane jsp = new JScrollPane(txtAreaReportStudents,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(panelButtoms, BorderLayout.NORTH);
        add(jsp, BorderLayout.CENTER);


        btnListStudents.addActionListener((ActionEvent e) -> {
            showStudents();
        });

        btnListAverageStudents.addActionListener((ActionEvent e) -> {
            showStudentsByAverage();
        });
    }

    public void showStudents() {
        txtAreaReportStudents.setText("ESTUDIANTES REGISTRADOS EN EL SISTEMA\n");
        PersistenceStudents persistenceStudents = persistenceFacade.getPersistenceStudents();
        ArrayList<Student> students = persistenceStudents.listStudents();
        if (students.isEmpty()) {
            txtAreaReportStudents.setText("\nNo hay estudiantes registrados");
        } else {
            for (Student student: students){
                txtAreaReportStudents.append("\n" + student.toString() + "\n");
            }
        }
    }

    public void showStudentsByAverage() {
        txtAreaReportStudents.setText("ESTUDIANTES LISTADOS POR PROMEDIO\n");
        PersistenceStudents persistenceStudents = persistenceFacade.getPersistenceStudents();
        ArrayList<Student> studentsByAverage = persistenceStudents.listStudentsInOrderFromAVLTree();
        if (studentsByAverage.isEmpty()) {
            txtAreaReportStudents.setText("\nNo hay estudiantes registrados");
        } else {
                for (Student student: studentsByAverage){
                    txtAreaReportStudents.append("\n" + student.toString() + "\n");
                }
        }
    }
}
