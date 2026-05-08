package Vues;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;

/**
 * Centralised design system.
 * Call {@link #applyGlobalLook()} once at application start-up.
 */
public class AppTheme {

    // ── Palette ─────────────────────────────────────────────────────────────
    public static final Color PRIMARY       = new Color(26,  35, 126);   // deep blue
    public static final Color PRIMARY_DARK  = new Color(13,  71, 161);
    public static final Color PRIMARY_LIGHT = new Color(57,  73, 171);
    public static final Color ACCENT        = new Color(66, 165, 245);
    public static final Color BG            = new Color(245, 247, 250);
    public static final Color CARD          = Color.WHITE;
    public static final Color TEXT          = new Color( 33,  33,  33);
    public static final Color TEXT_MUTED    = new Color(117, 117, 117);
    public static final Color SUCCESS       = new Color( 76, 175,  80);
    public static final Color ERROR         = new Color(244,  67,  54);
    public static final Color WARNING       = new Color(255, 152,   0);
    public static final Color ROW_ALT       = new Color(232, 234, 246);
    public static final Color BORDER        = new Color(220, 220, 220);

    // ── Typography ──────────────────────────────────────────────────────────
    public static final Font F_TITLE    = new Font("Segoe UI", Font.BOLD,  24);
    public static final Font F_HEADING  = new Font("Segoe UI", Font.BOLD,  16);
    public static final Font F_BODY     = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font F_SMALL    = new Font("Segoe UI", Font.PLAIN, 12);
    public static final Font F_BOLD_SM  = new Font("Segoe UI", Font.BOLD,  12);
    public static final Font F_BTN      = new Font("Segoe UI", Font.BOLD,  13);

    // ── Look & feel ─────────────────────────────────────────────────────────
    public static void applyGlobalLook() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}
        UIManager.put("TabbedPane.selected",         new Color(232, 234, 246));
        UIManager.put("TabbedPane.background",       BG);
        UIManager.put("TabbedPane.contentBorderInsets", new Insets(0,0,0,0));
    }

    // ── Button factory ──────────────────────────────────────────────────────
    public static JButton btn(String label, Color bg) {
        JButton b = new JButton(label) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getModel().isRollover() ? bg.darker() : bg);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        b.setFont(F_BTN);
        b.setForeground(Color.WHITE);
        b.setBackground(bg);
        b.setContentAreaFilled(false);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
        b.setOpaque(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setPreferredSize(new Dimension(130, 36));
        return b;
    }

    public static JButton btnPrimary(String t) { return btn(t, PRIMARY);  }
    public static JButton btnSuccess(String t)  { return btn(t, SUCCESS);  }
    public static JButton btnDanger(String t)   { return btn(t, ERROR);    }
    public static JButton btnWarning(String t)  { return btn(t, WARNING);  }

    // ── Input factory ───────────────────────────────────────────────────────
    public static JTextField textField(String initial) {
        JTextField f = new JTextField(initial);
        styleInput(f);
        return f;
    }
    public static JPasswordField passwordField() {
        JPasswordField f = new JPasswordField();
        styleInput(f);
        return f;
    }
    private static void styleInput(JComponent f) {
        f.setFont(F_BODY);
        f.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER),
            BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        f.setPreferredSize(new Dimension(220, 38));
    }

    // ── Table styling ───────────────────────────────────────────────────────
    public static void styleTable(JTable t) {
        t.setFont(F_BODY);
        t.setRowHeight(40);
        t.setSelectionBackground(new Color(197, 202, 233));
        t.setSelectionForeground(TEXT);
        t.setGridColor(new Color(235, 235, 235));
        t.setShowVerticalLines(false);
        t.setIntercellSpacing(new Dimension(0, 1));
        t.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        JTableHeader h = t.getTableHeader();
        h.setFont(F_BOLD_SM);
        h.setBackground(PRIMARY);
        h.setForeground(Color.WHITE);
        h.setPreferredSize(new Dimension(0, 44));
        h.setReorderingAllowed(false);

        t.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable tbl, Object val, boolean sel, boolean foc, int row, int col) {
                Component c = super.getTableCellRendererComponent(tbl, val, sel, foc, row, col);
                if (!sel) c.setBackground(row % 2 == 0 ? Color.WHITE : ROW_ALT);
                setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
                return c;
            }
        });

        
        t.setDefaultRenderer(Boolean.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable tbl, Object val, boolean sel, boolean foc, int row, int col) {
                boolean available = Boolean.TRUE.equals(val);
                JLabel lbl = new JLabel(available ? "Disponible" : "Emprunte", SwingConstants.CENTER);
                lbl.setFont(F_BOLD_SM);
                lbl.setOpaque(true);
                lbl.setForeground(new Color(available ? 0x4CAF50 : 0xF44336));
                lbl.setBackground(row % 2 == 0 ? Color.WHITE : ROW_ALT);
                return lbl;
            }
        });
    }

   
    public static TableCellRenderer statusRenderer() {
        return new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable t, Object val, boolean sel, boolean foc, int row, int col) {
                super.getTableCellRendererComponent(t, val, sel, foc, row, col);
                if (!sel) {
                    String s = val == null ? "" : val.toString();
                    if ("En cours".equals(s))       setForeground(new Color(255, 152, 0));
                    else if ("Retourne".equals(s))  setForeground(new Color(76, 175, 80));
                    else if ("En retard".equals(s)) setForeground(new Color(244, 67, 54));
                    else                            setForeground(new Color(33, 33, 33));
                }
                setFont(F_BOLD_SM);
                setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
                return this;
            }
        };
    }

    // ── Misc helpers ────────────────────────────────────────────────────────
    public static JScrollPane scrollPane(JTable table) {
        JScrollPane sp = new JScrollPane(table);
        sp.setBorder(BorderFactory.createLineBorder(BORDER));
        sp.getViewport().setBackground(Color.WHITE);
        return sp;
    }

    public static JPanel navBar(String rightText, JButton... extraButtons) {
        JPanel bar = new JPanel(new BorderLayout()) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setPaint(new GradientPaint(0, 0, PRIMARY, getWidth(), 0, PRIMARY_DARK));
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.dispose();
            }
        };
        bar.setPreferredSize(new Dimension(0, 60));
        bar.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

        JLabel logo = new JLabel("📚  BiblioGest");
        logo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        logo.setForeground(Color.WHITE);

        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        right.setOpaque(false);
        if (rightText != null) {
            JLabel lbl = new JLabel("👤  " + rightText);
            lbl.setFont(F_BODY);
            lbl.setForeground(new Color(200, 215, 255));
            right.add(lbl);
        }
        for (JButton b : extraButtons) right.add(b);

        bar.add(logo,  BorderLayout.WEST);
        bar.add(right, BorderLayout.EAST);
        return bar;
    }

    private AppTheme() {}
}
