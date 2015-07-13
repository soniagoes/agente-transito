package br.com.agente.model.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table (name="pessoa")
public class Pessoa implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id // indica que é a chave primaria
    @GeneratedValue //campo que vai ser auto incremento
    @Column(name="IdPessoa", nullable=false)
    private Integer idPessoa;
    @Column(name="nome",nullable = false, length=80)
    private String nome;
   @Column(name="email",nullable = false, length=80)
    private String email;
   @Column(name="telefone",nullable = false, length=15)//(067)-8888-8888
    private String telefone;
   @Column(name="cpf",nullable = false, length=14)//999.999.999-99
    private String cpf;
   @Column(name="dtNasc",nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dtNasc;
   @Column(name="dtCadastro",nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dtCadastro;

    public Pessoa() {
    }

    public Integer getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDtNasc() {
        return dtNasc;
    }

    public void setDtNasc(Date dtNasc) {
        this.dtNasc = dtNasc;
    }

    public Date getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(Date dtCadastro) {
        this.dtCadastro = dtCadastro;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (this.idPessoa != null ? this.idPessoa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pessoa other = (Pessoa) obj;
        if (this.idPessoa != other.idPessoa && (this.idPessoa == null || !this.idPessoa.equals(other.idPessoa))) {
            return false;
        }
        return true;
    }
    
}
