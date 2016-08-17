package to;

import java.sql.Date;

public class ExtratoTO {

	public int idCliente;
	public double valorMovimentacao;
	public double saldoAtual;
	public int IdTipoMovimentacao;
	public int tipoCredDeb;
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

	public int getIdTipoMovimentacao() {
		return IdTipoMovimentacao;
	}

	public int getTipoCredDeb() {
		return tipoCredDeb;
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

	public void setValorMovimentacao(double valorMovimentacao) {
		this.valorMovimentacao = valorMovimentacao;
	}

	public void setSaldoAtual(double saldoAtual) {
		this.saldoAtual = saldoAtual;
	}

	public void setIdTipoMovimentacao(int idTipoMovimentacao) {
		IdTipoMovimentacao = idTipoMovimentacao;
	}

	public void setTipoCredDeb(int tipoCredDeb) {
		this.tipoCredDeb = tipoCredDeb;
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExtratoTO other = (ExtratoTO) obj;
		if (idCliente != other.idCliente)
			return false;
		if (saldoAtual != other.saldoAtual)
			return false;
		if (valorMovimentacao != other.valorMovimentacao)
			return false;
		if (IdTipoMovimentacao != other.IdTipoMovimentacao)
			return false;
		if (tipoCredDeb != other.tipoCredDeb)
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (dataInicial == null) {
			if (other.dataInicial != null)
				return false;
		} else if (!dataInicial.equals(other.dataInicial))
			return false;
		if (dataFinal == null) {
			if (other.dataFinal != null)
				return false;
		} else if (!dataFinal.equals(other.dataFinal))
			return false;
		return true;
	}

}
