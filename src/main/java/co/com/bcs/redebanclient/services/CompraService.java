package co.com.bcs.redebanclient.services;

import co.com.bcs.redebanclient.services.dto.DatosCompra;
import co.com.bcs.redebanclient.services.dto.InformacionCompra;
import co.com.bcs.redebanclient.services.dto.ReversoCompra;

public interface CompraService {
    
 public InformacionCompra comprar(DatosCompra compra);

 public InformacionCompra reversarComprar (ReversoCompra compra);


}
