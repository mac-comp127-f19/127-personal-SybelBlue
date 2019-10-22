package terribleSpacing;

import javax.swing.*;
import java.awt.*;

public class TextMangler extends JPanel {
    private final JFrame windowFrame;
    private final TextArea textArea;
    private WhitespaceMangler textMangler;

    private boolean hasMangled = false;

    public static void main(String[] args) {
        new TextMangler("Mangle me");
    }

    public TextMangler(String text) {
        windowFrame = new JFrame("Text Mangler");
        windowFrame.setSize(500, 700);
        windowFrame.setVisible(true);
        windowFrame.setLocation(30, 30);
        Container contentPane = windowFrame.getContentPane();
        contentPane.setLayout(new BorderLayout());

        setLayout(new BorderLayout());

        textArea = new TextArea(text);

        JPanel controlPanel = new JPanel(new BorderLayout());
        JButton stepButton = new JButton("Next Step");
        stepButton.addActionListener(e -> onStepButtonClicked());
        controlPanel.add(stepButton, BorderLayout.CENTER);

        add(controlPanel, BorderLayout.AFTER_LAST_LINE);
        add(textArea, BorderLayout.CENTER);

        contentPane.add(this, BorderLayout.CENTER);
        windowFrame.revalidate();
    }

    private void onStepButtonClicked() {
        if (!hasMangled) {
            hasMangled = true;
            textArea.setEditable(false);
            textMangler = new WhitespaceMangler(textArea.getText());
        }

        textMangler.mangleALittle();
        textArea.setText(textMangler.getLines());
    }
}
