package com.example.demosoap.converter;


import com.example.demosoap.gen.Empleado;
import com.example.demosoap.model.EmpleadoModel;
import org.springframework.stereotype.Component;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Component
public class EmpleadoConverter {

    public EmpleadoModel convertEmpleadoToEmpleadoModel(Empleado empleado) {
        EmpleadoModel empleadoModel = new EmpleadoModel();
        empleadoModel.setId(empleado.getId());
        empleadoModel.setNombres(empleado.getNombres());
        empleadoModel.setApellidos(empleado.getApellidos());
        empleadoModel.setTipoDocumento(empleado.getTipoDocumento());
        empleadoModel.setNumeroDocumento(empleado.getNumeroDocumento());

        XMLGregorianCalendar xmlGregorianCalendarFechaNacimiento = empleado.getFechaNacimiento();
        Date dateNacimiento = xmlGregorianCalendarFechaNacimiento.toGregorianCalendar().getTime();

        XMLGregorianCalendar xmlGregorianCalendarFechaVinculacion = empleado.getFechaVinculacion();
        Date dateVinculacion = xmlGregorianCalendarFechaVinculacion.toGregorianCalendar().getTime();

        empleadoModel.setFechaNacimiento(dateNacimiento);
        empleadoModel.setFechaVinculacion(dateVinculacion);
        empleadoModel.setCargo(empleado.getCargo());
        empleadoModel.setSalario(empleado.getSalario());
        return empleadoModel;
    }

    public Empleado convertEmpleadoModelToProduct(EmpleadoModel empleadoModel) throws DatatypeConfigurationException {
        Empleado empleado = new Empleado();
        empleado.setId(empleadoModel.getId());
        empleado.setNombres(empleadoModel.getNombres());
        empleado.setApellidos(empleadoModel.getApellidos());
        empleado.setTipoDocumento(empleadoModel.getTipoDocumento());
        empleado.setNumeroDocumento(empleadoModel.getNumeroDocumento());

        DatatypeFactory datatypeFactoryNacimiento = DatatypeFactory.newInstance();
        GregorianCalendar gregorianCalendarNacimiento = new GregorianCalendar();
        gregorianCalendarNacimiento.setTime(empleadoModel.getFechaNacimiento());
        XMLGregorianCalendar xmlGregorianCalendarNacimiento = datatypeFactoryNacimiento.newXMLGregorianCalendar(gregorianCalendarNacimiento);

        empleado.setFechaNacimiento(xmlGregorianCalendarNacimiento);

        DatatypeFactory datatypeFactoryVinculacion = DatatypeFactory.newInstance();
        GregorianCalendar gregorianCalendarVinculacion = new GregorianCalendar();
        gregorianCalendarVinculacion.setTime(empleadoModel.getFechaNacimiento());
        XMLGregorianCalendar xmlGregorianCalendarVinculacion = datatypeFactoryVinculacion.newXMLGregorianCalendar(gregorianCalendarVinculacion);

        empleado.setFechaVinculacion(xmlGregorianCalendarVinculacion);
        empleado.setCargo(empleadoModel.getCargo());
        empleado.setSalario(empleadoModel.getSalario());
        return empleado;
    }

    public List<EmpleadoModel> convertProductsToProductModels(List<Empleado> empleados) {
        List<EmpleadoModel> productModels = new ArrayList<EmpleadoModel>();
        for (Empleado emp : empleados) {
            productModels.add(convertEmpleadoToEmpleadoModel(emp));
        }
        return productModels;
    }

    public List<Empleado> convertEmpleadoModelsToEmpleado(List<EmpleadoModel> empleadoModels) throws DatatypeConfigurationException {
        List<Empleado> empleados = new ArrayList<Empleado>();
        for (EmpleadoModel empleadoModel : empleadoModels) {
            empleados.add(convertEmpleadoModelToProduct(empleadoModel));
        }
        return empleados;
    }
}
