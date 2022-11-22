/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modele.ModeleListe;
import sae.Adjacent;
import static sae.Initialisation.makeInit;
import sae.Sommet;

/**
 *
 * @author tilio
 */
public class Ihm extends javax.swing.JFrame {
    private List<Sommet> listeSommet;
    private ModeleListe modeleSommet = new ModeleListe();
    private ModeleListe modeleLien = new ModeleListe();
    private ModeleListe modeleType = new ModeleListe();
    private ModeleListe modeleSaut = new ModeleListe();
    
    /**
     * Creates new form ihm
     */
    public Ihm() {
        List<String> listeVide = new ArrayList<>();
        modeleSommet.initialize(listeVide);
        initComponents();
        jListMainSommet.setModel(modeleSommet);
        jListMainLien.setModel(modeleLien);
        jListSaut.setModel(modeleSaut);
        jListChoixSaut.setModel(modeleSommet);
        jListComparer1.setModel(modeleSommet);
        jListComparer2.setModel(modeleSommet);
        jListDistance1.setModel(modeleSommet);
        jListDistance2.setModel(modeleSommet);
    }
    
    public void fillListMainSommet(){
        for(Sommet s : listeSommet){
            modeleSommet.ajouter(s.getNomSommet());
        }
    }
    
    public void fillListMainLien(){
        for(Sommet s : listeSommet){
            for(Adjacent a : s.getListAdjacent()){
                modeleLien.ajouter(s.getNomSommet()+" - "+ a.getSommetAdj());
            }
        }
    }
    
    public void fillListVille(){
        modeleType.vider();
        for(Sommet s : listeSommet){
            if(s.getTypeDeSommet().contains("V")){
            modeleType.ajouter(s.getNomSommet());
            }
        }
    }
    
    public void fillListResto(){
        modeleType.vider();
        for(Sommet s : listeSommet){
            if(s.getTypeDeSommet().contains("R")){
            modeleType.ajouter(s.getNomSommet());
            }
        }
    }
    
    public void fillListLoisir(){
        modeleType.vider();
        for(Sommet s : listeSommet){
            if(s.getTypeDeSommet().contains("L")){
            modeleType.ajouter(s.getNomSommet());
            }
        }
    }
    
    public void fillListAutoroute(){
        modeleType.vider();
        for(Sommet s : listeSommet){
            for(Adjacent a : s.getListAdjacent()){
                if(a.getTypeDeRoute().contains("A")){
                modeleType.ajouter(s.getNomSommet()+" - "+ a.getSommetAdj());
                }
            }
        }
    }
    
    public void fillListNationale(){
        modeleType.vider();
        for(Sommet s : listeSommet){
            for(Adjacent a : s.getListAdjacent()){
                if(a.getTypeDeRoute().contains("N")){
                modeleType.ajouter(s.getNomSommet()+" - "+ a.getSommetAdj());
                }
            }
        }
    }
    
    public void fillListDepartementale(){
        modeleType.vider();
        for(Sommet s : listeSommet){
            for(Adjacent a : s.getListAdjacent()){
                if(a.getTypeDeRoute().contains("D")){
                modeleType.ajouter(s.getNomSommet()+" - "+ a.getSommetAdj());
                }
            }
        }
    }
    
    //jListChoixSaut.getSelectedValue()
    
    public void fillList1Saut(){
        modeleSaut.vider();
        for(Sommet s : listeSommet){
            if(s.getNomSommet() == jListChoixSaut.getSelectedValue()){
                for(Adjacent a : s.getListAdjacent()){
                    modeleSaut.ajouter(a.getSommetAdj());
                }
            }
        }
    }
    
    public void fillList2Saut(){
        modeleSaut.vider();
        for(Sommet s : listeSommet){
            if(s.getNomSommet() == jListChoixSaut.getSelectedValue()){
                for(Adjacent a : s.getListAdjacent()){
                    for(Sommet ss : listeSommet){
                        if(a.getSommetAdj().contains(ss.getNomSommet())){
                            for(Adjacent aa : ss.getListAdjacent()){
                                if(!aa.getSommetAdj().contains(jListChoixSaut.getSelectedValue())){
                                    modeleSaut.ajouter(aa.getSommetAdj());
                                }
                            }
                        }
                    }
                }
            }    
        }
    }
    
