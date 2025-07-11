/**********************************************************************************************
 * Copyright (c) 2012  DCA-FEEC-UNICAMP
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl.html
 * <p>
 * Contributors:
 * K. Raizer, A. L. O. Paraense, E. M. Froes, R. R. Gudwin - initial API and implementation
 ***********************************************************************************************/
package br.unicamp.cst.util.viewer;



import br.unicamp.cst.util.Refresher;

import java.awt.BorderLayout;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import br.unicamp.cst.util.viewer.bindings.soar.PlansSubsystemViewer;
import br.unicamp.cst.core.entities.Codelet;
import br.unicamp.cst.core.entities.Mind;
import br.unicamp.cst.util.viewer.motivational.MotivationalSubsystemViewer;

/**
 *
 * @author gudwin
 */
public class MindViewer extends javax.swing.JFrame {

    private String windowName;
    private JTree jtree;
    private Mind m;

    private List<Codelet> motivationalCodelets;
    private List<Codelet> emotionalCodelets;
    private List<Codelet> goalCodelets;
    private List<Codelet> appraisalCodelets;
    private List<Codelet> moodCodelets;
    private List<Codelet> behavioralCodelets;


    private Thread threadMindEntities;
    private Thread threadDrives;
    private Thread threadEmotionalDrives;
    private Thread threadAppraisals;
    private Thread threadMoods;
    private Thread threadGoals;
    private Thread threadPlans;

    private int selectedIndex = 0;
    private double initialTime = Calendar.getInstance().getTimeInMillis();

    private ChartPanel motivationalChart;
    private ChartPanel codeletsChart;
    private ChartPanel emotionalChart;

    private DefaultTreeModel dtMotivationalCodelets;
    private DefaultTreeModel dtEmotionalCodelets;
    private DefaultTreeModel dtAppraisalCodelets;
    private DefaultTreeModel dtMoodCodelets;
    private DefaultTreeModel dtGoalCodelets;

    private MotivationalSubsystemViewer motivationalModuleViewer;
    private PlansSubsystemViewer plansSubsystemViewer;


    private long instant = 0;
    private boolean bStopRefresh = false;
    private Refresher refresher = null;


