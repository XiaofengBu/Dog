package cn.hhu.bu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.CharArrayReader;
import java.io.File;
import java.util.Set;

/**
 * Created by buxiaofeng on 2017/5/27.
 */
public class MainFrame extends JFrame{
    private JLabel bhLabel;
    private JComboBox bhjcb;
    private JPanel bhJPanel,xsJPanel;
    private JLabel bsLabel;
    private JComboBox bsjcb;
    private JButton jButton,countButton;
    private JLabel fileLabel;
    private FileDialog openDialog;
    private File file;
    private JTextArea jTextArea;
    public MainFrame(){
        this.setTitle("汉字分类");
        this.setSize(1024, 768);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bhJPanel=new JPanel();
        bhLabel=new JLabel("选择部首笔画数:");
        String []bhstr={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15"};
        bhjcb=new JComboBox(bhstr);
        bsLabel=new JLabel("选择部首:");
        //String []bsstr={"丨","亅","丿","乛","一","乙","乚","丶"};
        bsjcb=new JComboBox();
        BSlist bSlist=new BSlist(1);
        for(BSItem bsItem:bSlist.bsSet){
            bsjcb.addItem(bsItem);
        }
        jButton=new JButton("选择文件");
        fileLabel=new JLabel("");
        openDialog=new FileDialog(this,"打开",FileDialog.LOAD);
        countButton=new JButton("统计");
        jTextArea=new JTextArea();
        jTextArea.setLineWrap(true);
        jTextArea.setColumns(40);
        xsJPanel=new JPanel();
        bhJPanel.add(bhLabel);
        bhJPanel.add(bhjcb);
        bhJPanel.add(bsLabel);
        bhJPanel.add(bsjcb);
        bhJPanel.add(jButton);
        bhJPanel.add(fileLabel);
        bhJPanel.add(countButton);
        xsJPanel.add(jTextArea);
        this.add(bhJPanel,BorderLayout.NORTH);
        this.add(xsJPanel,BorderLayout.CENTER);
        this.addEvent();
        this.setVisible(true);
    }
    private void addEvent(){
        countButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(file==null){
                    JOptionPane.showMessageDialog(null,"提示","请选择文件",JOptionPane.OK_OPTION);
                }else{
                    BSItem bsItem=(BSItem)bsjcb.getSelectedItem();
                    WordCount wordCount=new WordCount(bsItem.bsCode);
                    wordCount.readFile(file);
                    Set<Character> countSet=wordCount.countSet;
                    jTextArea.setText("");
                    for(Character c:countSet){
                        System.out.print(c);
                        jTextArea.append(String.valueOf(c));
                    }
                }
            }
        });
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openDialog.setVisible(true);
                String dirPath=openDialog.getDirectory();
                String fileName=openDialog.getFile();

                if(dirPath==null||fileName==null)
                    return;
                fileLabel.setText(fileName);
                file = new File(dirPath + fileName);
            }
        });
        bhjcb.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent arg0) {
                if(arg0.getStateChange()==ItemEvent.SELECTED) {
                    bsjcb.removeAllItems();
                    String bh=(String)bhjcb.getSelectedItem();
                    BSlist bSlist=new BSlist(Integer.parseInt(bh));
                    for(BSItem bsItem:bSlist.bsSet){
                        bsjcb.addItem(bsItem);
                    }
                }
            }
        });
    }
}
