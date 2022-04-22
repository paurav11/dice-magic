package com.dice.dicemagic;

import javax.swing.*;
import java.awt.*;
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
        total.setFont(new Font(total.getFont().getName(), Font.BOLD, 14));
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

        rollDice.addActionListener(e -> {
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
                diceTotal = dice1Number + dice2Number;
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
        });

        backButton.addActionListener(e -> {
            audioClips.playAudioClip("audio/pops.wav");
            mainWindow.setVisible(true);
            onePlayerWindow.dispose();
        });

        newChallengeButton.addActionListener(e -> {
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
        });

        restartButton.addActionListener(e -> {
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

    static int p1Wins, p2Wins, win1, win2;
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
        randomChallenge.setBounds(165,160,100,20);
        randomChallenge.setFont(new Font(randomChallenge.getFont().getName(), Font.BOLD, 14));
        randomChallenge.setForeground(Color.GRAY);
        randomChallenge.setVisible(false);

        JLabel winCounter1 = new JLabel("P1 Wins");
        winCounter1.setBounds(300,160,150,20);
        winCounter1.setFont(new Font(winCounter1.getFont().getName(), Font.BOLD, 14));
        winCounter1.setForeground(Color.GRAY);
        winCounter1.setVisible(false);

        JLabel winCounter2 = new JLabel("P2 Wins");
        winCounter2.setBounds(420,160,150,20);
        winCounter2.setFont(new Font(winCounter2.getFont().getName(), Font.BOLD, 14));
        winCounter2.setForeground(Color.GRAY);
        winCounter2.setVisible(false);

        JLabel remainingChancesValue = new JLabel("00");
        remainingChancesValue.setBounds(65,180,100,50);
        remainingChancesValue.setFont(new Font(remainingChancesValue.getFont().getName(), Font.BOLD, 32));
        remainingChancesValue.setForeground(new Color(45, 131, 148));
        remainingChancesValue.setVisible(false);

        JLabel randomChallengeValue = new JLabel("00");
        randomChallengeValue.setBounds(180,180,100,50);
        randomChallengeValue.setFont(new Font(randomChallengeValue.getFont().getName(), Font.BOLD, 32));
        randomChallengeValue.setForeground(new Color(45, 131, 148));
        randomChallengeValue.setVisible(false);

        JLabel winCounter1Value = new JLabel("00");
        winCounter1Value.setBounds(310,180,100,50);
        winCounter1Value.setFont(new Font(winCounter1Value.getFont().getName(), Font.BOLD, 32));
        winCounter1Value.setForeground(new Color(45, 131, 148));
        winCounter1Value.setVisible(false);

        JLabel winCounter2Value = new JLabel("00");
        winCounter2Value.setBounds(430,180,100,50);
        winCounter2Value.setFont(new Font(winCounter2Value.getFont().getName(), Font.BOLD, 32));
        winCounter2Value.setForeground(new Color(45, 131, 148));
        winCounter2Value.setVisible(false);

        JLabel player1Label = new JLabel("Player 1 (P1)");
        player1Label.setBounds(40,250,100,20);
        player1Label.setFont(new Font(player1Label.getFont().getName(), Font.BOLD, 14));
        player1Label.setForeground(Color.GRAY);
        player1Label.setVisible(false);

        JButton rollDice1 = new JButton(new ImageIcon("img/roll-dice.png"));
        rollDice1.setBounds(55,300,48,48);
        rollDice1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        rollDice1.setBorderPainted(false);
        rollDice1.setContentAreaFilled(false);
        rollDice1.setFocusPainted(false);
        rollDice1.setOpaque(false);
        rollDice1.setVisible(false);

        ImageIcon dice1Image = new ImageIcon("img/dice-small.gif");
        JLabel dice1 = new JLabel();
        dice1.setBounds(175,300,48,48);
        dice1.setIcon(dice1Image);
        dice1.setVisible(false);

        JLabel plus1 = new JLabel("+");
        plus1.setBounds(250,310,32,32);
        plus1.setFont(new Font(plus1.getFont().getName(), Font.BOLD, 32));
        plus1.setForeground(new Color(45, 131, 148));
        plus1.setVisible(false);

        ImageIcon dice2Image = new ImageIcon("img/dice-small.gif");
        JLabel dice2 = new JLabel();
        dice2.setBounds(300,300,48,48);
        dice2.setIcon(dice2Image);
        dice2.setVisible(false);

        JLabel equals1 = new JLabel("=");
        equals1.setBounds(385,310,32,32);
        equals1.setFont(new Font(equals1.getFont().getName(), Font.BOLD, 32));
        equals1.setForeground(new Color(45, 131, 148));
        equals1.setVisible(false);

        JLabel total1Value = new JLabel("00");
        total1Value.setBounds(430,300,100,50);
        total1Value.setFont(new Font(total1Value.getFont().getName(), Font.BOLD, 32));
        total1Value.setForeground(new Color(45, 131, 148));
        total1Value.setVisible(false);

        JLabel rollDice1Label = new JLabel("Roll 2 Dices");
        rollDice1Label.setBounds(40,360,100,20);
        rollDice1Label.setFont(new Font(rollDice1Label.getFont().getName(), Font.BOLD, 14));
        rollDice1Label.setForeground(Color.GRAY);
        rollDice1Label.setVisible(false);

        JLabel dice1Label = new JLabel("Dice 1");
        dice1Label.setBounds(177,360,100,20);
        dice1Label.setFont(new Font(dice1Label.getFont().getName(), Font.BOLD, 14));
        dice1Label.setForeground(Color.GRAY);
        dice1Label.setVisible(false);

        JLabel dice2Label = new JLabel("Dice 2");
        dice2Label.setBounds(303,360,100,20);
        dice2Label.setFont(new Font(dice2Label.getFont().getName(), Font.BOLD, 14));
        dice2Label.setForeground(Color.GRAY);
        dice2Label.setVisible(false);

        JLabel total1 = new JLabel("Total");
        total1.setBounds(430,360,100,20);
        total1.setFont(new Font(total1.getFont().getName(), Font.BOLD, 14));
        total1.setForeground(Color.GRAY);
        total1.setVisible(false);

        JLabel player2Label = new JLabel("Player 2 (P2)");
        player2Label.setBounds(40,410,100,20);
        player2Label.setFont(new Font(player1Label.getFont().getName(), Font.BOLD, 14));
        player2Label.setForeground(Color.GRAY);
        player2Label.setVisible(false);

        JButton rollDice2 = new JButton(new ImageIcon("img/roll-dice.png"));
        rollDice2.setBounds(55,460,48,48);
        rollDice2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        rollDice2.setBorderPainted(false);
        rollDice2.setContentAreaFilled(false);
        rollDice2.setFocusPainted(false);
        rollDice2.setOpaque(false);
        rollDice2.setVisible(false);

        ImageIcon dice3Image = new ImageIcon("img/dice-small.gif");
        JLabel dice3 = new JLabel();
        dice3.setBounds(175,460,48,48);
        dice3.setIcon(dice3Image);
        dice3.setVisible(false);

        JLabel plus2 = new JLabel("+");
        plus2.setBounds(250,470,32,32);
        plus2.setFont(new Font(plus2.getFont().getName(), Font.BOLD, 32));
        plus2.setForeground(new Color(45, 131, 148));
        plus2.setVisible(false);

        ImageIcon dice4Image = new ImageIcon("img/dice-small.gif");
        JLabel dice4 = new JLabel();
        dice4.setBounds(300,460,48,48);
        dice4.setIcon(dice4Image);
        dice4.setVisible(false);

        JLabel equals2 = new JLabel("=");
        equals2.setBounds(385,470,32,32);
        equals2.setFont(new Font(equals2.getFont().getName(), Font.BOLD, 32));
        equals2.setForeground(new Color(45, 131, 148));
        equals2.setVisible(false);

        JLabel total2Value = new JLabel("00");
        total2Value.setBounds(430,460,100,50);
        total2Value.setFont(new Font(total2Value.getFont().getName(), Font.BOLD, 32));
        total2Value.setForeground(new Color(45, 131, 148));
        total2Value.setVisible(false);

        JLabel rollDice2Label = new JLabel("Roll 2 Dices");
        rollDice2Label.setBounds(40,520,100,20);
        rollDice2Label.setFont(new Font(rollDice2Label.getFont().getName(), Font.BOLD, 14));
        rollDice2Label.setForeground(Color.GRAY);
        rollDice2Label.setVisible(false);

        JLabel dice3Label = new JLabel("Dice 1");
        dice3Label.setBounds(177,520,100,20);
        dice3Label.setFont(new Font(dice3Label.getFont().getName(), Font.BOLD, 14));
        dice3Label.setForeground(Color.GRAY);
        dice3Label.setVisible(false);

        JLabel dice4Label = new JLabel("Dice 2");
        dice4Label.setBounds(303,520,100,20);
        dice4Label.setFont(new Font(dice4Label.getFont().getName(), Font.BOLD, 14));
        dice4Label.setForeground(Color.GRAY);
        dice4Label.setVisible(false);

        JLabel total2 = new JLabel("Total");
        total2.setBounds(430,520,100,20);
        total2.setFont(new Font(total2.getFont().getName(), Font.BOLD, 14));
        total2.setForeground(Color.GRAY);
        total2.setVisible(false);

        JButton restartButton = new JButton("Restart");
        restartButton.setBounds(190,590,140,40);
        restartButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        restartButton.setContentAreaFilled(false);
        restartButton.setFocusPainted(false);
        restartButton.setOpaque(false);
        restartButton.setVisible(false);

        JButton newChallengeButton = new JButton("New Challenge");
        newChallengeButton.setBounds(100,590,140,40);
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
            winCounter1.setVisible(true);
            winCounter2.setVisible(true);
            remainingChancesValue.setVisible(true);
            remainingChancesValue.setText("03");
            randomChallengeNumber = generateRandomNumber("challenge");
            String randomNumber = String.valueOf(randomChallengeNumber);
            if (randomChallengeNumber >=2 && randomChallengeNumber<=9){
                randomNumber = "0" + randomNumber;
            }
            randomChallengeValue.setVisible(true);
            randomChallengeValue.setText(randomNumber);
            winCounter1Value.setVisible(true);
            winCounter2Value.setVisible(true);
            player1Label.setVisible(true);
            player1Label.setText("Player 1 (P1) *");
            rollDice1.setVisible(true);
            dice1.setVisible(true);
            plus1.setVisible(true);
            dice2.setVisible(true);
            equals1.setVisible(true);
            total1Value.setVisible(true);
            rollDice1Label.setVisible(true);
            dice1Label.setVisible(true);
            dice2Label.setVisible(true);
            total1.setVisible(true);
            player2Label.setVisible(true);
            rollDice2.setVisible(true);
            rollDice2.setEnabled(false);
            dice3.setVisible(true);
            plus2.setVisible(true);
            dice4.setVisible(true);
            equals2.setVisible(true);
            total2Value.setVisible(true);
            rollDice2Label.setVisible(true);
            dice3Label.setVisible(true);
            dice4Label.setVisible(true);
            total2.setVisible(true);
            restartButton.setVisible(true);
            JOptionPane.showMessageDialog(twoPlayerWindow,"Player 1's turn!");
        });

        backButton.addActionListener(e -> {
            audioClips.playAudioClip("audio/pops.wav");
            mainWindow.setVisible(true);
            twoPlayerWindow.dispose();
        });

        rollDice1.addActionListener(e -> {
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
                diceTotal = dice1Number + dice2Number;
                if (diceTotal>=2 && diceTotal<=9){
                    total1Value.setText("0" + diceTotal);
                } else {
                    total1Value.setText(String.valueOf(diceTotal));
                }

                if (diceTotal == randomChallengeNumber){
                    p1Wins++;
                    win1++;
                    winCounter1Value.setText("0" + p1Wins);
                    audioClips.playAudioClip("audio/bell.wav");
                }

                if (chancesLeft == 0){
                    rollDice1.setEnabled(false);
                    JOptionPane.showMessageDialog(twoPlayerWindow,"Now, its Player 2's turn!");
                    rollDice2.setEnabled(true);
                    chancesLeft = 3;
                    remainingChancesValue.setText("0" + chancesLeft);
                    player1Label.setText("Player 1 (P1)");
                    player2Label.setText("Player 2 (P2) *");
                }
            }
        });

        rollDice2.addActionListener(e -> {
            chancesLeft--;
            if (chancesLeft >= 0){
                remainingChancesValue.setText("0" + chancesLeft);
                audioClips.playAudioClip("audio/rolling-dice.wav");
                dice1Number = generateRandomNumber("rollDice");
                dice2Number = generateRandomNumber(("rollDice"));
                switch (dice1Number){
                    case 1: dice3.setIcon(new ImageIcon("img/ds1.png"));
                        break;
                    case 2: dice3.setIcon(new ImageIcon("img/ds2.png"));
                        break;
                    case 3: dice3.setIcon(new ImageIcon("img/ds3.png"));
                        break;
                    case 4: dice3.setIcon(new ImageIcon("img/ds4.png"));
                        break;
                    case 5: dice3.setIcon(new ImageIcon("img/ds5.png"));
                        break;
                    case 6: dice3.setIcon(new ImageIcon("img/ds6.png"));
                        break;
                    default: break;
                }
                switch (dice2Number){
                    case 1: dice4.setIcon(new ImageIcon("img/ds1.png"));
                        break;
                    case 2: dice4.setIcon(new ImageIcon("img/ds2.png"));
                        break;
                    case 3: dice4.setIcon(new ImageIcon("img/ds3.png"));
                        break;
                    case 4: dice4.setIcon(new ImageIcon("img/ds4.png"));
                        break;
                    case 5: dice4.setIcon(new ImageIcon("img/ds5.png"));
                        break;
                    case 6: dice4.setIcon(new ImageIcon("img/ds6.png"));
                        break;
                    default: break;
                }
                diceTotal = dice1Number + dice2Number;
                if (diceTotal>=2 && diceTotal<=9){
                    total2Value.setText("0" + diceTotal);
                } else {
                    total2Value.setText(String.valueOf(diceTotal));
                }

                if (diceTotal == randomChallengeNumber){
                    p2Wins++;
                    win2++;
                    winCounter2Value.setText("0" + p2Wins);
                    audioClips.playAudioClip("audio/bell.wav");
                }

                if (chancesLeft == 0){
                    rollDice2.setEnabled(false);
                    if (win1 > win2){
                        JOptionPane.showMessageDialog(twoPlayerWindow,
                                "Player 1 wins!",
                                "Result for this challenge",
                                JOptionPane.INFORMATION_MESSAGE,
                                new ImageIcon("img/trophy.png"));
                    } else if (win2 > win1) {
                        JOptionPane.showMessageDialog(twoPlayerWindow,
                                "Player 2 wins!",
                                "Result for this challenge",
                                JOptionPane.INFORMATION_MESSAGE,
                                new ImageIcon("img/trophy.png"));
                    } else if (win1 == win2) {
                        JOptionPane.showMessageDialog(twoPlayerWindow,
                                "It's a tie!",
                                "Result for this challenge",
                                JOptionPane.INFORMATION_MESSAGE,
                                new ImageIcon("img/handshake.png"));
                    }
                    restartButton.setBounds(275,590,140,40);
                    newChallengeButton.setVisible(true);
                    win1 = 0;
                    win2 = 0;
                }
            }
        });

        newChallengeButton.addActionListener(e -> {
            audioClips.playAudioClip("audio/pops.wav");
            newChallengeButton.setVisible(false);
            restartButton.setBounds(190,590,140,40);
            JOptionPane.showMessageDialog(twoPlayerWindow,"Player 1's turn!");
            player1Label.setText("Player 1 (P1) *");
            player2Label.setText("Player 2 (P2)");

            randomChallengeNumber = generateRandomNumber("challenge");
            randomChallengeValue.setText(String.valueOf(randomChallengeNumber));
            if (randomChallengeNumber>=2 && randomChallengeNumber<=9){
                randomChallengeValue.setText("0" + randomChallengeNumber);
            }
            chancesLeft = 3;
            remainingChancesValue.setText("0" + chancesLeft);
            if (!rollDice1.isEnabled()){
                rollDice1.setEnabled(true);
            }
            dice1.setIcon(new ImageIcon("img/dice-small.gif"));
            dice2.setIcon(new ImageIcon("img/dice-small.gif"));
            dice3.setIcon(new ImageIcon("img/dice-small.gif"));
            dice4.setIcon(new ImageIcon("img/dice-small.gif"));
            dice1Number = 0;
            dice2Number = 0;
            diceTotal = 0;
            total1Value.setText("0" + diceTotal);
            total2Value.setText("0" + diceTotal);
        });

        restartButton.addActionListener(e -> {
            audioClips.playAudioClip("audio/pops.wav");
            newChallengeButton.setVisible(false);
            restartButton.setBounds(190,590,140,40);
            JOptionPane.showMessageDialog(twoPlayerWindow,"Player 1's turn!");
            player1Label.setText("Player 1 (P1) *");
            player2Label.setText("Player 2 (P2)");
            p1Wins = 0;
            p2Wins = 0;
            win1 = 0;
            win2 = 0;
            winCounter1Value.setText("0" + win1);
            winCounter2Value.setText("0" + win2);
            randomChallengeNumber = generateRandomNumber("challenge");
            randomChallengeValue.setText(String.valueOf(randomChallengeNumber));
            if (randomChallengeNumber>=2 && randomChallengeNumber<=9){
                randomChallengeValue.setText("0" + randomChallengeNumber);
            }
            chancesLeft = 3;
            remainingChancesValue.setText("0" + chancesLeft);
            if (!rollDice1.isEnabled()){
                rollDice1.setEnabled(true);
                rollDice2.setEnabled(false);
            }
            dice1.setIcon(new ImageIcon("img/dice-small.gif"));
            dice2.setIcon(new ImageIcon("img/dice-small.gif"));
            dice3.setIcon(new ImageIcon("img/dice-small.gif"));
            dice4.setIcon(new ImageIcon("img/dice-small.gif"));
            dice1Number = 0;
            dice2Number = 0;
            diceTotal = 0;
            total1Value.setText("0" + diceTotal);
            total2Value.setText("0" + diceTotal);
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
                chancesLeft = 0;
                randomChallengeNumber = 0;
                dice1Number = 0;
                dice2Number = 0;
                diceTotal = 0;
                p1Wins = 0;
                p2Wins = 0;
                win1 = 0;
                win2 = 0;
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
        twoPlayerWindow.add(winCounter1);
        twoPlayerWindow.add(winCounter2);
        twoPlayerWindow.add(remainingChancesValue);
        twoPlayerWindow.add(randomChallengeValue);
        twoPlayerWindow.add(winCounter1Value);
        twoPlayerWindow.add(winCounter2Value);
        twoPlayerWindow.add(player1Label);
        twoPlayerWindow.add(rollDice1);
        twoPlayerWindow.add(dice1);
        twoPlayerWindow.add(plus1);
        twoPlayerWindow.add(dice2);
        twoPlayerWindow.add(equals1);
        twoPlayerWindow.add(total1Value);
        twoPlayerWindow.add(rollDice1Label);
        twoPlayerWindow.add(dice1Label);
        twoPlayerWindow.add(dice2Label);
        twoPlayerWindow.add(total1);
        twoPlayerWindow.add(player2Label);
        twoPlayerWindow.add(rollDice2);
        twoPlayerWindow.add(dice3);
        twoPlayerWindow.add(plus2);
        twoPlayerWindow.add(dice4);
        twoPlayerWindow.add(equals2);
        twoPlayerWindow.add(total2Value);
        twoPlayerWindow.add(rollDice2Label);
        twoPlayerWindow.add(dice3Label);
        twoPlayerWindow.add(dice4Label);
        twoPlayerWindow.add(total2);
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