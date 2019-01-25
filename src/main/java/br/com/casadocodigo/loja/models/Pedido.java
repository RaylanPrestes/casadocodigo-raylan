package br.com.casadocodigo.loja.models;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class Pedido {

	private int id;
	private BigDecimal valor;
	@DateTimeFormat
	private Calendar data;
	private List<Produto> produtos;
	
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produto) {
		this.produtos = produto;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public Calendar getData() {
		return this.data;
	}
	public void setData(Calendar data) {
		this.data = data;
	}	
}
