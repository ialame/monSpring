package com.pca.gui;

import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class MaDate extends JPanel {

    public static void main(String[] args) {
        JFrame frame = new JFrame("JXPicker Example");
        JPanel panel = new JPanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(400, 400, 250, 100);

        JXDatePicker picker = new JXDatePicker();
        picker.setDate(Calendar.getInstance().getTime());
        picker.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
        Date d = picker.getDate();
        Instant instant = Instant.ofEpochMilli(d.getTime());
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        LocalDate ld = localDateTime.toLocalDate();
        panel.add(picker);
        frame.getContentPane().add(panel);

        frame.setVisible(true);
    }
}