package to;

import java.sql.Date;

public class MovimentacaoBancariaTO {
	
	public int	 	idCliente;
	public double	valorMovimentcao;
	public double	saldoAtual;
	public int		tipoMovimentacao;
	public Date		data;

	public int getIdCliente() {
		return idCliente;
	}

	public double getValorMovimentcao() {
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

	public void setValorMovimentcao(double valorRetirado) {
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
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MovimentacaoBancariaTO other = (MovimentacaoBancariaTO) obj;
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
