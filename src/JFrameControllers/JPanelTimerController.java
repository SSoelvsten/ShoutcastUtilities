package JFrameControllers;

import Observer.TimerObserver;
import Time.*;
import Time.Timer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
                                 ArrayList<String> stratNames,
                                 ArrayList<TimerCalculatorStrategy> calcStrategies){
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

        JButton startStopButt = new JButton("Start/Stop");
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

        panel.add(startStopButt, BorderLayout.CENTER);

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

        JButton strategyButt = new JButton(stratNames.get(strategyIndex));
        strategyButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                strategyIndex = (strategyIndex + 1) % calcStrategies.size();
                timer.setCalculationStrategy(calcStrategies.get(strategyIndex));
                strategyButt.setText(stratNames.get(strategyIndex));
            }
        });

        panel.add(strategyButt, BorderLayout.WEST);
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
