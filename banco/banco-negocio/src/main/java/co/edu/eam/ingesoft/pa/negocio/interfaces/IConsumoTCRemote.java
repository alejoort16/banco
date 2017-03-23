package co.edu.eam.ingesoft.pa.negocio.interfaces;

import entidades.ConsumoTarjetaCredito;

public interface IConsumoTCRemote {
	
	public ConsumoTarjetaCredito buscarConsumo(int num);
	public void GuardarConsumo(ConsumoTarjetaCredito consumo);

}
