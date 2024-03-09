/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.sun.glass.events.KeyEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.ClienteVO;
import servicos.ClienteServicos;
import servicos.ServicosFactory;

/**
 *
 * @author 181700028
 */
public class GUIManutencaoCliente extends javax.swing.JInternalFrame {

    /**
     * Creates new form GUIManutencaoCliente
     */
    DefaultTableModel dtm = new DefaultTableModel(
            new Object[][]{},
            new Object[]{"Código","Nome","CPF","Genero","Rua","Numero","Complemento","Status","Ultima Atualização"}
    );
    public GUIManutencaoCliente() {
        initComponents();
        preencherTabela();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    
    private void preencherTabela(){
        
        try {
            //Buscando objeto na ClienteServicos
            ClienteServicos cs = ServicosFactory.getClienteServicos();
            
            //Criando um ArrayList<ProdutoVO> vazio para receber o arrayList com os dados da ClienteServicos
            ArrayList<ClienteVO> prod = new ArrayList<>();
            
            //Recebendo o ArrayList cheio no cliente
            prod = cs.buscarCliente(); 
            
            for(int i = 0; i < prod.size(); i++){
                dtm.addRow(new String[] {
                    String.valueOf(prod.get(i).getIdCliente()),
                    String.valueOf(prod.get(i).getNome()),
                    String.valueOf(prod.get(i).getCpf()),
                    String.valueOf(prod.get(i).getGenero()),
                    String.valueOf(prod.get(i).getRua()),
                    String.valueOf(prod.get(i).getNumero()),
                    String.valueOf(prod.get(i).getComplemento()),
                    String.valueOf(prod.get(i).getStatus()),
                });
            }//fim do laço for
            
            //Adicionando modelo de tabela com os dados na tabela jtCliente
            jtCliente.setModel(dtm);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Erro! GUIManutencaoCliente.preencherTabela"+ e.getMessage());
        }//fim do catch
        
    }//fim preencherTabela
    
    private void limparTabela(){
        
        dtm.setNumRows(0);
        
    }//fim limparTabela
    
    private void filtrar(){
        
        try {
            if(jtfPesquisaPro.getText().isEmpty()){
                preencherTabela();
            }else{
                ClienteServicos cs = ServicosFactory.getClienteServicos();
                String pesquisa = (String) jcbPesquisaPro.getSelectedItem();
                String query;
                
                if(pesquisa.equals("Código")){
                    query = "where idcliente = " + jtfPesquisaPro.getText();//para numero só where
                }else if(pesquisa.equals("Nome")){
                    query = "where nome like '%" + jtfPesquisaPro.getText() + "%' "; //para string se usa like '%palavra%'
                }else if(pesquisa.equals("CPF")){
                    query = "where cpf = '%"+ jtfPesquisaPro.getText() + "%' ";
                }else if(pesquisa.equals("Genero")){
                    query = "where genero '%"+ jtfPesquisaPro.getText() + "%' ";
                }else if(pesquisa.equals("Rua")){
                    query = "where rua ="+ jtfPesquisaPro.getText() + "%' ";
                }else if(pesquisa.equals("Numero")){
                    query = "where numero '%"+ jtfPesquisaPro.getText() + "%' ";
                }else if(pesquisa.equals("Complemento")){
                    query = "where complemento '%"+ jtfPesquisaPro.getText() + "%' ";
                }else{
                    query = "where status = "+ jtfPesquisaPro.getText() + "%' ";
                }//fim do if else if
                
                //Criando ArrayList vazio para receber os dados do ArrayList
                ArrayList<ClienteVO> col = new ArrayList<>();
                
                //Recebendo o ArrayList cheio do produto
                col = cs.filtrarCliente(query);
                
                for(int i = 0; i < col.size(); i++){
                    dtm.addRow(new String[]{ 
                    String.valueOf(col.get(i).getIdCliente()),
                    String.valueOf(col.get(i).getNome()),
                    String.valueOf(col.get(i).getCpf()),
                    String.valueOf(col.get(i).getGenero()),
                    String.valueOf(col.get(i).getRua()),
                    String.valueOf(col.get(i).getNumero()),
                    String.valueOf(col.get(i).getComplemento()),
                    String.valueOf(col.get(i).getStatus()),
                    });
                }//fecha o for
                
                //Adicionar o modelo de tabela com os dados da tabela jtCliente
                jtCliente.setModel(dtm);
                
            }//fim do if else
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao filtrar cliente!GUIManutencãoCliente.filtrar" + e.getMessage());
        }
        
    }//fecha o filtrar
    
    private void deletarCliente(){
        try {
            int linha = jtCliente.getSelectedRow();
            
            if(linha == -1){
                JOptionPane.showMessageDialog(null,
                        "Por favor selecione uma linha!");
            }else{               
                int resposta = JOptionPane.showConfirmDialog(null,
                        "Deseja mesmo deletar o cliente selecionado?",
                        "Deletar cliente",
                        JOptionPane.YES_NO_OPTION);
                if(resposta == JOptionPane.YES_OPTION){                   
                    ClienteServicos cs = ServicosFactory.getClienteServicos();
                    String codCliente = (String) jtCliente.getValueAt(linha, 0);
                    cs.deletarCliente(Long.parseLong(codCliente));
                
                    //Mensagem para o usuário
                    JOptionPane.showMessageDialog(null,
                        "cliente excluido com sucesso!");   
                }else{                   
                    JOptionPane.showMessageDialog(null,
                            "cliente não excluido");
                }//fim if else              
            }//fim do if else
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao deletar cliente!GUIManutencaoCliente.deletar" + e.getMessage());
        }
    }
    private void alterarCliente(){
        int linha = jtCliente.getSelectedRow();
        if(linha != -1){
            jtfCodigo.setText((String) jtCliente.getValueAt(linha, 0));
            jtfNome.setText((String) jtCliente.getValueAt(linha, 1));
            jtfCPF.setText((String) jtCliente.getValueAt(linha, 2));
            jtfGenero.setText((String) jtCliente.getValueAt(linha, 3));
            jtfRua.setText((String) jtCliente.getValueAt(linha, 4));
            jtfNumero.setText((String) jtCliente.getValueAt(linha, 5));
            jtfComplemento.setText((String) jtCliente.getValueAt(linha, 6));
            jtfStatus.setText((String) jtCliente.getValueAt(linha, 7));
            
        }else{
            JOptionPane.showMessageDialog(
                    null,
                    "Você não selecionou nenhuma linha!");
        }//fecha o if else
    }//fim do método alterarProduto
    
    
    public void confirmarAlteracao(){
        try {
            ClienteVO cVO = new ClienteVO();
            if(jtfNome.getText().isEmpty() ||
                    jtfCPF.getText().isEmpty() ||
                    jtfGenero.getText().isEmpty() || 
                    jtfRua.getText().isEmpty() ||
                    jtfNumero.getText().isEmpty() ||
                    jtfComplemento.getText().isEmpty() ||
                    jtfStatus.getText().isEmpty() ) {
                JOptionPane.showMessageDialog(
                        null,
                        "Erro! Por favor insira as informações corretamente!",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }else{
                cVO.setIdCliente(Integer.parseInt(jtfCodigo.getText() ) );
                cVO.setNome(jtfNome.getText() );
                cVO.setCpf(jtfCPF.getText() );
                cVO.setGenero(jtfGenero.getText()  );
                cVO.setRua(jtfRua.getText()  );
                cVO.setNumero(jtfNumero.getText()  );
                cVO.setComplemento(jtfComplemento.getText() );
                cVO.setStatus(jtfStatus.getText()  );
                
                ClienteServicos cs = ServicosFactory.getClienteServicos();
                cs.alterarCliente(cVO);
                
                JOptionPane.showMessageDialog(
                        null,
                        "cliente alterado com sucesso!");
            }//fim do if else
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Erro!" + e.getMessage());
        }
    }//fim do método confirmarAlteracao
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane2 = new javax.swing.JLayeredPane();
        jbtnPreencher = new javax.swing.JButton();
        jbtnLimpar = new javax.swing.JButton();
        jbtnDeletar = new javax.swing.JButton();
        jbtnAlterar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtCliente = new javax.swing.JTable();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jtfGenero = new javax.swing.JTextField();
        jtfNome = new javax.swing.JTextField();
        jtfCodigo = new javax.swing.JTextField();
        jbtnConfirmarAlteracao = new javax.swing.JButton();
        jtfCPF = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jtfRua = new javax.swing.JTextField();
        jtfNumero = new javax.swing.JTextField();
        jtfComplemento = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jtfStatus = new javax.swing.JTextField();
        jLayeredPane4 = new javax.swing.JLayeredPane();
        jLabel5 = new javax.swing.JLabel();
        jtfPesquisaPro = new javax.swing.JTextField();
        jcbPesquisaPro = new javax.swing.JComboBox<>();

        setClosable(true);
        setIconifiable(true);

        jLayeredPane2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jbtnPreencher.setText("Preencher");
        jbtnPreencher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnPreencherActionPerformed(evt);
            }
        });

        jbtnLimpar.setText("Limpar");
        jbtnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnLimparActionPerformed(evt);
            }
        });

        jbtnDeletar.setText("Deletar");
        jbtnDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnDeletarActionPerformed(evt);
            }
        });
        jbtnDeletar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jbtnDeletarKeyPressed(evt);
            }
        });

        jbtnAlterar.setText("Alterar");
        jbtnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAlterarActionPerformed(evt);
            }
        });
        jbtnAlterar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jbtnAlterarKeyPressed(evt);
            }
        });

        jLayeredPane2.setLayer(jbtnPreencher, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jbtnLimpar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jbtnDeletar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jbtnAlterar, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane2Layout = new javax.swing.GroupLayout(jLayeredPane2);
        jLayeredPane2.setLayout(jLayeredPane2Layout);
        jLayeredPane2Layout.setHorizontalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbtnPreencher)
                .addGap(59, 59, 59)
                .addComponent(jbtnLimpar)
                .addGap(58, 58, 58)
                .addComponent(jbtnAlterar)
                .addGap(62, 62, 62)
                .addComponent(jbtnDeletar)
                .addContainerGap(75, Short.MAX_VALUE))
        );
        jLayeredPane2Layout.setVerticalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnPreencher)
                    .addComponent(jbtnLimpar)
                    .addComponent(jbtnDeletar)
                    .addComponent(jbtnAlterar))
                .addContainerGap())
        );

        jtCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Nome", "CPF", "Genero", "Rua", "Numero", "Complemento", "Status"
            }
        ));
        jScrollPane1.setViewportView(jtCliente);

        jLayeredPane1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setText("Genero:");

        jLabel2.setText("CPF:");

        jLabel3.setText("Nome:");

        jLabel4.setText("Código:");

        jtfCodigo.setEditable(false);
        jtfCodigo.setFocusable(false);

        jbtnConfirmarAlteracao.setText("Confirmar Alteração");
        jbtnConfirmarAlteracao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnConfirmarAlteracaoActionPerformed(evt);
            }
        });
        jbtnConfirmarAlteracao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jbtnConfirmarAlteracaoKeyPressed(evt);
            }
        });

        jtfCPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfCPFActionPerformed(evt);
            }
        });

        jLabel6.setText("Rua");

        jLabel7.setText("Numero");

        jLabel8.setText("complemento:");

        jtfRua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfRuaActionPerformed(evt);
            }
        });

        jtfNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfNumeroActionPerformed(evt);
            }
        });

        jtfComplemento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfComplementoActionPerformed(evt);
            }
        });

        jLabel9.setText("Status");

        jtfStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfStatusActionPerformed(evt);
            }
        });

        jLayeredPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jtfGenero, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jtfNome, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jtfCodigo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jbtnConfirmarAlteracao, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jtfCPF, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jtfRua, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jtfNumero, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jtfComplemento, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jtfStatus, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfNome)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(jtfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jtfCPF)
                    .addComponent(jtfGenero, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jtfRua, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jtfNumero, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jtfComplemento, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbtnConfirmarAlteracao, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jtfStatus))
                .addContainerGap())
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jtfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtfCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtfGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jtfRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jtfNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jtfComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jtfStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnConfirmarAlteracao)
                        .addContainerGap())))
        );

        jLayeredPane4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel5.setText("Pesquisa:");

        jtfPesquisaPro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfPesquisaProKeyReleased(evt);
            }
        });

        jcbPesquisaPro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Código", "Nome", "CPF", "Genero", "Rua", "Numero", "Complemento", "Status" }));
        jcbPesquisaPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbPesquisaProActionPerformed(evt);
            }
        });

        jLayeredPane4.setLayer(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(jtfPesquisaPro, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(jcbPesquisaPro, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane4Layout = new javax.swing.GroupLayout(jLayeredPane4);
        jLayeredPane4.setLayout(jLayeredPane4Layout);
        jLayeredPane4Layout.setHorizontalGroup(
            jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jtfPesquisaPro)
                .addGap(18, 18, 18)
                .addComponent(jcbPesquisaPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jLayeredPane4Layout.setVerticalGroup(
            jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jtfPesquisaPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbPesquisaPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 572, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLayeredPane4)
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1)
                                .addComponent(jLayeredPane2))))
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 605, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLayeredPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jLayeredPane1)
                    .addContainerGap()))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnPreencherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnPreencherActionPerformed
        limparTabela();
        preencherTabela();
    }//GEN-LAST:event_jbtnPreencherActionPerformed

    private void jbtnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnLimparActionPerformed
        limparTabela();
    }//GEN-LAST:event_jbtnLimparActionPerformed

    private void jbtnDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnDeletarActionPerformed
        deletarCliente();
        limparTabela();
        preencherTabela();
    }//GEN-LAST:event_jbtnDeletarActionPerformed

    private void jbtnDeletarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jbtnDeletarKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            deletarCliente();
            limparTabela();
            preencherTabela();
        }
    }//GEN-LAST:event_jbtnDeletarKeyPressed

    private void jbtnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAlterarActionPerformed
        alterarCliente();
    }//GEN-LAST:event_jbtnAlterarActionPerformed

    private void jbtnAlterarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jbtnAlterarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            alterarCliente();
        }
    }//GEN-LAST:event_jbtnAlterarKeyPressed

    private void jbtnConfirmarAlteracaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnConfirmarAlteracaoActionPerformed
        confirmarAlteracao();
        limparTabela();
        preencherTabela();
    }//GEN-LAST:event_jbtnConfirmarAlteracaoActionPerformed

    private void jbtnConfirmarAlteracaoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jbtnConfirmarAlteracaoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            confirmarAlteracao();
            limparTabela();
            preencherTabela();
        }
    }//GEN-LAST:event_jbtnConfirmarAlteracaoKeyPressed

    private void jtfCPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfCPFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfCPFActionPerformed

    private void jtfRuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfRuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfRuaActionPerformed

    private void jtfNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfNumeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfNumeroActionPerformed

    private void jtfComplementoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfComplementoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfComplementoActionPerformed

    private void jtfPesquisaProKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfPesquisaProKeyReleased
        // Evento quando clica no barra de pesquisa
        limparTabela();
        filtrar();
    }//GEN-LAST:event_jtfPesquisaProKeyReleased

    private void jcbPesquisaProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbPesquisaProActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbPesquisaProActionPerformed

    private void jtfStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfStatusActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnAlterar;
    private javax.swing.JButton jbtnConfirmarAlteracao;
    private javax.swing.JButton jbtnDeletar;
    private javax.swing.JButton jbtnLimpar;
    private javax.swing.JButton jbtnPreencher;
    private javax.swing.JComboBox<String> jcbPesquisaPro;
    private javax.swing.JTable jtCliente;
    private javax.swing.JTextField jtfCPF;
    private javax.swing.JTextField jtfCodigo;
    private javax.swing.JTextField jtfComplemento;
    private javax.swing.JTextField jtfGenero;
    private javax.swing.JTextField jtfNome;
    private javax.swing.JTextField jtfNumero;
    private javax.swing.JTextField jtfPesquisaPro;
    private javax.swing.JTextField jtfRua;
    private javax.swing.JTextField jtfStatus;
    // End of variables declaration//GEN-END:variables
}
