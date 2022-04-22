package com.dice.dicemagic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

public class DiceMagic{
    JFrame mainWindow;
    Image appIcon = Toolkit.getDefaultToolkit().getImage("img/dice-magic-logo.png");
    Audio audioClips = new Audio();
    DiceMagic(){
        mainWindow = new JFrame("Dice Magic");

        JButton minimizeButton = new JButton(new ImageIcon("img/minimize.png"));
        minimizeButton.setBounds(440,20,20,20);
        minimizeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        minimizeButton.setBorderPainted(false);
        minimizeButton.setContentAreaFilled(false);
        minimizeButton.setFocusPainted(false);
        minimizeButton.setOpaque(false);

        JButton closeButton = new JButton(new ImageIcon("img/close.png"));
        closeButton.setBounds(470,20,20,20);
        closeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        closeButton.setBorderPainted(false);
        closeButton.setContentAreaFilled(false);
        closeButton.setFocusPainted(false);
        closeButton.setOpaque(false);

        JLabel playMessage = new JLabel("Play!");
        playMessage.setBounds(230, 40, 150, 40);
        playMessage.setFont(new Font(playMessage.getFont().getName(), Font.BOLD, 24));
        playMessage.setForeground(new Color(45, 131, 148));

        ImageIcon diceTitleImage = new ImageIcon("img/dice-title.png");
        JLabel diceTitle = new JLabel();
        diceTitle.setBounds(100,90,131,98);
        diceTitle.setIcon(diceTitleImage);
        ImageIcon diceImage = new ImageIcon("img/dice.gif");
        JLabel diceAnimation = new JLabel();
        diceAnimation.setBounds(215,140,72,72);
        diceAnimation.setIcon(diceImage);
        ImageIcon magicTitleImage = new ImageIcon("img/magic-title.png");
        JLabel magicTitle = new JLabel();
        magicTitle.setBounds(277,175,156,98);
        magicTitle.setIcon(magicTitleImage);

        JButton onePlayerButton = new JButton("1 Player");
        onePlayerButton.setBounds(185, 310, 140, 40);
        onePlayerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        onePlayerButton.setContentAreaFilled(false);
        onePlayerButton.setFocusPainted(false);
        onePlayerButton.setOpaque(false);

        JButton twoPlayerButton = new JButton("2 Player");
        twoPlayerButton.setBounds(185, 380, 140, 40);
        twoPlayerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        twoPlayerButton.setContentAreaFilled(false);
        twoPlayerButton.setFocusPainted(false);
        twoPlayerButton.setOpaque(false);

        DragListener drag = new DragListener();
        mainWindow.addMouseListener(drag);
        mainWindow.addMouseMotionListener(drag);

        minimizeButton.addActionListener(e -> {
            audioClips.playAudioClip("audio/pops.wav");
            mainWindow.setState(Frame.ICONIFIED);
        });

        closeButton.addActionListener(e -> {
            audioClips.playAudioClip("audio/pops.wav");
            if (JOptionPane.showConfirmDialog(mainWindow,
                    "Are you sure you want to close this application?",
                    "Closing Dice Magic?",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    new ImageIcon("img/cross.png")) == JOptionPane.YES_OPTION) {
                System.exit(0);
            } else {
                mainWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        });

        onePlayerButton.addActionListener(e -> {
            audioClips.playAudioClip("audio/pops.wav");
            mainWindow.setVisible(false);
            startOnePlayer();
        });

        twoPlayerButton.addActionListener(e -> {
            audioClips.playAudioClip("audio/pops.wav");
            mainWindow.setVisible(false);
            startTwoPlayer();
        });

        mainWindow.add(minimizeButton);
        mainWindow.add(closeButton);
        mainWindow.add(playMessage);
        mainWindow.add(diceTitle);
        mainWindow.add(diceAnimation);
        mainWindow.add(magicTitle);
        mainWindow.add(onePlayerButton);
        mainWindow.add(twoPlayerButton);
        mainWindow.setIconImage(appIcon);
        mainWindow.setSize(550, 500);
        mainWindow.setUndecorated(true);
        mainWindow.setShape(new RoundRectangle2D.Double(10,10,500,450,50,50));
        mainWindow.getContentPane().setBackground(new Color(245,245,245));
        mainWindow.setLayout(null);
        mainWindow.setResizable(false);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setVisible(true);
    }

    static int chancesLeft, randomChallengeNumber, dice1Number, dice2Number, diceTotal, wins;
    public void startOnePlayer(){
        JFrame onePlayerWindow = new JFrame("Dice Magic - 1 Player");

        JButton minimizeButton = new JButton(new ImageIcon("img/minimize.png"));
        minimizeButton.setBounds(470,20,20,20);
        minimizeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        minimizeButton.setBorderPainted(false);
        minimizeButton.setContentAreaFilled(false);
        minimizeButton.setFocusPainted(false);
        minimizeButton.setOpaque(false);

        JButton homeButton = new JButton(new ImageIcon("img/home.png"));
        homeButton.setBounds(470,20,20,20);
        homeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        homeButton.setBorderPainted(false);
        homeButton.setContentAreaFilled(false);
        homeButton.setFocusPainted(false);
        homeButton.setOpaque(false);
        homeButton.setVisible(false);

        ImageIcon diceMagicImage = new ImageIcon("img/dice-magic-title.png");
        JLabel diceMagicTitle = new JLabel();
        diceMagicTitle.setBounds(175,40,187,48);
        diceMagicTitle.setIcon(diceMagicImage);

        ImageIcon stickManTitleImage = new ImageIcon("img/stick-man-title-1.png");
        JLabel stickManTitle = new JLabel();
        stickManTitle.setBounds(220,90,101,32);
        stickManTitle.setIcon(stickManTitleImage);

        JLabel readyLabel = new JLabel("Are you ready?");
        readyLabel.setBounds(185, 180, 200, 40);
        readyLabel.setFont(new Font(readyLabel.getFont().getName(), Font.BOLD, 22));
        readyLabel.setForeground(new Color(45, 131, 148));

        JButton startButton = new JButton("Start");
        startButton.setBounds(190,240,140,40);
        startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        startButton.setContentAreaFilled(false);
        startButton.setFocusPainted(false);
        startButton.setOpaque(false);
        startButton.setVisible(true);

        JButton backButton = new JButton("Return to Home");
        backButton.setBounds(190,320,140,40);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.setContentAreaFilled(false);
        backButton.setFocusPainted(false);
        backButton.setOpaque(false);
        backButton.setVisible(true);

        JLabel remainingChances = new JLabel("Chances left");
        remainingChances.setBounds(40,160,100,20);
        remainingChances.setFont(new Font(remainingChances.getFont().getName(), Font.BOLD, 14));
        remainingChances.setForeground(Color.GRAY);
        remainingChances.setVisible(false);

        JLabel randomChallenge = new JLabel("Challenge");
        randomChallenge.setBounds(230,160,100,20);
        randomChallenge.setFont(new Font(randomChallenge.getFont().getName(), Font.BOLD, 14));
        randomChallenge.setForeground(Color.GRAY);
        randomChallenge.setVisible(false);

        JLabel winCounter = new JLabel("Wins");
        winCounter.setBounds(430,160,150,20);
        winCounter.setFont(new Font(winCounter.getFont().getName(), Font.BOLD, 14));
        winCounter.setForeground(Color.GRAY);
        winCounter.setVisible(false);

        JLabel remainingChancesValue = new JLabel("00");
        remainingChancesValue.setBounds(65,180,100,50);
        remainingChancesValue.setFont(new Font(remainingChancesValue.getFont().getName(), Font.BOLD, 32));
        remainingChancesValue.setForeground(new Color(45, 131, 148));
        remainingChancesValue.setVisible(false);

        JLabel randomChallengeValue = new JLabel("00");
        randomChallengeValue.setBounds(245,180,100,50);
        randomChallengeValue.setFont(new Font(randomChallengeValue.getFont().getName(), Font.BOLD, 32));
        randomChallengeValue.setForeground(new Color(45, 131, 148));
        randomChallengeValue.setVisible(false);

        JLabel winCounterValue = new JLabel("00");
        winCounterValue.setBounds(430,180,100,50);
        winCounterValue.setFont(new Font(winCounterValue.getFont().getName(), Font.BOLD, 32));
        winCounterValue.setForeground(new Color(45, 131, 148));
        winCounterValue.setVisible(false);

        JButton rollDice = new JButton(new ImageIcon("img/roll-dice.png"));
        rollDice.setBounds(55,270,48,48);
        rollDice.setCursor(new Cursor(Cursor.HAND_CURSOR));
        rollDice.setBorderPainted(false);
        rollDice.setContentAreaFilled(false);
        rollDice.setFocusPainted(false);
        rollDice.setOpaque(false);
        rollDice.setVisible(false);

        ImageIcon dice1Image = new ImageIcon("img/dice-small.gif");
        JLabel dice1 = new JLabel();
        dice1.setBounds(175,270,48,48);
        dice1.setIcon(dice1Image);
        dice1.setVisible(false);

        JLabel plus = new JLabel("+");
        plus.setBounds(250,280,32,32);
        plus.setFont(new Font(plus.getFont().getName(), Font.BOLD, 32));
        plus.setForeground(new Color(45, 131, 148));
        plus.setVisible(false);

        ImageIcon dice2Image = new ImageIcon("img/dice-small.gif");
        JLabel dice2 = new JLabel();
        dice2.setBounds(300,270,48,48);
        dice2.setIcon(dice2Image);
        dice2.setVisible(false);

        JLabel equals = new JLabel("=");
        equals.setBounds(385,280,32,32);
        equals.setFont(new Font(equals.getFont().getName(), Font.BOLD, 32));
        equals.setForeground(new Color(45, 131, 148));
        equals.setVisible(false);

        JLabel totalValue = new JLabel("00");
        totalValue.setBounds(430,270,100,50);
        totalValue.setFont(new Font(totalValue.getFont().getName(), Font.BOLD, 32));
        totalValue.setForeground(new Color(45, 131, 148));
        totalValue.setVisible(false);

        JLabel rollDiceLabel = new JLabel("Roll 2 Dices");
        rollDiceLabel.setBounds(40,330,100,20);
        rollDiceLabel.setFont(new Font(rollDiceLabel.getFont().getName(), Font.BOLD, 14));
        rollDiceLabel.setForeground(Color.GRAY);
        rollDiceLabel.setVisible(false);

        JLabel dice1Label = new JLabel("Dice 1");
        dice1Label.setBounds(177,330,100,20);
        dice1Label.setFont(new Font(dice1Label.getFont().getName(), Font.BOLD, 14));
        dice1Label.setForeground(Color.GRAY);
        dice1Label.setVisible(false);

        JLabel dice2Label = new JLabel("Dice 2");
        dice2Label.setBounds(303,330,100,20);
        dice2Label.setFont(new Font(dice2Label.getFont().getName(), Font.BOLD, 14));
        dice2Label.setForeground(Color.GRAY);
        dice2Label.setVisible(false);

        JLabel total = new JLabel("Total");
        total.setBounds(430,330,100,20);
        total.setFont(new Font(dice2Label.getFont().getName(), Font.BOLD, 14));
        total.setForeground(Color.GRAY);
        total.setVisible(false);

        JButton restartButton = new JButton("Restart");
        restartButton.setBounds(195,400,140,40);
        restartButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        restartButton.setContentAreaFilled(false);
        restartButton.setFocusPainted(false);
        restartButton.setOpaque(false);
        restartButton.setVisible(false);

        JButton newChallengeButton = new JButton("New Challenge");
        newChallengeButton.setBounds(100,400,140,40);
        newChallengeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        newChallengeButton.setContentAreaFilled(false);
        newChallengeButton.setFocusPainted(false);
        newChallengeButton.setOpaque(false);
        newChallengeButton.setVisible(false);

        startButton.addActionListener(e -> {
            chancesLeft = 3;
            audioClips.playAudioClip("audio/pops.wav");
            readyLabel.setVisible(false);
            startButton.setVisible(false);
            backButton.setVisible(false);
            minimizeButton.setBounds(440,20,20,20);
            homeButton.setVisible(true);
            remainingChances.setVisible(true);
            randomChallenge.setVisible(true);
            winCounter.setVisible(true);
            remainingChancesValue.setVisible(true);
            remainingChancesValue.setText("03");
            randomChallengeNumber = generateRandomNumber("challenge");
            String randomNumber = String.valueOf(randomChallengeNumber);
            if (randomChallengeNumber >=2 && randomChallengeNumber<=9){
                randomNumber = "0" + randomNumber;
            }
            randomChallengeValue.setVisible(true);
            randomChallengeValue.setText(randomNumber);
            winCounterValue.setVisible(true);
            rollDice.setVisible(true);
            dice1.setVisible(true);
            plus.setVisible(true);
            dice2.setVisible(true);
            equals.setVisible(true);
            totalValue.setVisible(true);
            rollDiceLabel.setVisible(true);
            dice1Label.setVisible(true);
            dice2Label.setVisible(true);
            total.setVisible(true);
            restartButton.setVisible(true);
        });

        rollDice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chancesLeft--;
                if (chancesLeft >= 0){
                    remainingChancesValue.setText("0" + chancesLeft);
                    audioClips.playAudioClip("audio/rolling-dice.wav");
                    dice1Number = generateRandomNumber("rollDice");
                    dice2Number = generateRandomNumber(("rollDice"));
                    switch (dice1Number){
                        case 1: dice1.setIcon(new ImageIcon("img/ds1.png"));
                            break;
                        case 2: dice1.setIcon(new ImageIcon("img/ds2.png"));
                            break;
                        case 3: dice1.setIcon(new ImageIcon("img/ds3.png"));
                            break;
                        case 4: dice1.setIcon(new ImageIcon("img/ds4.png"));
                            break;
                        case 5: dice1.setIcon(new ImageIcon("img/ds5.png"));
                            break;
                        case 6: dice1.setIcon(new ImageIcon("img/ds6.png"));
                            break;
                        default: break;
                    }
                    switch (dice2Number){
                        case 1: dice2.setIcon(new ImageIcon("img/ds1.png"));
                            break;
                        case 2: dice2.setIcon(new ImageIcon("img/ds2.png"));
                            break;
                        case 3: dice2.setIcon(new ImageIcon("img/ds3.png"));
                            break;
                        case 4: dice2.setIcon(new ImageIcon("img/ds4.png"));
                            break;
                        case 5: dice2.setIcon(new ImageIcon("img/ds5.png"));
                            break;
                        case 6: dice2.setIcon(new ImageIcon("img/ds6.png"));
                            break;
                        default: break;
                    }
                    diceTotal = dice1Number + dice2Number;;
                    if (diceTotal>=2 && diceTotal<=9){
                        totalValue.setText("0" + diceTotal);
                    } else {
                        totalValue.setText(String.valueOf(diceTotal));
                    }

                    if (diceTotal == randomChallengeNumber){
                        wins++;
                        winCounterValue.setText("0" + wins);
                        audioClips.playAudioClip("audio/bell.wav");
                    }

                    if (chancesLeft == 0){
                        restartButton.setBounds(275,400,140,40);
                        newChallengeButton.setVisible(true);
                        rollDice.setEnabled(false);
                    }
                }
            }
        });

        backButton.addActionListener(e -> {
            audioClips.playAudioClip("audio/pops.wav");
            mainWindow.setVisible(true);
            onePlayerWindow.dispose();
        });

        newChallengeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                audioClips.playAudioClip("audio/pops.wav");
                newChallengeButton.setVisible(false);
                restartButton.setBounds(195,400,140,40);

                randomChallengeNumber = generateRandomNumber("challenge");
                randomChallengeValue.setText(String.valueOf(randomChallengeNumber));
                if (randomChallengeNumber>=2 && randomChallengeNumber<=9){
                    randomChallengeValue.setText("0" + randomChallengeNumber);
                }
                chancesLeft = 3;
                remainingChancesValue.setText("0" + chancesLeft);
                if (!rollDice.isEnabled()){
                    rollDice.setEnabled(true);
                }
                dice1.setIcon(new ImageIcon("img/dice-small.gif"));
                dice2.setIcon(new ImageIcon("img/dice-small.gif"));
                dice1Number = 0;
                dice2Number = 0;
                diceTotal = 0;
                totalValue.setText("0" + diceTotal);
            }
        });

        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                audioClips.playAudioClip("audio/pops.wav");
                newChallengeButton.setVisible(false);
                restartButton.setBounds(195,400,140,40);

                wins = 0;
                winCounterValue.setText("0" + wins);
                randomChallengeNumber = generateRandomNumber("challenge");
                randomChallengeValue.setText(String.valueOf(randomChallengeNumber));
                if (randomChallengeNumber>=2 && randomChallengeNumber<=9){
                    randomChallengeValue.setText("0" + randomChallengeNumber);
                }
                chancesLeft = 3;
                remainingChancesValue.setText("0" + chancesLeft);
                if (!rollDice.isEnabled()){
                    rollDice.setEnabled(true);
                }
                dice1.setIcon(new ImageIcon("img/dice-small.gif"));
                dice2.setIcon(new ImageIcon("img/dice-small.gif"));
                dice1Number = 0;
                dice2Number = 0;
                diceTotal = 0;
                totalValue.setText("0" + diceTotal);
            }
        });

        DragListener drag = new DragListener();
        onePlayerWindow.addMouseListener(drag);
        onePlayerWindow.addMouseMotionListener(drag);

        minimizeButton.addActionListener(e -> {
            audioClips.playAudioClip("audio/pops.wav");
            onePlayerWindow.setState(Frame.ICONIFIED);
        });

        homeButton.addActionListener(e -> {
            audioClips.playAudioClip("audio/pops.wav");
            if (JOptionPane.showConfirmDialog(onePlayerWindow,
                    "All your current progress will be lost.",
                    "Back to Home",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                    new ImageIcon("img/bin.png")) == JOptionPane.YES_OPTION){
                chancesLeft = 0;
                randomChallengeNumber = 0;
                dice1Number = 0;
                dice2Number = 0;
                diceTotal = 0;
                wins = 0;
                mainWindow.setVisible(true);
                onePlayerWindow.dispose();
            } else {
                onePlayerWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        });
        onePlayerWindow.add(minimizeButton);
        onePlayerWindow.add(homeButton);
        onePlayerWindow.add(diceMagicTitle);
        onePlayerWindow.add(stickManTitle);
        onePlayerWindow.add(readyLabel);
        onePlayerWindow.add(startButton);
        onePlayerWindow.add(backButton);
        onePlayerWindow.add(remainingChances);
        onePlayerWindow.add(randomChallenge);
        onePlayerWindow.add(winCounter);
        onePlayerWindow.add(remainingChancesValue);
        onePlayerWindow.add(randomChallengeValue);
        onePlayerWindow.add(winCounterValue);
        onePlayerWindow.add(rollDice);
        onePlayerWindow.add(dice1);
        onePlayerWindow.add(plus);
        onePlayerWindow.add(dice2);
        onePlayerWindow.add(equals);
        onePlayerWindow.add(totalValue);
        onePlayerWindow.add(rollDiceLabel);
        onePlayerWindow.add(dice1Label);
        onePlayerWindow.add(dice2Label);
        onePlayerWindow.add(total);
        onePlayerWindow.add(newChallengeButton);
        onePlayerWindow.add(restartButton);
        onePlayerWindow.setIconImage(appIcon);
        onePlayerWindow.setUndecorated(true);
        onePlayerWindow.setSize(550,500);
        onePlayerWindow.setShape(new RoundRectangle2D.Double(10,10,500,450,50,50));
        onePlayerWindow.getContentPane().setBackground(new Color(245,245,245));
        onePlayerWindow.setLayout(null);
        onePlayerWindow.setResizable(false);
        onePlayerWindow.setLocationRelativeTo(null);
        onePlayerWindow.setVisible(true);
    }

    public void startTwoPlayer(){
        JFrame twoPlayerWindow = new JFrame("Dice Magic - 2 Player");

        JButton minimizeButton = new JButton(new ImageIcon("img/minimize.png"));
        minimizeButton.setBounds(470,20,20,20);
        minimizeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        minimizeButton.setBorderPainted(false);
        minimizeButton.setContentAreaFilled(false);
        minimizeButton.setFocusPainted(false);
        minimizeButton.setOpaque(false);

        JButton homeButton = new JButton(new ImageIcon("img/home.png"));
        homeButton.setBounds(470,20,20,20);
        homeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        homeButton.setBorderPainted(false);
        homeButton.setContentAreaFilled(false);
        homeButton.setFocusPainted(false);
        homeButton.setOpaque(false);
        homeButton.setVisible(false);

        ImageIcon diceMagicImage = new ImageIcon("img/dice-magic-title.png");
        JLabel diceMagicTitle = new JLabel();
        diceMagicTitle.setBounds(175,40,187,48);
        diceMagicTitle.setIcon(diceMagicImage);

        ImageIcon stickManTitleImage = new ImageIcon("img/stick-man-title-2.png");
        JLabel stickManTitle = new JLabel();
        stickManTitle.setBounds(215,90,116,32);
        stickManTitle.setIcon(stickManTitleImage);

        JLabel readyLabel = new JLabel("Are you ready?");
        readyLabel.setBounds(185, 180, 200, 40);
        readyLabel.setFont(new Font(readyLabel.getFont().getName(), Font.BOLD, 22));
        readyLabel.setForeground(new Color(45, 131, 148));

        JButton startButton = new JButton("Start");
        startButton.setBounds(190,240,140,40);
        startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        startButton.setContentAreaFilled(false);
        startButton.setFocusPainted(false);
        startButton.setOpaque(false);
        startButton.setVisible(true);

        JButton backButton = new JButton("Return to Home");
        backButton.setBounds(190,320,140,40);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.setContentAreaFilled(false);
        backButton.setFocusPainted(false);
        backButton.setOpaque(false);
        backButton.setVisible(true);

        JLabel remainingChances = new JLabel("Chances left");
        remainingChances.setBounds(40,160,100,20);
        remainingChances.setFont(new Font(remainingChances.getFont().getName(), Font.BOLD, 14));
        remainingChances.setForeground(Color.GRAY);
        remainingChances.setVisible(false);

        JLabel randomChallenge = new JLabel("Challenge");
        randomChallenge.setBounds(230,160,100,20);
        randomChallenge.setFont(new Font(randomChallenge.getFont().getName(), Font.BOLD, 14));
        randomChallenge.setForeground(Color.GRAY);
        randomChallenge.setVisible(false);

        JLabel winCounter = new JLabel("Wins");
        winCounter.setBounds(430,160,150,20);
        winCounter.setFont(new Font(winCounter.getFont().getName(), Font.BOLD, 14));
        winCounter.setForeground(Color.GRAY);
        winCounter.setVisible(false);

        JLabel remainingChancesValue = new JLabel("00");
        remainingChancesValue.setBounds(65,180,100,50);
        remainingChancesValue.setFont(new Font(remainingChancesValue.getFont().getName(), Font.BOLD, 32));
        remainingChancesValue.setForeground(new Color(45, 131, 148));
        remainingChancesValue.setVisible(false);

        JLabel randomChallengeValue = new JLabel("00");
        randomChallengeValue.setBounds(245,180,100,50);
        randomChallengeValue.setFont(new Font(randomChallengeValue.getFont().getName(), Font.BOLD, 32));
        randomChallengeValue.setForeground(new Color(45, 131, 148));
        randomChallengeValue.setVisible(false);

        JLabel winCounterValue = new JLabel("00");
        winCounterValue.setBounds(430,180,100,50);
        winCounterValue.setFont(new Font(winCounterValue.getFont().getName(), Font.BOLD, 32));
        winCounterValue.setForeground(new Color(45, 131, 148));
        winCounterValue.setVisible(false);

        JButton rollDice = new JButton(new ImageIcon("img/roll-dice.png"));
        rollDice.setBounds(55,270,48,48);
        rollDice.setCursor(new Cursor(Cursor.HAND_CURSOR));
        rollDice.setBorderPainted(false);
        rollDice.setContentAreaFilled(false);
        rollDice.setFocusPainted(false);
        rollDice.setOpaque(false);
        rollDice.setVisible(false);

        ImageIcon dice1Image = new ImageIcon("img/dice-small.gif");
        JLabel dice1 = new JLabel();
        dice1.setBounds(175,270,48,48);
        dice1.setIcon(dice1Image);
        dice1.setVisible(false);

        JLabel plus = new JLabel("+");
        plus.setBounds(250,280,32,32);
        plus.setFont(new Font(plus.getFont().getName(), Font.BOLD, 32));
        plus.setForeground(new Color(45, 131, 148));
        plus.setVisible(false);

        ImageIcon dice2Image = new ImageIcon("img/dice-small.gif");
        JLabel dice2 = new JLabel();
        dice2.setBounds(300,270,48,48);
        dice2.setIcon(dice2Image);
        dice2.setVisible(false);

        JLabel equals = new JLabel("=");
        equals.setBounds(385,280,32,32);
        equals.setFont(new Font(equals.getFont().getName(), Font.BOLD, 32));
        equals.setForeground(new Color(45, 131, 148));
        equals.setVisible(false);

        JLabel totalValue = new JLabel("00");
        totalValue.setBounds(430,270,100,50);
        totalValue.setFont(new Font(totalValue.getFont().getName(), Font.BOLD, 32));
        totalValue.setForeground(new Color(45, 131, 148));
        totalValue.setVisible(false);

        JLabel rollDiceLabel = new JLabel("Roll 2 Dices");
        rollDiceLabel.setBounds(40,330,100,20);
        rollDiceLabel.setFont(new Font(rollDiceLabel.getFont().getName(), Font.BOLD, 14));
        rollDiceLabel.setForeground(Color.GRAY);
        rollDiceLabel.setVisible(false);

        JLabel dice1Label = new JLabel("Dice 1");
        dice1Label.setBounds(177,330,100,20);
        dice1Label.setFont(new Font(dice1Label.getFont().getName(), Font.BOLD, 14));
        dice1Label.setForeground(Color.GRAY);
        dice1Label.setVisible(false);

        JLabel dice2Label = new JLabel("Dice 2");
        dice2Label.setBounds(303,330,100,20);
        dice2Label.setFont(new Font(dice2Label.getFont().getName(), Font.BOLD, 14));
        dice2Label.setForeground(Color.GRAY);
        dice2Label.setVisible(false);

        JLabel total = new JLabel("Total");
        total.setBounds(430,330,100,20);
        total.setFont(new Font(dice2Label.getFont().getName(), Font.BOLD, 14));
        total.setForeground(Color.GRAY);
        total.setVisible(false);

        JButton restartButton = new JButton("Restart");
        restartButton.setBounds(190,400,140,40);
        restartButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        restartButton.setContentAreaFilled(false);
        restartButton.setFocusPainted(false);
        restartButton.setOpaque(false);
        restartButton.setVisible(false);

        JButton newChallengeButton = new JButton("New Challenge");
        newChallengeButton.setBounds(100,400,140,40);
        newChallengeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        newChallengeButton.setContentAreaFilled(false);
        newChallengeButton.setFocusPainted(false);
        newChallengeButton.setOpaque(false);
        newChallengeButton.setVisible(false);

        startButton.addActionListener(e -> {
            chancesLeft = 3;
            audioClips.playAudioClip("audio/pops.wav");
            readyLabel.setVisible(false);
            startButton.setVisible(false);
            backButton.setVisible(false);
            twoPlayerWindow.setSize(550,700);
            twoPlayerWindow.setShape(new RoundRectangle2D.Double(10,10,500,650,50,50));
            minimizeButton.setBounds(440,20,20,20);
            homeButton.setVisible(true);
            remainingChances.setVisible(true);
            randomChallenge.setVisible(true);
            winCounter.setVisible(true);
            remainingChancesValue.setVisible(true);
            remainingChancesValue.setText("03");
            randomChallengeNumber = generateRandomNumber("challenge");
            String randomNumber = String.valueOf(randomChallengeNumber);
            if (randomChallengeNumber >=2 && randomChallengeNumber<=9){
                randomNumber = "0" + randomNumber;
            }
            randomChallengeValue.setVisible(true);
            randomChallengeValue.setText(randomNumber);
            winCounterValue.setVisible(true);
            rollDice.setVisible(true);
            dice1.setVisible(true);
            plus.setVisible(true);
            dice2.setVisible(true);
            equals.setVisible(true);
            totalValue.setVisible(true);
            rollDiceLabel.setVisible(true);
            dice1Label.setVisible(true);
            dice2Label.setVisible(true);
            total.setVisible(true);
            restartButton.setVisible(true);
        });

        rollDice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chancesLeft--;
                if (chancesLeft >= 0){
                    remainingChancesValue.setText("0" + chancesLeft);
                    audioClips.playAudioClip("audio/rolling-dice.wav");
                    dice1Number = generateRandomNumber("rollDice");
                    dice2Number = generateRandomNumber(("rollDice"));
                    switch (dice1Number){
                        case 1: dice1.setIcon(new ImageIcon("img/ds1.png"));
                            break;
                        case 2: dice1.setIcon(new ImageIcon("img/ds2.png"));
                            break;
                        case 3: dice1.setIcon(new ImageIcon("img/ds3.png"));
                            break;
                        case 4: dice1.setIcon(new ImageIcon("img/ds4.png"));
                            break;
                        case 5: dice1.setIcon(new ImageIcon("img/ds5.png"));
                            break;
                        case 6: dice1.setIcon(new ImageIcon("img/ds6.png"));
                            break;
                        default: break;
                    }
                    switch (dice2Number){
                        case 1: dice2.setIcon(new ImageIcon("img/ds1.png"));
                            break;
                        case 2: dice2.setIcon(new ImageIcon("img/ds2.png"));
                            break;
                        case 3: dice2.setIcon(new ImageIcon("img/ds3.png"));
                            break;
                        case 4: dice2.setIcon(new ImageIcon("img/ds4.png"));
                            break;
                        case 5: dice2.setIcon(new ImageIcon("img/ds5.png"));
                            break;
                        case 6: dice2.setIcon(new ImageIcon("img/ds6.png"));
                            break;
                        default: break;
                    }
                    diceTotal = dice1Number + dice2Number;;
                    if (diceTotal>=2 && diceTotal<=9){
                        totalValue.setText("0" + diceTotal);
                    } else {
                        totalValue.setText(String.valueOf(diceTotal));
                    }

                    if (diceTotal == randomChallengeNumber){
                        wins++;
                        winCounterValue.setText("0" + wins);
                        audioClips.playAudioClip("audio/bell.wav");
                    }

                    if (chancesLeft == 0){
                        restartButton.setBounds(275,400,140,40);
                        newChallengeButton.setVisible(true);
                        rollDice.setEnabled(false);
                    }
                }
            }
        });

        backButton.addActionListener(e -> {
            audioClips.playAudioClip("audio/pops.wav");
            mainWindow.setVisible(true);
            twoPlayerWindow.dispose();
        });

        newChallengeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                audioClips.playAudioClip("audio/pops.wav");
                newChallengeButton.setVisible(false);
                restartButton.setBounds(190,400,140,40);

                randomChallengeNumber = generateRandomNumber("challenge");
                randomChallengeValue.setText(String.valueOf(randomChallengeNumber));
                if (randomChallengeNumber>=2 && randomChallengeNumber<=9){
                    randomChallengeValue.setText("0" + randomChallengeNumber);
                }
                chancesLeft = 3;
                remainingChancesValue.setText("0" + chancesLeft);
                if (!rollDice.isEnabled()){
                    rollDice.setEnabled(true);
                }
                dice1.setIcon(new ImageIcon("img/dice-small.gif"));
                dice2.setIcon(new ImageIcon("img/dice-small.gif"));
                dice1Number = 0;
                dice2Number = 0;
                diceTotal = 0;
                totalValue.setText("0" + diceTotal);
            }
        });

        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                audioClips.playAudioClip("audio/pops.wav");
                newChallengeButton.setVisible(false);
                restartButton.setBounds(190,400,140,40);

                wins = 0;
                winCounterValue.setText("0" + wins);
                randomChallengeNumber = generateRandomNumber("challenge");
                randomChallengeValue.setText(String.valueOf(randomChallengeNumber));
                if (randomChallengeNumber>=2 && randomChallengeNumber<=9){
                    randomChallengeValue.setText("0" + randomChallengeNumber);
                }
                chancesLeft = 3;
                remainingChancesValue.setText("0" + chancesLeft);
                if (!rollDice.isEnabled()){
                    rollDice.setEnabled(true);
                }
                dice1.setIcon(new ImageIcon("img/dice-small.gif"));
                dice2.setIcon(new ImageIcon("img/dice-small.gif"));
                dice1Number = 0;
                dice2Number = 0;
                diceTotal = 0;
                totalValue.setText("0" + diceTotal);
            }
        });

        DragListener drag = new DragListener();
        twoPlayerWindow.addMouseListener(drag);
        twoPlayerWindow.addMouseMotionListener(drag);

        minimizeButton.addActionListener(e -> {
            audioClips.playAudioClip("audio/pops.wav");
            twoPlayerWindow.setState(Frame.ICONIFIED);
        });

        homeButton.addActionListener(e -> {
            audioClips.playAudioClip("audio/pops.wav");
            if (JOptionPane.showConfirmDialog(twoPlayerWindow,
                    "All your current progress will be lost.",
                    "Back to Home",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    new ImageIcon("img/bin.png")) == JOptionPane.YES_OPTION){
                mainWindow.setVisible(true);
                twoPlayerWindow.dispose();
            } else {
                twoPlayerWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        });
        twoPlayerWindow.add(minimizeButton);
        twoPlayerWindow.add(homeButton);
        twoPlayerWindow.add(diceMagicTitle);
        twoPlayerWindow.add(stickManTitle);
        twoPlayerWindow.add(readyLabel);
        twoPlayerWindow.add(startButton);
        twoPlayerWindow.add(backButton);
        twoPlayerWindow.add(remainingChances);
        twoPlayerWindow.add(randomChallenge);
        twoPlayerWindow.add(winCounter);
        twoPlayerWindow.add(remainingChancesValue);
        twoPlayerWindow.add(randomChallengeValue);
        twoPlayerWindow.add(winCounterValue);
        twoPlayerWindow.add(rollDice);
        twoPlayerWindow.add(dice1);
        twoPlayerWindow.add(plus);
        twoPlayerWindow.add(dice2);
        twoPlayerWindow.add(equals);
        twoPlayerWindow.add(totalValue);
        twoPlayerWindow.add(rollDiceLabel);
        twoPlayerWindow.add(dice1Label);
        twoPlayerWindow.add(dice2Label);
        twoPlayerWindow.add(total);
        twoPlayerWindow.add(newChallengeButton);
        twoPlayerWindow.add(restartButton);
        twoPlayerWindow.setIconImage(appIcon);
        twoPlayerWindow.setUndecorated(true);
        twoPlayerWindow.setSize(550,500);
        twoPlayerWindow.setShape(new RoundRectangle2D.Double(10,10,500,450,50,50));
        twoPlayerWindow.getContentPane().setBackground(new Color(245,245,245));
        twoPlayerWindow.setLayout(null);
        twoPlayerWindow.setResizable(false);
        twoPlayerWindow.setLocationRelativeTo(null);
        twoPlayerWindow.setVisible(true);
    }

    public int generateRandomNumber(String s){
        if (s.equals("challenge")){
            return (int)(Math.random()*(12-2) + 2);
        } else if (s.equals("rollDice")){
            return (int)(Math.random()*(6-1) + 1);
        }
        return -1;
    }

    public static void main(String[] args) {
        new DiceMagic();
    }
}
