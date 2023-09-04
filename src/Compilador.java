
import com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme;
import compilerTools.CodeBlock;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import compilerTools.Directory;
import compilerTools.ErrorLSSL;
import compilerTools.Functions;
import compilerTools.Grammar;
import compilerTools.Production;
import compilerTools.TextColor;
import compilerTools.Token;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import javax.management.StringValueExp;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Herrera
 */
public class Compilador extends javax.swing.JFrame {

    private String title;
    private Directory directorio;
    private ArrayList<Token> tokens;
    private ArrayList<ErrorLSSL> errors;
    private ArrayList<TextColor> textsColor;
    private Timer timerKeyReleased;
    private ArrayList<Production> identProd;
    private ArrayList<Production> funAritProd;
    private ArrayList<Production> funComProd;
    private ArrayList<Production> cicProd;
    private ArrayList<Production> impProd;
    private HashMap<String, String> identificadores;
    private boolean codeHasBeenCompiled = false;

    /**
     * Creates new form Compilador
     */
    static class CustomTabAction extends AbstractAction {

        private static final String TAB = "    "; // Cuatro espacios por defecto

        @Override
        public void actionPerformed(ActionEvent e) {
            JTextComponent textComponent = (JTextComponent) e.getSource();
            Document doc = textComponent.getDocument();
            int caretPosition = textComponent.getCaretPosition();

            try {
                doc.insertString(caretPosition, TAB, null);
            } catch (BadLocationException ex) {
                ex.printStackTrace();
            }
        }
    }

    public Compilador() {
        initComponents();
        init();
    }

    private void init() {
        title = "Compiler";
        setLocationRelativeTo(null);
        setTitle(title);
        directorio = new Directory(this, jtpCode, title, ".comp");
        addWindowListener(new WindowAdapter() {// Cuando presiona la "X" de la esquina superior derecha
            @Override
            public void windowClosing(WindowEvent e) {
                directorio.Exit();
                System.exit(0);
            }
        });
        Functions.setLineNumberOnJTextComponent(jtpCode); //Pone los numeros de linea
        timerKeyReleased = new Timer((int) (1000 * 0.3), (ActionEvent e) -> {
            timerKeyReleased.stop();

            int posicion = jtpCode.getCaretPosition();
            jtpCode.setText(jtpCode.getText().replaceAll("[\r]+", ""));
            jtpCode.setCaretPosition(posicion);

            colorAnalysis();

        });

        // Configura el comportamiento personalizado de la tecla Tab
        InputMap inputMap = jtpCode.getInputMap(JComponent.WHEN_FOCUSED);
        ActionMap actionMap = jtpCode.getActionMap();

        KeyStroke tabKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0);
        inputMap.put(tabKeyStroke, "customTabAction");
        actionMap.put("customTabAction", new CustomTabAction());

