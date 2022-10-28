import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Aplicacao {
    public static void main(String[] args) throws Exception {
//        Caminhao carrinho = new Caminhao("PCE234", 2, 20000);
//        System.out.println(carrinho.calcularCustos());
//        Frota frota1 = new Frota ("frotinha");
//        frota1.carregar("./Teste.txt");
//        frota1.salvar("./Write.txt");
//        System.out.println(carrinho.getAutonomia());
//        Rota rota1 = new Rota("0/0/0",500);
//        System.out.println(frota1.incluirrota("FER678",rota1));
        JFrame frame = new JFrame("My First GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);
        JButton button1 = new JButton("Button 1");
        JButton button2 = new JButton("Button 2");
        frame.getContentPane().add(button1);
        frame.getContentPane().add(button2);
        frame.setVisible(true);
    }
}
