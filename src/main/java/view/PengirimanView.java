package view;

import pengiriman.PengirimanController;
import pengiriman.PengirimanDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PengirimanView extends JFrame{

    private JTextField noResiField;
    private JTextField alamatField;
    private JTextField pembeliField;
    private PengirimanController pengirimanController;
    public void initialize(){
        setTitle("Fullscreen Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // insisialisasi controller
        pengirimanController = new PengirimanController();

        // inisialisasi input form dan button
        noResiField = new JTextField(20);
        alamatField = new JTextField(5);

        JButton submitButton = new JButton("Submit");
        JButton closeButton = new JButton("Close");

        // inisialisasi input panel dengan inputan yang sudah di buat
        GroupLayout layoutInput = new GroupLayout(getContentPane());
        getContentPane().setLayout(layoutInput);

        layoutInput.setAutoCreateGaps(true);
        layoutInput.setAutoCreateContainerGaps(true);

        // Create horizontal group
        GroupLayout.SequentialGroup hGroup = layoutInput.createSequentialGroup();
        hGroup.addGroup(layoutInput.createParallelGroup()
                .addComponent(new JLabel("No Resi"))
                .addComponent(new JLabel("Alamat")));
        hGroup.addGroup(layoutInput.createParallelGroup()
                .addComponent(noResiField)
                .addComponent(alamatField)
                .addGroup(layoutInput.createSequentialGroup()
                        .addComponent(submitButton)
                        .addComponent(closeButton)));
        layoutInput.setHorizontalGroup(hGroup);

        // Create vertical group
        GroupLayout.SequentialGroup vGroup = layoutInput.createSequentialGroup();
        vGroup.addGroup(layoutInput.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(new JLabel("No Resi"))
                .addComponent(noResiField));
        vGroup.addGroup(layoutInput.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(new JLabel("Alamat"))
                .addComponent(alamatField));
        vGroup.addGroup(layoutInput.createParallelGroup()
                .addComponent(submitButton)
                .addComponent(closeButton));
        layoutInput.setVerticalGroup(vGroup);

        // menambahkan listener action submit button
        submitButton.addActionListener(new ActionListener() {

            String msg = "Berhasil Menyimpan data";
            boolean isError = false;

            @Override
            public void actionPerformed(ActionEvent e) {
                PengirimanDTO pengirimanDTO = new PengirimanDTO();
                pengirimanDTO.setNoResi(noResiField.getText());
                pengirimanDTO.setAlamat(alamatField.getText());
                pengirimanDTO.setNamaPembeli(pembeliField.getText());

                try {
                    pengirimanController.savePengiriman(pengirimanDTO);
                } catch (Exception ex){
                    isError = true;
                    msg = ex.getMessage();
                }

                JOptionPane.showMessageDialog(PengirimanView.this, msg);
            }
        });

        // menambahkan listener action closeButton
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setSize(300, 150);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