    /**
     * Creates new form WorldObjectViewer
     * 
     * @param mind A Mind object
     * @param windowName Name to be used by MindViewer
     * @param behavioralCodelets A list of behavioral codelets
     */
    public MindViewer(Mind mind, String windowName, List<Codelet> behavioralCodelets) {
        initComponents();
        setMind(mind);

        setWindowName(windowName);
        setBehavioralCodelets(behavioralCodelets);
        MindPanel newContentPane = new MindPanel(mind);
        java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 161;
        gridBagConstraints.ipady = 197;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 0);
        pnMindEntities.add(newContentPane, gridBagConstraints);
        setTitle(windowName);
        startMindEntitiesThread();
        buildMindModulePanels(mind);
        Logger.getLogger("ac.biu.nlp.nlp.engineml").setLevel(Level.OFF);
        Logger.getLogger("org.BIU.utils.logging.ExperimentLogger").setLevel(Level.OFF);
        Logger.getLogger("java.awt").setLevel(Level.OFF);
        Logger.getLogger("sun.awt").setLevel(Level.OFF);
        Logger.getLogger("javax.swing").setLevel(Level.OFF);
    }
    
    
    private void buildMindModulePanels(Mind mind){
        if(mind.getCodeletGroupsNumber() > 0) {
            if (mind.getCodeletGroupList("Motivational") != null) {
                if (mind.getCodeletGroupList("Motivational").size() > 0) {
                    motivationalModuleViewer = new MotivationalSubsystemViewer(Long.parseLong(txtRefreshTime.getText()), mind);
                    tbModules.add("Motivational Subsystem", motivationalModuleViewer);
                }
            }    
        }    


    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        pnSimulationControl = new javax.swing.JPanel();
        lblRefreshTime = new javax.swing.JLabel();
        txtRefreshTime = new javax.swing.JFormattedTextField();
        btnPlus = new javax.swing.JButton();
        btnMinus = new javax.swing.JButton();
        tbControl = new javax.swing.JTabbedPane();
        jspMainSplit = new javax.swing.JSplitPane();
        splMainChart = new javax.swing.JSplitPane();
        pnMindEntities = new javax.swing.JPanel();
        pnChart = new javax.swing.JPanel();
        cbRefreshChart = new javax.swing.JCheckBox();
        pnCodelets = new javax.swing.JPanel();
        sdChart = new javax.swing.JSlider();
        tbModules = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setSize(new java.awt.Dimension(1024, 768));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        lblRefreshTime.setText("Refresh Time (ms):");

        txtRefreshTime.setEditable(false);
        txtRefreshTime.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtRefreshTime.setText("100");
        txtRefreshTime.setToolTipText("");
        txtRefreshTime.setEnabled(false);

        btnPlus.setText("+");
        btnPlus.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnPlus.setMaximumSize(new java.awt.Dimension(25, 25));
        btnPlus.setMinimumSize(new java.awt.Dimension(25, 25));
        btnPlus.setPreferredSize(new java.awt.Dimension(25, 25));
        btnPlus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlusActionPerformed(evt);
            }
        });

        btnMinus.setText("-");
        btnMinus.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnMinus.setMaximumSize(new java.awt.Dimension(25, 25));
        btnMinus.setMinimumSize(new java.awt.Dimension(25, 25));
        btnMinus.setPreferredSize(new java.awt.Dimension(25, 25));
        btnMinus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnSimulationControlLayout = new javax.swing.GroupLayout(pnSimulationControl);
        pnSimulationControl.setLayout(pnSimulationControlLayout);
        pnSimulationControlLayout.setHorizontalGroup(
            pnSimulationControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnSimulationControlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRefreshTime)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRefreshTime, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPlus, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMinus, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(417, Short.MAX_VALUE))
        );
        pnSimulationControlLayout.setVerticalGroup(
            pnSimulationControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnSimulationControlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnSimulationControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnPlus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnSimulationControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblRefreshTime)
                        .addComponent(txtRefreshTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnMinus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.1;
        getContentPane().add(pnSimulationControl, gridBagConstraints);

        jspMainSplit.setDividerLocation(220);
        jspMainSplit.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        splMainChart.setDividerLocation(400);

        pnMindEntities.setMinimumSize(new java.awt.Dimension(0, 0));
        pnMindEntities.setLayout(new java.awt.GridBagLayout());
        splMainChart.setLeftComponent(pnMindEntities);
        pnMindEntities.getAccessibleContext().setAccessibleName("");

        pnChart.setMinimumSize(new java.awt.Dimension(0, 0));
        pnChart.setName(""); // NOI18N
        pnChart.setLayout(new java.awt.GridBagLayout());

        cbRefreshChart.setSelected(true);
        cbRefreshChart.setText("Auto Refresh");
        cbRefreshChart.setToolTipText("");
        cbRefreshChart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbRefreshChartActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        pnChart.add(cbRefreshChart, gridBagConstraints);

        pnCodelets.setBackground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout pnCodeletsLayout = new javax.swing.GroupLayout(pnCodelets);
        pnCodelets.setLayout(pnCodeletsLayout);
        pnCodeletsLayout.setHorizontalGroup(
            pnCodeletsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 366, Short.MAX_VALUE)
        );
        pnCodeletsLayout.setVerticalGroup(
            pnCodeletsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 177, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 10.0;
        pnChart.add(pnCodelets, gridBagConstraints);

        sdChart.setToolTipText("");
        sdChart.setValue(100);
        sdChart.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sdChartStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_END;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        pnChart.add(sdChart, gridBagConstraints);

        splMainChart.setRightComponent(pnChart);

        jspMainSplit.setTopComponent(splMainChart);

        tbModules.setMinimumSize(new java.awt.Dimension(0, 0));
        jspMainSplit.setRightComponent(tbModules);
        tbModules.getAccessibleContext().setAccessibleName("Motivational Subsystem");
        tbModules.getAccessibleContext().setAccessibleDescription("");

        tbControl.addTab("Mind's Entities", jspMainSplit);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        getContentPane().add(tbControl, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPlusActionPerformed(java.awt.event.ActionEvent evt) {                                        
        int value = Integer.parseInt(txtRefreshTime.getText());
        value += 10;
        txtRefreshTime.setText(String.valueOf(value));
        setRefreshTimeToModules(value);
    }

    private void btnMinusActionPerformed(java.awt.event.ActionEvent evt) {
        int value = Integer.parseInt(txtRefreshTime.getText());
        value -= 10;
        txtRefreshTime.setText(String.valueOf(value < 100 ? 100 : value));
        setRefreshTimeToModules(value < 100 ? 100 : value);
    }

    private void setRefreshTimeToModules(long value){
        if(motivationalModuleViewer != null){
            motivationalModuleViewer.setRefreshTime(value);
        }
        if(plansSubsystemViewer != null){
            plansSubsystemViewer.setRefreshTime(value);
        }
    }


    private void cbRefreshChartActionPerformed(java.awt.event.ActionEvent evt) {
        sdChart.setValue(100);
        if (cbRefreshChart.isSelected()) {
            synchronized (getThreadMindEntities()) {
                if (getThreadMindEntities() != null) {
                    getThreadMindEntities().notify();
                }
            }
        }
    }

    private void sdChartStateChanged(javax.swing.event.ChangeEvent evt) {
        if (sdChart.getValue() == 100) {
            getCodeletsChart().restoreAutoDomainBounds();
        } else {
            XYPlot plot = (XYPlot) getCodeletsChart().getChart().getPlot();
            double newUpper = sdChart.getValue() * getInstant() / sdChart.getMaximum();
            plot.getDomainAxis().setRange(newUpper - 10000, newUpper);
        }

    }
    
    private void startMindEntitiesThread() {

        long initialTime = Calendar.getInstance().getTimeInMillis();

        setThreadMindEntities(new Thread() {
            @Override
            public void run() {

                XYSeriesCollection dataset = new XYSeriesCollection();

                for (Codelet co : getBehavioralCodelets()) {
                    dataset.addSeries(new XYSeries(co.getName()));
                }

                synchronized (pnCodelets) {
                    pnCodelets.setLayout(new BorderLayout());
                    setCodeletsChart(br.unicamp.cst.util.viewer.ChartViewerUtil.createLineXYChart(dataset, "CodeRack Inspection", "Codelets", "Activation", 100));
                    pnCodelets.add(getCodeletsChart(), BorderLayout.CENTER);
                    pnCodelets.validate();
                }

                while (!isbStopRefresh()) {
                    if (cbRefreshChart.isSelected()) {
                        if (refresher != null) setInstant(refresher.refresh());
                        else setInstant(Calendar.getInstance().getTimeInMillis() - initialTime);
                        ChartViewerUtil.updateValuesInXYLineChart(dataset, getBehavioralCodelets(), getInstant());
                    }
                    try {
                        int refreshTime = txtRefreshTime.getText().trim().equals("") ? 100 : Integer.parseInt(txtRefreshTime.getText());
                        Thread.currentThread().sleep(refreshTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        getThreadMindEntities().start();
    }

    public void setMind(Mind m) {
        this.m = m;
    }

    public String getWindowName() {
        return windowName;
    }

    public void setWindowName(String windowName) {
        this.windowName = windowName;
    }

    public List<Codelet> getBehavioralCodelets() {
        return behavioralCodelets;
    }

    public void setBehavioralCodelets(List<Codelet> behavioralCodelets) {
        this.behavioralCodelets = behavioralCodelets;
    }

    public double getInitialTime() {
        return initialTime;
    }

    public void setInitialTime(double initialTime) {
        this.initialTime = initialTime;
    }

    public JTree getJtree() {
        return jtree;
    }

    public void setJtree(JTree jtree) {
        this.jtree = jtree;
    }

    public Thread getThreadAppraisals() {
        return threadAppraisals;
    }

    public void setThreadAppraisals(Thread threadAppraisals) {
        this.threadAppraisals = threadAppraisals;
    }

    public Thread getThreadMoods() {
        return threadMoods;
    }

    public void setThreadMoods(Thread threadMoods) {
        this.threadMoods = threadMoods;
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }

    public ChartPanel getMotivationalChart() {
        return motivationalChart;
    }

    public void setMotivationalChart(ChartPanel motivationalChart) {
        this.motivationalChart = motivationalChart;
    }

    public ChartPanel getCodeletsChart() {
        return codeletsChart;
    }

    public void setCodeletsChart(ChartPanel codeletsChart) {
        this.codeletsChart = codeletsChart;
    }

    public ChartPanel getEmotionalChart() {
        return emotionalChart;
    }

    public void setEmotionalChart(ChartPanel emotionalChart) {
        this.emotionalChart = emotionalChart;
    }

    public long getInstant() {
        return instant;
    }

    public void setInstant(long instant) {
        this.instant = instant;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMinus;
    private javax.swing.JButton btnPlus;
    private javax.swing.JCheckBox cbRefreshChart;
    private javax.swing.JSplitPane jspMainSplit;
    private javax.swing.JLabel lblRefreshTime;
    private javax.swing.JPanel pnChart;
    private javax.swing.JPanel pnCodelets;
    private javax.swing.JPanel pnMindEntities;
    private javax.swing.JPanel pnSimulationControl;
    private javax.swing.JSlider sdChart;
    private javax.swing.JSplitPane splMainChart;
    private javax.swing.JTabbedPane tbControl;
    private javax.swing.JTabbedPane tbModules;
    private javax.swing.JFormattedTextField txtRefreshTime;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the motivationalCodelets
     */
    public List<Codelet> getMotivationalCodelets() {
        return motivationalCodelets;
    }

    /**
     * @param motivationalCodelets the motivationalCodelets to set
     */
    public void setMotivationalCodelets(List<Codelet> motivationalCodelets) {
        this.motivationalCodelets = motivationalCodelets;
    }

    /**
     * @return the emotionalCodelets
     */
    public List<Codelet> getEmotionalCodelets() {
        return emotionalCodelets;
    }

    /**
     * @param emotionalCodelets the emotionalCodelets to set
     */
    public void setEmotionalCodelets(List<Codelet> emotionalCodelets) {
        this.emotionalCodelets = emotionalCodelets;
    }

    /**
     * @return the goalCodelets
     */
    public List<Codelet> getGoalCodelets() {
        return goalCodelets;
    }

    /**
     * @param goalCodelets the goalCodelets to set
     */
    public void setGoalCodelets(List<Codelet> goalCodelets) {
        this.goalCodelets = goalCodelets;
    }

    /**
     * @return the appraisalCodelets
     */
    public List<Codelet> getAppraisalCodelets() {
        return appraisalCodelets;
    }

    /**
     * @param appraisalCodelets the appraisalCodelets to set
     */
    public void setAppraisalCodelets(List<Codelet> appraisalCodelets) {
        this.appraisalCodelets = appraisalCodelets;
    }

    /**
     * @return the moodCodelets
     */
    public List<Codelet> getMoodCodelets() {
        return moodCodelets;
    }

    /**
     * @param moodCodelets the moodCodelets to set
     */
    public void setMoodCodelets(List<Codelet> moodCodelets) {
        this.moodCodelets = moodCodelets;
    }

    /**
     * @return the threadDrives
     */
    public Thread getThreadDrives() {
        return threadDrives;
    }

    /**
     * @param threadDrives the threadDrives to set
     */
    public void setThreadDrives(Thread threadDrives) {
        this.threadDrives = threadDrives;
    }

    /**
     * @return the threadEmotionalDrives
     */
    public Thread getThreadEmotionalDrives() {
        return threadEmotionalDrives;
    }

    /**
     * @param threadEmotionalDrives the threadEmotionalDrives to set
     */
    public void setThreadEmotionalDrives(Thread threadEmotionalDrives) {
        this.threadEmotionalDrives = threadEmotionalDrives;
    }

    /**
     * @return the bStopRefresh
     */
    public boolean isbStopRefresh() {
        return bStopRefresh;
    }

    /**
     * @param bStopRefresh the bStopRefresh to set
     */
    public void setbStopRefresh(boolean bStopRefresh) {
        this.bStopRefresh = bStopRefresh;
    }

    /**
     * @return the threadMindEntities
     */
    public Thread getThreadMindEntities() {
        return threadMindEntities;
    }

    /**
     * @param threadMindEntities the threadMindEntities to set
     */
    public void setThreadMindEntities(Thread threadMindEntities) {
        this.threadMindEntities = threadMindEntities;
    }

    /**
     * @return the dtMotivationalCodelets
     */
    public DefaultTreeModel getDtMotivationalCodelets() {
        return dtMotivationalCodelets;
    }

    /**
     * @param dtMotivationalCodelets the dtMotivationalCodelets to set
     */
    public void setDtMotivationalCodelets(DefaultTreeModel dtMotivationalCodelets) {
        this.dtMotivationalCodelets = dtMotivationalCodelets;
    }

    /**
     * @return the dtEmotionalCodelets
     */
    public DefaultTreeModel getDtEmotionalCodelets() {
        return dtEmotionalCodelets;
    }

    /**
     * @param dtEmotionalCodelets the dtEmotionalCodelets to set
     */
    public void setDtEmotionalCodelets(DefaultTreeModel dtEmotionalCodelets) {
        this.dtEmotionalCodelets = dtEmotionalCodelets;
    }

    /**
     * @return the dtAppraisalCodelets
     */
    public DefaultTreeModel getDtAppraisalCodelets() {
        return dtAppraisalCodelets;
    }

    /**
     * @param dtAppraisalCodelets the dtAppraisalCodelets to set
     */
    public void setDtAppraisalCodelets(DefaultTreeModel dtAppraisalCodelets) {
        this.dtAppraisalCodelets = dtAppraisalCodelets;
    }

    /**
     * @return the dtMoodCodelets
     */
    public DefaultTreeModel getDtMoodCodelets() {
        return dtMoodCodelets;
    }

    /**
     * @param dtMoodCodelets the dtMoodCodelets to set
     */
    public void setDtMoodCodelets(DefaultTreeModel dtMoodCodelets) {
        this.dtMoodCodelets = dtMoodCodelets;
    }

    /**
     * @return the dtGoalCodelets
     */
    public DefaultTreeModel getDtGoalCodelets() {
        return dtGoalCodelets;
    }

    /**
     * @param dtGoalCodelets the dtGoalCodelets to set
     */
    public void setDtGoalCodelets(DefaultTreeModel dtGoalCodelets) {
        this.dtGoalCodelets = dtGoalCodelets;
    }

    /**
     * @return the threadGoals
     */
    public Thread getThreadGoals() {
        return threadGoals;
    }

    /**
     * @param threadGoals the threadGoals to set
     */
    public void setThreadGoals(Thread threadGoals) {
        this.threadGoals = threadGoals;
    }
    
    /**
     * @return the Refresher used in the Codelet Monitor
     */
    public Refresher getRefresher() {
        return refresher;
    }

    /**
     * @param ref the Refresher used in the Codelet Monitor
     */
    public void setRefresher(Refresher ref) {
        this.refresher = ref;
    }

   

    /**
     * @return the threadPlans
     */
    public Thread getThreadPlans() {
        return threadPlans;
    }

    /**
     * @param threadPlans the threadPlans to set
     */
    public void setThreadPlans(Thread threadPlans) {
        this.threadPlans = threadPlans;
    }
    
}
