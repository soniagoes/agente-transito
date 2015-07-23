package br.com.agente.controller;

import br.com.agente.model.dao.HibernateDAO;
import br.com.agente.model.dao.InterfaceDAO;
import br.com.agente.model.entities.Cidade;
import br.com.agente.util.FacesContextUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "mbCidade")
//suporte a jsf por isso essa anotação @ManagedBean
@RequestScoped
public class MbCidade implements Serializable {

    private static final long serialVersionUID = 1L;
    //instancia de cidade
    private Cidade cidade = new Cidade();
    //lista de cidades
    private List<Cidade> cidades;
//construtor (alt + insert)
    public MbCidade() {
    }
//criando a interface DAO com a instancia cidadeDAO
    private InterfaceDAO<Cidade> cidadeDAO() {
        //instanciando o HibernateDAO<Cidade> sendo obrigado a passar via construtor a classe e a sessão do jsf
        //esse HibernateDAO<Cidade> é genérico, então se precisar criar
        //cidadeDAO, enderecoDAO, pessoaDAO
        //fica simples, não preciso implementar um DAO para cada um
        //basta instanciar passando o nome do DAO, a classe e a sessao do hibernate
        //já tem um DAO em tempo de execução para todas as minhas entidades.
        InterfaceDAO<Cidade> cidadeDAO = new HibernateDAO<Cidade>(Cidade.class, FacesContextUtil.getRequestSession());
        return cidadeDAO;
    }

    public String limpCidade() {
        cidade = new Cidade();
        return editCidade();
        //return "/restrict/cadastrarcidade.faces";
    }

    public String editCidade() {
        return "/restrict/cadastrarcidade.faces";
    }

    public String addCidade() {
        //verifica se o id de cidade está vindo preenchido para adicionar a cidade
        //senão ele atualiza o registro cidade no banco.
        if (cidade.getIdCidade() == null || cidade.getIdCidade() == 0) {
            insertCidade();
        } else {
            updateCidade();
        }
        limpCidade();
        return null;
    }
//reclama que não tem os dois metodos, insertCidade e updateCidade
//clicar no interrogação e pedir pra criar...
    private void insertCidade() {
        cidadeDAO().save(cidade);
        //termina de salvar, retorna a mensagem
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso", ""));
    }

    private void updateCidade() {
        cidadeDAO().update(cidade);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização efetuada com sucesso", ""));
    }
    
    public void deleteCidade(){
        cidadeDAO().remove(cidade);        
    }
    
    public List<Cidade> getCidades() {       
        cidades = cidadeDAO().getEntities();
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
        
}
