package com.example.demo.dto;


public class PedidoDTO {
    private Integer userId;
    private Integer mesaId;
    private String descripcion;

    public PedidoDTO() {}

    public PedidoDTO(Integer userId, Integer mesaId, String descripcion) {
        this.userId = userId;
        this.mesaId = mesaId;
        this.descripcion = descripcion;
    }

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }

    public Integer getMesaId() { return mesaId; }
    public void setMesaId(Integer mesaId) { this.mesaId = mesaId; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
