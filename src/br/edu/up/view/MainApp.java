package br.edu.up.view;

import controller.MainController;
import model.Aluno;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        MainController controller = new MainController();

        List<Aluno> alunos = controller.listarTodosOsAlunos();
        for (Aluno aluno : alunos) {
            System.out.println(aluno);
        }

        Aluno novoAluno = new Aluno(101, "José Maria", 8.5);
        controller.adicionarNovoAluno(novoAluno);

        controller.processarAlunos();

        System.out.println("Operações concluídas com sucesso!");
    }
}