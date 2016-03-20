/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasinteligentes;

/**
 *
 * @author Jacichen
 */
public class GUI extends javax.swing.JFrame {
    private Agent agent;
    private TextPrinter printer;
    /**
     * Creates new form Interface
     */
    public GUI() {
        initComponents();
        printer = new JTextAreaPrinter(terminalBox);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        terminalScroll = new javax.swing.JScrollPane();
        terminalBox = new javax.swing.JTextArea();
        continueButton = new javax.swing.JButton();
        renderPanel = new sistemasinteligentes.RenderPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        terminalBox.setEditable(false);
        terminalBox.setColumns(20);
        terminalBox.setRows(5);
        terminalScroll.setViewportView(terminalBox);

        continueButton.setText("Continuar");
        continueButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                continueButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout renderPanelLayout = new javax.swing.GroupLayout(renderPanel);
        renderPanel.setLayout(renderPanelLayout);
        renderPanelLayout.setHorizontalGroup(
            renderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
        );
        renderPanelLayout.setVerticalGroup(
            renderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 147, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(renderPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(terminalScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(continueButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(renderPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(continueButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(terminalScroll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void continueButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_continueButtonActionPerformed
        agent.advance();
        renderPanel.repaint();
        printer.update();
    }//GEN-LAST:event_continueButtonActionPerformed
    
    public void assignRenderizable(Renderizable rend){
        renderPanel.assignRenderizable(rend);
    }
    
    public void unasignRenderizable(Renderizable rend){
        renderPanel.unassignRenderizable(rend);
    }
    
    public void assignPrintable(Printable p){
        printer.assignPrintable(p);
    }
    
    public void unassignPrintable(Printable p){
        printer.unassignPrintable(p);
    }

    public void setAgent(Agent agent){
        this. agent = agent; 
    }
    
    public void printText(String text)
    {
        terminalBox.setText(text);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton continueButton;
    private sistemasinteligentes.RenderPanel renderPanel;
    private javax.swing.JTextArea terminalBox;
    private javax.swing.JScrollPane terminalScroll;
    // End of variables declaration//GEN-END:variables
}
