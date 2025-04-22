// コンポーネントの配置を調整するために必要
import java.awt.FlowLayout;
import java.awt.event.ActionEvent; // ボタンを押したときに反応するために必要
import java.awt.event.ActionListener; // ボタンの動作を管理するために必要

// Swingのクラスを使うために必要
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;

public class CountdownTimerGUI {
    private JFrame frame;  // アプリのウィンドウ
    private JLabel label;  // タイマーの時間を表示するラベル
    private JTextField textField;  // ユーザーが入力するためのテキストフィールド
    private JButton startButton;  // カウントダウンを開始するボタン
    private Timer timer;  // 1秒ごとにカウントダウンを更新するためのタイマー
    private int timeRemaining;  // 残り時間（秒）

    public CountdownTimerGUI() {
        // アプリケーションのウィンドウを作成
        frame = new JFrame("Countdown Timer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // ウィンドウを閉じたときにアプリも終了
        frame.setSize(300, 150);  // ウィンドウのサイズを設定
        frame.setLayout(new FlowLayout());  // コンポーネントを並べるレイアウト

        // タイマーのメッセージを表示するラベル
        label = new JLabel("秒数で記入して");
        
        // ユーザーが秒数を入力するためのテキストフィールド
        textField = new JTextField(10);  // 幅10のテキストフィールド
        
        // タイマーを開始するボタン
        startButton = new JButton("スタート");

        // ボタンが押されたときの動作を設定
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // テキストフィールドの入力を整数に変換してtimeRemainingにセット
                    timeRemaining = Integer.parseInt(textField.getText());
                    startCountdown();  // カウントダウンを開始
                } catch (NumberFormatException ex) {
                    // 入力が無効な場合はエラーメッセージを表示
                    JOptionPane.showMessageDialog(frame, "Please enter a valid number.");
                }
            }
        });

        // ウィンドウにコンポーネントを追加
        frame.add(label);
        frame.add(textField);
        frame.add(startButton);
        frame.setVisible(true);  // ウィンドウを表示
    }

    // カウントダウンを開始するメソッド
    private void startCountdown() {
        // 1秒ごとに実行されるタイマーを作成
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (timeRemaining > 0) {
                    timeRemaining--;  // 1秒減らす
                    label.setText("残り" + timeRemaining + "秒");
                } else {
                    label.setText("Time's up!");  // タイマー終了時に表示
                    timer.stop();  // タイマーを停止
                }
            }
        });
        timer.start();  // タイマーを開始
    }

    // アプリケーションを起動するメインメソッド
    public static void main(String[] args) {
        new CountdownTimerGUI();  // CountdownTimerGUIクラスのインスタンスを作成
    }
}
