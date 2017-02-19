package JFrameControllers;

import Observer.TimerObserver;
import Time.*;
import Time.Timer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

public class JPanelTimerController implements TimerObserver {

    private ModifiableTimer timer;

    private JPanel panel = new JPanel(new BorderLayout());

    private int fieldsize = 3;
    private JTextField secondField = new JTextField(fieldsize);
    private JTextField minuteField = new JTextField(fieldsize);
    private JTextField hourField = new JTextField(fieldsize);
    private JTextField tickField = new JTextField(7);

    private int strategyIndex = 0;

    public JPanelTimerController(ModifiableTimer timer,
                                 List<NameCalcStrategyPair> calcStrats){
        this.timer = timer;
        timer.subscribe(this);

        JPanel valuesPanel = new JPanel();

        valuesPanel.add(new JLabel("hours"));
        valuesPanel.add(hourField);
        hourField.setText("" + timer.getHour());

        valuesPanel.add(new JLabel("minutes"));
        valuesPanel.add(minuteField);
        minuteField.setText("" + timer.getMinute());

        valuesPanel.add(new JLabel("seconds"));
        valuesPanel.add(secondField);
        secondField.setText("" + timer.getSecond());

        valuesPanel.add(new JLabel("   tick (ms)"));
        valuesPanel.add(tickField);
        tickField.setText("" + timer.getTickrate());

        panel.add(valuesPanel, BorderLayout.SOUTH);

        JButton startStopButt = new JButton("Start / Stop");
        startStopButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!timer.isRunning()){
                    try{
                        int h = Integer.parseInt(hourField.getText());
                        int m = Integer.parseInt(minuteField.getText());
                        int s = Integer.parseInt(secondField.getText());

                        timer.set(h, m, s);
                        timer.start();
                    } catch(NumberFormatException exception) {
                        hourField.setText("" + timer.getHour());
                        minuteField.setText("" + timer.getMinute());
                        secondField.setText("" + timer.getSecond());
                    }
                } else {
                    timer.stop();
                }
            }
        });

        panel.add(startStopButt, BorderLayout.NORTH);

        JButton tickButton = new JButton("Set Tickrate");
        tickButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int ms = Integer.parseInt(tickField.getText());
                    timer.setTickrate(ms);
                } catch (NumberFormatException exception) {
                    tickField.setText(timer.getTickrate() + "");
                }
            }
        });

        panel.add(tickButton, BorderLayout.EAST);

        JButton strategyButt = new JButton("Type: " + calcStrats.get(strategyIndex).getName());
        strategyButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                strategyIndex = (strategyIndex + 1) % calcStrats.size();
                timer.setCalculationStrategy(calcStrats.get(strategyIndex).getCalculationStrategy());
                strategyButt.setText("Type: " +  calcStrats.get(strategyIndex).getName());
            }
        });

        panel.add(strategyButt, BorderLayout.CENTER);

        JButton currTimeButt = new JButton("System clock");
        currTimeButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date date = new Date();
                secondField.setText(date.getSeconds() + "");
                minuteField.setText(date.getMinutes() + "");
                hourField.setText(date.getHours() + "");
            }
        });

        panel.add(currTimeButt, BorderLayout.WEST);
    }

    public JPanel getPanel(){
        return panel;
    }

    @Override
    public void notifyTick(Timer timer) {
        secondField.setText(timer.getSecond() + "");
        minuteField.setText(timer.getMinute() + "");
        hourField.setText(timer.getHour() + "");
    }
}
