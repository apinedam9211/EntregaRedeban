package co.com.bcs.redebanclient.services;

import co.com.bcs.redebanclient.services.dto.DatosAnulacion;
import co.com.bcs.redebanclient.services.dto.InformacionCompra;
import co.com.bcs.redebanclient.services.dto.ReversoAnulacion;

public interface AnulacionService {
     
    InformacionCompra anular(DatosAnulacion datosAnulacion);

    InformacionCompra reversarAnular(ReversoAnulacion reversoAnulacion);

}
