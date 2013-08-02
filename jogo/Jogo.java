import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Jogo
{
    public static void main(String[] s) throws Exception
    {

        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

        MiniMax mm = new MiniMax(3);

        Estado corrente = new Estado("1 1 1 1 1 1 1 1 0 0 0 0 0 0 0 0 2 2 2 2 2 2 2 2", 1);

        corrente.setJogador(corrente.j2);

        System.out.println("Breakthrou!");

        while (!corrente.isFinal()) {
            System.out.println(corrente+"\n");
            if (corrente.getJogador() == corrente.j2)
            {
                boolean validado = false;
                while (!validado)
                {
                    try {
                        System.out.print("Linha Inicial (1 a 6): ");
                        byte lI = (byte)(Integer.parseInt( keyboard.readLine()) - 1);

                        System.out.print("Coluna Inicial (1 a 4): ");
                        byte cI = (byte)(Integer.parseInt( keyboard.readLine()) - 1);

                        System.out.print("Linha Final (1 a 6): ");
                        byte l = (byte)(Integer.parseInt( keyboard.readLine()) - 1);

                        System.out.print("Coluna Final (1 a 4): ");
                        byte c = (byte)(Byte.parseByte( keyboard.readLine()) - 1);

                        if ( corrente.livre(l, c)) {
                            corrente = new Estado( corrente, corrente.j1);
                            corrente.joga(lI,cI, l, c, corrente.j1);
                            validado = true;
                        } else {
                            System.out.println("\nOperação illegal, casa ocupada!");
                        }

                    } catch (Exception ex) {
                    }
                }
            } else {
                mm.max(corrente);
                System.out.println("Nodos Visitados: " + mm.visitados);
                corrente = corrente.getProxJog();
            }
        }
        System.out.println("\nFIM"+corrente);
    }
}