        Functions.insertAsteriskInName(this, jtpCode, () -> {
            timerKeyReleased.restart();
        });
        tokens = new ArrayList<>();
        errors = new ArrayList<>();
        textsColor = new ArrayList<>();
        identProd = new ArrayList<>();
        funAritProd = new ArrayList<>();
        identificadores = new HashMap<>();
        Functions.setAutocompleterJTextComponent(new String[]{}, jtpCode, () -> {
            timerKeyReleased.restart();
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rootPanel = new javax.swing.JPanel();
        buttonsFilePanel = new javax.swing.JPanel();
        btnAbrir = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnGuardarC = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtpCode = new javax.swing.JTextPane();
        panelButtonCompilerExecute = new javax.swing.JPanel();
        btnCompilar = new javax.swing.JButton();
        btnEjecutar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtaOutputConsole = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTokens = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        btnAbrir.setText("Abrir");
        btnAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirActionPerformed(evt);
            }
        });

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnGuardarC.setText("Guardar como");
        btnGuardarC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout buttonsFilePanelLayout = new javax.swing.GroupLayout(buttonsFilePanel);
        buttonsFilePanel.setLayout(buttonsFilePanelLayout);
        buttonsFilePanelLayout.setHorizontalGroup(
            buttonsFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsFilePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAbrir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardarC)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        buttonsFilePanelLayout.setVerticalGroup(
            buttonsFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsFilePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(buttonsFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAbrir)
                    .addComponent(btnNuevo)
                    .addComponent(btnGuardar)
                    .addComponent(btnGuardarC))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jtpCode.setFont(new java.awt.Font("Consolas", 0, 15)); // NOI18N
        jtpCode.setForeground(new java.awt.Color(204, 204, 204));
        jtpCode.setCaretColor(new java.awt.Color(204, 204, 204));
        jtpCode.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        jScrollPane1.setViewportView(jtpCode);

        btnCompilar.setText("Compilar");
        btnCompilar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompilarActionPerformed(evt);
            }
        });

        btnEjecutar.setText("Ejecutar");
        btnEjecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEjecutarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelButtonCompilerExecuteLayout = new javax.swing.GroupLayout(panelButtonCompilerExecute);
        panelButtonCompilerExecute.setLayout(panelButtonCompilerExecuteLayout);
        panelButtonCompilerExecuteLayout.setHorizontalGroup(
            panelButtonCompilerExecuteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelButtonCompilerExecuteLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCompilar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEjecutar)
                .addContainerGap())
        );
        panelButtonCompilerExecuteLayout.setVerticalGroup(
            panelButtonCompilerExecuteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelButtonCompilerExecuteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelButtonCompilerExecuteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCompilar)
                    .addComponent(btnEjecutar))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jtaOutputConsole.setEditable(false);
        jtaOutputConsole.setColumns(20);
        jtaOutputConsole.setRows(5);
        jScrollPane2.setViewportView(jtaOutputConsole);

        tblTokens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Componente léxico", "Lexema", "[Línea, Columna]"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTokens.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tblTokens);

        javax.swing.GroupLayout rootPanelLayout = new javax.swing.GroupLayout(rootPanel);
        rootPanel.setLayout(rootPanelLayout);
        rootPanelLayout.setHorizontalGroup(
            rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rootPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, rootPanelLayout.createSequentialGroup()
                        .addComponent(buttonsFilePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelButtonCompilerExecute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 693, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        rootPanelLayout.setVerticalGroup(
            rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rootPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonsFilePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelButtonCompilerExecute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(rootPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        getContentPane().add(rootPanel);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        directorio.New();
        clearFields();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirActionPerformed
        if (directorio.Open()) {
            colorAnalysis();
            clearFields();
        }
    }//GEN-LAST:event_btnAbrirActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (directorio.Save()) {
            clearFields();
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnGuardarCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCActionPerformed
        if (directorio.SaveAs()) {
            clearFields();
        }
    }//GEN-LAST:event_btnGuardarCActionPerformed

    private void btnCompilarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompilarActionPerformed
        if (getTitle().contains("*") || getTitle().equals(title)) {
            if (directorio.Save()) {
                compile();
            }
        } else {
            compile();
        }
    }//GEN-LAST:event_btnCompilarActionPerformed

    private void btnEjecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEjecutarActionPerformed
        btnCompilar.doClick();
        if (codeHasBeenCompiled) {
            if (!errors.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No se puede ejecutar el código ya que se encontró uno o más errores",
                        "Error en la compilación", JOptionPane.ERROR_MESSAGE);
            } else {
                CodeBlock codeBlock = Functions.splitCodeInCodeBlocks(tokens, "{", "}", ";");
                System.out.println(codeBlock);
                ArrayList<String> blocksOfCode = codeBlock.getBlocksOfCodeInOrderOfExec();
                System.out.println(blocksOfCode);

            }
        }
    }//GEN-LAST:event_btnEjecutarActionPerformed

    private void compile() {
        clearFields();
        lexicalAnalysis();
        fillTableTokens();
        syntacticAnalysis();
        semanticAnalysis();
        printConsole();
        codeHasBeenCompiled = true;
    }

    private void lexicalAnalysis() {
        // Extraer tokens
        Lexer lexer;
        try {
            File codigo = new File("code.encrypter");
            FileOutputStream output = new FileOutputStream(codigo);
            byte[] bytesText = jtpCode.getText().getBytes();
            output.write(bytesText);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(new FileInputStream(codigo), "UTF8"));
            lexer = new Lexer(entrada);
            while (true) {
                Token token = lexer.yylex();
                if (token == null) {
                    break;
                }
                tokens.add(token);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("El archivo no pudo ser encontrado... " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error al escribir en el archivo... " + ex.getMessage());
        }
    }

    private void syntacticAnalysis() {
        Grammar gramatica = new Grammar(tokens, errors);

        identProd = new ArrayList<>();
        funAritProd = new ArrayList<>();
        funComProd = new ArrayList<>();
        impProd = new ArrayList<>();
        cicProd = new ArrayList<>();
        identificadores = new HashMap<>();


        /*Eliminacion de errores*/
        gramatica.delete(new String[]{"ERROR", "ERROR2"}, 1);

        /* Agrupacion de valores*/
        gramatica.group("VALOR", "(NUMERO | BOOLEANO | CADENA)", true);

        // operaciones comparativas
        gramatica.group("OPERACION_COMPARATIVA", "FUNCION_COMPARACION PARENTESIS_A (VALOR | IDENTIFICADOR) COMA (VALOR | IDENTIFICADOR) PARENTESIS_C", true, funComProd);

        // operaciones matematicas
        gramatica.group("OPERACION_ARITMETICA", "FUNCION_ARITMETICA PARENTESIS_A (VALOR | IDENTIFICADOR) COMA (VALOR | IDENTIFICADOR) PARENTESIS_C", true, funAritProd);

        // errores en funciones matematicas
        gramatica.group("OPERACION_ARITMETICA", "FUNCION_ARITMETICA PARENTESIS_A (VALOR | IDENTIFICADOR) COMA (VALOR | IDENTIFICADOR)", true, 20, "error sintactico: falta cerrar un parentesis [#, %]");
        gramatica.group("OPERACION_ARITMETICA", "FUNCION_ARITMETICA  (VALOR | IDENTIFICADOR) COMA (VALOR | IDENTIFICADOR) PARENTESIS_C", true, 20, "error sintactico: falta abrir un parentesis [#, %]");
        gramatica.group("OPERACION_ARITMETICA", "FUNCION_ARITMETICA", true, 20, "error sintactico: declaracion de la funcion errornea [#, %]");
        gramatica.group("OPERACION_ARITMETICA", "FUNCION_ARITMETICA PARENTESIS_A  COMA (VALOR | IDENTIFICADOR) PARENTESIS_C", true, 20, "error sintactico: falta un valor [#, %]");
        gramatica.group("OPERACION_ARITMETICA", "FUNCION_ARITMETICA PARENTESIS_A (VALOR | IDENTIFICADOR) COMA  PARENTESIS_C", true, 20, "error sintactico: falta cerrar un valor [#, %]");
        gramatica.group("OPERACION_ARITMETICA", "FUNCION_ARITMETICA PARENTESIS_A PARENTESIS_C", true, 20, "error sintactico: falta agregar dos valores [#, %]");
        gramatica.group("OPERACION_ARITMETICA", "FUNCION_ARITMETICA PARENTESIS_A COMA PARENTESIS_C", true, 20, "error sintactico: falta agregar dos valores separados por coma [#, %]");

        // operacion aritmetica completa
        /* Declaracion de variables */
        gramatica.group("VARIABLE", "TIPO_DATO IDENTIFICADOR ASIGNACION (VALOR | IDENTIFICADOR | OPERACION_ARITMETICA | OPERACION_COMPARATIVA)", true, identProd);

        /* errores */
        gramatica.group("VARIABLE", "TIPO_DATO ASIGNACION (VALOR | IDENTIFICADOR)", true, 2, "error sintactico: falta el identificador en la variable [#, %]");
        gramatica.group("VARIABLE", "TIPO_DATO IDENTIFICADOR (VALOR | IDENTIFICADOR)", true, 3, "error sintactico: falta el valor en la declaracion [#, %]");
        gramatica.group("VARIABLE", "IDENTIFICADOR ASIGNACION (VALOR | IDENTIFICADOR)", true, 4, "error sintactico: falta el tipo de dato en la declaracion [#, %]");
        gramatica.group("VARIABLE", "TIPO_DATO IDENTIFICADOR", true, 5, "error sintactico: falta inicializar la variable con algun valor [#, %]");

        /* Agrupar identificadores */
        gramatica.group("VALOR", "IDENTIFICADOR", true);

        // llamar funciones
        gramatica.group("LLAMAR_FUNCION", "NOMBRE_FUNCION PARENTESIS_A PARENTESIS_C", true);

        // verificacion del punto y coma al final de una sentencia
        // identificadores de variables
        gramatica.group("VARIABLE_PC", "VARIABLE FIN_SENTENCIA", true);
        gramatica.group("VARIABLE_PC", "VARIABLE", 6, "error sintactico: falta el punto y coma al final de la sentencia [#, %]");

        // funciones
        gramatica.group("LLAMAR_FUNCION_COMP_PC", "LLAMAR_FUNCION FIN_SENTENCIA");
        gramatica.group("LLAMAR_FUNCION_COMP_PC", "LLAMAR_FUNCION", 7, "error sintactico: falta el punto y coma al final de la declaracion de una funcion [#, %]");

        // imprimir
        gramatica.group("IMPRIMIR_VALOR", "IMPRIMIR PARENTESIS_A (VALOR | IDENTIFICADOR) PARENTESIS_C FIN_SENTENCIA", true, impProd);

        // funciones aritmeticas
        gramatica.group("OPERACION_ARITMETICA_COM_PC", "OPERACION_ARITMETICA FIN_SENTENCIA", true);

        // funciones aritmeticas incompletas
        gramatica.group("OPERACION_ARITMETICA_COM_PC", "OPERACION_ARITMETICA", true, 21, "error sintactico: falta finalizar sentencia con punto y coma [#, %]");

        // funciones comparativas completas
        gramatica.group("OPERACION_COMPARATIVA_COM_PC", "OPERACION_COMPARATIVA FIN_SENTENCIA", true);

        // funciones comparativas incompletas
        gramatica.group("OPERACION_COMPARATIVA_COM_PC", "OPERACION_COMPARATIVA", true, 21, "error sintactico: falta finalizar sentencia con punto y coma [#, %]");

        // errores en imprimir
        gramatica.group("IMPRIMIR_VALOR", "IMPRIMIR PARENTESIS_A VALOR PARENTESIS_C", 8, "error sintactico: falta un punto y coma [#, %]");
        gramatica.group("IMPRIMIR_VALOR", "IMPRIMIR PARENTESIS_A VALOR", 9, "error sintactico: falta cerrar el parentesis [#, %]");
        gramatica.group("IMPRIMIR_VALOR", "IMPRIMIR", 10, "error sintactico: falta el valor a imprimir cerrado entre parentesis [#, %]");

        // sentencias
        gramatica.group("SENTENCIAS", "(VARIABLE_PC | FUNCION_COMP_PC | IMPRIMIR_VALOR | OPERACION_ARITMETICA_COM_PC | OPERACION_COMPARATIVA_COM_PC)");

        // agrupacion de estructuras de control        
        gramatica.group("EST_CONTROL_COMP", "CICLO PARENTESIS_A (VALOR | IDENTIFICADOR) PARENTESIS_C", true, cicProd);

        // errores en estructuras de control
        gramatica.group("EST_CONTROL_COMP", "CICLO PARENTESIS_A VALOR", true, 11, "error sintactico: falta cerrar un parentesis [#, %]");
        gramatica.group("EST_CONTROL_COMP", "CICLO PARENTESIS_A PARENTESIS_C", true, 12, "error sintactico: falta agregar un valor dentro de la estructura de control [#, %]");
        gramatica.group("EST_CONTROL_COMP", "CICLO VALOR PARENTESIS_C", true, 13, "error sintactico: falta abrir un aprentesis [#, %]");

        // estructuras de control anidadas
        gramatica.loopForFunExecUntilChangeNotDetected(() -> {
            gramatica.group("EST_CONTROL_COMP_LASLC", "EST_CONTROL_COMP LLAVE_A (SENTENCIAS) ? LLAVE_C", true);
            gramatica.group("SENTENCIAS", "(SENTENCIAS | EST_CONTROL_COMP_LASLC)+");
        });

        // estructuras de funcion incompletas
        gramatica.loopForFunExecUntilChangeNotDetected(() -> {
            gramatica.group("EST_CONTROL_COMP_LASLC", "EST_CONTROL_COMP (SENTENCIAS) ? LLAVE_C", true, 14, "error sintactico falta la llave que abre en la estructura de control");
            gramatica.group("EST_CONTROL_COMP_LASLC", "EST_CONTROL_COMP LLAVE_A (SENTENCIAS) ? ", true, 15, "error sintactico falta la llave que cierra en la estructura de control");
            gramatica.group("EST_CONTROL_COMP_LASLC", "EST_CONTROL_COMP (SENTENCIAS) ? ", true, 15, "error sintactico faltan las llaves en la estructura de control");
            gramatica.group("SENTENCIAS", "(SENTENCIAS | EST_CONTROL_COMP_LASLC)+");
        });

        // declaracion de funcion
        gramatica.group("DECLARACION_FUNCION", "FUNCION IDENTIFICADOR_FUNCION PARENTESIS_A PARENTESIS_C LLAVE_A (SENTENCIAS)? LLAVE_C");

        // Eliminacion de tipos de dato y operadores de asignacion
        gramatica.delete("TIPO_DATO", 16, "Error sintactico: el tipo de dato no esta en una declaracion [#, %]");
        gramatica.delete("ASIGNACION", 17, "Error sintactico: el operador de asignacion no esta en una declaracion [#, %]");

        // eliminar estructuras de control
        gramatica.delete("CICLO", 16, "Error sintactico: el CICLO no esta en una declaracion [#, %]");
        gramatica.delete("SI", 16, "Error sintactico: el SIC no esta en una declaracion [#, %]");
        gramatica.delete("ENTONCES", 16, "Error sintactico: el NOC no esta en una declaracion [#, %]");

        // eliminar funciones aritmeticas
        gramatica.delete("FUNCION_ARITMETICA", 16, "Error sintactico: el NOC no esta en una declaracion [#, %]");

        // Eliminacion de operadores logicos
        gramatica.delete("OPERADOR_LOGICO", 18, "error sintactico: el operador logino no esta en una operacion logica [#, %]");
        gramatica.delete("OPERADOR_COMPARACION", 19, "error sintactico: el operador de comparacion no esta en una operacion logica [#, %]");

        // eliminacion de parentesis
        gramatica.delete(new String[]{"PARENTESIS_A", "PARENTESIS_C"}, 20, "error sintactico: el parentesis [] no esta contenido en una agrupacion [#, %]");

        // eliminacion del punto y coma
        gramatica.delete("FIN_SENTENCIA", 21, "error sintactico: el punto y coma no esta al final de ninguna sentencia [#, %]");

        // eliminacion del punto y coma
        gramatica.delete("COMA", 21, "error sintactico: la coma no esta al final de ninguna sentencia [#, %]");

        // eliminar las llaves que abren y cierran
        gramatica.delete(new String[]{"LLAVE_A", "LLAVE_C"}, 22, "error sintactico: la llave [] no esta contenido en una agrupacion [#, %]");

        /* Mostrar gramáticas */
        gramatica.show();
    }

    private void semanticAnalysis() {

        HashMap<String, String> identDataType = new HashMap<>();
        identDataType.put("num", "NUMERO");
        identDataType.put("bol", "BOOLEANO");
        identDataType.put("cad", "CADENA");

        // Mapeo de tipos de datos a expresiones regulares
        HashMap<String, String> tipoDatoExpresionRegular = new HashMap<>();
        tipoDatoExpresionRegular.put("num", "-?\\d+(\\.\\d+)?");
        tipoDatoExpresionRegular.put("bol", "ver|fal");
        tipoDatoExpresionRegular.put("cad", "'.*'");

        //** ------------------------------------------------------
        // Correcta asignacion de variables y funciones aritmeticas
        //** ------------------------------------------------------     
        for (Production id : identProd) {
            // solo para pruebas
            System.out.println(id.lexemeRank(0, -1)); // codigo del lenguaje
            System.out.println(id.lexicalCompRank(0, -1)); // NUMERO BOOLEANO IDENTIFICADOR
            System.out.println("*");
            //--------------------------

            boolean isIde = false;

            if (id.lexicalCompRank(-1).equals("IDENTIFICADOR")) {
                isIde = true;
                if (!identificadores.containsKey(id.lexemeRank(-1))) {
                    errors.add(new ErrorLSSL(1, "error semantico: la variable " + id.lexemeRank(-1) + " no ha sido declarada 2 [#, %]", id, true));
                } else {
                    String tipoDato = id.lexemeRank(0);
                    String expresionRegular = tipoDatoExpresionRegular.get(tipoDato);
                    String valor = identificadores.get(id.lexemeRank(-1));

                    if (expresionRegular != null && valor != null && !valor.matches(expresionRegular)) {
                        errors.add(new ErrorLSSL(1, "Error semántico: valor no compatible con el tipo de dato " + tipoDato + " [" + expresionRegular + "]", id, true));
                    } else {
                        System.out.println(valor);
                        identificadores.put(id.lexemeRank(1), valor);
                    }
                }
            }

            boolean isFun = false;

            if (id.lexicalCompRank(3).equals("FUNCION_ARITMETICA") && !isIde) {
                isFun = true;
                if (id.lexemeRank(0).equals("num")) {
                    String val1 = "";
                    String val2 = "";

                    if (id.lexicalCompRank(5).equals("NUMERO")) {
                        val1 = id.lexemeRank(5);
                    } else if (id.lexicalCompRank(5).equals("IDENTIFICADOR")) {
                        if (!verificarIdentificadorNumero(id, 5)) {
                            errors.add(new ErrorLSSL(1, "Error semántico: se esperaban tipos de datos de tipo NUMERO [#, %]", id, true));
                        } else {
                            val1 = identificadores.get(id.lexemeRank(5));
                        }

                    }

                    if (id.lexicalCompRank(7).equals("NUMERO")) {
                        val2 = id.lexemeRank(7);
                    } else if (id.lexicalCompRank(7).equals("IDENTIFICADOR")) {
                        if (!verificarIdentificadorNumero(id, 7)) {
                            errors.add(new ErrorLSSL(1, "Error semántico: se esperaban tipos de datos de tipo NUMERO [#, %]", id, true));
                        } else {
                            val2 = identificadores.get(id.lexemeRank(7));
                        }
                    }

                    if (!val1.isEmpty() && !val2.isEmpty()) {
                        double numero1 = Double.parseDouble(val1);
                        double numero2 = Double.parseDouble(val2);
                        double resultado = 0.0; // Inicializar el resultado

                        // Realizar las operaciones según el operador
                        if (id.lexemeRank(3).equals("sumar")) {
                            resultado = numero1 + numero2;
                        } else if (id.lexemeRank(3).equals("restar")) {
                            resultado = numero1 - numero2;
                        } else if (id.lexemeRank(3).equals("multiplicar")) {
                            resultado = numero1 * numero2;
                        } else if (id.lexemeRank(3).equals("dividir")) {
                            if (numero2 != 0.0) {
                                resultado = numero1 / numero2;
                            } else {
                                errors.add(new ErrorLSSL(1, "Error semántico: división entre cero [#, %]", id, true));
                            }
                        }

                        // Guardar el resultado en el HashMap identificadores
                        identificadores.put(id.lexemeRank(1), String.valueOf(resultado));
                    }
                } else {
                    errors.add(new ErrorLSSL(1, "Error semántico: el tipo de dato no es compatible [#, %]", id, true));
                }

            }

            boolean isFunCom = false;

            if (id.lexicalCompRank(3).equals("FUNCION_COMPARACION") && !isIde) {
                isFunCom = true;
                if (id.lexemeRank(0).equals("bol")) {
                    String val1 = "";
                    String val2 = "";

                    if (id.lexicalCompRank(5).equals("NUMERO")) {
                        val1 = id.lexemeRank(5);
                    } else if (id.lexicalCompRank(5).equals("IDENTIFICADOR")) {
                        if (!verificarIdentificadorNumero(id, 5)) {
                            errors.add(new ErrorLSSL(1, "Error semántico: se esperaban tipos de datos de tipo NUMERO [#, %]", id, true));
                        } else {
                            val1 = identificadores.get(id.lexemeRank(5));
                        }

                    }

                    if (id.lexicalCompRank(7).equals("NUMERO")) {
                        val2 = id.lexemeRank(7);
                    } else if (id.lexicalCompRank(7).equals("IDENTIFICADOR")) {
                        if (!verificarIdentificadorNumero(id, 7)) {
                            errors.add(new ErrorLSSL(1, "Error semántico: se esperaban tipos de datos de tipo NUMERO [#, %]", id, true));
                        } else {
                            val2 = identificadores.get(id.lexemeRank(7));
                        }
                    }

                    if (!val1.isEmpty() && !val2.isEmpty()) {
                        double numero1 = Double.parseDouble(val1);
                        double numero2 = Double.parseDouble(val2);
                        String resultado = ""; // Inicializar el resultado

                        // Realizar las operaciones según el operador
                        if (id.lexemeRank(3).equals("menor")) {
                            resultado = (numero1 < numero2) ? "ver" : "fal";
                        } else if (id.lexemeRank(3).equals("mayor")) {
                            resultado = (numero1 > numero2) ? "ver" : "fal";
                        } else if (id.lexemeRank(3).equals("igual")) {
                            resultado = (numero1 == numero2) ? "ver" : "fal";
                        }
                        
                        

                        // Guardar el resultado en el HashMap identificadores
                        identificadores.put(id.lexemeRank(1), resultado);
                    }
                } else {
                    errors.add(new ErrorLSSL(1, "Error semántico: el tipo de dato no es compatible [#, %]", id, true));
                }

            }

            if (!identDataType.get(id.lexemeRank(0)).equals(id.lexicalCompRank(-1)) && !isIde && !isFun && !isFunCom) {
                errors.add(new ErrorLSSL(1, "error semantico: valor no compatible con el tipo de dato [#, %]", id, true));
            } else if (!isIde && !isFun && !isFunCom) {
                identificadores.put(id.lexemeRank(1), id.lexemeRank(-1));
            }

        }

        //** -----------------------------------------------------------
        // Verificar si existen las variables para funciones aritmeticas
        //** -----------------------------------------------------------
        for (Production funArit : funAritProd) {
            System.out.println(funArit.lexemeRank(0, -1)); // Código del lenguaje
            System.out.println(funArit.lexicalCompRank(0, -1)); // Palabras como NUMERO o BOOLEANO

            boolean param1 = verificarIdentificadorNumero(funArit, 2);
            boolean param2 = verificarIdentificadorNumero(funArit, 4);

            if (!((funArit.lexicalCompRank(2).equals("NUMERO") || param1) && (funArit.lexicalCompRank(4).equals("NUMERO") || param2))) {
                errors.add(new ErrorLSSL(1, "Error semántico: se esperaban tipos de datos de tipo NUMERO [#, %]", funArit, true));
            }
        }

        //** ------------------------------------------------------------
        // Verificar si existen las variables para funciones comparativas
        //** ------------------------------------------------------------
        System.out.println("produccion comparativa");
        for (Production funCom : funComProd) {
            System.out.println(funCom.lexemeRank(0, -1)); // Código del lenguaje
            System.out.println(funCom.lexicalCompRank(0, -1)); // Palabras como NUMERO o BOOLEANO

            boolean param1 = verificarIdentificadorNumero(funCom, 2);
            boolean param2 = verificarIdentificadorNumero(funCom, 4);

            if (!((funCom.lexicalCompRank(2).equals("NUMERO") || param1) && (funCom.lexicalCompRank(4).equals("NUMERO") || param2))) {
                errors.add(new ErrorLSSL(1, "Error semántico: se esperaban tipos de datos de tipo NUMERO [#, %]", funCom, true));
            }
        }

        //** ----------------------------------------------
        // Verificar si la variable existe para impreciones
        //** ----------------------------------------------
        for (Production imp : impProd) {
            System.out.println(imp.lexemeRank(0, -1)); // Código del lenguaje
            System.out.println(imp.lexicalCompRank(0, -1)); // Palabras como NUMERO o BOOLEANO

            if (imp.lexicalCompRank(2).equals("IDENTIFICADOR")) {
                String variableName = imp.lexemeRank(2);
                verificarExistenciaVariable(variableName, imp);
            }

        }

        //** ---------------------------------------------
        // Verificar si la variable existe para los ciclos
        //** ---------------------------------------------
        for (Production cic : cicProd) {
            System.out.println(cic.lexemeRank(0, -1)); // Código del lenguaje
            System.out.println(cic.lexicalCompRank(0, -1)); // Palabras como NUMERO o BOOLEANO

            if (!cic.lexicalCompRank(2).equals("NUMERO") && !cic.lexicalCompRank(2).equals("IDENTIFICADOR")) {
                errors.add(new ErrorLSSL(1, "Error semántico: se esperaban tipos de datos de tipo NUMERO [#, %]", cic, true));
            } else if (cic.lexicalCompRank(2).equals("IDENTIFICADOR")) {
                String variableName = cic.lexemeRank(2);
                if (verificarExistenciaVariable(variableName, cic)) {
                    if (!identificadores.get(cic.lexemeRank(2)).matches(tipoDatoExpresionRegular.get("num"))) {
                        errors.add(new ErrorLSSL(1, "Error semántico: se esperaban tipos de datos de tipo NUMERO [#, %]", cic, true));
                    }
                }
            }

        }

        // verificar que si se guarden los nombres de variables con su valor
        for (String key : identificadores.keySet()) {
            String value = identificadores.get(key);
            System.out.println("nombre variable: " + key + ", valor: " + value);

        }
    }

    private boolean verificarExistenciaVariable(String variableName, Production funArit) {
        if (!identificadores.containsKey(variableName)) {
            errors.add(new ErrorLSSL(1, "Error semántico: variable " + variableName + " no ha sido declarada", funArit, true));
            return false;
        }
        return false;
    }

    private boolean verificarIdentificadorNumero(Production funArit, int index) {
        if (funArit.lexicalCompRank(index).equals("IDENTIFICADOR")) {
            String variableName = funArit.lexemeRank(index);

            if (!identificadores.containsKey(variableName)) {
                errors.add(new ErrorLSSL(1, "Error semántico: variable " + variableName + " no ha sido declarada", funArit, true));
                return false;
            } else {
                return identificadores.get(variableName).matches("-?\\d+(\\.\\d+)?");
            }
        }
        return false;
    }

    private boolean verificarIdentificadorBooleano(Production funArit, int index) {
        if (funArit.lexicalCompRank(index).equals("IDENTIFICADOR")) {
            String variableName = funArit.lexemeRank(index);

            if (!identificadores.containsKey(variableName)) {
                errors.add(new ErrorLSSL(1, "Error semántico: variable " + variableName + " no ha sido declarada", funArit, true));
                return false;
            } else {
                return identificadores.get(variableName).matches("ver|fal");
            }
        }
        return true;
    }

    private void colorAnalysis() {
        /* Limpiar el arreglo de colores */
        textsColor.clear();
        /* Extraer rangos de colores */
        LexerColor lexerColor;
        try {
            File codigo = new File("color.encrypter");
            FileOutputStream output = new FileOutputStream(codigo);
            byte[] bytesText = jtpCode.getText().getBytes();
            output.write(bytesText);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(new FileInputStream(codigo), "UTF8"));
            lexerColor = new LexerColor(entrada);
            while (true) {
                TextColor textColor = lexerColor.yylex();
                if (textColor == null) {
                    break;
                }
                textsColor.add(textColor);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("El archivo no pudo ser encontrado... " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error al escribir en el archivo... " + ex.getMessage());
        }
        Functions.colorTextPane(textsColor, jtpCode, new Color(220, 220, 220));
    }

    private void fillTableTokens() {
        tokens.forEach(token -> {
            Object[] data = new Object[]{token.getLexicalComp(), token.getLexeme(), "[" + token.getLine() + ", " + token.getColumn() + "]"};
            Functions.addRowDataInTable(tblTokens, data);
        });
    }

    private void printConsole() {
        int sizeErrors = errors.size();
        if (sizeErrors > 0) {
            Functions.sortErrorsByLineAndColumn(errors);
            String strErrors = "\n";
            for (ErrorLSSL error : errors) {
                String strError = String.valueOf(error);
                strErrors += strError + "\n";
            }
            jtaOutputConsole.setText("Compilación terminada...\n" + strErrors + "\nLa compilación terminó con errores...");
        } else {
            jtaOutputConsole.setText("Compilación terminada...");
        }
        jtaOutputConsole.setCaretPosition(0);
    }

    private void clearFields() {
        Functions.clearDataInTable(tblTokens);
        jtaOutputConsole.setText("");
        tokens.clear();
        errors.clear();
        identProd.clear();
        identificadores.clear();
        codeHasBeenCompiled = false;
    }

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
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatDarkPurpleIJTheme());
            } catch (UnsupportedLookAndFeelException ex) {
                System.out.println("LookAndFeel no soportado: " + ex);
            }
            new Compilador().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrir;
    private javax.swing.JButton btnCompilar;
    private javax.swing.JButton btnEjecutar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardarC;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JPanel buttonsFilePanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jtaOutputConsole;
    private javax.swing.JTextPane jtpCode;
    private javax.swing.JPanel panelButtonCompilerExecute;
    private javax.swing.JPanel rootPanel;
    private javax.swing.JTable tblTokens;
    // End of variables declaration//GEN-END:variables
}
