import java.util.*;

public class InterpretadorREPL {
    private static final Map<String, Double> variaveis = new HashMap<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Interpretador - Digite expressões ou atribuições (ex: x = 5 + 3), ou 'sair' para encerrar.");

        while (true) {
            System.out.print("> ");
            String linha = scanner.nextLine().trim();

            if (linha.equalsIgnoreCase("sair")) break;

            try {
                double resultado = avaliarLinha(linha);
                System.out.println("= " + resultado);
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private static double avaliarLinha(String linha) {
        if (linha.contains("=")) {
            String[] partes = linha.split("=");
            if (partes.length != 2) throw new RuntimeException("Atribuição inválida.");

            String nome = partes[0].trim();
            String expressao = partes[1].trim();

            double valor = avaliarExpressao(expressao);
            variaveis.put(nome, valor);
            return valor;
        } else {
            return avaliarExpressao(linha);
        }
    }

    private static double avaliarExpressao(String expr) {
        List<Double> valores = new ArrayList<>();
        List<Character> operadores = new ArrayList<>();

        StringBuilder token = new StringBuilder();
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            if (c == '+' || c == '-' || c == '*' || c == '/') {
                valores.add(obterValor(token.toString().trim()));
                operadores.add(c);
                token.setLength(0);
            } else {
                token.append(c);
            }
        }
        valores.add(obterValor(token.toString().trim()));

        // Primeiro resolve * e /
        for (int i = 0; i < operadores.size(); ) {
            char op = operadores.get(i);
            if (op == '*' || op == '/') {
                double a = valores.get(i);
                double b = valores.get(i + 1);
                double res = (op == '*') ? (a * b) : (a / b);

                valores.set(i, res);
                valores.remove(i + 1);
                operadores.remove(i);
            } else {
                i++;
            }
        }

        // Depois resolve + e -
        double resultado = valores.get(0);
        for (int i = 0; i < operadores.size(); i++) {
            char op = operadores.get(i);
            double b = valores.get(i + 1);
            resultado = (op == '+') ? (resultado + b) : (resultado - b);
        }

        return resultado;
    }

    private static double obterValor(String token) {
        if (token.isEmpty()) throw new RuntimeException("Token vazio.");

        try {
            return Double.parseDouble(token);
        } catch (NumberFormatException e) {
            if (variaveis.containsKey(token)) {
                return variaveis.get(token);
            } else {
                throw new RuntimeException("Variável '" + token + "' não definida.");
            }
        }
    }
}
