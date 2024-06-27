package br.edu.up.dao;

import model.Aluno;
import java.util.List;

public interface AlunoDAO {
    List<Aluno> listarTodos();
    void adicionarAluno(Aluno aluno);
    int quantidadeDeAlunos();
    int quantidadeDeAprovados();
    int quantidadeDeReprovados();
    double menorNota();
    double maiorNota();
    double mediaGeral();
    void gerarResumo();
}