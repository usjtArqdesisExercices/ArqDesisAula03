package to;

import java.sql.Date;

public class ExtratoTO {
	
	public int	 	idCliente;
	public double	valorMovimentacao;
	public double	saldoAtual;
	public int		tipoMovimentacao;
	public int		tipo;
	private Date dataInicial;
	private Date dataFinal;
	private Date data;

	public int getIdCliente() {
		return idCliente;
	}
	public double getValorMovimentacao() {
		return valorMovimentacao;
	}
	public double getSaldoAtual() {
		return saldoAtual;
	}
	public int getTipoMovimentacao() {
		return tipoMovimentacao;
	}
	public int getTipo() {
		return tipo;
	}
	public Date getDataInicial() {
		return dataInicial;
	}
	public Date getDataFinal() {
		return dataFinal;
	}
	public Date getData() {
		return data;
	}	
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public void setValorMovimentacao(double valorRetirado) {
		this.valorMovimentacao = valorRetirado;
	}
	public void setSaldoAtual(double saldoAtual) {
		this.saldoAtual = saldoAtual;
	}
	public void setTipoMovimentacao(int tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	public void setData(Date data) {
		this.data = data;
	}	
	

}
