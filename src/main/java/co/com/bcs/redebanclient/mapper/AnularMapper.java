package co.com.bcs.redebanclient.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Value;

import co.com.bcs.redebanclient.services.dto.DatosAnulacion;
import co.com.bcs.redebanclient.services.dto.InformacionCompra;
import co.com.bcs.wsclient.objects.anulacion.TipoRespuesta;
import co.com.bcs.wsclient.objects.anulacion.TipoSolicitudCancelacion;
import lombok.AccessLevel;
import lombok.Getter;

@Mapper(componentModel = "spring")
public abstract class AnularMapper {

    @Value("${constants.idTerminal}")
    private @Getter(value = AccessLevel.PROTECTED) String idTerminal;

    @Value("${constants.idAdquiriente}")
    private @Getter(value = AccessLevel.PROTECTED) String idAdquiriente;

    @Mapping(target = "fechaPosteo", source = "infoCompraResp.fechaPosteo")
    @Mapping(target = "fechaTransaccion", source = "infoCompraResp.fechaTransaccion")
    @Mapping(target = "numeroAprobacion", source = "infoCompraResp.numAprobacion")
    @Mapping(target = "costoTransaccion", source = "infoCompraResp.costoTransaccion") 
    @Mapping(target="idTransaccionAutorizador" , source="idTransaccionAutorizador")
    public abstract InformacionCompra toInformacionCompra(TipoRespuesta respuesta);


    @Mapping(source = "tipoDocumento", target = "idPersona.tipoDocumento")
    @Mapping(source = "numeroDocumento", target = "idPersona.numDocumento")
    @Mapping(source = "numeroTarjeta", target = "infoMedioPago.idTarjetaCredito.numTarjeta")
    @Mapping(source = "cvcTarjeta", target = "infoMedioPago.idTarjetaCredito.codVerificacion")
    @Mapping(source = "fechaExpiracion", target = "infoMedioPago.idTarjetaCredito.fechaExpiracion", dateFormat = "yyyy-MM-dd")
    @Mapping(source = "franquicia", target = "infoMedioPago.idTarjetaCredito.franquicia")
    @Mapping(source = "cuotas", target = "infoCompra.cantidadCuotas")
    @Mapping(source = "monto", target = "infoCompra.montoTotal")
    @Mapping(source = "referencia", target = "infoCompra.referencia")
    @Mapping(source = "codigoTransaccion", target = "cabeceraSolicitud.infoPuntoInteraccion.idTransaccionTerminal")
    @Mapping(target = "cabeceraSolicitud.infoPuntoInteraccion.tipoTerminal", constant = "WEB")
    @Mapping(target = "cabeceraSolicitud.infoPuntoInteraccion.idTerminal", expression = "java(getIdTerminal())")
    @Mapping(target = "cabeceraSolicitud.infoPuntoInteraccion.idAdquiriente", expression = "java(getIdAdquiriente())")
    @Mapping(target = "cabeceraSolicitud.infoPuntoInteraccion.modoCapturaPAN", constant = "ALMACENADO")
    @Mapping(target = "cabeceraSolicitud.infoPuntoInteraccion.capacidadPIN", constant = "VIRTUAL")
    @Mapping(target = "infoRefCancelacion.numAprobacion" , source="numAprobacion")
    @Mapping(target = "infoRefCancelacion.idTransaccionAutorizador" , source="idTransaccionAutorizador")
    public abstract TipoSolicitudCancelacion toTipoSolicitudCancelacion(DatosAnulacion datosAnulacion);
    }
