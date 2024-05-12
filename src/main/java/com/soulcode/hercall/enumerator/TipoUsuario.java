package com.soulcode.hercall.enumerator;

public enum TipoUsuario {
    FUNCIONARIO("Funcionário"),
    TECNICO("Técnico"),
    ADMIN("Admin");

    private String tipoUsuario;

    TipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }
}