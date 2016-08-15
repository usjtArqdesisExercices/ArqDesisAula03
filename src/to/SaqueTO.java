package to;

import java.sql.Date;

public class SaqueTO {
	
	public int		idCliente;
	public double	valorSaque;
	public double	saldoAtual;
	public Date		data;
	
	public double getValorSaque() {
		return valorSaque;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public double getSaldoAtual() {
		return saldoAtual;
	}

	public Date getData() {
		return data;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public void setSaldoAtual(double saldoAtual) {
		this.saldoAtual = saldoAtual;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	public void setValorSaque(double valorSaque) {
		this.valorSaque = valorSaque;
	}

}
