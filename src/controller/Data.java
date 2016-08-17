package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Data {

	public Data() {

	}

	public int retornaDias(String dataInicial, String dataFinal) {

		int totalDias = 0;

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar data1 = Calendar.getInstance();
		Calendar data2 = Calendar.getInstance();
		try {
			data1.setTime(sdf.parse(dataInicial));
			data2.setTime(sdf.parse(dataFinal));
		} catch (java.text.ParseException e) {
		}
		totalDias = data2.get(Calendar.DAY_OF_YEAR)
				- data1.get(Calendar.DAY_OF_YEAR);

		return totalDias;
	}

	@SuppressWarnings("deprecation")
	public Date retornaData(int dias) {

		Date minhaData = new Date();
		long mili = minhaData.getTime();
		java.sql.Date dataSQL = new java.sql.Date(mili);

		dataSQL.setDate(dataSQL.getDate() - dias);

		return dataSQL;
	}

	public Date retornaTipoDate(String data) {

		// Transforma a Data recebida, para importar no SQL
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date minhaData = null;

		try {
			minhaData = formatter.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		long mili = minhaData.getTime();
		java.sql.Date dataSQL = new java.sql.Date(mili);

		return dataSQL;
	}

	public Date retornaDataHoje() {

		Date minhaData = new Date();
		long mili = minhaData.getTime();
		java.sql.Date dataSQL = new java.sql.Date(mili);

		return dataSQL;
	}

	public boolean verificaMaiorData(Date dataInicial, Date dataFinal) {

		long miliInicial = dataInicial.getTime();
		long miliFinal = dataFinal.getTime();
		
		if (miliFinal < miliInicial) {

			return true;
		} else {

			return false;
		}
	}
	
	public boolean seDataMaiorQueHoje(Date dataInicial, Date dataFinal) {

		long miliInicial = dataInicial.getTime();
		long miliFinal = dataFinal.getTime();
		long miliHoje = retornaDataHoje().getTime();
		
		if (miliFinal > miliHoje || miliInicial > miliHoje) {

			return true;
		} else {

			return false;
		}
	}

}
