package to;

import java.sql.Date;

public class MovimentacaoBancariaTO {
	
	public int	 	idCliente;
	public double	valorRetirado;
	public double	saldoAtual;
	public int		tipoMovimentacao;
	public Date		data;

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
	

}
