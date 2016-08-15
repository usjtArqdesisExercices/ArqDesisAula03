package controller;

import java.util.Date;

import dao.MovimentacaoBancariaDAO;
import to.MovimentacaoBancariaTO;

public class MovimentacaoBancaria {
	
	public int	 	idCliente;
	public double	valorRetirado;
	public double	saldoAtual;
	public int		tipoMovimentacao;
	public Date		data;

	public MovimentacaoBancaria(int idCliente, double valorRetirado, double saldoAtual, int tipoMovimentacao,
			Date data) {
		super();
		this.idCliente = idCliente;
		this.valorRetirado = valorRetirado;
		this.saldoAtual = saldoAtual;
		this.tipoMovimentacao = tipoMovimentacao;
		this.data = data;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public double getValorRetirado() {
		return valorRetirado;
	}

	public double getSaldoAtual() {
		return saldoAtual;
	}

	public int getTipoMovimentacao() {
		return tipoMovimentacao;
	}
	
	public Date getData() {
		return data;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public void setValorRetirado(double valorRetirado) {
		this.valorRetirado = valorRetirado;
	}

	public void setSaldoAtual(double saldoAtual) {
		this.saldoAtual = saldoAtual;
	}

	public void setTipoMovimentacao(int tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	public void salvaDebito(){
		
		MovimentacaoBancariaDAO movBancariaDAO = new MovimentacaoBancariaDAO();
		MovimentacaoBancariaTO movBancariaTO =  new MovimentacaoBancariaTO();
		movBancariaTO.setIdCliente(idCliente);
		movBancariaTO.setSaldoAtual(saldoAtual);
		movBancariaTO.setValorRetirado(valorRetirado);
		movBancariaTO.setTipoMovimentacao(2);
		movBancariaTO.setData((java.sql.Date) data);
		movBancariaDAO.salvaDebito(movBancariaTO);
		
	}

}
