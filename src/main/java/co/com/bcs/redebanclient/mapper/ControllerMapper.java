package co.com.bcs.redebanclient.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.com.bcs.redebanclient.controller.v1.entity.Anulacion;
import co.com.bcs.redebanclient.controller.v1.entity.Compra;
import co.com.bcs.redebanclient.controller.v1.entity.InfoCompra;
import co.com.bcs.redebanclient.controller.v1.entity.Tarjeta;
import co.com.bcs.redebanclient.services.dto.DatosAnulacion;
import co.com.bcs.redebanclient.services.dto.DatosCompra;
import co.com.bcs.redebanclient.services.dto.InformacionCompra;
import co.com.bcs.redebanclient.services.dto.ReversoAnulacion;
import co.com.bcs.redebanclient.services.dto.ReversoCompra;

@Mapper(componentModel = "spring")
public interface ControllerMapper {

    @Mapping(target = "tipoDocumento", source = "compra.cliente.tipoDocumento")
    @Mapping(target = "numeroDocumento", source = "compra.cliente.numeroDocumento")
    @Mapping(target = "numeroTarjeta", source = "compra.tarjeta.numero")
    @Mapping(target = "cvcTarjeta", source = "compra.tarjeta.cvc")
    @Mapping(target = "fechaExpiracion", source = "compra.tarjeta")
    @Mapping(target = "franquicia", source = "compra.tarjeta.franquicia")
    @Mapping(target = "cuotas", source = "compra.pago.cuotas")
    @Mapping(target = "monto", source = "compra.pago.monto")
    @Mapping(target = "referencia", source = "compra.pago.referencia")
    @Mapping(target = "codigoTransaccion", source = "codigoTx")
    DatosCompra toDatosCompra(Compra compra, Long codigoTx);

  

    @Mapping(target = "tipoDocumento", source = "compra.cliente.tipoDocumento")
    @Mapping(target = "numeroDocumento", source = "compra.cliente.numeroDocumento")
    @Mapping(target = "numeroTarjeta", source = "compra.tarjeta.numero")
    @Mapping(target = "cvcTarjeta", source = "compra.tarjeta.cvc")
    @Mapping(target = "fechaExpiracion", source = "compra.tarjeta")
    @Mapping(target = "franquicia", source = "compra.tarjeta.franquicia")
    @Mapping(target = "cuotas", source = "compra.pago.cuotas")
    @Mapping(target = "monto", source = "compra.pago.monto")
    @Mapping(target = "referencia", source = "compra.pago.referencia")
    @Mapping(target = "codigoTransaccion", source = "codigoTx")
    @Mapping(target = "descripcionReverso", source = "descripcionReverso")
    ReversoCompra toReversoCompra(Compra compra, Long codigoTx, String descripcionReverso);

    InfoCompra toInfoCompra(InformacionCompra informacionCompra);

    @Mapping(target = "tipoDocumento", source = "anulacion.cliente.tipoDocumento")
    @Mapping(target = "numeroDocumento", source = "anulacion.cliente.numeroDocumento")
    @Mapping(target = "numeroTarjeta", source = "anulacion.tarjeta.numero")
    @Mapping(target = "cvcTarjeta", source = "anulacion.tarjeta.cvc")
    @Mapping(target = "fechaExpiracion", source = "anulacion.tarjeta")
    @Mapping(target = "franquicia", source = "anulacion.tarjeta.franquicia")
    @Mapping(target = "cuotas", source = "anulacion.pago.cuotas")
    @Mapping(target = "monto", source = "anulacion.pago.monto")
    @Mapping(target = "referencia", source = "anulacion.pago.referencia")
    @Mapping(target = "codigoTransaccion", source = "codigoTx")
    @Mapping(target = "numAprobacion", source = "anulacion.datosAnulacion.numAprobacion")
    @Mapping(target = "idTransaccionAutorizador", source = "anulacion.datosAnulacion.idTransaccionAutorizador")
    @Mapping(target = "razonAnulacion", source = "anulacion.datosAnulacion.razonAnulacion")
    DatosAnulacion toAnulacion(Anulacion anulacion, Long codigoTx);

    @Mapping(target = "tipoDocumento", source = "anulacion.cliente.tipoDocumento")
    @Mapping(target = "numeroDocumento", source = "anulacion.cliente.numeroDocumento")
    @Mapping(target = "numeroTarjeta", source = "anulacion.tarjeta.numero")
    @Mapping(target = "cvcTarjeta", source = "anulacion.tarjeta.cvc")
    @Mapping(target = "fechaExpiracion", source = "anulacion.tarjeta")
    @Mapping(target = "franquicia", source = "anulacion.tarjeta.franquicia")
    @Mapping(target = "cuotas", source = "anulacion.pago.cuotas")
    @Mapping(target = "monto", source = "anulacion.pago.monto")
    @Mapping(target = "referencia", source = "anulacion.pago.referencia")
    @Mapping(target = "codigoTransaccion", source = "codigoTx")
    @Mapping(target = "numAprobacion", source = "anulacion.datosAnulacion.numAprobacion")
    @Mapping(target = "idTransaccionAutorizador", source = "anulacion.datosAnulacion.idTransaccionAutorizador")
    @Mapping(target = "razonAnulacion", source = "anulacion.datosAnulacion.razonAnulacion")
    @Mapping(target = "descripcionReverso", source = "descripcionReverso")
    ReversoAnulacion toReversoAnulacion(Anulacion anulacion, String descripcionReverso, Long codigoTx);

    default String toFechaExpiracion(Tarjeta tarjeta) {

        if (tarjeta == null) {
            return "";
        }
        return tarjeta.getAnioExpiracion() + "-" + tarjeta.getMesExpiracion() + "-01";

    }

}
