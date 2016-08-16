package controller;

import java.util.Date;

import dao.MovimentacaoBancariaDAO;
import to.MovimentacaoBancariaTO;

public class MovimentacaoBancaria {
	
	public int	 	idCliente;
	public double	valorMovimentcao;
	public double	saldoAtual;
	public int		tipoMovimentacao;
	public Date		data;

	public MovimentacaoBancaria(int idCliente, double valorRetirado, double saldoAtual, int tipoMovimentacao,
			Date data) {
		super();
		this.idCliente = idCliente;
		this.valorMovimentcao = valorRetirado;
		this.saldoAtual = saldoAtual;
		this.tipoMovimentacao = tipoMovimentacao;
		this.data = data;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public double getValorRetirado() {
		return valorMovimentcao;
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
		this.valorMovimentcao = valorRetirado;
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
		movBancariaTO.setValorMovimentcao(valorMovimentcao);
		movBancariaTO.setTipoMovimentacao(tipoMovimentacao);
		movBancariaTO.setData((java.sql.Date) data);
		movBancariaDAO.salvaDebito(movBancariaTO);
		
	}
	
	
	@Override	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MovimentacaoBancaria other = (MovimentacaoBancaria) obj;
		if (idCliente != other.idCliente)
			return false;
		if (saldoAtual != other.saldoAtual)
			return false;
		if (valorMovimentcao != other.valorMovimentcao)
			return false;
		if (tipoMovimentacao != other.tipoMovimentacao)
			return false;		
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		return true;
	}

}
