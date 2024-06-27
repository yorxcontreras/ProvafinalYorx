package br.edu.up.controller;

import dao.AlunoDAO;
import dao.AlunoDAOImpl;
import model.Aluno;

import java.util.List;

public class MainController {
    private AlunoDAO alunoDAO;

    public MainController() {
        this.alunoDAO = new AlunoDAOImpl();
    }

    public void adicionarNovoAluno(Aluno aluno) {
        alunoDAO.adicionarAluno(aluno);
    }

    public void processarAlunos() {
        alunoDAO.gerarResumo();
    }

    public List<Aluno> listarTodosOsAlunos() {
        return alunoDAO.listarTodos();
    }
}