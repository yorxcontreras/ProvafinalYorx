package br.edu.up.dao;
import model.Aluno;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAOImpl implements AlunoDAO {
    private static final String ARQUIVO_CSV = "recursos/alunos.csv";
    private static final String ARQUIVO_RESUMO = "recursos/resumo.csv";
    private static final String CSV_DIVISOR = ";";

    @Override
    public List<Aluno> listarTodos() {
        List<Aluno> listaDeAlunos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_CSV))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dadosAluno = linha.split(CSV_DIVISOR);
                int matricula = Integer.parseInt(dadosAluno[0]);
                String nome = dadosAluno[1];
                double nota = Double.parseDouble(dadosAluno[2].replace(",", ".")); // substituir vírgula por ponto
                Aluno aluno = new Aluno(matricula, nome, nota);
                listaDeAlunos.add(aluno);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        return listaDeAlunos;
    }

    @Override
    public void adicionarAluno(Aluno aluno) {
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(ARQUIVO_CSV, true)))) {
            String linha = aluno.getMatricula() + CSV_DIVISOR + aluno.getNome() + CSV_DIVISOR + aluno.getNota();
            pw.println(linha);
        } catch (IOException e) {
            System.out.println("Erro ao adicionar aluno no arquivo: " + e.getMessage());
        }
    }

    @Override
    public int quantidadeDeAlunos() {
        return listarTodos().size();
    }

    @Override
    public int quantidadeDeAprovados() {
        int contador = 0;
        for (Aluno aluno : listarTodos()) {
            if (aluno.getNota() >= 6.0) {
                contador++;
            }
        }
        return contador;
    }

    @Override
    public int quantidadeDeReprovados() {
        return quantidadeDeAlunos() - quantidadeDeAprovados();
    }

    @Override
    public double menorNota() {
        double menor = Double.MAX_VALUE;
        for (Aluno aluno : listarTodos()) {
            if (aluno.getNota() < menor) {
                menor = aluno.getNota();
            }
        }
        return menor;
    }

    @Override
    public double maiorNota() {
        double maior = Double.MIN_VALUE;
        for (Aluno aluno : listarTodos()) {
            if (aluno.getNota() > maior) {
                maior = aluno.getNota();
            }
        }
        return maior;
    }

    @Override
    public double mediaGeral() {
        double soma = 0;
        for (Aluno aluno : listarTodos()) {
            soma += aluno.getNota();
        }
        return soma / quantidadeDeAlunos();
    }

    @Override
    public void gerarResumo() {
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(ARQUIVO_RESUMO)))) {
            pw.println("Quantidade de alunos na turma;Quantidade de aprovados;Quantidade de reprovados;Menor nota da turma;Maior nota da turma;Média geral da turma");
            pw.println(quantidadeDeAlunos() + CSV_DIVISOR + quantidadeDeAprovados() + CSV_DIVISOR + quantidadeDeReprovados() + CSV_DIVISOR +
                    menorNota() + CSV_DIVISOR + maiorNota() + CSV_DIVISOR + mediaGeral());
        } catch (IOException e) {
            System.out.println("Erro ao gerar o arquivo de resumo: " + e.getMessage());
        }
    }
}