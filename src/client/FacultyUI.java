package client;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/**
 *
 * @author Nirav Chavda
 */
public class FacultyUI extends javax.swing.JFrame {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/exam_management";
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private final String EXAM_ID;
    private String examName;
    private String facultyUsername;

    private DefaultListModel model = new DefaultListModel();
    private PrintWriter writer;
    private Socket socket;

    public FacultyUI(String examId) {
        EXAM_ID = examId;
        initComponents();
        loadDataFromDatabase();

        try {
            socket = new Socket("localhost", 8989);
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
            writer.println(facultyUsername);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        ChatClient client = new ChatClient("localhost", 8989);
        new ClientReadHandler(socket, client, model, facultyUIChatBox).start();
    }

    private void loadDataFromDatabase() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD); Statement statement = connection.createStatement()) {
            Class.forName(JDBC_DRIVER);
            System.out.println("Creating connection...");
            System.out.println("Creating statement...");

            String sql = "SELECT examName, facultyUsername FROM exams WHERE examId='" + EXAM_ID + "'";
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            examName = rs.getString("examName");
            facultyUsername = rs.getString("facultyUsername");

            facultyUIDisplayExamID.setText("Exam ID: " + EXAM_ID);
            facultyUIDisplayExamName.setText("Exam Name: " + examName);
            facultyUIDisplayFacultyUsername.setText("Exam Name: " + facultyUsername);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        facultyUIChatBox = new javax.swing.JList<>();
        facultyUIComboBox = new javax.swing.JComboBox<>();
        facultyUIChatTextBox = new javax.swing.JTextField();
        facultyUISendButton = new javax.swing.JButton();
        facultyUIDisplayExamID = new javax.swing.JLabel();
        facultyUIDisplayExamName = new javax.swing.JLabel();
        facultyUIDisplayFacultyUsername = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(facultyUIChatBox);

        facultyUIComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        facultyUISendButton.setText("Send");
        facultyUISendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                facultyUISendButtonActionPerformed(evt);
            }
        });

        facultyUIDisplayExamID.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        facultyUIDisplayExamID.setText("Exam ID: ");

        facultyUIDisplayExamName.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        facultyUIDisplayExamName.setText("Exam Name: ");

        facultyUIDisplayFacultyUsername.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        facultyUIDisplayFacultyUsername.setText("Faculty Username: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(facultyUIDisplayExamID)
                    .addComponent(facultyUIDisplayExamName)
                    .addComponent(facultyUIDisplayFacultyUsername))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 242, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(facultyUIChatTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(facultyUISendButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)
                    .addComponent(facultyUIComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(29, 29, 29))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(facultyUIDisplayExamID)
                        .addGap(11, 11, 11)
                        .addComponent(facultyUIDisplayFacultyUsername)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(facultyUIDisplayExamName))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(facultyUIComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(facultyUIChatTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(facultyUISendButton))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void facultyUISendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_facultyUISendButtonActionPerformed
        // TODO add your handling code here:
        writer.println(facultyUIChatTextBox.getText());
        model.addElement(facultyUIChatTextBox.getText());
        facultyUIChatBox.setModel(model);
    }//GEN-LAST:event_facultyUISendButtonActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FacultyUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FacultyUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FacultyUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FacultyUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FacultyUI("HQUxLt").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> facultyUIChatBox;
    private javax.swing.JTextField facultyUIChatTextBox;
    private javax.swing.JComboBox<String> facultyUIComboBox;
    private javax.swing.JLabel facultyUIDisplayExamID;
    private javax.swing.JLabel facultyUIDisplayExamName;
    private javax.swing.JLabel facultyUIDisplayFacultyUsername;
    private javax.swing.JButton facultyUISendButton;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
