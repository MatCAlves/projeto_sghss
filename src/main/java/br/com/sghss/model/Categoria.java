package br.com.sghss.model;

public enum Categoria {
    CURATIVOS("Curativos"),
    DESCARTAVEIS("Descartáveis"),
    MEDICAMENTOS("Medicamentos");

    private String descricao;

    Categoria(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }
}