package ikslorin.Clock;

import java.util.Timer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A window to manage the time.
 */
public class ClockManager {
    //The clock and countdown
    private Clock clock;
    private Countdown countdown;

    private Timer timer;

    //Are they already running?
    private boolean clockActive;
    private boolean countActive;

    //The fields
    private JTextField hours;
    private JTextField minutes;
    private JTextField seconds;

    public ClockManager(){
        clock = new Clock();
        countdown = new Countdown(this);

        timer = new Timer();

        clockActive = false;
        countActive = false;

        createWindow();
    }

    private void createWindow(){
        //Create the textfields
        hours = new JTextField(3);
        hours.setEditable(true);

        minutes = new JTextField(3);
        minutes.setEditable(true);

        seconds = new JTextField(3);
        seconds.setEditable(true);

        reloadCounters();

        //Create the four buttons
        JButton startClockButt = new JButton("Start Clock");
        startClockButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if(!clockActive) {
                    timer.scheduleAtFixedRate(clock, 0, 1000);
                }
            }
        });

        JButton stopClockButt = new JButton("Stop Clock");
        stopClockButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                clock.cancel();
            }
        });

        JButton startCountButt = new JButton("Start Countdown");
        startCountButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if(!countActive) {
                    try {
                        int h = Integer.parseInt(hours.getText());
                        int m = Integer.parseInt(minutes.getText());
                        int s = Integer.parseInt(seconds.getText());

                        countdown.setCountDown(h, m, s);

                        timer.scheduleAtFixedRate(countdown, 0, 1000);
                    } catch (NumberFormatException e) {
                        reloadCounters();
                        hours.setText("" + 0);
                    }
                }
            }
        });

        JButton stopCountButt = new JButton("Stop Countdown");
        stopCountButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                countdown.cancel();
                reloadCounters();
            }
        });

        //Create a panel for the fields
        JPanel countFields = new JPanel();
        countFields.setLayout(new GridLayout(3,2));
        countFields.add(new JLabel("Hours: "));
        countFields.add(hours);
        countFields.add(new JLabel("Minutes: "));
        countFields.add(minutes);
        countFields.add(new JLabel("Seconds: "));
        countFields.add(seconds);

        //Create a panel for the buttons
        JPanel buttPanel = new JPanel();
        buttPanel.setLayout(new GridLayout(2,2));
        buttPanel.add(stopClockButt);
        buttPanel.add(startClockButt);
        buttPanel.add(stopCountButt);
        buttPanel.add(startCountButt);

        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.add(countFields, BorderLayout.WEST);
        frame.add(buttPanel, BorderLayout.EAST);

        //Give the window a title and an icon (though the latter doesn't want to work)
        frame.setTitle("Countdowner and Countdown");
        frame.setIconImage(new ImageIcon("icon.png").getImage());

        //Usual stuff
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void reloadCounters(){
        hours.setText("" + countdown.getHours());
        minutes.setText("" + countdown.getMinutes());
        seconds.setText("" + countdown.getSeconds());
    }
}
