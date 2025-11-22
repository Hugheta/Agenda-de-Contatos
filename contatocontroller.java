// Arquivo: Controller/ContatoController.java
package Controller;

import Model.Contato;
import java.util.List;

public class ContatoController {

    // CREATE - Adicionar novo contato
    public boolean adicionarContato(String nome, String email, String telefone) {
        if (validarCampos(nome, email)) {
            boolean sucesso = Contato.criar(nome, email, telefone);
            if (sucesso) {
                System.out.println("Contato adicionado com sucesso!");
            } else {
                System.out.println("Erro ao adicionar contato!");
            }
            return sucesso;
        } else {
            System.out.println("Nome e email sao obrigatorios!");
            return false;
        }
    }

    // READ - Listar todos os contatos
    public List<Contato> listarContatos() {
        List<Contato> contatos = Contato.listarTodos();

        if (contatos.isEmpty()) {
            System.out.println("Nenhum contato encontrado.");
        } else {
            System.out.println("Total de contatos: " + contatos.size());
        }

        return contatos;
    }

    // READ - Buscar contato por ID
    public Contato buscarContatoPorId(int id) {
        Contato contato = Contato.buscarPorId(id);

        if (contato != null) {
            System.out.println("Contato encontrado!");
        } else {
            System.out.println("Contato nao encontrado!");
        }

        return contato;
    }

    
    public Contato buscarContatoPorEmail(String email) {
        Contato contato = Contato.buscarPorEmail(email);

        if (contato != null) {
            System.out.println("Contato encontrado!");
        } else {
            System.out.println("Contato nao encontrado!");
        }

        return contato;
    }

    // UPDATE - Atualizar contato
    public boolean atualizarContato(int id, String nome, String email, String telefone) {
        if (validarCampos(nome, email)) {
            Contato contatoExistente = Contato.buscarPorId(id);

            if (contatoExistente == null) {
                System.out.println("Contato com ID " + id + " nao encontrado!");
                return false;
            }

            boolean sucesso = Contato.atualizar(id, nome, email, telefone);
            if (sucesso) {
                System.out.println("Contato atualizado com sucesso!");
            } else {
                System.out.println("Erro ao atualizar contato!");
            }
            return sucesso;
        } else {
            System.out.println("Nome e email sao obrigatorios!");
            return false;
        }
    }

   
    public boolean deletarContato(int id) {
        Contato contatoExistente = Contato.buscarPorId(id);

        if (contatoExistente == null) {
            System.out.println("Contato com ID " + id + " nao encontrado!");
            return false;
        }

        boolean sucesso = Contato.deletar(id);
        if (sucesso) {
            System.out.println("Contato deletado com sucesso!");
        } else {
            System.out.println("Erro ao deletar contato!");
        }
        return sucesso;
    }

   
    private boolean validarCampos(String nome, String email) {
        return nome != null && !nome.trim().isEmpty() &&
               email != null && !email.trim().isEmpty();
    }

    
    public void exibirContato(Contato contato) {
        if (contato != null) {
            System.out.println("\n--- Detalhes do Contato ---");
            System.out.println("ID: " + contato.getId());
            System.out.println("Nome: " + contato.getNome());
            System.out.println("Email: " + contato.getEmail());
            System.out.println("Telefone: " + (contato.getTelefone() != null ? contato.getTelefone() : "Nao informado"));
            System.out.println("----------------------------\n");
        } else {
            System.out.println("Contato invalido!");
        }
    }

    // Exibir lista de contatos
    public void exibirListaContatos(List<Contato> contatos) {
        if (contatos.isEmpty()) {
            System.out.println("Nenhum contato para exibir!");
            return;
        }

        System.out.println("\n====== LISTA DE CONTATOS ======");
        System.out.printf("%-5s | %-30s | %-30s | %-15s%n", "ID", "Nome", "Email", "Telefone");
        System.out.println("-----------------------------------------------");

        for (Contato c : contatos) {
            String telefone = c.getTelefone() != null ? c.getTelefone() : "N/A";
            System.out.printf("%-5d | %-30s | %-30s | %-15s%n", 
                c.getId(), c.getNome(), c.getEmail(), telefone);
        }

        System.out.println("===============================\n");
    }
}
