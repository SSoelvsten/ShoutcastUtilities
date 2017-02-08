package ikslorin.Clock;

import ikslorin.TXTManager;
import Config.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A window to manage the time.
 */
public class ClockManager {
    //The countdownClock and countdown
    private Clock countdownClock;
    private Clock timeClock;


    //The fields
    private JTextField hours;
    private JTextField minutes;
    private JTextField seconds;

    public ClockManager(){
        createWindow();
        initializeTimes();
    }

    private void initializeTimes(){
        seconds.setText("0");
        minutes.setText("0");
        hours.setText("0");
    }

    private void createWindow(){
        //Create the textfields
        hours = new JTextField(3);
        hours.setEditable(true);

        minutes = new JTextField(3);
        minutes.setEditable(true);

        seconds = new JTextField(3);
        seconds.setEditable(true);


        //Create the four buttons
        JButton startClockButt = new JButton("Start Clock");
        startClockButt.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {

                if (timeClock != null){
                    timeClock.stop();
                }

                final String outfile = Config.getInstance().getString("file_clock");
                timeClock = new Clock();
                timeClock.setEventHandler(new ClockEvent() {
                    @Override
                    public void onUpdate(Clock clock) {
                        TXTManager.writeFullFile(outfile, clock.toString());
                    }

                    @Override
                    public void onFinish(Clock clock){

                    }
                });

                timeClock.startClock();
            }
        });

        JButton stopClockButt = new JButton("Stop Clock");
        stopClockButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if(timeClock != null){
                    timeClock.stop();
                }
            }
        });

        JButton startCountButt = new JButton("Start Countdown");
        startCountButt.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent event) {

                 if (countdownClock != null) {
                     countdownClock.stop();
                 }

                 try {
                     int h = Integer.parseInt(hours.getText());
                     int m = Integer.parseInt(minutes.getText());
                     int s = Integer.parseInt(seconds.getText());
                     final String outfile = Config.getInstance().getString("file_countdown");
                     countdownClock = new Clock();
                     countdownClock.setTime(h, m, s);
                     countdownClock.setEventHandler(new ClockEvent() {
                         @Override
                         public void onUpdate(Clock clock) {
                             hours.setText("" + clock.getHours());
                             minutes.setText("" + clock.getMinutes());
                             seconds.setText("" + clock.getSeconds());
                             TXTManager.writeFullFile(outfile, clock.toString());
                         }
                     });

                     countdownClock.startCountdown();

                 } catch (NumberFormatException e) {
                     System.out.println(e);
                 }


             }
         });

        JButton stopCountButt = new JButton("Stop Countdown");
        stopCountButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (countdownClock != null) {
                    countdownClock.stop();
                }

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
        frame.setTitle("Clock and Countdown");
        frame.setIconImage(new ImageIcon("icon.png").getImage());

        //Usual stuff
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
