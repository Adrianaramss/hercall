package com.soulcode.hercall.enumerator;

public enum TipoStatus {
    AGUARDANDO_TECNICO("Aguardando técnico"),
    EM_ATENDIMENTO("Em atendimento"),
    ESCALADO_PARA_OUTRO_SETOR("Escalado para outro setor"),
    CANCELADO("Cancelado"),
    FINALIZADO("Finalizado");

    private String tipoStatus;

    TipoStatus(String tipoStatus) {
        this.tipoStatus = tipoStatus;
    }

    public String getTipoStatus() {
        return tipoStatus;
    }

}