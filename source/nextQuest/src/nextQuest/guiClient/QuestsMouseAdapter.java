package nextQuest.guiClient;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QuestsMouseAdapter extends MouseAdapter {
    private QuestsRow row;

    public QuestsMouseAdapter(QuestsRow row) {
        this.row = row;
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        ((QuestsPanel) row.getParent()).selectRow(row);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        ((QuestsPanel) row.getParent()).setHighlightedRow(row);
    }



}
