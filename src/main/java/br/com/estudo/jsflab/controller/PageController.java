package br.com.estudo.jsflab.controller;

import br.com.estudo.jsflab.model.Pessoa;
import br.com.estudo.jsflab.repository.PessoaRepository;
import br.com.estudo.jsflab.repository.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class PageController implements Serializable {

    private Pessoa pessoa;
    private List<Pessoa> pessoas;
    private PessoaRepository pessoaRepository;
    
    public PageController() {
    }
    
    @PostConstruct
    public void init() {
        pessoaRepository = new PessoaRepository();
    }
    
    public void novo() {
        pessoa = null;
    }

    public void salvar() {
        if (getPessoa().getId() == null) {
            pessoaRepository.create(pessoa);

        } else {
            try {
                pessoaRepository.edit(pessoa);
            } catch (Exception ex) {
                Logger.getLogger(PageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        atualizarTela();
    }

    public void excluir() {
        if (pessoa != null && (pessoa.getId() != null && pessoa.getId() > 0)) {
            try {
                pessoaRepository.destroy(pessoa.getId());
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(PageController.class.getName()).log(Level.SEVERE, null, ex);
            }
            atualizarTela();
        }
    }

    private void atualizarTela() {
        atualizarGride();
        novo();
    }

    private void atualizarGride() {
        pessoas = null;
    }

    public Pessoa getPessoa() {
        if (pessoa == null) {
            pessoa = new Pessoa();
        }
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<Pessoa> getPessoas() {
        if (pessoas == null) {
            pessoas = pessoaRepository.findPessoaEntities();
        }
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

}