    public void fillList2Distance(){
        jTextFieldReponseDistance.setText("");
        if((jListDistance1.getSelectedValue() != null)&(jListDistance2.getSelectedValue() != null)){
           for(Sommet s : listeSommet){
                if(s.getNomSommet() == jListDistance1.getSelectedValue()){
                    for(Adjacent a : s.getListAdjacent()){
                        for(Sommet ss : listeSommet){
                            if(a.getSommetAdj().contains(ss.getNomSommet())){
                                for(Adjacent aa : ss.getListAdjacent()){
                                    if((!aa.getSommetAdj().contains(jListDistance1.getSelectedValue()))&(aa.getSommetAdj().contains(jListDistance2.getSelectedValue()))){
                                        jTextFieldReponseDistance.setText("Vrai");
                                    }
                                    else if(!jTextFieldReponseDistance.getText().contains("Vrai")){
                                        jTextFieldReponseDistance.setText("Faux");
                                    }
                                }
                            }
                        }
                    }
                }    
            }
        }
        else{
            jTextFieldReponseDistance.setText("ERREUR, Sélectionnez 2 valeurs");
        }
    }
    
    public void Comparer(){
        int ville1=0, ville2=0, resto1=0, resto2=0, loisir1=0, loisir2=0;
        if((jListComparer1.getSelectedValue() != null)&(jListComparer2.getSelectedValue() != null)){
//----------------------------- Compte les types 2 Sauts du premier sommet selectionné
            for(Sommet s : listeSommet){
                if(s.getNomSommet() == jListComparer1.getSelectedValue()){
                    for(Adjacent a : s.getListAdjacent()){
                        for(Sommet ss : listeSommet){
                            if(a.getSommetAdj().contains(ss.getNomSommet())){
                                for(Adjacent aa : ss.getListAdjacent()){
                                    if(!aa.getSommetAdj().contains(jListComparer1.getSelectedValue())){
                                        for(Sommet sss : listeSommet){
                                            if(aa.getSommetAdj().contains(sss.getNomSommet())){
                                                if(sss.getTypeDeSommet().contains("V")){
                                                    ville1++;
                                                }
                                                else if (sss.getTypeDeSommet().contains("R")){
                                                    resto1++;
                                                }
                                                else if (sss.getTypeDeSommet().contains("L")){
                                                    loisir1++;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }    
            }
                    
//---------------------------- Compte les types 1 Sauts du premier sommet selectionné           
            for(Sommet s : listeSommet){
                if(s.getNomSommet() == jListComparer1.getSelectedValue()){
                    for(Adjacent a : s.getListAdjacent()){
                        for(Sommet ss : listeSommet){
                            if(a.getSommetAdj().contains(ss.getNomSommet())){
                                if(ss.getTypeDeSommet().contains("V")){
                                    ville1++;
                                }
                                else if (ss.getTypeDeSommet().contains("R")){
                                    resto1++;
                                }
                                else if (ss.getTypeDeSommet().contains("L")){
                                    loisir1++;
                                }
                            }
                        }
                    }
                }
            }
            
            
//----------------------------- Compte les types 2 Sauts du second sommet selectionné           
            for(Sommet s : listeSommet){
                if(s.getNomSommet() == jListComparer2.getSelectedValue()){
                    for(Adjacent a : s.getListAdjacent()){
                        for(Sommet ss : listeSommet){
                            if(a.getSommetAdj().contains(ss.getNomSommet())){
                                for(Adjacent aa : ss.getListAdjacent()){
                                    if(!aa.getSommetAdj().contains(jListComparer2.getSelectedValue())){
                                        for(Sommet sss : listeSommet){
                                            if(aa.getSommetAdj().contains(sss.getNomSommet())){
                                                if(sss.getTypeDeSommet().contains("V")){
                                                    ville2++;
                                                }
                                                else if (sss.getTypeDeSommet().contains("R")){
                                                    resto2++;
                                                }
                                                else if (sss.getTypeDeSommet().contains("L")){
                                                    loisir2++;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }    
            }
            
//---------------------------- Compte les types 1 Sauts du second sommet selectionné           
            for(Sommet s : listeSommet){
                if(s.getNomSommet() == jListComparer2.getSelectedValue()){
                    for(Adjacent a : s.getListAdjacent()){
                        for(Sommet ss : listeSommet){
                            if(a.getSommetAdj().contains(ss.getNomSommet())){
                                if(ss.getTypeDeSommet().contains("V")){
                                    ville2++;
                                }
                                else if (ss.getTypeDeSommet().contains("R")){
                                    resto2++;
                                }
                                else if (ss.getTypeDeSommet().contains("L")){
                                    loisir2++;
                                }
                            }
                        }
                    }
                }
            }

//-----------OUVERTE
            if(ville1 > ville2){
                jTextFieldOuverture.setText(jListComparer1.getSelectedValue()+" est plus ouverte avec "+ville1+" ville(s) contre "+ville2);
            }
            else if(ville1 < ville2){
                jTextFieldOuverture.setText(jListComparer2.getSelectedValue()+" est plus ouverte avec "+ville2+" ville(s) contre "+ville1);
            }
            else{
                jTextFieldOuverture.setText("Egalité avec "+ville1+" ville(s)");
            }
            //-----------GASTRONOMIQUE
            if(resto1 > resto2){
                jTextFieldGastronomie.setText(jListComparer1.getSelectedValue()+" est plus gastronomique avec "+resto1+" restaurant(s) contre "+resto2);
            }
            else if(resto1 < resto2){
                jTextFieldGastronomie.setText(jListComparer2.getSelectedValue()+" est plus gastronomique avec "+resto2+" restaurant(s) contre "+resto1);
            }
            else{
                jTextFieldGastronomie.setText("Egalité avec "+resto1+" restaurant(s)");
            }
            //-----------CULTURELLE
            if(loisir1 > loisir2){
                jTextFieldCulture.setText(jListComparer1.getSelectedValue()+" est plus culturelle avec "+loisir1+" loisir(s) contre "+loisir2);
            }
            else if(loisir1 < loisir2){
                jTextFieldCulture.setText(jListComparer2.getSelectedValue()+" est plus culturelle avec "+loisir2+" loisir(s) contre "+loisir1);
            }
            else{
                jTextFieldCulture.setText("Egalité avec "+loisir1+" loisir(s)");
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

        jFileChooser = new javax.swing.JFileChooser();
        jDialogAfficher = new javax.swing.JDialog();
        jRadioButtonVilles = new javax.swing.JRadioButton();
        jRadioButtonRestaurants = new javax.swing.JRadioButton();
        jRadioButtonLoisirs = new javax.swing.JRadioButton();
        jRadioButtonAutoroutes = new javax.swing.JRadioButton();
        jRadioButtonNationals = new javax.swing.JRadioButton();
        jRadioButtonDepartementales = new javax.swing.JRadioButton();
        jLabelLiens = new javax.swing.JLabel();
        jLabelSommet = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jLabelNombre = new javax.swing.JLabel();
        jTextFieldNombre = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        buttonGroupType = new javax.swing.ButtonGroup();
        jDialogSaut = new javax.swing.JDialog();
        jRadioButton2Saut = new javax.swing.JRadioButton();
        jRadioButton1Saut = new javax.swing.JRadioButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jListSaut = new javax.swing.JList<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        jListChoixSaut = new javax.swing.JList<>();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldNombreSaut = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        buttonGroupSaut = new javax.swing.ButtonGroup();
        jDialogComparer = new javax.swing.JDialog();
        jScrollPane5 = new javax.swing.JScrollPane();
        jListComparer2 = new javax.swing.JList<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        jListComparer1 = new javax.swing.JList<>();
        jTextFieldOuverture = new javax.swing.JTextField();
        jTextFieldGastronomie = new javax.swing.JTextField();
        jTextFieldCulture = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jComparer = new javax.swing.JButton();
        jDialogDistance = new javax.swing.JDialog();
        jScrollPane7 = new javax.swing.JScrollPane();
        jListDistance1 = new javax.swing.JList<>();
        jTextFieldReponseDistance = new javax.swing.JTextField();
        jScrollPane8 = new javax.swing.JScrollPane();
        jListDistance2 = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPaneMainMenu = new javax.swing.JScrollPane();
        jListMainSommet = new javax.swing.JList<>();
        jButtonAfficher = new javax.swing.JButton();
        jLabelTitrejListMenu = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListMainLien = new javax.swing.JList<>();
        jTextFieldNombreMainLien = new javax.swing.JTextField();
        jTextFieldNombreMainSommet = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButtonSaut = new javax.swing.JButton();
        jButtonComparerSommet = new javax.swing.JButton();
        jButton2distance = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        jDialogAfficher.setTitle("Afficher par type d'éléments");

        buttonGroupType.add(jRadioButtonVilles);
        jRadioButtonVilles.setText("VILLES");
        jRadioButtonVilles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonVillesActionPerformed(evt);
            }
        });

        buttonGroupType.add(jRadioButtonRestaurants);
        jRadioButtonRestaurants.setText("RESTAURANTS");
        jRadioButtonRestaurants.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonRestaurantsActionPerformed(evt);
            }
        });

        buttonGroupType.add(jRadioButtonLoisirs);
        jRadioButtonLoisirs.setText("LOISIRS");
        jRadioButtonLoisirs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonLoisirsActionPerformed(evt);
            }
        });

        buttonGroupType.add(jRadioButtonAutoroutes);
        jRadioButtonAutoroutes.setText("AUTOROUTES");
        jRadioButtonAutoroutes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonAutoroutesActionPerformed(evt);
            }
        });

        buttonGroupType.add(jRadioButtonNationals);
        jRadioButtonNationals.setText("NATIONALES");
        jRadioButtonNationals.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonNationalsActionPerformed(evt);
            }
        });

        buttonGroupType.add(jRadioButtonDepartementales);
        jRadioButtonDepartementales.setText("DEPARTEMENTALES");
        jRadioButtonDepartementales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonDepartementalesActionPerformed(evt);
            }
        });

        jLabelLiens.setText("LIENS");

        jLabelSommet.setText("SOMMETS");

        jList2.setModel(modeleType);
        jScrollPane1.setViewportView(jList2);

        jLabelNombre.setText("Nombre :");

        jTextFieldNombre.setEditable(false);
        jTextFieldNombre.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel10.setText("Selectionnez un type pour voir les éléments et leur nombre :");

        javax.swing.GroupLayout jDialogAfficherLayout = new javax.swing.GroupLayout(jDialogAfficher.getContentPane());
        jDialogAfficher.getContentPane().setLayout(jDialogAfficherLayout);
        jDialogAfficherLayout.setHorizontalGroup(
            jDialogAfficherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogAfficherLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jDialogAfficherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialogAfficherLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addContainerGap(144, Short.MAX_VALUE))
                    .addGroup(jDialogAfficherLayout.createSequentialGroup()
                        .addGroup(jDialogAfficherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButtonNationals)
                            .addComponent(jRadioButtonRestaurants)
                            .addComponent(jRadioButtonVilles)
                            .addComponent(jLabelSommet)
                            .addComponent(jRadioButtonLoisirs)
                            .addComponent(jRadioButtonDepartementales)
                            .addComponent(jLabelLiens, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRadioButtonAutoroutes))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jDialogAfficherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jDialogAfficherLayout.createSequentialGroup()
                                .addComponent(jLabelNombre)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25))))
        );
        jDialogAfficherLayout.setVerticalGroup(
            jDialogAfficherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogAfficherLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addGroup(jDialogAfficherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialogAfficherLayout.createSequentialGroup()
                        .addComponent(jLabelSommet)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButtonVilles)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButtonRestaurants)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButtonLoisirs)
                        .addGap(39, 39, 39)
                        .addComponent(jLabelLiens)
                        .addGap(8, 8, 8)
                        .addComponent(jRadioButtonAutoroutes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButtonNationals)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButtonDepartementales))
                    .addGroup(jDialogAfficherLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jDialogAfficherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelNombre))))
                .addGap(16, 16, 16))
        );

        jDialogSaut.setTitle("Afficher les voisins à 1 et 2 sauts");

        buttonGroupSaut.add(jRadioButton2Saut);
        jRadioButton2Saut.setText("2 SAUTS");
        jRadioButton2Saut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2SautActionPerformed(evt);
            }
        });

        buttonGroupSaut.add(jRadioButton1Saut);
        jRadioButton1Saut.setText("1 SAUT");
        jRadioButton1Saut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1SautActionPerformed(evt);
            }
        });

        jScrollPane3.setViewportView(jListSaut);

        jListChoixSaut.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(jListChoixSaut);

        jLabel4.setText("Resultat :");

        jTextFieldNombreSaut.setEditable(false);

        jLabel5.setText("Nombre :");

        jLabel12.setText("Visualisez les sommets voisins à 1 ou 2 sauts du sommet sélectionné :");

        javax.swing.GroupLayout jDialogSautLayout = new javax.swing.GroupLayout(jDialogSaut.getContentPane());
        jDialogSaut.getContentPane().setLayout(jDialogSautLayout);
        jDialogSautLayout.setHorizontalGroup(
            jDialogSautLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogSautLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jDialogSautLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jDialogSautLayout.createSequentialGroup()
                        .addGroup(jDialogSautLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogSautLayout.createSequentialGroup()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28))
                            .addGroup(jDialogSautLayout.createSequentialGroup()
                                .addGroup(jDialogSautLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jRadioButton2Saut, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jRadioButton1Saut, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(107, 107, 107)))
                        .addGroup(jDialogSautLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jDialogSautLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jDialogSautLayout.createSequentialGroup()
                                    .addGap(62, 62, 62)
                                    .addComponent(jLabel4))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogSautLayout.createSequentialGroup()
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(137, 137, 137)))
                            .addGroup(jDialogSautLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jDialogSautLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextFieldNombreSaut, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 2, Short.MAX_VALUE)))
                .addGap(33, 33, 33))
        );
        jDialogSautLayout.setVerticalGroup(
            jDialogSautLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogSautLayout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addGroup(jDialogSautLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialogSautLayout.createSequentialGroup()
                        .addComponent(jRadioButton1Saut)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton2Saut))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogSautLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jDialogSautLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldNombreSaut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDialogSautLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        jDialogComparer.setTitle("Comparer deux sommets");

        jListComparer2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane5.setViewportView(jListComparer2);

        jListComparer1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane6.setViewportView(jListComparer1);

        jTextFieldOuverture.setEditable(false);

        jTextFieldGastronomie.setEditable(false);

        jTextFieldCulture.setEditable(false);

        jLabel6.setText("Ouverture");

        jLabel7.setText("Culture");

        jLabel8.setText("Gastronomie");

        jLabel13.setText("Sélectionnez 2 villes puis cliquez sur comparer :");

        jComparer.setText("Comparer");
        jComparer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComparerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialogComparerLayout = new javax.swing.GroupLayout(jDialogComparer.getContentPane());
        jDialogComparer.getContentPane().setLayout(jDialogComparerLayout);
        jDialogComparerLayout.setHorizontalGroup(
            jDialogComparerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogComparerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jDialogComparerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialogComparerLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jDialogComparerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDialogComparerLayout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jDialogComparerLayout.createSequentialGroup()
                                .addGroup(jDialogComparerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldGastronomie, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldOuverture, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldCulture, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))))
                    .addGroup(jDialogComparerLayout.createSequentialGroup()
                        .addGroup(jDialogComparerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDialogComparerLayout.createSequentialGroup()
                                .addGap(247, 247, 247)
                                .addComponent(jLabel7))
                            .addGroup(jDialogComparerLayout.createSequentialGroup()
                                .addGap(236, 236, 236)
                                .addComponent(jLabel8))
                            .addGroup(jDialogComparerLayout.createSequentialGroup()
                                .addGap(223, 223, 223)
                                .addComponent(jComparer))
                            .addGroup(jDialogComparerLayout.createSequentialGroup()
                                .addGap(237, 237, 237)
                                .addComponent(jLabel6)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jDialogComparerLayout.setVerticalGroup(
            jDialogComparerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogComparerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialogComparerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jDialogComparerLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldOuverture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldGastronomie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldCulture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComparer)
                        .addGap(28, 28, 28))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                    .addComponent(jScrollPane6))
                .addContainerGap())
        );

        jDialogDistance.setTitle("Sommets à 2-distance");

        jListDistance1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane7.setViewportView(jListDistance1);

        jTextFieldReponseDistance.setEditable(false);
        jTextFieldReponseDistance.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldReponseDistance.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldReponseDistance.setBorder(null);

        jListDistance2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane8.setViewportView(jListDistance2);

        jButton1.setText("Deux Distance ?");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel14.setText("Selectionnez deux villes ");

        jLabel15.setText("pour savoir si elles sont à 2-distance");

        javax.swing.GroupLayout jDialogDistanceLayout = new javax.swing.GroupLayout(jDialogDistance.getContentPane());
        jDialogDistance.getContentPane().setLayout(jDialogDistanceLayout);
        jDialogDistanceLayout.setHorizontalGroup(
            jDialogDistanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogDistanceLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jDialogDistanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialogDistanceLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addGroup(jDialogDistanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogDistanceLayout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(70, 70, 70))
                            .addGroup(jDialogDistanceLayout.createSequentialGroup()
                                .addComponent(jTextFieldReponseDistance, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25))))
                    .addGroup(jDialogDistanceLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jDialogDistanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogDistanceLayout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(24, 24, 24))
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jDialogDistanceLayout.setVerticalGroup(
            jDialogDistanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogDistanceLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialogDistanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jDialogDistanceLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15)
                        .addGap(39, 39, 39)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldReponseDistance, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                    .addComponent(jScrollPane7))
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Application GRAMA 1.0");

        jScrollPaneMainMenu.setViewportView(jListMainSommet);

        jButtonAfficher.setText("Afficher par type");
        jButtonAfficher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAfficherActionPerformed(evt);
            }
        });

        jLabelTitrejListMenu.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelTitrejListMenu.setText("Affichage du Graphe sélectionné");

        jListMainLien.setModel(modeleLien);
        jScrollPane2.setViewportView(jListMainLien);

        jTextFieldNombreMainLien.setEditable(false);

        jTextFieldNombreMainSommet.setEditable(false);

        jLabel1.setText("Nombre :");

        jLabel2.setText("Sommets");

        jLabel3.setText("Liens");

        jButtonSaut.setText("Afficher les voisins directs");
        jButtonSaut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSautActionPerformed(evt);
            }
        });

        jButtonComparerSommet.setText("Comparer deux sommets");
        jButtonComparerSommet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonComparerSommetActionPerformed(evt);
            }
        });

        jButton2distance.setText("2-Distance");
        jButton2distance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2distanceActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel11.setText("Bienvenu sur l'application Grama 1.0.");

        jLabel9.setText("Cliquez sur une des fonctionnalités ci dessous :");

        jMenu1.setText("Fichier");

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vue/open.gif"))); // NOI18N
        jMenuItem1.setText("Ouvrir");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vue/quitter.gif"))); // NOI18N
        jMenuItem2.setText("Quitter");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("En savoir plus");

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vue/about.gif"))); // NOI18N
        jMenuItem3.setText("Aide");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonSaut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonAfficher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2distance, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonComparerSommet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(171, 171, 171)
                        .addComponent(jLabel3)
                        .addGap(82, 82, 82))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPaneMainMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabelTitrejListMenu)
                        .addGap(100, 100, 100))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldNombreMainSommet, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jTextFieldNombreMainLien, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel9))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabelTitrejListMenu))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel11)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPaneMainMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                            .addComponent(jScrollPane2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldNombreMainLien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldNombreMainSommet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonAfficher, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonSaut, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2distance, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonComparerSommet, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        jFileChooser.showOpenDialog(this);
        File selectedFile = jFileChooser.getSelectedFile();
        listeSommet = makeInit(selectedFile);
        this.fillListMainSommet();
        this.fillListMainLien();
        jTextFieldNombreMainSommet.setText(Integer.toString(modeleSommet.getSize()));
        jTextFieldNombreMainLien.setText(Integer.toString(modeleLien.getSize()));
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        if(JOptionPane.showConfirmDialog(this, "Souhaitez-vous quitter l'application ?", "Attention", JOptionPane.YES_NO_OPTION)==0) System.exit(0);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jButtonAfficherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAfficherActionPerformed
        jDialogAfficher.setVisible(true);
        jDialogAfficher.pack();
    }//GEN-LAST:event_jButtonAfficherActionPerformed

    private void jRadioButtonVillesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonVillesActionPerformed
        this.fillListVille();
        jTextFieldNombre.setText(Integer.toString(modeleType.getSize()));
    }//GEN-LAST:event_jRadioButtonVillesActionPerformed

    private void jRadioButtonRestaurantsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonRestaurantsActionPerformed
        this.fillListResto();
        jTextFieldNombre.setText(Integer.toString(modeleType.getSize()));
    }//GEN-LAST:event_jRadioButtonRestaurantsActionPerformed

    private void jRadioButtonLoisirsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonLoisirsActionPerformed
        this.fillListLoisir();
        jTextFieldNombre.setText(Integer.toString(modeleType.getSize()));
    }//GEN-LAST:event_jRadioButtonLoisirsActionPerformed

    private void jRadioButtonAutoroutesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonAutoroutesActionPerformed
        this.fillListAutoroute();
        jTextFieldNombre.setText(Integer.toString(modeleType.getSize()));
    }//GEN-LAST:event_jRadioButtonAutoroutesActionPerformed

    private void jRadioButtonNationalsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonNationalsActionPerformed
        this.fillListNationale();
        jTextFieldNombre.setText(Integer.toString(modeleType.getSize()));
    }//GEN-LAST:event_jRadioButtonNationalsActionPerformed

    private void jRadioButtonDepartementalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonDepartementalesActionPerformed
        this.fillListDepartementale();
        jTextFieldNombre.setText(Integer.toString(modeleType.getSize()));
    }//GEN-LAST:event_jRadioButtonDepartementalesActionPerformed

    private void jButtonSautActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSautActionPerformed
        jDialogSaut.setVisible(true);
        jDialogSaut.pack();
    }//GEN-LAST:event_jButtonSautActionPerformed

    private void jRadioButton2SautActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2SautActionPerformed
        this.fillList2Saut();
        jTextFieldNombreSaut.setText(Integer.toString(modeleSaut.getSize()));
    }//GEN-LAST:event_jRadioButton2SautActionPerformed

    private void jRadioButton1SautActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1SautActionPerformed
        this.fillList1Saut();
        jTextFieldNombreSaut.setText(Integer.toString(modeleSaut.getSize()));
    }//GEN-LAST:event_jRadioButton1SautActionPerformed

    private void jButtonComparerSommetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonComparerSommetActionPerformed
        jDialogComparer.setVisible(true);
        jDialogComparer.pack();
    }//GEN-LAST:event_jButtonComparerSommetActionPerformed

    private void jButton2distanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2distanceActionPerformed
        jDialogDistance.setVisible(true);
        jDialogDistance.pack();
    }//GEN-LAST:event_jButton2distanceActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.fillList2Distance();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        JOptionPane.showMessageDialog(this, "Bonjour, \nBienvenus sur l'application GRAMA. \nOuvrez un graphe (.csv) dans le menu \"Fichier\".\nUtilisez les différentes fonctionnalités pour manipuler votre graphe.\n\nVersion 1.0. 2022\nCrédits : Turc Romane, Charrier Tilio, Lageat Louaï", "Aide", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jComparerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComparerActionPerformed
        this.Comparer();
    }//GEN-LAST:event_jComparerActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ihm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ihm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ihm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ihm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ihm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupSaut;
    private javax.swing.ButtonGroup buttonGroupType;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2distance;
    private javax.swing.JButton jButtonAfficher;
    private javax.swing.JButton jButtonComparerSommet;
    private javax.swing.JButton jButtonSaut;
    private javax.swing.JButton jComparer;
    private javax.swing.JDialog jDialogAfficher;
    private javax.swing.JDialog jDialogComparer;
    private javax.swing.JDialog jDialogDistance;
    private javax.swing.JDialog jDialogSaut;
    private javax.swing.JFileChooser jFileChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelLiens;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelSommet;
    private javax.swing.JLabel jLabelTitrejListMenu;
    private javax.swing.JList<String> jList2;
    private javax.swing.JList<String> jListChoixSaut;
    private javax.swing.JList<String> jListComparer1;
    private javax.swing.JList<String> jListComparer2;
    private javax.swing.JList<String> jListDistance1;
    private javax.swing.JList<String> jListDistance2;
    private javax.swing.JList<String> jListMainLien;
    private javax.swing.JList<String> jListMainSommet;
    private javax.swing.JList<String> jListSaut;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JRadioButton jRadioButton1Saut;
    private javax.swing.JRadioButton jRadioButton2Saut;
    private javax.swing.JRadioButton jRadioButtonAutoroutes;
    private javax.swing.JRadioButton jRadioButtonDepartementales;
    private javax.swing.JRadioButton jRadioButtonLoisirs;
    private javax.swing.JRadioButton jRadioButtonNationals;
    private javax.swing.JRadioButton jRadioButtonRestaurants;
    private javax.swing.JRadioButton jRadioButtonVilles;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPaneMainMenu;
    private javax.swing.JTextField jTextFieldCulture;
    private javax.swing.JTextField jTextFieldGastronomie;
    private javax.swing.JTextField jTextFieldNombre;
    private javax.swing.JTextField jTextFieldNombreMainLien;
    private javax.swing.JTextField jTextFieldNombreMainSommet;
    private javax.swing.JTextField jTextFieldNombreSaut;
    private javax.swing.JTextField jTextFieldOuverture;
    private javax.swing.JTextField jTextFieldReponseDistance;
    // End of variables declaration//GEN-END:variables
}
