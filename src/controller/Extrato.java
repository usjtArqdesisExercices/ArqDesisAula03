package controller;

import java.util.ArrayList;
import java.util.Date;
import dao.ExtratoDAO;
import to.ExtratoTO;

public class Extrato {

	public int idCliente;
	public double valorMovimentacao;
	public double saldoAtual;
	public int IdTipoMovimentacao;
	public int tipoCredDeb;
	public Date data;
	private Date dataInicial;
	private Date dataFinal;

	public Extrato(int idCliente, double valorRetirado, double saldoAtual, int tipoMovimentacao, int tipoCredDeb ,Date data) {
		super();
		this.idCliente = idCliente;
		this.valorMovimentacao = valorRetirado;
		this.saldoAtual = saldoAtual;
		this.IdTipoMovimentacao = tipoMovimentacao;
		this.data = data;
		this.tipoCredDeb = tipoCredDeb;
	}

	public Extrato() {
		super();

	}

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

	public void extratoDias(int numeroDias) {

		Data data = new Data();

		setDataInicial(data.retornaData(numeroDias));
		setDataFinal(data.retornaDataHoje());
	}

	public void salvaExtrato() {

		ExtratoDAO movBancariaDAO = new ExtratoDAO();
		ExtratoTO extratoTO = new ExtratoTO();
		extratoTO.setIdCliente(idCliente);
		extratoTO.setSaldoAtual(saldoAtual);
		extratoTO.setValorMovimentacao(valorMovimentacao);
		extratoTO.setIdTipoMovimentacao(IdTipoMovimentacao);
		extratoTO.setTipoCredDeb(2);
		extratoTO.setData((java.sql.Date) data);
		movBancariaDAO.salvaDebito(extratoTO);

	}

	public <E> void consultaExtrato() {

		ExtratoDAO extDAO = new ExtratoDAO();
		ExtratoTO extTO = new ExtratoTO();

		extTO.setIdCliente(idCliente);
		extTO.setDataInicial((java.sql.Date) dataInicial);
		extTO.setDataFinal((java.sql.Date) dataFinal);

		ArrayList<ExtratoTO> extrato = new ArrayList<>();
		extrato = extDAO.consultaExtrato(extTO);

		for (int i = 0; i < extrato.size(); i++) {
			System.out.println("ID do Cliente: " + extrato.get(i).getIdCliente() + "\nValor Da Movimentacao: "
					+ extrato.get(i).getValorMovimentacao() + "\nValor Saldo Atual: " + extrato.get(i).getSaldoAtual()
					+ "\nTipo Movimentação: " + extrato.get(i).getIdTipoMovimentacao() + "\nData Movimentacao: "
					+ extrato.get(i).getData() + "\n-------------------------------------------------");
		}
	}

	public void createDate() {

		Date minhaData = new Date();
		long mili = minhaData.getTime();
		java.sql.Date dataSQL = new java.sql.Date(mili);

		setDataFinal(dataSQL);
		setDataInicial(dataSQL);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Extrato other = (Extrato) obj;
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
